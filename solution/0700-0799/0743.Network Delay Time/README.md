# [743. 网络延迟时间](https://leetcode.cn/problems/network-delay-time)

[English Version](/solution/0700-0799/0743.Network%20Delay%20Time/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有 <code>n</code> 个网络节点，标记为&nbsp;<code>1</code>&nbsp;到 <code>n</code>。</p>

<p>给你一个列表&nbsp;<code>times</code>，表示信号经过 <strong>有向</strong> 边的传递时间。&nbsp;<code>times[i] = (u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>)</code>，其中&nbsp;<code>u<sub>i</sub></code>&nbsp;是源节点，<code>v<sub>i</sub></code>&nbsp;是目标节点， <code>w<sub>i</sub></code>&nbsp;是一个信号从源节点传递到目标节点的时间。</p>

<p>现在，从某个节点&nbsp;<code>K</code>&nbsp;发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回&nbsp;<code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0743.Network%20Delay%20Time/images/931_example_1.png" style="height: 220px; width: 200px;" /></p>

<pre>
<strong>输入：</strong>times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
<strong>输出：</strong>2
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>times = [[1,2,1]], n = 2, k = 1
<strong>输出：</strong>1
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>times = [[1,2,1]], n = 2, k = 2
<strong>输出：</strong>-1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= times.length &lt;= 6000</code></li>
	<li><code>times[i].length == 3</code></li>
	<li><code>1 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li><code>0 &lt;= w<sub>i</sub> &lt;= 100</code></li>
	<li>所有 <code>(u<sub>i</sub>, v<sub>i</sub>)</code> 对都 <strong>互不相同</strong>（即，不含重复边）</li>
</ul>

## 解法

设 n 表示点数，m 表示边数。

**方法一：朴素 Dijkstra 算法**

时间复杂度 $O(n^2+m)$。

**方法二：堆优化 Dijkstra 算法**

时间复杂度 $O(m\log n)$。

**方法三：Bellman Ford 算法**

时间复杂度 $O(nm)$。

**方法四：SPFA 算法**

时间复杂度，平均情况下 $O(m)$，最坏情况下 $O(nm)$。

朴素 Dijkstra 算法：

堆优化 Dijkstra 算法：

Bellman Ford 算法：

SPFA 算法：

### **Java**

朴素 Dijkstra 算法：

```java
class Solution {
    private static final int INF = 0x3f3f;

    public int networkDelayTime(int[][] times, int n, int k) {
        int[][] g = new int[n][n];
        int[] dist = new int[n];
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; ++i) {
            dist[i] = INF;
            Arrays.fill(g[i], INF);
        }
        for (int[] t : times) {
            g[t[0] - 1][t[1] - 1] = t[2];
        }
        dist[k - 1] = 0;
        for (int i = 0; i < n; ++i) {
            int t = -1;
            for (int j = 0; j < n; ++j) {
                if (!vis[j] && (t == -1 || dist[t] > dist[j])) {
                    t = j;
                }
            }
            vis[t] = true;
            for (int j = 0; j < n; ++j) {
                dist[j] = Math.min(dist[j], dist[t] + g[t][j]);
            }
        }
        int ans = 0;
        for (int d : dist) {
            ans = Math.max(ans, d);
        }
        return ans == INF ? -1 : ans;
    }
}
```

堆优化 Dijkstra 算法：

```java
class Solution {
    private static final int INF = 0x3f3f;

    public int networkDelayTime(int[][] times, int n, int k) {
        List<int[]>[] g = new List[n];
        int[] dist = new int[n];
        for (int i = 0; i < n; ++i) {
            dist[i] = INF;
            g[i] = new ArrayList<>();
        }
        for (int[] t : times) {
            g[t[0] - 1].add(new int[]{t[1] - 1, t[2]});
        }
        dist[k - 1] = 0;
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        q.offer(new int[]{0, k - 1});
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int u = p[1];
            for (int[] ne : g[u]) {
                int v = ne[0], w = ne[1];
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    q.offer(new int[]{dist[v], v});
                }
            }
        }
        int ans = 0;
        for (int d : dist) {
            ans = Math.max(ans, d);
        }
        return ans == INF ? -1 : ans;
    }
}
```

Bellman Ford 算法：

```java
class Solution {
    private static final int INF = 0x3f3f;

    public int networkDelayTime(int[][] times, int n, int k) {
        int[] dist = new int[n];
        int[] backup = new int[n];
        Arrays.fill(dist, INF);
        dist[k - 1] = 0;
        for (int i = 0; i < n; ++i) {
            System.arraycopy(dist, 0, backup, 0, n);
            for (int[] t : times) {
                int u = t[0] - 1, v = t[1] - 1, w = t[2];
                dist[v] = Math.min(dist[v], backup[u] + w);
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, dist[i]);
        }
        return ans == INF ? -1 : ans;
    }
}
```

SPFA 算法：

```java
class Solution {
    private static final int INF = 0x3f3f;

    public int networkDelayTime(int[][] times, int n, int k) {
        int[] dist = new int[n];
        boolean[] vis = new boolean[n];
        List<int[]>[] g = new List[n];
        for (int i = 0; i < n; ++i) {
            dist[i] = INF;
            g[i] = new ArrayList<>();
        }
        for (int[] t : times) {
            int u = t[0] - 1, v = t[1] - 1, w = t[2];
            g[u].add(new int[] {v, w});
        }
        --k;
        dist[k] = 0;
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(k);
        vis[k] = true;
        while (!q.isEmpty()) {
            int u = q.poll();
            vis[u] = false;
            for (int[] ne : g[u]) {
                int v = ne[0], w = ne[1];
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    if (!vis[v]) {
                        q.offer(v);
                        vis[v] = true;
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, dist[i]);
        }
        return ans == INF ? -1 : ans;
    }
}
```

朴素 Dijkstra 算法：

堆优化 Dijkstra 算法：

Bellman Ford 算法：

SPFA 算法：

朴素 Dijkstra 算法：

堆优化 Dijkstra 算法：

Bellman Ford 算法：

SPFA 算法：
