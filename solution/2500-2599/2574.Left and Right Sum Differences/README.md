# [2574. 左右元素和的差值](https://leetcode.cn/problems/left-and-right-sum-differences)

## 题目描述

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> ，请你找出一个下标从 <strong>0</strong> 开始的整数数组 <code>answer</code> ，其中：</p>

<ul>
	<li><code>answer.length == nums.length</code></li>
	<li><code>answer[i] = |leftSum[i] - rightSum[i]|</code></li>
</ul>

<p>其中：</p>

<ul>
	<li><code>leftSum[i]</code> 是数组 <code>nums</code> 中下标 <code>i</code> 左侧元素之和。如果不存在对应的元素，<code>leftSum[i] = 0</code> 。</li>
	<li><code>rightSum[i]</code> 是数组 <code>nums</code> 中下标 <code>i</code> 右侧元素之和。如果不存在对应的元素，<code>rightSum[i] = 0</code> 。</li>
</ul>

<p>返回数组 <code>answer</code> 。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [10,4,8,3]
<strong>输出：</strong>[15,1,11,22]
<strong>解释：</strong>数组 leftSum 为 [0,10,14,22] 且数组 rightSum 为 [15,11,3,0] 。
数组 answer 为 [|0 - 15|,|10 - 11|,|14 - 3|,|22 - 0|] = [15,1,11,22] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [1]
<strong>输出：</strong>[0]
<strong>解释：</strong>数组 leftSum 为 [0] 且数组 rightSum 为 [0] 。
数组 answer 为 [|0 - 0|] = [0] 。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

**方法一：前缀和**

我们定义变量left表示数组 `nums` 中下标i左侧元素之和，变量right表示数组 `nums` 中下标i右侧元素之和。初始时left = 0,right = \sum_{i = 0}^{n - 1} nums[i]。

遍历数组 `nums`，对于当前遍历到的数字x，我们更新right = right - x，此时left和right分别表示数组 `nums` 中下标i左侧元素之和和右侧元素之和。我们将left和right的差的绝对值加入答案数组 `ans` 中，然后更新left = left + x。

遍历完成后，返回答案数组 `ans` 即可。

时间复杂度O(n)，空间复杂度O(1)。其中n为数组 `nums` 的长度。

相似题目：

-   [0724. 寻找数组的中心下标](/solution/0700-0799/0724.Find%20Pivot%20Index/README.md)
-   [1991. 找到数组的中间位置](/solution/1900-1999/1991.Find%20the%20Middle%20Index%20in%20Array/README.md)

### **Java**

```java
class Solution {
    public int[] leftRigthDifference(int[] nums) {
        int left = 0, right = Arrays.stream(nums).sum();
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            right -= nums[i];
            ans[i] = Math.abs(left - right);
            left += nums[i];
        }
        return ans;
    }
}
```

**
