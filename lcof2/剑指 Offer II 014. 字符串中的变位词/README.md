# [剑指 Offer II 014. 字符串中的变位词](https://leetcode.cn/problems/MPnaiL)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个字符串&nbsp;<code>s1</code>&nbsp;和&nbsp;<code>s2</code>，写一个函数来判断 <code>s2</code> 是否包含 <code>s1</code><strong>&nbsp;</strong>的某个变位词。</p>

<p>换句话说，第一个字符串的排列之一是第二个字符串的 <strong>子串</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入: </strong>s1 = &quot;ab&quot; s2 = &quot;eidbaooo&quot;
<strong>输出: </strong>True
<strong>解释:</strong> s2 包含 s1 的排列之一 (&quot;ba&quot;).
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入: </strong>s1= &quot;ab&quot; s2 = &quot;eidboaoo&quot;
<strong>输出:</strong> False
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s1.length, s2.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s1</code> 和 <code>s2</code> 仅包含小写字母</li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 567&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/permutation-in-string/">https://leetcode.cn/problems/permutation-in-string/</a></p>

## 解法

维护一个长度固定的窗口向前滑动

### **Java**

```java
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length();
        if (n1 > n2) {
            return false;
        }
        int[] window = new int[26];
        for (int i = 0; i < n1; i++) {
            window[s1.charAt(i) - 'a']++;
            window[s2.charAt(i) - 'a']--;
        }
        if (check(window)) {
            return true;
        }
        for (int i = n1; i < n2; i++) {
            window[s2.charAt(i) - 'a']--;
            window[s2.charAt(i - n1) - 'a']++;
            if (check(window)) {
                return true;
            }
        }
        return false;
    }

    private boolean check(int[] window) {
        return Arrays.stream(window).allMatch(cnt -> cnt == 0);
    }
}
```
