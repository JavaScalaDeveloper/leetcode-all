# [583. 两个字符串的删除操作](https://leetcode.cn/problems/delete-operation-for-two-strings)

## 题目描述

<p>给定两个单词&nbsp;<code>word1</code>&nbsp;和<meta charset="UTF-8" />&nbsp;<code>word2</code>&nbsp;，返回使得<meta charset="UTF-8" />&nbsp;<code>word1</code>&nbsp;和&nbsp;<meta charset="UTF-8" />&nbsp;<code>word2</code><em>&nbsp;</em><strong>相同</strong>所需的<strong>最小步数</strong>。</p>

<p><strong>每步&nbsp;</strong>可以删除任意一个字符串中的一个字符。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> word1 = "sea", word2 = "eat"
<strong>输出:</strong> 2
<strong>解释:</strong> 第一步将 "sea" 变为 "ea" ，第二步将 "eat "变为 "ea"
</pre>

<p><strong>示例 &nbsp;2:</strong></p>

<pre>
<b>输入：</b>word1 = "leetcode", word2 = "etco"
<b>输出：</b>4
</pre>

<p><strong>提示：</strong></p>
<meta charset="UTF-8" />

<ul>
	<li><code>1 &lt;= word1.length, word2.length &lt;= 500</code></li>
	<li><code>word1</code>&nbsp;和&nbsp;<code>word2</code>&nbsp;只包含小写英文字母</li>
</ul>

## 解法

**方法一：动态规划**

类似[1143. 最长公共子序列](/solution/1100-1199/1143.Longest%20Common%20Subsequence/README.md)。

定义 `dp[i][j]` 表示使得 `word1[0:i-1]` 和 `word1[0:j-1]` 两个字符串相同所需执行的删除操作次数。

时间复杂度：$O(mn)$。

### **Java**

```java
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= n; ++j) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}
```
