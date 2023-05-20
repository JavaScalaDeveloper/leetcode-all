package com.solution._1833;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int n = costs.length;
        for (int i = 0; i < n; ++i) {
            if (coins < costs[i]) {
                return i;
            }
            coins -= costs[i];
        }
        return n;
    }
}
