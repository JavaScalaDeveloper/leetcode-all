# [73. 矩阵置零](https://leetcode.cn/problems/set-matrix-zeroes)

## 题目描述

<p>给定一个&nbsp;<code><em>m</em> x <em>n</em></code> 的矩阵，如果一个元素为 <strong>0 </strong>，则将其所在行和列的所有元素都设为 <strong>0</strong> 。请使用 <strong><a href="http://baike.baidu.com/item/%E5%8E%9F%E5%9C%B0%E7%AE%97%E6%B3%95" target="_blank">原地</a></strong> 算法<strong>。</strong></p>

<ul>
</ul>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0073.Set%20Matrix%20Zeroes/images/mat1.jpg" style="width: 450px; height: 169px;" />
<pre>
<strong>输入：</strong>matrix = [[1,1,1],[1,0,1],[1,1,1]]
<strong>输出：</strong>[[1,0,1],[0,0,0],[1,0,1]]
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0073.Set%20Matrix%20Zeroes/images/mat2.jpg" style="width: 450px; height: 137px;" />
<pre>
<strong>输入：</strong>matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
<strong>输出：</strong>[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[0].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>-2<sup>31</sup> &lt;= matrix[i][j] &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<p><strong>进阶：</strong></p>

<ul>
	<li>一个直观的解决方案是使用 &nbsp;<code>O(<em>m</em><em>n</em>)</code>&nbsp;的额外空间，但这并不是一个好的解决方案。</li>
	<li>一个简单的改进方案是使用 <code>O(<em>m</em>&nbsp;+&nbsp;<em>n</em>)</code> 的额外空间，但这仍然不是最好的解决方案。</li>
	<li>你能想出一个仅使用常量空间的解决方案吗？</li>
</ul>

## 解法

**方法一：数组标记**

我们分别用数组 `rows` 和 `cols` 标记待清零的行和列。

然后再遍历一遍矩阵，将 `rows` 和 `cols` 中标记的行和列对应的元素清零。

时间复杂度O(m× n)，空间复杂度O(m+n)。其中m和n分别为矩阵的行数和列数。

**方法二：原地标记**

方法一中使用了额外的数组标记待清零的行和列，实际上我们也可以直接用矩阵的第一行和第一列来标记，不需要开辟额外的数组空间。

由于第一行、第一列用来做标记，它们的值可能会因为标记而发生改变，因此，我们需要额外的变量i0,j0来标记第一行、第一列是否需要被清零。

时间复杂度O(m× n)，空间复杂度O(1)。其中m和n分别为矩阵的行数和列数。

### **Java**

```java
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[] rows = new boolean[m];
        boolean[] cols = new boolean[n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 0) {
                    rows[i] = true;
                    cols[j] = true;
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (rows[i] || cols[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
```

```java
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean i0 = false, j0 = false;
        for (int j = 0; j < n; ++j) {
            if (matrix[0][j] == 0) {
                i0 = true;
                break;
            }
        }
        for (int i = 0; i < m; ++i) {
            if (matrix[i][0] == 0) {
                j0 = true;
                break;
            }
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (i0) {
            for (int j = 0; j < n; ++j) {
                matrix[0][j] = 0;
            }
        }
        if (j0) {
            for (int i = 0; i < m; ++i) {
                matrix[i][0] = 0;
            }
        }
    }
}
```
