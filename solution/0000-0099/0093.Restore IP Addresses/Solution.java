package com.solution._0093;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<String> ans;

    public static void main(String[] args) {
        String ipStr = "101023";
        Solution solution = new Solution();
        List<String> res = solution.restoreIpAddresses(ipStr);
        List<String> res2 = solution.restoreIpAddresses2(ipStr);
        System.out.println(res);
        System.out.println(res2);
    }

    public List<String> restoreIpAddresses(String s) {
        ans = new ArrayList<>();
        dfs(s, new ArrayList<>());
        return ans;
    }

    private void dfs(String s, List<String> t) {
        if (t.size() == 4) {
            if ("".equals(s)) {
                ans.add(String.join(".", t));
            }
            return;
        }
        for (int i = 1; i < Math.min(4, s.length() + 1); ++i) {
            String c = s.substring(0, i);
            if (check(c)) {
                t.add(c);
                dfs(s.substring(i), t);
                t.remove(t.size() - 1);
            }
        }
    }

    private boolean check(String s) {
        if ("".equals(s)) {
            return false;
        }
        int num = Integer.parseInt(s);
        if (num > 255) {
            return false;
        }
        return s.charAt(0) != '0' || s.length() <= 1;
    }

    public List<String> restoreIpAddresses2(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() < 4 || s.length() > 12) {
            return res;
        }
        int[] segments = new int[4];
        dfs2(s, 0, 0, segments, res);
        return res;
    }

    /*
    从字符串的开头开始逐一尝试在哪些位置添加 "."，以分隔出一个整数，并将该整数加入结果数组中。
    如果已经添加了三个整数且剩下的字符串也可以分隔为一个符合要求的整数，则将该整数也加入结果数组中，形成完整的有效 IP 地址。
    如果已经添加了四个整数，则说明已经构成了一个有效的 IP 地址，将其加入结果列表中。需要注意的是，
    添加的整数必须在 0 到 255 的范围内，且不能有前导 0。因此，在尝试添加整数时需要判断其是否合理。
    时间复杂度为 O(3^4)，即 IP 地址的四个整数都可能有三种分隔方式，空间复杂度为 O(1)。
     */
    private void dfs2(String s, int start, int segmentIndex, int[] segments, List<String> res) {
        if (segmentIndex == 4) {
            if (start == s.length()) {
                res.add(segments[0] + "." + segments[1] + "." + segments[2] + "." + segments[3]);
            }
            return;
        }
        for (int i = start; i < start + 3 && i < s.length(); i++) {
            String segmentString = s.substring(start, i + 1);
            int segment = Integer.parseInt(segmentString);
            if (segment > 255 || (segmentString.length() > 1 && segmentString.charAt(0) == '0')) {
                break;
            }
            segments[segmentIndex] = segment;
            dfs2(s, i + 1, segmentIndex + 1, segments, res);
        }
    }

}
