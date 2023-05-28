package com.solution._0053;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int res = maxSubArray(arr);
        int res2 = maxSubArray2(arr);
        System.out.println(res);
        System.out.println(res2);
    }
    public static int maxSubArray(int[] nums) {
        int f = nums[0], res = nums[0];
        for (int i = 1, n = nums.length; i < n; ++i) {
            f = nums[i] + Math.max(f, 0);
            res = Math.max(res, f);
        }
        return res;
    }
    public static int maxSubArray2(int[] nums) {
        return maxSub2(nums, 0, nums.length - 1);
    }

    private static int maxSub2(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        int mid = (left + right) >>> 1;
        int lsum = maxSub2(nums, left, mid);
        int rsum = maxSub2(nums, mid + 1, right);
        return Math.max(Math.max(lsum, rsum), crossMaxSub2(nums, left, mid, right));
    }

    private static int crossMaxSub2(int[] nums, int left, int mid, int right) {
        int lsum = 0, rsum = 0;
        int lmx = Integer.MIN_VALUE, rmx = Integer.MIN_VALUE;
        for (int i = mid; i >= left; --i) {
            lsum += nums[i];
            lmx = Math.max(lmx, lsum);
        }
        for (int i = mid + 1; i <= right; ++i) {
            rsum += nums[i];
            rmx = Math.max(rmx, rsum);
        }
        return lmx + rmx;
    }
}
