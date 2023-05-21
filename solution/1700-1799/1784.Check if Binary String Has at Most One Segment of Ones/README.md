# [1784. 检查二进制字符串字段](https://leetcode.cn/problems/check-if-binary-string-has-at-most-one-segment-of-ones)

## 题目描述

<p>给你一个二进制字符串 <code>s</code> ，该字符串 <strong>不含前导零</strong> 。</p>

<p>如果 <code>s</code> 包含 <strong>零个或一个由连续的 <code>'1'</code> 组成的字段</strong> ，返回 <code>true</code>​​​ 。否则，返回 <code>false</code> 。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "1001"
<strong>输出：</strong>false
<strong>解释：</strong>由连续若干个&nbsp;<code>'1'</code> 组成的字段数量为 2，返回 false
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "110"
<strong>输出：</strong>true</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s[i]</code>​​​​ 为 <code>'0'</code> 或 <code>'1'</code></li>
	<li><code>s[0]</code> 为 <code>'1'</code></li>
</ul>

## 解法

**方法一：0 后面不能有 1**

注意到字符串s不含前导零，说明s以 '1' 开头。

若字符串s存在 "01" 串，那么s就是形如 "1...01..." 的字符串，此时s出现了至少两个连续的 '1' 片段，不满足题意，返回 `false`。

若字符串s不存在 "01" 串，那么s只能是形如 "1..1000..." 的字符串，此时s只有一个连续的 '1' 片段，满足题意，返回 `true`。

因此，只需要判断字符串s是否存在 "01" 串即可。

时间复杂度O(n)。其中n为字符串s的长度。

### **Java**

```java
class Solution {
    public boolean checkOnesSegment(String s) {
        return !s.contains("01");
    }
}
```
