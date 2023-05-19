# [面试题 17.12. BiNode](https://leetcode.cn/problems/binode-lcci)

[English Version](/lcci/17.12.BiNode/README_EN.md)

## 题目描述


<p>二叉树数据结构<code>TreeNode</code>可用来表示单向链表（其中<code>left</code>置空，<code>right</code>为下一个链表节点）。实现一个方法，把二叉搜索树转换为单向链表，要求值的顺序保持不变，转换操作应是原址的，也就是在原始的二叉搜索树上直接修改。</p>

<p>返回转换后的单向链表的头节点。</p>

<p><strong>注意：</strong>本题相对原题稍作改动</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong> [4,2,5,1,3,null,6,0]
<strong>输出：</strong> [0,null,1,null,2,null,3,null,4,null,5,null,6]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li>节点数量不会超过 100000。</li>
</ul>

## 解法

**方法一：中序遍历**

中序遍历过程中改变指针指向。

时间复杂度 $O(n)$。

同 [897. 递增顺序查找树](/solution/0800-0899/0897.Increasing%20Order%20Search%20Tree/README.md)。

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
    private TreeNode prev;

    public TreeNode convertBiNode(TreeNode root) {
        TreeNode dummy = new TreeNode(0, null, root);
        prev = dummy;
        dfs(root);
        return dummy.right;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        prev.right = root;
        root.left = null;
        prev = root;
        dfs(root.right);
    }
}
```
