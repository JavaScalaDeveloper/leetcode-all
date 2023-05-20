package com.solution._0001;
import change.datastructure.*;
import change.tools.listnode.ArrayUtils;

import java.util.*;
public class Solution {
    public static void main(String[] args) {
        int[] arr = {1, 2, 6, 3, 8, 5};
        int target = 7;
        int[] result = twoSum(arr, target);
        System.out.println(ArrayUtils.toList(result));
    }
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0;; ++i) {
            int x = nums[i];
            int y = target - x;
            if (m.containsKey(y)) {
                return new int[] {m.get(y), i};
            }
            m.put(x, i);
        }
    }
}
