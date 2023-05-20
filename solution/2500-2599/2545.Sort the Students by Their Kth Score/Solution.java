package com.solution._2545;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int[][] sortTheStudents(int[][] score, int k) {
        Arrays.sort(score, (a, b) -> b[k] - a[k]);
        return score;
    }
}
