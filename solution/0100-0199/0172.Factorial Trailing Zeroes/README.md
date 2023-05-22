# [172. 阶乘后的零](https://leetcode.cn/problems/factorial-trailing-zeroes)

## 题目描述

<p>给定一个整数 <code>n</code> ，返回 <code>n!</code> 结果中尾随零的数量。</p>

<p>提示&nbsp;<code>n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1</code></p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 3
<strong>输出：</strong>0
<strong>解释：</strong>3! = 6 ，不含尾随 0
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 5
<strong>输出：</strong>1
<strong>解释：</strong>5! = 120 ，有一个尾随 0
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 0
<strong>输出：</strong>0
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>4</sup></code></li>
</ul>

<p><b>进阶：</b>你可以设计并实现对数时间复杂度的算法来解决此问题吗？</p>

## 解法

**方法一：数学**

题目实际上是求[1,n]中有多少个5的因数。

我们以130为例来分析：

1. 第1次除以5，得到26，表示存在26个包含因数5的数；
1. 第2次除以5，得到5，表示存在5个包含因数5^2的数；
1. 第3次除以5，得到1，表示存在1个包含因数5^3的数；
1. 累加得到从[1,n]中所有5的因数的个数。

### **Java**

```java
class Solution {
    public int trailingZeroes(int n) {
        int ans = 0;
        while (n > 0) {
            n /= 5;
            ans += n;
        }
        return ans;
    }
}
```
