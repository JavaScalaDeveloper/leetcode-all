# [面试题 01.06. 字符串压缩](https://leetcode.cn/problems/compress-string-lcci)

[English Version](/lcci/01.06.Compress%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。比如，字符串<code>aabcccccaaa</code>会变为<code>a2b1c5a3</code>。若“压缩”后的字符串没有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。</p>

<p> <strong>示例1:</strong></p>

<pre>
<strong> 输入</strong>："aabcccccaaa"
<strong> 输出</strong>："a2b1c5a3"
</pre>

<p> <strong>示例2:</strong></p>

<pre>
<strong> 输入</strong>："abbccd"
<strong> 输出</strong>："abbccd"
<strong> 解释</strong>："abbccd"压缩后为"a1b2c2d1"，比原字符串长度更长。
</pre>

<p><strong>提示：</strong></p>

<ol>
<li>字符串长度在[0, 50000]范围内。</li>
</ol>

## 解法

双指针遍历字符串求解。

### **Java**

```java
class Solution {
    public String compressString(String S) {
        int n;
        if (S == null || (n = S.length()) < 2) {
            return S;
        }
        int p = 0, q = 1;
        StringBuilder sb = new StringBuilder();
        while (q < n) {
            if (S.charAt(p) != S.charAt(q)) {
                sb.append(S.charAt(p)).append(q - p);
                p = q;
            }
            ++q;
        }
        sb.append(S.charAt(p)).append(q - p);
        String res = sb.toString();
        return res.length() < n ? res : S;
    }
}
```
