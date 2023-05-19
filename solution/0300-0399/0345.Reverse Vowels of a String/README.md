# [345. 反转字符串中的元音字母](https://leetcode.cn/problems/reverse-vowels-of-a-string)

[English Version](/solution/0300-0399/0345.Reverse%20Vowels%20of%20a%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> ，仅反转字符串中的所有元音字母，并返回结果字符串。</p>

<p>元音字母包括 <code>'a'</code>、<code>'e'</code>、<code>'i'</code>、<code>'o'</code>、<code>'u'</code>，且可能以大小写两种形式出现不止一次。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "hello"
<strong>输出：</strong>"holle"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "leetcode"
<strong>输出：</strong>"leotcede"</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 3 * 10<sup>5</sup></code></li>
	<li><code>s</code> 由 <strong>可打印的 ASCII</strong> 字符组成</li>
</ul>

## 解法

将字符串转为字符数组（或列表），定义双指针 i、j，分别指向数组（列表）头部和尾部，当 i、j 指向的字符均为元音字母时，进行交换。

依次遍历，当 `i >= j` 时，遍历结束。将字符数组（列表）转为字符串返回即可。

### **Java**

```java
class Solution {
    public String reverseVowels(String s) {
        Set<Character> vowels
            = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        int i = 0, j = s.length() - 1;
        char[] chars = s.toCharArray();
        while (i < j) {
            if (!vowels.contains(chars[i])) {
                ++i;
                continue;
            }
            if (!vowels.contains(chars[j])) {
                --j;
                continue;
            }
            char t = chars[i];
            chars[i] = chars[j];
            chars[j] = t;
            ++i;
            --j;
        }
        return new String(chars);
    }
}
```
