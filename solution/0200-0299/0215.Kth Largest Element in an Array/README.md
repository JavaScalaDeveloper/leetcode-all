# [215. 数组中的第 K 个最大元素](https://leetcode.cn/problems/kth-largest-element-in-an-array)

## 题目描述

<p>给定整数数组 <code>nums</code> 和整数 <code>k</code>，请返回数组中第 <code><strong>k</strong></code> 个最大的元素。</p>

<p>请注意，你需要找的是数组排序后的第 <code>k</code> 个最大的元素，而不是第 <code>k</code> 个不同的元素。</p>

<p>你必须设计并实现时间复杂度为 <code>O(n)</code> 的算法解决此问题。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> <code>[3,2,1,5,6,4],</code> k = 2
<strong>输出:</strong> 5
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入:</strong> <code>[3,2,3,1,2,4,5,5,6], </code>k = 4
<strong>输出:</strong> 4</pre>

<p><strong>提示： </strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup>&nbsp;&lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

**方法一：排序**

将数组nums升序排列，然后获取nums[n-k]。

时间复杂度O(nlogn)，其中n表示数组nums的长度。

**方法二：partition**

并不是所有时候，都需要整个数组进入有序状态，只需要**局部有序**，或者说，从大到小排序，只要[0..k)位置的元素有序，那么就能确定结果，此处使用**快速排序**。

快速排序有一特点，每一次循环结束时，能够确定的是：partition一定处于它该处于的索引位置。从而根据它得知，结果值是在左数组还是在右数组当中，然后对那一数组进行排序即可。

时间复杂度O(n)，其中n表示数组nums的长度。

### **Java**

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        return quickSort(nums, 0, n - 1, n - k);
    }

    private int quickSort(int[] nums, int left, int right, int k) {
        if (left == right) {
            return nums[left];
        }
        int i = left - 1, j = right + 1;
        int x = nums[(left + right) >>> 1];
        while (i < j) {
            while (nums[++i] < x)
                ;
            while (nums[--j] > x)
                ;
            if (i < j) {
                int t = nums[i];
                nums[i] = nums[j];
                nums[j] = t;
            }
        }
        if (j < k) {
            return quickSort(nums, j + 1, right, k);
        }
        return quickSort(nums, left, j, k);
    }
}
```
