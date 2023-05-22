# [1971. 寻找图中是否存在路径](https://leetcode.cn/problems/find-if-path-exists-in-graph)

## 题目描述

<p>有一个具有 <code>n</code> 个顶点的 <strong>双向</strong> 图，其中每个顶点标记从 <code>0</code> 到 <code>n - 1</code>（包含 <code>0</code> 和 <code>n - 1</code>）。图中的边用一个二维整数数组 <code>edges</code> 表示，其中 <code>edges[i] = [u<sub>i</sub>, v<sub>i</sub>]</code> 表示顶点 <code>ui</code> 和顶点 <code>vi</code> 之间的双向边。 每个顶点对由 <strong>最多一条</strong> 边连接，并且没有顶点存在与自身相连的边。</p>

<p>请你确定是否存在从顶点 <code>source</code> 开始，到顶点 <code>destination</code> 结束的 <strong>有效路径</strong> 。</p>

<p>给你数组 <code>edges</code> 和整数 <code>n</code>、<code>source</code> 和 <code>destination</code>，如果从 <code>source</code> 到 <code>destination</code> 存在 <strong>有效路径</strong> ，则返回 <code>true</code>，否则返回 <code>false</code> 。</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1971.Find%20if%20Path%20Exists%20in%20Graph/images/validpath-ex1.png" style="width: 141px; height: 121px;" />
<pre>
<strong>输入：</strong>n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
<strong>输出：</strong>true
<strong>解释：</strong>存在由顶点 0 到顶点 2 的路径:
- 0 → 1 → 2 
- 0 → 2
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1971.Find%20if%20Path%20Exists%20in%20Graph/images/validpath-ex2.png" style="width: 281px; height: 141px;" />
<pre>
<strong>输入：</strong>n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
<strong>输出：</strong>false
<strong>解释：</strong>不存在由顶点 0 到顶点 5 的路径.
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>0 &lt;= edges.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>u<sub>i</sub> != v<sub>i</sub></code></li>
	<li><code>0 &lt;= source, destination &lt;= n - 1</code></li>
	<li>不存在重复边</li>
	<li>不存在指向顶点自身的边</li>
</ul>

## 解法

**方法一：DFS**

我们先将 `edges` 转换成邻接表g，然后使用 DFS，判断是否存在从 `source` 到 `destination` 的路径。

过程中，我们用数组 `vis` 记录已经访问过的顶点，避免重复访问。

时间复杂度O(n + m)，其中n和m分别是节点数和边数。

**方法二：并查集**

判断图中两个节点是否连通，一种比较简单直接的方法是使用并查集。

先构建并查集，然后将每条边的两个节点合并。

最后查询 `source` 和 `destination` 的祖宗节点是否相同，相同则说明两个节点连通。

时间复杂度O(n + m  × \alpha(m))，空间复杂度O(n)。其中n和m分别是节点数和边数。

附并查集相关介绍以及常用模板：

并查集是一种树形的数据结构，顾名思义，它用于处理一些不交集的**合并**及**查询**问题。 它支持两种操作：

1. 查找（Find）：确定某个元素处于哪个子集，单次操作时间复杂度O(\alpha(n))
1. 合并（Union）：将两个子集合并成一个集合，单次操作时间复杂度O(\alpha(n))

其中\alpha为阿克曼函数的反函数，其增长极其缓慢，也就是说其单次操作的平均运行时间可以认为是一个很小的常数。

以下是并查集的常用模板，需要熟练掌握。其中：

-   `n` 表示节点数
-   `p` 存储每个点的父节点，初始时每个点的父节点都是自己
-   `size` 只有当节点是祖宗节点时才有意义，表示祖宗节点所在集合中，点的数量
-   `find(x)` 函数用于查找x所在集合的祖宗节点
-   `union(a, b)` 函数用于合并a和b所在的集合

```java [sol1-Java 模板]
int[] p = new int[n];
int[] size = new int[n];
for (int i = 0; i < n; ++i) {
    p[i] = i;
    size[i] = 1;
}

int find(int x) {
    if (p[x] != x) {
        // 路径压缩
        p[x] = find(p[x]);
    }
    return p[x];
}

void union(int a, int b) {
    int pa = find(a), pb = find(b);
    if (pa == pb) {
        return;
    }
    p[pa] = pb;
    size[pb] += size[pa];
}
```

### **Java**

```java
class Solution {
    private boolean[] vis;
    private List<Integer>[] g;

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        vis = new boolean[n];
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        return dfs(source, destination);
    }

    private boolean dfs(int source, int destination) {
        if (source == destination) {
            return true;
        }
        vis[source] = true;
        for (int nxt : g[source]) {
            if (!vis[nxt] && dfs(nxt, destination)) {
                return true;
            }
        }
        return false;
    }
}
```

```java
class Solution {
    private int[] p;

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        for (int[] e : edges) {
            p[find(e[0])] = find(e[1]);
        }
        return find(source) == find(destination);
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```
