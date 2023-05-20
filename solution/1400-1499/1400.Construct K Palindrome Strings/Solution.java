package com.solution._1400;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public boolean canConstruct(String s, int k) {
        int n = s.length();
        if (n < k) {
            return false;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < n; ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        int x = 0;
        for (int v : cnt) {
            x += v & 1;
        }
        return x <= k;
    }
}
