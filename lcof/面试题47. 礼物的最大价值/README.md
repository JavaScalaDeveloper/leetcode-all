# [面试题 47. 礼物的最大价值](https://leetcode.cn/problems/li-wu-de-zui-da-jie-zhi-lcof/)

## 题目描述

<p>在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> 
<code>[
&nbsp; [1,3,1],
&nbsp; [1,5,1],
&nbsp; [4,2,1]
]</code>
<strong>输出:</strong> <code>12
</code><strong>解释:</strong> 路径 1&rarr;3&rarr;5&rarr;2&rarr;1 可以拿到最多价值的礼物</pre>

<p>&nbsp;</p>

<p>提示：</p>

<ul>
	<li><code>0 &lt; grid.length &lt;= 200</code></li>
	<li><code>0 &lt; grid[0].length &lt;= 200</code></li>
</ul>

## 解法

**方法一：动态规划**

我们定义 $f[i][j]$ 为从棋盘左上角走到 $(i-1, j-1)$ 的礼物最大累计价值，那么 $f[i][j]$ 的值由 $f[i-1][j]$ 和 $f[i][j-1]$ 决定，即从上方格子和左方格子走过来的两个方案中选择一个价值较大的方案。因此我们可以写出动态规划转移方程：

$$
f[i][j] = max(f[i-1][j], f[i][j-1]) + grid[i-1][j-1]
$$

答案为 $f[m][n]$。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别为棋盘的行数和列数。

我们注意到 $f[i][j]$ 只与 $f[i-1][j]$ 和 $f[i][j-1]$ 有关，因此我们可以仅用两行数组 $f[2][n+1]$ 来存储状态，从而将空间复杂度优化到 $O(n)$。

### **Java**

```java
class Solution {
    public int maxValue(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] f = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]) + grid[i - 1][j - 1];
            }
        }
        return f[m][n];
    }
}
```

```java
class Solution {
    public int maxValue(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] f = new int[2][n + 1];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                f[i & 1][j] = Math.max(f[i & 1 ^ 1][j], f[i & 1][j - 1]) + grid[i - 1][j - 1];
            }
        }
        return f[m & 1][n];
    }
}
```
