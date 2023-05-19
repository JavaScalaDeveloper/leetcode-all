# [面试题 53 - I. 在排序数组中查找数字 I](https://leetcode.cn/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/)

## 题目描述

<p>统计一个数字在排序数组中出现的次数。</p>

<p> </p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [<code>5,7,7,8,8,10]</code>, target = 8
<strong>输出:</strong> 2</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [<code>5,7,7,8,8,10]</code>, target = 6
<strong>输出:</strong> 0</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> <= nums[i] <= 10<sup>9</sup></code></li>
	<li><code>nums</code> 是一个非递减数组</li>
	<li><code>-10<sup>9</sup> <= target <= 10<sup>9</sup></code></li>
</ul>

<p> </p>

<p><strong>注意：</strong>本题与主站 34 题相同（仅返回值不同）：<a href="https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/">https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/</a></p>

## 解法

**方法一：二分查找**

由于数组 `nums` 已排好序，我们可以使用二分查找的方法找到数组中第一个大于等于 `target` 的元素的下标 $l$，以及第一个大于 `target` 的元素的下标 $r$，那么 `target` 的个数就是 $r - l$。

时间复杂度 $O(\log n)$，空间复杂度 $O(1)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

### **Python3**



### **Java**

```java
class Solution {
    public int search(int[] nums, int target) {
        int l = lowerBound(nums, target);
        int r = lowerBound(nums, target + 1);
        return r - l;
    }

    private int lowerBound(int[] nums, int x) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```





















### **...**

```

```


