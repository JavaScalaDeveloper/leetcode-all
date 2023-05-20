package com.solution._2083;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public long numberOfSubstrings(String s) {
        int[] cnt = new int[26];
        long ans = 0;
        for (int i = 0; i < s.length(); ++i) {
            int j = s.charAt(i) - 'a';
            ++cnt[j];
            ans += cnt[j];
        }
        return ans;
    }
}
