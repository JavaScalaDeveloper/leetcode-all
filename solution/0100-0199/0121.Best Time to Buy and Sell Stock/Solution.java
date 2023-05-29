package com.solution._0121;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {7, 1, 5, 3, 6, 4};
        int res = solution.maxProfit(arr);
        System.out.println(res);
    }

    public int maxProfit(int[] prices) {
        int ans = 0, mi = prices[0];
        for (int v : prices) {
            ans = Math.max(ans, v - mi);
            mi = Math.min(mi, v);
        }
        return ans;
    }
}
