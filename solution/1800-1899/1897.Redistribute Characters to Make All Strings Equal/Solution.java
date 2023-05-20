package com.solution._1897;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public boolean makeEqual(String[] words) {
        int[] counter = new int[26];
        for (String word : words) {
            for (char c : word.toCharArray()) {
                ++counter[c - 'a'];
            }
        }
        int n = words.length;
        for (int i = 0; i < 26; ++i) {
            if (counter[i] % n != 0) {
                return false;
            }
        }
        return true;
    }
}
