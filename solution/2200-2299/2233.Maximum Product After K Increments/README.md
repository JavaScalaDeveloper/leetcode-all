# [2233. K 次增加后的最大乘积](https://leetcode.cn/problems/maximum-product-after-k-increments)

## 题目描述

<p>给你一个非负整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;。每次操作，你可以选择&nbsp;<code>nums</code>&nbsp;中 <strong>任一</strong>&nbsp;元素并将它 <strong>增加</strong>&nbsp;<code>1</code>&nbsp;。</p>

<p>请你返回 <strong>至多</strong>&nbsp;<code>k</code>&nbsp;次操作后，能得到的<em>&nbsp;</em><code>nums</code>的&nbsp;<strong>最大乘积</strong>&nbsp;。由于答案可能很大，请你将答案对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;取余后返回。</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [0,4], k = 5
<b>输出：</b>20
<b>解释：</b>将第一个数增加 5 次。
得到 nums = [5, 4] ，乘积为 5 * 4 = 20 。
可以证明 20 是能得到的最大乘积，所以我们返回 20 。
存在其他增加 nums 的方法，也能得到最大乘积。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [6,3,3,2], k = 2
<b>输出：</b>216
<b>解释：</b>将第二个数增加 1 次，将第四个数增加 1 次。
得到 nums = [6, 4, 3, 3] ，乘积为 6 * 4 * 3 * 3 = 216 。
可以证明 216 是能得到的最大乘积，所以我们返回 216 。
存在其他增加 nums 的方法，也能得到最大乘积。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length, k &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

**方法一：贪心 + 优先队列（小根堆）**

每次操作，贪心地选择最小的元素进行加1，共进行k次操作。最后累乘所有元素得到结果。注意取模操作。

时间复杂度O(n+klogn)。其中，n表示nums的长度。建堆的时间复杂度为O(n)，每次弹出最小元素进行加1，再放回堆中，时间复杂度为O(logn)，共进行k次操作。

### **Java**

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int maximumProduct(int[] nums, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int v : nums) {
            q.offer(v);
        }
        while (k-- > 0) {
            q.offer(q.poll() + 1);
        }
        long ans = 1;
        while (!q.isEmpty()) {
            ans = (ans * q.poll()) % MOD;
        }
        return (int) ans;
    }
}
```
