# [2104. 子数组范围和](https://leetcode.cn/problems/sum-of-subarray-ranges)

[English Version](/solution/2100-2199/2104.Sum%20of%20Subarray%20Ranges/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> 。<code>nums</code> 中，子数组的 <strong>范围</strong> 是子数组中最大元素和最小元素的差值。</p>

<p>返回 <code>nums</code> 中 <strong>所有</strong> 子数组范围的 <strong>和</strong> <em>。</em></p>

<p>子数组是数组中一个连续 <strong>非空</strong> 的元素序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3]
<strong>输出：</strong>4
<strong>解释：</strong>nums 的 6 个子数组如下所示：
[1]，范围 = 最大 - 最小 = 1 - 1 = 0 
[2]，范围 = 2 - 2 = 0
[3]，范围 = 3 - 3 = 0
[1,2]，范围 = 2 - 1 = 1
[2,3]，范围 = 3 - 2 = 1
[1,2,3]，范围 = 3 - 1 = 2
所有范围的和是 0 + 0 + 0 + 1 + 1 + 2 = 4</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,3,3]
<strong>输出：</strong>4
<strong>解释：</strong>nums 的 6 个子数组如下所示：
[1]，范围 = 最大 - 最小 = 1 - 1 = 0
[3]，范围 = 3 - 3 = 0
[3]，范围 = 3 - 3 = 0
[1,3]，范围 = 3 - 1 = 2
[3,3]，范围 = 3 - 3 = 0
[1,3,3]，范围 = 3 - 1 = 2
所有范围的和是 0 + 0 + 0 + 2 + 0 + 2 = 4
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [4,-2,-3,4,1]
<strong>输出：</strong>59
<strong>解释：</strong>nums 中所有子数组范围的和是 59
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你可以设计一种时间复杂度为 <code>O(n)</code> 的解决方案吗？</p>

## 解法

**方法一：暴力枚举**

循环遍历 $i$，作为子数组的起始位置。对于每个 $i$，遍历每个 $j$ 作为子数组的终止位置，此过程中不断求解子数组的最大值、最小值，然后累加差值到结果 `ans` 中。

最后返回 `ans` 即可。

时间复杂度 $O(n^2)$。

**方法二：单调栈**

枚举每个元素 `nums[i]` 作为最大值出现在了多少个子数组中，以及作为最小值出现在多少个子数组中。

其中 `nums[i]` 作为最大值的贡献为正，作为最小值的贡献为负。

我们以 `nums[i]` 作为最大值为例。找出左侧第一个比 `nums[i]` 大的位置 `left[i]`，右侧第一个大于等于 `nums[i]` 的位置 `right[i]`。计算每个 `nums[i]` 的贡献 $(i - left[i])\times (right[i] - i)\times arr[i]$，累加得到结果。

时间复杂度 $O(n)$。

相似题目：[907. 子数组的最小值之和](/solution/0900-0999/0907.Sum%20of%20Subarray%20Minimums/README.md)

### **Java**

```java
class Solution {
    public long subArrayRanges(int[] nums) {
        long ans = 0;
        int n = nums.length;
        for (int i = 0; i < n - 1; ++i) {
            int mi = nums[i], mx = nums[i];
            for (int j = i + 1; j < n; ++j) {
                mi = Math.min(mi, nums[j]);
                mx = Math.max(mx, nums[j]);
                ans += (mx - mi);
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public long subArrayRanges(int[] nums) {
        long mx = f(nums);
        for (int i = 0; i < nums.length; ++i) {
            nums[i] *= -1;
        }
        long mi = f(nums);
        return mx + mi;
    }

    private long f(int[] nums) {
        Deque<Integer> stk = new ArrayDeque<>();
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, n);
        for (int i = 0; i < n; ++i) {
            while (!stk.isEmpty() && nums[stk.peek()] <= nums[i]) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                left[i] = stk.peek();
            }
            stk.push(i);
        }
        stk.clear();
        for (int i = n - 1; i >= 0; --i) {
            while (!stk.isEmpty() && nums[stk.peek()] < nums[i]) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                right[i] = stk.peek();
            }
            stk.push(i);
        }
        long s = 0;
        for (int i = 0; i < n; ++i) {
            s += (long) (i - left[i]) * (right[i] - i) * nums[i];
        }
        return s;
    }
}
```

### **TypeScript**
