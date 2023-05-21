# [1015. 可被 K 整除的最小整数](https://leetcode.cn/problems/smallest-integer-divisible-by-k)

## 题目描述

<p>给定正整数 <code>k</code>&nbsp;，你需要找出可以被 <code>k</code>&nbsp;整除的、仅包含数字 <code><strong>1</strong></code> 的最 <strong>小</strong> 正整数 <code>n</code>&nbsp;的长度。</p>

<p>返回 <code>n</code>&nbsp;的长度。如果不存在这样的 <code>n</code>&nbsp;，就返回-1。</p>

<p><strong>注意：</strong> <code>n</code> 可能不符合 64 位带符号整数。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>k = 1
<strong>输出：</strong>1
<strong>解释：</strong>最小的答案是 n = 1，其长度为 1。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>k = 2
<strong>输出：</strong>-1
<strong>解释：</strong>不存在可被 2 整除的正整数 n 。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>k = 3
<strong>输出：</strong>3
<strong>解释：</strong>最小的答案是 n = 111，其长度为 3。</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

**方法一：数学**

我们注意到，正整数n初始值为1，每次乘以10后再加1，即n = n × 10 + 1，而(n × 10 + 1) mod k = ((n mod k) × 10 + 1) mod k，因此我们可以通过计算n mod k来判断n是否能被k整除。

我们从n = 1开始，每次计算n mod k，直到n mod k = 0，此时n就是我们要求的最小正整数，其长度即为n的位数。否则，我们更新n = (n × 10 + 1) mod k。如果循环k次后，仍然没有找到n mod k = 0，则说明不存在这样的n，返回-1。

时间复杂度O(k)，空间复杂度O(1)。其中k为给定的正整数。

### **Java**

```java
class Solution {
    public int smallestRepunitDivByK(int k) {
        int n = 1 % k;
        for (int i = 1; i <= k; ++i) {
            if (n == 0) {
                return i;
            }
            n = (n * 10 + 1) % k;
        }
        return -1;
    }
}
```
