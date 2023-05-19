# [438. 找到字符串中所有字母异位词](https://leetcode.cn/problems/find-all-anagrams-in-a-string)

[English Version](/solution/0400-0499/0438.Find%20All%20Anagrams%20in%20a%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个字符串&nbsp;<code>s</code>&nbsp;和 <code>p</code>，找到&nbsp;<code>s</code><strong>&nbsp;</strong>中所有&nbsp;<code>p</code><strong>&nbsp;</strong>的&nbsp;<strong>异位词&nbsp;</strong>的子串，返回这些子串的起始索引。不考虑答案输出的顺序。</p>

<p><strong>异位词 </strong>指由相同字母重排列形成的字符串（包括相同的字符串）。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入: </strong>s = "cbaebabacd", p = "abc"
<strong>输出: </strong>[0,6]
<strong>解释:</strong>
起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
</pre>

<p><strong>&nbsp;示例 2:</strong></p>

<pre>
<strong>输入: </strong>s = "abab", p = "ab"
<strong>输出: </strong>[0,1,2]
<strong>解释:</strong>
起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length, p.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>s</code>&nbsp;和&nbsp;<code>p</code>&nbsp;仅包含小写字母</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

“双指针 + 滑动窗口”求解。定义滑动窗口的左右两个指针 left、right，right 一步步往右走遍历 s 字符串，当 right 指针遍历到的字符加入 t 后超过 counter 的字符数量时，将滑动窗口左侧字符不断弹出，也就是 left 指针不断右移，直到符合要求为止。

若滑动窗口长度等于字符串 p 的长度时，这时的 s 子字符串就是 p 的异位词。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->



### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

“暴力”求解。利用哈希表 counter 统计字符串 p 中每个字符出现的次数。然后遍历字符串 s，判断子串 `s[i, i + p)` 中每个字符出现的次数是否与 counter 相同。若是，则将当前下标 i 添加到结果列表中。时间复杂度 `O(s.length * p.length)`。

```java
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int[] counter = new int[26];
        for (char c : p.toCharArray()) {
            ++counter[c - 'a'];
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i + p.length() - 1 < s.length(); ++i) {
            int[] t = Arrays.copyOf(counter, counter.length);
            boolean find = true;
            for (int j = i; j < i + p.length(); ++j) {
                if (--t[s.charAt(j) - 'a'] < 0) {
                    find = false;
                    break;
                }
            }
            if (find) {
                ans.add(i);
            }
        }
        return ans;
    }
}
```

“双指针 + 滑动窗口”求解。

```java
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int[] counter = new int[26];
        for (char c : p.toCharArray()) {
            ++counter[c - 'a'];
        }
        List<Integer> ans = new ArrayList<>();
        int left = 0, right = 0;
        int[] t = new int[26];
        while (right < s.length()) {
            int i = s.charAt(right) - 'a';
            ++t[i];
            while (t[i] > counter[i]) {
                --t[s.charAt(left) - 'a'];
                ++left;
            }
            if (right - left + 1 == p.length()) {
                ans.add(left);
            }
            ++right;
        }
        return ans;
    }
}
```

### **TypeScript**















### **...**

```

```


