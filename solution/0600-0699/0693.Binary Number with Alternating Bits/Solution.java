package com.solution._0693;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public boolean hasAlternatingBits(int n) {
        n ^= (n >> 1);
        return (n & (n + 1)) == 0;
    }
}
