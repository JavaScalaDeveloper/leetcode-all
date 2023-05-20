package com.solution._0948;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public int bagOfTokensScore(int[] tokens, int power) {
        Arrays.sort(tokens);
        int i = 0, j = tokens.length - 1;
        int ans = 0, t = 0;
        while (i <= j) {
            if (power >= tokens[i]) {
                power -= tokens[i++];
                ++t;
                ans = Math.max(ans, t);
            } else if (t > 0) {
                power += tokens[j--];
                --t;
            } else {
                break;
            }
        }
        return ans;
    }
}
