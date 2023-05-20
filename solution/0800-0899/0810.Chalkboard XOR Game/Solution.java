package com.solution._0810;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public boolean xorGame(int[] nums) {
        return nums.length % 2 == 0 || Arrays.stream(nums).reduce(0, (a, b) -> a ^ b) == 0;
    }
}
