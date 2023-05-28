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
package com.solution._0095;

import change.datastructure.TreeNode;
import change.tools.listnode.TreeNodeUtils;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        List<TreeNode> res = generateTrees(3);
        for (TreeNode tree : res) {
            TreeNodeUtils.printTree(tree);
        }
    }

    public static List<TreeNode> generateTrees(int n) {
        return generateTrees(1, n);
    }

    private static List<TreeNode> generateTrees(int left, int right) {
        List<TreeNode> ans = new ArrayList<>();
        if (left > right) {
            ans.add(null);
        } else {
            for (int i = left; i <= right; ++i) {
                List<TreeNode> leftTrees = generateTrees(left, i - 1);
                List<TreeNode> rightTrees = generateTrees(i + 1, right);
                for (TreeNode l : leftTrees) {
                    for (TreeNode r : rightTrees) {
                        TreeNode node = new TreeNode(i, l, r);
                        ans.add(node);
                    }
                }
            }
        }
        return ans;
    }
}
