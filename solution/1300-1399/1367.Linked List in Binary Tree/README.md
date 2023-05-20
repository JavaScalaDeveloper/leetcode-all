# [1367. 二叉树中的链表](https://leetcode.cn/problems/linked-list-in-binary-tree)

## 题目描述

<p>给你一棵以&nbsp;<code>root</code>&nbsp;为根的二叉树和一个&nbsp;<code>head</code>&nbsp;为第一个节点的链表。</p>

<p>如果在二叉树中，存在一条一直向下的路径，且每个点的数值恰好一一对应以&nbsp;<code>head</code>&nbsp;为首的链表中每个节点的值，那么请你返回 <code>True</code> ，否则返回 <code>False</code> 。</p>

<p>一直向下的路径的意思是：从树中某个节点开始，一直连续向下的路径。</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1367.Linked%20List%20in%20Binary%20Tree/images/sample_1_1720.png" style="height: 280px; width: 220px;"></strong></p>

<pre><strong>输入：</strong>head = [4,2,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
<strong>输出：</strong>true
<strong>解释：</strong>树中蓝色的节点构成了与链表对应的子路径。
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1367.Linked%20List%20in%20Binary%20Tree/images/sample_2_1720.png" style="height: 280px; width: 220px;"></strong></p>

<pre><strong>输入：</strong>head = [1,4,2,6], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
<strong>输出：</strong>true
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>head = [1,4,2,6,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
<strong>输出：</strong>false
<strong>解释：</strong>二叉树中不存在一一对应链表的路径。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li>二叉树和链表中的每个节点的值都满足&nbsp;<code>1 &lt;= node.val&nbsp;&lt;= 100</code>&nbsp;。</li>
	<li>链表包含的节点数目在&nbsp;<code>1</code>&nbsp;到&nbsp;<code>100</code>&nbsp;之间。</li>
	<li>二叉树包含的节点数目在&nbsp;<code>1</code>&nbsp;到&nbsp;<code>2500</code>&nbsp;之间。</li>
</ul>

## 解法

**方法一：递归**

我们设计一个递归函数 $dfs(head, root)$，表示链表 $head$ 是否是以 $root$ 为起点的路径上的节点值一一对应的子路径。函数 $dfs(head, root)$ 的逻辑如下：

-   如果链表 $head$ 为空，说明链表已经遍历完了，返回 `true`；
-   如果二叉树 $root$ 为空，说明二叉树已经遍历完了，但链表还没遍历完，返回 `false`；
-   如果二叉树 $root$ 的值与链表 $head$ 的值不相等，返回 `false`；
-   否则，返回 $dfs(head.next, root.left)$ 或 $dfs(head.next, root.right)$。

我们在主函数中，对二叉树的每个节点调用 $dfs(head, root)$，只要有一个返回 `true`，就说明链表是二叉树的子路径，返回 `true`；如果所有节点都返回 `false`，说明链表不是二叉树的子路径，返回 `false`。

时间复杂度 $O(n^2)，空间复杂度 O(n)$。其中 $n$ 是二叉树的节点数。

### **Java**

```java
class Solution {
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (root == null) {
            return false;
        }
        return dfs(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    private boolean dfs(ListNode head, TreeNode root) {
        if (head == null) {
            return true;
        }
        if (root == null || head.val != root.val) {
            return false;
        }
        return dfs(head.next, root.left) || dfs(head.next, root.right);
    }
}
```
