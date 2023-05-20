package com.solution._0012;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public String intToRoman(int num) {
        List<String> cs
            = Arrays.asList("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I");
        List<Integer> vs = Arrays.asList(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1);
        StringBuilder ans = new StringBuilder();
        for (int i = 0, n = cs.size(); i < n; ++i) {
            while (num >= vs.get(i)) {
                num -= vs.get(i);
                ans.append(cs.get(i));
            }
        }
        return ans.toString();
    }
}
