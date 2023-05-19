# [316. 去除重复字母](https://leetcode.cn/problems/remove-duplicate-letters)

[English Version](/solution/0300-0399/0316.Remove%20Duplicate%20Letters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 <strong>返回结果的字典序最小</strong>（要求不能打乱其他字符的相对位置）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong><code>s = "bcabc"</code>
<strong>输出<code>：</code></strong><code>"abc"</code>
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong><code>s = "cbacdcbc"</code>
<strong>输出：</strong><code>"acdb"</code></pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> 由小写英文字母组成</li>
</ul>

<p>&nbsp;</p>

<p><strong>注意：</strong>该题与 1081 <a href="https://leetcode.cn/problems/smallest-subsequence-of-distinct-characters">https://leetcode.cn/problems/smallest-subsequence-of-distinct-characters</a> 相同</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：栈**

我们用一个数组 `last` 记录每个字符最后一次出现的位置，用栈来保存结果字符串，用一个数组 `vis` 或者一个整型变量 `mask` 记录当前字符是否在栈中。

遍历字符串 $s$，对于每个字符 $c$，如果 $c$ 不在栈中，我们就需要判断栈顶元素是否大于 $c$，如果大于 $c$，且栈顶元素在后面还会出现，我们就将栈顶元素弹出，将 $c$ 压入栈中。

最后将栈中元素拼接成字符串作为结果返回。

时间复杂度 $O(n)$。其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->





### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String removeDuplicateLetters(String s) {
        int n = s.length();
        int[] last = new int[26];
        for (int i = 0; i < n; ++i) {
            last[s.charAt(i) - 'a'] = i;
        }
        Deque<Character> stk = new ArrayDeque<>();
        int mask = 0;
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (((mask >> (c - 'a')) & 1) == 1) {
                continue;
            }
            while (!stk.isEmpty() && stk.peek() > c && last[stk.peek() - 'a'] > i) {
                mask ^= 1 << (stk.pop() - 'a');
            }
            stk.push(c);
            mask |= 1 << (c - 'a');
        }
        StringBuilder ans = new StringBuilder();
        for (char c : stk) {
            ans.append(c);
        }
        return ans.reverse().toString();
    }
}
```











### **...**

```

```


