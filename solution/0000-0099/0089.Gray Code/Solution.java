package com.solution._0089;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < 1 << n; ++i) {
            ans.add(i ^ (i >> 1));
        }
        return ans;
    }
}
