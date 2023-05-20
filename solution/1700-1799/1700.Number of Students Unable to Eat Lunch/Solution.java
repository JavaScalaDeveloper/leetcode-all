package com.solution._1700;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int countStudents(int[] students, int[] sandwiches) {
        int[] cnt = new int[2];
        for (int v : students) {
            ++cnt[v];
        }
        for (int v : sandwiches) {
            if (cnt[v]-- == 0) {
                return cnt[v ^ 1];
            }
        }
        return 0;
    }
}
