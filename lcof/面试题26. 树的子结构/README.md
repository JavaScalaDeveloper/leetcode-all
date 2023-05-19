# [面试题 26. 树的子结构](https://leetcode.cn/problems/shu-de-zi-jie-gou-lcof/)

## 题目描述

<p>输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)</p>

<p>B是A的子结构， 即 A中有出现和B相同的结构和节点值。</p>

<p>例如:<br>
给定的树 A:</p>

<p><code>&nbsp; &nbsp; &nbsp;3<br>
&nbsp; &nbsp; / \<br>
&nbsp; &nbsp;4 &nbsp; 5<br>
&nbsp; / \<br>
&nbsp;1 &nbsp; 2</code><br>
给定的树 B：</p>

<p><code>&nbsp; &nbsp;4&nbsp;<br>
&nbsp; /<br>
&nbsp;1</code><br>
返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>A = [1,2,3], B = [3,1]
<strong>输出：</strong>false
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>A = [3,4,5,1,2], B = [4,1]
<strong>输出：</strong>true</pre>

<p><strong>限制：</strong></p>

<p><code>0 &lt;= 节点个数 &lt;= 10000</code></p>

## 解法

**方法一：DFS**

我们先判断 `A` 或 `B` 是否为空，如果 `A` 或 `B` 为空，直接返回 `false`。

然后我们定义一个 `dfs(A, B)` 函数，用于判断从 `A` 的根节点开始，是否存在一棵子树和 `B` 的结构相同，如果存在，返回 `true`，否则返回 `false`。

在 `dfs` 函数中，我们首先判断 `B` 是否为空，如果 `B` 为空，说明 `A` 的子树和 `B` 的结构相同，返回 `true`。

然后我们判断 `A` 是否为空，或者 `A` 和 `B` 的根节点值是否相同，如果 `A` 为空，或者 `A` 和 `B` 的根节点值不相同，说明 `A` 的子树和 `B` 的结构不同，返回 `false`。

最后我们返回 `dfs(A.left, B.left) and dfs(A.right, B.right)`，即 `A` 的左子树和 `B` 的左子树是否相同，以及 `A` 的右子树和 `B` 的右子树是否相同。

最后我们返回 `dfs(A, B) or isSubStructure(A.left, B) or isSubStructure(A.right, B)`，即 `A` 的子树和 `B` 的结构是否相同，或者 `A` 的左子树和 `B` 的结构是否相同，或者 `A` 的右子树和 `B` 的结构是否相同。

时间复杂度 $O(m \times n)$，空间复杂度 $O(\max(m, n))$。其中 $m$ 和 $n$ 分别为树 `A` 和 `B` 的节点数。

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
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        return dfs(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    private boolean dfs(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        if (A == null || A.val != B.val) {
            return false;
        }
        return dfs(A.left, B.left) && dfs(A.right, B.right);
    }
}
```
