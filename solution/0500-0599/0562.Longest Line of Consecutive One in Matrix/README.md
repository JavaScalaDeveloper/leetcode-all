# [562. 矩阵中最长的连续 1 线段](https://leetcode.cn/problems/longest-line-of-consecutive-one-in-matrix)

## 题目描述

<p>给定一个&nbsp;<code>m x n</code>&nbsp;的二进制矩阵 <code>mat</code><b>&nbsp;</b>，返回矩阵中最长的连续1线段。</p>

<p>这条线段可以是水平的、垂直的、对角线的或者反对角线的。</p>

<p><strong>示例 1:</strong></p>

<p><img src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0562.Longest%20Line%20of%20Consecutive%20One%20in%20Matrix/images/long1-grid.jpg" /></p>

<pre>
<strong>输入:</strong>&nbsp;mat = [[0,1,1,0],[0,1,1,0],[0,0,0,1]]
<strong>输出:</strong> 3
</pre>

<p><strong>示例 2:</strong></p>

<p><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0562.Longest%20Line%20of%20Consecutive%20One%20in%20Matrix/images/long2-grid.jpg" /></p>

<pre>
<strong>输入:</strong> mat = [[1,1,1,1],[0,1,1,0],[0,0,0,1]]
<strong>输出:</strong> 4
</pre>

<p><strong>提示:</strong></p>

<ul>
	<li><code>m == mat.length</code></li>
	<li><code>n == mat[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>4</sup></code></li>
	<li><code>mat[i][j]</code>&nbsp;不是&nbsp;<code>0</code>&nbsp;就是&nbsp;<code>1</code>.</li>
</ul>

## 解法

**方法一：动态规划**

我们定义 $f[i][j][k]$ 表示方向为 $k$，且以 $(i, j)$ 结尾的最长连续 $1$ 的长度。其中 $k$ 的取值范围为 $0, 1, 2, 3$，分别表示水平、垂直、对角线、反对角线。

> 我们也可以用四个二维数组分别表示四个方向的最长连续 $1$ 的长度。

遍历矩阵，当遇到 $1$ 时，更新 $f[i][j][k]$ 的值。对于每个位置 $(i, j)$，我们只需要更新其四个方向的值即可。然后更新答案。

时间复杂度 $O(m\times n)$，空间复杂度 $O(m\times n)$。其中 $m$ 和 $n$ 分别为矩阵的行数和列数。

### **Java**

```java
class Solution {
    public int longestLine(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] a = new int[m + 2][n + 2];
        int[][] b = new int[m + 2][n + 2];
        int[][] c = new int[m + 2][n + 2];
        int[][] d = new int[m + 2][n + 2];
        int ans = 0;
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (mat[i - 1][j - 1] == 1) {
                    a[i][j] = a[i - 1][j] + 1;
                    b[i][j] = b[i][j - 1] + 1;
                    c[i][j] = c[i - 1][j - 1] + 1;
                    d[i][j] = d[i - 1][j + 1] + 1;
                    ans = max(ans, a[i][j], b[i][j], c[i][j], d[i][j]);
                }
            }
        }
        return ans;
    }

    private int max(int... arr) {
        int ans = 0;
        for (int v : arr) {
            ans = Math.max(ans, v);
        }
        return ans;
    }
}
```
