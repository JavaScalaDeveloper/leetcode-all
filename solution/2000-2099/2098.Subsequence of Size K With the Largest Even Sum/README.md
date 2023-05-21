# [2098. 长度为 K 的最大偶数和子序列](https://leetcode.cn/problems/subsequence-of-size-k-with-the-largest-even-sum)

## 题目描述

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code> 。找出&nbsp;<code>nums</code> 长度为 <code>k</code> 的所有子序列中的 <strong>最大偶数和</strong> 。<br />
返回<strong>此总和</strong>，如果此总和不存在，则返回 <code>-1</code>。<br />
<strong>子序列</strong> 是一个数组，可以通过删除一些元素或不删除任何元素而从另一个数组派生，而不改变其余元素的顺序。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> nums = [4,1,5,3,1], k = 3
<strong>输出:</strong> 12
<strong>解释:</strong>
具有最大可能偶数和的子序列是[4,5,3]。它的和为 4 + 5 + 3 = 12
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [4,6,2], k = 3
<strong>输出:</strong> 12
<strong>解释:</strong>
具有最大可能偶数和的子序列是[4,6,2]。它的和为 4 + 6 + 2 = 12
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> nums = [1,3,5], k = 1
<strong>输出:</strong> -1
<strong>解释:</strong>
长度为 1 的 NUM 的子序列没有偶数和。
</pre>

<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
</ul>

## 解法

**方法一：贪心 + 排序**

我们注意到，题目选取的是子序列，因此我们可以考虑先对数组进行排序。

接下来，我们先贪心地选取最大的k个数，如果这些数的和为偶数，则直接返回这个和ans。

否则，我们有两种贪心策略：

1. 在最大的k个数中，找到一个最小的偶数mi1，然后在剩下的n - k个数中，找到一个最大的奇数mx1，将mi1替换为mx1，如果存在这样的替换，那么替换后的和ans - mi1 + mx1一定是偶数；
1. 在最大的k个数中，找到一个最小的奇数mi2，然后在剩下的n - k个数中，找到一个最大的偶数mx2，将mi2替换为mx2，如果存在这样的替换，那么替换后的和ans - mi2 + mx2一定是偶数。

我们取最大的偶数和作为答案。如果不存在偶数和，则返回-1。

时间复杂度O(n \times \log n)，空间复杂度O(\log n)。其中n为数组长度。

### **Java**

```java
class Solution {
    public long largestEvenSum(int[] nums, int k) {
        Arrays.sort(nums);
        long ans = 0;
        int n = nums.length;
        for (int i = 0; i < k; ++i) {
            ans += nums[n - i - 1];
        }
        if (ans % 2 == 0) {
            return ans;
        }
        final int inf = 1 << 29;
        int mx1 = -inf, mx2 = -inf;
        for (int i = 0; i < n - k; ++i) {
            if (nums[i] % 2 == 1) {
                mx1 = nums[i];
            } else {
                mx2 = nums[i];
            }
        }
        int mi1 = inf, mi2 = inf;
        for (int i = n - 1; i >= n - k; --i) {
            if (nums[i] % 2 == 1) {
                mi2 = nums[i];
            } else {
                mi1 = nums[i];
            }
        }
        ans = Math.max(-1, Math.max(ans - mi1 + mx1, ans - mi2 + mx2));
        return ans % 2 != 0 ? -1 : ans;
    }
}
```
