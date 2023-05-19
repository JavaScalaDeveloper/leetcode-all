# [1382. 将二叉搜索树变平衡](https://leetcode.cn/problems/balance-a-binary-search-tree)

[English Version](/solution/1300-1399/1382.Balance%20a%20Binary%20Search%20Tree/README_EN.md)

## 题目描述

<p>给你一棵二叉搜索树，请你返回一棵&nbsp;<strong>平衡后</strong>&nbsp;的二叉搜索树，新生成的树应该与原来的树有着相同的节点值。如果有多种构造方法，请你返回任意一种。</p>

<p>如果一棵二叉搜索树中，每个节点的两棵子树高度差不超过 <code>1</code> ，我们就称这棵二叉搜索树是&nbsp;<strong>平衡的</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1382.Balance%20a%20Binary%20Search%20Tree/images/balance1-tree.jpg" style="height: 319px; width: 500px;" /></p>

<pre>
<strong>输入：</strong>root = [1,null,2,null,3,null,4,null,null]
<strong>输出：</strong>[2,1,3,null,null,null,4]
<strong>解释：</strong>这不是唯一的正确答案，[3,1,4,null,2,null,null] 也是一个可行的构造方案。
</pre>

<p><strong>示例 2：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1382.Balance%20a%20Binary%20Search%20Tree/images/balanced2-tree.jpg" style="height: 145px; width: 224px;" /></p>

<pre>
<strong>输入:</strong> root = [2,1,3]
<strong>输出:</strong> [2,1,3]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树节点的数目在&nbsp;<code>[1, 10<sup>4</sup>]</code>&nbsp;范围内。</li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

先中序遍历获取到升序排列的每个节点值，然后分治构建二叉树。

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
    private List<Integer> vals;

    public TreeNode balanceBST(TreeNode root) {
        vals = new ArrayList<>();
        dfs(root);
        return build(0, vals.size() - 1);
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        vals.add(root.val);
        dfs(root.right);
    }

    private TreeNode build(int i, int j) {
        if (i > j) {
            return null;
        }
        int mid = (i + j) >> 1;
        TreeNode root = new TreeNode(vals.get(mid));
        root.left = build(i, mid - 1);
        root.right = build(mid + 1, j);
        return root;
    }
}
```
