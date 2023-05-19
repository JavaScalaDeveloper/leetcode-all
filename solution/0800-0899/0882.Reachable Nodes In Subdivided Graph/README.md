# [882. 细分图中的可到达节点](https://leetcode.cn/problems/reachable-nodes-in-subdivided-graph)

[English Version](/solution/0800-0899/0882.Reachable%20Nodes%20In%20Subdivided%20Graph/README_EN.md)

## 题目描述

<p>给你一个无向图（<strong>原始图</strong>），图中有 <code>n</code> 个节点，编号从 <code>0</code> 到 <code>n - 1</code> 。你决定将图中的每条边 <strong>细分</strong> 为一条节点链，每条边之间的新节点数各不相同。</p>

<p>图用由边组成的二维数组 <code>edges</code> 表示，其中&nbsp;<code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>, cnt<sub>i</sub>]</code> 表示原始图中节点&nbsp;<code>u<sub>i</sub></code> 和&nbsp;<code>v<sub>i</sub></code> 之间存在一条边，<code>cnt<sub>i</sub></code> 是将边 <strong>细分</strong> 后的新节点总数。注意，<code>cnt<sub>i</sub> == 0</code> 表示边不可细分。</p>

<p>要 <strong>细分</strong> 边 <code>[ui, vi]</code> ，需要将其替换为 <code>(cnt<sub>i</sub> + 1)</code> 条新边，和&nbsp;<code>cnt<sub>i</sub></code> 个新节点。新节点为 <code>x<sub>1</sub></code>, <code>x<sub>2</sub></code>, ..., <code>x<sub>cnt<sub>i</sub></sub></code> ，新边为 <code>[u<sub>i</sub>, x<sub>1</sub>]</code>, <code>[x<sub>1</sub>, x<sub>2</sub>]</code>, <code>[x<sub>2</sub>, x<sub>3</sub>]</code>, ..., <code>[x<sub>cnt<sub>i</sub>-1</sub>, x<sub>cnt<sub>i</sub></sub>]</code>, <code>[x<sub>cnt<sub>i</sub></sub>, v<sub>i</sub>]</code> 。</p>

<p>现在得到一个&nbsp;<strong>新的细分图</strong> ，请你计算从节点 <code>0</code> 出发，可以到达多少个节点？如果节点间距离是 <code>maxMoves</code> 或更少，则视为 <strong>可以到达</strong> 。</p>

<p>给你原始图和 <code>maxMoves</code> ，返回 <em>新的细分图中从节点 <code>0</code> 出发</em><strong><em> 可到达的节点数</em></strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0882.Reachable%20Nodes%20In%20Subdivided%20Graph/images/origfinal.png" style="height: 247px; width: 600px;" />
<pre>
<strong>输入：</strong>edges = [[0,1,10],[0,2,1],[1,2,2]], maxMoves = 6, n = 3
<strong>输出：</strong>13
<strong>解释：</strong>边的细分情况如上图所示。
可以到达的节点已经用黄色标注出来。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>edges = [[0,1,4],[1,2,6],[0,2,8],[1,3,1]], maxMoves = 10, n = 4
<strong>输出：</strong>23
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>edges = [[1,2,4],[1,4,5],[1,3,1],[2,3,4],[3,4,5]], maxMoves = 17, n = 5
<strong>输出：</strong>1
<strong>解释：</strong>节点 0 与图的其余部分没有连通，所以只有节点 0 可以到达。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= edges.length &lt;= min(n * (n - 1) / 2, 10<sup>4</sup>)</code></li>
	<li><code>edges[i].length == 3</code></li>
	<li><code>0 &lt;= u<sub>i</sub> &lt; v<sub>i</sub> &lt; n</code></li>
	<li>图中 <strong>不存在平行边</strong></li>
	<li><code>0 &lt;= cnt<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= maxMoves &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= n &lt;= 3000</code></li>
</ul>

## 解法

**方法一：Dijkstra 算法**

这道题本质是求从节点 $0$ 出发，最多经过 $maxMoves$ 步，可以到达多少个节点。单源最短路，且边权非负，我们可以考虑使用 Dijkstra 算法。

根据题目描述，节点 $u_i$ 到节点 $v_i$ 之间存在 $cnt_i$ 个新节点，那么节点 $u_i$ 到节点 $v_i$ 的距离为 $cnt_i + 1$。

我们举个简单的例子，以下节点 $1$ 和节点 $2$ 之间存在 $3$ 个新节点，那么节点 $1$ 到节点 $2$ 之间有 $4$ 条边，也即距离为 $4$。

```
1 -- o -- o -- o -- 2
```

因此，我们可以将原图中两点之间新节点的个数 $cnt_i$ 加 $1$，得到两点之间的距离。然后构建一个邻接表 $g$，用于存储每个节点的邻接节点以及到达邻接节点的距离。

接下来，我们使用 Dijkstra 算法求出从节点 $0$ 到原始图其余所有节点的最短距离，存储在数组 $dist$ 中。

然后，我们遍历数组 $dist$，统计其中小于等于 $maxMoves$ 的节点个数，也就是我们可以到达的节点数。不过，这并不是最终答案，我们还需要加上新节点中符合条件的节点个数。

我们可以发现，如果我们能在 $dist[u]$ 步到达节点 $u$（其中 $dist[u] \leq maxMoves$），并且节点 $u$ 到节点 $v$ 之间有 $cnt$ 个新节点，那么我们能通过节点 $u$ 到达的新节点个数 $a=\min(cnt, maxMoves - dist[u])$。同理，我们能通过节点 $v$ 到达的新节点个数 $b=\min(cnt, maxMoves - dist[v])$。那么，我们能到达节点 $u$ 和节点 $v$ 之间的新节点个数为 $\min(cnt, a + b)$。

因此，我们再遍历所有的边，统计其中能到达的新节点个数，累加到答案中即可。

时间复杂度 $O(n + m \times \log n)$，其中 $m$ 和 $n$ 分别为边数和节点数。

### **Java**

```java
class Solution {
    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (var e : edges) {
            int u = e[0], v = e[1], cnt = e[2] + 1;
            g[u].add(new int[] {v, cnt});
            g[v].add(new int[] {u, cnt});
        }
        int[] dist = new int[n];
        Arrays.fill(dist, 1 << 30);
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        q.offer(new int[] {0, 0});
        dist[0] = 0;
        while (!q.isEmpty()) {
            var p = q.poll();
            int d = p[0], u = p[1];
            for (var nxt : g[u]) {
                int v = nxt[0], cnt = nxt[1];
                if (d + cnt < dist[v]) {
                    dist[v] = d + cnt;
                    q.offer(new int[] {dist[v], v});
                }
            }
        }
        int ans = 0;
        for (int d : dist) {
            if (d <= maxMoves) {
                ++ans;
            }
        }
        for (var e : edges) {
            int u = e[0], v = e[1], cnt = e[2];
            int a = Math.min(cnt, Math.max(0, maxMoves - dist[u]));
            int b = Math.min(cnt, Math.max(0, maxMoves - dist[v]));
            ans += Math.min(cnt, a + b);
        }
        return ans;
    }
}
```
