# [114. 二叉树展开为链表](https://leetcode.cn/problems/flatten-binary-tree-to-linked-list)

## 题目描述

<p>给你二叉树的根结点 <code>root</code> ，请你将它展开为一个单链表：</p>

<ul>
	<li>展开后的单链表应该同样使用 <code>TreeNode</code> ，其中 <code>right</code> 子指针指向链表中下一个结点，而左子指针始终为 <code>null</code> 。</li>
	<li>展开后的单链表应该与二叉树 <a href="https://baike.baidu.com/item/%E5%85%88%E5%BA%8F%E9%81%8D%E5%8E%86/6442839?fr=aladdin" target="_blank"><strong>先序遍历</strong></a> 顺序相同。</li>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0114.Flatten%20Binary%20Tree%20to%20Linked%20List/images/flaten.jpg" style="width: 500px; height: 226px;" />
<pre>
<strong>输入：</strong>root = [1,2,5,3,4,null,6]
<strong>输出：</strong>[1,null,2,null,3,null,4,null,5,null,6]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = []
<strong>输出：</strong>[]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>root = [0]
<strong>输出：</strong>[0]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中结点数在范围 <code>[0, 2000]</code> 内</li>
	<li><code>-100 <= Node.val <= 100</code></li>
</ul>

<p> </p>

<p><strong>进阶：</strong>你可以使用原地算法（<code>O(1)</code> 额外空间）展开这棵树吗？</p>

## 解法

**方法一：寻找前驱节点**

先序遍历的访问顺序是“根、左子树、右子树”，左子树最后一个节点访问完后，接着会访问根节点的右子树节点。

因此，对于当前节点，如果其左子节点不为空，我们找到左子树的最右节点，作为前驱节点，然后将当前节点的右子节点赋给前驱节点的右子节点。然后将当前节点的左子节点赋给当前节点的右子节点，并将当前节点的左子节点置为空。然后将当前节点的右子节点作为下一个节点，继续处理，直至所有节点处理完毕。

时间复杂度 $O(n)$，空间复杂度 O(1)$。其中 $n$ 是树中节点的个数。

### **Java**

```java
class Solution {
    public void flatten(TreeNode root) {
        while (root != null) {
            if (root.left != null) {
                // 找到当前节点左子树的最右节点
                TreeNode pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }

                // 将左子树的最右节点指向原来的右子树
                pre.right = root.right;

                // 将当前节点指向左子树
                root.right = root.left;
                root.left = null;
            }
            root = root.right;
        }
    }
}
```
