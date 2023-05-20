# [742. 二叉树最近的叶节点](https://leetcode.cn/problems/closest-leaf-in-a-binary-tree)

## 题目描述

<p>给定一个 <strong>每个结点的值互不相同</strong>&nbsp;的二叉树，和一个目标整数值 <code>k</code>，返回 <em>树中与目标值 <code>k</code>&nbsp; <strong>最近的叶结点</strong></em> 。&nbsp;</p>

<p><strong>与叶结点最近</strong><em> </em>表示在二叉树中到达该叶节点需要行进的边数与到达其它叶结点相比最少。而且，当一个结点没有孩子结点时称其为叶结点。</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0742.Closest%20Leaf%20in%20a%20Binary%20Tree/images/closest1-tree.jpg" /></p>

<pre>
<strong>输入：</strong>root = [1, 3, 2], k = 1
<strong>输出：</strong> 2
<strong>解释：</strong> 2 和 3 都是距离目标 1 最近的叶节点。
</pre>

<p><strong>示例 2：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0742.Closest%20Leaf%20in%20a%20Binary%20Tree/images/closest2-tree.jpg" /></p>

<pre>
<strong>输入：</strong>root = [1], k = 1
<strong>输出：</strong>1
<strong>解释：</strong>最近的叶节点是根结点自身。
</pre>

<p><strong>示例 3：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0742.Closest%20Leaf%20in%20a%20Binary%20Tree/images/closest3-tree.jpg" /></p>

<pre>
<strong>输入：</strong>root = [1,2,3,4,null,null,null,5,null,6], k = 2
<strong>输出：</strong>3
<strong>解释：</strong>值为 3（而不是值为 6）的叶节点是距离结点 2 的最近结点。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li>二叉树节点数在&nbsp;<code>[1, 1000]</code> 范围内</li>
	<li><code>1 &lt;= Node.val &lt;= 1000</code></li>
	<li>每个节点值都 <strong>不同</strong></li>
	<li>给定的二叉树中有某个结点使得&nbsp;<code>node.val == k</code></li>
</ul>

## 解法

DFS 和 BFS。

先通过 DFS 构建图，然后 BFS 找距离值为 k 的结点最近的叶子结点。

### **Java**

```java
class Solution {
    private Map<TreeNode, List<TreeNode>> g;

    public int findClosestLeaf(TreeNode root, int k) {
        g = new HashMap<>();
        dfs(root, null);
        Deque<TreeNode> q = new LinkedList<>();
        for (Map.Entry<TreeNode, List<TreeNode>> entry : g.entrySet()) {
            if (entry.getKey() != null && entry.getKey().val == k) {
                q.offer(entry.getKey());
                break;
            }
        }
        Set<TreeNode> seen = new HashSet<>();
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            seen.add(node);
            if (node != null) {
                if (node.left == null && node.right == null) {
                    return node.val;
                }
                for (TreeNode next : g.get(node)) {
                    if (!seen.contains(next)) {
                        q.offer(next);
                    }
                }
            }
        }
        return 0;
    }

    private void dfs(TreeNode root, TreeNode p) {
        if (root != null) {
            g.computeIfAbsent(root, k -> new ArrayList<>()).add(p);
            g.computeIfAbsent(p, k -> new ArrayList<>()).add(root);
            dfs(root.left, root);
            dfs(root.right, root);
        }
    }
}
```
