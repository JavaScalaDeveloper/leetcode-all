# [101. 对称二叉树](https://leetcode.cn/problems/symmetric-tree)

## 题目描述

<p>给你一个二叉树的根节点 <code>root</code> ， 检查它是否轴对称。</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0101.Symmetric%20Tree/images/symtree1.jpg" style="width: 354px; height: 291px;" />
<pre>
<strong>输入：</strong>root = [1,2,2,3,4,4,3]
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0101.Symmetric%20Tree/images/symtree2.jpg" style="width: 308px; height: 258px;" />
<pre>
<strong>输入：</strong>root = [1,2,2,null,3,null,3]
<strong>输出：</strong>false
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点数目在范围 <code>[1, 1000]</code> 内</li>
	<li><code>-100 &lt;= Node.val &lt;= 100</code></li>
</ul>

<p><strong>进阶：</strong>你可以运用递归和迭代两种方法解决这个问题吗？</p>

## 解法

**方法一：递归**

我们设计一个函数dfs(root1, root2)，用于判断两个二叉树是否对称。答案即为dfs(root, root)。

函数dfs(root1, root2)的逻辑如下：

-   如果root1和root2都为空，则两个二叉树对称，返回 `true`；
-   如果root1和root2中只有一个为空，或者root1.val \neq root2.val，则两个二叉树不对称，返回 `false`；
-   否则，判断root1的左子树和root2的右子树是否对称，以及root1的右子树和root2的左子树是否对称，这里使用了递归。

时间复杂度O(n)，空间复杂度O(n)。其中n是二叉树的节点数。

### **Java**

```java
class Solution {
    public boolean isSymmetric(TreeNode root) {
        return dfs(root, root);
    }

    private boolean dfs(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null || root1.val != root2.val) {
            return false;
        }
        return dfs(root1.left, root2.right) && dfs(root1.right, root2.left);
    }
}
```

```

```
