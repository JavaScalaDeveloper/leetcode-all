package com.solution._2037;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int minMovesToSeat(int[] seats, int[] students) {
        Arrays.sort(seats);
        Arrays.sort(students);
        int ans = 0;
        for (int i = 0; i < seats.length; ++i) {
            ans += Math.abs(seats[i] - students[i]);
        }
        return ans;
    }
}
