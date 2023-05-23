package com.solution._0006;
import change.datastructure.*;
import change.tools.listnode.ArrayUtils;

import java.util.*;
public class Solution {
    public static void main(String[] args) {
        String s = "ABCDEFGHIJKLMNOPQRSTU";
        Solution solution = new Solution();
        String result = solution.convert(s, 4);
        System.out.println(result);
        String result2 = convert3(s, 4);
        System.out.println(result2);
    }
    /*
    这个算法的时间复杂度是 O(n)，其中 n 是字符串 s 的长度。该算法使用一个StringBuilder数组来保存每一行的字符，然后通过遍历输入字符串来
    填充字符，同时跟踪当前行和移动方向。
     */
    public static String convert3(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder[] rows = new StringBuilder[numRows];
        int curRow = 0;
        boolean goingDown = false;
        for (char c : s.toCharArray()) {
            if (rows[curRow] == null) {
                rows[curRow] = new StringBuilder();
            }
            rows[curRow].append(c);
            if (curRow == 0 || curRow == numRows - 1) {
//                决定往下或者往上
                goingDown = !goingDown;
            }
//            进入往下或者往上一行
            curRow += goingDown ? 1 : -1;
        }
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row != null ? row.toString() : "");
        }
        return result.toString();
    }

    public String convert2(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder[] g = new StringBuilder[numRows];
        Arrays.setAll(g, k -> new StringBuilder());
        int i = 0, k = -1;
        for (char c : s.toCharArray()) {
            g[i].append(c);
            if (i == 0 || i == numRows - 1) {
                k = -k;
            }
            i += k;
        }
        return String.join("", g);
    }
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder ans = new StringBuilder();
        int group = 2 * numRows - 2;
        for (int i = 1; i <= numRows; i++) {
            int interval = i == numRows ? group : 2 * numRows - 2 * i;
            int idx = i - 1;
            while (idx < s.length()) {
                ans.append(s.charAt(idx));
                idx += interval;
                interval = group - interval;
                if (interval == 0) {
                    interval = group;
                }
            }
        }
        return ans.toString();
    }
}
