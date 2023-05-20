package com.solution._0581;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int[] arr = nums.clone();
        Arrays.sort(arr);
        int left = 0, right = nums.length - 1;
        while (left <= right && nums[left] == arr[left]) {
            ++left;
        }
        while (left <= right && nums[right] == arr[right]) {
            --right;
        }
        return right - left + 1;
    }
}
