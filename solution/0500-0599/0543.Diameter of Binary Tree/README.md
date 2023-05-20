# [543. 二叉树的直径](https://leetcode.cn/problems/diameter-of-binary-tree)

## 题目描述

<p>给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。</p>

<p><strong>示例 :</strong><br>
给定二叉树</p>

<pre>          1
         / \
        2   3
       / \     
      4   5    
</pre>

<p>返回&nbsp;<strong>3</strong>, 它的长度是路径 [4,2,1,3] 或者&nbsp;[5,2,1,3]。</p>

<p><strong>注意：</strong>两结点之间的路径长度是以它们之间边的数目表示。</p>

## 解法

**方法一**：后序遍历求每个结点的深度，此过程中获取每个结点左右子树的最长伸展（深度），迭代获取最长路径。

相似题目：[687. 最长同值路径](/solution/0600-0699/0687.Longest%20Univalue%20Path/README.md)

**方法二**：构建图，两次 DFS。

相似题目：[1245. 树的直径](/solution/1200-1299/1245.Tree%20Diameter/README.md), [1522. N 叉树的直径](/solution/1500-1599/1522.Diameter%20of%20N-Ary%20Tree/README.md)

### **Java**

```java
class Solution {
    private int ans;

    public int diameterOfBinaryTree(TreeNode root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        ans = Math.max(ans, left + right);
        return 1 + Math.max(left, right);
    }
}
```

**
