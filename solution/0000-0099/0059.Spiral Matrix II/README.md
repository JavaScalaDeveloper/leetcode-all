# [59. 螺旋矩阵 II](https://leetcode.cn/problems/spiral-matrix-ii)

[English Version](/solution/0000-0099/0059.Spiral%20Matrix%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个正整数 <code>n</code> ，生成一个包含 <code>1</code> 到 <code>n<sup>2</sup></code> 所有元素，且元素按顺时针顺序螺旋排列的 <code>n x n</code> 正方形矩阵 <code>matrix</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0059.Spiral%20Matrix%20II/images/spiraln.jpg" style="width: 242px; height: 242px;" />
<pre>
<strong>输入：</strong>n = 3
<strong>输出：</strong>[[1,2,3],[8,9,4],[7,6,5]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>[[1]]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= n <= 20</code></li>
</ul>

## 解法

**方法一：模拟**

直接模拟螺旋矩阵的生成过程。

定义一个二维数组 `ans`，用于存储螺旋矩阵。用 `i` 和 `j` 分别表示当前位置的行号和列号，用 `k` 表示当前的方向编号，`dirs` 表示方向编号与方向的对应关系。

从 `1` 开始，依次填入矩阵中的每个位置。每次填入一个位置后，计算下一个位置的行号和列号，如果下一个位置不在矩阵中或者已经被填过，则改变方向，再计算下一个位置的行号和列号。

时间复杂度 $O(n^2)$，其中 $n$ 是矩阵的边长。忽略输出数组不计，空间复杂度 $O(1)$。

### **Java**

```java
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int i = 0, j = 0, k = 0;
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int v = 1; v <= n * n; ++v) {
            ans[i][j] = v;
            int x = i + dirs[k][0], y = j + dirs[k][1];
            if (x < 0 || y < 0 || x >= n || y >= n || ans[x][y] > 0) {
                k = (k + 1) % 4;
                x = i + dirs[k][0];
                y = j + dirs[k][1];
            }
            i = x;
            j = y;
        }
        return ans;
    }
}
```

### **TypeScript**
