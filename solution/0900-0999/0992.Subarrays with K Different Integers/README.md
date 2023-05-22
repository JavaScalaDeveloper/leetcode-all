# [992. K 个不同整数的子数组](https://leetcode.cn/problems/subarrays-with-k-different-integers)

## 题目描述

<p>给定一个正整数数组 <code>nums</code>和一个整数 <font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size:12.6px"><span style="background-color:#f9f2f4">k</span></span></font></font>&nbsp;，返回 <font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size:12.6px"><span style="background-color:#f9f2f4">num</span></span></font></font>&nbsp;中 「<strong>好子数组」</strong><em>&nbsp;</em>的数目。</p>

<p>如果 <code>nums</code>&nbsp;的某个子数组中不同整数的个数恰好为 <code>k</code>，则称 <code>nums</code>&nbsp;的这个连续、不一定不同的子数组为 <strong>「</strong><strong>好子数组 」</strong>。</p>

<ul>
	<li>例如，<code>[1,2,3,1,2]</code> 中有&nbsp;<code>3</code>&nbsp;个不同的整数：<code>1</code>，<code>2</code>，以及&nbsp;<code>3</code>。</li>
</ul>

<p><strong>子数组</strong> 是数组的 <strong>连续</strong> 部分。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,1,2,3], k = 2
<strong>输出：</strong>7
<strong>解释：</strong>恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,1,3,4], k = 3
<strong>输出：</strong>3
<strong>解释：</strong>恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i], k &lt;= nums.length</code></li>
</ul>

## 解法

**方法一：双指针**

我们遍历数组nums，对于每个i，我们需要找到最小的j_1，使得nums[j_1], nums[j_1 + 1], \dots, nums[i]中不同的整数个数小于等于k，以及最小的j_2，使得nums[j_2], nums[j_2 + 1], \dots, nums[i]中不同的整数个数小于等于k-1。那么j_2 - j_1就是以i结尾的满足条件的子数组的个数。

在实现上，我们定义一个函数f(k)，表示nums中每个位置i对应的最小的j，使得nums[j], nums[j + 1], \dots, nums[i]中不同的整数个数小于等于k。该函数可以通过双指针实现，具体实现见代码。

然后我们调用f(k)和f(k-1)，计算出每个位置对应的j_1和j_2，然后计算出以每个位置i结尾的满足条件的子数组的个数，最后求和即可。

时间复杂度O(n)，空间复杂度O(n)。其中n为数组nums的长度。

### **Java**

```java
class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        int[] left = f(nums, k);
        int[] right = f(nums, k - 1);
        int ans = 0;
        for (int i = 0; i < nums.length; ++i) {
            ans += right[i] - left[i];
        }
        return ans;
    }

    private int[] f(int[] nums, int k) {
        int n = nums.length;
        int[] cnt = new int[n + 1];
        int[] pos = new int[n];
        int s = 0;
        for (int i = 0, j = 0; i < n; ++i) {
            if (++cnt[nums[i]] == 1) {
                ++s;
            }
            for (; s > k; ++j) {
                if (--cnt[nums[j]] == 0) {
                    --s;
                }
            }
            pos[i] = j;
        }
        return pos;
    }
}
```
