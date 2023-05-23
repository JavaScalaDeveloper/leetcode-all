package com.solution._0010;

public class Solution {
    public static void main(String[] args) {
        String s = "aaa", p = "a*";
        boolean res = isMatch(s, p);
        System.out.println(res);
        boolean res2 = isMatch2(s, p);
        System.out.println(res2);
    }

    public static boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (i > 0 && (p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1))) {
                        f[i][j] |= f[i - 1][j];
                    }
                } else if (i > 0 && (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1))) {
                    f[i][j] = f[i - 1][j - 1];
                }
//                System.out.println("i=" + i + ",j=" + j + ",f[i][j]=" + f[i][j]);
            }
        }
        return f[m][n];
    }

    /*
    递归
     */
    public static boolean isMatch2(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty();
        }
        boolean firstMatch = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
        if (p.length() >= 2 && p.charAt(1) == '*') {
            return isMatch2(s, p.substring(2)) || (firstMatch && isMatch2(s.substring(1), p));
        } else {
            return firstMatch && isMatch2(s.substring(1), p.substring(1));
        }
    }

}
