# [面试题 66. 构建乘积数组](https://leetcode.cn/problems/gou-jian-cheng-ji-shu-zu-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个数组 <code>A[0,1,…,n-1]</code>，请构建一个数组 <code>B[0,1,…,n-1]</code>，其中 <code>B[i]</code> 的值是数组 <code>A</code> 中除了下标 <code>i</code> 以外的元素的积, 即 <code>B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]</code>。不能使用除法。</p>

<p> </p>

<p><strong>示例:</strong></p>

<pre>
<strong>输入:</strong> [1,2,3,4,5]
<strong>输出:</strong> [120,60,40,30,24]</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>所有元素乘积之和不会溢出 32 位整数</li>
	<li><code>a.length <= 100000</code></li>
</ul>

## 解法

**方法一：两次遍历**

我们先创建一个长度为 $n$ 的答案数组 $ans$。

接下来，我们从左到右遍历数组 $a$，过程中维护一个变量 $left$，表示当前元素左边所有元素的乘积，初始时 $left=1$。当遍历到 $a[i]$ 时，我们将 $left$ 赋值给 $ans[i]$，然后 $left$ 乘以 $a[i]$，即 $left \leftarrow left \times a[i]$。

然后，我们从右到左遍历数组 $a$，过程中维护一个变量 $right$，表示当前元素右边所有元素的乘积，初始时 $right=1$。当遍历到 $a[i]$ 时，我们将 $ans[i]$ 乘上 $right$，然后 $right$ 乘以 $a[i]$，即 $right \leftarrow right \times a[i]$。

最终，数组 $ans$ 即为所求的答案。

时间复杂度 $O(n)$，其中 $n$ 为数组 $a$ 的长度。忽略答案数组的空间消耗，空间复杂度 $O(1)$。

### **Java**

```java
class Solution {
    public int[] constructArr(int[] a) {
        int n = a.length;
        int[] ans = new int[n];
        for (int i = 0, left = 1; i < n; ++i) {
            ans[i] = left;
            left *= a[i];
        }
        for (int i = n - 1, right = 1; i >= 0; --i) {
            ans[i] *= right;
            right *= a[i];
        }
        return ans;
    }
}
```
