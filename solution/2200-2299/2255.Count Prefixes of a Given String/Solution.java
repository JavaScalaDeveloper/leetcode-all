package com.solution._2255;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int countPrefixes(String[] words, String s) {
        int ans = 0;
        for (String word : words) {
            if (word.equals(s.substring(0, Math.min(s.length(), word.length())))) {
                ++ans;
            }
        }
        return ans;
    }
}
