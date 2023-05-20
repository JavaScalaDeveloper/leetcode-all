package com.solution._1812;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public boolean squareIsWhite(String coordinates) {
        return (coordinates.charAt(0) + coordinates.charAt(1)) % 2 == 1;
    }
}
