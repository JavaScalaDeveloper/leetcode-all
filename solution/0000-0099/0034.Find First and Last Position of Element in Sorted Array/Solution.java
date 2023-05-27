package com.solution._0034;

import change.tools.listnode.ArrayUtils;

public class Solution {
    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 7, 7, 8, 8, 8, 10};
        int target = 8;
        int[] result = searchRange(arr, target);
        System.out.println(ArrayUtils.toList(result));
    }

    public static int[] searchRange(int[] nums, int target) {
        int l = search(nums, target);
//        找到大于target的第一个数的最小index
        int r = search(nums, target + 1);
        return l == r ? new int[]{-1, -1} : new int[]{l, r - 1};
    }

    private static int search(int[] nums, int x) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] >= x) {
//                如果找到了，继续往左找，直到找到了最小的index
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
