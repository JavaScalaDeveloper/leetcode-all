package com.solution._0461;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
}
