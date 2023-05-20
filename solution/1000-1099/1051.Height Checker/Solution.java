package com.solution._1051;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int heightChecker(int[] heights) {
        int[] expected = heights.clone();
        Arrays.sort(expected);
        int ans = 0;
        for (int i = 0; i < heights.length; ++i) {
            if (heights[i] != expected[i]) {
                ++ans;
            }
        }
        return ans;
    }
}
