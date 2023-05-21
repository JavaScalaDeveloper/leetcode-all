# [1102. 得分最高的路径](https://leetcode.cn/problems/path-with-maximum-minimum-value)

## 题目描述

<p>给定一个 <code>m x n</code> 的整数矩阵&nbsp;<code>grid</code>，返回从 <code>(0,0)</code> 开始到 <code>(m - 1, n - 1)</code> 在四个基本方向上移动的路径的最大 <strong>分数</strong> 。</p>

<p>一条路径的 <strong>分数</strong> 是该路径上的最小值。</p>

<ul>
	<li>例如，路径 <code>8 → 4 → 5 → 9</code> 的得分为 <code>4</code> 。</li>
</ul>

<p><strong>示例 1：</strong></p>

<p><img src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1102.Path%20With%20Maximum%20Minimum%20Value/images/maxgrid1.jpg" /></p>

<pre>
<strong>输入：</strong>grid = [[5,4,5],[1,2,6],[7,4,6]]
<strong>输出：</strong>4
<strong>解释：</strong>得分最高的路径用黄色突出显示。 
</pre>

<p><strong>示例 2：</strong></p>

<p><img src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1102.Path%20With%20Maximum%20Minimum%20Value/images/maxgrid2.jpg" /></p>

<pre>
<strong>输入：</strong>grid = [[2,2,1,2,2,2],[1,2,2,2,1,2]]
<strong>输出：</strong>2</pre>

<p><strong>示例 3：</strong></p>

<p><img src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1102.Path%20With%20Maximum%20Minimum%20Value/images/maxgrid3.jpg" /></p>

<pre>
<strong>输入：</strong>grid = [[3,4,6,3,4],[0,2,1,1,7],[8,8,3,2,7],[3,2,4,9,8],[4,1,2,0,0],[4,6,5,4,3]]
<strong>输出：</strong>3</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

**方法一：排序 + 并查集**

我们先将矩阵的每个元素构建一个三元组(v, i, j)，其中v表示元素值，而i和j分别表示元素在矩阵中的行和列。然后对这些三元组按照元素值从大到小进行排序，存放在列表q中。

接下来，我们按顺序从q中取出三元组，将其对应的元素值作为路径的分数，并且将该位置标记为已访问。然后我们检查该位置的上下左右四个相邻位置，如果某个相邻位置已经被访问过，那么我们就将该位置与当前位置进行合并。如果发现位置(0, 0)和位置(m - 1, n - 1)已经被合并，那么我们就可以直接返回当前路径的分数，即为答案。

时间复杂度O(m \times n \times (\log (m \times n) + \alpha(m \times n)))，其中m和n分别为矩阵的行数和列数。

### **Java**

```java
class Solution {
    private int[] p;

    public int maximumMinimumPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        p = new int[m * n];
        List<int[]> q = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                q.add(new int[] {grid[i][j], i, j});
                p[i * n + j] = i * n + j;
            }
        }
        q.sort((a, b) -> b[0] - a[0]);
        boolean[][] vis = new boolean[m][n];
        int[] dirs = {-1, 0, 1, 0, -1};
        int ans = 0;
        for (int i = 0; find(0) != find(m * n - 1); ++i) {
            int[] t = q.get(i);
            vis[t[1]][t[2]] = true;
            ans = t[0];
            for (int k = 0; k < 4; ++k) {
                int x = t[1] + dirs[k], y = t[2] + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && vis[x][y]) {
                    p[find(x * n + y)] = find(t[1] * n + t[2]);
                }
            }
        }
        return ans;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

```java
class UnionFind {
    private int[] p;
    private int[] size;

    public UnionFind(int n) {
        p = new int[n];
        size = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
            size[i] = 1;
        }
    }

    public int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    public void union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa != pb) {
            if (size[pa] > size[pb]) {
                p[pb] = pa;
                size[pa] += size[pb];
            } else {
                p[pa] = pb;
                size[pb] += size[pa];
            }
        }
    }
}

class Solution {
    public int maximumMinimumPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        UnionFind uf = new UnionFind(m * n);
        List<int[]> q = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                q.add(new int[] {grid[i][j], i, j});
            }
        }
        q.sort((a, b) -> b[0] - a[0]);
        boolean[][] vis = new boolean[m][n];
        int[] dirs = {-1, 0, 1, 0, -1};
        int ans = 0;
        for (int i = 0; uf.find(0) != uf.find(m * n - 1); ++i) {
            int[] t = q.get(i);
            vis[t[1]][t[2]] = true;
            ans = t[0];
            for (int k = 0; k < 4; ++k) {
                int x = t[1] + dirs[k], y = t[2] + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && vis[x][y]) {
                    uf.union(x * n + y, t[1] * n + t[2]);
                }
            }
        }
        return ans;
    }
}
```
