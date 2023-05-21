# [2638. 统计 K-Free 子集的总数](https://leetcode.cn/problems/count-the-number-of-k-free-subsets)

## 题目描述

<p>给定一个包含 <strong>无重复</strong> 元素的整数数组 <code>nums</code> 和一个整数 <code>k</code> 。</p>

<p>如果一个子集中 <strong>不</strong> 存在两个差的绝对值等于 <code>k</code> 的元素，则称其为 <strong>k-Free</strong> 子集。注意，空集是一个 <strong>k-Free</strong> 子集。</p>

<p>返回 <code>nums</code> 中 <strong>k-Free</strong> 子集的数量。</p>

<p>一个数组的 <strong>子集</strong> 是该数组中的元素的选择（可能为零个）。</p>

<p><strong class="example">示例 1 ：</strong></p>

<pre>
<b>输入：</b>nums = [5,4,6], k = 1
<b>输出：</b>5
<b>解释：</b>有 5 个合法子集：{}, {5}, {4}, {6} 和 {4, 6} 。
</pre>

<p><strong class="example">示例 2 ：</strong></p>

<pre>
<b>输入：</b>nums = [2,3,5,8], k = 5
<b>输出：</b>12
<b>解释：</b>有12个合法子集：{}, {2}, {3}, {5}, {8}, {2, 3}, {2, 3, 5}, {2, 5}, {2, 5, 8}, {2, 8}, {3, 5} 和 {5, 8} 。
</pre>

<p><strong class="example">示例 3 ：</strong></p>

<pre>
<b>输入：</b>nums = [10,5,9,11], k = 20
<b>输出：</b>16
<b>解释：</b>所有的子集都是有效的。由于子集的总数为 24 = 16，因此答案为 16 。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 50</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= 1000</code></li>
</ul>

## 解法

**方法一：分组 + 动态规划**

我们先将数组nums按照升序排序，然后将数组中的元素按照模k分组，即nums[i] mod k相同的元素放在同一组中。那么对于任意两个不同组的元素，它们的差值的绝对值一定不等于k。因此，我们可以求出每一组的子集个数，然后将每一组的子集个数相乘即可得到答案。

对于每一组arr，我们可以使用动态规划求出子集个数。设f[i]表示前i个元素的子集个数，初始时f[0] = 1，而f[1]=2。当i ≥ 2时，如果arr[i-1]-arr[i-2]=k，如果我们选择arr[i-1]，那么f[i]=f[i-2]；如果我们不选择arr[i-1]，那么f[i]=f[i-1]。因此，当arr[i-1]-arr[i-2]=k时，有f[i]=f[i-1]+f[i-2]；否则f[i] = f[i - 1] × 2。这一组的子集个数即为f[m]，其中m为数组arr的长度。

最后，我们将每一组的子集个数相乘即可得到答案。

时间复杂度O(n × log n)，空间复杂度O(n)。其中n为数组nums的长度。

### **Java**

```java
class Solution {
    public long countTheNumOfKFreeSubsets(int[] nums, int k) {
        Arrays.sort(nums);
        Map<Integer, List<Integer>> g = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            g.computeIfAbsent(nums[i] % k, x -> new ArrayList<>()).add(nums[i]);
        }
        long ans = 1;
        for (var arr : g.values()) {
            int m = arr.size();
            long[] f = new long[m + 1];
            f[0] = 1;
            f[1] = 2;
            for (int i = 2; i <= m; ++i) {
                if (arr.get(i - 1) - arr.get(i - 2) == k) {
                    f[i] = f[i - 1] + f[i - 2];
                } else {
                    f[i] = f[i - 1] * 2;
                }
            }
            ans *= f[m];
        }
        return ans;
    }
}
```
