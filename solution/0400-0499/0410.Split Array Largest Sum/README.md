# [410. 分割数组的最大值](https://leetcode.cn/problems/split-array-largest-sum)

## 题目描述

<p>给定一个非负整数数组 <code>nums</code> 和一个整数&nbsp;<code>m</code> ，你需要将这个数组分成&nbsp;<code>m</code><em>&nbsp;</em>个非空的连续子数组。</p>

<p>设计一个算法使得这&nbsp;<code>m</code><em>&nbsp;</em>个子数组各自和的最大值最小。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [7,2,5,10,8], m = 2
<strong>输出：</strong>18
<strong>解释：</strong>
一共有四种方法将 nums 分割为 2 个子数组。 
其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。
因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,4,5], m = 2
<strong>输出：</strong>9
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,4,4], m = 3
<strong>输出：</strong>4
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= m &lt;= min(50, nums.length)</code></li>
</ul>

## 解法

**方法一：二分查找**

我们注意到，当子数组的和的最大值越大，子数组的个数越少，当存在一个满足条件的子数组和的最大值时，那么比这个最大值更大的子数组和的最大值一定也满足条件。也就是说，我们可以对子数组和的最大值进行二分查找，找到满足条件的最小值。

我们定义二分查找的左边界left = max(nums)，右边界right = sum(nums)，然后对于二分查找的每一步，我们取中间值mid = (left + right) / 2，然后判断是否存在一个分割方式，使得子数组的和的最大值不超过mid，如果存在，则说明mid可能是满足条件的最小值，因此我们将右边界调整为mid，否则我们将左边界调整为mid + 1。

我们如何判断是否存在一个分割方式，使得子数组的和的最大值不超过mid呢？我们可以使用贪心的方法，从左到右遍历数组，将数组中的元素依次加入到子数组中，如果当前子数组的和大于mid，则我们将当前元素加入到下一个子数组中。如果我们能够将数组分割成不超过k个子数组，且每个子数组的和的最大值不超过mid，则说明mid是满足条件的最小值，否则mid不是满足条件的最小值。

时间复杂度O(n × log m)，空间复杂度 O(1)。其中n和m分别是数组的长度和数组所有元素的和。

### **Java**

```java
class Solution {
    public int splitArray(int[] nums, int k) {
        int left = 0, right = 0;
        for (int x : nums) {
            left = Math.max(left, x);
            right += x;
        }
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(nums, mid, k)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int[] nums, int mx, int k) {
        int s = 1 << 30, cnt = 0;
        for (int x : nums) {
            s += x;
            if (s > mx) {
                ++cnt;
                s = x;
            }
        }
        return cnt <= k;
    }
}
```
