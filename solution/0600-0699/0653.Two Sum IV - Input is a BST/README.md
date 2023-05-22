# [653. 两数之和 IV - 输入二叉搜索树](https://leetcode.cn/problems/two-sum-iv-input-is-a-bst)

## 题目描述

<p>给定一个二叉搜索树 <code>root</code> 和一个目标结果 <code>k</code>，如果二叉搜索树中存在两个元素且它们的和等于给定的目标结果，则返回 <code>true</code>。</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0653.Two%20Sum%20IV%20-%20Input%20is%20a%20BST/images/sum_tree_1.jpg" style="height: 229px; width: 400px;" />
<pre>
<strong>输入:</strong> root = [5,3,6,2,4,null,7], k = 9
<strong>输出:</strong> true
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0653.Two%20Sum%20IV%20-%20Input%20is%20a%20BST/images/sum_tree_2.jpg" style="height: 229px; width: 400px;" />
<pre>
<strong>输入:</strong> root = [5,3,6,2,4,null,7], k = 28
<strong>输出:</strong> false
</pre>

<p><strong>提示:</strong></p>

<ul>
	<li>二叉树的节点个数的范围是&nbsp;&nbsp;<code>[1, 10<sup>4</sup>]</code>.</li>
	<li><code>-10<sup>4</sup>&nbsp;&lt;= Node.val &lt;= 10<sup>4</sup></code></li>
	<li>题目数据保证，输入的 <code>root</code> 是一棵 <strong>有效</strong> 的二叉搜索树</li>
	<li><code>-10<sup>5</sup>&nbsp;&lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

**方法一：哈希表 + DFS**

DFS 遍历二叉搜索树，对于每个节点，判断 `k - node.val` 是否在哈希表中，如果在，则返回 `true`，否则将 `node.val` 加入哈希表中。

时间复杂度O(n)，空间复杂度O(n)。其中n为二叉搜索树的节点个数。

**方法二：哈希表 + BFS**

与方法一类似，只是使用 BFS 遍历二叉搜索树。

时间复杂度O(n)，空间复杂度O(n)。其中n为二叉搜索树的节点个数。

### **Java**

```java
class Solution {
    private Set<Integer> vis = new HashSet<>();
    private int k;

    public boolean findTarget(TreeNode root, int k) {
        this.k = k;
        return dfs(root);
    }

    private boolean dfs(TreeNode root) {
        if (root == null) {
            return false;
        }
        if (vis.contains(k - root.val)) {
            return true;
        }
        vis.add(root.val);
        return dfs(root.left) || dfs(root.right);
    }
}
```

```java
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        Set<Integer> vis = new HashSet<>();
        while (!q.isEmpty()) {
            for (int n = q.size(); n > 0; --n) {
                TreeNode node = q.poll();
                if (vis.contains(k - node.val)) {
                    return true;
                }
                vis.add(node.val);
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
        }
        return false;
    }
}
```
