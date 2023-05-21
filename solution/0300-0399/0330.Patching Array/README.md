# [330. 按要求补齐数组](https://leetcode.cn/problems/patching-array)

## 题目描述

<p>给定一个已排序的正整数数组 <code>nums</code>&nbsp;<em>，</em>和一个正整数&nbsp;<code>n</code><em> 。</em>从&nbsp;<code>[1, n]</code>&nbsp;区间内选取任意个数字补充到&nbsp;nums&nbsp;中，使得&nbsp;<code>[1, n]</code>&nbsp;区间内的任何数字都可以用&nbsp;nums&nbsp;中某几个数字的和来表示。</p>

<p>请返回 <em>满足上述要求的最少需要补充的数字个数</em>&nbsp;。</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入: </strong>nums = <code>[1,3]</code>, n = <code>6</code>
<strong>输出: </strong>1 
<strong>解释:</strong>
根据 nums&nbsp;里现有的组合&nbsp;<code>[1], [3], [1,3]</code>，可以得出&nbsp;<code>1, 3, 4</code>。
现在如果我们将&nbsp;<code>2</code>&nbsp;添加到&nbsp;nums 中，&nbsp;组合变为: <code>[1], [2], [3], [1,3], [2,3], [1,2,3]</code>。
其和可以表示数字&nbsp;<code>1, 2, 3, 4, 5, 6</code>，能够覆盖&nbsp;<code>[1, 6]</code>&nbsp;区间里所有的数。
所以我们最少需要添加一个数字。</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入: </strong>nums = <code>[1,5,10]</code>, n = <code>20</code>
<strong>输出:</strong> 2
<strong>解释: </strong>我们需要添加&nbsp;<code>[2,4]</code>。
</pre>

<p><strong>示例&nbsp;3:</strong></p>

<pre>
<strong>输入: </strong>nums = <code>[1,2,2]</code>, n = <code>5</code>
<strong>输出:</strong> 0
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>nums</code>&nbsp;按 <strong>升序排列</strong></li>
	<li><code>1 &lt;= n &lt;= 2<sup>31</sup>&nbsp;- 1</code></li>
</ul>

## 解法

**方法一：贪心**

我们假设数字x是最小的不能表示的正整数，那么[1,..x-1]的这些数都是可以表示的。为了能表示数字x，我们需要添加一个小于等于x的数：

-   如果添加的数等于x，由于[1,..x-1]的数都可以表示，添加x后，区间[1,..2x-1]内的数都可以表示，最小的不能表示的正整数变成了2x。
-   如果添加的数小于x，不妨设为x'，由于[1,..x-1]的数都可以表示，添加x'后，区间[1,..x+x'-1]内的数都可以表示，最小的不能表示的正整数变成了x+x' \lt 2x。

因此，我们应该贪心地添加数字x，这样可以覆盖的区间更大。

我们用一个变量x记录当前不能表示的最小正整数，初始化为1，此时[1,..x-1]是空的，表示当前没有任何数可以被覆盖；用一个变量i记录当前遍历到的数组下标。

循环进行以下操作：

-   如果i在数组范围内且nums[i] \le x，说明当前数字可以被覆盖，因此将x的值加上nums[i]，并将i的值加1。
-   否则，说明x没有被覆盖，因此需要在数组中补充一个数x，然后x更新为2x。
-   重复上述操作，直到x的值大于n。

最终答案即为补充的数的数量。

时间复杂度O(m + \log n)，其中m为数组nums的长度。空间复杂度O(1)。

### **Java**

```java
class Solution {
    public int minPatches(int[] nums, int n) {
        long x = 1;
        int ans = 0;
        for (int i = 0; x <= n;) {
            if (i < nums.length && nums[i] <= x) {
                x += nums[i++];
            } else {
                ++ans;
                x <<= 1;
            }
        }
        return ans;
    }
}
```
