package com.solution._0017;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        List<String> res = letterCombinations("2345");
        System.out.println(res);
        List<String> res2 = letterCombinations2("2345");
        System.out.println(res2);
    }

    /*
    递归调用 dfs 函数来遍历所有可能的情况。对于每一层递归，我们都会将当前数字对应的所有字母添加到当前组合中，然后继续搜索下一层。
    当到达最后一个数字时，我们将当前组合添加到结果列表中并返回上一层递归。
    该算法的时间复杂度是 O(3^n * 4^m)，其中 n 和 m 分别是数字 7 和 9 的数目。因为每个数字对应的字母数都不超过 4 个，且输入字符
    串中只包含数字 2 到 9，因此在最坏情况下，我们需要遍历的所有情况数是 3^n * 4^m 种，其中 n 和 m 分别是数字 7 和 9 的数目。
     */
    private static final String[] mapping = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    private static final List<String> result = new ArrayList<>();

    public static List<String> letterCombinations2(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        dfs(digits, "");
        return result;
    }

    private static void dfs(String digits, String combination) {
        System.out.println("digits=" + digits + ",combination=" + combination + ",");
        if (digits.length() == 0) {
            result.add(combination);
            return;
        }
        String letters = mapping[digits.charAt(0) - '0'];
        for (int i = 0; i < letters.length(); i++) {
            dfs(digits.substring(1), combination + letters.charAt(i));
        }
    }

    public static List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits.length() == 0) {
            return ans;
        }
        ans.add("");
        String[] d = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        for (char i : digits.toCharArray()) {
            String s = d[i - '2'];
            List<String> t = new ArrayList<>();
            for (String a : ans) {
                for (String b : s.split("")) {
                    t.add(a + b);
                }
            }
            ans = t;
        }
        return ans;
    }
}
