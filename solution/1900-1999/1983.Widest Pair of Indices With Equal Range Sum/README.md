# [1983. 范围和相等的最宽索引对](https://leetcode.cn/problems/widest-pair-of-indices-with-equal-range-sum)

## 题目描述

<p>给定两个 <strong>以0为索引</strong> 的二进制数组 <code>nums1</code> 和 <code>nums2</code> 。找出 <strong>最宽</strong> 的索引对 <code>(i, j)</code> ，使的&nbsp;<code>i &lt;= j</code>&nbsp;并且&nbsp;<code>nums1[i] + nums1[i+1] + ... + nums1[j] == nums2[i] + nums2[i+1] + ... + nums2[j]</code>。</p>

<p><strong>最宽</strong> 的指标对是指在 <code>i </code>和<code> j </code>之间的 <strong>距离最大</strong> 的指标对。一对指标之间的 <strong>距离</strong> 定义为<code> j - i + 1</code> 。</p>

<p>返回 <strong>最宽</strong> 索引对的 <strong>距离</strong> 。如果没有满足条件的索引对，则返回 <code>0</code> 。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums1 = [1,1,0,1], nums2 = [0,1,1,0]
<strong>输出:</strong> 3
<strong>解释:</strong>
如果i = 1, j = 3:
Nums1 [1] + Nums1 [2] + Nums1[3] = 1 + 0 + 1 = 2。
Nums2 [1] + Nums2 [2] + Nums2[3] = 1 + 1 + 0 = 2。
i和j之间的距离是j - i + 1 = 3 - 1 + 1 = 3。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums1 = [0,1], nums2 = [1,1]
<strong>输出:</strong> 1
<strong>解释:
</strong>If i = 1 and j = 1:
nums1[1] = 1。
nums2[1] = 1。
i和j之间的距离是j - i + 1 = 1 - 1 + 1 = 1。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> nums1 = [0], nums2 = [1]
<strong>输出:</strong> 0
<strong>解释:
</strong>没有满足要求的索引对。
</pre>

<p><strong>提示:</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>nums1[i]</code>&nbsp;仅为&nbsp;<code>0</code>&nbsp;或&nbsp;<code>1</code>.</li>
	<li><code>nums2[i]</code>&nbsp;仅为&nbsp;<code>0</code>&nbsp;或&nbsp;<code>1</code>.</li>
</ul>

## 解法

**方法一：前缀和 + 哈希表**

我们观察到，对于任意的索引对(i, j)，如果nums1[i] + nums1[i+1] + ... + nums1[j] = nums2[i] + nums2[i+1] + ... + nums2[j]，那么nums1[i] - nums2[i] + nums1[i+1] - nums2[i+1] + ... + nums1[j] - nums2[j] = 0。如果我们将数组nums1与数组nums2对应位置的元素相减，得到一个新的数组nums，那么问题转换为在数组nums中找到一个最长的子数组，使得子数组的和为0。这可以通过前缀和 + 哈希表的方法求解。

我们定义一个变量s表示当前nums的前缀和，用一个哈希表d保存每个前缀和第一次出现的位置。初始时s = 0,d[0] = -1。

接下来，我们遍历数组nums中的每个元素x，计算s的值，然后检查哈希表中是否存在s，如果哈希表存在s，那么说明存在一个子数组nums[d[s]+1,..i]，使得子数组的和为0，我们更新答案为max(ans, i - d[s])。否则，我们将s的值加入哈希表中，表示s第一次出现的位置为i。

遍历结束，即可得到最终的答案。

时间复杂度O(n)，空间复杂度O(n)。其中n为数组nums的长度。

### **Java**

```java
class Solution {
    public int widestPairOfIndices(int[] nums1, int[] nums2) {
        Map<Integer, Integer> d = new HashMap<>();
        d.put(0, -1);
        int n = nums1.length;
        int s = 0;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            s += nums1[i] - nums2[i];
            if (d.containsKey(s)) {
                ans = Math.max(ans, i - d.get(s));
            } else {
                d.put(s, i);
            }
        }
        return ans;
    }
}
```
