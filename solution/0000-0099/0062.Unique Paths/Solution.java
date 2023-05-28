package com.solution._0062;

public class Solution {
    public static void main(String[] args) {
        int m = 3, n = 7;
        int res = uniquePaths(m, n);
        System.out.println(res);
    }

    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] + 1;
            }
        }
        return dp[m - 1][n - 1] + 1;
    }
}
