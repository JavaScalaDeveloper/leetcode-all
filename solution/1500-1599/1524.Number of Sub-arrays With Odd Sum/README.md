# [1524. 和为奇数的子数组数目](https://leetcode.cn/problems/number-of-sub-arrays-with-odd-sum)

## 题目描述

<p>给你一个整数数组&nbsp;<code>arr</code>&nbsp;。请你返回和为 <strong>奇数</strong>&nbsp;的子数组数目。</p>

<p>由于答案可能会很大，请你将结果对&nbsp;<code>10^9 + 7</code>&nbsp;取余后返回。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [1,3,5]
<strong>输出：</strong>4
<strong>解释：</strong>所有的子数组为 [[1],[1,3],[1,3,5],[3],[3,5],[5]] 。
所有子数组的和为 [1,4,9,3,8,5].
奇数和包括 [1,9,3,5] ，所以答案为 4 。
</pre>

<p><strong>示例 2 ：</strong></p>

<pre><strong>输入：</strong>arr = [2,4,6]
<strong>输出：</strong>0
<strong>解释：</strong>所有子数组为 [[2],[2,4],[2,4,6],[4],[4,6],[6]] 。
所有子数组和为 [2,6,12,4,10,6] 。
所有子数组和都是偶数，所以答案为 0 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>arr = [1,2,3,4,5,6,7]
<strong>输出：</strong>16
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>arr = [100,100,99,99]
<strong>输出：</strong>4
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>arr = [7]
<strong>输出：</strong>1
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10^5</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 100</code></li>
</ul>

## 解法

**方法一：前缀和 + 计数器**

我们定义一个长度为2的数组cnt作为计数器，其中cnt[0]和cnt[1]分别表示前缀和为偶数和奇数的子数组的个数。初始时cnt[0] = 1，而cnt[1] = 0。

接下来，我们维护当前的前缀和s，初始时s = 0。

遍历数组arr，对于遍历到的每个元素x，我们将s的值加上x，然后根据s的奇偶性，将cnt[s \mod 2 \oplus 1]的值累加到答案中，然后我们将cnt[s \mod 2]的值加1。

遍历结束后，我们即可得到答案。注意答案的取模运算。

时间复杂度O(n)，空间复杂度O(1)。其中n为数组arr的长度。

### **Java**

```java
class Solution {
    public int numOfSubarrays(int[] arr) {
        final int mod = (int) 1e9 + 7;
        int[] cnt = {1, 0};
        int ans = 0, s = 0;
        for (int x : arr) {
            s += x;
            ans = (ans + cnt[s & 1 ^ 1]) % mod;
            ++cnt[s & 1];
        }
        return ans;
    }
}
```
