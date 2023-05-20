package com.solution._1003;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public boolean isValid(String s) {
        if (s.length() % 3 > 0) {
            return false;
        }
        StringBuilder t = new StringBuilder();
        for (char c : s.toCharArray()) {
            t.append(c);
            if (t.length() >= 3 && "abc".equals(t.substring(t.length() - 3))) {
                t.delete(t.length() - 3, t.length());
            }
        }
        return t.isEmpty();
    }
}
