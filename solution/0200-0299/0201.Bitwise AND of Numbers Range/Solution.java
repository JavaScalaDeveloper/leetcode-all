package com.solution._0201;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        while (left < right) {
            right &= (right - 1);
        }
        return right;
    }
}
