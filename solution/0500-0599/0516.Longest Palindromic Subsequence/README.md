# [516. 最长回文子序列](https://leetcode.cn/problems/longest-palindromic-subsequence)

[English Version](/solution/0500-0599/0516.Longest%20Palindromic%20Subsequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> ，找出其中最长的回文子序列，并返回该序列的长度。</p>

<p>子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "bbbab"
<strong>输出：</strong>4
<strong>解释：</strong>一个可能的最长回文子序列为 "bbbb" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "cbbd"
<strong>输出：</strong>2
<strong>解释：</strong>一个可能的最长回文子序列为 "bb" 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 1000</code></li>
	<li><code>s</code> 仅由小写英文字母组成</li>
</ul>

## 解法

动态规划。

设 `dp[i][j]` 表示字符串 `s[i..j]` 中的最长回文子序列的长度。初始化 `dp[i][i] = 1`(`i∈[0, n-1]`)。

-   对于 `s[i] == s[j]`，`dp[i][j] = dp[i + 1][j - 1] + 2`；
-   对于 `s[i] != s[j]`，`dp[i][j] = max(dp[i + 1][j], dp[i][j - 1])`。

### **Java**

```java
class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; ++i) {
            dp[i][i] = 1;
        }
        for (int j = 1; j < n; ++j) {
            for (int i = j - 1; i >= 0; --i) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
```
