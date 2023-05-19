# [面试题 29. 顺时针打印矩阵](https://leetcode.cn/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/)

## 题目描述

<p>输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>matrix = [[1,2,3],[4,5,6],[7,8,9]]
<strong>输出：</strong>[1,2,3,6,9,8,7,4,5]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>matrix =&nbsp;[[1,2,3,4],[5,6,7,8],[9,10,11,12]]
<strong>输出：</strong>[1,2,3,4,8,12,11,10,9,5,6,7]
</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<ul>
	<li><code>0 &lt;= matrix.length &lt;= 100</code></li>
	<li><code>0 &lt;= matrix[i].length&nbsp;&lt;= 100</code></li>
</ul>

<p>注意：本题与主站 54 题相同：<a href="https://leetcode.cn/problems/spiral-matrix/">https://leetcode.cn/problems/spiral-matrix/</a></p>

## 解法

**方法一：模拟**

我们用 $i$ 和 $j$ 分别表示当前访问到的元素的行和列，用 $k$ 表示当前的方向，用数组或哈希表 $vis$ 记录每个元素是否被访问过。每次我们访问到一个元素后，将其标记为已访问，然后按照当前的方向前进一步，如果前进一步后发现越界或者已经访问过，则改变方向继续前进，直到遍历完整个矩阵。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

**方法二：逐层模拟**

从外往里一圈一圈遍历并存储矩阵元素即可。

时间复杂度 $O(m \times n)$，空间复杂度 $O(1)$。其中 $m$ 和 $n$ 分别是矩阵的行数和列数。

<!-- tabs:start -->

### **Python3**





### **Java**

```java
class Solution {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new int[] {};
        }
        int m = matrix.length, n = matrix[0].length;
        boolean[][] vis = new boolean[m][n];
        int[] ans = new int[m * n];
        int i = 0, j = 0, k = 0;
        int[] dirs = {0, 1, 0, -1, 0};
        for (int h = 0; h < m * n; ++h) {
            ans[h] = matrix[i][j];
            vis[i][j] = true;
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x < 0 || y < 0 || x >= m || y >= n || vis[x][y]) {
                k = (k + 1) % 4;
                x = i + dirs[k];
                y = j + dirs[k + 1];
            }
            i = x;
            j = y;
        }
        return ans;
    }
}
```

```java
class Solution {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new int[] {};
        }
        int m = matrix.length, n = matrix[0].length;
        int top = 0, bottom = m - 1, left = 0, right = n - 1;
        int[] ans = new int[m * n];
        int k = 0;
        while (left <= right && top <= bottom) {
            for (int j = left; j <= right; ++j) {
                ans[k++] = matrix[top][j];
            }
            for (int i = top + 1; i <= bottom; ++i) {
                ans[k++] = matrix[i][right];
            }
            if (left < right && top < bottom) {
                for (int j = right - 1; j >= left; --j) {
                    ans[k++] = matrix[bottom][j];
                }
                for (int i = bottom - 1; i > top; --i) {
                    ans[k++] = matrix[i][left];
                }
            }
            ++top;
            --bottom;
            ++left;
            --right;
        }
        return ans;
    }
}
```





















### **...**

```

```


