# [2009. 使数组连续的最少操作数](https://leetcode.cn/problems/minimum-number-of-operations-to-make-array-continuous)

## 题目描述

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;。每一次操作中，你可以将&nbsp;<code>nums</code>&nbsp;中&nbsp;<strong>任意</strong>&nbsp;一个元素替换成 <strong>任意&nbsp;</strong>整数。</p>

<p>如果&nbsp;<code>nums</code>&nbsp;满足以下条件，那么它是 <strong>连续的</strong>&nbsp;：</p>

<ul>
	<li><code>nums</code>&nbsp;中所有元素都是 <b>互不相同</b>&nbsp;的。</li>
	<li><code>nums</code>&nbsp;中 <strong>最大</strong>&nbsp;元素与&nbsp;<strong>最小</strong>&nbsp;元素的差等于&nbsp;<code>nums.length - 1</code>&nbsp;。</li>
</ul>

<p>比方说，<code>nums = [4, 2, 5, 3]</code>&nbsp;是 <strong>连续的</strong>&nbsp;，但是&nbsp;<code>nums = [1, 2, 3, 5, 6]</code> <strong>不是连续的</strong>&nbsp;。</p>

<p>请你返回使 <code>nums</code>&nbsp;<strong>连续</strong>&nbsp;的 <strong>最少</strong>&nbsp;操作次数。</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [4,2,5,3]
<b>输出：</b>0
<b>解释：</b>nums 已经是连续的了。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [1,2,3,5,6]
<b>输出：</b>1
<b>解释：</b>一个可能的解是将最后一个元素变为 4 。
结果数组为 [1,2,3,5,4] ，是连续数组。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>nums = [1,10,100,1000]
<b>输出：</b>3
<b>解释：</b>一个可能的解是：
- 将第二个元素变为 2 。
- 将第三个元素变为 3 。
- 将第四个元素变为 4 。
结果数组为 [1,2,3,4] ，是连续数组。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

**方法一：排序 + 去重 + 二分查找**

我们先将数组排序，去重。

然后遍历数组，枚举以当前元素nums[i]作为连续数组的最小值，通过二分查找找到第一个大于nums[i] + n - 1的位置j，那么j-i就是当前元素作为最小值时，连续数组的长度，更新答案，即ans = \min(ans, n - (j - i))。

最后返回ans即可。

时间复杂度O(n \times \log n)，空间复杂度O(\log n)。其中n为数组长度。

**方法二：排序 + 去重 + 双指针**

与方法一类似，我们先将数组排序，去重。

然后遍历数组，枚举以当前元素nums[i]作为连续数组的最小值，通过双指针找到第一个大于nums[i] + n - 1的位置j，那么j-i就是当前元素作为最小值时，连续数组的长度，更新答案，即ans = \min(ans, n - (j - i))。

最后返回ans即可。

时间复杂度O(n \times \log n)，空间复杂度O(\log n)。其中n为数组长度。

### **Java**

```java
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int m = 1;
        for (int i = 1; i < n; ++i) {
            if (nums[i] != nums[i - 1]) {
                nums[m++] = nums[i];
            }
        }
        int ans = n;
        for (int i = 0; i < m; ++i) {
            int j = search(nums, nums[i] + n - 1, i, m);
            ans = Math.min(ans, n - (j - i));
        }
        return ans;
    }

    private int search(int[] nums, int x, int left, int right) {
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] > x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

```java
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int m = 1;
        for (int i = 1; i < n; ++i) {
            if (nums[i] != nums[i - 1]) {
                nums[m++] = nums[i];
            }
        }
        int ans = n;
        for (int i = 0, j = 0; i < m; ++i) {
            while (j < m && nums[j] - nums[i] <= n - 1) {
                ++j;
            }
            ans = Math.min(ans, n - (j - i));
        }
        return ans;
    }
}
```
