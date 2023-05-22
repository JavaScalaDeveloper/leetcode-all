# [剑指 Offer II 018. 有效的回文](https://leetcode.cn/problems/XltzEq)

## 题目描述



<p>给定一个字符串 <code>s</code> ，验证 <code>s</code>&nbsp;是否是&nbsp;<strong>回文串&nbsp;</strong>，只考虑字母和数字字符，可以忽略字母的大小写。</p>

<p>本题中，将空字符串定义为有效的&nbsp;<strong>回文串&nbsp;</strong>。</p>



<p><strong>示例 1:</strong></p>

<pre>
<strong>输入: </strong>s =<strong> </strong>&quot;A man, a plan, a canal: Panama&quot;
<strong>输出:</strong> true
<strong>解释：</strong>&quot;amanaplanacanalpanama&quot; 是回文串</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> s = &quot;race a car&quot;
<strong>输出:</strong> false
解释：&quot;raceacar&quot; 不是回文串</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li>字符串 <code>s</code> 由 ASCII 字符组成</li>
</ul>



<p><meta charset="UTF-8" />注意：本题与主站 125&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/valid-palindrome/">https://leetcode.cn/problems/valid-palindrome/</a></p>

## 解法

### **Java**

```java
class Solution {
    public boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            }
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            }
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
```

使用 `is_alphabetic()` 与 `is_numeric()` 过滤字符
