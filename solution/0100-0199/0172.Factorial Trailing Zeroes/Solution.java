package com.solution._0172;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int trailingZeroes(int n) {
        int ans = 0;
        while (n > 0) {
            n /= 5;
            ans += n;
        }
        return ans;
    }
}
