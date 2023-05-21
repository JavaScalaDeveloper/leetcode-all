# [628. 三个数的最大乘积](https://leetcode.cn/problems/maximum-product-of-three-numbers)

## 题目描述

<p>给你一个整型数组 <code>nums</code> ，在数组中找出由三个数组成的最大乘积，并输出这个乘积。</p>



<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3]
<strong>输出：</strong>6
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,4]
<strong>输出：</strong>24
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [-1,-2,-3]
<strong>输出：</strong>-6
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>3 <= nums.length <= 10<sup>4</sup></code></li>
	<li><code>-1000 <= nums[i] <= 1000</code></li>
</ul>

## 解法

**方法一：排序 + 分类讨论**

我们先对数组nums进行排序，接下来分两种情况讨论：

-   如果nums中全是非负数或者全是非正数，那么答案即为最后三个数的乘积，即nums[n-1] × nums[n-2] × nums[n-3]；
-   如果nums中既有正数也有负数，那么答案可能是两个最小负数和一个最大整数的乘积，即nums[n-1] × nums[0] × nums[1]；也可能是最后三个数的乘积，即nums[n-1] × nums[n-2] × nums[n-3]。

最后返回两种情况的最大值即可。

时间复杂度O(n × log n)，空间复杂度O(log n)。其中n为数组nums的长度。

**方法二：一次遍历**

我们可以不用对数组进行排序，而是维护五个变量，其中mi1和mi2表示数组中最小的两个数，而mx1、mx2和mx3表示数组中最大的三个数。

最后返回max(mi1 × mi2 × mx1, mx1 × mx2 × mx3)即可。

时间复杂度O(n)，空间复杂度O(1)。

### **Java**

```java
class Solution {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int a = nums[n - 1] * nums[n - 2] * nums[n - 3];
        int b = nums[n - 1] * nums[0] * nums[1];
        return Math.max(a, b);
    }
}
```

```java
class Solution {
    public int maximumProduct(int[] nums) {
        final int inf = 1 << 30;
        int mi1 = inf, mi2 = inf;
        int mx1 = -inf, mx2 = -inf, mx3 = -inf;
        for (int x : nums) {
            if (x < mi1) {
                mi2 = mi1;
                mi1 = x;
            } else if (x < mi2) {
                mi2 = x;
            }
            if (x > mx1) {
                mx3 = mx2;
                mx2 = mx1;
                mx1 = x;
            } else if (x > mx2) {
                mx3 = mx2;
                mx2 = x;
            } else if (x > mx3) {
                mx3 = x;
            }
        }
        return Math.max(mi1 * mi2 * mx1, mx1 * mx2 * mx3);
    }
}
```
