# [115. 不同的子序列](https://leetcode.cn/problems/distinct-subsequences)

## 题目描述

<p>给你两个字符串 <code>s</code><strong> </strong>和 <code>t</code> ，统计并返回在 <code>s</code> 的 <strong>子序列</strong> 中 <code>t</code> 出现的个数。</p>

<p>题目数据保证答案符合 32 位带符号整数范围。</p>

<p><strong>示例&nbsp;1：</strong></p>

<pre>
<strong>输入：</strong>s = "rabbbit", t = "rabbit"<code>
<strong>输出</strong></code><strong>：</strong><code>3
</code><strong>解释：</strong>
如下所示, 有 3 种可以从 s 中得到 <code>"rabbit" 的方案</code>。
<code><strong><u>rabb</u></strong>b<strong><u>it</u></strong></code>
<code><strong><u>ra</u></strong>b<strong><u>bbit</u></strong></code>
<code><strong><u>rab</u></strong>b<strong><u>bit</u></strong></code></pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入：</strong>s = "babgbag", t = "bag"
<code><strong>输出</strong></code><strong>：</strong><code>5
</code><strong>解释：</strong>
如下所示, 有 5 种可以从 s 中得到 <code>"bag" 的方案</code>。 
<code><strong><u>ba</u></strong>b<u><strong>g</strong></u>bag</code>
<code><strong><u>ba</u></strong>bgba<strong><u>g</u></strong></code>
<code><u><strong>b</strong></u>abgb<strong><u>ag</u></strong></code>
<code>ba<u><strong>b</strong></u>gb<u><strong>ag</strong></u></code>
<code>babg<strong><u>bag</u></strong></code>
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length, t.length &lt;= 1000</code></li>
	<li><code>s</code> 和 <code>t</code> 由英文字母组成</li>
</ul>

## 解法

**方法一：动态规划**

定义 `dp[i][j]` 表示 `s[0..i]` 的子序列中 `t[0..j]` 出现的个数。初始时 `dp[i][0]=1`，表示空串是任意字符串的子序列。答案为 `dp[m][n]`。

当 `s[i] == t[j]` 时，`dp[i][j] = dp[i-1][j-1] + dp[i-1][j]`，即 `s[0..i]` 的子序列中 `t[0..j]` 出现的个数等于 `s[0..i-1]` 的子序列中 `t[0..j-1]` 出现的个数加上 `s[0..i-1]` 的子序列中 `t[0..j]` 出现的个数。

当 `s[i] != t[j]` 时，`dp[i][j] = dp[i-1][j]`，即 `s[0..i]` 的子序列中 `t[0..j]` 出现的个数等于 `s[0..i-1]` 的子序列中 `t[0..j]` 出现的个数。

因此，可以得到状态转移方程：


dp[i][j]=
\begin{cases}
dp[i-1][j-1]+dp[i-1][j], & s[i]=t[j] \\
dp[i-1][j], & s[i]\neq t[j]
\end{cases}


时间复杂度O(m× n)，空间复杂度O(m× n)。其中m,n分别是字符串 `s` 和 `t` 的长度。

### **Java**

```java
class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; ++i) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                dp[i][j] += dp[i - 1][j];
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }
}
```
