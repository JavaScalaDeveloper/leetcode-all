package com.solution._1558;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int minOperations(int[] nums) {
        int ans = 0;
        int mx = 0;
        for (int v : nums) {
            mx = Math.max(mx, v);
            ans += Integer.bitCount(v);
        }
        ans += Integer.toBinaryString(mx).length() - 1;
        return ans;
    }
}
