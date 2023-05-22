# [面试题 53 - II. 0 ～ n-1 中缺失的数字](https://leetcode.cn/problems/que-shi-de-shu-zi-lcof/)

## 题目描述

<p>一个长度为 n-1 的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。</p>



<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> [0,1,3]
<strong>输出:</strong> 2
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre><strong>输入:</strong> [0,1,2,3,4,5,6,7,9]
<strong>输出:</strong> 8</pre>



<p><strong>限制：</strong></p>

<p><code>1 &lt;= 数组长度 &lt;= 10000</code></p>

## 解法

**方法一：二分查找**

我们可以使用二分查找的方法找到这个缺失的数字。初始化左边界 $l=0$，右边界 $r=n$，其中 $n$ 是数组的长度。

每次计算中间元素的下标 $mid$，如果 $nums[mid] \gt mid$，则缺失的数字一定在区间 $[l,..mid]$ 中，否则缺失的数字一定在区间 $[mid+1,..r]$ 中。

最后返回左边界 $l$ 即可。

时间复杂度 $O(\log n)$，空间复杂度 $O(1)$。其中 $n$ 是数组的长度。

### **Java**

```java
class Solution {
    public int missingNumber(int[] nums) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] > mid) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```
