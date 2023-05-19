# [505. 迷宫 II](https://leetcode.cn/problems/the-maze-ii)

[English Version](/solution/0500-0599/0505.The%20Maze%20II/README_EN.md)

## 题目描述

<p><strong>迷宫</strong>中有一个球，它有空地 (表示为 <code>0</code>) 和墙 (表示为 <code>1</code>)。球可以<strong>向上</strong>、<strong>向下</strong>、<strong>向左</strong>或<strong>向右</strong>滚过空地，但直到撞上墙之前它都不会停止滚动。当球停止时，它才可以选择下一个滚动方向。</p>

<p>给定 <code>m × n</code> 的<strong>迷宫</strong>(<code>maze</code>)，球的<strong>起始位置&nbsp;</strong>(<code>start = [start<sub>row</sub>, start<sub>col</sub>]</code>) 和<strong>目的地&nbsp;</strong>(<code>destination = [destination<sub>row</sub>, destination<sub>col</sub>]</code>)，返回球在<strong>目的地&nbsp;</strong>(<code>destination</code>) 停止的最短<strong>距离</strong>。如果球不能在<strong>目的地&nbsp;</strong>(<code>destination</code>) 停止，返回 <code>-1</code>。</p>

<p><strong>距离</strong>是指球从起始位置 ( 不包括 ) 到终点 ( 包括 ) 所经过的<strong>空地</strong>数。</p>

<p>你可以假设<strong>迷宫的边界都是墙&nbsp;</strong>( 见例子 )。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0505.The%20Maze%20II/images/maze1-1-grid.jpg" /></p>

<pre>
<strong>输入:</strong> maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [4,4]
<strong>输出:</strong> 12
<strong>解析:</strong> 一条最短路径 : left -&gt; down -&gt; left -&gt; down -&gt; right -&gt; down -&gt; right。
             总距离为 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12。

</pre>

<p><strong>示例&nbsp;2:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0505.The%20Maze%20II/images/maze1-2-grid.jpg" /></p>

<pre>
<strong>输入:</strong> maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [3,2]
<strong>输出:</strong> -1
<strong>解析:</strong> 球不可能在目的地停下来。注意，你可以经过目的地，但不能在那里停下来。
</pre>

<p><strong>示例&nbsp;3:</strong></p>

<pre>
<strong>输入:</strong> maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]], start = [4,3], destination = [0,1]
<strong>输出:</strong> -1
</pre>

<p>&nbsp;</p>

<p><strong>注意:</strong></p>

<ul>
	<li><code>m == maze.length</code></li>
	<li><code>n == maze[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
	<li><code>maze[i][j]</code>&nbsp;是&nbsp;<code>0</code>&nbsp;或&nbsp;<code>1</code>.</li>
	<li><code>start.length == 2</code></li>
	<li><code>destination.length == 2</code></li>
	<li><code>0 &lt;= start<sub>row</sub>, destination<sub>row</sub>&nbsp;&lt; m</code></li>
	<li><code>0 &lt;= start<sub>col</sub>, destination<sub>col</sub>&nbsp;&lt; n</code></li>
	<li>球和目的地都存在于一个空地中，它们最初不会处于相同的位置。</li>
	<li>
	<p data-group="1-1">迷宫<strong>至少包含两个空地</strong>。</p>
	</li>
</ul>

## 解法

BFS。

注意在一般的广度优先搜索中，我们不会经过同一个节点超过一次，但在这道题目中，只要从起始位置到当前节点的步数 step 小于之前记录的最小步数 `dist[i, j]`，我们就会把 `(i, j)` 再次加入队列中。

### **Java**

```java
class Solution {
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int m = maze.length;
        int n = maze[0].length;
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[start[0]][start[1]] = 0;
        Deque<int[]> q = new LinkedList<>();
        q.offer(start);
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int i = p[0], j = p[1];
            for (int k = 0; k < 4; ++k) {
                int x = i, y = j, step = dist[i][j];
                int a = dirs[k], b = dirs[k + 1];
                while (
                    x + a >= 0 && x + a < m && y + b >= 0 && y + b < n && maze[x + a][y + b] == 0) {
                    x += a;
                    y += b;
                    ++step;
                }
                if (step < dist[x][y]) {
                    dist[x][y] = step;
                    q.offer(new int[] {x, y});
                }
            }
        }
        return dist[destination[0]][destination[1]] == Integer.MAX_VALUE
            ? -1
            : dist[destination[0]][destination[1]];
    }
}
```
