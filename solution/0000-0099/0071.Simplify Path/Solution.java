package com.solution._0071;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public static void main(String[] args) {
        String res = simplifyPath("/a/./b/../../c/");
        System.out.println(res);
    }
    public static String simplifyPath(String path) {
        Deque<String> stk = new ArrayDeque<>();
        for (String s : path.split("/")) {
            if ("".equals(s) || ".".equals(s)) {
                continue;
            }
            if ("..".equals(s)) {
                stk.pollLast();
            } else {
                stk.offerLast(s);
            }
        }
        return "/" + String.join("/", stk);
    }
}
