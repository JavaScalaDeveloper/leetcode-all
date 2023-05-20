package com.solution._1780;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public boolean checkPowersOfThree(int n) {
        while (n > 0) {
            if (n % 3 > 1) {
                return false;
            }
            n /= 3;
        }
        return true;
    }
}
