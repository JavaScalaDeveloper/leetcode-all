package com.solution._1016;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public boolean queryString(String s, int n) {
        if (n > 1023) {
            return false;
        }
        for (int i = n; i > n / 2; i--) {
            if (!s.contains(Integer.toBinaryString(i))) {
                return false;
            }
        }
        return true;
    }
}
