# [107. 二叉树的层序遍历 II](https://leetcode.cn/problems/binary-tree-level-order-traversal-ii)

## 题目描述

<p>给你二叉树的根节点 <code>root</code> ，返回其节点值 <strong>自底向上的层序遍历</strong> 。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0107.Binary%20Tree%20Level%20Order%20Traversal%20II/images/tree1.jpg" style="width: 277px; height: 302px;" />
<pre>
<strong>输入：</strong>root = [3,9,20,null,null,15,7]
<strong>输出：</strong>[[15,7],[9,20],[3]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = [1]
<strong>输出：</strong>[[1]]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>root = []
<strong>输出：</strong>[]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点数目在范围 <code>[0, 2000]</code> 内</li>
	<li><code>-1000 &lt;= Node.val &lt;= 1000</code></li>
</ul>

## 解法

同 [102](/solution/0100-0199/0102.Binary%20Tree%20Level%20Order%20Traversal/README.md)，最后反转一下结果即可。

### **Java**

```java
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        Deque<TreeNode> q = new LinkedList<>();
        q.offerLast(root);
        while (!q.isEmpty()) {
            List<Integer> t = new ArrayList<>();
            for (int i = q.size(); i > 0; --i) {
                TreeNode node = q.pollFirst();
                t.add(node.val);
                if (node.left != null) {
                    q.offerLast(node.left);
                }
                if (node.right != null) {
                    q.offerLast(node.right);
                }
            }
            ans.addFirst(t);
        }
        return ans;
    }
}
```
