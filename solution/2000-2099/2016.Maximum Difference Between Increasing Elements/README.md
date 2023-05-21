# [2016. 增量元素之间的最大差值](https://leetcode.cn/problems/maximum-difference-between-increasing-elements)

## 题目描述

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> ，该数组的大小为 <code>n</code> ，请你计算 <code>nums[j] - nums[i]</code> 能求得的 <strong>最大差值 </strong>，其中 <code>0 &lt;= i &lt; j &lt; n</code> 且 <code>nums[i] &lt; nums[j]</code> 。</p>

<p>返回 <strong>最大差值</strong> 。如果不存在满足要求的 <code>i</code> 和 <code>j</code> ，返回 <code>-1</code> 。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [7,<em><strong>1</strong></em>,<em><strong>5</strong></em>,4]
<strong>输出：</strong>4
<strong>解释：</strong>
最大差值出现在 i = 1 且 j = 2 时，nums[j] - nums[i] = 5 - 1 = 4 。
注意，尽管 i = 1 且 j = 0 时 ，nums[j] - nums[i] = 7 - 1 = 6 &gt; 4 ，但 i &gt; j 不满足题面要求，所以 6 不是有效的答案。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [9,4,3,2]
<strong>输出：</strong>-1
<strong>解释：</strong>
不存在同时满足 i &lt; j 和 nums[i] &lt; nums[j] 这两个条件的 i, j 组合。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [<em><strong>1</strong></em>,5,2,<em><strong>10</strong></em>]
<strong>输出：</strong>9
<strong>解释：</strong>
最大差值出现在 i = 0 且 j = 3 时，nums[j] - nums[i] = 10 - 1 = 9 。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>2 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

**方法一：维护前缀最小值**

我们用变量mi表示当前遍历到的元素中的最小值，用变量ans表示最大差值，初始时mi为+\infty，而ans为-1。

遍历数组，对于当前遍历到的元素x，如果x \gt mi，则更新ans为max(ans, x - mi)，否则更新mi = x。

遍历结束后，返回ans。

时间复杂度O(n)，空间复杂度O(1)。其中n为数组的长度。

### **Java**

```java
class Solution {
    public int maximumDifference(int[] nums) {
        int mi = 1 << 30;
        int ans = -1;
        for (int x : nums) {
            if (x > mi) {
                ans = Math.max(ans, x - mi);
            } else {
                mi = x;
            }
        }
        return ans;
    }
}
```
