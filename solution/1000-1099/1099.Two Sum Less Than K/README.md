# [1099. 小于 K 的两数之和](https://leetcode.cn/problems/two-sum-less-than-k)

## 题目描述

<p>给你一个整数数组 <code>nums</code> 和整数 <code>k</code> ，返回最大和 <code>sum</code> ，满足存在 <code>i < j</code> 使得 <code>nums[i] + nums[j] = sum</code> 且 <code>sum < k</code> 。如果没有满足此等式的 <code>i,j</code> 存在，则返回 <code>-1</code> 。</p>



<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [34,23,1,24,75,33,54,8], k = 60
<strong>输出：</strong>58
<strong>解释：</strong>
34 和 24 相加得到 58，58 小于 60，满足题意。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [10,20,30], k = 15
<strong>输出：</strong>-1
<strong>解释：</strong>
我们无法找到和小于 15 的两个元素。</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 100</code></li>
	<li><code>1 <= nums[i] <= 1000</code></li>
	<li><code>1 <= k <= 2000</code></li>
</ul>

## 解法

**方法一：排序 + 二分查找**

我们可以先对数组 `nums` 进行排序，初始化答案为 `-1`。

接下来，我们枚举数组中的每个元素 `nums[i]`，并在数组中寻找满足 `nums[j] + nums[i] < k` 的最大的 `nums[j]`。这里我们可以使用二分查找来加速寻找过程。如果找到了这样的 `nums[j]`，那么我们就可以更新答案，即 `ans = max(ans, nums[i] + nums[j])`。

枚举结束后，返回答案即可。

时间复杂度O(n \times \log n)，空间复杂度O(\log n)。其中n是数组 `nums` 的长度。

**方法二：排序 + 双指针**

与方法一类似，我们可以先对数组 `nums` 进行排序，初始化答案为 `-1`。

接下来，我们使用双指针i和j分别指向数组的左右两端，每次判断 `nums[i] + nums[j]` 是否小于 `k`，如果小于 `k`，那么我们就可以更新答案，即 `ans = max(ans, nums[i] + nums[j])`，并将i右移一位，否则将j左移一位。

枚举结束后，返回答案即可。

时间复杂度O(n \times \log n)，空间复杂度O(\log n)。其中n是数组 `nums` 的长度。

### **Java**

```java
class Solution {
    public int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = -1;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            int j = search(nums, k - nums[i], i + 1, n) - 1;
            if (i < j) {
                ans = Math.max(ans, nums[i] + nums[j]);
            }
        }
        return ans;
    }

    private int search(int[] nums, int x, int l, int r) {
        while (l < r) {
            int mid = (l + r) >> 1;
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

```java
class Solution {
    public int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = -1;
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int t = nums[i] + nums[j];
            if (t < k) {
                ans = Math.max(ans, t);
                ++i;
            } else {
                --j;
            }
        }
        return ans;
    }
}
```
