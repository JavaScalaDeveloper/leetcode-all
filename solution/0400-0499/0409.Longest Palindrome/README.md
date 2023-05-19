# [409. 最长回文串](https://leetcode.cn/problems/longest-palindrome)

[English Version](/solution/0400-0499/0409.Longest%20Palindrome/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个包含大写字母和小写字母的字符串<meta charset="UTF-8" />&nbsp;<code>s</code>&nbsp;，返回&nbsp;<em>通过这些字母构造成的 <strong>最长的回文串</strong></em>&nbsp;。</p>

<p>在构造过程中，请注意 <strong>区分大小写</strong> 。比如&nbsp;<code>"Aa"</code>&nbsp;不能当做一个回文字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1: </strong></p>

<pre>
<strong>输入:</strong>s = "abccccdd"
<strong>输出:</strong>7
<strong>解释:</strong>
我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong>s = "a"
<strong>输出:</strong>1
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入:</strong>s = "aaaaaccc"
<strong>输出:</strong>7</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2000</code></li>
	<li><code>s</code>&nbsp;只由小写 <strong>和/或</strong> 大写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：计数**

一个合法的回文字符串，最多存在一个出现奇数次数的字符，其余字符出现次数均为偶数。

因此，我们可以先遍历字符串 $s$，统计每个字符出现的次数，记录在数组或哈希表 $cnt$ 中。

然后，我们遍历 $cnt$，对于每个字符 $c$，如果 $cnt[c]$ 为偶数，则直接将 $cnt[c]$ 累加到答案 $ans$ 中；如果 $cnt[c]$ 为奇数，则将 $cnt[c] - 1$ 累加到 $ans$ 中，如果 $ans$ 为偶数，则将 $ans$ 增加 $1$。

最后，我们返回 $ans$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 为字符串 $s$ 的长度；而 $C$ 为字符集的大小，本题中 $C = 128$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->



### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int longestPalindrome(String s) {
        int[] cnt = new int[128];
        for (int i = 0; i < s.length(); ++i) {
            ++cnt[s.charAt(i)];
        }
        int ans = 0;
        for (int v : cnt) {
            ans += v - (v & 1);
            if (ans % 2 == 0 && v % 2 == 1) {
                ++ans;
            }
        }
        return ans;
    }
}
```









### **TypeScript**









### **...**

```

```


