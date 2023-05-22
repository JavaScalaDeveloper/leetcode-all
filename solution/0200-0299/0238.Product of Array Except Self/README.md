# [238. 除自身以外数组的乘积](https://leetcode.cn/problems/product-of-array-except-self)

## 题目描述

<p>给你一个整数数组&nbsp;<code>nums</code>，返回 <em>数组&nbsp;<code>answer</code>&nbsp;，其中&nbsp;<code>answer[i]</code>&nbsp;等于&nbsp;<code>nums</code>&nbsp;中除&nbsp;<code>nums[i]</code>&nbsp;之外其余各元素的乘积</em>&nbsp;。</p>

<p>题目数据 <strong>保证</strong> 数组&nbsp;<code>nums</code>之中任意元素的全部前缀元素和后缀的乘积都在&nbsp; <strong>32 位</strong> 整数范围内。</p>

<p>请<strong>不要使用除法，</strong>且在&nbsp;<code>O(<em>n</em>)</code> 时间复杂度内完成此题。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = <code>[1,2,3,4]</code>
<strong>输出:</strong> <code>[24,12,8,6]</code>
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [-1,1,0,-3,3]
<strong>输出:</strong> [0,0,9,0,0]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-30 &lt;= nums[i] &lt;= 30</code></li>
	<li><strong>保证</strong> 数组&nbsp;<code>nums</code>之中任意元素的全部前缀元素和后缀的乘积都在&nbsp; <strong>32 位</strong> 整数范围内</li>
</ul>

<p><strong>进阶：</strong>你可以在 <code>O(1)</code>&nbsp;的额外空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组<strong>不被视为</strong>额外空间。）</p>

## 解法

**方法一：两次遍历**

我们定义两个变量left和right，分别表示当前元素左边所有元素的乘积和右边所有元素的乘积。初始时left=1,right=1。定义一个长度为n的答案数组ans。

我们先从左到右遍历数组，对于遍历到的第i个元素，我们用left更新ans[i]，然后left乘以nums[i]。

然后，我们从右到左遍历数组，对于遍历到的第i个元素，我们更新ans[i]为ans[i] × right，然后right乘以nums[i]。

遍历结束后，数组 `ans` 即为所求的答案。

时间复杂度O(n)，其中n是数组 `nums` 的长度。忽略答案数组的空间消耗，空间复杂度O(1)。

### **Java**

```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0, left = 1; i < n; ++i) {
            ans[i] = left;
            left *= nums[i];
        }
        for (int i = n - 1, right = 1; i >= 0; --i) {
            ans[i] *= right;
            right *= nums[i];
        }
        return ans;
    }
}
```
