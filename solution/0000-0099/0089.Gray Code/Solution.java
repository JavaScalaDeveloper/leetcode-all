package com.solution._0089;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        List<Integer> res = grayCode(3);
        System.out.println(res);
    }

    /*
    格雷数
    i=  0	,bit(i ^ (i >> 1))=  0
    i=  1	,bit(i ^ (i >> 1))=  1
    i= 10	,bit(i ^ (i >> 1))= 11
    i= 11	,bit(i ^ (i >> 1))= 10
    i=100	,bit(i ^ (i >> 1))=110
    i=101	,bit(i ^ (i >> 1))=111
    i=110	,bit(i ^ (i >> 1))=101
    i=111	,bit(i ^ (i >> 1))=100
     */
    public static List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < 1 << n; ++i) {
            System.out.println("i="+Integer.toBinaryString(i)+"\t,bit(i ^ (i >> 1))="+Integer.toBinaryString(i ^ (i >> 1)));
            ans.add(i ^ (i >> 1));
        }
        return ans;
    }
}
