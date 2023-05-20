# [1263. 推箱子](https://leetcode.cn/problems/minimum-moves-to-move-a-box-to-their-target-location)

## 题目描述

<p>「推箱子」是一款风靡全球的益智小游戏，玩家需要将箱子推到仓库中的目标位置。</p>

<p>游戏地图用大小为&nbsp;<code>m x n</code>&nbsp;的网格 <code>grid</code> 表示，其中每个元素可以是墙、地板或者是箱子。</p>

<p>现在你将作为玩家参与游戏，按规则将箱子&nbsp;<code>'B'</code>&nbsp;移动到目标位置&nbsp;<code>'T'</code> ：</p>

<ul>
	<li>玩家用字符&nbsp;<code>'S'</code>&nbsp;表示，只要他在地板上，就可以在网格中向上、下、左、右四个方向移动。</li>
	<li>地板用字符&nbsp;<code>'.'</code>&nbsp;表示，意味着可以自由行走。</li>
	<li>墙用字符&nbsp;<code>'#'</code>&nbsp;表示，意味着障碍物，不能通行。&nbsp;</li>
	<li>箱子仅有一个，用字符&nbsp;<code>'B'</code>&nbsp;表示。相应地，网格上有一个目标位置&nbsp;<code>'T'</code>。</li>
	<li>玩家需要站在箱子旁边，然后沿着箱子的方向进行移动，此时箱子会被移动到相邻的地板单元格。记作一次「推动」。</li>
	<li>玩家无法越过箱子。</li>
</ul>

<p>返回将箱子推到目标位置的最小 <strong>推动</strong> 次数，如果无法做到，请返回&nbsp;<code>-1</code>。</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1263.Minimum%20Moves%20to%20Move%20a%20Box%20to%20Their%20Target%20Location/images/sample_1_1620.png" style="height: 335px; width: 500px;" /></strong></p>

<pre>
<strong>输入：</strong>grid = [["#","#","#","#","#","#"],
             ["#","T","#","#","#","#"],
&nbsp;            ["#",".",".","B",".","#"],
&nbsp;            ["#",".","#","#",".","#"],
&nbsp;            ["#",".",".",".","S","#"],
&nbsp;            ["#","#","#","#","#","#"]]
<strong>输出：</strong>3
<strong>解释：</strong>我们只需要返回推箱子的次数。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>grid = [["#","#","#","#","#","#"],
             ["#","T","#","#","#","#"],
&nbsp;            ["#",".",".","B",".","#"],
&nbsp;            ["#","#","#","#",".","#"],
&nbsp;            ["#",".",".",".","S","#"],
&nbsp;            ["#","#","#","#","#","#"]]
<strong>输出：</strong>-1
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>grid = [["#","#","#","#","#","#"],
&nbsp;            ["#","T",".",".","#","#"],
&nbsp;            ["#",".","#","B",".","#"],
&nbsp;            ["#",".",".",".",".","#"],
&nbsp;            ["#",".",".",".","S","#"],
&nbsp;            ["#","#","#","#","#","#"]]
<strong>输出：</strong>5
<strong>解释：</strong>向下、向左、向左、向上再向上。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 20</code></li>
	<li><code>grid</code> 仅包含字符&nbsp;<code>'.'</code>, <code>'#'</code>,&nbsp; <code>'S'</code> , <code>'T'</code>, 以及&nbsp;<code>'B'</code>。</li>
	<li><code>grid</code>&nbsp;中&nbsp;<code>'S'</code>, <code>'B'</code>&nbsp;和&nbsp;<code>'T'</code>&nbsp;各只能出现一个。</li>
</ul>

## 解法

**方法一：双端队列 BFS**

我们把玩家的位置和箱子的位置看成一个状态，即 $(s_i, s_j, b_i, b_j)$，其中 $(s_i, s_j)$ 是玩家的位置，而 $(b_i, b_j)$ 是箱子的位置。在代码实现上，我们定义一个函数 $f(i, j)$，它将二维坐标 $(i, j)$ 映射到一个一维的状态编号，即 $f(i, j) = i \times n + j$，其中 $n$ 是网格的列数。那么玩家和箱子的状态就是 $(f(s_i, s_j), f(b_i, b_j))$。

我们首先遍历网格，找到玩家和箱子的初始位置，记为 $(s_i, s_j)$ 和 $(b_i, b_j)$。

