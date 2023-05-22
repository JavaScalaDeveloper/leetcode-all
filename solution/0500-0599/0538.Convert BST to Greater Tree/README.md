# [538. 把二叉搜索树转换为累加树](https://leetcode.cn/problems/convert-bst-to-greater-tree)

## 题目描述

<p>给出二叉<strong> 搜索 </strong>树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 <code>node</code>&nbsp;的新值等于原树中大于或等于&nbsp;<code>node.val</code>&nbsp;的值之和。</p>

<p>提醒一下，二叉搜索树满足下列约束条件：</p>

<ul>
	<li>节点的左子树仅包含键<strong> 小于 </strong>节点键的节点。</li>
	<li>节点的右子树仅包含键<strong> 大于</strong> 节点键的节点。</li>
	<li>左右子树也必须是二叉搜索树。</li>
</ul>

<p><strong>注意：</strong>本题和 1038:&nbsp;<a href="https://leetcode.cn/problems/binary-search-tree-to-greater-sum-tree/">https://leetcode.cn/problems/binary-search-tree-to-greater-sum-tree/</a> 相同</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0538.Convert%20BST%20to%20Greater%20Tree/images/tree.png" style="height: 364px; width: 534px;"></strong></p>

<pre><strong>输入：</strong>[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
<strong>输出：</strong>[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>root = [0,null,1]
<strong>输出：</strong>[1,null,1]
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>root = [1,0,2]
<strong>输出：</strong>[3,3,2]
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>root = [3,2,4,1]
<strong>输出：</strong>[7,9,4,10]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li>树中的节点数介于 <code>0</code>&nbsp;和 <code>10<sup>4</sup></code><sup>&nbsp;</sup>之间。</li>
	<li>每个节点的值介于 <code>-10<sup>4</sup></code>&nbsp;和&nbsp;<code>10<sup>4</sup></code>&nbsp;之间。</li>
	<li>树中的所有值 <strong>互不相同</strong> 。</li>
	<li>给定的树为二叉搜索树。</li>
</ul>

## 解法

**前言**

二叉搜索树的中序遍历（左根右）结果是一个单调递增的有序序列，我们反序进行中序遍历（右根左），即可以得到一个单调递减的有序序列。通过累加单调递减的有序序列，我们可以得到大于等于 `node.val` 的新值，并重新赋值给 `node`。

关于反序中序遍历，有三种方法，一是递归遍历，二是栈实现非递归遍历，三是 Morris 遍历。

**方法一：递归**

按照“右根左”的顺序，递归遍历二叉搜索树，累加遍历到的所有节点值到s中，然后每次赋值给对应的 `node` 节点。

时间复杂度O(n)，空间复杂度O(n)。其中n是二叉搜索树的节点数。

**方法二：Morris 遍历**

Morris 遍历无需使用栈，时间复杂度O(n)，空间复杂度为O(1)。核心思想是：

定义 s 表示二叉搜索树节点值累加和。遍历二叉树节点：

1. 若当前节点 root 的右子树为空，**将当前节点值添加至 s** 中，更新当前节点值为 s，并将当前节点更新为 `root.left`。
2. 若当前节点 root 的右子树不为空，找到右子树的最左节点 next（也即是 root 节点在中序遍历下的后继节点）：
    - 若后继节点 next 的左子树为空，将后继节点的左子树指向当前节点 root，并将当前节点更新为 `root.right`。
    - 若后继节点 next 的左子树不为空，**将当前节点值添加 s** 中，更新当前节点值为 s，然后将后继节点左子树指向空（即解除 next 与 root 的指向关系），并将当前节点更新为 `root.left`。
3. 循环以上步骤，直至二叉树节点为空，遍历结束。
4. 最后返回二叉搜索树根节点即可。

> Morris 反序中序遍历跟 Morris 中序遍历思路一致，只是将中序遍历的“左根右”变为“右根左”。

递归遍历：

Morris 遍历：

### **Java**

递归遍历：

```java
class Solution {
    private int s;

    public TreeNode convertBST(TreeNode root) {
        dfs(root);
        return root;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.right);
        s += root.val;
        root.val = s;
        dfs(root.left);
    }
}
```

Morris 遍历：

```java
class Solution {
    public TreeNode convertBST(TreeNode root) {
        int s = 0;
        TreeNode node = root;
        while (root != null) {
            if (root.right == null) {
                s += root.val;
                root.val = s;
                root = root.left;
            } else {
                TreeNode next = root.right;
                while (next.left != null && next.left != root) {
                    next = next.left;
                }
                if (next.left == null) {
                    next.left = root;
                    root = root.right;
                } else {
                    s += root.val;
                    root.val = s;
                    next.left = null;
                    root = root.left;
                }
            }
        }
        return node;
    }
}
```

递归遍历：

Morris 遍历：

递归遍历：

Morris 遍历：
