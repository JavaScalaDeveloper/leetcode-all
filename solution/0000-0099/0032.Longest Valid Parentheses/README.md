# [32. 最长有效括号](https://leetcode.cn/problems/longest-valid-parentheses)

## 题目描述

<p>给你一个只包含 <code>'('</code> 和 <code>')'</code> 的字符串，找出最长有效（格式正确且连续）括号子串的长度。</p>



<div class="original__bRMd">
<div>
<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "(()"
<strong>输出：</strong>2
<strong>解释：</strong>最长有效括号子串是 "()"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = ")()())"
<strong>输出：</strong>4
<strong>解释：</strong>最长有效括号子串是 "()()"
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = ""
<strong>输出：</strong>0
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= s.length <= 3 * 10<sup>4</sup></code></li>
	<li><code>s[i]</code> 为 <code>'('</code> 或 <code>')'</code></li>
</ul>
</div>
</div>

## 解法

**方法一：动态规划**

我们定义f[i]表示以s[i-1]结尾的最长有效括号的长度，那么答案就是max(f[i])。

当i \lt 2时，字符串长度小于2，不存在有效括号，因此f[i] = 0。

当i \ge 2时，我们考虑以s[i-1]结尾的最长有效括号的长度f[i]：

-   如果s[i-1]是左括号，那么以s[i-1]结尾的最长有效括号的长度一定为0，因此f[i] = 0。
-   如果s[i-1]是右括号，有以下两种情况：
    -   如果s[i-2]是左括号，那么以s[i-1]结尾的最长有效括号的长度为f[i-2] + 2。
    -   如果s[i-2]是右括号，那么以s[i-1]结尾的最长有效括号的长度为f[i-1] + 2，但是还需要考虑s[i-f[i-1]-2]是否是左括号，如果是左括号，那么以s[i-1]结尾的最长有效括号的长度为f[i-1] + 2 + f[i-f[i-1]-2]。

因此，我们可以得到状态转移方程：


\begin{cases}
f[i] = 0, & \text{if } s[i-1] = '(',\\
f[i] = f[i-2] + 2, & \text{if } s[i-1] = ')' \text{ and } s[i-2] = '(',\\
f[i] = f[i-1] + 2 + f[i-f[i-1]-2], & \text{if } s[i-1] = ')' \text{ and } s[i-2] = ')' \text{ and } s[i-f[i-1]-2] = '(',\\
\end{cases}


最后，我们只需要返回max(f)即可。

时间复杂度O(n)，空间复杂度O(n)。其中n为字符串的长度。

### **Java**

```java
class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length();
        if (n < 2) {
            return 0;
        }
        int[] f = new int[n + 1];
        int ans = 0;
        for (int i = 2; i <= n; ++i) {
            if (s.charAt(i - 1) == ')') {
                if (s.charAt(i - 2) == '(') {
                    f[i] = f[i - 2] + 2;
                } else {
                    int j = i - f[i - 1] - 1;
                    if (j > 0 && s.charAt(j - 1) == '(') {
                        f[i] = f[i - 1] + 2 + f[j - 1];
                    }
                }
                ans = Math.max(ans, f[i]);
            }
        }
        return ans;
    }
}
```
