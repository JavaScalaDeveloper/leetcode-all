# [209. 长度最小的子数组](https://leetcode.cn/problems/minimum-size-subarray-sum)

## 题目描述

<p>给定一个含有 <code>n</code><strong> </strong>个正整数的数组和一个正整数 <code>target</code><strong> 。</strong></p>

<p>找出该数组中满足其和<strong> </strong><code>≥ target</code><strong> </strong>的长度最小的 <strong>连续子数组</strong> <code>[nums<sub>l</sub>, nums<sub>l+1</sub>, ..., nums<sub>r-1</sub>, nums<sub>r</sub>]</code> ，并返回其长度<strong>。</strong>如果不存在符合条件的子数组，返回 <code>0</code> 。</p>



<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>target = 7, nums = [2,3,1,2,4,3]
<strong>输出：</strong>2
<strong>解释：</strong>子数组 <code>[4,3]</code> 是该条件下的长度最小的子数组。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>target = 4, nums = [1,4,4]
<strong>输出：</strong>1
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>target = 11, nums = [1,1,1,1,1,1,1,1]
<strong>输出：</strong>0
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= target <= 10<sup>9</sup></code></li>
	<li><code>1 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= nums[i] <= 10<sup>5</sup></code></li>
</ul>



<p><strong>进阶：</strong></p>

<ul>
	<li>如果你已经实现<em> </em><code>O(n)</code> 时间复杂度的解法, 请尝试设计一个 <code>O(n log(n))</code> 时间复杂度的解法。</li>
</ul>

## 解法

**方法一：前缀和 + 二分查找**

我们先预处理出数组 $nums$ 的前缀和数组 $s$，其中 $s[i]$ 表示数组 $nums$ 前 $i$ 项元素之和。由于数组 $nums$ 中的元素都是正整数，因此数组 $s$ 也是单调递增的。另外，我们初始化答案 $ans = n + 1$，其中 $n$ 为数组 $nums$ 的长度。

接下来，我们遍历前缀和数组 $s$，对于其中的每个元素 $s[i]$，我们可以通过二分查找的方法找到满足 $s[j] \geq s[i] + target$ 的最小下标 $j$，如果 $j \leq n$，则说明存在满足条件的子数组，我们可以更新答案，即 $ans = min(ans, j - i)$。

最后，如果 $ans \leq n$，则说明存在满足条件的子数组，返回 $ans$，否则返回 $0$。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

**方法二：双指针**

我们可以使用双指针 $j$ 和 $i$ 维护一个窗口，其中窗口中的所有元素之和小于 $target$。初始时 $j = 0$，答案 $ans = n + 1$，其中 $n$ 为数组 $nums$ 的长度。

接下来，指针 $i$ 从 $0$ 开始向右移动，每次移动一步，我们将指针 $i$ 对应的元素加入窗口，同时更新窗口中元素之和。如果窗口中元素之和大于等于 $target$，说明当前子数组满足条件，我们可以更新答案，即 $ans = min(ans, i - j + 1)$。然后我们不断地从窗口中移除元素 $nums[j]$，直到窗口中元素之和小于 $target$，然后重复上述过程。

最后，如果 $ans \leq n$，则说明存在满足条件的子数组，返回 $ans$，否则返回 $0$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 $nums$ 的长度。

### **Java**

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        long[] s = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        int ans = n + 1;
        for (int i = 0; i <= n; ++i) {
            int j = search(s, s[i] + target);
            if (j <= n) {
                ans = Math.min(ans, j - i);
            }
        }
        return ans <= n ? ans : 0;
    }

    private int search(long[] nums, long x) {
        int l = 0, r = nums.length;
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
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        long s = 0;
        int ans = n + 1;
        for (int i = 0, j = 0; i < n; ++i) {
            s += nums[i];
            while (j < n && s >= target) {
                ans = Math.min(ans, i - j + 1);
                s -= nums[j++];
            }
        }
        return ans <= n ? ans : 0;
    }
}
```
