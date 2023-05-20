# [1579. 保证图可完全遍历](https://leetcode.cn/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable)

## 题目描述

<p>Alice 和 Bob 共有一个无向图，其中包含 n 个节点和 3&nbsp; 种类型的边：</p>

<ul>
	<li>类型 1：只能由 Alice 遍历。</li>
	<li>类型 2：只能由 Bob 遍历。</li>
	<li>类型 3：Alice 和 Bob 都可以遍历。</li>
</ul>

<p>给你一个数组 <code>edges</code> ，其中 <code>edges[i] = [type<sub>i</sub>, u<sub>i</sub>, v<sub>i</sub>]</code>&nbsp;表示节点 <code>u<sub>i</sub></code> 和 <code>v<sub>i</sub></code> 之间存在类型为 <code>type<sub>i</sub></code> 的双向边。请你在保证图仍能够被 Alice和 Bob 完全遍历的前提下，找出可以删除的最大边数。如果从任何节点开始，Alice 和 Bob 都可以到达所有其他节点，则认为图是可以完全遍历的。</p>

<p>返回可以删除的最大边数，如果 Alice 和 Bob 无法完全遍历图，则返回 -1 。</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1579.Remove%20Max%20Number%20of%20Edges%20to%20Keep%20Graph%20Fully%20Traversable/images/5510ex1.png" style="height: 191px; width: 179px;"></strong></p>

<pre><strong>输入：</strong>n = 4, edges = [[3,1,2],[3,2,3],[1,1,3],[1,2,4],[1,1,2],[2,3,4]]
<strong>输出：</strong>2
<strong>解释：</strong>如果删除<strong> </strong>[1,1,2] 和 [1,1,3] 这两条边，Alice 和 Bob 仍然可以完全遍历这个图。再删除任何其他的边都无法保证图可以完全遍历。所以可以删除的最大边数是 2 。
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1579.Remove%20Max%20Number%20of%20Edges%20to%20Keep%20Graph%20Fully%20Traversable/images/5510ex2.png" style="height: 190px; width: 178px;"></strong></p>

<pre><strong>输入：</strong>n = 4, edges = [[3,1,2],[3,2,3],[1,1,4],[2,1,4]]
<strong>输出：</strong>0
<strong>解释：</strong>注意，删除任何一条边都会使 Alice 和 Bob 无法完全遍历这个图。
</pre>

<p><strong>示例 3：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1579.Remove%20Max%20Number%20of%20Edges%20to%20Keep%20Graph%20Fully%20Traversable/images/5510ex3.png" style="height: 190px; width: 178px;"></strong></p>

<pre><strong>输入：</strong>n = 4, edges = [[3,2,3],[1,1,2],[2,3,4]]
<strong>输出：</strong>-1
<strong>解释：</strong>在当前图中，Alice 无法从其他节点到达节点 4 。类似地，Bob 也不能达到节点 1 。因此，图无法完全遍历。</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10^5</code></li>
	<li><code>1 &lt;= edges.length &lt;= min(10^5, 3 * n * (n-1) / 2)</code></li>
	<li><code>edges[i].length == 3</code></li>
	<li><code>1 &lt;= edges[i][0] &lt;= 3</code></li>
	<li><code>1 &lt;= edges[i][1] &lt; edges[i][2] &lt;= n</code></li>
	<li>所有元组 <code>(type<sub>i</sub>, u<sub>i</sub>, v<sub>i</sub>)</code> 互不相同</li>
</ul>

## 解法

**方法一：贪心 + 并查集**

题目要求我们删除最多数目的边，使得 Alice 和 Bob 都可以遍历整个图。也即是说，我们需要保留尽可能少的边，并且要求这些边能够使得 Alice 和 Bob 都可以遍历整个图。

我们可以用两个并查集 $ufa$ 和 $ufb$ 分别维护 Alice 和 Bob 的遍历情况。

接下来，我们优先遍历公共边，即 $type=3$ 的边。对于每一条公共边的两个端点 $u$ 和 $v$，如果这两个点已经在同一个连通分量中，那么我们就可以删去这条边，因此答案加一；否则我们就将这两个点合并，即执行 $ufa.union(u, v)$ 和 $ufb.union(u, v)$。

然后，我们再遍历 Alice 独有的边，即 $type=1$ 的边。对于每一条 Alice 独有的边的两个端点 $u$ 和 $v$，如果这两个点已经在同一个连通分量中，那么我们就可以删去这条边，答案加一；否则我们就将这两个点合并，即执行 $ufa.union(u, v)$。同理，对于 Bob 独有的边，我们也可以执行相同的操作。

最后，如果 Alice 和 Bob 都可以遍历整个图，那么答案就是我们删除的边数；否则答案就是 $-1$。

时间复杂度 $O(m \times \alpha(n))$，空间复杂度 $O(n)$。其中 $m$ 是边数，而 $\alpha(n)$ 是阿克曼函数的反函数。

### **Java**

```java
class UnionFind {
    private int[] p;
    private int[] size;
    public int cnt;

    public UnionFind(int n) {
        p = new int[n];
        size = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
            size[i] = 1;
        }
        cnt = n;
    }

    public int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    public boolean union(int a, int b) {
        int pa = find(a - 1), pb = find(b - 1);
        if (pa == pb) {
            return false;
        }
        if (size[pa] > size[pb]) {
            p[pb] = pa;
            size[pa] += size[pb];
        } else {
            p[pa] = pb;
            size[pb] += size[pa];
        }
        --cnt;
        return true;
    }
}

class Solution {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        UnionFind ufa = new UnionFind(n);
        UnionFind ufb = new UnionFind(n);
        int ans = 0;
        for (var e : edges) {
            int t = e[0], u = e[1], v = e[2];
            if (t == 3) {
                if (ufa.union(u, v)) {
                    ufb.union(u, v);
                } else {
                    ++ans;
                }
            }
        }
        for (var e : edges) {
            int t = e[0], u = e[1], v = e[2];
            if (t == 1 && !ufa.union(u, v)) {
                ++ans;
            }
            if (t == 2 && !ufb.union(u, v)) {
                ++ans;
            }
        }
        return ufa.cnt == 1 && ufb.cnt == 1 ? ans : -1;
    }
}
```
