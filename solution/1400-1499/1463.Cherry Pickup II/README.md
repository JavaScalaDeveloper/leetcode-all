# [1463. 摘樱桃 II](https://leetcode.cn/problems/cherry-pickup-ii)

## 题目描述

<p>给你一个&nbsp;<code>rows x cols</code> 的矩阵&nbsp;<code>grid</code>&nbsp;来表示一块樱桃地。 <code>grid</code>&nbsp;中每个格子的数字表示你能获得的樱桃数目。</p>

<p>你有两个机器人帮你收集樱桃，机器人 1 从左上角格子 <code>(0,0)</code> 出发，机器人 2 从右上角格子 <code>(0, cols-1)</code> 出发。</p>

<p>请你按照如下规则，返回两个机器人能收集的最多樱桃数目：</p>

<ul>
	<li>从格子&nbsp;<code>(i,j)</code> 出发，机器人可以移动到格子&nbsp;<code>(i+1, j-1)</code>，<code>(i+1, j)</code> 或者&nbsp;<code>(i+1, j+1)</code>&nbsp;。</li>
	<li>当一个机器人经过某个格子时，它会把该格子内所有的樱桃都摘走，然后这个位置会变成空格子，即没有樱桃的格子。</li>
	<li>当两个机器人同时到达同一个格子时，它们中只有一个可以摘到樱桃。</li>
	<li>两个机器人在任意时刻都不能移动到 <code>grid</code>&nbsp;外面。</li>
	<li>两个机器人最后都要到达&nbsp;<code>grid</code>&nbsp;最底下一行。</li>
</ul>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1463.Cherry%20Pickup%20II/images/sample_1_1802.png" style="height: 182px; width: 139px;"></strong></p>

<pre><strong>输入：</strong>grid = [[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
<strong>输出：</strong>24
<strong>解释：</strong>机器人 1 和机器人 2 的路径在上图中分别用绿色和蓝色表示。
机器人 1 摘的樱桃数目为 (3 + 2 + 5 + 2) = 12 。
机器人 2 摘的樱桃数目为 (1 + 5 + 5 + 1) = 12 。
樱桃总数为： 12 + 12 = 24 。
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1400-1499/1463.Cherry%20Pickup%20II/images/sample_2_1802.png" style="height: 257px; width: 284px;"></strong></p>

<pre><strong>输入：</strong>grid = [[1,0,0,0,0,0,1],[2,0,0,0,0,3,0],[2,0,9,0,0,0,0],[0,3,0,5,4,0,0],[1,0,2,3,0,0,6]]
<strong>输出：</strong>28
<strong>解释：</strong>机器人 1 和机器人 2 的路径在上图中分别用绿色和蓝色表示。
机器人 1 摘的樱桃数目为 (1 + 9 + 5 + 2) = 17 。
机器人 2 摘的樱桃数目为 (1 + 3 + 4 + 3) = 11 。
樱桃总数为： 17 + 11 = 28 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>grid = [[1,0,0,3],[0,0,0,3],[0,0,3,3],[9,0,3,3]]
<strong>输出：</strong>22
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>grid = [[1,1],[1,1]]
<strong>输出：</strong>4
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>rows == grid.length</code></li>
	<li><code>cols == grid[i].length</code></li>
	<li><code>2 &lt;= rows, cols &lt;= 70</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 100&nbsp;</code></li>
</ul>

## 解法

**方法一：动态规划**

我们定义f[i][j_1][j_2]表示两个机器人分别在第i行的位置j_1和j_2时能够摘到的最多樱桃数目。初始时f[0][0][n-1] = grid[0][0] + grid[0][n-1]，其余值均为-1。答案为\max_{0 \leq j_1, j_2 < n} f[m-1][j_1][j_2]。

考虑f[i][j_1][j_2]，如果j_1 \neq j_2，那么机器人在第i行能摘到的樱桃数目为grid[i][j_1] + grid[i][j_2]；如果j_1 = j_2，那么机器人在第i行能摘到的樱桃数目为grid[i][j_1]。我们可以枚举两个机器人的上一个状态f[i-1][y1][y2]，其中y_1, y_2分别是两个机器人在第i-1行的位置，那么有y_1 \in \{j_1-1, j_1, j_1+1\}且y_2 \in \{j_2-1, j_2, j_2+1\}。状态转移方程如下：


f[i][j_1][j_2] = \max_{y_1 \in \{j_1-1, j_1, j_1+1\}, y_2 \in \{j_2-1, j_2, j_2+1\}} f[i-1][y_1][y_2] + \begin{cases} grid[i][j_1] + grid[i][j_2], & j_1 \neq j_2 \\ grid[i][j_1], & j_1 = j_2 \end{cases}


其中f[i-1][y_1][y_2]为-1时需要忽略。

最终的答案即为\max_{0 \leq j_1, j_2 < n} f[m-1][j_1][j_2]。

时间复杂度O(m \times n^2)，空间复杂度O(m \times n^2)。其中m和n分别是网格的行数和列数。

注意到f[i][j_1][j_2]的计算只和f[i-1][y_1][y_2]有关，因此我们可以使用滚动数组优化空间复杂度，空间复杂度优化后的时间复杂度为O(n^2)。

### **Java**

```java
class Solution {
    public int cherryPickup(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][][] f = new int[m][n][n];
        for (var g : f) {
            for (var h : g) {
                Arrays.fill(h, -1);
            }
        }
        f[0][0][n - 1] = grid[0][0] + grid[0][n - 1];
        for (int i = 1; i < m; ++i) {
            for (int j1 = 0; j1 < n; ++j1) {
                for (int j2 = 0; j2 < n; ++j2) {
                    int x = grid[i][j1] + (j1 == j2 ? 0 : grid[i][j2]);
                    for (int y1 = j1 - 1; y1 <= j1 + 1; ++y1) {
                        for (int y2 = j2 - 1; y2 <= j2 + 1; ++y2) {
                            if (y1 >= 0 && y1 < n && y2 >= 0 && y2 < n && f[i - 1][y1][y2] != -1) {
                                f[i][j1][j2] = Math.max(f[i][j1][j2], f[i - 1][y1][y2] + x);
                            }
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int j1 = 0; j1 < n; ++j1) {
            for (int j2 = 0; j2 < n; ++j2) {
                ans = Math.max(ans, f[m - 1][j1][j2]);
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int cherryPickup(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] f = new int[n][n];
        int[][] g = new int[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(f[i], -1);
            Arrays.fill(g[i], -1);
        }
        f[0][n - 1] = grid[0][0] + grid[0][n - 1];
        for (int i = 1; i < m; ++i) {
            for (int j1 = 0; j1 < n; ++j1) {
                for (int j2 = 0; j2 < n; ++j2) {
                    int x = grid[i][j1] + (j1 == j2 ? 0 : grid[i][j2]);
                    for (int y1 = j1 - 1; y1 <= j1 + 1; ++y1) {
                        for (int y2 = j2 - 1; y2 <= j2 + 1; ++y2) {
                            if (y1 >= 0 && y1 < n && y2 >= 0 && y2 < n && f[y1][y2] != -1) {
                                g[j1][j2] = Math.max(g[j1][j2], f[y1][y2] + x);
                            }
                        }
                    }
                }
            }
            int[][] t = f;
            f = g;
            g = t;
        }
        int ans = 0;
        for (int j1 = 0; j1 < n; ++j1) {
            for (int j2 = 0; j2 < n; ++j2) {
                ans = Math.max(ans, f[j1][j2]);
            }
        }
        return ans;
    }
}
```
