package com.solution._0043;

import change.tools.listnode.ArrayUtils;

public class Solution {
    public static void main(String[] args) {
        String s1 = "99", s2 = "76";
        String result = multiply(s1, s2);
        System.out.println(result);
    }

    /*
    99*76
     */
    public static String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        int m = num1.length(), n = num2.length();
        int[] arr = new int[m + n];
//        计算每一位，得[0, 63, 117, 54]
        for (int i = m - 1; i >= 0; --i) {
            int a = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; --j) {
                int b = num2.charAt(j) - '0';
                arr[i + j + 1] += a * b;
            }
        }
        /*
        计算进位，以上公式等于
           54
         117
         63
       + 0
---------------
         7524
        返回数组为： [7, 5, 2, 4]
         */
        for (int i = arr.length - 1; i > 0; --i) {
            arr[i - 1] += arr[i] / 10;
            arr[i] %= 10;
        }

        int i = arr[0] == 0 ? 1 : 0;
        StringBuilder ans = new StringBuilder();
        for (; i < arr.length; ++i) {
            ans.append(arr[i]);
        }
        return ans.toString();
    }
}
