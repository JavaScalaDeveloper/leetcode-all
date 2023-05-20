package com.solution._2683;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public boolean doesValidArrayExist(int[] derived) {
        int s = 0;
        for (int x : derived) {
            s ^= x;
        }
        return s == 0;
    }
}
