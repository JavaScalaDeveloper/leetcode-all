# [797. 所有可能的路径](https://leetcode.cn/problems/all-paths-from-source-to-target)

## 题目描述

<p>给你一个有&nbsp;<code>n</code>&nbsp;个节点的 <strong>有向无环图（DAG）</strong>，请你找出所有从节点 <code>0</code>&nbsp;到节点 <code>n-1</code>&nbsp;的路径并输出（<strong>不要求按特定顺序</strong>）</p>

<p><meta charset="UTF-8" />&nbsp;<code>graph[i]</code>&nbsp;是一个从节点 <code>i</code> 可以访问的所有节点的列表（即从节点 <code>i</code> 到节点&nbsp;<code>graph[i][j]</code>存在一条有向边）。</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0797.All%20Paths%20From%20Source%20to%20Target/images/all_1.jpg" /></p>

<pre>
<strong>输入：</strong>graph = [[1,2],[3],[3],[]]
<strong>输出：</strong>[[0,1,3],[0,2,3]]
<strong>解释：</strong>有两条路径 0 -&gt; 1 -&gt; 3 和 0 -&gt; 2 -&gt; 3
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0797.All%20Paths%20From%20Source%20to%20Target/images/all_2.jpg" /></p>

<pre>
<strong>输入：</strong>graph = [[4,3,1],[3,2,4],[3],[4],[]]
<strong>输出：</strong>[[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == graph.length</code></li>
	<li><code>2 &lt;= n &lt;= 15</code></li>
	<li><code>0 &lt;= graph[i][j] &lt; n</code></li>
	<li><code>graph[i][j] != i</code>（即不存在自环）</li>
	<li><code>graph[i]</code> 中的所有元素 <strong>互不相同</strong></li>
	<li>保证输入为 <strong>有向无环图（DAG）</strong></li>
</ul>

## 解法

因为图中不存在环，所以直接用 DFS 或 BFS 遍历即可

BFS：

DFS：

### **Java**

BFS：

```java
class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int n = graph.length;
        Queue<List<Integer>> queue = new ArrayDeque<>();
        queue.offer(Arrays.asList(0));
        List<List<Integer>> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            List<Integer> path = queue.poll();
            int u = path.get(path.size() - 1);
            if (u == n - 1) {
                ans.add(path);
                continue;
            }
            for (int v : graph[u]) {
                List<Integer> next = new ArrayList<>(path);
                next.add(v);
                queue.offer(next);
            }
        }
        return ans;
    }
}
```

DFS：

```java
class Solution {
    private List<List<Integer>> ans;
    private int[][] graph;

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        ans = new ArrayList<>();
        this.graph = graph;
        List<Integer> t = new ArrayList<>();
        t.add(0);
        dfs(t);
        return ans;
    }

    private void dfs(List<Integer> t) {
        int cur = t.get(t.size() - 1);
        if (cur == graph.length - 1) {
            ans.add(new ArrayList<>(t));
            return;
        }
        for (int v : graph[cur]) {
            t.add(v);
            dfs(t);
            t.remove(t.size() - 1);
        }
    }
}
```

DFS：

DFS：
