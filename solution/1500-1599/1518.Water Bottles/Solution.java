package com.solution._1518;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int ans = numBottles;
        while (numBottles >= numExchange) {
            numBottles -= (numExchange - 1);
            ++ans;
        }
        return ans;
    }
}
