# [1617. 统计子树中城市之间最大距离](https://leetcode.cn/problems/count-subtrees-with-max-distance-between-cities)

## 题目描述

<p>给你 <code>n</code> 个城市，编号为从 <code>1</code> 到 <code>n</code> 。同时给你一个大小为 <code>n-1</code> 的数组 <code>edges</code> ，其中 <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> 表示城市 <code>u<sub>i</sub></code> 和 <code>v<sub>i</sub></code><sub> </sub>之间有一条双向边。题目保证任意城市之间只有唯一的一条路径。换句话说，所有城市形成了一棵 <strong>树</strong> 。</p>

<p>一棵 <strong>子树</strong> 是城市的一个子集，且子集中任意城市之间可以通过子集中的其他城市和边到达。两个子树被认为不一样的条件是至少有一个城市在其中一棵子树中存在，但在另一棵子树中不存在。</p>

<p>对于 <code>d</code> 从 <code>1</code> 到 <code>n-1</code> ，请你找到城市间 <strong>最大距离</strong> 恰好为 <code>d</code> 的所有子树数目。</p>

<p>请你返回一个大小为 <code>n-1</code> 的数组，其中第<em> </em><code>d</code><em> </em>个元素（<strong>下标从 1 开始</strong>）是城市间 <strong>最大距离</strong> 恰好等于 <code>d</code> 的子树数目。</p>

<p><strong>请注意</strong>，两个城市间距离定义为它们之间需要经过的边的数目。</p>



<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1617.Count%20Subtrees%20With%20Max%20Distance%20Between%20Cities/images/p1.png" style="width: 161px; height: 181px;" /></strong></p>

<pre>
<b>输入：</b>n = 4, edges = [[1,2],[2,3],[2,4]]
<b>输出：</b>[3,4,0]
<strong>解释：
</strong>子树 {1,2}, {2,3} 和 {2,4} 最大距离都是 1 。
子树 {1,2,3}, {1,2,4}, {2,3,4} 和 {1,2,3,4} 最大距离都为 2 。
不存在城市间最大距离为 3 的子树。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>n = 2, edges = [[1,2]]
<b>输出：</b>[1]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>n = 3, edges = [[1,2],[2,3]]
<b>输出：</b>[2,1]
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>2 <= n <= 15</code></li>
	<li><code>edges.length == n-1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>1 <= u<sub>i</sub>, v<sub>i</sub> <= n</code></li>
	<li>题目保证 <code>(u<sub>i</sub>, v<sub>i</sub>)</code> 所表示的边互不相同。</li>
</ul>

## 解法

**方法一：二进制枚举 + BFS 或 DFS**

我们注意到n ≤ 15，因此可以考虑使用二进制枚举的方法枚举所有的子树。而子树中节点的最大距离，其实就是子树中两个节点之间的最长路径，也即是树的直径，求解树的直径一般可以使用 DFS 或 BFS，先找到树直径的一个端点，然后再从该端点出发，找到树的另一个端点，这两个端点之间的路径长度就是树的直径。

接下来，我们详细说明具体的代码实现。

我们先根据数组edges构建出邻接表g，其中g[u]表示节点u的所有邻接节点。

用一个二进制数mask表示子树，其中mask的第i位为1表示节点i在子树中，否则表示节点i不在子树中。每个节点都有两种状态，即在子树中或不在子树中，有n个节点，因此一共有2^n种状态。

接下来，我们在[1,..2^n-1]的范围内枚举子树mask，对于每个子树：

如果mask的二进制表示中只有一个二进制位为1，即mask \in [1,2,4,8,\cdots,2^{n-1}]，则跳过该mask，因为这些mask表示的子树只有一个节点，不符合题意；

否则，我们找到mask的二进制表示中最高位的二进制位为1的位置，记为cur。然后从节点cur出发，通过深度优先搜索或者广度优先搜索，找到树直径的一个端点nxt，然后我们再从节点nxt出发，同样通过深度优先搜索或者广度优先搜索，过程中记录下最大距离mx。

当走到最深的节点时，即可得知树的直径。此时我们更新答案数组ans，将ans[mx-1]的值加1。注意，这里是mx-1，因为题目中的最大距离是从1开始计数的。

最后，枚举完所有的子树，返回答案数组ans即可。

时间复杂度O(2^n × n)，空间复杂度O(n)。其中n为节点个数。

### **Java**

```java
class Solution {
    private List<Integer>[] g;
    private int msk;
    private int nxt;
    private int mx;

    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int u = e[0] - 1, v = e[1] - 1;
            g[u].add(v);
            g[v].add(u);
        }
        int[] ans = new int[n - 1];
        for (int mask = 1; mask < 1 << n; ++mask) {
            if ((mask & (mask - 1)) == 0) {
                continue;
            }
            msk = mask;
            mx = 0;
            int cur = 31 - Integer.numberOfLeadingZeros(msk);
            dfs(cur, 0);
            if (msk == 0) {
                msk = mask;
                mx = 0;
                dfs(nxt, 0);
                ++ans[mx - 1];
            }
        }
        return ans;
    }

    private void dfs(int u, int d) {
        msk ^= 1 << u;
        if (mx < d) {
            mx = d;
            nxt = u;
        }
        for (int v : g[u]) {
            if ((msk >> v & 1) == 1) {
                dfs(v, d + 1);
            }
        }
    }
}
```

```java
class Solution {
    private List<Integer>[] g;
    private int msk;
    private int nxt;

    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int u = e[0] - 1, v = e[1] - 1;
            g[u].add(v);
            g[v].add(u);
        }
        int[] ans = new int[n - 1];
        for (int mask = 1; mask < 1 << n; ++mask) {
            if ((mask & (mask - 1)) == 0) {
                continue;
            }
            msk = mask;
            int cur = 31 - Integer.numberOfLeadingZeros(msk);
            bfs(cur);
            if (msk == 0) {
                msk = mask;
                int mx = bfs(nxt);
                ++ans[mx - 1];
            }
        }
        return ans;
    }

    private int bfs(int u) {
        int d = -1;
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(u);
        msk ^= 1 << u;
        while (!q.isEmpty()) {
            ++d;
            for (int k = q.size(); k > 0; --k) {
                u = q.poll();
                nxt = u;
                for (int v : g[u]) {
                    if ((msk >> v & 1) == 1) {
                        msk ^= 1 << v;
                        q.offer(v);
                    }
                }
            }
        }
        return d;
    }
}
```
