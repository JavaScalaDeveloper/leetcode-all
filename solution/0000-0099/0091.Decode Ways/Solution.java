package com.solution._0091;

public class Solution {
    public static void main(String[] args) {
        String s = "11106";
        int res = numDecodings(s);
        int res2 = numDecodings2(s);
        System.out.println(res);
        System.out.println(res2);
    }

    public static int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; ++i) {
            //num=0~9
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }
            //num=10~25
            if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + s.charAt(i - 1) - '0') <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

    public static int numDecodings2(String s) {
        int n = s.length();
        int a = 0, b = 1, c = 0;
        for (int i = 1; i <= n; ++i) {
            c = 0;
            if (s.charAt(i - 1) != '0') {
                c += b;
            }
            if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + s.charAt(i - 1) - '0') <= 26) {
                c += a;
            }
            a = b;
            b = c;
        }
        return c;
    }
}
