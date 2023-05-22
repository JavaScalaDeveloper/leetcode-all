# [2516. 每种字符至少取 K 个](https://leetcode.cn/problems/take-k-of-each-character-from-left-and-right)

## 题目描述

<p>给你一个由字符 <code>'a'</code>、<code>'b'</code>、<code>'c'</code> 组成的字符串 <code>s</code> 和一个非负整数 <code>k</code> 。每分钟，你可以选择取走 <code>s</code> <strong>最左侧</strong> 还是 <strong>最右侧</strong> 的那个字符。</p>

<p>你必须取走每种字符 <strong>至少</strong> <code>k</code> 个，返回需要的 <strong>最少</strong> 分钟数；如果无法取到，则返回<em> </em><code>-1</code> 。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "aabaaaacaabc", k = 2
<strong>输出：</strong>8
<strong>解释：</strong>
从 s 的左侧取三个字符，现在共取到两个字符 'a' 、一个字符 'b' 。
从 s 的右侧取五个字符，现在共取到四个字符 'a' 、两个字符 'b' 和两个字符 'c' 。
共需要 3 + 5 = 8 分钟。
可以证明需要的最少分钟数是 8 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "a", k = 1
<strong>输出：</strong>-1
<strong>解释：</strong>无法取到一个字符 'b' 或者 'c'，所以返回 -1 。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 仅由字母 <code>'a'</code>、<code>'b'</code>、<code>'c'</code> 组成</li>
	<li><code>0 &lt;= k &lt;= s.length</code></li>
</ul>

## 解法

**方法一：滑动窗口**

我们先用哈希表或者一个长度为3的数组 `cnt` 统计字符串s中每个字符的个数，如果有字符的个数小于k个，则无法取到，提前返回-1。

题目要我们在字符串左侧以及右侧取走字符，最终取到的每种字符的个数都不少于k个。我们不妨反着考虑问题，取走中间某个窗口大小的字符串，使得剩下的两侧字符串中，每种字符的个数都不少于k个。

因此，我们维护一个滑动窗口，用指针j和i分别表示窗口的左右边界，窗口内的字符串是我们要取走的。我们每一次移动右边界i，将对应的字符s[i]加入到窗口中（也即取走一个字符s[i]），此时如果cnt[s[i]]个数小于k，那么我们循环移动左边界j，直到cnt[s[i]]个数不小于k为止。此时的窗口大小为i - j + 1，更新最大窗口。

最终的答案就是字符串s的长度减去最大窗口的大小。

时间复杂度O(n)，空间复杂度O(1)。其中n为字符串s的长度。

### **Java**

```java
class Solution {
    public int takeCharacters(String s, int k) {
        int[] cnt = new int[3];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        if (cnt[0] < k || cnt[1] < k || cnt[2] < k) {
            return -1;
        }
        int ans = 0, j = 0;
        for (int i = 0; i < n; ++i) {
            int c = s.charAt(i) - 'a';
            --cnt[c];
            while (cnt[c] < k) {
                ++cnt[s.charAt(j++) - 'a'];
            }
            ans = Math.max(ans, i - j + 1);
        }
        return n - ans;
    }
}
```
