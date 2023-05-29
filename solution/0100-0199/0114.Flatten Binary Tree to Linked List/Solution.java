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
package com.solution._0114;
import change.datastructure.*;
import change.tools.listnode.TreeNodeUtils;

import java.util.*;
public class Solution {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1, new TreeNode(2, null, null), new TreeNode(3, null, null));
        flatten(treeNode);
        TreeNodeUtils.printTree(treeNode);
    }
    public static void flatten(TreeNode root) {
        while (root != null) {
            if (root.left != null) {
                TreeNode pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = root.right;
                root.right = root.left;
                root.left = null;
            }
            root = root.right;
        }
    }

    /*
    递归
     */
    public static void flatten2(TreeNode root) {
        if (root == null) {
            return;
        }
        // 1. 展开左子树
        flatten(root.left);
        TreeNode temp = root.right;
        // 2. 展开右子树，并链接到已展开的左子树后面
        flatten(temp);
        root.right = root.left;
        root.left = null;
        while (root.right != null) {
            root = root.right;
        }
        root.right = temp;
    }
}
