package com.solution._0015;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        List<List<Integer>> res = threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        System.out.println(res);
    }
    /*
    将数组排序，这样相同的数字就会挨在一起，方便进行去重；
    从左到右枚举数组中的每个数字作为第一个数 a；
    在 a 右边的区域内使用双指针 left 和 right，找出所有满足 a + nums[left] + nums[right] == 0 的三元组，并将其加入结果集；
    注意去重。
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums); // 排序
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) { // 去重
                continue;
            }
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    while (left < right && nums[left] == nums[left - 1]) { // 去重
                        left++;
                    }
                    right--;
                    while (left < right && nums[right] == nums[right + 1]) { // 去重
                        right--;
                    }
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }
}
