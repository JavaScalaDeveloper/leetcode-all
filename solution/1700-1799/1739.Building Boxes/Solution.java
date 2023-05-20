package com.solution._1739;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int minimumBoxes(int n) {
        int s = 0, k = 1;
        while (s + k * (k + 1) / 2 <= n) {
            s += k * (k + 1) / 2;
            ++k;
        }
        --k;
        int ans = k * (k + 1) / 2;
        k = 1;
        while (s < n) {
            ++ans;
            s += k;
            ++k;
        }
        return ans;
    }
}
