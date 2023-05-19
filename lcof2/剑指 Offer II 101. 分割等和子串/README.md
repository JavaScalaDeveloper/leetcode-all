# [剑指 Offer II 101. 分割等和子串](https://leetcode.cn/problems/NUPfPr)

## 题目描述



<p>给定一个非空的正整数数组 <code>nums</code> ，请判断能否将这些数字分成元素和相等的两部分。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,5,11,5]
<strong>输出：</strong>true
<strong>解释：</strong>nums<strong> </strong>可以分割成 [1, 5, 5] 和 [11] 。</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,5]
<strong>输出：</strong>false
<strong>解释：</strong>nums<strong> </strong>不可以分为和相等的两部分
</pre>

<p>&nbsp;</p>

<p><meta charset="UTF-8" /></p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 200</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 416&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/partition-equal-subset-sum/">https://leetcode.cn/problems/partition-equal-subset-sum/</a></p>

## 解法

题目可以转换为 `0-1` 背包问题，在 m 个数字中选出一些数字（每个数字只能使用一次），这些数字之和恰好等于 `s / 2`（s 表示所有数字之和）。

也可以用 DFS + 记忆化搜索。

空间优化：

DFS + 记忆化搜索：

### **Java**

```java
class Solution {
    public boolean canPartition(int[] nums) {
        int s = 0;
        for (int x : nums) {
            s += x;
        }
        if (s % 2 != 0) {
            return false;
        }
        int m = nums.length, n = (s >> 1) + 1;
        boolean[] dp = new boolean[n];
        dp[0] = true;
        if (nums[0] < n) {
            dp[nums[0]] = true;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = n - 1; j >= nums[i]; --j) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[n - 1];
    }
}
```
