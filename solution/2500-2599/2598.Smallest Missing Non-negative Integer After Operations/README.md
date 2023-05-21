# [2598. 执行操作后的最大 MEX](https://leetcode.cn/problems/smallest-missing-non-negative-integer-after-operations)

## 题目描述

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> 和一个整数 <code>value</code> 。</p>

<p>在一步操作中，你可以对 <code>nums</code> 中的任一元素加上或减去 <code>value</code> 。</p>

<ul>
	<li>例如，如果 <code>nums = [1,2,3]</code> 且 <code>value = 2</code> ，你可以选择 <code>nums[0]</code> 减去 <code>value</code> ，得到 <code>nums = [-1,2,3]</code> 。</li>
</ul>

<p>数组的 MEX (minimum excluded) 是指其中数组中缺失的最小非负整数。</p>

<ul>
	<li>例如，<code>[-1,2,3]</code> 的 MEX 是 <code>0</code> ，而 <code>[1,0,3]</code> 的 MEX 是 <code>2</code> 。</li>
</ul>

<p>返回在执行上述操作 <strong>任意次</strong> 后，<code>nums</code><em> </em>的最大 MEX <em>。</em></p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [1,-10,7,13,6,8], value = 5
<strong>输出：</strong>4
<strong>解释：</strong>执行下述操作可以得到这一结果：
- nums[1] 加上 value 两次，nums = [1,<em><strong>0</strong></em>,7,13,6,8]
- nums[2] 减去 value 一次，nums = [1,0,<em><strong>2</strong></em>,13,6,8]
- nums[3] 减去 value 两次，nums = [1,0,2,<em><strong>3</strong></em>,6,8]
nums 的 MEX 是 4 。可以证明 4 是可以取到的最大 MEX 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [1,-10,7,13,6,8], value = 7
<strong>输出：</strong>2
<strong>解释：</strong>执行下述操作可以得到这一结果：
- nums[2] 减去 value 一次，nums = [1,-10,<em><strong>0</strong></em>,13,6,8]
nums 的 MEX 是 2 。可以证明 2 是可以取到的最大 MEX 。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length, value &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

**方法一：计数**

我们用哈希表或数组cnt统计数组中每个数对value取模后的余数的个数。

然后从0开始遍历，对于当前遍历到的数i，如果cnt[i \bmod value]为0，说明数组中不存在一个数对value取模后的余数为i，那么i就是数组的 MEX，直接返回即可。否则，将cnt[i \bmod value]减1，继续遍历。

时间复杂度O(n)，空间复杂度O(value)。其中n为数组nums的长度。

### **Java**

```java
class Solution {
    public int findSmallestInteger(int[] nums, int value) {
        int[] cnt = new int[value];
        for (int x : nums) {
            ++cnt[(x % value + value) % value];
        }
        for (int i = 0;; ++i) {
            if (cnt[i % value]-- == 0) {
                return i;
            }
        }
    }
}
```
