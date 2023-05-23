package com.solution._0026;

public class Solution {
    public static void main(String[] args) {
        int res = removeDuplicates(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4});
        System.out.println(res);
    }

    public static int removeDuplicates(int[] nums) {
        int k = 0;
        for (int x : nums) {
            if (k == 0 || x != nums[k - 1]) {
                System.out.println("k=" + k + ",num[k]=" + nums[k] + ",x=" + x);
                nums[k++] = x;
            }
        }
        return k;
    }
}
