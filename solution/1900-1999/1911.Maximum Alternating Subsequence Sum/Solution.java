package com.solution._1911;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public long maxAlternatingSum(int[] nums) {
        long f = 0, g = 0;
        for (int x : nums) {
            long ff = Math.max(g - x, f);
            long gg = Math.max(f + x, g);
            f = ff;
            g = gg;
        }
        return Math.max(f, g);
    }
}
