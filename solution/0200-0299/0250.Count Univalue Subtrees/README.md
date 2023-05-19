# [250. 统计同值子树](https://leetcode.cn/problems/count-univalue-subtrees)

[English Version](/solution/0200-0299/0250.Count%20Univalue%20Subtrees/README_EN.md)

## 题目描述

<p>给定一个二叉树，统计该二叉树数值相同的子树个数。</p>

<p>同值子树是指该子树的所有节点都拥有相同的数值。</p>

<p><strong>示例：</strong></p>

<pre><strong>输入: </strong>root = [5,1,5,5,5,null,5]

              5
             / \
            1   5
           / \   \
          5   5   5

<strong>输出:</strong> 4
</pre>

## 解法

**方法一：递归**

我们设计一个递归函数 $dfs(root)$，该函数返回以 $root$ 为根的子树中所有节点的值是否相同。

函数 $dfs(root)$ 的递归过程如下：

-   如果 $root$ 为空，则返回 `true`；
-   否则，我们递归地计算 $root$ 的左右子树，记为 $l$ 和 $r$；如果 $l$ 为 `false` 或者 $r$ 为 `false`，则返回 `false`；如果 $root$ 的左子树不为空且 $root$ 的左子树的值不等于 $root$ 的值，或者 $root$ 的右子树不为空且 $root$ 的右子树的值不等于 $root$ 的值，则返回 `false`；否则，我们将答案加一，并返回 `true`。

递归结束后，返回答案即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉树的节点个数。

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
    private int ans;

    public int countUnivalSubtrees(TreeNode root) {
        dfs(root);
        return ans;
    }

    private boolean dfs(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean l = dfs(root.left);
        boolean r = dfs(root.right);
        if (!l || !r) {
            return false;
        }
        int a = root.left == null ? root.val : root.left.val;
        int b = root.right == null ? root.val : root.right.val;
        if (a == b && b == root.val) {
            ++ans;
            return true;
        }
        return false;
    }
}
```
