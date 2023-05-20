# [623. 在二叉树中增加一行](https://leetcode.cn/problems/add-one-row-to-tree)

## 题目描述

<p>给定一个二叉树的根&nbsp;<code>root</code>&nbsp;和两个整数 <code>val</code> 和&nbsp;<code>depth</code>&nbsp;，在给定的深度&nbsp;<code>depth</code>&nbsp;处添加一个值为 <code>val</code> 的节点行。</p>

<p>注意，根节点&nbsp;<code>root</code>&nbsp;位于深度&nbsp;<code>1</code>&nbsp;。</p>

<p>加法规则如下:</p>

<ul>
	<li>给定整数&nbsp;<code>depth</code>，对于深度为&nbsp;<code>depth - 1</code> 的每个非空树节点 <code>cur</code> ，创建两个值为 <code>val</code> 的树节点作为 <code>cur</code> 的左子树根和右子树根。</li>
	<li><code>cur</code> 原来的左子树应该是新的左子树根的左子树。</li>
	<li><code>cur</code> 原来的右子树应该是新的右子树根的右子树。</li>
	<li>如果 <code>depth == 1 </code>意味着&nbsp;<code>depth - 1</code>&nbsp;根本没有深度，那么创建一个树节点，值 <code>val </code>作为整个原始树的新根，而原始树就是新根的左子树。</li>
</ul>

<p><strong>示例 1:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0623.Add%20One%20Row%20to%20Tree/images/addrow-tree.jpg" style="height: 231px; width: 500px;" /></p>

<pre>
<strong>输入:</strong> root = [4,2,6,3,1,5], val = 1, depth = 2
<strong>输出:</strong> [4,1,1,2,null,null,6,3,1,5]</pre>

<p><strong>示例 2:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0623.Add%20One%20Row%20to%20Tree/images/add2-tree.jpg" style="height: 277px; width: 500px;" /></p>

<pre>
<strong>输入:</strong> root = [4,2,null,3,1], val = 1, depth = 3
<strong>输出:</strong>  [4,2,null,1,1,3,null,null,1]
</pre>

<p><strong>提示:</strong></p>

<ul>
	<li>节点数在&nbsp;<code>[1, 10<sup>4</sup>]</code>&nbsp;范围内</li>
	<li>树的深度在&nbsp;<code>[1, 10<sup>4</sup>]</code>范围内</li>
	<li><code>-100 &lt;= Node.val &lt;= 100</code></li>
	<li><code>-10<sup>5</sup>&nbsp;&lt;= val &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= depth &lt;= the depth of tree + 1</code></li>
</ul>

## 解法

**方法一：DFS**

**方法二：BFS**

### **Java**

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int val;
    private int depth;

    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            return new TreeNode(val, root, null);
        }
        this.val = val;
        this.depth = depth;
        dfs(root, 1);
        return root;
    }

    private void dfs(TreeNode root, int d) {
        if (root == null) {
            return;
        }
        if (d == depth - 1) {
            TreeNode l = new TreeNode(val, root.left, null);
            TreeNode r = new TreeNode(val, null, root.right);
            root.left = l;
            root.right = r;
            return;
        }
        dfs(root.left, d + 1);
        dfs(root.right, d + 1);
    }
}
```

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            return new TreeNode(val, root, null);
        }
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int i = 0;
        while (!q.isEmpty()) {
            ++i;
            for (int k = q.size(); k > 0; --k) {
                TreeNode node = q.pollFirst();
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
                if (i == depth - 1) {
                    node.left = new TreeNode(val, node.left, null);
                    node.right = new TreeNode(val, null, node.right);
                }
            }
        }
        return root;
    }
}
```
