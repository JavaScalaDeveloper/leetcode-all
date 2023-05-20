package com.solution._2264;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public String largestGoodInteger(String num) {
        for (int i = 9; i >= 0; i--) {
            String ret = String.valueOf(i).repeat(3);
            if (num.contains(ret)) {
                return ret;
            }
        }
        return "";
    }
}
