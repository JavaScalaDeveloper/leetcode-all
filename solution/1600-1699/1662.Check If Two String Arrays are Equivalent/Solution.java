package com.solution._1662;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        return String.join("", word1).equals(String.join("", word2));
    }
}
