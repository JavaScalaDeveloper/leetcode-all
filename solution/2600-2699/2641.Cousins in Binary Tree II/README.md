# [2641. 二叉树的堂兄弟节点 II](https://leetcode.cn/problems/cousins-in-binary-tree-ii)

## 题目描述

<p>给你一棵二叉树的根&nbsp;<code>root</code>&nbsp;，请你将每个节点的值替换成该节点的所有 <strong>堂兄弟节点值的和&nbsp;</strong>。</p>

<p>如果两个节点在树中有相同的深度且它们的父节点不同，那么它们互为 <strong>堂兄弟</strong>&nbsp;。</p>

<p>请你返回修改值之后，树的根<em>&nbsp;</em><code>root</code><em>&nbsp;</em>。</p>

<p><strong>注意</strong>，一个节点的深度指的是从树根节点到这个节点经过的边数。</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2641.Cousins%20in%20Binary%20Tree%20II/images/example11.png" style="width: 571px; height: 151px;" /></p>

<pre>
<b>输入：</b>root = [5,4,9,1,10,null,7]
<b>输出：</b>[0,0,0,7,7,null,11]
<b>解释：</b>上图展示了初始的二叉树和修改每个节点的值之后的二叉树。
- 值为 5 的节点没有堂兄弟，所以值修改为 0 。
- 值为 4 的节点没有堂兄弟，所以值修改为 0 。
- 值为 9 的节点没有堂兄弟，所以值修改为 0 。
- 值为 1 的节点有一个堂兄弟，值为 7 ，所以值修改为 7 。
- 值为 10 的节点有一个堂兄弟，值为 7 ，所以值修改为 7 。
- 值为 7 的节点有两个堂兄弟，值分别为 1 和 10 ，所以值修改为 11 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2600-2699/2641.Cousins%20in%20Binary%20Tree%20II/images/diagram33.png" style="width: 481px; height: 91px;" /></p>

<pre>
<b>输入：</b>root = [3,1,2]
<b>输出：</b>[0,0,0]
<b>解释：</b>上图展示了初始的二叉树和修改每个节点的值之后的二叉树。
- 值为 3 的节点没有堂兄弟，所以值修改为 0 。
- 值为 1 的节点没有堂兄弟，所以值修改为 0 。
- 值为 2 的节点没有堂兄弟，所以值修改为 0 。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点数目的范围是&nbsp;<code>[1, 10<sup>5</sup>]</code> 。</li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

**方法一：两次 DFS**

我们用一个数组 $s$ 记录二叉树每一层的节点值之和，其中 $s[i]$ 表示第 $i$ 层的节点值之和。

接下来，我们先跑一遍 DFS，计算出数组 $s$ 的值。然后再跑一遍 DFS，更新每个节点的子节点的值，子节点的值等于子节点所在层的节点值之和减去子节点及其兄弟节点的值。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉树的节点数。

**方法二：BFS**

我们先将根节点的值更新为 $0$，用一个队列 $q$ 来存储每一层的所有节点，初始时将根节点入队。

然后遍历队列，计算每一层的所有子节点的值之和 $s$，然后计算每个子节点及其兄弟节点的值之和 $t$，然后更新每个子节点的值为 $s - t$。

遍历结束后，返回根节点即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉树的节点数。

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
    private List<Integer> s = new ArrayList<>();

    public TreeNode replaceValueInTree(TreeNode root) {
        dfs1(root, 0);
        root.val = 0;
        dfs2(root, 1);
        return root;
    }

    private void dfs1(TreeNode root, int d) {
        if (root == null) {
            return;
        }
        if (s.size() <= d) {
            s.add(0);
        }
        s.set(d, s.get(d) + root.val);
        dfs1(root.left, d + 1);
        dfs1(root.right, d + 1);
    }

    private void dfs2(TreeNode root, int d) {
        if (root == null) {
            return;
        }
        int l = root.left == null ? 0 : root.left.val;
        int r = root.right == null ? 0 : root.right.val;
        if (root.left != null) {
            root.left.val = s.get(d) - l - r;
        }
        if (root.right != null) {
            root.right.val = s.get(d) - l - r;
        }
        dfs2(root.left, d + 1);
        dfs2(root.right, d + 1);
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
    public TreeNode replaceValueInTree(TreeNode root) {
        root.val = 0;
        List<TreeNode> q = List.of(root);
        while (!q.isEmpty()) {
            List<TreeNode> p = q;
            q = new ArrayList<>();
            int s = 0;
            for (TreeNode node : p) {
                if (node.left != null) {
                    q.add(node.left);
                    s += node.left.val;
                }
                if (node.right != null) {
                    q.add(node.right);
                    s += node.right.val;
                }
            }
            for (TreeNode node : p) {
                int t = (node.left == null ? 0 : node.left.val)
                    + (node.right == null ? 0 : node.right.val);
                if (node.left != null) {
                    node.left.val = s - t;
                }
                if (node.right != null) {
                    node.right.val = s - t;
                }
            }
        }
        return root;
    }
}
```
