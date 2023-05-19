# [面试题 04.01. 节点间通路](https://leetcode.cn/problems/route-between-nodes-lcci)

[English Version](/lcci/04.01.Route%20Between%20Nodes/README_EN.md)

## 题目描述


<p>节点间通路。给定有向图，设计一个算法，找出两个节点之间是否存在一条路径。</p>

<p><strong>示例1:</strong></p>

<pre><strong> 输入</strong>：n = 3, graph = [[0, 1], [0, 2], [1, 2], [1, 2]], start = 0, target = 2
<strong> 输出</strong>：true
</pre>

<p><strong>示例2:</strong></p>

<pre><strong> 输入</strong>：n = 5, graph = [[0, 1], [0, 2], [0, 4], [0, 4], [0, 1], [1, 3], [1, 4], [1, 3], [2, 3], [3, 4]], start = 0, target = 4
<strong> 输出</strong> true
</pre>

<p><strong>提示：</strong></p>

<ol>
	<li>节点数量n在[0, 1e5]范围内。</li>
	<li>节点编号大于等于 0 小于 n。</li>
	<li>图中可能存在自环和平行边。</li>
</ol>

## 解法

**方法一：DFS**

**方法二：BFS**

### **Java**

```java
class Solution {
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        Map<Integer, List<Integer>> g = new HashMap<>();
        for (int[] e : graph) {
            g.computeIfAbsent(e[0], k -> new ArrayList<>()).add(e[1]);
        }
        Set<Integer> vis = new HashSet<>();
        vis.add(start);
        return dfs(start, target, g, vis);
    }

    private boolean dfs(int u, int target, Map<Integer, List<Integer>> g, Set<Integer> vis) {
        if (u == target) {
            return true;
        }
        for (int v : g.getOrDefault(u, Collections.emptyList())) {
            if (!vis.contains(v)) {
                vis.add(v);
                if (dfs(v, target, g, vis)) {
                    return true;
                }
            }
        }
        return false;
    }
}
```

```java
class Solution {
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        Map<Integer, List<Integer>> g = new HashMap<>();
        for (int[] e : graph) {
            g.computeIfAbsent(e[0], k -> new ArrayList<>()).add(e[1]);
        }
        Deque<Integer> q = new ArrayDeque<>();
        q.offer(start);
        Set<Integer> vis = new HashSet<>();
        vis.add(start);
        while (!q.isEmpty()) {
            int u = q.poll();
            if (u == target) {
                return true;
            }
            for (int v : g.getOrDefault(u, Collections.emptyList())) {
                if (!vis.contains(v)) {
                    vis.add(v);
                    q.offer(v);
                }
            }
        }
        return false;
    }
}
```
