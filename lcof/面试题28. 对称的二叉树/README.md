# [面试题 28. 对称的二叉树](https://leetcode.cn/problems/dui-cheng-de-er-cha-shu-lcof/)

## 题目描述

<p>请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。</p>

<p>例如，二叉树&nbsp;[1,2,2,3,4,4,3] 是对称的。</p>

<p><code>&nbsp; &nbsp; 1<br>
&nbsp; &nbsp;/ \<br>
&nbsp; 2 &nbsp; 2<br>
&nbsp;/ \ / \<br>
3 &nbsp;4 4 &nbsp;3</code><br>
但是下面这个&nbsp;[1,2,2,null,3,null,3] 则不是镜像对称的:</p>

<p><code>&nbsp; &nbsp; 1<br>
&nbsp; &nbsp;/ \<br>
&nbsp; 2 &nbsp; 2<br>
&nbsp; &nbsp;\ &nbsp; \<br>
&nbsp; &nbsp;3 &nbsp; &nbsp;3</code></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>root = [1,2,2,3,4,4,3]
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>root = [1,2,2,null,3,null,3]
<strong>输出：</strong>false</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<p><code>0 &lt;= 节点个数 &lt;= 1000</code></p>

<p>注意：本题与主站 101 题相同：<a href="https://leetcode.cn/problems/symmetric-tree/">https://leetcode.cn/problems/symmetric-tree/</a></p>

## 解法

**方法一：递归**

我们设计一个递归函数 `dfs`，它接收两个参数 `a` 和 `b`，分别代表两棵树的根节点。我们可以对 `a` 和 `b` 进行如下判断：

-   如果 `a` 和 `b` 都为空，说明两棵树都遍历完了，返回 `true`；
-   如果 `a` 和 `b` 中有且只有一个为空，说明两棵树的结构不同，返回 `false`；
-   如果 `a` 和 `b` 的值不相等，说明两棵树的结构不同，返回 `false`；
-   如果 `a` 和 `b` 的值相等，那么我们分别递归地判断 `a` 的左子树和 `b` 的右子树，以及 `a` 的右子树和 `b` 的左子树是否对称。

最后，我们返回 `dfs(root, root)`，即判断 `root` 的左子树和右子树是否对称。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉树的节点数。

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
    public boolean isSymmetric(TreeNode root) {
        return dfs(root, root);
    }

    private boolean dfs(TreeNode a, TreeNode b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null || a.val != b.val) {
            return false;
        }
        return dfs(a.left, b.right) && dfs(a.right, b.left);
    }
}
```
