# [266. 回文排列](https://leetcode.cn/problems/palindrome-permutation)

[English Version](/solution/0200-0299/0266.Palindrome%20Permutation/README_EN.md)

## 题目描述

<p>给定一个字符串，判断该字符串中是否可以通过重新排列组合，形成一个回文字符串。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入:</strong> <code>&quot;code&quot;</code>
<strong>输出:</strong> false</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入:</strong> <code>&quot;aab&quot;</code>
<strong>输出:</strong> true</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入:</strong> <code>&quot;carerac&quot;</code>
<strong>输出:</strong> true</pre>

## 解法

**方法一：数组**

创建一个长度为 $26$ 的数组，统计每个字母出现的频率，至多有一个字符出现奇数次数即可。

时间复杂度 $O(n)$，空间复杂度 $O(26)$。其中 $n$ 是字符串的长度。

**方法二：哈希表**

利用哈希表来维护元素。遍历字符串每个字母 $s[i]$，若 $s[i]$ 在哈希表中，则将 $s[i]$ 从哈希表中删除，否则将 $s[i]$ 加入哈希表。

遍历结束，若哈希表中元素个数不超过 $1$，则返回 $true$，否则返回 $false$。

时间复杂度 $O(n)$，空间复杂度 $O(26)$。其中 $n$ 是字符串的长度。

### **Java**

```java
class Solution {
    public boolean canPermutePalindrome(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            ++cnt[c - 'a'];
        }
        int n = 0;
        for (int v : cnt) {
            n += v % 2;
        }
        return n < 2;
    }
}
```
