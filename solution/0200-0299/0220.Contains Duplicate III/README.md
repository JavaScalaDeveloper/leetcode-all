# [220. 存在重复元素 III](https://leetcode.cn/problems/contains-duplicate-iii)

[English Version](/solution/0200-0299/0220.Contains%20Duplicate%20III/README_EN.md)

## 题目描述

<p>给你一个整数数组 <code>nums</code> 和两个整数 <code>k</code> 和 <code>t</code> 。请你判断是否存在 <b>两个不同下标</b> <code>i</code> 和 <code>j</code>，使得 <code>abs(nums[i] - nums[j]) <= t</code> ，同时又满足 <code>abs(i - j) <= k</code><em> </em>。</p>

<p>如果存在则返回 <code>true</code>，不存在返回 <code>false</code>。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,1], k<em> </em>= 3, t = 0
<strong>输出：</strong>true</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,0,1,1], k<em> </em>=<em> </em>1, t = 2
<strong>输出：</strong>true</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,5,9,1,5,9], k = 2, t = 3
<strong>输出：</strong>false</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= nums.length <= 2 * 10<sup>4</sup></code></li>
	<li><code>-2<sup>31</sup> <= nums[i] <= 2<sup>31</sup> - 1</code></li>
	<li><code>0 <= k <= 10<sup>4</sup></code></li>
	<li><code>0 <= t <= 2<sup>31</sup> - 1</code></li>
</ul>

## 解法

**方法一：滑动窗口 + 有序集合**

维护一个大小为 $k$ 的滑动窗口，窗口中的元素保持有序。

遍历数组 `nums`，对于每个元素 $nums[i]$，我们在有序集合中查找第一个大于等于 $nums[i] - t$ 的元素，如果元素存在，并且该元素小于等于 $nums[i] + t$，说明找到了一对符合条件的元素，返回 `true`。否则，我们将 $nums[i]$ 插入到有序集合中，并且如果有序集合的大小超过了 $k$，我们需要将最早加入有序集合的元素删除。

时间复杂度 $O(n\times \log k)$，其中 $n$ 是数组 `nums` 的长度。对于每个元素，我们需要 $O(\log k)$ 的时间来查找有序集合中的元素，一共有 $n$ 个元素，因此总时间复杂度是 $O(n\times \log k)$。

### **Java**

```java
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Long> ts = new TreeSet<>();
        for (int i = 0; i < nums.length; ++i) {
            Long x = ts.ceiling((long) nums[i] - (long) valueDiff);
            if (x != null && x <= (long) nums[i] + (long) valueDiff) {
                return true;
            }
            ts.add((long) nums[i]);
            if (i >= indexDiff) {
                ts.remove((long) nums[i - indexDiff]);
            }
        }
        return false;
    }
}
```
