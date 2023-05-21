# [1682. 最长回文子序列 II](https://leetcode.cn/problems/longest-palindromic-subsequence-ii)

## 题目描述

<p>字符串&nbsp;<code>s</code>&nbsp;的某个子序列符合下列条件时，称为“<strong>好的回文子序列</strong>”：</p>

<ul>
	<li>它是&nbsp;<code>s</code>&nbsp;的子序列。</li>
	<li>它是回文序列（反转后与原序列相等）。</li>
	<li>长度为<strong>偶数</strong>。</li>
	<li>除中间的两个字符外，其余任意两个连续字符不相等。</li>
</ul>

<p>例如，若&nbsp;<code>s = "abcabcabb"</code>，则&nbsp;<code>"abba"</code>&nbsp;可称为“好的回文子序列”，而&nbsp;<code>"bcb"</code>&nbsp;（长度不是偶数）和&nbsp;<code>"bbbb"</code>&nbsp;（含有相等的连续字符）不能称为“好的回文子序列”。</p>

<p>给定一个字符串&nbsp;<code>s</code>， 返回<em>&nbsp;</em><code>s</code>&nbsp;的<strong>最长“好的回文子序列”</strong>的<strong>长度</strong>。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> s = "bbabab"
<strong>输出:</strong> 4
<strong>解释:</strong> s 的最长“好的回文子序列”是 "baab"。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> s = "dcbccacdb"
<strong>输出:</strong> 4
<strong>解释:</strong> s 的最长“好的回文子序列”是 "dccd"。
</pre>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 250</code></li>
	<li><code>s</code>&nbsp;包含小写英文字母。</li>
</ul>

## 解法

**方法一：记忆化搜索**

我们设计一个函数dfs(i, j, x)表示字符串s中下标范围[i, j]内，且以字符x结尾的最长“好的回文子序列”的长度。答案为dfs(0, n - 1, 26)。

函数dfs(i, j, x)的计算过程如下：

-   如果i >= j，则dfs(i, j, x) = 0；
-   如果s[i] = s[j]，且s[i] \neq x，那么dfs(i, j, x) = dfs(i + 1, j - 1, s[i]) + 2；
-   如果s[i] \neq s[j]，那么dfs(i, j, x) = max(dfs(i + 1, j, x), dfs(i, j - 1, x))。

过程中，我们可以使用记忆化搜索的方式，避免重复计算。

时间复杂度O(n^2 \times C)。其中n为字符串s的长度，而C为字符集大小。本题中C = 26。

### **Java**

```java
class Solution {
    private int[][][] f;
    private String s;

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        this.s = s;
        f = new int[n][n][27];
        for (var a : f) {
            for (var b : a) {
                Arrays.fill(b, -1);
            }
        }
        return dfs(0, n - 1, 26);
    }

    private int dfs(int i, int j, int x) {
        if (i >= j) {
            return 0;
        }
        if (f[i][j][x] != -1) {
            return f[i][j][x];
        }
        int ans = 0;
        if (s.charAt(i) == s.charAt(j) && s.charAt(i) - 'a' != x) {
            ans = dfs(i + 1, j - 1, s.charAt(i) - 'a') + 2;
        } else {
            ans = Math.max(dfs(i + 1, j, x), dfs(i, j - 1, x));
        }
        f[i][j][x] = ans;
        return ans;
    }
}
```
