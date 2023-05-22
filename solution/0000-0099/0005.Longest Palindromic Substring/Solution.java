package com.solution._0005;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int mx = 1, start = 0;
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i <= j; ++i) {
                boolean flag = s.charAt(i) == s.charAt(j);
                if (j - i < 2) {
                    dp[i][j] = flag;
                } else {
                    dp[i][j] = dp[i + 1][j - 1] && flag;
                }
                if (dp[i][j] && mx < j - i + 1) {
                    mx = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substring(start, start + mx);
    }
}
