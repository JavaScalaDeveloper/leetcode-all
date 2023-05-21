# [1679. K 和数对的最大数目](https://leetcode.cn/problems/max-number-of-k-sum-pairs)

## 题目描述

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code> 。</p>

<p>每一步操作中，你需要从数组中选出和为 <code>k</code> 的两个整数，并将它们移出数组。</p>

<p>返回你可以对数组执行的最大操作数。</p>



<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,4], k = 5
<strong>输出：</strong>2
<strong>解释：</strong>开始时 nums = [1,2,3,4]：
- 移出 1 和 4 ，之后 nums = [2,3]
- 移出 2 和 3 ，之后 nums = []
不再有和为 5 的数对，因此最多执行 2 次操作。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,1,3,4,3], k = 6
<strong>输出：</strong>1
<strong>解释：</strong>开始时 nums = [3,1,3,4,3]：
- 移出前两个 3 ，之后nums = [1,4,3]
不再有和为 6 的数对，因此最多执行 1 次操作。</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= nums[i] <= 10<sup>9</sup></code></li>
	<li><code>1 <= k <= 10<sup>9</sup></code></li>
</ul>

## 解法

**方法一：排序**

我们对 `nums` 进行排序。然后l,r分别指向 `nums` 首尾元素，判断两整数之和s与k的大小关系。

-   若s = k，说明找到了两个整数，满足和为k，答案加一，然后l,r向中间移动；
-   若s > k，则r指针向左移动；
-   若s < k，则l指针向右移动；
-   继续循环判断，直至l ≥ r。

循环结束，返回答案。

时间复杂度O(n× log n)，空间复杂度O(log n)。其中n为 `nums` 的长度。

**方法二：哈希表**

我们使用哈希表 `cnt` 记录当前剩余整数及其出现的次数。

遍历 `nums`，对于当前整数x，判断k - x是否在 `cnt` 中，若存在，则说明找到了两个整数，满足和为k，答案加一，然后将k - x的出现次数减一；否则，将x的出现次数加一。

遍历结束，返回答案。

时间复杂度O(n)，空间复杂度O(n)。其中n为 `nums` 的长度。

### **Java**

```java
class Solution {
    public int maxOperations(int[] nums, int k) {
        Arrays.sort(nums);
        int l = 0, r = nums.length - 1;
        int ans = 0;
        while (l < r) {
            int s = nums[l] + nums[r];
            if (s == k) {
                ++ans;
                ++l;
                --r;
            } else if (s > k) {
                --r;
            } else {
                ++l;
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int maxOperations(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int ans = 0;
        for (int x : nums) {
            if (cnt.containsKey(k - x)) {
                ++ans;
                if (cnt.merge(k - x, -1, Integer::sum) == 0) {
                    cnt.remove(k - x);
                }
            } else {
                cnt.merge(x, 1, Integer::sum);
            }
        }
        return ans;
    }
}
```
