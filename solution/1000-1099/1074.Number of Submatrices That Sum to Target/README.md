# [1074. 元素和为目标值的子矩阵数量](https://leetcode.cn/problems/number-of-submatrices-that-sum-to-target)

## 题目描述

<p>给出矩阵 <code>matrix</code> 和目标值 <code>target</code>，返回元素总和等于目标值的非空子矩阵的数量。</p>

<p>子矩阵 <code>x1, y1, x2, y2</code> 是满足 <code>x1 <= x <= x2</code> 且 <code>y1 <= y <= y2</code> 的所有单元 <code>matrix[x][y]</code> 的集合。</p>

<p>如果 <code>(x1, y1, x2, y2)</code> 和 <code>(x1', y1', x2', y2')</code> 两个子矩阵中部分坐标不同（如：<code>x1 != x1'</code>），那么这两个子矩阵也不同。</p>



<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1074.Number%20of%20Submatrices%20That%20Sum%20to%20Target/images/mate1.jpg" style="width: 242px; height: 242px;" /></p>

<pre>
<strong>输入：</strong>matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
<strong>输出：</strong>4
<strong>解释：</strong>四个只含 0 的 1x1 子矩阵。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>matrix = [[1,-1],[-1,1]], target = 0
<strong>输出：</strong>5
<strong>解释：</strong>两个 1x2 子矩阵，加上两个 2x1 子矩阵，再加上一个 2x2 子矩阵。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>matrix = [[904]], target = 0
<strong>输出：</strong>0
</pre>



<p><strong><strong>提示：</strong></strong></p>

<ul>
	<li><code>1 <= matrix.length <= 100</code></li>
	<li><code>1 <= matrix[0].length <= 100</code></li>
	<li><code>-1000 <= matrix[i] <= 1000</code></li>
	<li><code>-10^8 <= target <= 10^8</code></li>
</ul>

## 解法

**方法一：枚举上下边界 + 前缀和 + 哈希表**

我们可以枚举矩阵的上下边界i和j，每次算出当前上下边界内每列的元素和，记为数组col，然后问题就转换为如何在数组col中寻找和为目标值target的子数组个数。我们累加这些子数组的个数，就是题目要求的答案。

那么题目就变成了：给定一个数组nums和目标值target，计算有多少个子数组的和为target，我们可以通过函数f(nums, target)来求解。

函数f(nums, target)的计算方法如下：

-   定义一个哈希表d，用来记录出现过的前缀和以及其出现次数，初始时d[0] = 1；
-   初始化变量s = 0, cnt = 0，其中s表示前缀和，而cnt表示和为target的子数组个数；
-   从左到右遍历数组nums，对于当前遍历到的元素x，更新前缀和s = s + x，如果d[s - target]的值存在，那么更新cnt = cnt + d[s - target]，即子数组个数增加d[s - target]。然后更新哈希表中元素d[s]的值，即d[s] = d[s] + 1；继续遍历下一个元素；
-   遍历结束之后，返回子数组个数cnt。

时间复杂度O(m^2 \times n)，空间复杂度O(n)。其中m和n分别是矩阵的行数和列数。

### **Java**

```java
class Solution {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            int[] col = new int[n];
            for (int j = i; j < m; ++j) {
                for (int k = 0; k < n; ++k) {
                    col[k] += matrix[j][k];
                }
                ans += f(col, target);
            }
        }
        return ans;
    }

    private int f(int[] nums, int target) {
        Map<Integer, Integer> d = new HashMap<>();
        d.put(0, 1);
        int s = 0, cnt = 0;
        for (int x : nums) {
            s += x;
            cnt += d.getOrDefault(s - target, 0);
            d.merge(s, 1, Integer::sum);
        }
        return cnt;
    }
}
```
