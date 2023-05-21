# [1746. 经过一次操作后的最大子数组和](https://leetcode.cn/problems/maximum-subarray-sum-after-one-operation)

## 题目描述

<p>你有一个整数数组 <code>nums</code>。你只能将一个元素 <code>nums[i]</code> 替换为 <code>nums[i] * nums[i]</code>。</p>

<p>返回替换后的最大子数组和。</p>



<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,-1,-4,-3]
<strong>输出：</strong>17
<strong>解释：</strong>你可以把-4替换为16(-4*(-4))，使nums = [2,-1,<strong>16</strong>,-3]. 现在，最大子数组和为 2 + -1 + 16 = 17.</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,-1,1,1,-1,-1,1]
<strong>输出：</strong>4
<strong>解释：</strong>你可以把第一个-1替换为1，使 nums = [1,<strong>1</strong>,1,1,-1,-1,1]. 现在，最大子数组和为 1 + 1 + 1 + 1 = 4.</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> <= nums[i] <= 10<sup>4</sup></code></li>
</ul>

## 解法

**方法一：动态规划**

我们定义f[i]表示以nums[i]结尾，且没有进行替换的最大子数组和，另外定义g[i]表示以nums[i]结尾，且进行了替换的最大子数组和。那么有如下状态转移方程：


\begin{aligned}
f[i] &= \max(f[i - 1], 0) + nums[i] \\
g[i] &= \max(\max(f[i - 1], 0) + nums[i] \times nums[i], g[i - 1] + nums[i])
\end{aligned}


最终答案即为所有max(f[i], g[i])的最大值。

由于f[i]只与f[i - 1]有关，因此我们可以只用两个变量来维护f[i]和g[i]的值，从而将空间复杂度降低到O(1)。

时间复杂度O(n)，空间复杂度O(1)。其中n为数组nums的长度。

### **Java**

```java
class Solution {
    public int maxSumAfterOperation(int[] nums) {
        int f = 0, g = 0;
        int ans = Integer.MIN_VALUE;
        for (int x : nums) {
            int ff = Math.max(f, 0) + x;
            int gg = Math.max(Math.max(f, 0) + x * x, g + x);
            f = ff;
            g = gg;
            ans = Math.max(ans, Math.max(f, g));
        }
        return ans;
    }
}
```
