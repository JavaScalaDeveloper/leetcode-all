package com.solution._1374;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public String generateTheString(int n) {
        return (n % 2 == 1) ? "a".repeat(n) : "a".repeat(n - 1) + "b";
    }
}
