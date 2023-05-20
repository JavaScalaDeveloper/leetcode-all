package com.solution._0961;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int repeatedNTimes(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int num : nums) {
            if (s.contains(num)) {
                return num;
            }
            s.add(num);
        }
        return -1;
    }
}
