package com.solution._2446;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public boolean haveConflict(String[] event1, String[] event2) {
        return !(event1[0].compareTo(event2[1]) > 0 || event1[1].compareTo(event2[0]) < 0);
    }
}
