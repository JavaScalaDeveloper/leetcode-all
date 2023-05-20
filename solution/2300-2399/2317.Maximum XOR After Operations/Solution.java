package com.solution._2317;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int maximumXOR(int[] nums) {
        int ans = 0;
        for (int x : nums) {
            ans |= x;
        }
        return ans;
    }
}
