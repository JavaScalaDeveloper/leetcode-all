# [624. 数组列表中的最大距离](https://leetcode.cn/problems/maximum-distance-in-arrays)

[English Version](/solution/0600-0699/0624.Maximum%20Distance%20in%20Arrays/README_EN.md)

## 题目描述

<p>给定&nbsp;<code>m</code>&nbsp;个数组，每个数组都已经按照升序排好序了。现在你需要从两个不同的数组中选择两个整数（每个数组选一个）并且计算它们的距离。两个整数&nbsp;<code>a</code>&nbsp;和&nbsp;<code>b</code>&nbsp;之间的距离定义为它们差的绝对值&nbsp;<code>|a-b|</code>&nbsp;。你的任务就是去找到最大距离</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong> 
[[1,2,3],
 [4,5],
 [1,2,3]]
<strong>输出：</strong> 4
<strong>解释：</strong>
一种得到答案 4 的方法是从第一个数组或者第三个数组中选择 1，同时从第二个数组中选择 5 。
</pre>

<p>&nbsp;</p>

<p><strong>注意：</strong></p>

<ol>
	<li>每个给定数组至少会有 1 个数字。列表中至少有两个非空数组。</li>
	<li><strong>所有</strong>&nbsp;<code>m</code>&nbsp;个数组中的数字总数目在范围 [2, 10000] 内。</li>
	<li><code>m</code>&nbsp;个数组中所有整数的范围在 [-10000, 10000] 内。</li>
</ol>

<p>&nbsp;</p>

## 解法

**方法一：维护最大值和最小值**

我们注意到，最大距离一定是两个数组中的一个最大值和另一个最小值之间的距离。因此，我们可以维护两个变量，分别表示当前数组中的最大值和最小值，然后遍历数组，更新最大距离，同时更新最大值和最小值。

遍历结束后，即可得到最大距离。

时间复杂度 $O(m)$，空间复杂度 $O(1)$。其中 $m$ 为数组的个数。

### **Java**

```java
class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        int ans = 0;
        int mi = arrays.get(0).get(0);
        int mx = arrays.get(0).get(arrays.get(0).size() - 1);
        for (int i = 1; i < arrays.size(); ++i) {
            var arr = arrays.get(i);
            int a = Math.abs(arr.get(0) - mx);
            int b = Math.abs(arr.get(arr.size() - 1) - mi);
            ans = Math.max(ans, Math.max(a, b));
            mi = Math.min(mi, arr.get(0));
            mx = Math.max(mx, arr.get(arr.size() - 1));
        }
        return ans;
    }
}
```
