package com.solution._1332;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int removePalindromeSub(String s) {
        for (int i = 0, j = s.length() - 1; i < j; ++i, --j) {
            if (s.charAt(i) != s.charAt(j)) {
                return 2;
            }
        }
        return 1;
    }
}
