package com.solution._0060;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        int n = 3, k = 3;
        String res1 = getPermutation(n, k);
        String res2 = getPermutation2(n, k);
        System.out.println(res1);
        System.out.println(res2);
    }

    /*
    可以利用组合数学的方法来求解第 k 个排列。首先可以确定第 k 个排列的第一位数字是多少，因为剩下的数字有 (n-1)! 种排列方式，如果 k <= (n-1)!，说明第一个数字是 1，否则第一个数字是 2，以此类推。
    接下来需要求解剩下的 (n-1) 个数字在所有排列中的相对顺序。由于已经确定了第一个数字，只需要考虑剩下的数字即可。假设有 m 个数字，则它们的全排列共有 m! 种。根据前面的分析，可以确定第二个数字是哪个，假设它是 x，则又将问题转化为求剩下的 m-1 个数字在所有排列中的相对顺序。因此可以依此类推，每次确定一个数字，然后将问题转化为求剩下的数字在所有排列中的相对顺序。
    当剩下的数字只有一个时，显然它只有一种排列方式，即为自己本身。因此可以将 recursion 视为递归地选定下一个数字，并将剩下的数字进行排序。
    时间复杂度为 O(n^2)，主要消耗在计算阶乘和删除列表元素上。空间复杂度为 O(n)。
    本题不能使用dfs来解，因为dfs时间复杂度为 O(n!)，空间复杂度为 O(n!)。由于 n! 的阶乘很快就会爆掉，因此当 n 比较大时，这种方法会超时或者超出内存限制。
     */
    public static String getPermutation2(int n, int k) {
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }
        return getPermutation(nums, k);
    }

    private static String getPermutation(List<Integer> nums, int k) {
        int n = nums.size();
        if (n == 1) {
            return String.valueOf(nums.get(0));
        }

        // 计算 (n-1)!
        int fac = 1;
        for (int i = 1; i < n; i++) {
            fac *= i;
        }

        // 计算第一个数字是多少
        int index = (k - 1) / fac;
        int first = nums.get(index);
        nums.remove(index);

        // 递归求解剩下的数字在所有排列中的相对顺序
        return first + getPermutation(nums, k - index * fac);
    }


    public static String getPermutation(int n, int k) {
        StringBuilder ans = new StringBuilder();
        boolean[] vis = new boolean[n + 1];
        for (int i = 0; i < n; ++i) {
            int fact = 1;
            for (int j = 1; j < n - i; ++j) {
                fact *= j;
            }
            for (int j = 1; j <= n; ++j) {
                if (!vis[j]) {
                    if (k > fact) {
                        k -= fact;
                    } else {
                        ans.append(j);
                        vis[j] = true;
                        break;
                    }
                }
            }
        }
        return ans.toString();
    }
}
