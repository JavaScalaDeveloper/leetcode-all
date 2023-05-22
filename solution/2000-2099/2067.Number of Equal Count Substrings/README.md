# [2067. 等计数子串的数量](https://leetcode.cn/problems/number-of-equal-count-substrings)

## 题目描述

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的字符串 <code>s</code>，只包含小写英文字母和一个整数 <code>count</code>。如果&nbsp;<code>s</code>&nbsp;的&nbsp;<strong>子串 </strong>中的每种字母在子串中恰好出现 <code>count</code> 次，这个子串就被称为&nbsp;<strong>等计数子串</strong>。</p>

<p>返回<em> <code>s</code> 中&nbsp;<strong>等计数子串&nbsp;</strong>的个数。</em></p>

<p><strong>子串&nbsp;</strong>是字符串中连续的非空字符序列。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> s = "aaabcbbcc", count = 3
<strong>输出:</strong> 3
<strong>解释:</strong>
从下标 0 开始到下标 2 结束的子串是 "aaa"。
字母 “a” 在子串中恰好出现了 3 次。
从下标 3 开始到下标 8 结束的子串是 "bcbbcc"。
字母 “b” 和 “c” 在子串中恰好出现了 3 次。
从下标 0 开始到下标 8 结束的子串是 "aaabcbbcc"。
字母 “a”、“b” 和 “c” 在子串中恰好出现了 3 次。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> s = "abcd", count = 2
<strong>输出:</strong> 0
<strong>解释:</strong>
每种字母在 s 中出现的次数小于 count。
因此，s 中没有子串是等计数子串，返回 0。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> s = "a", count = 5
<strong>输出:</strong> 0
<strong>解释:</strong>
每种字母在 s 中出现的次数小于 count。
因此，s 中没有子串是等计数子串，返回 0。</pre>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= count &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>s</code> 只由小写英文字母组成。</li>
</ul>

## 解法

**方法一：枚举 + 滑动窗口**

我们可以在[1..26]范围内枚举子串的字母种类数x，那么子串长度就是count × x。

接下来，我们将当前子串长度作为窗口的大小，统计窗口大小中有多少种字母的个数为count，记录在y中。如果此时x = y，说明当前窗口中的字母个数都为count，那么就可以将答案加一。

时间复杂度O(n × C)，空间复杂度O(C)。其中n为字符串s的长度，而C为字母的种类数，本题中C = 26。

### **Java**

```java
class Solution {
    public int equalCountSubstrings(String s, int count) {
        int ans = 0;
        int n = s.length();
        for (int x = 1; x < 27 && count * x <= n; ++x) {
            int m = count * x;
            int[] cnt = new int[26];
            int y = 0;
            for (int i = 0; i < n; ++i) {
                int a = s.charAt(i) - 'a';
                ++cnt[a];
                if (cnt[a] == count) {
                    ++y;
                }
                if (cnt[a] == count + 1) {
                    --y;
                }
                int j = i - m;
                if (j >= 0) {
                    int b = s.charAt(j) - 'a';
                    --cnt[b];
                    if (cnt[b] == count) {
                        ++y;
                    }
                    if (cnt[b] == count - 1) {
                        --y;
                    }
                }
                if (x == y) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}
```
