# [1838. 最高频元素的频数](https://leetcode.cn/problems/frequency-of-the-most-frequent-element)

## 题目描述

<p>元素的 <strong>频数</strong> 是该元素在一个数组中出现的次数。</p>

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code> 。在一步操作中，你可以选择 <code>nums</code> 的一个下标，并将该下标对应元素的值增加 <code>1</code> 。</p>

<p>执行最多 <code>k</code> 次操作后，返回数组中最高频元素的 <strong>最大可能频数</strong> <em>。</em></p>



<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,4], k = 5
<strong>输出：</strong>3<strong>
解释：</strong>对第一个元素执行 3 次递增操作，对第二个元素执 2 次递增操作，此时 nums = [4,4,4] 。
4 是数组中最高频元素，频数是 3 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,4,8,13], k = 5
<strong>输出：</strong>2
<strong>解释：</strong>存在多种最优解决方案：
- 对第一个元素执行 3 次递增操作，此时 nums = [4,4,8,13] 。4 是数组中最高频元素，频数是 2 。
- 对第二个元素执行 4 次递增操作，此时 nums = [1,8,8,13] 。8 是数组中最高频元素，频数是 2 。
- 对第三个元素执行 5 次递增操作，此时 nums = [1,4,13,13] 。13 是数组中最高频元素，频数是 2 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,9,6], k = 2
<strong>输出：</strong>1
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= nums[i] <= 10<sup>5</sup></code></li>
	<li><code>1 <= k <= 10<sup>5</sup></code></li>
</ul>

## 解法

**方法一：排序 + 滑动窗口**

我们可以先对数组nums进行排序，然后枚举每个数作为最高频元素，用滑动窗口维护下标l到r的数都增加到nums[r]的操作次数。如果操作次数大于k，则窗口左端右移，直到操作次数小于等于k。这样，我们就可以求出以每个数为最高频元素的最大频数。

时间复杂度O(n \times \log n)，空间复杂度O(\log n)。其中n为数组nums的长度。

**方法二：排序 + 前缀和 + 二分查找**

我们观察发现，如果一个区间长度cnt满足条件，那么区间长度小于cnt的也一定满足条件。因此，我们可以使用二分查找的方法，找到最大的且满足条件的区间长度。

在二分查找之前，我们需要对数组nums[r]进行排序，然后计算出数组nums[r]的前缀和数组s，其中s[i]表示数组nums[r]前i个数的和。这样，我们就可以在O(1)的时间内求出区间[i, j]的和为s[j + 1] - s[i]。

接下来，我们定义二分的左边界left=1,right=n。然后二分枚举区间长度mid，如果当前区间长度mid满足条件，那么我们就更新二分的左边界为mid，否则更新二分的右边界为mid-1。最后，我们返回二分的左边界即可。

问题转换为如何判断区间长度为cnt的区间是否满足条件。我们在[0,..n-cnt]范围内枚举左端点i，那么此时区间的右端点j = i + cnt - 1。要把区间内的所有数都增加到nums[j]，需要的操作次数为nums[j] \times cnt - (s[j + 1] - s[i])。如果这个操作次数小于等于k，那么说明区间长度为cnt的区间满足条件，返回 `true`。否则枚举结束，返回 `false`。

时间复杂度O(n \times \log n)，空间复杂度O(n)。其中n为数组nums的长度。

### **Java**

```java
class Solution {
    public int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = 1, window = 0;
        for (int l = 0, r = 1; r < n; ++r) {
            window += (nums[r] - nums[r - 1]) * (r - l);
            while (window > k) {
                window -= (nums[r] - nums[l++]);
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}
```

```java
class Solution {
    private long[] s;
    private int[] nums;
    private int n;
    private int k;

    public int maxFrequency(int[] nums, int k) {
        n = nums.length;
        Arrays.sort(nums);
        this.nums = nums;
        this.s = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        this.k = k;
        int left = 1, right = n;
        while (left < right) {
            int mid = (left + right + 1) >>> 1;
            if (check(mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean check(int cnt) {
        for (int i = 0; i < n + 1 - cnt; ++i) {
            int j = i + cnt - 1;
            if (1L * nums[j] * cnt - (s[j + 1] - s[i]) <= k) {
                return true;
            }
        }
        return false;
    }
}
```
