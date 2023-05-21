# [1338. 数组大小减半](https://leetcode.cn/problems/reduce-array-size-to-the-half)

## 题目描述

<p>给你一个整数数组&nbsp;<code>arr</code>。你可以从中选出一个整数集合，并删除这些整数在数组中的每次出现。</p>

<p>返回&nbsp;<strong>至少</strong>&nbsp;能删除数组中的一半整数的整数集合的最小大小。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [3,3,3,3,5,5,5,2,2,7]
<strong>输出：</strong>2
<strong>解释：</strong>选择 {3,7} 使得结果数组为 [5,5,5,2,2]、长度为 5（原数组长度的一半）。
大小为 2 的可行集合有 {3,5},{3,2},{5,2}。
选择 {2,7} 是不可行的，它的结果数组为 [3,3,3,3,5,5,5]，新数组长度大于原数组的二分之一。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [7,7,7,7,7,7]
<strong>输出：</strong>1
<strong>解释：</strong>我们只能选择集合 {7}，结果数组为空。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>5</sup></code></li>
	<li><code>arr.length</code>&nbsp;为偶数</li>
	<li><code>1 &lt;= arr[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

**方法一：计数 + 排序**

我们可以用哈希表或数组cnt统计数组arr中每个数字出现的次数，然后将cnt中的数字从大到小排序，从大到小遍历cnt，每次遍历将当前数字x加入答案，并将m加上x，如果m \geq \frac{n}{2}，则返回答案。

时间复杂度O(n \times \log n)，空间复杂度O(n)。其中n为数组arr的长度。

### **Java**

```java
class Solution {
    public int minSetSize(int[] arr) {
        int mx = 0;
        for (int x : arr) {
            mx = Math.max(mx, x);
        }
        int[] cnt = new int[mx + 1];
        for (int x : arr) {
            ++cnt[x];
        }
        Arrays.sort(cnt);
        int ans = 0;
        int m = 0;
        for (int i = mx;; --i) {
            if (cnt[i] > 0) {
                m += cnt[i];
                ++ans;
                if (m * 2 >= arr.length) {
                    return ans;
                }
            }
        }
    }
}
```
