# [1856. 子数组最小乘积的最大值](https://leetcode.cn/problems/maximum-subarray-min-product)

## 题目描述

<p>一个数组的 <strong>最小乘积</strong> 定义为这个数组中 <strong>最小值</strong> <strong>乘以 </strong>数组的 <strong>和</strong> 。</p>

<ul>
	<li>比方说，数组 <code>[3,2,5]</code> （最小值是 <code>2</code>）的最小乘积为 <code>2 * (3+2+5) = 2 * 10 = 20</code> 。</li>
</ul>

<p>给你一个正整数数组 <code>nums</code> ，请你返回 <code>nums</code> 任意 <strong>非空子数组</strong> 的<strong>最小乘积</strong> 的 <strong>最大值</strong> 。由于答案可能很大，请你返回答案对  <code>10<sup>9</sup> + 7</code> <strong>取余 </strong>的结果。</p>

<p>请注意，最小乘积的最大值考虑的是取余操作 <strong>之前</strong> 的结果。题目保证最小乘积的最大值在 <strong>不取余</strong> 的情况下可以用 <strong>64 位有符号整数</strong> 保存。</p>

<p><strong>子数组</strong> 定义为一个数组的 <strong>连续</strong> 部分。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,<strong>2,3,2</strong>]
<b>输出：</b>14
<b>解释：</b>最小乘积的最大值由子数组 [2,3,2] （最小值是 2）得到。
2 * (2+3+2) = 2 * 7 = 14 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [2,<strong>3,3</strong>,1,2]
<b>输出：</b>18
<b>解释：</b>最小乘积的最大值由子数组 [3,3] （最小值是 3）得到。
3 * (3+3) = 3 * 6 = 18 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [3,1,<strong>5,6,4</strong>,2]
<b>输出：</b>60
<b>解释：</b>最小乘积的最大值由子数组 [5,6,4] （最小值是 4）得到。
4 * (5+6+4) = 4 * 15 = 60 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= nums[i] <= 10<sup>7</sup></code></li>
</ul>

## 解法

**方法一：单调栈 + 前缀和**

我们可以枚举每个元素 $nums[i]$ 作为子数组的最小值，找出子数组的左右边界 $left[i]$ 和 $right[i]$。其中 $left[i]$ 表示 $i$ 左侧第一个严格小于 $nums[i]$ 的位置，而 $right[i]$ 表示 $i$ 右侧第一个小于等于 $nums[i]$ 的位置。

为了方便地算出子数组的和，我们可以预处理出前缀和数组 $s$，其中 $s[i]$ 表示 $nums$ 前 $i$ 个元素的和。

那么以 $nums[i]$ 作为子数组最小值的最小乘积为 $nums[i] \times s[right[i]] - s[left[i] + 1]$。我们可以枚举每个元素 $nums[i]$，求出以 $nums[i]$ 作为子数组最小值的最小乘积，然后取最大值即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

### **Java**

```java
class Solution {
    public int maxSumMinProduct(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, n);
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            while (!stk.isEmpty() && nums[stk.peek()] >= nums[i]) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                left[i] = stk.peek();
            }
            stk.push(i);
        }
        stk.clear();
        for (int i = n - 1; i >= 0; --i) {
            while (!stk.isEmpty() && nums[stk.peek()] > nums[i]) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                right[i] = stk.peek();
            }
            stk.push(i);
        }
        long[] s = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        long ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, nums[i] * (s[right[i]] - s[left[i] + 1]));
        }
        final int mod = (int) 1e9 + 7;
        return (int) (ans % mod);
    }
}
```

### **TypeSript**
