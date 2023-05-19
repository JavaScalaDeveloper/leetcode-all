# [剑指 Offer II 052. 展平二叉搜索树](https://leetcode.cn/problems/NYBBNL)

## 题目描述



<p>给你一棵二叉搜索树，请&nbsp;<strong>按中序遍历</strong> 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20052.%20%E5%B1%95%E5%B9%B3%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91/images/ex1.jpg" style="width: 600px; height: 350px;" /></p>

<pre>
<strong>输入：</strong>root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
<strong>输出：</strong>[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20052.%20%E5%B1%95%E5%B9%B3%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91/images/ex2.jpg" style="width: 300px; height: 114px;" /></p>

<pre>
<strong>输入：</strong>root = [5,1,7]
<strong>输出：</strong>[1,null,5,null,7]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点数的取值范围是 <code>[1, 100]</code></li>
	<li><code>0 &lt;= Node.val &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 897&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/increasing-order-search-tree/">https://leetcode.cn/problems/increasing-order-search-tree/</a></p>

## 解法

由于二叉搜索树的性质，可以利用中序遍历得到递增序列

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
    public TreeNode increasingBST(TreeNode root) {
        TreeNode head = null, tail = null;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if (head == null) {
                head = cur;
            } else {
                tail.right = cur;
            }
            tail = cur;
            cur.left = null;
            cur = cur.right;
        }
        return head;
    }
}
```

### **C**

```c
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */

struct TreeNode *dfs(struct TreeNode *root, struct TreeNode *cur) {
    if (!root) {
        return cur;
    }
    cur = dfs(root->left, cur);
    cur->right = malloc(sizeof(struct TreeNode));
    cur->right->val = root->val;
    cur->right->left = NULL;
    cur->right->right = NULL;
    cur = cur->right;
    return dfs(root->right, cur);
}

struct TreeNode *increasingBST(struct TreeNode *root) {
    struct TreeNode *dummy = malloc(sizeof(struct TreeNode));
    dfs(root, dummy);
    return dummy->right;
}
```
