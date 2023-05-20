package com.solution._0441;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int arrangeCoins(int n) {
        return (int) (Math.sqrt(2) * Math.sqrt(n + 0.125) - 0.5);
    }
}
