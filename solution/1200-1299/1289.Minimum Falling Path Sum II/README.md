# [1289. 下降路径最小和 II](https://leetcode.cn/problems/minimum-falling-path-sum-ii)

## 题目描述

<p>给你一个&nbsp;<code>n x n</code> 整数矩阵&nbsp;<code>arr</code>&nbsp;，请你返回 <strong>非零偏移下降路径</strong> 数字和的最小值。</p>

<p><strong>非零偏移下降路径</strong> 定义为：从&nbsp;<code>arr</code> 数组中的每一行选择一个数字，且按顺序选出来的数字中，相邻数字不在原数组的同一列。</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1289.Minimum%20Falling%20Path%20Sum%20II/images/falling-grid.jpg" style="width: 244px; height: 245px;" /></p>

<pre>
<strong>输入：</strong>arr = [[1,2,3],[4,5,6],[7,8,9]]
<strong>输出：</strong>13
<strong>解释：</strong>
所有非零偏移下降路径包括：
[1,5,9], [1,5,7], [1,6,7], [1,6,8],
[2,4,8], [2,4,9], [2,6,7], [2,6,8],
[3,4,8], [3,4,9], [3,5,7], [3,5,9]
下降路径中数字和最小的是&nbsp;[1,5,7] ，所以答案是&nbsp;13 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>grid = [[7]]
<strong>输出：</strong>7
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == grid.length == grid[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 200</code></li>
	<li><code>-99 &lt;= grid[i][j] &lt;= 99</code></li>
</ul>

## 解法

**方法一：动态规划**

我们定义f[i][j]表示前i行，且最后一个数字在第j列的最小数字和。那么状态转移方程为：


f[i][j] = min_{k \neq j} f[i - 1][k] + grid[i - 1][j]


其中k表示第i - 1行的数字在第k列，第i行第j列的数字为grid[i - 1][j]。

最后答案为f[n]中的最小值。

时间复杂度O(n^3)，空间复杂度O(n^2)。其中n为矩阵的行数。

实际上，我们也可以维护三个变量f,g和fp，分别表示前i行的最小数字和、第i行的第二小数字和以及第i行的最小数字在第fp列。这样我们就可以将时间复杂度降低到O(n^2)，空间复杂度降低到O(1)。

### **Java**

```java
class Solution {
    public int minFallingPathSum(int[][] grid) {
        int n = grid.length;
        int[][] f = new int[n + 1][n];
        final int inf = 1 << 30;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < n; ++j) {
                int x = inf;
                for (int k = 0; k < n; ++k) {
                    if (k != j) {
                        x = Math.min(x, f[i - 1][k]);
                    }
                }
                f[i][j] = grid[i - 1][j] + (x == inf ? 0 : x);
            }
        }
        int ans = inf;
        for (int x : f[n]) {
            ans = Math.min(ans, x);
        }
        return ans;
    }
}
```

```java
class Solution {
    public int minFallingPathSum(int[][] grid) {
        int f = 0, g = 0;
        int fp = -1;
        final int inf = 1 << 30;
        for (int[] row : grid) {
            int ff = inf, gg = inf;
            int ffp = -1;
            for (int j = 0; j < row.length; ++j) {
                int s = (j != fp ? f : g) + row[j];
                if (s < ff) {
                    gg = ff;
                    ff = s;
                    ffp = j;
                } else if (s < gg) {
                    gg = s;
                }
            }
            f = ff;
            g = gg;
            fp = ffp;
        }
        return f;
    }
}
```
