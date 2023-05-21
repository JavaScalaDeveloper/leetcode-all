# [2422. 使用合并操作将数组转换为回文序列](https://leetcode.cn/problems/merge-operations-to-turn-array-into-a-palindrome)

## 题目描述

<p>给定一个由&nbsp;<strong>正整数&nbsp;</strong>组成的数组 <code>nums</code>。</p>

<p>可以对阵列执行如下操作，<strong>次数不限</strong>:</p>

<ul>
	<li>选择任意两个&nbsp;<strong>相邻&nbsp;</strong>的元素并用它们的&nbsp;<strong>和</strong>&nbsp;<strong>替换&nbsp;</strong>它们。

    <ul>
    	<li>例如，如果 <code>nums = [1,<u>2,3</u>,1]</code>，则可以应用一个操作使其变为 <code>[1,5,1]</code>。</li>
    </ul>
    </li>

</ul>

<p>返回<em>将数组转换为&nbsp;<strong>回文序列&nbsp;</strong>所需的&nbsp;<strong>最小&nbsp;</strong>操作数。</em></p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [4,3,2,1,2,3,1]
<strong>输出:</strong> 2
<strong>解释:</strong> 我们可以通过以下 2 个操作将数组转换为回文:
- 在数组的第 4 和第 5 个元素上应用该操作，nums 将等于 [4,3,2,<strong><u>3</u></strong>,3,1].
- 在数组的第 5 和第 6 个元素上应用该操作，nums 将等于 [4,3,2,3,<strong><u>4</u></strong>].
数组 [4,3,2,3,4] 是一个回文序列。
可以证明，2 是所需的最小操作数。
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入:</strong> nums = [1,2,3,4]
<strong>输出:</strong> 3
<strong>解释:</strong> 我们在任意位置进行 3 次运算，最后得到数组 [10]，它是一个回文序列。
</pre>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

**方法一：贪心 + 双指针**

定义两个指针i和j，分别指向数组的首尾，用变量a和b分别表示首尾两个元素的值，变量ans表示操作次数。

如果a \lt b，我们将指针i向右移动一位，即i \leftarrow i + 1，然后将a加上指针i指向的元素的值，即a \leftarrow a + nums[i]，同时将操作次数加一，即ans \leftarrow ans + 1。

如果a \gt b，我们将指针j向左移动一位，即j \leftarrow j - 1，然后将b加上指针j指向的元素的值，即b \leftarrow b + nums[j]，同时将操作次数加一，即ans \leftarrow ans + 1。

否则，说明a = b，此时我们将指针i向右移动一位，即i \leftarrow i + 1，将指针j向左移动一位，即j \leftarrow j - 1，并且更新a和b的值，即a \leftarrow nums[i]以及b \leftarrow nums[j]。

循环上述过程，直至指针i \ge j，返回操作次数ans即可。

时间复杂度O(n)，空间复杂度O(1)。其中n为数组的长度。

### **Java**

```java
class Solution {
    public int minimumOperations(int[] nums) {
        int i = 0, j = nums.length - 1;
        long a = nums[i], b = nums[j];
        int ans = 0;
        while (i < j) {
            if (a < b) {
                a += nums[++i];
                ++ans;
            } else if (b < a) {
                b += nums[--j];
                ++ans;
            } else {
                a = nums[++i];
                b = nums[--j];
            }
        }
        return ans;
    }
}
```
