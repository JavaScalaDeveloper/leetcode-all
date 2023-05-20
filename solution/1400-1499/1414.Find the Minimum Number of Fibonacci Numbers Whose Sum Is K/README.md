# [1414. 和为 K 的最少斐波那契数字数目](https://leetcode.cn/problems/find-the-minimum-number-of-fibonacci-numbers-whose-sum-is-k)

## 题目描述

<p>给你数字 <code>k</code>&nbsp;，请你返回和为&nbsp;<code>k</code>&nbsp;的斐波那契数字的最少数目，其中，每个斐波那契数字都可以被使用多次。</p>

<p>斐波那契数字定义为：</p>

<ul>
	<li>F<sub>1</sub> = 1</li>
	<li>F<sub>2</sub> = 1</li>
	<li>F<sub>n</sub> = F<sub>n-1</sub> + F<sub>n-2</sub>&nbsp;， 其中 n &gt; 2 。</li>
</ul>

<p>数据保证对于给定的 <code>k</code>&nbsp;，一定能找到可行解。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>k = 7
<strong>输出：</strong>2 
<strong>解释：</strong>斐波那契数字为：1，1，2，3，5，8，13，&hellip;&hellip;
对于 k = 7 ，我们可以得到 2 + 5 = 7 。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>k = 10
<strong>输出：</strong>2 
<strong>解释：</strong>对于 k = 10 ，我们可以得到 2 + 8 = 10 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>k = 19
<strong>输出：</strong>3 
<strong>解释：</strong>对于 k = 19 ，我们可以得到 1 + 5 + 13 = 19 。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= 10^9</code></li>
</ul>

## 解法

由于斐波那契数特点，数字重用在此题中是一个烟雾弹。举例推导：`k = 288`，数列（局部）`1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377`，可以由两个 144，或 `233 + 55` 组成；`k = 10`，可以由两个 5 或 `8 + 2` 组成。

由此可以使用贪心策略，逆向遍历斐波那契数列，进行暴力查找：

```txt
FIND-MIN-FIBONACCI-NUMBERS(k)
    r = 0
    for n in f
        if k >= n
            k -= n
            r++
            if k === 0
                return res
```

### **Java**

```java
class Solution {

    public int findMinFibonacciNumbers(int k) {
        if (k < 2) {
            return k;
        }
        int a = 1, b = 1;
        while (b <= k) {
            b = a + b;
            a = b - a;
        }
        return 1 + findMinFibonacciNumbers(k - a);
    }
}
```
