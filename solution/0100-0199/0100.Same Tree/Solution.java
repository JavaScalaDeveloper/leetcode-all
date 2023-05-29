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
package com.solution._0100;
import change.datastructure.*;
import change.tools.listnode.TreeNodeUtils;

import java.util.*;
public class Solution {
    public static void main(String[] args) {
        TreeNode treeNode1 = TreeNodeUtils.buildTree();
        TreeNode treeNode2 = TreeNodeUtils.buildTree();
        boolean res = isSameTree(treeNode1, treeNode2);
        System.out.println(res);
    }
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == q) return true;
        if (p == null || q == null || p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
