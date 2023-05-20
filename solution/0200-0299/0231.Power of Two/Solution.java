package com.solution._0231;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
