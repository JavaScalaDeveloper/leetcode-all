package com.solution._0344;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public void reverseString(char[] s) {
        for (int i = 0, j = s.length - 1; i < j; ++i, --j) {
            char t = s[i];
            s[i] = s[j];
            s[j] = t;
        }
    }
}
