# [10. 正则表达式匹配](https://leetcode.cn/problems/regular-expression-matching)

[English Version](/solution/0000-0099/0010.Regular%20Expression%20Matching/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;和一个字符规律&nbsp;<code>p</code>，请你来实现一个支持 <code>'.'</code>&nbsp;和&nbsp;<code>'*'</code>&nbsp;的正则表达式匹配。</p>

<ul>
	<li><code>'.'</code> 匹配任意单个字符</li>
	<li><code>'*'</code> 匹配零个或多个前面的那一个元素</li>
</ul>

<p>所谓匹配，是要涵盖&nbsp;<strong>整个&nbsp;</strong>字符串&nbsp;<code>s</code>的，而不是部分字符串。</p>
&nbsp;

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "aa", p = "a"
<strong>输出：</strong>false
<strong>解释：</strong>"a" 无法匹配 "aa" 整个字符串。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入：</strong>s = "aa", p = "a*"
<strong>输出：</strong>true
<strong>解释：</strong>因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
</pre>

<p><strong>示例&nbsp;3：</strong></p>

<pre>
<strong>输入：</strong>s = "ab", p = ".*"
<strong>输出：</strong>true
<strong>解释：</strong>".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length&nbsp;&lt;= 20</code></li>
	<li><code>1 &lt;= p.length&nbsp;&lt;= 20</code></li>
	<li><code>s</code>&nbsp;只包含从&nbsp;<code>a-z</code>&nbsp;的小写字母。</li>
	<li><code>p</code>&nbsp;只包含从&nbsp;<code>a-z</code>&nbsp;的小写字母，以及字符&nbsp;<code>.</code>&nbsp;和&nbsp;<code>*</code>。</li>
	<li>保证每次出现字符&nbsp;<code>*</code> 时，前面都匹配到有效的字符</li>
</ul>

## 解法

**方法一：记忆化搜索**

我们设计一个函数 $dfs(i, j)$，表示从 $s$ 的第 $i$ 个字符开始，和 $p$ 的第 $j$ 个字符开始是否匹配。那么答案就是 $dfs(0, 0)$。

函数 $dfs(i, j)$ 的计算过程如下：

-   如果 $j$ 已经到达 $p$ 的末尾，那么如果 $i$ 也到达了 $s$ 的末尾，那么匹配成功，否则匹配失败。
-   如果 $j$ 的下一个字符是 `'*'`，我们可以选择匹配 $0$ 个 $s[i]$ 字符，那么就是 $dfs(i, j + 2)$。如果此时 $i \lt m$ 并且 $s[i]$ 和 $p[j]$ 匹配，那么我们可以选择匹配 $1$ 个 $s[i]$ 字符，那么就是 $dfs(i + 1, j)$。
-   如果 $j$ 的下一个字符不是 `'*'`，那么如果 $i \lt m$ 并且 $s[i]$ 和 $p[j]$ 匹配，那么就是 $dfs(i + 1, j + 1)$。否则匹配失败。

过程中，我们可以使用记忆化搜索，避免重复计算。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是 $s$ 和 $p$ 的长度。

**方法二：动态规划**

我们可以将方法一中的记忆化搜索转换为动态规划。

定义 $f[i][j]$ 表示字符串 $s$ 的前 $i$ 个字符和字符串 $p$ 的前 $j$ 个字符是否匹配。那么答案就是 $f[m][n]$。初始化 $f[0][0] = true$，表示空字符串和空正则表达式是匹配的。

与方法一类似，我们可以分情况来讨论。

-   如果 $p[j - 1]$ 是 `'*'`，那么我们可以选择匹配 $0$ 个 $s[i - 1]$ 字符，那么就是 $f[i][j] = f[i][j - 2]$。如果此时 $s[i - 1]$ 和 $p[j - 2]$ 匹配，那么我们可以选择匹配 $1$ 个 $s[i - 1]$ 字符，那么就是 $f[i][j] = f[i][j] \lor f[i - 1][j]$。
-   如果 $p[j - 1]$ 不是 `'*'`，那么如果 $s[i - 1]$ 和 $p[j - 1]$ 匹配，那么就是 $f[i][j] = f[i - 1][j - 1]$。否则匹配失败。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是 $s$ 和 $p$ 的长度。

### **Java**

```java
class Solution {
    private Boolean[][] f;
    private String s;
    private String p;
    private int m;
    private int n;

    public boolean isMatch(String s, String p) {
        m = s.length();
        n = p.length();
        f = new Boolean[m + 1][n + 1];
        this.s = s;
        this.p = p;
        return dfs(0, 0);
    }

    private boolean dfs(int i, int j) {
        if (j >= n) {
            return i == m;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        boolean res = false;
        if (j + 1 < n && p.charAt(j + 1) == '*') {
            res = dfs(i, j + 2) || (i < m && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') && dfs(i + 1, j));
        } else {
            res = i < m && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') && dfs(i + 1, j + 1);
        }
        return f[i][j] = res;
    }
}
```

```java
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (i > 0 && (p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1))) {
                        f[i][j] |= f[i - 1][j];
                    }
                } else if (i > 0
                    && (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1))) {
                    f[i][j] = f[i - 1][j - 1];
                }
            }
        }
        return f[m][n];
    }
}
```
