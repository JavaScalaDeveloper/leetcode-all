# [1822. 数组元素积的符号](https://leetcode.cn/problems/sign-of-the-product-of-an-array)

[English Version](/solution/1800-1899/1822.Sign%20of%20the%20Product%20of%20an%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>已知函数 <code>signFunc(x)</code> 将会根据 <code>x</code> 的正负返回特定值：</p>

<ul>
	<li>如果 <code>x</code> 是正数，返回 <code>1</code> 。</li>
	<li>如果 <code>x</code> 是负数，返回 <code>-1</code> 。</li>
	<li>如果 <code>x</code> 是等于 <code>0</code> ，返回 <code>0</code> 。</li>
</ul>

<p>给你一个整数数组 <code>nums</code> 。令 <code>product</code> 为数组 <code>nums</code> 中所有元素值的乘积。</p>

<p>返回 <code>signFunc(product)</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [-1,-2,-3,-4,3,2,1]
<strong>输出：</strong>1
<strong>解释：</strong>数组中所有值的乘积是 144 ，且 signFunc(144) = 1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,5,0,2,-3]
<strong>输出：</strong>0
<strong>解释：</strong>数组中所有值的乘积是 0 ，且 signFunc(0) = 0
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [-1,1,-1,1,-1]
<strong>输出：</strong>-1
<strong>解释：</strong>数组中所有值的乘积是 -1 ，且 signFunc(-1) = -1
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 1000</code></li>
	<li><code>-100 <= nums[i] <= 100</code></li>
</ul>

## 解法

**方法一：直接遍历**

题目要求返回数组元素乘积的符号，即正数返回 $1$，负数返回 $-1$， 等于 $0$ 则返回 $0$。

我们可以定义一个答案变量 `ans`，初始值为 $1$。

然后遍历数组每个元素 $v$，如果 $v$ 为负数，则将 `ans` 乘上 $-1$，如果 $v$ 为 $0$，则提前返回 $0$。

遍历结束后，返回 `ans` 即可。

时间复杂度为 $O(n)$，空间复杂度为 $O(1)$。其中 $n$ 为数组长度。

### **Java**

```java
class Solution {
    public int arraySign(int[] nums) {
        int ans = 1;
        for (int v : nums) {
            if (v == 0) {
                return 0;
            }
            if (v < 0) {
                ans *= -1;
            }
        }
        return ans;
    }
}
```

### **C**

```c
int arraySign(int *nums, int numsSize) {
    int ans = 1;
    for (int i = 0; i < numsSize; i++) {
        if (nums[i] == 0) {
            return 0;
        }
        if (nums[i] < 0) {
            ans *= -1;
        }
    }
    return ans;
}
```
