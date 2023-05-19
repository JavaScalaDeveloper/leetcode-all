# [564. 寻找最近的回文数](https://leetcode.cn/problems/find-the-closest-palindrome)

[English Version](/solution/0500-0599/0564.Find%20the%20Closest%20Palindrome/README_EN.md)

## 题目描述

<p>给定一个表示整数的字符串&nbsp;<code>n</code> ，返回与它最近的回文整数（不包括自身）。如果不止一个，返回较小的那个。</p>

<p>“最近的”定义为两个整数<strong>差的绝对值</strong>最小。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> n = "123"
<strong>输出:</strong> "121"
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> n = "1"
<strong>输出:</strong> "0"
<strong>解释:</strong> 0 和 2是最近的回文，但我们返回最小的，也就是 0。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= n.length &lt;= 18</code></li>
	<li><code>n</code>&nbsp;只由数字组成</li>
	<li><code>n</code>&nbsp;不含前导 0</li>
	<li><code>n</code>&nbsp;代表在&nbsp;<code>[1, 10<sup>18</sup>&nbsp;- 1]</code> 范围内的整数</li>
</ul>

## 解法

-   用原数的前半部分替换后半部分得到的回文整数。
-   用原数的前半部分加一后的结果替换后半部分得到的回文整数。
-   用原数的前半部分减一后的结果替换后半部分得到的回文整数。
-   为防止位数变化导致构造的回文整数错误，因此直接构造 999999…999 和 100…001 作为备选答案。

求以上数字中，最接近原数且不等于原数的最小数字。

### **Java**

```java
class Solution {
    public String nearestPalindromic(String n) {
        long x = Long.parseLong(n);
        long ans = -1;
        for (long t : get(n)) {
            if (ans == -1 || Math.abs(t - x) < Math.abs(ans - x)
                || (Math.abs(t - x) == Math.abs(ans - x) && t < ans)) {
                ans = t;
            }
        }
        return Long.toString(ans);
    }

    private Set<Long> get(String n) {
        int l = n.length();
        Set<Long> res = new HashSet<>();
        res.add((long) Math.pow(10, l - 1) - 1);
        res.add((long) Math.pow(10, l) + 1);
        long left = Long.parseLong(n.substring(0, (l + 1) / 2));
        for (long i = left - 1; i <= left + 1; ++i) {
            StringBuilder sb = new StringBuilder();
            sb.append(i);
            sb.append(new StringBuilder(i + "").reverse().substring(l & 1));
            res.add(Long.parseLong(sb.toString()));
        }
        res.remove(Long.parseLong(n));
        return res;
    }
}
```
