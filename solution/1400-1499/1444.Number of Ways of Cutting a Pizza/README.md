# [1444. 切披萨的方案数](https://leetcode.cn/problems/number-of-ways-of-cutting-a-pizza)

## 题目描述

<p>给你一个&nbsp;<code>rows x cols</code>&nbsp;大小的矩形披萨和一个整数 <code>k</code>&nbsp;，矩形包含两种字符：&nbsp;<code>&#39;A&#39;</code> （表示苹果）和&nbsp;<code>&#39;.&#39;</code>&nbsp;（表示空白格子）。你需要切披萨 <code>k-1</code> 次，得到&nbsp;<code>k</code>&nbsp;块披萨并送给别人。</p>

<p>切披萨的每一刀，先要选择是向垂直还是水平方向切，再在矩形的边界上选一个切的位置，将披萨一分为二。如果垂直地切披萨，那么需要把左边的部分送给一个人，如果水平地切，那么需要把上面的部分送给一个人。在切完最后一刀后，需要把剩下来的一块送给最后一个人。</p>

<p>请你返回确保每一块披萨包含&nbsp;<strong>至少</strong>&nbsp;一个苹果的切披萨方案数。由于答案可能是个很大的数字，请你返回它对 10^9 + 7 取余的结果。</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1444.Number%20of%20Ways%20of%20Cutting%20a%20Pizza/images/ways_to_cut_apple_1.png" style="height: 378px; width: 500px;"></strong></p>

<pre><strong>输入：</strong>pizza = [&quot;A..&quot;,&quot;AAA&quot;,&quot;...&quot;], k = 3
<strong>输出：</strong>3 
<strong>解释：</strong>上图展示了三种切披萨的方案。注意每一块披萨都至少包含一个苹果。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>pizza = [&quot;A..&quot;,&quot;AA.&quot;,&quot;...&quot;], k = 3
<strong>输出：</strong>1
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>pizza = [&quot;A..&quot;,&quot;A..&quot;,&quot;...&quot;], k = 1
<strong>输出：</strong>1
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= rows, cols &lt;= 50</code></li>
	<li><code>rows ==&nbsp;pizza.length</code></li>
	<li><code>cols ==&nbsp;pizza[i].length</code></li>
	<li><code>1 &lt;= k &lt;= 10</code></li>
	<li><code>pizza</code>&nbsp;只包含字符&nbsp;<code>&#39;A&#39;</code>&nbsp;和&nbsp;<code>&#39;.&#39;</code>&nbsp;。</li>
</ul>

## 解法

**方法一：二维前缀和 + 记忆化搜索**

我们可以使用二维前缀和来快速计算出每个子矩形中苹果的数量，定义s[i][j]表示矩形前i行，前j列的子矩形中苹果的数量，那么s[i][j]可以由s[i-1][j],s[i][j-1],s[i-1][j-1]三个子矩形的苹果数量求得，具体的计算方法如下：


s[i][j] = s[i-1][j] + s[i][j-1] - s[i-1][j-1] + (pizza[i-1][j-1] == 'A')


其中pizza[i-1][j-1]表示矩形中第i行，第j列的字符，如果是苹果，则为1，否则为0。

接下来，我们设计一个函数dfs(i, j, k)，表示将矩形(i, j, m-1, n-1)切k-1刀，得到k块披萨的方案数，其中(i, j)和(m-1, n-1)分别是矩形的左上角和右下角的坐标。函数dfs(i, j, k)的计算方法如下：

-   如果k = 0，表示没有刀可以切了，那么我们需要判断矩形中是否有苹果，如果有苹果，则返回1，否则返回0；
-   如果k \gt 0，我们需要枚举最后一刀的切法，如果最后一刀是水平切，那么我们需要枚举切的位置x，其中i \lt x \lt m，如果s[x][n] - s[i][n] - s[x][j] + s[i][j] \gt 0，说明切出来的上面一块披萨中有苹果，我们累加dfs(x, j, k-1)的值到答案中；如果最后一刀是垂直切，那么我们需要枚举切的位置y，其中j \lt y \lt n，如果s[m][y] - s[i][y] - s[m][j] + s[i][j] \gt 0，说明切出来的左边一块披萨中有苹果，我们累加dfs(i, y, k-1)的值到答案中。

最终的答案即为dfs(0, 0, k)的值。

为了避免重复计算，我们可以使用记忆化搜索的方法，用一个三维数组f来记录dfs(i, j, k)的值。当我们需要计算dfs(i, j, k)的值时，如果f[i][j][k]不为-1，说明我们之前已经计算过了，直接返回f[i][j][k]即可，否则我们按照上面的方法计算dfs(i, j, k)的值，并将结果保存到f[i][j][k]中。

时间复杂度O(m \times n \times k \times (m + n))，空间复杂度O(m \times n \times k)。其中m,n分别是矩形的行数和列数。

相似题目：[2312. 卖木头块](/solution/2300-2399/2312.Selling%20Pieces%20of%20Wood/README.md)

### **Java**

```java
class Solution {
    private int m;
    private int n;
    private int[][] s;
    private Integer[][][] f;
    private final int mod = (int) 1e9 + 7;

    public int ways(String[] pizza, int k) {
        m = pizza.length;
        n = pizza[0].length();
        s = new int[m + 1][n + 1];
        f = new Integer[m][n][k];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                int x = pizza[i - 1].charAt(j - 1) == 'A' ? 1 : 0;
                s[i][j] = s[i - 1][j] + s[i][j - 1] - s[i - 1][j - 1] + x;
            }
        }
        return dfs(0, 0, k - 1);
    }

    private int dfs(int i, int j, int k) {
        if (k == 0) {
            return s[m][n] - s[i][n] - s[m][j] + s[i][j] > 0 ? 1 : 0;
        }
        if (f[i][j][k] != null) {
            return f[i][j][k];
        }
        int ans = 0;
        for (int x = i + 1; x < m; ++x) {
            if (s[x][n] - s[i][n] - s[x][j] + s[i][j] > 0) {
                ans = (ans + dfs(x, j, k - 1)) % mod;
            }
        }
        for (int y = j + 1; y < n; ++y) {
            if (s[m][y] - s[i][y] - s[m][j] + s[i][j] > 0) {
                ans = (ans + dfs(i, y, k - 1)) % mod;
            }
        }
        return f[i][j][k] = ans;
    }
}
```
