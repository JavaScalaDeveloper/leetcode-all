package com.solution._1963;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int minSwaps(String s) {
        int ans = 0;
        for (char c : s.toCharArray()) {
            if (c == '[') {
                ++ans;
            } else if (ans > 0) {
                --ans;
            }
        }
        return (ans + 1) >> 1;
    }
}
