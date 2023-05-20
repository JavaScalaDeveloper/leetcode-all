package com.solution._0435;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        int t = intervals[0][1], ans = 0;
        for (int i = 1; i < intervals.length; ++i) {
            if (intervals[i][0] >= t) {
                t = intervals[i][1];
            } else {
                ++ans;
            }
        }
        return ans;
    }
}
