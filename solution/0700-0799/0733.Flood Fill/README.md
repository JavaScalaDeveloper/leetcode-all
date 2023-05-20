# [733. 图像渲染](https://leetcode.cn/problems/flood-fill)

## 题目描述

<p>有一幅以&nbsp;<code>m x n</code>&nbsp;的二维整数数组表示的图画&nbsp;<code>image</code>&nbsp;，其中&nbsp;<code>image[i][j]</code>&nbsp;表示该图画的像素值大小。</p>

<p>你也被给予三个整数 <code>sr</code> ,&nbsp; <code>sc</code> 和 <code>newColor</code> 。你应该从像素&nbsp;<code>image[sr][sc]</code>&nbsp;开始对图像进行 上色<strong>填充</strong> 。</p>

<p>为了完成<strong> 上色工作</strong> ，从初始像素开始，记录初始坐标的 <strong>上下左右四个方向上</strong> 像素值与初始坐标相同的相连像素点，接着再记录这四个方向上符合条件的像素点与他们对应 <strong>四个方向上</strong> 像素值与初始坐标相同的相连像素点，……，重复该过程。将所有有记录的像素点的颜色值改为&nbsp;<code>newColor</code>&nbsp;。</p>

<p>最后返回 <em>经过上色渲染后的图像&nbsp;</em>。</p>

<p><strong>示例 1:</strong></p>

<p><img src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0733.Flood%20Fill/images/flood1-grid.jpg" /></p>

<pre>
<strong>输入:</strong> image = [[1,1,1],[1,1,0],[1,0,1]]，sr = 1, sc = 1, newColor = 2
<strong>输出:</strong> [[2,2,2],[2,2,0],[2,0,1]]
<strong>解析:</strong> 在图像的正中间，(坐标(sr,sc)=(1,1)),在路径上所有符合条件的像素点的颜色都被更改成2。
注意，右下角的像素没有更改为2，因为它不是在上下左右四个方向上与初始点相连的像素点。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, newColor = 2
<strong>输出:</strong> [[2,2,2],[2,2,2]]
</pre>

<p><strong>提示:</strong></p>

<ul>
	<li><code>m == image.length</code></li>
	<li><code>n == image[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 50</code></li>
	<li><code>0 &lt;= image[i][j], newColor &lt; 2<sup>16</sup></code></li>
	<li><code>0 &lt;= sr &lt;&nbsp;m</code></li>
	<li><code>0 &lt;= sc &lt;&nbsp;n</code></li>
</ul>

## 解法

**方法一：Flood fill 算法**

Flood fill 算法是从一个区域中提取若干个连通的点与其他相邻区域区分开（或分别染成不同颜色）的经典算法。因为其思路类似洪水从一个区域扩散到所有能到达的区域而得名。

最简单的实现方法是采用 DFS 的递归方法，也可以采用 BFS 的迭代来实现。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别为图像的行数和列数。

DFS：

BFS：

### **Java**

DFS：

```java
class Solution {
    private int[] dirs = {-1, 0, 1, 0, -1};
    private int[][] image;
    private int nc;
    private int oc;

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        nc = color;
        oc = image[sr][sc];
        this.image = image;
        dfs(sr, sc);
        return image;
    }

    private void dfs(int i, int j) {
        if (i < 0 || i >= image.length || j < 0 || j >= image[0].length || image[i][j] != oc || image[i][j] == nc) {
            return;
        }
        image[i][j] = nc;
        for (int k = 0; k < 4; ++k) {
            dfs(i + dirs[k], j + dirs[k + 1]);
        }
    }
}
```

BFS：

```java
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if (image[sr][sc] == color) {
            return image;
        }
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {sr, sc});
        int oc = image[sr][sc];
        image[sr][sc] = color;
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int i = p[0], j = p[1];
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < image.length && y >= 0 && y < image[0].length
                    && image[x][y] == oc) {
                    q.offer(new int[] {x, y});
                    image[x][y] = color;
                }
            }
        }
        return image;
    }
}
```

DFS：

BFS：

DFS：

BFS：
