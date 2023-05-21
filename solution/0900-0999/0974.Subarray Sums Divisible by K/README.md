# [974. 和可被 K 整除的子数组](https://leetcode.cn/problems/subarray-sums-divisible-by-k)

## 题目描述

<p>给定一个整数数组 <code>nums</code>&nbsp;和一个整数 <code>k</code> ，返回其中元素之和可被 <code>k</code>&nbsp;整除的（连续、非空） <strong>子数组</strong> 的数目。</p>

<p><strong>子数组</strong> 是数组的 <strong>连续</strong> 部分。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [4,5,0,-2,-3,1], k = 5
<strong>输出：</strong>7
<strong>解释：
</strong>有 7 个子数组满足其元素之和可被 k = 5 整除：
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [5], k = 9
<strong>输出:</strong> 0
</pre>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup>&nbsp;&lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>2 &lt;= k &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

**方法一：哈希表 + 前缀和**

假设存在i \leq j，使得nums[i,..j]的和能被k整除，如果我们令s_i表示nums[0,..i]的和，令s_j表示nums[0,..j]的和，那么s_j - s_i能被k整除，即(s_j - s_i) \bmod k = 0，也即s_j \bmod k = s_i \bmod k。因此，我们可以用哈希表统计前缀和模k的值的个数，从而快速判断是否存在满足条件的子数组。

我们用一个哈希表cnt统计前缀和模k的值的个数，即cnt[i]表示前缀和模k的值为i的个数。初始时cnt[0]=1。用变量s表示前缀和，初始时s = 0。

接下来，从左到右遍历数组nums，对于遍历到的每个元素x，我们计算s = (s + x) \bmod k，然后更新答案ans = ans + cnt[s]，其中cnt[s]表示前缀和模k的值为s的个数。最后我们将cnt[s]的值加1，继续遍历下一个元素。

最终，我们返回答案ans。

> 注意，由于s的值可能为负数，因此我们可以将s模k的结果加上k，再对k取模，以确保s的值为非负数。

时间复杂度O(n)，空间复杂度O(n)。其中n为数组nums的长度。

### **Java**

```java
class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        cnt.put(0, 1);
        int ans = 0, s = 0;
        for (int x : nums) {
            s = ((s + x) % k + k) % k;
            ans += cnt.getOrDefault(s, 0);
            cnt.merge(s, 1, Integer::sum);
        }
        return ans;
    }
}
```
