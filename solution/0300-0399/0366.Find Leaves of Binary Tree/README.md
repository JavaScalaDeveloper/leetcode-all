# [366. 寻找二叉树的叶子节点](https://leetcode.cn/problems/find-leaves-of-binary-tree)

[English Version](/solution/0300-0399/0366.Find%20Leaves%20of%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵二叉树，请按以下要求的顺序收集它的全部节点：</p>

<ol>
	<li>依次从左到右，每次收集并删除所有的叶子节点</li>
	<li>重复如上过程直到整棵树为空</li>
</ol>

<p>&nbsp;</p>

<p><strong>示例:</strong></p>

<pre><strong>输入: </strong>[1,2,3,4,5]
&nbsp; 
&nbsp;         1
         / \
        2   3
       / \     
      4   5    

<strong>输出: </strong>[[4,5,3],[2],[1]]
</pre>

<p>&nbsp;</p>

<p><strong>解释:</strong></p>

<p>1. 删除叶子节点&nbsp;<code>[4,5,3]</code> ，得到如下树结构：</p>

<pre>          1
         / 
        2          
</pre>

<p>&nbsp;</p>

<p>2. 现在删去叶子节点&nbsp;<code>[2]</code>&nbsp;，得到如下树结构：</p>

<pre>          1          
</pre>

<p>&nbsp;</p>

<p>3. 现在删去叶子节点&nbsp;<code>[1]</code>&nbsp;，得到空树：</p>

<pre>          []         
</pre>

## 解法

添加前置节点 prev，初始时 `prev.left = root`。

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
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        TreeNode prev = new TreeNode(0, root, null);
        while (prev.left != null) {
            List<Integer> t = new ArrayList<>();
            dfs(prev.left, prev, t);
            res.add(t);
        }
        return res;
    }

    private void dfs(TreeNode root, TreeNode prev, List<Integer> t) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            t.add(root.val);
            if (prev.left == root) {
                prev.left = null;
            } else {
                prev.right = null;
            }
        }
        dfs(root.left, root, t);
        dfs(root.right, root, t);
    }
}
```
