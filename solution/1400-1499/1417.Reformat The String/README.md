# [1417. 重新格式化字符串](https://leetcode.cn/problems/reformat-the-string)

## 题目描述

<p>给你一个混合了数字和字母的字符串 <code>s</code>，其中的字母均为小写英文字母。</p>

<p>请你将该字符串重新格式化，使得任意两个相邻字符的类型都不同。也就是说，字母后面应该跟着数字，而数字后面应该跟着字母。</p>

<p>请你返回 <strong>重新格式化后</strong> 的字符串；如果无法按要求重新格式化，则返回一个 <strong>空字符串</strong> 。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = &quot;a0b1c2&quot;
<strong>输出：</strong>&quot;0a1b2c&quot;
<strong>解释：</strong>&quot;0a1b2c&quot; 中任意两个相邻字符的类型都不同。 &quot;a0b1c2&quot;, &quot;0a1b2c&quot;, &quot;0c2a1b&quot; 也是满足题目要求的答案。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = &quot;leetcode&quot;
<strong>输出：</strong>&quot;&quot;
<strong>解释：</strong>&quot;leetcode&quot; 中只有字母，所以无法满足重新格式化的条件。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s = &quot;1229857369&quot;
<strong>输出：</strong>&quot;&quot;
<strong>解释：</strong>&quot;1229857369&quot; 中只有数字，所以无法满足重新格式化的条件。
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>s = &quot;covid2019&quot;
<strong>输出：</strong>&quot;c2o0v1i9d&quot;
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>s = &quot;ab123&quot;
<strong>输出：</strong>&quot;1a2b3&quot;
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 500</code></li>
	<li><code>s</code> 仅由小写英文字母和/或数字组成。</li>
</ul>

## 解法

**方法一：模拟**

将字符串s中的所有字符分成“数字”、“字母”两类，分别放入a,b两个数组中。

比较a,b两个数组的长度，若a长度小于b，则交换a,b。接着判断两个数组长度差，若超过1，则返回空字符串。

接着同时遍历两个数组，依次添加a,b中对应字符到答案中。遍历结束，若a长度大于b，则添加a的最后一个字符到答案中。

时间复杂度O(n)，空间复杂度O(n)。其中n是字符串s的长度。

### **Java**

```java
class Solution {
    public String reformat(String s) {
        StringBuilder a = new StringBuilder();
        StringBuilder b = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                a.append(c);
            } else {
                b.append(c);
            }
        }
        int m = a.length(), n = b.length();
        if (Math.abs(m - n) > 1) {
            return "";
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < Math.min(m, n); ++i) {
            if (m > n) {
                ans.append(a.charAt(i));
                ans.append(b.charAt(i));
            } else {
                ans.append(b.charAt(i));
                ans.append(a.charAt(i));
            }
        }
        if (m > n) {
            ans.append(a.charAt(m - 1));
        }
        if (m < n) {
            ans.append(b.charAt(n - 1));
        }
        return ans.toString();
    }
}
```
