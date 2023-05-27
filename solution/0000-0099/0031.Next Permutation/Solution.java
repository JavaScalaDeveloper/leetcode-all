package com.solution._0031;

import change.tools.listnode.ArrayUtils;

public class Solution {
    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2};
        nextPermutation(arr);
        System.out.println(ArrayUtils.toList(arr));
    }

    public static void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 2;
        for (; i >= 0; --i) {
            if (nums[i] < nums[i + 1]) {
                break;
            }
        }//[1, 3, 4, 2]
        if (i >= 0) {
            for (int j = n - 1; j > i; --j) {
                if (nums[j] > nums[i]) {
                    swap(nums, i, j);
                    break;
                }
            }
        }//[1, 4, 3, 2]
        for (int j = i + 1, k = n - 1; j < k; ++j, --k) {
            swap(nums, j, k);
        }//[1, 4, 2, 3]
    }

    private static void swap(int[] nums, int i, int j) {
        int t = nums[j];
        nums[j] = nums[i];
        nums[i] = t;
    }
}
