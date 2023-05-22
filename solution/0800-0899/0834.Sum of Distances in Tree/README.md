# [834. 树中距离之和](https://leetcode.cn/problems/sum-of-distances-in-tree)

## 题目描述

<p>给定一个无向、连通的树。树中有 <code>n</code> 个标记为 <code>0...n-1</code> 的节点以及 <code>n-1</code>&nbsp;条边&nbsp;。</p>

<p>给定整数 <code>n</code> 和数组&nbsp;<code>edges</code>&nbsp;，&nbsp;<code>edges[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>表示树中的节点&nbsp;<code>a<sub>i</sub></code>&nbsp;和&nbsp;<code>b<sub>i</sub></code>&nbsp;之间有一条边。</p>

<p>返回长度为 <code>n</code> 的数组&nbsp;<code>answer</code>&nbsp;，其中&nbsp;<code>answer[i]</code>&nbsp;是树中第 <code>i</code> 个节点与所有其他节点之间的距离之和。</p>

<p><strong>示例 1:</strong></p>

<p><img src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0834.Sum%20of%20Distances%20in%20Tree/images/lc-sumdist1.jpg" /></p>

<pre>
<strong>输入: </strong>n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
<strong>输出: </strong>[8,12,6,10,10,10]
<strong>解释: </strong>树如图所示。
我们可以计算出 dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5) 
也就是 1 + 1 + 2 + 2 + 2 = 8。 因此，answer[0] = 8，以此类推。
</pre>

<p><strong>示例 2:</strong></p>
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0834.Sum%20of%20Distances%20in%20Tree/images/lc-sumdist2.jpg" />
<pre>
<strong>输入:</strong> n = 1, edges = []
<strong>输出:</strong> [0]
</pre>

<p><strong>示例 3:</strong></p>
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0834.Sum%20of%20Distances%20in%20Tree/images/lc-sumdist3.jpg" />
<pre>
<strong>输入:</strong> n = 2, edges = [[1,0]]
<strong>输出:</strong> [1,1]
</pre>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>edges.length == n - 1</code></li>
	<li><code>edges[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub>&nbsp;&lt; n</code></li>
	<li><code>a<sub>i</sub>&nbsp;!= b<sub>i</sub></code></li>
	<li>给定的输入保证为有效的树</li>
</ul>

## 解法

**方法一：树形 DP（换根）**

我们先跑一遍 DFS，计算出每个节点的子树大小，记录在数组size中，并且统计出节点0到其他节点的距离之和，记录在ans[0]中。

接下来，我们再跑一遍 DFS，枚举每个点作为根节点时，其他节点到根节点的距离之和。假设当前节点i的答案为t，当我们从节点i转移到节点j时，距离之和变为t - size[j] + n - size[j]，即距离节点j及其子树节点的距离之和减少size[j]，而距离其它节点的距离之和增加n - size[j]。

时间复杂度O(n)，空间复杂度O(n)。其中n为树的节点数。

相似题目：

-   [2581. 统计可能的树根数目](/solution/2500-2599/2581.Count%20Number%20of%20Possible%20Root%20Nodes/README.md)

### **Java**

```java
class Solution {
    private int n;
    private int[] ans;
    private int[] size;
    private List<Integer>[] g;

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        this.n = n;
        g = new List[n];
        ans = new int[n];
        size = new int[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        dfs1(0, -1, 0);
        dfs2(0, -1, ans[0]);
        return ans;
    }

    private void dfs1(int i, int fa, int d) {
        ans[0] += d;
        size[i] = 1;
        for (int j : g[i]) {
            if (j != fa) {
                dfs1(j, i, d + 1);
                size[i] += size[j];
            }
        }
    }

    private void dfs2(int i, int fa, int t) {
        ans[i] = t;
        for (int j : g[i]) {
            if (j != fa) {
                dfs2(j, i, t - size[j] + n - size[j]);
            }
        }
    }
}
```
