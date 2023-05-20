# [15. 三数之和](https://leetcode.cn/problems/3sum)

[English Version](/solution/0000-0099/0015.3Sum/README_EN.md)

## 题目描述

<p>给你一个整数数组 <code>nums</code> ，判断是否存在三元组 <code>[nums[i], nums[j], nums[k]]</code> 满足 <code>i != j</code>、<code>i != k</code> 且 <code>j != k</code> ，同时还满足 <code>nums[i] + nums[j] + nums[k] == 0</code> 。请</p>

<p>你返回所有和为 <code>0</code> 且不重复的三元组。</p>

<p><strong>注意：</strong>答案中不可以包含重复的三元组。</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [-1,0,1,2,-1,-4]
<strong>输出：</strong>[[-1,-1,2],[-1,0,1]]
<strong>解释：</strong>
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
注意，输出的顺序和三元组的顺序并不重要。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,1,1]
<strong>输出：</strong>[]
<strong>解释：</strong>唯一可能的三元组和不为 0 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,0,0]
<strong>输出：</strong>[[0,0,0]]
<strong>解释：</strong>唯一可能的三元组和为 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 3000</code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

**方法一：排序 + 双指针**

我们注意到，题目不要求我们按照顺序返回三元组，因此我们不妨先对数组进行排序，这样就可以方便地跳过重复的元素。

接下来，我们枚举三元组的第一个元素 $nums[i]$，其中 $0 \leq i \lt n - 2$。对于每个 $i$，我们可以通过维护两个指针 $j = i + 1$ 和 $k = n - 1$，从而找到满足 $nums[i] + nums[j] + nums[k] = 0$ 的 $j$ 和 $k$。在枚举的过程中，我们需要跳过重复的元素，以避免出现重复的三元组。

具体判断逻辑如下：

如果 $i \gt 0$ 并且 $nums[i] = nums[i - 1]$，则说明当前枚举的元素与上一个元素相同，我们可以直接跳过，因为不会产生新的结果。

如果 $nums[i] \gt 0$，则说明当前枚举的元素大于 $0$，则三数之和必然无法等于 $0$，结束枚举。

否则，我们令左指针 $j = i + 1$，右指针 $k = n - 1$，当 $j \lt k$ 时，执行循环，计算三数之和 $x = nums[i] + nums[j] + nums[k]$，并与 $0$ 比较：

-   如果 $x \lt 0$，则说明 $nums[j]$ 太小，我们需要将 $j$ 右移一位。
-   如果 $x \gt 0$，则说明 $nums[k]$ 太大，我们需要将 $k$ 左移一位。
-   否则，说明我们找到了一个合法的三元组，将其加入答案，并将 $j$ 右移一位，将 $k$ 左移一位，同时跳过所有重复的元素，继续寻找下一个合法的三元组。

枚举结束后，我们即可得到三元组的答案。

时间复杂度 $O(n^2)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组的长度。

### **Java**

```java
class Solution {
    /*
    将数组排序，这样相同的数字就会挨在一起，方便进行去重；
    从左到右枚举数组中的每个数字作为第一个数 a；
    在 a 右边的区域内使用双指针 left 和 right，找出所有满足 a + nums[left] + nums[right] == 0 的三元组，并将其加入结果集；
    注意去重。
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums); // 排序
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) { // 去重
                continue;
            }
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    while (left < right && nums[left] == nums[left - 1]) { // 去重
                        left++;
                    }
                    right--;
                    while (left < right && nums[right] == nums[right + 1]) { // 去重
                        right--;
                    }
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }
}
```
