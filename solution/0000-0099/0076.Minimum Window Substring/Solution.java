package com.solution._0076;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public static void main(String[] args) {
        String s = "ADOBECODEBANC", t = "ABC";
        String res = minWindow(s, t);
        System.out.println(res);
    }
    public static String minWindow(String s, String t) {
        int[] need = new int[128];
        int[] window = new int[128];
        int m = s.length(), n = t.length();
        for (int i = 0; i < n; ++i) {
            ++need[t.charAt(i)];
        }
        int cnt = 0, j = 0, k = -1, mi = 1 << 30;
        for (int i = 0; i < m; ++i) {
            ++window[s.charAt(i)];
            if (need[s.charAt(i)] >= window[s.charAt(i)]) {
                ++cnt;
            }
            //匹配到了包含t的所有子字符串
            while (cnt == n) {
                //找到最小的mi
                if (i - j + 1 < mi) {
                    mi = i - j + 1;
                    k = j;
                }
                //窗口往右移动
                if (need[s.charAt(j)] >= window[s.charAt(j)]) {
                    --cnt;
                }
                --window[s.charAt(j++)];
            }
        }
        return k < 0 ? "" : s.substring(k, k + mi);
    }
}
