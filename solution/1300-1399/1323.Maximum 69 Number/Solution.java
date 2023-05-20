package com.solution._1323;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int maximum69Number(int num) {
        return Integer.valueOf(String.valueOf(num).replaceFirst("6", "9"));
    }
}
