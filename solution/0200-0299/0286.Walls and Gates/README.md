# [286. 墙与门](https://leetcode.cn/problems/walls-and-gates)

## 题目描述

<p>你被给定一个 <code>m × n</code> 的二维网格 <code>rooms</code> ，网格中有以下三种可能的初始化值：</p>

<ol>
	<li><code>-1</code> 表示墙或是障碍物</li>
	<li><code>0</code> 表示一扇门</li>
	<li><code>INF</code> 无限表示一个空的房间。然后，我们用 <code>2<sup>31</sup> - 1 = 2147483647</code> 代表 <code>INF</code>。你可以认为通往门的距离总是小于 <code>2147483647</code> 的。</li>
</ol>

<p>你要给每个空房间位上填上该房间到 <strong>最近门的距离</strong> ，如果无法到达门，则填 <code>INF</code> 即可。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0286.Walls%20and%20Gates/images/grid.jpg" style="width: 500px; height: 223px;" />
<pre>
<strong>输入：</strong>rooms = [[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
<strong>输出：</strong>[[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>rooms = [[-1]]
<strong>输出：</strong>[[-1]]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>rooms = [[2147483647]]
<strong>输出：</strong>[[2147483647]]
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>rooms = [[0]]
<strong>输出：</strong>[[0]]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == rooms.length</code></li>
	<li><code>n == rooms[i].length</code></li>
	<li><code>1 <= m, n <= 250</code></li>
	<li><code>rooms[i][j]</code> 是 <code>-1</code>、<code>0</code> 或 <code>2<sup>31</sup> - 1</code></li>
</ul>

## 解法

BFS。

将所有门放入队列，依次向外扩进行宽搜。由于宽度优先搜索保证我们在搜索 d + 1 距离的位置时， 距离为 d 的位置都已经被搜索过了，所以到达每一个房间的时候一定是最短距离。

### **Java**

```java
class Solution {
    public void wallsAndGates(int[][] rooms) {
        int m = rooms.length;
        int n = rooms[0].length;
        Deque<int[]> q = new LinkedList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (rooms[i][j] == 0) {
                    q.offer(new int[] {i, j});
                }
            }
        }
        int d = 0;
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            ++d;
            for (int i = q.size(); i > 0; --i) {
                int[] p = q.poll();
                for (int j = 0; j < 4; ++j) {
                    int x = p[0] + dirs[j];
                    int y = p[1] + dirs[j + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && rooms[x][y] == Integer.MAX_VALUE) {
                        rooms[x][y] = d;
                        q.offer(new int[] {x, y});
                    }
                }
            }
        }
    }
}
```