然后，我们定义一个双端队列 $q$，其中每个元素都是一个三元组 $(f(s_i, s_j), f(b_i, b_j), d)$，表示玩家位于 $(s_i, s_j)$，箱子位于 $(b_i, b_j)$，并且已经进行了 $d$ 次推动。初始时，我们将 $(f(s_i, s_j), f(b_i, b_j), 0)$ 加入队列 $q$。

另外，我们用一个二维数组 $vis$ 记录每个状态是否已经访问过，初始时 $vis[f(s_i, s_j), f(b_i, b_j)]$ 标记为已访问。

接下来，我们开始进行广度优先搜索。

在每一步搜索中，我们取出队头元素 $(f(s_i, s_j), f(b_i, b_j), d)$，并检查是否满足 $grid[b_i][b_j] = 'T'$，如果是，说明箱子已经被推到目标位置，此时将 $d$ 作为答案返回即可。

否则，我们枚举玩家的下一步移动方向，玩家新的位置记为 $(s_x, s_y)$，如果 $(s_x, s_y)$ 是一个合法的位置，我们判断此时 $(s_x, s_y)$ 是否与箱子的位置 $(b_i, b_j)$ 相同：

-   如果相同，说明当前玩家到达了箱子的位置，并且推动箱子往前走了一步。箱子新的位置为 $(b_x, b_y)$，如果 $(b_x, b_y)$ 是一个合法的位置，且状态 $(f(s_x, s_y), f(b_x, b_y))$ 没有被访问过，那么我们就将 $(f(s_x, s_y), f(b_x, b_y), d + 1)$ 加入队列 $q$ 的末尾，并将 $vis[f(s_x, s_y), f(b_x, b_y)]$ 标记为已访问。
-   如果不同，说明当前玩家没有推动箱子，那么我们只需要判断状态 $(f(s_x, s_y), f(b_i, b_j))$ 是否被访问过，如果没有被访问过，那么我们就将 $(f(s_x, s_y), f(b_i, b_j), d)$ 加入队列 $q$ 的头部，并将 $vis[f(s_x, s_y), f(b_i, b_j)]$ 标记为已访问。

继续进行广度优先搜索，直到队列为空为止。

> 注意，如果推动箱子，那么推动次数 $d$ 需要加 $1$，并且新的状态加入到队列 $q$ 的末尾；如果没推动箱子，那么推动次数 $d$ 不变，新的状态加入到队列 $q$ 的头部。

最后，如果没有找到合法的推动方案，那么返回 $-1$。

时间复杂度 $O(m^2 \times n^2)$，空间复杂度 $O(m^2 \times n^2)$。其中 $m$ 和 $n$ 分别是网格的行数和列数。

### **Java**

```java
class Solution {
    private int m;
    private int n;
    private char[][] grid;

    public int minPushBox(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.grid = grid;
        int si = 0, sj = 0, bi = 0, bj = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 'S') {
                    si = i;
                    sj = j;
                } else if (grid[i][j] == 'B') {
                    bi = i;
                    bj = j;
                }
            }
        }
        int[] dirs = {-1, 0, 1, 0, -1};
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][] vis = new boolean[m * n][m * n];
        q.offer(new int[] {f(si, sj), f(bi, bj), 0});
        vis[f(si, sj)][f(bi, bj)] = true;
        while (!q.isEmpty()) {
            var p = q.poll();
            int d = p[2];
            bi = p[1] / n;
            bj = p[1] % n;
            if (grid[bi][bj] == 'T') {
                return d;
            }
            si = p[0] / n;
            sj = p[0] % n;
            for (int k = 0; k < 4; ++k) {
                int sx = si + dirs[k], sy = sj + dirs[k + 1];
                if (!check(sx, sy)) {
                    continue;
                }
                if (sx == bi && sy == bj) {
                    int bx = bi + dirs[k], by = bj + dirs[k + 1];
                    if (!check(bx, by) || vis[f(sx, sy)][f(bx, by)]) {
                        continue;
                    }
                    vis[f(sx, sy)][f(bx, by)] = true;
                    q.offer(new int[] {f(sx, sy), f(bx, by), d + 1});
                } else if (!vis[f(sx, sy)][f(bi, bj)]) {
                    vis[f(sx, sy)][f(bi, bj)] = true;
                    q.offerFirst(new int[] {f(sx, sy), f(bi, bj), d});
                }
            }
        }
        return -1;
    }

    private int f(int i, int j) {
        return i * n + j;
    }

    private boolean check(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n && grid[i][j] != '#';
    }
}
```
