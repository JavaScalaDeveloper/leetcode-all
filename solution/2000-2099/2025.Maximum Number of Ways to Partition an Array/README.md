# [2025. 分割数组的最多方案数](https://leetcode.cn/problems/maximum-number-of-ways-to-partition-an-array)

## 题目描述

<p>给你一个下标从 <strong>0</strong>&nbsp;开始且长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;。<strong>分割</strong>&nbsp;数组 <code>nums</code>&nbsp;的方案数定义为符合以下两个条件的 <code>pivot</code>&nbsp;数目：</p>

<ul>
	<li><code>1 &lt;= pivot &lt; n</code></li>
	<li><code>nums[0] + nums[1] + ... + nums[pivot - 1] == nums[pivot] + nums[pivot + 1] + ... + nums[n - 1]</code></li>
</ul>

<p>同时给你一个整数&nbsp;<code>k</code>&nbsp;。你可以将&nbsp;<code>nums</code>&nbsp;中&nbsp;<strong>一个</strong>&nbsp;元素变为&nbsp;<code>k</code>&nbsp;或&nbsp;<strong>不改变</strong>&nbsp;数组。</p>

<p>请你返回在 <strong>至多</strong>&nbsp;改变一个元素的前提下，<strong>最多</strong>&nbsp;有多少种方法 <strong>分割</strong>&nbsp;<code>nums</code>&nbsp;使得上述两个条件都满足。</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [2,-1,2], k = 3
<b>输出：</b>1
<b>解释：</b>一个最优的方案是将 nums[0] 改为 k&nbsp;。数组变为 [<em><strong>3</strong></em>,-1,2] 。
有一种方法分割数组：
- pivot = 2 ，我们有分割 [3,-1 | 2]：3 + -1 == 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [0,0,0], k = 1
<b>输出：</b>2
<b>解释：</b>一个最优的方案是不改动数组。
有两种方法分割数组：
- pivot = 1 ，我们有分割 [0 | 0,0]：0 == 0 + 0 。
- pivot = 2 ，我们有分割 [0,0 | 0]: 0 + 0 == 0 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>nums = [22,4,-25,-20,-15,15,-16,7,19,-10,0,-13,-14], k = -33
<b>输出：</b>4
<b>解释：</b>一个最优的方案是将 nums[2] 改为 k 。数组变为 [22,4,<em><strong>-33</strong></em>,-20,-15,15,-16,7,19,-10,0,-13,-14] 。
有四种方法分割数组。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= k, nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

**方法一：前缀和 + 哈希表**

我们可以先预处理得到数组nums对应的前缀和数组s，其中s[i]表示数组nums[0,...i-1]的和。那么数组所有元素之和为s[n - 1]。

如果不修改数组nums，那么两个子数组的和相等的条件是s[n - 1]必须为偶数，如果s[n - 1]为偶数，那么我们求出ans = \frac{right[s[n - 1] / 2]}{2}。

如果修改数组nums，那么我们可以枚举每一个修改的位置i，将nums[i]修改为k，那么数组总和的变化量d = k - nums[i]，此时i左侧部分的和保持不变，那么合法的分割要满足s[i] = s[n - 1] + d - s[i]，即s[i] = \frac{s[n - 1] + d}{2}；而右侧部分的每个前缀和都增加了d，那么合法的分割要满足s[i] + d = s[n - 1] + d - (s[i] + d)，即s[i] = \frac{s[n - 1] - d}{2}。我们用哈希表left和right分别记录左侧部分和右侧部分每个前缀和出现的次数，那么我们可以求出ans = max(ans, left[\frac{s[n - 1] + d}{2}]) + right[\frac{s[n - 1] - d}{2}]。

最后，我们返回ans即可。

时间复杂度O(n)，空间复杂度O(n)。其中n为数组nums的长度。

### **Java**

```java
class Solution {
    public int waysToPartition(int[] nums, int k) {
        int n = nums.length;
        int[] s = new int[n];
        s[0] = nums[0];
        Map<Integer, Integer> right = new HashMap<>();
        for (int i = 0; i < n - 1; ++i) {
            right.merge(s[i], 1, Integer::sum);
            s[i + 1] = s[i] + nums[i + 1];
        }
        int ans = 0;
        if (s[n - 1] % 2 == 0) {
            ans = right.getOrDefault(s[n - 1] / 2, 0);
        }
        Map<Integer, Integer> left = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            int d = k - nums[i];
            if ((s[n - 1] + d) % 2 == 0) {
                int t = left.getOrDefault((s[n - 1] + d) / 2, 0)
                    + right.getOrDefault((s[n - 1] - d) / 2, 0);
                ans = Math.max(ans, t);
            }
            left.merge(s[i], 1, Integer::sum);
            right.merge(s[i], -1, Integer::sum);
        }
        return ans;
    }
}
```
