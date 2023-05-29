package com.solution._0120;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        [[2],[3,4],[6,5,7],[4,1,8,3]]
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        List<Integer> list4 = new ArrayList<>();
        list1.add(2);
        list2.add(3);
        list2.add(4);
        list3.add(6);
        list3.add(5);
        list3.add(7);
        list4.add(4);
        list4.add(1);
        list4.add(8);
        list4.add(3);
        List<List<Integer>> lists = new ArrayList<>();
        lists.add(list1);
        lists.add(list2);
        lists.add(list3);
        lists.add(list4);
        int res = solution.minimumTotal(lists);
        int res2 = solution.minimumTotal2(lists);
        System.out.println(res);
        System.out.println(res2);
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n + 1];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = 0; j <= i; ++j) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }

    /*
    自上而下搜索
     */
    public int minimumTotal2(List<List<Integer>> triangle) {
        int n = triangle.size();
        int res = triangle.get(0).get(0), k = 0;
        for (int i = 1; i < n; i++) {
            List<Integer> row = triangle.get(i);
            if (row.get(k) > row.get(k + 1)) {
                res += row.get(k + 1);
                k++;
            } else {
                res += row.get(k);
            }
        }
        return res;
    }
}
