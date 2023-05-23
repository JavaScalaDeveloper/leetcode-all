package com.solution._0007;

public class Solution {
    public static void main(String[] args) {
        int res = reverse2(123);
        System.out.println(res);
    }

    public static int reverse2(int x) {
        int res = 0;
        while (x != 0) {
            //res=0,x=123
            //res=3,x=12
            //res=32,x=1
            res = res * 10 + x % 10;
            x /= 10;
        }
        return res;
    }

    public int reverse(int x) {
        int ans = 0;
        for (; x != 0; x /= 10) {
            if (ans < Integer.MIN_VALUE / 10 || ans > Integer.MAX_VALUE / 10) {
                return 0;
            }
            ans = ans * 10 + x % 10;
        }
        return ans;
    }
}
