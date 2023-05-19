# [面试题 55 - I. 二叉树的深度](https://leetcode.cn/problems/er-cha-shu-de-shen-du-lcof/)

## 题目描述

<p>输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。</p>

<p>例如：</p>

<p>给定二叉树 <code>[3,9,20,null,null,15,7]</code>，</p>

<pre>    3
   / \
  9  20
    /  \
   15   7</pre>

<p>返回它的最大深度&nbsp;3 。</p>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>节点总数 &lt;= 10000</code></li>
</ol>

<p>注意：本题与主站 104&nbsp;题相同：<a href="https://leetcode.cn/problems/maximum-depth-of-binary-tree/">https://leetcode.cn/problems/maximum-depth-of-binary-tree/</a></p>

## 解法

**方法一：递归**

我们可以用递归的方法来解决这道题。递归的终止条件是当前节点为空，此时深度为 $0$；如果当前节点不为空，则当前的深度为其左右子树深度的最大值加 $1$，递归计算当前节点的左右子节点的深度，然后返回它们的最大值加 $1$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉树的节点数。最坏情况下，二叉树退化为链表，递归深度达到 $n$，系统使用 $O(n)$ 大小的栈空间。

### **Java**

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
```
