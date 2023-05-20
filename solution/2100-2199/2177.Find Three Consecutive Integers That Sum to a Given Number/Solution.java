package com.solution._2177;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public long[] sumOfThree(long num) {
        if (num % 3 != 0) {
            return new long[] {};
        }
        long x = num / 3;
        return new long[] {x - 1, x, x + 1};
    }
}
