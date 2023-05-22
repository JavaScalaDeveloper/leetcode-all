# [201. 数字范围按位与](https://leetcode.cn/problems/bitwise-and-of-numbers-range)

## 题目描述

<p>给你两个整数 <code>left</code> 和 <code>right</code> ，表示区间 <code>[left, right]</code> ，返回此区间内所有数字 <strong>按位与</strong> 的结果（包含 <code>left</code> 、<code>right</code> 端点）。</p>



<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>left = 5, right = 7
<strong>输出：</strong>4
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>left = 0, right = 0
<strong>输出：</strong>0
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>left = 1, right = 2147483647
<strong>输出：</strong>0
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= left <= right <= 2<sup>31</sup> - 1</code></li>
</ul>

## 解法

**方法一：位运算**

题目可以转换为求数字的公共二进制前缀。

当left < right时，我们循环将right的最后一个二进制位1变成0，直到left = right，此时right即为数字的公共二进制前缀，返回right即可。

时间复杂度O(log n)，空间复杂度O(1)。

### **Java**

```java
class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        while (left < right) {
            right &= (right - 1);
        }
        return right;
    }
}
```
