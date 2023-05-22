# [325. 和等于 k 的最长子数组长度](https://leetcode.cn/problems/maximum-size-subarray-sum-equals-k)

## 题目描述

<p>给定一个数组 <code><em>nums</em></code> 和一个目标值 <code><em>k</em></code>，找到和等于<em> <code>k</code> </em>的最长连续子数组长度。如果不存在任意一个符合要求的子数组，则返回 <code>0</code>。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入: </strong><em>nums</em> = <code>[1,-1,5,-2,3]</code>, <em>k</em> = <code>3</code>
<strong>输出: </strong>4 
<strong>解释: </strong>子数组 <code>[1, -1, 5, -2]</code> 和等于 3，且长度最长。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入: </strong><em>nums</em> = <code>[-2,-1,2,1]</code>, <em>k</em> = <code>1</code>
<strong>输出: </strong>2 <strong>
解释: </strong>子数组<code> [-1, 2]</code> 和等于 1，且长度最长。</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup>&nbsp;&lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup>&nbsp;&lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

**方法一：哈希表 + 前缀和**

我们可以用一个哈希表d记录数组nums中每个前缀和第一次出现的下标，初始时d[0] = -1。另外定义一个变量s记录前缀和。

接下来，遍历数组nums，对于当前遍历到的数字nums[i]，我们更新前缀和s = s + nums[i]，如果s-k在哈希表d中存在，不妨记j = d[s - k]，那么以nums[i]结尾的符合条件的子数组的长度为i - j，我们使用一个变量ans来维护最长的符合条件的子数组的长度。然后，如果s在哈希表中不存在，我们记录s和对应的下标i，即d[s] = i，否则我们不更新d[s]。需要注意的是，可能会有多个位置i都满足s的值，因此我们只记录最小的i，这样就能保证子数组的长度最长。

遍历结束之后，我们返回ans即可。

时间复杂度O(n)，空间复杂度O(n)。其中n是数组nums的长度。

### **Java**

```java
class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        Map<Long, Integer> d = new HashMap<>();
        d.put(0L, -1);
        int ans = 0;
        long s = 0;
        for (int i = 0; i < nums.length; ++i) {
            s += nums[i];
            ans = Math.max(ans, i - d.getOrDefault(s - k, i));
            d.putIfAbsent(s, i);
        }
        return ans;
    }
}
```
