# [94. 二叉树的中序遍历](https://leetcode.cn/problems/binary-tree-inorder-traversal)

## 题目描述

<p>给定一个二叉树的根节点 <code>root</code> ，返回 <em>它的 <strong>中序</strong>&nbsp;遍历</em> 。</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0094.Binary%20Tree%20Inorder%20Traversal/images/inorder_1.jpg" style="height: 200px; width: 125px;" />
<pre>
<strong>输入：</strong>root = [1,null,2,3]
<strong>输出：</strong>[1,3,2]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = []
<strong>输出：</strong>[]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>root = [1]
<strong>输出：</strong>[1]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点数目在范围 <code>[0, 100]</code> 内</li>
	<li><code>-100 &lt;= Node.val &lt;= 100</code></li>
</ul>

<p><strong>进阶:</strong>&nbsp;递归算法很简单，你可以通过迭代算法完成吗？</p>

## 解法

**1. 递归遍历**

先递归左子树，再访问根节点，接着递归右子树。

**2. 栈实现非递归遍历**

非递归的思路如下：

1. 定义一个栈 stk
2. 将树的左节点依次入栈
3. 左节点为空时，弹出栈顶元素并处理
4. 重复 2-3 的操作

**3. Morris 实现中序遍历**

Morris 遍历无需使用栈，空间复杂度为 O(1)。核心思想是：

遍历二叉树节点，

1. 若当前节点 root 的左子树为空，**将当前节点值添加至结果列表 ans** 中，并将当前节点更新为 `root.right`
2. 若当前节点 root 的左子树不为空，找到左子树的最右节点 prev（也即是 root 节点在中序遍历下的前驱节点）：
    - 若前驱节点 prev 的右子树为空，将前驱节点的右子树指向当前节点 root，并将当前节点更新为 `root.left`。
    - 若前驱节点 prev 的右子树不为空，**将当前节点值添加至结果列表 ans** 中，然后将前驱节点右子树指向空（即解除 prev 与 root 的指向关系），并将当前节点更新为 `root.right`。
3. 循环以上步骤，直至二叉树节点为空，遍历结束。

递归：

栈实现非递归：

Morris 遍历:

### **Java**

递归：

```java
class Solution {
    private List<Integer> ans;

    public List<Integer> inorderTraversal(TreeNode root) {
        ans = new ArrayList<>();
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        ans.add(root.val);
        dfs(root.right);
    }
}
```

栈实现非递归：

```java
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> stk = new ArrayDeque<>();
        while (root != null || !stk.isEmpty()) {
            if (root != null) {
                stk.push(root);
                root = root.left;
            } else {
                root = stk.pop();
                ans.add(root.val);
                root = root.right;
            }
        }
        return ans;
    }
}
```

Morris 遍历:

```java
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        while (root != null) {
            if (root.left == null) {
                ans.add(root.val);
                root = root.right;
            } else {
                TreeNode prev = root.left;
                while (prev.right != null && prev.right != root) {
                    prev = prev.right;
                }
                if (prev.right == null) {
                    prev.right = root;
                    root = root.left;
                } else {
                    ans.add(root.val);
                    prev.right = null;
                    root = root.right;
                }
            }
        }
        return ans;
    }
}
```