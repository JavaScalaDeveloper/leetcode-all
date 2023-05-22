# [80. 删除有序数组中的重复项 II](https://leetcode.cn/problems/remove-duplicates-from-sorted-array-ii)

## 题目描述

<p>给你一个有序数组 <code>nums</code> ，请你<strong><a href="http://baike.baidu.com/item/%E5%8E%9F%E5%9C%B0%E7%AE%97%E6%B3%95" target="_blank"> 原地</a></strong> 删除重复出现的元素，使得出现次数超过两次的元素<strong>只出现两次</strong> ，返回删除后数组的新长度。</p>

<p>不要使用额外的数组空间，你必须在 <strong><a href="https://baike.baidu.com/item/%E5%8E%9F%E5%9C%B0%E7%AE%97%E6%B3%95" target="_blank">原地 </a>修改输入数组 </strong>并在使用 O(1) 额外空间的条件下完成。</p>

<p><strong>说明：</strong></p>

<p>为什么返回数值是整数，但输出的答案是数组呢？</p>

<p>请注意，输入数组是以<strong>「引用」</strong>方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。</p>

<p>你可以想象内部操作如下:</p>

<pre>
// <strong>nums</strong> 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
int len = removeDuplicates(nums);

// 在函数里修改输入数组对于调用者是可见的。
// 根据你的函数返回的长度, 它会打印出数组中<strong> 该长度范围内</strong> 的所有元素。
for (int i = 0; i &lt; len; i++) {
&nbsp; &nbsp; print(nums[i]);
}
</pre>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,1,2,2,3]
<strong>输出：</strong>5, nums = [1,1,2,2,3]
<strong>解释：</strong>函数应返回新长度 length = <strong><code>5</code></strong>, 并且原数组的前五个元素被修改为 <strong><code>1, 1, 2, 2,</code></strong> <strong>3 </strong>。 不需要考虑数组中超出新长度后面的元素。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,0,1,1,1,1,2,3,3]
<strong>输出：</strong>7, nums = [0,0,1,1,2,3,3]
<strong>解释：</strong>函数应返回新长度 length = <strong><code>7</code></strong>, 并且原数组的前五个元素被修改为&nbsp;<strong><code>0</code></strong>, <strong>0</strong>, <strong>1</strong>, <strong>1</strong>, <strong>2</strong>, <strong>3</strong>, <strong>3 。</strong> 不需要考虑数组中超出新长度后面的元素。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>nums</code> 已按升序排列</li>
</ul>

## 解法

**方法一：一次遍历**

我们用一个变量k记录当前已经处理好的数组的长度，初始时k=0，表示空数组。

然后我们从左到右遍历数组，对于遍历到的每个元素x，如果k < 2或者x \neq nums[k-2]，我们就将x放到nums[k]的位置，然后k自增1。否则，x与nums[k-2]相同，我们直接跳过这个元素。继续遍历，直到遍历完整个数组。

这样，当遍历结束时，nums中前k个元素就是我们要求的答案，且k就是答案的长度。

时间复杂度O(n)，空间复杂度O(1)。其中n为数组的长度。

补充：

原问题要求最多相同的数字最多出现2次，我们可以扩展至相同的数字最多保留k个。

-   由于相同的数字最多保留k个，那么原数组的前k个元素我们可以直接保留；
-   对于后面的数字，能够保留的前提是：当前数字x与前面已保留的数字的倒数第k个元素比较，不同则保留，相同则跳过。

相似题目：[26. 删除有序数组中的重复项](/solution/0000-0099/0026.Remove%20Duplicates%20from%20Sorted%20Array/README.md)

### **Java**

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int k = 0;
        for (int x : nums) {
            if (k < 2 || x != nums[k - 2]) {
                nums[k++] = x;
            }
        }
        return k;
    }
}
```
