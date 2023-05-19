# [2368. 受限条件下可到达节点的数目](https://leetcode.cn/problems/reachable-nodes-with-restrictions)

[English Version](/solution/2300-2399/2368.Reachable%20Nodes%20With%20Restrictions/README_EN.md)

## 题目描述

<p>现有一棵由 <code>n</code> 个节点组成的无向树，节点编号从 <code>0</code> 到 <code>n - 1</code> ，共有 <code>n - 1</code> 条边。</p>

<p>给你一个二维整数数组 <code>edges</code> ，长度为 <code>n - 1</code> ，其中 <code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> 表示树中节点 <code>a<sub>i</sub></code> 和 <code>b<sub>i</sub></code> 之间存在一条边。另给你一个整数数组 <code>restricted</code> 表示 <strong>受限</strong> 节点。</p>

<p>在不访问受限节点的前提下，返回你可以从节点<em> </em><code>0</code><em> </em>到达的 <strong>最多</strong> 节点数目<em>。</em></p>

<p>注意，节点 <code>0</code> <strong>不</strong> 会标记为受限节点。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2368.Reachable%20Nodes%20With%20Restrictions/images/ex1drawio.png" style="width: 402px; height: 322px;">
<pre><strong>输入：</strong>n = 7, edges = [[0,1],[1,2],[3,1],[4,0],[0,5],[5,6]], restricted = [4,5]
<strong>输出：</strong>4
<strong>解释：</strong>上图所示正是这棵树。
在不访问受限节点的前提下，只有节点 [0,1,2,3] 可以从节点 0 到达。</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2368.Reachable%20Nodes%20With%20Restrictions/images/ex2drawio.png" style="width: 412px; height: 312px;">
<pre><strong>输入：</strong>n = 7, edges = [[0,1],[0,2],[0,5],[0,4],[3,2],[6,5]], restricted = [4,2,1]
<strong>输出：</strong>3
<strong>解释：</strong>上图所示正是这棵树。
在不访问受限节点的前提下，只有节点 [0,5,6] 可以从节点 0 到达。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li><code>edges</code> 表示一棵有效的树</li>
	<li><code>1 &lt;= restricted.length &lt; n</code></li>
	<li><code>1 &lt;= restricted[i] &lt; n</code></li>
	<li><code>restricted</code> 中的所有值 <strong>互不相同</strong></li>
</ul>

## 解法

**方法一：DFS/BFS**

建图，利用哈希表 $vis$ 记录有哪些受限的节点，然后 $DFS$ 或者 $BFS$ 搜索整个图，记录访问过的节点数目。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。

### **Java**

```java
class Solution {
    private List<Integer>[] g;
    private boolean[] vis;
    private int ans;

    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        vis = new boolean[n];
        for (int v : restricted) {
            vis[v] = true;
        }
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }

        ans = 0;
        dfs(0);
        return ans;
    }

    private void dfs(int u) {
        if (vis[u]) {
            return;
        }
        ++ans;
        vis[u] = true;
        for (int v : g[u]) {
            dfs(v);
        }
    }
}
```

```java
class Solution {
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        boolean[] vis = new boolean[n];
        for (int v : restricted) {
            vis[v] = true;
        }
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(0);
        int ans = 0;
        while (!q.isEmpty()) {
            int i = q.pollFirst();
            ++ans;
            vis[i] = true;
            for (int j : g[i]) {
                if (!vis[j]) {
                    q.offer(j);
                }
            }
        }
        return ans;
    }
}
```
