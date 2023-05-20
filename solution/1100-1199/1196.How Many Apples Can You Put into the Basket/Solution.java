package com.solution._1196;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int maxNumberOfApples(int[] weight) {
        Arrays.sort(weight);
        int ans = 0, t = 0;
        for (int v : weight) {
            if (t + v > 5000) {
                break;
            }
            t += v;
            ++ans;
        }
        return ans;
    }
}
