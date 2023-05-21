# [762. 二进制表示中质数个计算置位](https://leetcode.cn/problems/prime-number-of-set-bits-in-binary-representation)

## 题目描述

<p>给你两个整数&nbsp;<code>left</code>&nbsp;和&nbsp;<code>right</code> ，在闭区间 <code>[left, right]</code>&nbsp;范围内，统计并返回 <strong>计算置位位数为质数</strong> 的整数个数。</p>

<p><strong>计算置位位数</strong> 就是二进制表示中 <code>1</code> 的个数。</p>

<ul>
	<li>例如， <code>21</code>&nbsp;的二进制表示&nbsp;<code>10101</code>&nbsp;有 <code>3</code> 个计算置位。</li>
</ul>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>left = 6, right = 10
<strong>输出：</strong>4
<strong>解释：</strong>
6 -&gt; 110 (2 个计算置位，2 是质数)
7 -&gt; 111 (3 个计算置位，3 是质数)
9 -&gt; 1001 (2 个计算置位，2 是质数)
10-&gt; 1010 (2 个计算置位，2 是质数)
共计 4 个计算置位为质数的数字。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>left = 10, right = 15
<strong>输出：</strong>5
<strong>解释：</strong>
10 -&gt; 1010 (2 个计算置位, 2 是质数)
11 -&gt; 1011 (3 个计算置位, 3 是质数)
12 -&gt; 1100 (2 个计算置位, 2 是质数)
13 -&gt; 1101 (3 个计算置位, 3 是质数)
14 -&gt; 1110 (3 个计算置位, 3 是质数)
15 -&gt; 1111 (4 个计算置位, 4 不是质数)
共计 5 个计算置位为质数的数字。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= left &lt;= right &lt;= 10<sup>6</sup></code></li>
	<li><code>0 &lt;= right - left &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

**方法一：数学 + 位运算**

题目中left和right的范围均在10^6以内，而2^{20} = 1048576，因此，二进制中1的个数最多也就20个，而20以内的质数有 `[2, 3, 5, 7, 11, 13, 17, 19]`。

我们枚举[left,.. right]范围内的每个数，统计其二进制中1的个数，然后判断该个数是否为质数，如果是，答案加一。

时间复杂度O(n× log m)。其中n = right - left + 1，而m为[left,.. right]范围内的最大数。

### **Java**

```java
class Solution {
    private static Set<Integer> primes = Set.of(2, 3, 5, 7, 11, 13, 17, 19);

    public int countPrimeSetBits(int left, int right) {
        int ans = 0;
        for (int i = left; i <= right; ++i) {
            if (primes.contains(Integer.bitCount(i))) {
                ++ans;
            }
        }
        return ans;
    }
}
```
