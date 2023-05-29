package com.solution._0128;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public static void main(String[] args) {
        int[] arr = {100, 4, 200, 1, 3, 2};
        Solution solution = new Solution();
        int res = solution.longestConsecutive(arr);
        System.out.println(res);
    }
    /*
    利用HashSet的无序。唯一的特点，时间复杂度为 O(n)，其中 n 是数组 nums 的长度。时间复杂度取决于哈希表的插入和查询操作，平均情况下为 O(1)。空间复杂度也为 O(n)，用于存储哈希表。
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int x : nums) {
            s.add(x);
        }
        int ans = 0;
        for (int x : nums) {
            //如果该数前面存在数 num-1，则跳过该数；否则说明该数是某个连续序列的第一个数，从该数开始向后遍历，依次查找 num+1 是否在哈希表中，直到遇到不连续的数为止。
            if (!s.contains(x - 1)) {
                int y = x + 1;
                while (s.contains(y)) {
                    ++y;
                }
                ans = Math.max(ans, y - x);
            }
        }
        return ans;
    }
}
