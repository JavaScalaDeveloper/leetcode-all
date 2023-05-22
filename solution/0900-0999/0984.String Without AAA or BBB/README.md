# [984. 不含 AAA 或 BBB 的字符串](https://leetcode.cn/problems/string-without-aaa-or-bbb)

## 题目描述

<p>给定两个整数 <code>a</code>&nbsp;和 <code>b</code>&nbsp;，返回&nbsp;<strong>任意</strong>&nbsp;字符串 <code>s</code>&nbsp;，要求满足：</p>

<ul>
	<li><code>s</code>&nbsp;的长度为 <code>a + b</code>，且正好包含&nbsp;<code>a</code>&nbsp;个 <code>'a'</code>&nbsp;字母与&nbsp;<code>b</code> 个 <code>'b'</code>&nbsp;字母；</li>
	<li>子串&nbsp;<code>'aaa'</code>&nbsp;没有出现在 <code>s</code>&nbsp;中；</li>
	<li>子串&nbsp;<code>'bbb'</code> 没有出现在 <code>s</code>&nbsp;中。</li>
</ul>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>a = 1, b = 2
<strong>输出：</strong>"abb"
<strong>解释：</strong>"abb", "bab" 和 "bba" 都是正确答案。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>a = 4, b = 1
<strong>输出：</strong>"aabaa"</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= a, b&nbsp;&lt;= 100</code></li>
	<li>对于给定的 <code>a</code> 和 <code>b</code>，保证存在满足要求的 <code>s</code>&nbsp;</li>
</ul>
<span style="display:block"><span style="height:0px"><span style="position:absolute">​​​</span></span></span>

## 解法

**方法一：贪心 + 构造**

循环构造字符串，当a和b都大于 `0` 时：

1. 如果a> b，添加字符串 "aab"
1. 如果b> a，添加字符串 "bba"
1. 如果a=b，添加字符串 "ab"

循环结束，若a有剩余，则添加a个字符串 "a"；若b有剩余，则添加b个字符串 "b"。

时间复杂度O(a+b)。

### **Java**

```java
class Solution {
    public String strWithout3a3b(int a, int b) {
        StringBuilder ans = new StringBuilder();
        while (a > 0 && b > 0) {
            if (a > b) {
                ans.append("aab");
                a -= 2;
                b -= 1;
            } else if (a < b) {
                ans.append("bba");
                a -= 1;
                b -= 2;
            } else {
                ans.append("ab");
                --a;
                --b;
            }
        }
        if (a > 0) {
            ans.append("a".repeat(a));
        }
        if (b > 0) {
            ans.append("b".repeat(b));
        }
        return ans.toString();
    }
}
```
