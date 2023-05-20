package com.solution._2600;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        if (numOnes >= k) {
            return k;
        }
        k -= numOnes;
        if (numZeros >= k) {
            return numOnes;
        }
        k -= numZeros;
        return numOnes - k;
    }
}
