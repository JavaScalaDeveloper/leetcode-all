package com.solution._0005;

public class Solution {
    public static void main(String[] args) {
        String s = "babad";
        String res = longestPalindrome(s);
//        System.out.printf(res);
        String res2 = longestPalindrome2(s);
        System.out.printf(res2);
    }

    public static String longestPalindrome2(String s) {
        int len = s.length();
        if (len == 1) return s;
        int left = 0, right = 1;
        int max = 0;
        for (int i = 1; i < len - 1; i++) {
            int l = i - 1, r = i + 1;
            while (s.charAt(l) == s.charAt(r) && l > 0 && r < len - 1) {
                l--;
                r++;
            }
            if (r - l > max) {
                max = r - l;
                left = l;
                right = r;
            }
        }

        return s.substring(left, right - 1);

    }

    public static String longestPalindrome(String s) {
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
