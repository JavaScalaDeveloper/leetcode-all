# [1254. 统计封闭岛屿的数目](https://leetcode.cn/problems/number-of-closed-islands)

## 题目描述

<p>二维矩阵 <code>grid</code>&nbsp;由 <code>0</code>&nbsp;（土地）和 <code>1</code>&nbsp;（水）组成。岛是由最大的4个方向连通的 <code>0</code>&nbsp;组成的群，封闭岛是一个&nbsp;<code>完全</code> 由1包围（左、上、右、下）的岛。</p>

<p>请返回 <em>封闭岛屿</em> 的数目。</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1254.Number%20of%20Closed%20Islands/images/sample_3_1610.png" style="height: 151px; width: 240px;" /></p>

<pre>
<strong>输入：</strong>grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
<strong>输出：</strong>2
<strong>解释：</strong>
灰色区域的岛屿是封闭岛屿，因为这座岛屿完全被水域包围（即被 1 区域包围）。</pre>

<p><strong>示例 2：</strong></p>

<p><img src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1254.Number%20of%20Closed%20Islands/images/sample_4_1610.png" style="height: 98px; width: 160px;" /></p>

<pre>
<strong>输入：</strong>grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
<strong>输出：</strong>1
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>grid = [[1,1,1,1,1,1,1],
&nbsp;            [1,0,0,0,0,0,1],
&nbsp;            [1,0,1,1,1,0,1],
&nbsp;            [1,0,1,0,1,0,1],
&nbsp;            [1,0,1,1,1,0,1],
&nbsp;            [1,0,0,0,0,0,1],
             [1,1,1,1,1,1,1]]
<strong>输出：</strong>2
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= grid.length, grid[0].length &lt;= 100</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;=1</code></li>
</ul>

## 解法

**方法一：DFS**

遍历矩阵，对于每个陆地，我们进行深度优先搜索，找到与其相连的所有陆地，然后判断是否存在边界上的陆地，如果存在，则不是封闭岛屿，否则是封闭岛屿，答案加一。

最后返回答案即可。

时间复杂度O(m \times n)，空间复杂度O(m \times n)。其中m和n分别是矩阵的行数和列数。

**方法二：并查集**

我们可以用并查集维护每一块连通的陆地。

遍历矩阵，如果当前位置是在边界上，我们将其与虚拟节点m \times n连接。如果当前位置是陆地，我们将其与下方和右方的陆地连接。

接着，我们再次遍历矩阵，对于每一块陆地，如果其根节点就是本身，那么答案加一。

最后返回答案即可。

时间复杂度O(m \times n \times \alpha(m \times n))，空间复杂度O(m \times n)。其中m和n分别是矩阵的行数和列数。

### **Java**

```java
class Solution {
    private int m;
    private int n;
    private int[][] grid;

    public int closedIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    ans += dfs(i, j);
                }
            }
        }
        return ans;
    }

    private int dfs(int i, int j) {
        int res = i > 0 && i < m - 1 && j > 0 && j < n - 1 ? 1 : 0;
        grid[i][j] = 1;
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 0) {
                res &= dfs(x, y);
            }
        }
        return res;
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
    public int closedIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        UnionFind uf = new UnionFind(m * n + 1);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    uf.union(i * n + j, m * n);
                }
                if (grid[i][j] == 0) {
                    if (i + 1 < m && grid[i + 1][j] == 0) {
                        uf.union(i * n + j, (i + 1) * n + j);
                    }
                    if (j + 1 < n && grid[i][j + 1] == 0) {
                        uf.union(i * n + j, i * n + j + 1);
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0 && uf.find(i * n + j) == i * n + j) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}
```
