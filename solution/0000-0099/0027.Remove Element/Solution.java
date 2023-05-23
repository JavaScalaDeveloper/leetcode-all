package com.solution._0027;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public static void main(String[] args) {
        int res = removeElement2(new int[]{0, 1, 2, 2, 3, 0, 4, 2},2);
        System.out.println(res);
    }
    public static int removeElement2(int[] nums, int val) {
        int length = nums.length;
        for (int num : nums) {
            if (num == val) {
                length--;
            }
        }
        return length;
    }
    public int removeElement(int[] nums, int val) {
        int k = 0;
        for (int x : nums) {
            if (x != val) {
                nums[k++] = x;
            }
        }
        return k;
    }
}
