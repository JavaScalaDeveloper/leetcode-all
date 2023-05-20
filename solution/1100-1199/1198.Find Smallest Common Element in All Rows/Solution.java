package com.solution._1198;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int smallestCommonElement(int[][] mat) {
        int[] counter = new int[10001];
        for (int[] row : mat) {
            for (int num : row) {
                ++counter[num];
                if (counter[num] == mat.length) {
                    return num;
                }
            }
        }
        return -1;
    }
}
