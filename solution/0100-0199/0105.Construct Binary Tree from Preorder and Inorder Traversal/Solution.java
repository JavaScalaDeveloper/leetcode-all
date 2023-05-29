/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
package com.solution._0105;

import change.datastructure.TreeNode;
import change.tools.listnode.TreeNodeUtils;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    private final Map<Integer, Integer> indexes = new HashMap<>();

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] preorder = new int[]{3, 9, 20, 15, 7}, inorder = {9, 3, 15, 20, 7};
        TreeNode res = solution.buildTree(preorder, inorder);
        TreeNodeUtils.printTree(res);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; ++i) {
            //存储中序遍历的index
            indexes.put(inorder[i], i);
        }
        return dfs(preorder, inorder, 0, 0, preorder.length);
    }

    private TreeNode dfs(int[] preorder, int[] inorder, int i, int j, int n) {
        if (n <= 0) {
            return null;
        }
        int v = preorder[i];
        //通过前序遍历的值找到中序遍历的index
        int k = indexes.get(v);
        TreeNode root = new TreeNode(v);
        root.left = dfs(preorder, inorder, i + 1, j, k - j);
        root.right = dfs(preorder, inorder, i + 1 + k - j, k + 1, n - k + j - 1);
        return root;
    }
}
