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
package com.solution._0108;
import change.datastructure.*;
import change.tools.listnode.TreeNodeUtils;

import java.util.*;
public class Solution {
    private int[] nums;

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        Solution solution = new Solution();
        TreeNode res = solution.sortedArrayToBST(arr);
        TreeNodeUtils.printTree(res);
    }
    public TreeNode sortedArrayToBST(int[] nums) {
        this.nums = nums;
        return dfs(0, nums.length - 1);
    }

    private TreeNode dfs(int l, int r) {
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        TreeNode left = dfs(l, mid - 1);
        TreeNode right = dfs(mid + 1, r);
        return new TreeNode(nums[mid], left, right);
    }
}
