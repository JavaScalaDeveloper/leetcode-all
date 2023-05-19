# [417. 太平洋大西洋水流问题](https://leetcode.cn/problems/pacific-atlantic-water-flow)

[English Version](/solution/0400-0499/0417.Pacific%20Atlantic%20Water%20Flow/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一个 <code>m × n</code> 的矩形岛屿，与 <strong>太平洋</strong> 和 <strong>大西洋</strong> 相邻。&nbsp;<strong>“太平洋”&nbsp;</strong>处于大陆的左边界和上边界，而 <strong>“大西洋”</strong> 处于大陆的右边界和下边界。</p>

<p>这个岛被分割成一个由若干方形单元格组成的网格。给定一个 <code>m x n</code> 的整数矩阵&nbsp;<code>heights</code>&nbsp;，&nbsp;<code>heights[r][c]</code>&nbsp;表示坐标 <code>(r, c)</code> 上单元格 <strong>高于海平面的高度</strong> 。</p>

<p>岛上雨水较多，如果相邻单元格的高度 <strong>小于或等于</strong> 当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。水可以从海洋附近的任何单元格流入海洋。</p>

<p>返回网格坐标 <code>result</code>&nbsp;的 <strong>2D 列表</strong> ，其中&nbsp;<code>result[i] = [r<sub>i</sub>, c<sub>i</sub>]</code>&nbsp;表示雨水从单元格 <code>(ri, ci)</code> 流动 <strong>既可流向太平洋也可流向大西洋</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0417.Pacific%20Atlantic%20Water%20Flow/images/waterflow-grid.jpg" /></p>

<pre>
<strong>输入:</strong> heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
<strong>输出:</strong> [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入:</strong> heights = [[2,1],[1,2]]
<strong>输出:</strong> [[0,0],[0,1],[1,0],[1,1]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == heights.length</code></li>
	<li><code>n == heights[r].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>0 &lt;= heights[r][c] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

反向寻找，从海洋开始逆流，只要比该陆地高的地方就能逆流。最后是寻找两个海洋的水流都能经过的陆地即为结果。

### **Java**

```java
class Solution {
    private int[][] heights;
    private int m;
    private int n;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        m = heights.length;
        n = heights[0].length;
        this.heights = heights;
        Deque<int[]> q1 = new LinkedList<>();
        Deque<int[]> q2 = new LinkedList<>();
        Set<Integer> vis1 = new HashSet<>();
        Set<Integer> vis2 = new HashSet<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || j == 0) {
                    vis1.add(i * n + j);
                    q1.offer(new int[] {i, j});
                }
                if (i == m - 1 || j == n - 1) {
                    vis2.add(i * n + j);
                    q2.offer(new int[] {i, j});
                }
            }
        }
        bfs(q1, vis1);
        bfs(q2, vis2);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int x = i * n + j;
                if (vis1.contains(x) && vis2.contains(x)) {
                    ans.add(Arrays.asList(i, j));
                }
            }
        }
        return ans;
    }

    private void bfs(Deque<int[]> q, Set<Integer> vis) {
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            for (int k = q.size(); k > 0; --k) {
                int[] p = q.poll();
                for (int i = 0; i < 4; ++i) {
                    int x = p[0] + dirs[i];
                    int y = p[1] + dirs[i + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && !vis.contains(x * n + y)
                        && heights[x][y] >= heights[p[0]][p[1]]) {
                        vis.add(x * n + y);
                        q.offer(new int[] {x, y});
                    }
                }
            }
        }
    }
}
```

### **TypeScript**
