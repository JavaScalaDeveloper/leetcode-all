package com.solution._1823;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int findTheWinner(int n, int k) {
        if (n == 1) {
            return 1;
        }
        int ans = (findTheWinner(n - 1, k) + k) % n;
        return ans == 0 ? n : ans;
    }
}
