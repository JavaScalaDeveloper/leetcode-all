# [41. 缺失的第一个正数](https://leetcode.cn/problems/first-missing-positive)

## 题目描述

<p>给你一个未排序的整数数组 <code>nums</code> ，请你找出其中没有出现的最小的正整数。</p>
请你实现时间复杂度为 <code>O(n)</code> 并且只使用常数级别额外空间的解决方案。

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,0]
<strong>输出：</strong>3
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,4,-1,1]
<strong>输出：</strong>2
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [7,8,9,11,12]
<strong>输出：</strong>1
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 5 * 10<sup>5</sup></code></li>
	<li><code>-2<sup>31</sup> <= nums[i] <= 2<sup>31</sup> - 1</code></li>
</ul>

## 解法

**方法一：原地交换**

我们假设数组 `nums` 长度为 $n$，那么最小的正整数一定在 $[1, .., n + 1]$ 之间。我们可以遍历数组，将数组中的每个数 $x$ 交换到它应该在的位置上，即 $x$ 应该在的位置为 $x - 1$。如果 $x$ 不在 $[1, n + 1]$ 之间，那么我们就不用管它。

遍历结束后，我们再遍历数组，如果 $i+1$ 不等于 $nums[i]$，那么 $i+1$ 就是我们要找的最小的正整数。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。

### **Java**

```java
class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            while (nums[i] >= 1 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < n; ++i) {
            if (i + 1 != nums[i]) {
                return i + 1;
            }
        }
        return n + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```

**
