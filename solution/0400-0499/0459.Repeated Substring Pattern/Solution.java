package com.solution._0459;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public boolean repeatedSubstringPattern(String s) {
        String str = s + s;
        return str.substring(1, str.length() - 1).contains(s);
    }
}
