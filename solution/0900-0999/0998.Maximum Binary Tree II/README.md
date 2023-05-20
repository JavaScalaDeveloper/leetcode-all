# [998. 最大二叉树 II](https://leetcode.cn/problems/maximum-binary-tree-ii)

## 题目描述

<p><strong>最大树</strong> 定义：一棵树，并满足：其中每个节点的值都大于其子树中的任何其他值。</p>

<p>给你最大树的根节点 <code>root</code> 和一个整数 <code>val</code> 。</p>

<p>就像 <a href="https://leetcode.cn/problems/maximum-binary-tree/" target="_blank">之前的问题</a> 那样，给定的树是利用 <code>Construct(a)</code>&nbsp;例程从列表&nbsp;<code>a</code>（<code>root = Construct(a)</code>）递归地构建的：</p>

<ul>
	<li>如果 <code>a</code> 为空，返回&nbsp;<code>null</code> 。</li>
	<li>否则，令&nbsp;<code>a[i]</code> 作为 <code>a</code> 的最大元素。创建一个值为&nbsp;<code>a[i]</code>&nbsp;的根节点 <code>root</code> 。</li>
	<li><code>root</code>&nbsp;的左子树将被构建为&nbsp;<code>Construct([a[0], a[1], ..., a[i - 1]])</code> 。</li>
	<li><code>root</code>&nbsp;的右子树将被构建为&nbsp;<code>Construct([a[i + 1], a[i + 2], ..., a[a.length - 1]])</code> 。</li>
	<li>返回&nbsp;<code>root</code> 。</li>
</ul>

<p>请注意，题目没有直接给出 <code>a</code> ，只是给出一个根节点&nbsp;<code>root = Construct(a)</code> 。</p>

<p>假设 <code>b</code> 是 <code>a</code> 的副本，并在末尾附加值 <code>val</code>。题目数据保证 <code>b</code> 中的值互不相同。</p>

<p>返回&nbsp;<code>Construct(b)</code> 。</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0998.Maximum%20Binary%20Tree%20II/images/maximum-binary-tree-1-1.png" style="height: 160px; width: 159px;" /><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0998.Maximum%20Binary%20Tree%20II/images/maximum-binary-tree-1-2.png" style="height: 160px; width: 169px;" /></strong></p>

<pre>
<strong>输入：</strong>root = [4,1,3,null,null,2], val = 5
<strong>输出：</strong>[5,4,null,1,3,null,null,2]
<strong>解释：</strong>a = [1,4,2,3], b = [1,4,2,3,5]</pre>

<p><strong>示例 2：<br />
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0998.Maximum%20Binary%20Tree%20II/images/maximum-binary-tree-2-1.png" style="height: 160px; width: 180px;" /><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0998.Maximum%20Binary%20Tree%20II/images/maximum-binary-tree-2-2.png" style="height: 160px; width: 214px;" /></strong></p>

<pre>
<strong>输入：</strong>root = [5,2,4,null,1], val = 3
<strong>输出：</strong>[5,2,4,null,1,null,3]
<strong>解释：</strong>a = [2,1,5,4], b = [2,1,5,4,3]</pre>

<p><strong>示例 3：<br />
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0998.Maximum%20Binary%20Tree%20II/images/maximum-binary-tree-3-1.png" style="height: 160px; width: 180px;" /><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0998.Maximum%20Binary%20Tree%20II/images/maximum-binary-tree-3-2.png" style="height: 160px; width: 201px;" /></strong></p>

<pre>
<strong>输入：</strong>root = [5,2,3,null,1], val = 4
<strong>输出：</strong>[5,2,4,null,1,3]
<strong>解释：</strong>a = [2,1,5,3], b = [2,1,5,3,4]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点数目在范围 <code>[1, 100]</code> 内</li>
	<li><code>1 &lt;= Node.val &lt;= 100</code></li>
	<li>树中的所有值 <strong>互不相同</strong></li>
	<li><code>1 &lt;= val &lt;= 100</code></li>
</ul>

## 解法

**方法一：递归**

如果 $val$ 是最大数，那么将 $val$ 作为新的根节点，$root$ 作为新的根节点的左子树。

如果 $val$ 不是最大数，由于 $val$ 是在最后追加的数，那么一定是在 $root$ 的右边，所以将 $val$ 作为新节点插入 $root$ 的右子树即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。

**方法二：迭代**

搜索右子树，找到 $curr.val > val > curr.right.val$ 的节点，然后创建新的节点 $node$，把 $node.left$ 指向 $curr.right$，然后 $curr.right$ 指向 $node$。

最后返回 $root$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。

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
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root == null || root.val < val) {
            return new TreeNode(val, root, null);
        }
        root.right = insertIntoMaxTree(root.right, val);
        return root;
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
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root.val < val) {
            return new TreeNode(val, root, null);
        }
        TreeNode curr = root;
        TreeNode node = new TreeNode(val);
        while (curr.right != null && curr.right.val > val) {
            curr = curr.right;
        }
        node.left = curr.right;
        curr.right = node;
        return root;
    }
}
```

**
