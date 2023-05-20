# [1905. 统计子岛屿](https://leetcode.cn/problems/count-sub-islands)

## 题目描述

<p>给你两个 <code>m x n</code> 的二进制矩阵 <code>grid1</code> 和 <code>grid2</code> ，它们只包含 <code>0</code> （表示水域）和 <code>1</code> （表示陆地）。一个 <strong>岛屿</strong> 是由 <strong>四个方向</strong> （水平或者竖直）上相邻的 <code>1</code> 组成的区域。任何矩阵以外的区域都视为水域。</p>

<p>如果 <code>grid2</code> 的一个岛屿，被 <code>grid1</code> 的一个岛屿 <strong>完全</strong> 包含，也就是说 <code>grid2</code> 中该岛屿的每一个格子都被 <code>grid1</code> 中同一个岛屿完全包含，那么我们称 <code>grid2</code> 中的这个岛屿为 <strong>子岛屿</strong> 。</p>

<p>请你返回 <code>grid2</code> 中 <strong>子岛屿</strong> 的 <strong>数目</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1905.Count%20Sub%20Islands/images/test1.png" style="width: 493px; height: 205px;">
<pre><b>输入：</b>grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
<b>输出：</b>3
<strong>解释：</strong>如上图所示，左边为 grid1 ，右边为 grid2 。
grid2 中标红的 1 区域是子岛屿，总共有 3 个子岛屿。
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1905.Count%20Sub%20Islands/images/testcasex2.png" style="width: 491px; height: 201px;">
<pre><b>输入：</b>grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
<b>输出：</b>2 
<strong>解释：</strong>如上图所示，左边为 grid1 ，右边为 grid2 。
grid2 中标红的 1 区域是子岛屿，总共有 2 个子岛屿。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid1.length == grid2.length</code></li>
	<li><code>n == grid1[i].length == grid2[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 500</code></li>
	<li><code>grid1[i][j]</code> 和 <code>grid2[i][j]</code> 都要么是 <code>0</code> 要么是 <code>1</code> 。</li>
</ul>

## 解法

BFS、DFS 或者并查集。

BFS - Flood Fill 算法：

DFS：

并查集：

### **Java**

BFS - Flood Fill 算法：

```java
class Solution {
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid1.length;
        int n = grid1[0].length;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid2[i][j] == 1 && bfs(i, j, m, n, grid1, grid2)) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    private boolean bfs(int i, int j, int m, int n, int[][] grid1, int[][] grid2) {
        Queue<int[]> q = new ArrayDeque<>();
        grid2[i][j] = 0;
        q.offer(new int[]{i, j});
        boolean ans = true;
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            int[] p = q.poll();
            i = p[0];
            j = p[1];
            if (grid1[i][j] == 0) {
                ans = false;
            }
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k];
                int y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid2[x][y] == 1) {
                    q.offer(new int[]{x, y});
                    grid2[x][y] = 0;
                }
            }
        }
        return ans;
    }
}
```

DFS：

```java
class Solution {
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid1.length;
        int n = grid1[0].length;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid2[i][j] == 1 && dfs(i, j, m, n, grid1, grid2)) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    private boolean dfs(int i, int j, int m, int n, int[][] grid1, int[][] grid2) {
        boolean ans = grid1[i][j] == 1;
        grid2[i][j] = 0;
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k];
            int y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && grid2[x][y] == 1 && !dfs(x, y, m, n, grid1, grid2)) {
                ans = false;
            }
        }
        return ans;
    }
}
```

并查集：

```java
class Solution {
    private int[] p;

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int m = grid1.length;
        int n = grid1[0].length;
        p = new int[m * n];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        int[] dirs = {1, 0, 1};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid2[i][j] == 1) {
                    for (int k = 0; k < 2; ++k) {
                        int x = i + dirs[k];
                        int y = j + dirs[k + 1];
                        if (x < m && y < n && grid2[x][y] == 1) {
                            p[find(x * n + y)] = find(i * n + j);
                        }
                    }
                }
            }
        }
        int[] s = new int[m * n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid2[i][j] == 1) {
                    s[find(i * n + j)] = 1;
                }
            }
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int root = find(i * n + j);
                if (s[root] == 1 && grid1[i][j] == 0) {
                    s[root] = 0;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < s.length; ++i) {
            ans += s[i];
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

BFS - Flood Fill 算法：

DFS：

BFS - Flood Fill 算法：

DFS：
