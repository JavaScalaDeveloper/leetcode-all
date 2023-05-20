package com.solution._1523;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int countOdds(int low, int high) {
        return ((high + 1) >> 1) - (low >> 1);
    }
}
