# [1278. 分割回文串 III](https://leetcode.cn/problems/palindrome-partitioning-iii)

## 题目描述

<p>给你一个由小写字母组成的字符串&nbsp;<code>s</code>，和一个整数&nbsp;<code>k</code>。</p>

<p>请你按下面的要求分割字符串：</p>

<ul>
	<li>首先，你可以将&nbsp;<code>s</code>&nbsp;中的部分字符修改为其他的小写英文字母。</li>
	<li>接着，你需要把&nbsp;<code>s</code>&nbsp;分割成&nbsp;<code>k</code>&nbsp;个非空且不相交的子串，并且每个子串都是回文串。</li>
</ul>

<p>请返回以这种方式分割字符串所需修改的最少字符数。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = &quot;abc&quot;, k = 2
<strong>输出：</strong>1
<strong>解释：</strong>你可以把字符串分割成 &quot;ab&quot; 和 &quot;c&quot;，并修改 &quot;ab&quot; 中的 1 个字符，将它变成回文串。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = &quot;aabbc&quot;, k = 3
<strong>输出：</strong>0
<strong>解释：</strong>你可以把字符串分割成 &quot;aa&quot;、&quot;bb&quot; 和 &quot;c&quot;，它们都是回文串。</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s = &quot;leetcode&quot;, k = 8
<strong>输出：</strong>0
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code>&nbsp;中只含有小写英文字母。</li>
</ul>

## 解法

**方法一：动态规划**

定义dp[i][j]表示将字符串s的前i个字符分割成j个回文串所需要的最少修改次数，我们假定i下标从1开始，答案为dp[n][k]。

对于dp[i][j]，我们可以枚举第j-1个回文串的最后一个字符的位置h，那么dp[i][j]就等于dp[h][j-1] + g[h][i-1]的较小值，其中g[h][i-1]表示将字符串s[h..i-1]变成回文串所需要的最少修改次数（这一部分我们可以通过预处理得到，时间复杂度O(n^2)。

时间复杂度O(n^2\times k)。其中n为字符串s的长度。

### **Java**

```java
class Solution {
    public int palindromePartition(String s, int k) {
        int n = s.length();
        int[][] g = new int[n][n];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i; j < n; ++j) {
                g[i][j] = s.charAt(i) != s.charAt(j) ? 1 : 0;
                if (i + 1 < j) {
                    g[i][j] += g[i + 1][j - 1];
                }
            }
        }
        int[][] f = new int[n + 1][k + 1];
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= Math.min(i, k); ++j) {
                if (j == 1) {
                    f[i][j] = g[0][i - 1];
                } else {
                    f[i][j] = 10000;
                    for (int h = j - 1; h < i; ++h) {
                        f[i][j] = Math.min(f[i][j], f[h][j - 1] + g[h][i - 1]);
                    }
                }
            }
        }
        return f[n][k];
    }
}
```
