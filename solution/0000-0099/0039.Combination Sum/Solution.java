package com.solution._0039;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> t = new ArrayList<>();
    private int[] candidates;

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {2, 3, 5};
        int target = 8;
        List<List<Integer>> res = solution.combinationSum(arr, target);
        System.out.println(res);
    }

    public List<List<Integer>> combinationSum(int[] arr, int target) {
        Arrays.sort(arr);
        this.candidates = arr;
        dfs(0, target);
        return ans;
    }

    private void dfs(int i, int s) {
        if (s == 0) {
            ans.add(new ArrayList(t));
            return;
        }
        if (i >= candidates.length || s < candidates[i]) {
            return;
        }
        dfs(i + 1, s);
        t.add(candidates[i]);
//        System.out.println("t before=" + t);
        dfs(i, s - candidates[i]);
        t.remove(t.size() - 1);
//        System.out.println("t after=" + t);
    }
}
