package com.solution._0049;

import change.tools.listnode.ArrayUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> res = groupAnagrams2(strs);
        System.out.println(res);
    }

    public static List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int len = str.length();
            int[] chars = new int[26];
            for (int i = 0; i < len; i++) {
                chars[str.charAt(i) - 'a']++;
            }
            String key = Arrays.stream(chars)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(","));
//            List<String> valList = map.get(key);
//            if (valList == null) {
//                valList = new ArrayList<>();
//            }
//            valList.add(str);
//            map.put(key, valList);
            map.computeIfAbsent(key, val -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    }

    @Deprecated
    public static List<List<String>> groupAnagrams3(String[] strs) {
        Map<int[], String> map = new HashMap<>();
        for (String str : strs) {
            int len = str.length();
            int[] chars = new int[26];
            for (int i = 0; i < len; i++) {
                chars[str.charAt(i) - 'a']++;
            }
            map.merge(chars, str, String::join);
        }
        /*
        踩坑：此处的size=6
         Java 中的数组不支持作为 Map 的 key，因此创建了一个 int[] 作为 key 的情况下，Java 会默认将其当作 Object 处理，使用对象的 hashcode 和 equals 方法来进行比较。
         所以这个HashMap的size是6。
         */
        map.forEach((key, value) -> System.out.println(ArrayUtils.toList(key) + "->" + value));
        return null;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> d = new HashMap<>();
        for (String s : strs) {
            char[] t = s.toCharArray();
            Arrays.sort(t);
            String k = String.valueOf(t);
            d.computeIfAbsent(k, key -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(d.values());
    }
}
