package com.solution._2490;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public boolean isCircularSentence(String sentence) {
        if (sentence.charAt(0) != sentence.charAt(sentence.length() - 1)) {
            return false;
        }
        String[] ss = sentence.split(" ");
        for (int i = 1; i < ss.length; ++i) {
            if (ss[i].charAt(0) != ss[i - 1].charAt(ss[i - 1].length() - 1)) {
                return false;
            }
        }
        return true;
    }
}
