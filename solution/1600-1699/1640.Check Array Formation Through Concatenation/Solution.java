package com.solution._1640;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public boolean canFormArray(int[] arr, int[][] pieces) {
        Map<Integer, int[]> d = new HashMap<>();
        for (var p : pieces) {
            d.put(p[0], p);
        }
        for (int i = 0; i < arr.length;) {
            if (!d.containsKey(arr[i])) {
                return false;
            }
            for (int v : d.get(arr[i])) {
                if (arr[i++] != v) {
                    return false;
                }
            }
        }
        return true;
    }
}
