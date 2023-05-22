# [1092. 最短公共超序列](https://leetcode.cn/problems/shortest-common-supersequence)

## 题目描述

<p>给你两个字符串&nbsp;<code>str1</code> 和&nbsp;<code>str2</code>，返回同时以&nbsp;<code>str1</code>&nbsp;和&nbsp;<code>str2</code>&nbsp;作为 <strong>子序列</strong> 的最短字符串。如果答案不止一个，则可以返回满足条件的 <strong>任意一个</strong> 答案。</p>

<p>如果从字符串 <code>t</code> 中删除一些字符（也可能不删除），可以得到字符串 <code>s</code> ，那么 <code>s</code> 就是 t 的一个子序列。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>str1 = "abac", str2 = "cab"
<strong>输出：</strong>"cabac"
<strong>解释：</strong>
str1 = "abac" 是 "cabac" 的一个子串，因为我们可以删去 "cabac" 的第一个 "c"得到 "abac"。 
str2 = "cab" 是 "cabac" 的一个子串，因为我们可以删去 "cabac" 末尾的 "ac" 得到 "cab"。
最终我们给出的答案是满足上述属性的最短字符串。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>str1 = "aaaaaaaa", str2 = "aaaaaaaa"
<strong>输出：</strong>"aaaaaaaa"
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= str1.length, str2.length &lt;= 1000</code></li>
	<li><code>str1</code> 和&nbsp;<code>str2</code>&nbsp;都由小写英文字母组成。</li>
</ul>

## 解法

**方法一：动态规划 + 构造**

我们先用动态规划求出两个字符串的最长公共子序列，然后根据最长公共子序列构造出最短公共超序列。

定义f[i][j]表示字符串str1的前i个字符和字符串str2的前j个字符的最长公共子序列的长度。状态转移方程如下：


f[i][j] =
\begin{cases}
0 & i = 0 \text{ or } j = 0 \\
f[i - 1][j - 1] + 1 & str1[i - 1] = str2[j - 1] \\
max(f[i - 1][j], f[i][j - 1]) & str1[i - 1] \neq str2[j - 1]
\end{cases}


接下来我们基于f[i][j]构造出最短公共超序列。

```bash
str1:       a   b   a   c

str2:   c   a   b

ans:    c   a   b   a   c
```

不妨对照着上面的示例字符串，来看看如何构造出最短公共超序列。

我们用双指针i和j分别指向字符串str1和str2的末尾，然后从后往前遍历，每次比较str1[i]和str2[j]的值：

-   如果str1[i] = str2[j]，则将str1[i]或str2[j]中的任意一个字符加入到最答案序列的末尾，然后i和j同时减1；
-   如果str1[i] \neq str2[j]，则将f[i][j]与f[i - 1][j]和f[i][j - 1]中的最大值进行比较：
    -   如果f[i][j] = f[i - 1][j]，则将str1[i]加入到答案序列的末尾，然后i减1；
    -   如果f[i][j] = f[i][j - 1]，则将str2[j]加入到答案序列的末尾，然后j减1。

重复上述操作，直到i = 0或j = 0，然后将剩余的字符串加入到答案序列的末尾即可。

最后我们将答案序列反转，即可得到最终的答案。

时间复杂度O(m× n)，空间复杂度O(m× n)。其中m和n分别是字符串str1和str2的长度。

### **Java**

```java
class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        int m = str1.length(), n = str2.length();
        int[][] f = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                } else {
                    f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                }
            }
        }
        int i = m, j = n;
        StringBuilder ans = new StringBuilder();
        while (i > 0 || j > 0) {
            if (i == 0) {
                ans.append(str2.charAt(--j));
            } else if (j == 0) {
                ans.append(str1.charAt(--i));
            } else {
                if (f[i][j] == f[i - 1][j]) {
                    ans.append(str1.charAt(--i));
                } else if (f[i][j] == f[i][j - 1]) {
                    ans.append(str2.charAt(--j));
                } else {
                    ans.append(str1.charAt(--i));
                    --j;
                }
            }
        }
        return ans.reverse().toString();
    }
}
```
