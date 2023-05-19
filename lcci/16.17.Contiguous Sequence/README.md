# [面试题 16.17. 连续数列](https://leetcode.cn/problems/contiguous-sequence-lcci)

[English Version](/lcci/16.17.Contiguous%20Sequence/README_EN.md)

## 题目描述


<p>给定一个整数数组（有正数有负数），找出总和最大的连续数列，并返回总和。</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong> [-2,1,-3,4,-1,2,1,-5,4]
<strong>输出：</strong> 6
<strong>解释：</strong> 连续子数组 [4,-1,2,1] 的和最大，为 6。
</pre>

<p><strong>进阶：</strong></p>

<p>如果你已经实现复杂度为 O(<em>n</em>) 的解法，尝试使用更为精妙的分治法求解。</p>

## 解法

**方法一：动态规划**

定义状态 `dp[i]` 表示以 `nums[i]` 结尾的连续子数组的最大和，初始时 `dp[0] = nums[0]`，当 $i\gt 0$ 时，状态转移方程为：

$$
dp[i]=\max(dp[i-1],0)+nums[i], i>0
$$

答案为 `dp` 数组中的最大值。

时间复杂度 $O(n)$，其中 $n$ 表示 `nums` 的长度。

由于 `dp[i]` 只与 `dp[i-1]` 有关，因此可以使用滚动数组优化空间复杂度，将空间复杂度降低到 $O(1)$。

### **Java**

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int inf = Integer.MIN_VALUE;
        int ans = inf, s = inf;
        for (int v : nums) {
            s = Math.max(s, 0) + v;
            ans = Math.max(ans, s);
        }
        return ans;
    }
}
```
