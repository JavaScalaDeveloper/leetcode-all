package com.solution._0453;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int minMoves(int[] nums) {
        return Arrays.stream(nums).sum() - Arrays.stream(nums).min().getAsInt() * nums.length;
    }
}
