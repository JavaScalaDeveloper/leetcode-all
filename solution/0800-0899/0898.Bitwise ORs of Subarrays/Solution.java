package com.solution._0898;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int subarrayBitwiseORs(int[] arr) {
        Set<Integer> s = new HashSet<>();
        int prev = 0;
        for (int i = 0; i < arr.length; ++i) {
            prev |= arr[i];
            int curr = 0;
            for (int j = i; j >= 0; --j) {
                curr |= arr[j];
                s.add(curr);
                if (curr == prev) {
                    break;
                }
            }
        }
        return s.size();
    }
}
