package com.solution._0625;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int smallestFactorization(int num) {
        if (num < 2) {
            return num;
        }
        long ans = 0, mul = 1;
        for (int i = 9; i >= 2; --i) {
            if (num % i == 0) {
                while (num % i == 0) {
                    num /= i;
                    ans = mul * i + ans;
                    mul *= 10;
                }
            }
        }
        return num < 2 && ans <= Integer.MAX_VALUE ? (int) ans : 0;
    }
}
