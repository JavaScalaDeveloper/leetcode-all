# [1748. 唯一元素的和](https://leetcode.cn/problems/sum-of-unique-elements)

## 题目描述

<p>给你一个整数数组 <code>nums</code> 。数组中唯一元素是那些只出现 <strong>恰好一次</strong> 的元素。</p>

<p>请你返回 <code>nums</code> 中唯一元素的 <strong>和</strong> 。</p>



<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [1,2,3,2]
<b>输出：</b>4
<b>解释：</b>唯一元素为 [1,3] ，和为 4 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [1,1,1,1,1]
<b>输出：</b>0
<b>解释：</b>没有唯一元素，和为 0 。
</pre>

<p><strong>示例 3 ：</strong></p>

<pre><b>输入：</b>nums = [1,2,3,4,5]
<b>输出：</b>15
<b>解释：</b>唯一元素为 [1,2,3,4,5] ，和为 15 。
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## 解法

**方法一：计数**

我们可以用数组或哈希表 `cnt` 统计数组 `nums` 中每个数字出现的次数，然后遍历 `cnt`，对于出现次数为 1 的数字，将其加入答案。

遍历结束后，返回答案即可。

时间复杂度O(n)，空间复杂度O(M)。其中n和m分别是数组 `nums` 的长度和 `nums` 中的最大值。

### **Java**

```java
class Solution {
    public int sumOfUnique(int[] nums) {
        int[] cnt = new int[101];
        for (int x : nums) {
            ++cnt[x];
        }
        int ans = 0;
        for (int x = 0; x < 101; ++x) {
            if (cnt[x] == 1) {
                ans += x;
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int sumOfUnique(int[] nums) {
        int ans = 0;
        int[] cnt = new int[101];
        for (int x : nums) {
            if (++cnt[x] == 1) {
                ans += x;
            } else if (cnt[x] == 2) {
                ans -= x;
            }
        }
        return ans;
    }
}
```
