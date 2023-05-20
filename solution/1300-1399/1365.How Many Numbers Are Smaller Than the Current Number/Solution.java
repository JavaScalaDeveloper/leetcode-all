package com.solution._1365;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] cnt = new int[102];
        for (int x : nums) {
            ++cnt[x + 1];
        }
        for (int i = 1; i < cnt.length; ++i) {
            cnt[i] += cnt[i - 1];
        }
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = cnt[nums[i]];
        }
        return ans;
    }
}
