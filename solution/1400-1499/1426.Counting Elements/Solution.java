package com.solution._1426;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int countElements(int[] arr) {
        Set<Integer> s = new HashSet<>();
        for (int x : arr) {
            s.add(x);
        }
        int ans = 0;
        for (int x : arr) {
            if (s.contains(x + 1)) {
                ++ans;
            }
        }
        return ans;
    }
}
