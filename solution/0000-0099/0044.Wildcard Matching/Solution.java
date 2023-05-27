package com.solution._0044;

public class Solution {
    public static void main(String[] args) {
        String s1 = "abc", s2 = "a*";
        boolean res = isMatch(s1, s2);
        System.out.println(res);
    }

    public static boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;//空字符串之间的匹配是成功的
        for (int i = 1; i <= n; ++i) {
            //可以匹配任意字符序列，包括空字符序列。
            if (p.charAt(i - 1) == '*') {//
                dp[0][i] = dp[0][i - 1];
            }
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                /*
                当前字符匹配成功，则 dp[i][j] = dp[i-1][j-1]；否则，若 p 的当前字符为 '*'，则 dp[i][j] = dp[i-1][j] || dp[i][j-1]。
                其中 dp[i-1][j] 表示 '' 匹配了 s 的第 i 个字符，dp[i][j-1] 表示 '*' 匹配了空字符串。
                 */
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        return dp[m][n];
    }
}
