# [1512. 好数对的数目](https://leetcode.cn/problems/number-of-good-pairs)

## 题目描述

<p>给你一个整数数组 <code>nums</code> 。</p>

<p>如果一组数字 <code>(i,j)</code> 满足 <code>nums[i]</code> == <code>nums[j]</code> 且 <code>i</code> &lt; <code>j</code> ，就可以认为这是一组 <strong>好数对</strong> 。</p>

<p>返回好数对的数目。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,3,1,1,3]
<strong>输出：</strong>4
<strong>解释：</strong>有 4 组好数对，分别是 (0,3), (0,4), (3,4), (2,5) ，下标从 0 开始
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [1,1,1,1]
<strong>输出：</strong>6
<strong>解释：</strong>数组中的每组数字都是好数对</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,3]
<strong>输出：</strong>0
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## 解法

**方法一：计数**

遍历数组，对于每个元素 $x$，计算 $x$ 之前有多少个元素与其相等，即为 $x$ 与之前元素组成的好数对的数目。遍历完数组后，即可得到答案。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 为数组长度，而 $C$ 为数组中元素的取值范围。本题中 $C = 101$。

### **Java**

```java
class Solution {
    public int numIdenticalPairs(int[] nums) {
        int ans = 0;
        int[] cnt = new int[101];
        for (int x : nums) {
            ans += cnt[x]++;
        }
        return ans;
    }
}
```

```java
class Solution {
    public int numIdenticalPairs(int[] nums) {
        int[] cnt = new int[101];
        for (int x : nums) {
            ++cnt[x];
        }
        int ans = 0;
        for (int v : cnt) {
            ans += v * (v - 1) / 2;
        }
        return ans;
    }
}
```
