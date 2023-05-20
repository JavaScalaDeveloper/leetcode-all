package com.solution._0724;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int pivotIndex(int[] nums) {
        int left = 0, right = Arrays.stream(nums).sum();
        for (int i = 0; i < nums.length; ++i) {
            right -= nums[i];
            if (left == right) {
                return i;
            }
            left += nums[i];
        }
        return -1;
    }
}
