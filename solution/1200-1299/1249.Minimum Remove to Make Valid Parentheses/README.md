# [1249. 移除无效的括号](https://leetcode.cn/problems/minimum-remove-to-make-valid-parentheses)

[English Version](/solution/1200-1299/1249.Minimum%20Remove%20to%20Make%20Valid%20Parentheses/README_EN.md)

## 题目描述

<p>给你一个由 <code>'('</code>、<code>')'</code> 和小写字母组成的字符串 <code>s</code>。</p>

<p>你需要从字符串中删除最少数目的 <code>'('</code> 或者 <code>')'</code>&nbsp;（可以删除任意位置的括号)，使得剩下的「括号字符串」有效。</p>

<p>请返回任意一个合法字符串。</p>

<p>有效「括号字符串」应当符合以下&nbsp;<strong>任意一条&nbsp;</strong>要求：</p>

<ul>
	<li>空字符串或只包含小写字母的字符串</li>
	<li>可以被写作&nbsp;<code>AB</code>（<code>A</code>&nbsp;连接&nbsp;<code>B</code>）的字符串，其中&nbsp;<code>A</code>&nbsp;和&nbsp;<code>B</code>&nbsp;都是有效「括号字符串」</li>
	<li>可以被写作&nbsp;<code>(A)</code>&nbsp;的字符串，其中&nbsp;<code>A</code>&nbsp;是一个有效的「括号字符串」</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "lee(t(c)o)de)"
<strong>输出：</strong>"lee(t(c)o)de"
<strong>解释：</strong>"lee(t(co)de)" , "lee(t(c)ode)" 也是一个可行答案。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "a)b(c)d"
<strong>输出：</strong>"ab(c)d"
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "))(("
<strong>输出：</strong>""
<strong>解释：</strong>空字符串也是有效的
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code>&nbsp;可能是&nbsp;<code>'('</code>、<code>')'</code>&nbsp;或英文小写字母</li>
</ul>

## 解法

**方法一：两遍扫描**

先从左向右扫描，将多余的右括号删除，再从右向左扫描，将多余的左括号删除。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。

相似题目：

-   [678. 有效的括号字符串](/solution/0600-0699/0678.Valid%20Parenthesis%20String/README.md)
-   [2116. 判断一个括号字符串是否有效](/solution/2100-2199/2116.Check%20if%20a%20Parentheses%20String%20Can%20Be%20Valid/README.md)

### **Java**

```java
class Solution {
    public String minRemoveToMakeValid(String s) {
        Deque<Character> stk = new ArrayDeque<>();
        int x = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == ')' && x == 0) {
                continue;
            }
            if (c == '(') {
                ++x;
            } else if (c == ')') {
                --x;
            }
            stk.push(c);
        }
        StringBuilder ans = new StringBuilder();
        x = 0;
        while (!stk.isEmpty()) {
            char c = stk.pop();
            if (c == '(' && x == 0) {
                continue;
            }
            if (c == ')') {
                ++x;
            } else if (c == '(') {
                --x;
            }
            ans.append(c);
        }
        return ans.reverse().toString();
    }
}
```
