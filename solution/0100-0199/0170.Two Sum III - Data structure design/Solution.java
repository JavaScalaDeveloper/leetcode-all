package com.solution._0170;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Solution {
    private Map<Integer, Integer> cnt = new HashMap<>();

    public void add(int number) {
        cnt.merge(number, 1, Integer::sum);
    }

    public boolean find(int value) {
        Set<Map.Entry<Integer, Integer>> entries = cnt.entrySet();
        for (Map.Entry<Integer, Integer> e : cnt.entrySet()) {
            int x = e.getKey(), v = e.getValue();
            int y = value - x;
            if (cnt.containsKey(y)) {
                if (x != y || v > 1) {
                    return true;
                }
            }
        }
        return false;
    }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */
