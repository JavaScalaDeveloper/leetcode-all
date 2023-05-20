package com.solution._2543;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public boolean isReachable(int targetX, int targetY) {
        int x = gcd(targetX, targetY);
        return (x & (x - 1)) == 0;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
