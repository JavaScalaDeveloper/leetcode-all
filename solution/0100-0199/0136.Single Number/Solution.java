package com.solution._0136;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int v : nums) {
            ans ^= v;
        }
        return ans;
    }
}
