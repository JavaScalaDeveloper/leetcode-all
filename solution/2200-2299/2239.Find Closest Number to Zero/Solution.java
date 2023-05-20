package com.solution._2239;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int findClosestNumber(int[] nums) {
        int ans = 0, d = 1000000;
        for (int v : nums) {
            int t = Math.abs(v);
            if (t < d || (t == d && v > ans)) {
                ans = v;
                d = t;
            }
        }
        return ans;
    }
}
