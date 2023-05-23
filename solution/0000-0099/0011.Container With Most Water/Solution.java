package com.solution._0011;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public static void main(String[] args) {

    }
    public static int maxArea2(int[] height) {
        int l=0,r=height.length-1;
        int max=0;
        while (l < r) {
            max = Math.max(Math.min(height[l], height[r]) * (r - l), max);
            l++;
            r--;
        }
        return max;
    }
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1;
        int ans = 0;
        while (i < j) {
            int t = Math.min(height[i], height[j]) * (j - i);
            ans = Math.max(ans, t);
            if (height[i] < height[j]) {
                ++i;
            } else {
                --j;
            }
        }
        return ans;
    }
}
