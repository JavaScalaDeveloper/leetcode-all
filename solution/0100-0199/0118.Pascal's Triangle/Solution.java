package com.solution._0118;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {;
        List<List<Integer>> res = generate(6);
        for (List<Integer> re : res) {
            System.out.println(re);
        }
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < numRows; ++i) {
            List<Integer> t = new ArrayList<>();
            for (int j = 0; j < i + 1; ++j) {
                //上一行的第j列+第j-1列
                int v = j == 0 || j == i ? 1 : ans.get(i - 1).get(j) + ans.get(i - 1).get(j - 1);
                t.add(v);
            }
            ans.add(t);
        }
        return ans;
    }
}
