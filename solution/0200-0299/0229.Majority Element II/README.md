# [229. 多数元素 II](https://leetcode.cn/problems/majority-element-ii)

[English Version](/solution/0200-0299/0229.Majority%20Element%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个大小为&nbsp;<em>n&nbsp;</em>的整数数组，找出其中所有出现超过&nbsp;<code>⌊ n/3 ⌋</code>&nbsp;次的元素。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,2,3]
<strong>输出：</strong>[3]</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1]
<strong>输出：</strong>[1]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2]
<strong>输出：</strong>[1,2]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>尝试设计时间复杂度为 O(n)、空间复杂度为 O(1)的算法解决此问题。</p>

## 解法

摩尔投票法。

### **Java**

```java
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int n1 = 0, n2 = 0;
        int m1 = 0, m2 = 1;
        for (int m : nums) {
            if (m == m1) {
                ++n1;
            } else if (m == m2) {
                ++n2;
            } else if (n1 == 0) {
                m1 = m;
                ++n1;
            } else if (n2 == 0) {
                m2 = m;
                ++n2;
            } else {
                --n1;
                --n2;
            }
        }
        List<Integer> ans = new ArrayList<>();
        n1 = 0;
        n2 = 0;
        for (int m : nums) {
            if (m == m1) {
                ++n1;
            } else if (m == m2) {
                ++n2;
            }
        }
        if (n1 > nums.length / 3) {
            ans.add(m1);
        }
        if (n2 > nums.length / 3) {
            ans.add(m2);
        }
        return ans;
    }
}
```
