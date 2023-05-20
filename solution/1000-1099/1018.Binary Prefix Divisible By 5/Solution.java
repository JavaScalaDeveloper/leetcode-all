package com.solution._1018;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        List<Boolean> ans = new ArrayList<>();
        int x = 0;
        for (int v : nums) {
            x = (x << 1 | v) % 5;
            ans.add(x == 0);
        }
        return ans;
    }
}
