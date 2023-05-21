# [1250. 检查「好数组」](https://leetcode.cn/problems/check-if-it-is-a-good-array)

## 题目描述

<p>给你一个正整数数组 <code>nums</code>，你需要从中任选一些子集，然后将子集中每一个数乘以一个 <strong>任意整数</strong>，并求出他们的和。</p>

<p>假如该和结果为&nbsp;<code>1</code>，那么原数组就是一个「<strong>好数组</strong>」，则返回 <code>True</code>；否则请返回 <code>False</code>。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [12,5,7,23]
<strong>输出：</strong>true
<strong>解释：</strong>挑选数字 5 和 7。
5*3 + 7*(-2) = 1
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [29,6,10]
<strong>输出：</strong>true
<strong>解释：</strong>挑选数字 29, 6 和 10。
29*1 + 6*(-3) + 10*(-1) = 1
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [3,6]
<strong>输出：</strong>false
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10^5</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10^9</code></li>
</ul>

## 解法

**方法一：数学（裴蜀定理）**

我们先可以考虑选取两个数的情况，若选取的数是a和b，那么根据题目的要求，我们需要满足a \times x + b \times y = 1，其中x和y是任意整数。

根据裴蜀定理，可以得知，如果a和b互质，那么上述等式一定有解。实际上，裴蜀定理也可以推广到多个数的情况，即如果a_1, a_2, \cdots, a_i互质，那么a_1 \times x_1 + a_2 \times x_2 + \cdots + a_i \times x_i = 1一定有解，其中x_1, x_2, \cdots, x_i是任意整数。

因此，我们只需要判断在数组 `nums` 中是否存在i个互质的数即可。两个数互质的充要条件是它们的最大公约数为1。如果数组 `nums` 存在i个互质的数，那么数组 `nums` 中的所有数的最大公约数也为1。

所以我们将题目转化为：判断数组 `nums` 中的所有数的最大公约数是否为1。遍历数组 `nums`，求出数组 `nums` 中的所有数的最大公约数即可。

时间复杂度O(n + log m)，空间复杂度O(1)，其中n是数组 `nums` 的长度，而m是数组 `nums` 中的最大值。

### **Java**

```java
class Solution {
    public boolean isGoodArray(int[] nums) {
        int g = 0;
        for (int x : nums) {
            g = gcd(x, g);
        }
        return g == 1;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```
