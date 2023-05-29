public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {3, 3, 5, 0, 0, 3, 1, 4};
        int res = solution.maxProfit(arr);
        System.out.println(res);
    }
    public int maxProfit(int[] prices) {
        int f1 = -prices[0], f2 = 0, f3 = -prices[0], f4 = 0;
        for (int i = 1; i < prices.length; ++i) {
            f1 = Math.max(f1, -prices[i]);
            f2 = Math.max(f2, f1 + prices[i]);
            f3 = Math.max(f3, f2 - prices[i]);
            f4 = Math.max(f4, f3 + prices[i]);
        }
        return f4;
    }
}
