package com.solution._2194;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public List<String> cellsInRange(String s) {
        List<String> ans = new ArrayList<>();
        for (char i = s.charAt(0); i <= s.charAt(3); ++i) {
            for (char j = s.charAt(1); j <= s.charAt(4); ++j) {
                ans.add(i + "" + j);
            }
        }
        return ans;
    }
}
