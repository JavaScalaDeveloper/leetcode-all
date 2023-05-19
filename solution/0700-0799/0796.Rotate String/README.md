# [796. 旋转字符串](https://leetcode.cn/problems/rotate-string)

[English Version](/solution/0700-0799/0796.Rotate%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个字符串, <code>s</code>&nbsp;和&nbsp;<code>goal</code>。如果在若干次旋转操作之后，<code>s</code>&nbsp;能变成&nbsp;<code>goal</code>&nbsp;，那么返回&nbsp;<code>true</code>&nbsp;。</p>

<p><code>s</code>&nbsp;的 <strong>旋转操作</strong> 就是将&nbsp;<code>s</code> 最左边的字符移动到最右边。&nbsp;</p>

<ul>
	<li>例如, 若&nbsp;<code>s = 'abcde'</code>，在旋转一次之后结果就是<code>'bcdea'</code>&nbsp;。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> s = "abcde", goal = "cdeab"
<strong>输出:</strong> true
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> s = "abcde", goal = "abced"
<strong>输出:</strong> false
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length, goal.length &lt;= 100</code></li>
	<li><code>s</code>&nbsp;和&nbsp;<code>goal</code>&nbsp;由小写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->



### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean rotateString(String s, String goal) {
        return s.length() == goal.length() && (s + s).contains(goal);
    }
}
```









### **TypeScript**











### **...**

```

```


