# [104. 二叉树的最大深度](https://leetcode.cn/problems/maximum-depth-of-binary-tree)

## 题目描述

<p>给定一个二叉树，找出其最大深度。</p>

<p>二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。</p>

<p><strong>说明:</strong>&nbsp;叶子节点是指没有子节点的节点。</p>

<p><strong>示例：</strong><br>
给定二叉树 <code>[3,9,20,null,null,15,7]</code>，</p>

<pre>    3
   / \
  9  20
    /  \
   15   7</pre>

<p>返回它的最大深度&nbsp;3 。</p>

## 解法

**方法一：递归**

递归遍历左右子树，求左右子树的最大深度，然后取最大值加 $1$ 即可。

时间复杂度 $O(n)$，其中 $n$ 是二叉树的节点数。每个节点在递归中只被遍历一次。

### **Java**

```java
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = maxDepth(root.left);
        int r = maxDepth(root.right);
        return 1 + Math.max(l, r);
    }
}
```
