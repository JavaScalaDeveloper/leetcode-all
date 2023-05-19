# [2662. 前往目标的最小代价](https://leetcode.cn/problems/minimum-cost-of-a-path-with-special-roads)

[English Version](/solution/2600-2699/2662.Minimum%20Cost%20of%20a%20Path%20With%20Special%20Roads/README_EN.md)

## 题目描述

<p>给你一个数组 <code>start</code> ，其中 <code>start = [startX, startY]</code> 表示你的初始位置位于二维空间上的 <code>(startX, startY)</code> 。另给你一个数组 <code>target</code> ，其中 <code>target = [targetX, targetY]</code> 表示你的目标位置 <code>(targetX, targetY)</code> 。</p>

<p>从位置 <code>(x1, y1)</code> 到空间中任一其他位置 <code>(x2, y2)</code> 的代价是 <code>|x2 - x1| + |y2 - y1|</code> 。</p>

<p>给你一个二维数组 <code>specialRoads</code> ，表示空间中存在的一些特殊路径。其中 <code>specialRoads[i] = [x1<sub>i</sub>, y1<sub>i</sub>, x2<sub>i</sub>, y2<sub>i</sub>, cost<sub>i</sub>]</code> 表示第 <code>i</code> 条特殊路径可以从 <code>(x1<sub>i</sub>, y1<sub>i</sub>)</code> 到 <code>(x2<sub>i</sub>, y2<sub>i</sub>)</code> ，但成本等于 <code>cost<sub>i</sub></code> 。你可以使用每条特殊路径任意次数。</p>

<p>返回从 <code>(startX, startY)</code> 到 <code>(targetX, targetY)</code> 所需的最小代价。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>start = [1,1], target = [4,5], specialRoads = [[1,2,3,3,2],[3,4,4,5,1]]
<strong>输出：</strong>5
<strong>解释：</strong>从 (1,1) 到 (4,5) 的最优路径如下：
- (1,1) -&gt; (1,2) ，移动的代价是 |1 - 1| + |2 - 1| = 1 。
- (1,2) -&gt; (3,3) ，移动使用第一条特殊路径，代价是 2 。
- (3,3) -&gt; (3,4) ，移动的代价是 |3 - 3| + |4 - 3| = 1.
- (3,4) -&gt; (4,5) ，移动使用第二条特殊路径，代价是 1 。
总代价是 1 + 2 + 1 + 1 = 5 。
可以证明无法以小于 5 的代价完成从 (1,1) 到 (4,5) 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>start = [3,2], target = [5,7], specialRoads = [[3,2,3,4,4],[3,3,5,5,5],[3,4,5,6,6]]
<strong>输出：</strong>7
<strong>解释：</strong>最优路径是不使用任何特殊路径，直接以 |5 - 3| + |7 - 2| = 7 的代价从初始位置到达目标位置。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>start.length == target.length == 2</code></li>
	<li><code>1 &lt;= startX &lt;= targetX &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= startY &lt;= targetY &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= specialRoads.length &lt;= 200</code></li>
	<li><code>specialRoads[i].length == 5</code></li>
	<li><code>startX &lt;= x1<sub>i</sub>, x2<sub>i</sub> &lt;= targetX</code></li>
	<li><code>startY &lt;= y1<sub>i</sub>, y2<sub>i</sub> &lt;= targetY</code></li>
	<li><code>1 &lt;= cost<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

**方法一：Dijkstra**

我们可以发现，对于访问到的每个坐标点 $(x, y)$，假设从起点到 $(x, y)$ 的最小代价为 $d$。如果选择直接移动到 $(targetX, targetY)$，那么总代价就是 $d + |x - targetX| + |y - targetY|$。如果选择经过某条特殊路径 $(x_1, y_1) \rightarrow (x_2, y_2)$，那么我们需要可以花费 $|x - x_1| + |y - y_1| + cost$ 的代价，从 $(x, y)$ 移动到 $(x_2, y_2)$。

因此，我们可以使用 Dijkstra 算法求出从起点到所有点的最小代价，然后从中选择最小的那个。

我们定义一个优先队列 $q$，队列中的每一个元素是一个三元组 $(d, x, y)$，表示从起点到 $(x, y)$ 的最小代价为 $d$。初始时，我们将 $(0, startX, startY)$ 加入队列中。

在每一步中，我们取出队首元素 $(d, x, y)$，此时我们可以更新答案，即 $ans = \min(ans, d + dist(x, y, targetX, targetY))$。然后我们枚举所有的特殊路径 $(x_1, y_1) \rightarrow (x_2, y_2)$，将 $(d + dist(x, y, x_1, y_1) + cost, x_2, y_2)$ 加入队列中。

最后当队列为空时，我们就可以得到答案。

时间复杂度 $O(n^2 \times \log n)$，空间复杂度 $O(n^2)$。其中 $n$ 是特殊路径的数量。

### **Java**

```java
class Solution {
    public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
        int ans = 1 << 30;
        int n = 1000000;
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        Set<Long> vis = new HashSet<>();
        q.offer(new int[] {0, start[0], start[1]});
        while (!q.isEmpty()) {
            var p = q.poll();
            int x = p[1], y = p[2];
            long k = 1L * x * n + y;
            if (vis.contains(k)) {
                continue;
            }
            vis.add(k);
            int d = p[0];
            ans = Math.min(ans, d + dist(x, y, target[0], target[1]));
            for (var r : specialRoads) {
                int x1 = r[0], y1 = r[1], x2 = r[2], y2 = r[3], cost = r[4];
                q.offer(new int[] {d + dist(x, y, x1, y1) + cost, x2, y2});
            }
        }
        return ans;
    }

    private int dist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
```
