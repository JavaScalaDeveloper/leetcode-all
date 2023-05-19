# [58. 最后一个单词的长度](https://leetcode.cn/problems/length-of-last-word)

[English Version](/solution/0000-0099/0058.Length%20of%20Last%20Word/README_EN.md)

## 题目描述

<p>给你一个字符串 <code>s</code>，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中 <strong>最后一个</strong> 单词的长度。</p>

<p><strong>单词</strong> 是指仅由字母组成、不包含任何空格字符的最大子字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "Hello World"
<strong>输出：</strong>5
<strong>解释：</strong>最后一个单词是“World”，长度为5。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "   fly me   to   the moon  "
<strong>输出：</strong>4<strong>
解释：</strong>最后一个单词是“moon”，长度为4。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "luffy is still joyboy"
<strong>输出：</strong>6
<strong>解释：</strong>最后一个单词是长度为6的“joyboy”。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> 仅有英文字母和空格 <code>' '</code> 组成</li>
	<li><code>s</code> 中至少存在一个单词</li>
</ul>

## 解法

**方法一：逆向遍历 + 双指针**

我们从字符串 $s$ 末尾开始遍历，找到第一个不为空格的字符，即为最后一个单词的最后一个字符，下标记为 $i$。然后继续向前遍历，找到第一个为空格的字符，即为最后一个单词的第一个字符的前一个字符，记为 $j$。那么最后一个单词的长度即为 $i - j$。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $s$ 长度。空间复杂度 $O(1)$。

### **Java**

```java
class Solution {
    public int lengthOfLastWord(String s) {
        int i = s.length() - 1;
        while (i >= 0 && s.charAt(i) == ' ') {
            --i;
        }
        int j = i;
        while (j >= 0 && s.charAt(j) != ' ') {
            --j;
        }
        return i - j;
    }
}
```
