package com.solution._0077;
import change.datastructure.*;
import java.util.*;
public class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> t = new ArrayList<>();
    private int n;
    private int k;

    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;
        dfs(1);
        return ans;
    }

    private void dfs(int i) {
        if (t.size() == k) {
            ans.add(new ArrayList<>(t));
            return;
        }
        if (i > n) {
            return;
        }
        t.add(i);
        dfs(i + 1);
        t.remove(t.size() - 1);
        dfs(i + 1);
    }
}
