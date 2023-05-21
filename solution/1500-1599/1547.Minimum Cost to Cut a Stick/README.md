# [1547. 切棍子的最小成本](https://leetcode.cn/problems/minimum-cost-to-cut-a-stick)

## 题目描述

<p>有一根长度为 <code>n</code> 个单位的木棍，棍上从 <code>0</code> 到 <code>n</code> 标记了若干位置。例如，长度为 <strong>6</strong> 的棍子可以标记如下：</p>

<p><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1547.Minimum%20Cost%20to%20Cut%20a%20Stick/images/statement.jpg" style="height: 111px; width: 521px;" /></p>

<p>给你一个整数数组 <code>cuts</code> ，其中 <code>cuts[i]</code> 表示你需要将棍子切开的位置。</p>

<p>你可以按顺序完成切割，也可以根据需要更改切割的顺序。</p>

<p>每次切割的成本都是当前要切割的棍子的长度，切棍子的总成本是历次切割成本的总和。对棍子进行切割将会把一根木棍分成两根较小的木棍（这两根木棍的长度和就是切割前木棍的长度）。请参阅第一个示例以获得更直观的解释。</p>

<p>返回切棍子的 <strong>最小总成本</strong> 。</p>



<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1547.Minimum%20Cost%20to%20Cut%20a%20Stick/images/e1.jpg" style="height: 284px; width: 350px;" /></p>

<pre>
<strong>输入：</strong>n = 7, cuts = [1,3,4,5]
<strong>输出：</strong>16
<strong>解释：</strong>按 [1, 3, 4, 5] 的顺序切割的情况如下所示：
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1547.Minimum%20Cost%20to%20Cut%20a%20Stick/images/e11.jpg" style="height: 284px; width: 350px;" />
第一次切割长度为 7 的棍子，成本为 7 。第二次切割长度为 6 的棍子（即第一次切割得到的第二根棍子），第三次切割为长度 4 的棍子，最后切割长度为 3 的棍子。总成本为 7 + 6 + 4 + 3 = 20 。
而将切割顺序重新排列为 [3, 5, 1, 4] 后，总成本 = 16（如示例图中 7 + 4 + 3 + 2 = 16）。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 9, cuts = [5,6,1,4,2]
<strong>输出：</strong>22
<strong>解释：</strong>如果按给定的顺序切割，则总成本为 25 。总成本 <= 25 的切割顺序很多，例如，[4, 6, 5, 2, 1] 的总成本 = 22，是所有可能方案中成本最小的。</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>2 <= n <= 10^6</code></li>
	<li><code>1 <= cuts.length <= min(n - 1, 100)</code></li>
	<li><code>1 <= cuts[i] <= n - 1</code></li>
	<li><code>cuts</code> 数组中的所有整数都 <strong>互不相同</strong></li>
</ul>

## 解法

**方法一：动态规划（区间 DP）**

我们可以往切割点数组cuts中添加两个元素，分别是0和n，表示棍子的两端。然后我们对cuts数组进行排序，这样我们就可以将整个棍子切割为若干个区间，每个区间都有两个切割点。不妨设此时cuts数组的长度为m。

接下来，我们定义f[i][j]表示切割区间[cuts[i],..cuts[j]]的最小成本。

如果一个区间只有两个切割点，也就是说，我们无需切割这个区间，那么f[i][j] = 0。

否则，我们枚举区间的长度l，其中l等于切割点的数量减去1。然后我们枚举区间的左端点i，右端点j可以由i + l得到。对于每个区间，我们枚举它的切割点k，其中i \lt k \lt j，那么我们可以将区间[i, j]切割为[i, k]和[k, j]，此时的成本为f[i][k] + f[k][j] + cuts[j] - cuts[i]，我们取所有可能的k中的最小值，即为f[i][j]的值。

最后，我们返回f[0][m - 1]。

时间复杂度O(m^3)，空间复杂度O(m^2)。其中m为修改后的cuts数组的长度。

### **Java**

```java
class Solution {
    public int minCost(int n, int[] cuts) {
        List<Integer> nums = new ArrayList<>();
        for (int x : cuts) {
            nums.add(x);
        }
        nums.add(0);
        nums.add(n);
        Collections.sort(nums);
        int m = nums.size();
        int[][] f = new int[m][m];
        for (int l = 2; l < m; ++l) {
            for (int i = 0; i + l < m; ++i) {
                int j = i + l;
                f[i][j] = 1 << 30;
                for (int k = i + 1; k < j; ++k) {
                    f[i][j] = Math.min(f[i][j], f[i][k] + f[k][j] + nums.get(j) - nums.get(i));
                }
            }
        }
        return f[0][m - 1];
    }
}
```

```java
class Solution {
    public int minCost(int n, int[] cuts) {
        List<Integer> nums = new ArrayList<>();
        for (int x : cuts) {
            nums.add(x);
        }
        nums.add(0);
        nums.add(n);
        Collections.sort(nums);
        int m = nums.size();
        int[][] f = new int[m][m];
        for (int i = m - 1; i >= 0; --i) {
            for (int j = i + 2; j < m; ++j) {
                f[i][j] = 1 << 30;
                for (int k = i + 1; k < j; ++k) {
                    f[i][j] = Math.min(f[i][j], f[i][k] + f[k][j] + nums.get(j) - nums.get(i));
                }
            }
        }
        return f[0][m - 1];
    }
}
```
