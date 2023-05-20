# [106. 从中序与后序遍历序列构造二叉树](https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal)

## 题目描述

<p>给定两个整数数组 <code>inorder</code> 和 <code>postorder</code> ，其中 <code>inorder</code> 是二叉树的中序遍历， <code>postorder</code> 是同一棵树的后序遍历，请你构造并返回这颗&nbsp;<em>二叉树</em>&nbsp;。</p>

<p><strong>示例 1:</strong></p>
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0106.Construct%20Binary%20Tree%20from%20Inorder%20and%20Postorder%20Traversal/images/tree.jpg" />
<pre>
<b>输入：</b>inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
<b>输出：</b>[3,9,20,null,null,15,7]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<b>输入：</b>inorder = [-1], postorder = [-1]
<b>输出：</b>[-1]
</pre>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= inorder.length &lt;= 3000</code></li>
	<li><code>postorder.length == inorder.length</code></li>
	<li><code>-3000 &lt;= inorder[i], postorder[i] &lt;= 3000</code></li>
	<li><code>inorder</code>&nbsp;和&nbsp;<code>postorder</code>&nbsp;都由 <strong>不同</strong> 的值组成</li>
	<li><code>postorder</code>&nbsp;中每一个值都在&nbsp;<code>inorder</code>&nbsp;中</li>
	<li><code>inorder</code>&nbsp;<strong>保证</strong>是树的中序遍历</li>
	<li><code>postorder</code>&nbsp;<strong>保证</strong>是树的后序遍历</li>
</ul>

## 解法

**方法一：递归**

思路同 [105. 从前序与中序遍历序列构造二叉树](/solution/0100-0199/0105.Construct%20Binary%20Tree%20from%20Preorder%20and%20Inorder%20Traversal/README.md)。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉树的节点个数。

### **Java**

```java
class Solution {
    private Map<Integer, Integer> indexes = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; ++i) {
            indexes.put(inorder[i], i);
        }
        return dfs(inorder, postorder, 0, 0, inorder.length);
    }

    private TreeNode dfs(int[] inorder, int[] postorder, int i, int j, int n) {
        if (n <= 0) {
            return null;
        }
        int v = postorder[j + n - 1];
        int k = indexes.get(v);
        TreeNode root = new TreeNode(v);
        root.left = dfs(inorder, postorder, i, j, k - i);
        root.right = dfs(inorder, postorder, k + 1, j + k - i, n - k + i - 1);
        return root;
    }
}
```
