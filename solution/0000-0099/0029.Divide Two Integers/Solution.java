package com.solution._0029;

public class Solution {
    public static void main(String[] args) {
        int res = divide2(7, 3);
        System.out.println(res);
    }

    /*
    位运算
    将除数和被除数转换为二进制数，然后逐位相除，最后将结果转换为十进制数即可。移位除法的思路是通过二进制数的左移操作来模拟除法运算中的右移操作，
    即将被除数逐个减去 2 的幂次，得到商和余数。
     */
    public static int divide2(int dividend, int divisor) {
        // 处理除数为 0 的情况
        if (divisor == 0) {
            throw new ArithmeticException("Division by zero");
        }
        // 处理被除数为最小负整数的情况
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        boolean negative = (dividend ^ divisor) < 0; // 判断正负号
        long t = Math.abs((long) dividend); // 被除数转换为 long 类型
        long d = Math.abs((long) divisor); // 除数转换为 long 类型
        int result = 0;
        for (int i = 31; i >= 0; i--) { // 从高位到低位逐个处理
            if ((t >> i) >= d) { // 找到最大的倍数
                result += 1 << i; // 累加结果
                t -= d << i; // 更新被除数
            }
        }
        return negative ? -result : result; // 返回结果并处理正负号
    }

    public int divide(int a, int b) {
        int sign = 1;
        if ((a < 0) != (b < 0)) {
            sign = -1;
        }
        long x = Math.abs((long) a);
        long y = Math.abs((long) b);
        long tot = 0;
        while (x >= y) {
            int cnt = 0;
            while (x >= (y << (cnt + 1))) {
                cnt++;
            }
            tot += 1L << cnt;
            x -= y << cnt;
        }
        long ans = sign * tot;
        if (ans >= Integer.MIN_VALUE && ans <= Integer.MAX_VALUE) {
            return (int) ans;
        }
        return Integer.MAX_VALUE;
    }
}
