# [面试题 07. 重建二叉树](https://leetcode.cn/problems/zhong-jian-er-cha-shu-lcof/)

## 题目描述

<p>输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。</p>

<p>假设输入的前序遍历和中序遍历的结果中都不含重复的数字。</p>

<p> </p>

<p><strong>示例 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof/%E9%9D%A2%E8%AF%95%E9%A2%9807.%20%E9%87%8D%E5%BB%BA%E4%BA%8C%E5%8F%89%E6%A0%91/images/tree.jpg" />
<pre>
<strong>Input:</strong> preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
<strong>Output:</strong> [3,9,20,null,null,15,7]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>Input:</strong> preorder = [-1], inorder = [-1]
<strong>Output:</strong> [-1]
</pre>

<p> </p>

<p><strong>限制：</strong></p>

<p><code>0 <= 节点个数 <= 5000</code></p>

<p> </p>

<p><strong>注意</strong>：本题与主站 105 题重复：<a href="https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/">https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/</a></p>

## 解法

**方法一：哈希表 + 递归**

由于我们每一次都需要在中序序列中找到根节点的位置，因此我们可以使用哈希表 $d$ 来存储中序序列的值和索引，这样可以将查找的时间复杂度降低到 $O(1)$。

接下来，我们设计一个递归函数 $dfs(i, j, n)$，表示在前序序列中，从第 $i$ 个节点开始的 $n$ 个节点，对应的中序序列中，从第 $j$ 个节点开始的 $n$ 个节点，构造出的二叉树。

函数 $dfs(i, j, n)$ 的执行过程如下：

如果 $n = 0$，说明已经没有节点了，返回 $null$；

否则，我们取前序序列的第 $i$ 个节点作为根节点，创建一个树节点，即 `root = new TreeNode(preorder[i])`，然后我们在中序序列中找到根节点的位置，记为 $k$，则根节点左边的 $k - j$ 个节点为左子树，右边的 $n - k + j - 1$ 个节点为右子树。递归地调用函数 $dfs(i + 1, j, k - j)$ 构造左子树，调用函数 $dfs(i + k - j + 1, k + 1, n - k + j - 1)$ 构造右子树。最后返回根节点 `root`。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉树的节点个数。

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
    private Map<Integer, Integer> d = new HashMap<>();
    private int[] preorder;
    private int[] inorder;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = inorder.length;
        for (int i = 0; i < n; ++i) {
            d.put(inorder[i], i);
        }
        this.preorder = preorder;
        this.inorder = inorder;
        return dfs(0, 0, n);
    }

    private TreeNode dfs(int i, int j, int n) {
        if (n < 1) {
            return null;
        }
        int k = d.get(preorder[i]);
        int l = k - j;
        TreeNode root = new TreeNode(preorder[i]);
        root.left = dfs(i + 1, j, l);
        root.right = dfs(i + 1 + l, k + 1, n - l - 1);
        return root;
    }
}
```

### **TypeScript**
