package com.solution._0796;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public boolean rotateString(String s, String goal) {
        return s.length() == goal.length() && (s + s).contains(goal);
    }
}
