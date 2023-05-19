# [230. 二叉搜索树中第 K 小的元素](https://leetcode.cn/problems/kth-smallest-element-in-a-bst)

[English Version](/solution/0200-0299/0230.Kth%20Smallest%20Element%20in%20a%20BST/README_EN.md)

## 题目描述

<p>给定一个二叉搜索树的根节点 <code>root</code> ，和一个整数 <code>k</code> ，请你设计一个算法查找其中第 <code>k</code><strong> </strong>个最小元素（从 1 开始计数）。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0230.Kth%20Smallest%20Element%20in%20a%20BST/images/kthtree1.jpg" style="width: 212px; height: 301px;" />
<pre>
<strong>输入：</strong>root = [3,1,4,null,2], k = 1
<strong>输出：</strong>1
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0230.Kth%20Smallest%20Element%20in%20a%20BST/images/kthtree2.jpg" style="width: 382px; height: 302px;" />
<pre>
<strong>输入：</strong>root = [5,3,6,2,4,null,null,1], k = 3
<strong>输出：</strong>3
</pre>

<p> </p>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中的节点数为 <code>n</code> 。</li>
	<li><code>1 <= k <= n <= 10<sup>4</sup></code></li>
	<li><code>0 <= Node.val <= 10<sup>4</sup></code></li>
</ul>

<p> </p>

<p><strong>进阶：</strong>如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 <code>k</code> 小的值，你将如何优化算法？</p>

## 解法

**方法一：中序遍历**

由于二叉搜索树的性质，中序遍历一定能得到升序序列，因此可以采用中序遍历找出第 k 小的元素。

**方法二：预处理结点数**

预处理每个结点作为根节点的子树的节点数。

这种算法可以用来优化频繁查找第 k 个树、而二叉搜索树本身不被修改的情况。

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
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stk = new ArrayDeque<>();
        while (root != null || !stk.isEmpty()) {
            if (root != null) {
                stk.push(root);
                root = root.left;
            } else {
                root = stk.pop();
                if (--k == 0) {
                    return root.val;
                }
                root = root.right;
            }
        }
        return 0;
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
    public int kthSmallest(TreeNode root, int k) {
        BST bst = new BST(root);
        return bst.kthSmallest(k);
    }
}

class BST {
    private TreeNode root;
    private Map<TreeNode, Integer> cnt = new HashMap<>();

    public BST(TreeNode root) {
        this.root = root;
        count(root);
    }

    public int kthSmallest(int k) {
        TreeNode node = root;
        while (node != null) {
            int v = node.left == null ? 0 : cnt.get(node.left);
            if (v == k - 1) {
                return node.val;
            }
            if (v < k - 1) {
                node = node.right;
                k -= (v + 1);
            } else {
                node = node.left;
            }
        }
        return 0;
    }

    private int count(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int n = 1 + count(root.left) + count(root.right);
        cnt.put(root, n);
        return n;
    }
}
```
