# [416. 分割等和子集](https://leetcode.cn/problems/partition-equal-subset-sum)

## 题目描述

<p>给你一个 <strong>只包含正整数 </strong>的 <strong>非空 </strong>数组 <code>nums</code> 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,5,11,5]
<strong>输出：</strong>true
<strong>解释：</strong>数组可以分割成 [1, 5, 5] 和 [11] 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,5]
<strong>输出：</strong>false
<strong>解释：</strong>数组不能分割成两个元素和相等的子集。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 200</code></li>
	<li><code>1 <= nums[i] <= 100</code></li>
</ul>

## 解法

**方法一：动态规划**

题目可以转换为 `0-1` 背包问题。

设整数数组总和为 `s`，要使得数组分割成两个元素和相等的子数组，需要满足 s 能够被 2 整除。在此前提下，我们可以将问题抽象为： 从数组中选出若干个数，使得选出的元素之和为 `s/2`。显然这是一个 `0-1` 背包问题。

定义 `dp[i][j]` 表示是否可以从前 i 个数中选出若干个数，使得所选元素之和为 j。

动态规划——`0-1` 背包朴素做法：

动态规划——`0-1` 背包空间优化：

DFS + 记忆化搜索：

### **Java**

```java
class Solution {
    public boolean canPartition(int[] nums) {
        int s = 0;
        for (int v : nums) {
            s += v;
        }
        if (s % 2 != 0) {
            return false;
        }
        int m = nums.length;
        int n = s >> 1;
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                dp[i][j] = dp[i - 1][j];
                if (!dp[i][j] && nums[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[m][n];
    }
}
```

```java
class Solution {
    public boolean canPartition(int[] nums) {
        int s = 0;
        for (int v : nums) {
            s += v;
        }
        if (s % 2 != 0) {
            return false;
        }
        int n = s >> 1;
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int v : nums) {
            for (int j = n; j >= v; --j) {
                dp[j] = dp[j] || dp[j - v];
            }
        }
        return dp[n];
    }
}
```
