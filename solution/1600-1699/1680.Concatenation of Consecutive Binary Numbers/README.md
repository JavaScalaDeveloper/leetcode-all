# [1680. 连接连续二进制数字](https://leetcode.cn/problems/concatenation-of-consecutive-binary-numbers)

## 题目描述

<p>给你一个整数 <code>n</code> ，请你将 <code>1</code> 到 <code>n</code> 的二进制表示连接起来，并返回连接结果对应的 <strong>十进制</strong> 数字对 <code>10<sup>9</sup> + 7</code> 取余的结果。</p>



<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>n = 1
<b>输出：</b>1
<strong>解释：</strong>二进制的 "1" 对应着十进制的 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>n = 3
<b>输出：</b>27
<strong>解释：</strong>二进制下，1，2 和 3 分别对应 "1" ，"10" 和 "11" 。
将它们依次连接，我们得到 "11011" ，对应着十进制的 27 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>n = 12
<b>输出：</b>505379714
<b>解释：</b>连接结果为 "1101110010111011110001001101010111100" 。
对应的十进制数字为 118505380540 。
对 10<sup>9</sup> + 7 取余后，结果为 505379714 。
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

**方法一：位运算**

观察数字的连接规律，我们可以发现，当连接到第i个数时，实际上是将前i-1个数连接而成的结果ans往左移动一定的位数，然后再加上i这个数，移动的位数shift是i中二进制的位数。由于i在不断加1，移动的位数要么与上一次移动的位数保持不变，要么加一。当i为2的幂次方的时候，也即是说i的二进制数中只有一位是1时，移动的位数相比于上次加1。

时间复杂度O(n)，空间复杂度O(1)。其中n为给定的整数。

### **Java**

```java
class Solution {
    public int concatenatedBinary(int n) {
        final int mod = (int) 1e9 + 7;
        long ans = 0;
        for (int i = 1; i <= n; ++i) {
            ans = (ans << (32 - Integer.numberOfLeadingZeros(i)) | i) % mod;
        }
        return (int) ans;
    }
}
```

```java
class Solution {
    public int concatenatedBinary(int n) {
        final int mod = (int) 1e9 + 7;
        long ans = 0;
        int shift = 0;
        for (int i = 1; i <= n; ++i) {
            if ((i & (i - 1)) == 0) {
                ++shift;
            }
            ans = (ans << shift | i) % mod;
        }
        return (int) ans;
    }
}
```
