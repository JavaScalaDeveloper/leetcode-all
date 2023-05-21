# [1273. 删除树节点](https://leetcode.cn/problems/delete-tree-nodes)

## 题目描述

<p>给你一棵以节点 0 为根节点的树，定义如下：</p>

<ul>
	<li>节点的总数为&nbsp;<code>nodes</code>&nbsp;个；</li>
	<li>第&nbsp;<code>i</code> 个节点的值为&nbsp;<code>value[i]</code>&nbsp;；</li>
	<li>第&nbsp;<code>i</code> 个节点的父节点是&nbsp;<code>parent[i]</code>&nbsp;。</li>
</ul>

<p>请你删除节点值之和为 0 的每一棵子树。</p>

<p>在完成所有删除之后，返回树中剩余节点的数目。</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1273.Delete%20Tree%20Nodes/images/1421_sample_1.png" style="height: 347px; width: 403px;"></p>

<pre><strong>输入：</strong>nodes = 7, parent = [-1,0,0,1,2,2,2], value = [1,-2,4,0,-2,-1,-1]
<strong>输出：</strong>2
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nodes = 7, parent = [-1,0,0,1,2,2,2], value = [1,-2,4,0,-2,-1,-2]
<strong>输出：</strong>6
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nodes = 5, parent = [-1,0,1,0,0], value = [-672,441,18,728,378]
<strong>输出：</strong>5
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>nodes = 5, parent = [-1,0,0,1,1], value = [-686,-842,616,-739,-746]
<strong>输出：</strong>5
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nodes &lt;= 10^4</code></li>
	<li><code>parent.length == nodes</code></li>
	<li><code>0 &lt;= parent[i] &lt;= nodes - 1</code></li>
	<li><code>parent[0] == -1</code>&nbsp;表示节点 <code>0</code> 是树的根。</li>
	<li><code>value.length == nodes</code></li>
	<li><code>-10^5 &lt;= value[i] &lt;= 10^5</code></li>
	<li>题目输入数据 <strong>保证</strong> 是一棵 <strong>有效的树</strong> 。</li>
</ul>

## 解法

**方法一：DFS**

我们先将树转换成图g，其中g[i]表示节点i的所有子节点。

然后我们设计一个函数dfs(i)，表示以节点i为根的子树的节点数目和权值之和。那么答案就是dfs(0)[1]。

在这个函数中，我们递归地计算出以每个子节点j为根的子树的节点数目和权值之和，然后将这些值进行累加，如果累加后的值为零，那么我们就将这个子树的节点数目置为零。最后我们返回以节点i为根的子树的节点数目和权值之和。

时间复杂度O(n)，空间复杂度O(n)。其中n是树的节点数目。

### **Java**

```java
class Solution {
    private List<Integer>[] g;
    private int[] value;

    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
        g = new List[nodes];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 1; i < nodes; ++i) {
            g[parent[i]].add(i);
        }
        this.value = value;
        return dfs(0)[1];
    }

    private int[] dfs(int i) {
        int[] res = new int[] {value[i], 1};
        for (int j : g[i]) {
            int[] t = dfs(j);
            res[0] += t[0];
            res[1] += t[1];
        }
        if (res[0] == 0) {
            res[1] = 0;
        }
        return res;
    }
}
```
