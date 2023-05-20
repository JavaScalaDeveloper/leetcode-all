package com.solution._2481;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int numberOfCuts(int n) {
        return n > 1 && n % 2 == 1 ? n : n >> 1;
    }
}
