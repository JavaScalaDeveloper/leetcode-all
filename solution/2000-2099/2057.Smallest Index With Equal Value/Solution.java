package com.solution._2057;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int smallestEqual(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            if (i % 10 == nums[i]) {
                return i;
            }
        }
        return -1;
    }
}
