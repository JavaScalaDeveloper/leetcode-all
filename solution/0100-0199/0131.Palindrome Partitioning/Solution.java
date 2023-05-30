package com.solution._0131;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    private boolean[][] dp;
    private List<List<String>> ans;
    private int n;

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "aab";
        List<List<String>> res = solution.partition(s);
        System.out.println(res);
    }

    public List<List<String>> partition(String s) {
        ans = new ArrayList<>();
        n = s.length();
        dp = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], true);
        }
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
            }
        }
        dfs(s, 0, new ArrayList<>());
        return ans;
    }

    private void dfs(String s, int i, List<String> t) {
        if (i == n) {
            ans.add(new ArrayList<>(t));
            return;
        }
        for (int j = i; j < n; ++j) {
            if (dp[i][j]) {
                t.add(s.substring(i, j + 1));
                dfs(s, j + 1, t);
                t.remove(t.size() - 1);
            }
        }
    }

    /*
    回溯算法
    具体来说，我们可以枚举每个可能的分割位置，判断从该位置到当前字符串结尾的子串是否为回文串，如果是，则将其加入路径中，并继续搜索剩下的部分。如果搜索到字符串结尾，则将当前路径加入结果集。
     */
    public List<List<String>> partition2(String s) {
        List<List<String>> result = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return result;
        }
        List<String> path = new ArrayList<>();
        backtrack2(s, 0, path, result);
        return result;
    }

    private void backtrack2(String s, int start, List<String> path, List<List<String>> result) {
        if (start == s.length()) {  // 到达字符串结尾
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (isPalindrome(s, start, i)) {  // 从start到i的子串是回文串
                path.add(s.substring(start, i + 1));
                backtrack2(s, i + 1, path, result);
                path.remove(path.size() - 1);  // 回溯
            }
        }
    }

    private boolean isPalindrome(String s, int start, int end) {  // 判断子串是否为回文串
        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }
        return true;
    }

}
