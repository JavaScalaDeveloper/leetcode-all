# [1100. 长度为 K 的无重复字符子串](https://leetcode.cn/problems/find-k-length-substrings-with-no-repeated-characters)

## 题目描述

<p>给你一个字符串&nbsp;<code>S</code>，找出所有长度为&nbsp;<code>K</code>&nbsp;且不含重复字符的子串，请你返回全部满足要求的子串的&nbsp;<strong>数目</strong>。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>S = &quot;havefunonleetcode&quot;, K = 5
<strong>输出：</strong>6
<strong>解释：</strong>
这里有 6 个满足题意的子串，分别是：&#39;havef&#39;,&#39;avefu&#39;,&#39;vefun&#39;,&#39;efuno&#39;,&#39;etcod&#39;,&#39;tcode&#39;。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>S = &quot;home&quot;, K = 5
<strong>输出：</strong>0
<strong>解释：</strong>
注意：K 可能会大于 S 的长度。在这种情况下，就无法找到任何长度为 K 的子串。</pre>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= S.length &lt;= 10^4</code></li>
	<li><code>S</code> 中的所有字符均为小写英文字母</li>
	<li><code>1 &lt;= K &lt;= 10^4</code></li>
</ol>

## 解法

**方法一：双指针 + 计数器**

我们观察发现，字符均为小写字母，也即最多有26种不同的字符。因此，如果k \gt 26或者k \gt n，则无法找到任何长度为k且不含重复字符的子串，直接返回0即可。

接下来，我们用双指针j和i维护一个滑动窗口，其中j是滑动窗口的左端点，i是滑动窗口的右端点，用一个计数器cnt统计滑动窗口中每个字符出现的次数。

遍历字符串s，每次将s[i]加入滑动窗口，即cnt[s[i]]++，如果此时cnt[s[i]] \gt 1或者i - j + 1 \gt k，则循环将s[j]从滑动窗口中移除，即cnt[s[j]]--，并将j右移。如果j右移结束后，窗口大小i - j + 1恰好等于k，则说明滑动窗口中的字符串是一个符合题意的子串，将结果加一。

遍历结束后，即可得到所有符合题意的子串的个数。

时间复杂度O(n)，空间复杂度O(C)。其中n为字符串s的长度；而C为字符集的大小，本题中C = 26。

### **Java**

```java
class Solution {
    public int numKLenSubstrNoRepeats(String s, int k) {
        int n = s.length();
        if (k > n || k > 26) {
            return 0;
        }
        int[] cnt = new int[128];
        int ans = 0;
        for (int i = 0, j = 0; i < n; ++i) {
            ++cnt[s.charAt(i)];
            while (cnt[s.charAt(i)] > 1 || i - j + 1 > k) {
                cnt[s.charAt(j++)]--;
            }
            ans += i - j + 1 == k ? 1 : 0;
        }
        return ans;
    }
}
```
