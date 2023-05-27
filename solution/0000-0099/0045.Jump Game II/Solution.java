package com.solution._0045;

public class Solution {
    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 1, 4};
        int res = jump(arr);
        System.out.println(res);
    }

    public static int jump(int[] nums) {
        int ans = 0, mx = 0, last = 0;
        for (int i = 0; i < nums.length - 1; ++i) {
            System.out.println("i+num[i]=" + (i + nums[i]));
            //i表示起点到当前位置的最大距离（前面跳了多少次我不管），nums[i]+i表示从起点到下一次跳跃的最大距离
            mx = Math.max(mx, i + nums[i]);//mx=[2,4,4,4]
            //达到最大距离时，增加一次跳跃次数
            if (last == i) {
                ++ans;
                last = mx;
            }
        }
        return ans;
    }
}
