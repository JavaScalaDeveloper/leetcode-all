package com.solution._0003;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        String s = "pwwkew";
        int length = lengthOfLongestSubstring2(s);
        System.out.println(length);
    }

    /*
    在遍历字符串时，对于每个字符，判断它是否已经在哈希表中出现过。如果出现过，则更新无重复字符子串的起始位置为该字符上一个出现位置的下一个
    位置（因为从该位置到当前位置的字符肯定是重复的），否则直接插入到哈希表中，并更新当前子串的长度。这种实现方式比滑动窗口更为简单，但时间
    复杂度和空间复杂度都相同为 O(n)，只是代码稍微简洁一些。
     */
    public static int lengthOfLongestSubstring2(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0, start = 0; end < n; end++) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                // 如果当前字符已经在map中出现过，则更新起始位置start到该字符上一个出现位置的下一个位置
                start = Math.max(map.get(c) + 1, start);
            }
            // 更新当前字符的最近一次出现位置
            map.put(c, end);
            ans = Math.max(ans, end - start + 1);
        }
        return ans;
    }


    /*
    该算法采用滑动窗口的思想，维护一个当前无重复字符的子串。具体实现中，使用两个指针 left 和 right 分别表示当前子串的左右边界，使用
    集合 set 存储当前子串内出现过的字符。在遍历字符串时，如果当前字符已经在集合中出现过，则将左边界向右移动，并从集合中删除对应字符，
    直到当前子串不再包含重复字符。同时，使用 maxLen 保存出现过的最长无重复字符子串的长度，并在遍历过程中不断更新。
     */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> ss = new HashSet<>();
        int i = 0, ans = 0;
        for (int j = 0; j < s.length(); ++j) {
            char c = s.charAt(j);
            while (ss.contains(c)) {
                ss.remove(s.charAt(i++));
            }
            ss.add(c);
            ans = Math.max(ans, j - i + 1);
        }
        return ans;
    }
}
