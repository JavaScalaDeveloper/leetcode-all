# [1059. 从始点到终点的所有路径](https://leetcode.cn/problems/all-paths-from-source-lead-to-destination)

## 题目描述

<p>给定有向图的边&nbsp;<code>edges</code>，以及该图的始点&nbsp;<code>source</code>&nbsp;和目标终点&nbsp;<code>destination</code>，确定从始点&nbsp;<code>source</code>&nbsp;出发的所有路径是否最终结束于目标终点&nbsp;<code>destination</code>，即：</p>

<ul>
	<li>从始点&nbsp;<code>source</code> 到目标终点&nbsp;<code>destination</code> 存在至少一条路径</li>
	<li>如果存在从始点&nbsp;<code>source</code> 到没有出边的节点的路径，则该节点就是路径终点。</li>
	<li>从始点<code>source</code>到目标终点&nbsp;<code>destination</code> 可能路径数是有限数字</li>
</ul>

<p>当从始点&nbsp;<code>source</code> 出发的所有路径都可以到达目标终点&nbsp;<code>destination</code> 时返回&nbsp;<code>true</code>，否则返回 <code>false</code>。</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1059.All%20Paths%20from%20Source%20Lead%20to%20Destination/images/485_example_1.png" style="height: 208px; width: 200px;" /></p>

<pre>
<strong>输入：</strong>n = 3, edges = [[0,1],[0,2]], source = 0, destination = 2
<strong>输出：</strong>false
<strong>说明：</strong>节点 1 和节点 2 都可以到达，但也会卡在那里。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1059.All%20Paths%20from%20Source%20Lead%20to%20Destination/images/485_example_2.png" style="height: 230px; width: 200px;" /></p>

<pre>
<strong>输入：</strong>n = 4, edges = [[0,1],[0,3],[1,2],[2,1]], source = 0, destination = 3
<strong>输出：</strong>false
<strong>说明：</strong>有两种可能：在节点 3 处结束，或是在节点 1 和节点 2 之间无限循环。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1059.All%20Paths%20from%20Source%20Lead%20to%20Destination/images/485_example_3.png" style="height: 183px; width: 200px;" /></p>

<pre>
<strong>输入：</strong>n = 4, edges = [[0,1],[0,2],[1,3],[2,3]], source = 0, destination = 3
<strong>输出：</strong>true
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= edges.length &lt;= 10<sup>4</sup></code></li>
	<li><code>edges.length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub>&nbsp;&lt;= n - 1</code></li>
	<li><code>0 &lt;= source &lt;= n - 1</code></li>
	<li><code>0 &lt;= destination &lt;= n - 1</code></li>
	<li>给定的图中可能带有自环和平行边。</li>
</ul>

## 解法

**方法一：记忆化搜索**

建图，然后从 `source` 出发，进行深度优先搜索：

如果遇到了 `destination`，判断此时是否还有出边，如果有出边，返回 `false`，否则返回 `true`。

如果遇到了环（此前访问过），或者遇到了没有出边的节点，直接返回 `false`。

否则，我们把当前节点标记为已访问，然后对当前节点的所有出边进行深度优先搜索，只要有一条路径无法可以到达 `destination`，就返回 `false`，否则返回 `true`。

过程中我们用一个数组f记录每个节点的状态，每个f[i]的值有三种，分别表示：

-   对于f[i] = 0，表示节点i未被访问；
-   对于f[i] = 1，表示节点i已被访问，且可以到达 `destination`；
-   对于f[i] = 2，表示节点i已被访问，但无法到达 `destination`。

时间复杂度O(n)。其中n为节点数。

### **Java**

```java
class Solution {
    private List<Integer>[] g;
    private int[] f;
    private boolean[] vis;
    private int k;

    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        vis = new boolean[n];
        g = new List[n];
        k = destination;
        f = new int[n];
        Arrays.setAll(g, key -> new ArrayList<>());
        for (var e : edges) {
            g[e[0]].add(e[1]);
        }
        return dfs(source);
    }

    private boolean dfs(int i) {
        if (i == k) {
            return g[i].isEmpty();
        }
        if (f[i] != 0) {
            return f[i] == 1;
        }
        if (vis[i] || g[i].isEmpty()) {
            return false;
        }
        vis[i] = true;
        for (int j : g[i]) {
            if (!dfs(j)) {
                f[i] = -1;
                return false;
            }
        }
        f[i] = 1;
        return true;
    }
}
```
