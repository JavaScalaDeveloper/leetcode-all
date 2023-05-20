package com.solution._2527;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int xorBeauty(int[] nums) {
        int ans = 0;
        for (int x : nums) {
            ans ^= x;
        }
        return ans;
    }
}
