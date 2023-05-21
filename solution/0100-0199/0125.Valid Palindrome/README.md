# [125. 验证回文串](https://leetcode.cn/problems/valid-palindrome)

## 题目描述

<p>如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 <strong>回文串</strong> 。</p>

<p>字母和数字都属于字母数字字符。</p>

<p>给你一个字符串 <code>s</code>，如果它是 <strong>回文串</strong> ，返回 <code>true</code><em> </em>；否则，返回<em> </em><code>false</code><em> </em>。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> s = "A man, a plan, a canal: Panama"
<strong>输出：</strong>true
<strong>解释：</strong>"amanaplanacanalpanama" 是回文串。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "race a car"
<strong>输出：</strong>false
<strong>解释：</strong>"raceacar" 不是回文串。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = " "
<strong>输出：</strong>true
<strong>解释：</strong>在移除非字母数字字符之后，s 是一个空字符串 "" 。
由于空字符串正着反着读都一样，所以是回文串。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>s</code> 仅由可打印的 ASCII 字符组成</li>
</ul>

## 解法

**方法一：双指针**

我们用双指针i和j分别指向字符串s的两端，接下来循环以下过程，直至i \geq j：

1. 如果s[i]不是字母或数字，指针i右移一位，继续下一次循环；
1. 如果s[j]不是字母或数字，指针j左移一位，继续下一次循环；
1. 如果s[i]和s[j]的小写形式不相等，返回 `false`；
1. 否则，指针i右移一位，指针j左移一位，继续下一次循环。

循环结束，返回 `true`。

时间复杂度O(n)，其中n是字符串s的长度。空间复杂度O(1)。

### **Java**

```java
class Solution {
    public boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (!Character.isLetterOrDigit(s.charAt(i))) {
                ++i;
            } else if (!Character.isLetterOrDigit(s.charAt(j))) {
                --j;
            } else if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            } else {
                ++i;
                --j;
            }
        }
        return true;
    }
}
```
