package com.solution._0779;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int kthGrammar(int n, int k) {
        return Integer.bitCount(k - 1) & 1;
    }
}
