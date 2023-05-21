# [340. 至多包含 K 个不同字符的最长子串](https://leetcode.cn/problems/longest-substring-with-at-most-k-distinct-characters)

## 题目描述

<p>给你一个字符串 <code>s</code> 和一个整数 <code>k</code> ，请你找出&nbsp;<strong>至多&nbsp;</strong>包含<em> <code>k</code></em> 个 <strong>不同</strong> 字符的最长子串，并返回该子串的长度。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "eceba", k = 2
<strong>输出：</strong>3
<strong>解释：</strong>满足题目要求的子串是 "ece" ，长度为 3 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "aa", k = 1
<strong>输出：</strong>2
<strong>解释：</strong>满足题目要求的子串是 "aa" ，长度为 2 。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= k &lt;= 50</code></li>
</ul>

## 解法

**方法一：滑动窗口 + 哈希表**

我们可以使用滑动窗口的思想，维护一个滑动窗口，使得窗口内的字符串中不同字符的个数不超过k个。窗口内不同字符个数的统计可以用哈希表 `cnt` 来维护。

我们使用两个指针j和i分别表示滑动窗口的左右边界。我们先移动右边界i，将字符s[i]加入到窗口内，扩大滑动窗口，若此时窗口内不同字符的个数超过k个，则移动左边界j，缩小滑动窗口，直到窗口内不同字符的个数不超过k个。此时我们可以更新答案的最大值，即ans = max(ans, i - j + 1)。

时间复杂度O(n)，空间复杂度O(\min(n, k))。其中n为字符串的长度。

### **Java**

```java
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        Map<Character, Integer> cnt = new HashMap<>();
        int n = s.length();
        int ans = 0, j = 0;
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            cnt.put(c, cnt.getOrDefault(c, 0) + 1);
            while (cnt.size() > k) {
                char t = s.charAt(j);
                cnt.put(t, cnt.getOrDefault(t, 0) - 1);
                if (cnt.get(t) == 0) {
                    cnt.remove(t);
                }
                ++j;
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}
```
