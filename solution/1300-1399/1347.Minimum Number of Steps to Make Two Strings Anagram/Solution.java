package com.solution._1347;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int minSteps(String s, String t) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        int ans = 0;
        for (int i = 0; i < t.length(); ++i) {
            if (--cnt[t.charAt(i) - 'a'] < 0) {
                ++ans;
            }
        }
        return ans;
    }
}
