package com.solution._1287;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int findSpecialInteger(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            if (arr[i] == arr[i + (n >> 2)]) {
                return arr[i];
            }
        }
        return 0;
    }
}
