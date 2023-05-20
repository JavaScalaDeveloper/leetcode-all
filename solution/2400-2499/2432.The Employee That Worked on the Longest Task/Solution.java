package com.solution._2432;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int hardestWorker(int n, int[][] logs) {
        int ans = 0;
        int last = 0, mx = 0;
        for (int[] log : logs) {
            int uid = log[0], t = log[1];
            t -= last;
            if (mx < t || (mx == t && ans > uid)) {
                ans = uid;
                mx = t;
            }
            last += t;
        }
        return ans;
    }
}
