# [932. 漂亮数组](https://leetcode.cn/problems/beautiful-array)

## 题目描述

<p>如果长度为 <code>n</code> 的数组 <code>nums</code> 满足下述条件，则认为该数组是一个 <strong>漂亮数组</strong> ：</p>

<ul>
	<li><code>nums</code> 是由范围 <code>[1, n]</code> 的整数组成的一个排列。</li>
	<li>对于每个 <code>0 &lt;= i &lt; j &lt; n</code> ，均不存在下标 <code>k</code>（<code>i &lt; k &lt; j</code>）使得 <code>2 * nums[k] == nums[i] + nums[j]</code> 。</li>
</ul>

<p>给你整数 <code>n</code> ，返回长度为 <code>n</code> 的任一 <strong>漂亮数组</strong> 。本题保证对于给定的 <code>n</code> 至少存在一个有效答案。</p>

<p><strong class="example">示例 1 ：</strong></p>

<pre>
<strong>输入：</strong>n = 4
<strong>输出：</strong>[2,1,4,3]
</pre>

<p><strong class="example">示例 2 ：</strong></p>

<pre>
<strong>输入：</strong>n = 5
<strong>输出：</strong>[3,1,2,5,4]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

## 解法

**方法一：分治**

根据题意，漂亮数组A需要满足对于任意i<k<j,A_k*2 \neq A_i+A_j。

我们可以发现，不等式左侧一定是偶数，那么我们只要保证不等式右侧A_i和A_j分别是一奇一偶，那么不等式就恒成立。

利用分治，我们将n缩小规模为原来的一半，递归调用，可以得到两个漂亮数组left,right。我们将left中每个元素x_i变为x_i*2-1可以得到一个奇数数组；将right中每个元素x_i变为x_i*2，可以得到一个偶数数组。这两个数组仍然是漂亮数组。

> 基于一个性质，将漂亮数组中的每个元素x_i变换为kx_i+b，得到的数组仍然是漂亮数组。

将这两个漂亮数组合并在一起，由于满足一奇一偶，那么合并后的数组也是漂亮数组，从而得到了答案。

时间复杂度O(nlogn)。

### **Java**

```java
class Solution {
    public int[] beautifulArray(int n) {
        if (n == 1) {
            return new int[] {1};
        }
        int[] left = beautifulArray((n + 1) >> 1);
        int[] right = beautifulArray(n >> 1);
        int[] ans = new int[n];
        int i = 0;
        for (int x : left) {
            ans[i++] = x * 2 - 1;
        }
        for (int x : right) {
            ans[i++] = x * 2;
        }
        return ans;
    }
}
```
