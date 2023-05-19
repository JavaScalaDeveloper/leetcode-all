# [2198. 单因数三元组](https://leetcode.cn/problems/number-of-single-divisor-triplets)

[English Version](/solution/2100-2199/2198.Number%20of%20Single%20Divisor%20Triplets/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个下标从 <strong>0</strong> 开始的正整数数组 <code>nums</code>。由三个&nbsp;<strong>不同&nbsp;</strong>索引&nbsp;<code>(i, j, k)</code> 组成的三元组，如果 <code>nums[i] + nums[j] + nums[k]</code> 能被 <code>nums[i]</code>、<code>nums[j]</code>&nbsp;或 <code>nums[k]</code> 中的&nbsp;<strong>一个&nbsp;</strong>整除，则称为 <code>nums</code> 的&nbsp;<strong>单因数三元组</strong>。</p>

<p>返回 <em><code>nums</code> 的单因数三元组</em>。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [4,6,7,3,2]
<strong>输出:</strong> 12
<strong>解释:
</strong>三元组索引 (0, 3, 4), (0, 4, 3), (3, 0, 4), (3, 4, 0), (4, 0, 3), 和 (4, 3, 0) 的值为 [4, 3, 2] (或者说排列为 [4, 3, 2]).
4 + 3 + 2 = 9 只能被 3 整除，所以所有的三元组都是单因数三元组。
三元组索引 (0, 2, 3), (0, 3, 2), (2, 0, 3), (2, 3, 0), (3, 0, 2), 和 (3, 2, 0) 的值为 [4, 7, 3]  (或者说排列为 [4, 7, 3]).
4 + 7 + 3 = 14 只能被 7 整除，所以所有的三元组都是单因数三元组。
一共有 12 个单因数三元组。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [1,2,2]
<strong>输出:</strong> 6
<strong>提示:</strong>
三元组索引 (0, 1, 2), (0, 2, 1), (1, 0, 2), (1, 2, 0), (2, 0, 1), 和 (2, 1, 0) 的值为 [1, 2, 2] (或者说排列为 [1, 2, 2]).
1 + 2 + 2 = 5 只能被 1 整除，所以所有的三元组都是单因数三元组。
一共有6个单因数三元组。</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> nums = [1,1,1]
<strong>输出:</strong> 0
<strong>提示:</strong>
没有单因数三元组。
注意 (0, 1, 2) 不是单因数三元组。 因为 nums[0] + nums[1] + nums[2] = 3，3 可以被 nums[0], nums[1], nums[2] 整除。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## 解法

### **Java**

```java
class Solution {
    public long singleDivisorTriplet(int[] nums) {
        int[] counter = new int[101];
        for (int x : nums) {
            ++counter[x];
        }
        long ans = 0;
        for (int i = 1; i <= 100; ++i) {
            for (int j = 1; j <= 100; ++j) {
                for (int k = 1; k <= 100; ++k) {
                    int cnt1 = counter[i], cnt2 = counter[j], cnt3 = counter[k];
                    int s = i + j + k;
                    int cnt = 0;
                    if (s % i == 0) {
                        ++cnt;
                    }
                    if (s % j == 0) {
                        ++cnt;
                    }
                    if (s % k == 0) {
                        ++cnt;
                    }
                    if (cnt != 1) {
                        continue;
                    }
                    if (i == j) {
                        ans += (long) cnt1 * (cnt1 - 1) * cnt3;
                    } else if (i == k) {
                        ans += (long) cnt1 * (cnt1 - 1) * cnt2;
                    } else if (j == k) {
                        ans += (long) cnt1 * cnt2 * (cnt2 - 1);
                    } else {
                        ans += (long) cnt1 * cnt2 * cnt3;
                    }
                }
            }
        }
        return ans;
    }
}
```

### **TypeScript**
