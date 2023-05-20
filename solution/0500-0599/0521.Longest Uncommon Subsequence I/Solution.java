package com.solution._0521;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int findLUSlength(String a, String b) {
        return a.equals(b) ? -1 : Math.max(a.length(), b.length());
    }
}
