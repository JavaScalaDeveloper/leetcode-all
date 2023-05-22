# [1163. 按字典序排在最后的子串](https://leetcode.cn/problems/last-substring-in-lexicographical-order)

## 题目描述

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;，找出它的所有子串并按字典序排列，返回排在最后的那个子串。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "abab"
<strong>输出：</strong>"bab"
<strong>解释：</strong>我们可以找出 7 个子串 ["a", "ab", "aba", "abab", "b", "ba", "bab"]。按字典序排在最后的子串是 "bab"。
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入：</strong>s = "leetcode"
<strong>输出：</strong>"tcode"
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 4 * 10<sup>5</sup></code></li>
	<li><code>s</code> 仅含有小写英文字符。</li>
</ul>

## 解法

**方法一：双指针**

我们注意到，如果一个子串从位置i开始，那么字典序最大的子串一定是s[i,..n-1]，即从位置i开始的最长后缀。因此，我们只需要找出字典序最大的后缀子串即可。

我们使用双指针i和j，其中指针i指向当前字典序最大的子串的起始位置，指针j指向当前考虑的子串的起始位置。另外，用一个变量k记录当前比较到的位置。初始时i = 0,j=1,k=0。

每一次，我们比较s[i+k]和s[j+k]：

如果s[i + k] = s[j + k]，说明s[i,..i+k]和s[j,..j+k]相同，我们将k加1，继续比较s[i+k]和s[j+k]；

如果s[i + k] < s[j + k]，说明s[j,..j+k]的字典序更大。此时，我们更新i = i + k + 1，并将k重置为0。如果此时i ≥ j，那么我们将指针j更新为i + 1，即j = i + 1。这里我们跳过了以s[i,..,i+k]为起始位置的所有后缀子串，因为它们的字典序一定小于对应的s[j,..,j+k]为起始位置的后缀子串。

同理，如果s[i + k] > s[j + k]，说明s[i,..,i+k]的字典序更大。此时，我们更新j = j + k + 1，并将k重置为0。这里我们跳过了以s[j,..,j+k]为起始位置的所有后缀子串，因为它们的字典序一定小于对应的s[i,..,i+k]为起始位置的后缀子串。

最后，我们返回以i为起始位置的后缀子串即可，即s[i,..,n-1]。

时间复杂度O(n)，其中n为字符串s的长度。空间复杂度O(1)。

### **Java**

```java
class Solution {
    public String lastSubstring(String s) {
        int n = s.length();
        int i = 0;
        for (int j = 1, k = 0; j + k < n;) {
            int d = s.charAt(i + k) - s.charAt(j + k);
            if (d == 0) {
                ++k;
            } else if (d < 0) {
                i += k + 1;
                k = 0;
                if (i >= j) {
                    j = i + 1;
                }
            } else {
                j += k + 1;
                k = 0;
            }
        }
        return s.substring(i);
    }
}
```
