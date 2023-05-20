package com.solution._0551;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public boolean checkRecord(String s) {
        return s.indexOf("A") == s.lastIndexOf("A") && !s.contains("LLL");
    }
}
