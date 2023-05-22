# [2616. 最小化数对的最大差值](https://leetcode.cn/problems/minimize-the-maximum-difference-of-pairs)

## 题目描述

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>p</code>&nbsp;。请你从&nbsp;<code>nums</code>&nbsp;中找到&nbsp;<code>p</code> 个下标对，每个下标对对应数值取差值，你需要使得这 <code>p</code> 个差值的&nbsp;<strong>最大值</strong>&nbsp;<strong>最小</strong>。同时，你需要确保每个下标在这&nbsp;<code>p</code>&nbsp;个下标对中最多出现一次。</p>

<p>对于一个下标对&nbsp;<code>i</code>&nbsp;和&nbsp;<code>j</code>&nbsp;，这一对的差值为&nbsp;<code>|nums[i] - nums[j]|</code>&nbsp;，其中&nbsp;<code>|x|</code>&nbsp;表示 <code>x</code>&nbsp;的 <strong>绝对值</strong>&nbsp;。</p>

<p>请你返回 <code>p</code>&nbsp;个下标对对应数值 <strong>最大差值</strong>&nbsp;的 <strong>最小值</strong>&nbsp;。</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [10,1,2,7,1,3], p = 2
<b>输出：</b>1
<b>解释：</b>第一个下标对选择 1 和 4 ，第二个下标对选择 2 和 5 。
最大差值为 max(|nums[1] - nums[4]|, |nums[2] - nums[5]|) = max(0, 1) = 1 。所以我们返回 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [4,2,1,2], p = 1
<b>输出：</b>0
<b>解释：</b>选择下标 1 和 3 构成下标对。差值为 |2 - 2| = 0 ，这是最大差值的最小值。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= p &lt;= (nums.length)/2</code></li>
</ul>

## 解法

**方法一：二分查找 + 贪心**

我们注意到，最大差值具备单调性，即如果最大差值x满足条件，那么x-1也一定满足条件。因此我们可以使用二分查找的方法，找到最小的满足条件的最大差值。

我们可以将数组 `nums` 排序，然后枚举最大差值x，判断是否存在p个下标对，每个下标对对应数值取差值的最大值不超过x。如果存在，那么我们就可以将x减小，否则我们就将x增大。

判断是否存在p个下标对，每个下标对对应数值取差值的最大值不超过x，可以使用贪心的方法。我们从左到右遍历数组 `nums`，对于当前遍历到的下标i，如果i+1位置的数与i位置的数的差值不超过x，那么我们就可以将i和i+1位置的数作为一个下标对，更新下标对的数量cnt，然后将i的值增加2。否则，我们就将i的值增加1。遍历结束，如果cnt的值大于等于p，那么就说明存在p个下标对，每个下标对对应数值取差值的最大值不超过x，否则就说明不存在。

时间复杂度O(n × (log n + log m))，其中n是数组 `nums` 的长度，而m是数组 `nums` 中的最大值与最小值的差值。空间复杂度O(1)。

### **Java**

```java
class Solution {
    public int minimizeMax(int[] nums, int p) {
        Arrays.sort(nums);
        int n = nums.length;
        int l = 0, r = nums[n - 1] - nums[0] + 1;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (count(nums, mid) >= p) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private int count(int[] nums, int diff) {
        int cnt = 0;
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i + 1] - nums[i] <= diff) {
                ++cnt;
                ++i;
            }
        }
        return cnt;
    }
}
```
