package com.solution._0149;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public static void main(String[] args) {
        int[][] points = {{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}};
        Solution solution = new Solution();
        int res = solution.maxPoints(points);
        System.out.println(res);
    }
    public int maxPoints(int[][] points) {
        int n = points.length;
        int ans = 1;
        for (int i = 0; i < n; ++i) {
            int x1 = points[i][0], y1 = points[i][1];
            Map<String, Integer> cnt = new HashMap<>();
            for (int j = i + 1; j < n; ++j) {
                int x2 = points[j][0], y2 = points[j][1];
                int dx = x2 - x1, dy = y2 - y1;
                int g = gcd(dx, dy);
                String k = (dx / g) + "." + (dy / g);
                cnt.put(k, cnt.getOrDefault(k, 0) + 1);
                ans = Math.max(ans, cnt.get(k) + 1);
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
