# [1135. 最低成本联通所有城市](https://leetcode.cn/problems/connecting-cities-with-minimum-cost)

[English Version](/solution/1100-1199/1135.Connecting%20Cities%20With%20Minimum%20Cost/README_EN.md)

## 题目描述

<p>想象一下你是个城市基建规划者，地图上有&nbsp;<code>n</code>&nbsp;座城市，它们按以&nbsp;<code>1</code> 到&nbsp;<code>n</code>&nbsp;的次序编号。</p>

<p>给你整数 <code>n</code> 和一个数组&nbsp;<code>conections</code>，其中&nbsp;<code>connections[i] = [x<sub>i</sub>, y<sub>i</sub>, cost<sub>i</sub>]</code>&nbsp;表示将城市&nbsp;<code>x<sub>i</sub></code>&nbsp;和城市&nbsp;<code>y<sub>i</sub></code>&nbsp;连接所要的<code>cost<sub>i</sub></code>（<strong>连接是双向的</strong>）。</p>

<p>返回连接所有城市的<strong>最低成本</strong>，每对城市之间<strong>至少</strong>有一条路径。如果无法连接所有 <code>n</code>&nbsp;个城市，返回 <code>-1</code></p>

<p>该 <strong>最小成本</strong> 应该是所用全部连接成本的总和。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1135.Connecting%20Cities%20With%20Minimum%20Cost/images/1314_ex2.png" /></p>

<pre>
<strong>输入：</strong>n = 3, conections = [[1,2,5],[1,3,6],[2,3,1]]
<strong>输出：</strong>6
<strong>解释：</strong>选出任意 2 条边都可以连接所有城市，我们从中选取成本最小的 2 条。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1135.Connecting%20Cities%20With%20Minimum%20Cost/images/1314_ex1.png" /></p>

<pre>
<strong>输入：</strong>n = 4, conections = [[1,2,3],[3,4,4]]
<strong>输出：</strong>-1
<strong>解释：</strong>即使连通所有的边，也无法连接所有城市。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= connections.length &lt;= 10<sup>4</sup></code></li>
	<li><code>connections[i].length == 3</code></li>
	<li><code>1 &lt;= x<sub>i</sub>, y<sub>i</sub>&nbsp;&lt;= n</code></li>
	<li><code>x<sub>i</sub>&nbsp;!= y<sub>i</sub></code></li>
	<li><code>0 &lt;= cost<sub>i</sub>&nbsp;&lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

**方法一：Kruskal 算法**

Kruskal 算法是一种贪心算法，用于计算最小生成树。

Kruskal 算法的基本思想是，每次从边集中选择一条最小的边，如果这条边连接的两个顶点不在同一个连通分量中，则将这条边加入到最小生成树中，否则舍弃这条边。

对于本题，我们可以将边按照连通成本从小到大排序，用并查集维护连通分量，每次选择一条最小的边，如果这条边连接的两个顶点不在同一个连通分量中，则合并这两个顶点，然后累加连通成本。如果出现连通份量为 $1$ 的情况，则说明所有顶点都连通了，返回累加的连通成本，否则返回 $-1$。

时间复杂度 $O(m \times \log m)$，空间复杂度 $O(n)$。其中 $m$ 和 $n$ 分别为边数和顶点数。

### **Java**

```java
class Solution {
    private int[] p;

    public int minimumCost(int n, int[][] connections) {
        Arrays.sort(connections, Comparator.comparingInt(a -> a[2]));
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        int ans = 0;
        for (int[] e : connections) {
            int x = e[0] - 1, y = e[1] - 1, cost = e[2];
            if (find(x) == find(y)) {
                continue;
            }
            p[find(x)] = find(y);
            ans += cost;
            if (--n == 1) {
                return ans;
            }
        }
        return -1;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```
