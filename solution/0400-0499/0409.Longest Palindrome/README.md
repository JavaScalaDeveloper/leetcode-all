# [409. 最长回文串](https://leetcode.cn/problems/longest-palindrome)

## 题目描述

<p>给定一个包含大写字母和小写字母的字符串<meta charset="UTF-8" />&nbsp;<code>s</code>&nbsp;，返回&nbsp;<em>通过这些字母构造成的 <strong>最长的回文串</strong></em>&nbsp;。</p>

<p>在构造过程中，请注意 <strong>区分大小写</strong> 。比如&nbsp;<code>"Aa"</code>&nbsp;不能当做一个回文字符串。</p>

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

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2000</code></li>
	<li><code>s</code>&nbsp;只由小写 <strong>和/或</strong> 大写英文字母组成</li>
</ul>

## 解法

**方法一：计数**

一个合法的回文字符串，最多存在一个出现奇数次数的字符，其余字符出现次数均为偶数。

因此，我们可以先遍历字符串s，统计每个字符出现的次数，记录在数组或哈希表cnt中。

然后，我们遍历cnt，对于每个字符c，如果cnt[c]为偶数，则直接将cnt[c]累加到答案ans中；如果cnt[c]为奇数，则将cnt[c] - 1累加到ans中，如果ans为偶数，则将ans增加1。

最后，我们返回ans即可。

时间复杂度O(n)，空间复杂度O(C)。其中n为字符串s的长度；而C为字符集的大小，本题中C = 128。

### **Java**

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
