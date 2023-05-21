# [1027. 最长等差数列](https://leetcode.cn/problems/longest-arithmetic-subsequence)

## 题目描述

<p>给你一个整数数组&nbsp;<code>nums</code>，返回 <code>nums</code>&nbsp;中最长等差子序列的<strong>长度</strong>。</p>

<p>回想一下，<code>nums</code> 的子序列是一个列表&nbsp;<code>nums[i<sub>1</sub>], nums[i<sub>2</sub>], ..., nums[i<sub>k</sub>]</code> ，且&nbsp;<code>0 &lt;= i<sub>1</sub> &lt; i<sub>2</sub> &lt; ... &lt; i<sub>k</sub> &lt;= nums.length - 1</code>。并且如果&nbsp;<code>seq[i+1] - seq[i]</code>(&nbsp;<code>0 &lt;= i &lt; seq.length - 1</code>) 的值都相同，那么序列&nbsp;<code>seq</code>&nbsp;是等差的。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,6,9,12]
<strong>输出：</strong>4
<strong>解释： </strong>
整个数组是公差为 3 的等差数列。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [9,4,7,2,10]
<strong>输出：</strong>3
<strong>解释：</strong>
最长的等差子序列是 [4,7,10]。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [20,1,15,3,10,5,8]
<strong>输出：</strong>4
<strong>解释：</strong>
最长的等差子序列是 [20,15,10,5]。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 500</code></li>
</ul>

## 解法

**方法一：动态规划**

我们定义f[i][j]表示以nums[i]结尾且公差为j的等差数列的最大长度。初始时f[i][j]=1，即每个元素自身都是一个长度为1的等差数列。

> 由于公差可能为负数，且最大差值为500，因此，我们可以将统一将公差加上500，这样公差的范围就变成了[0, 1000]。

考虑f[i]，我们可以枚举nums[i]的前一个元素nums[k]，那么公差j=nums[i]-nums[k]+500，此时有f[i][j]=\max(f[i][j], f[k][j]+1)，然后我们更新答案ans=\max(ans, f[i][j])。

最后返回答案即可。

> 如果初始时f[i][j]=0，那么我们需要在最后返回答案时加上1。

时间复杂度O(n \times (d + n))，空间复杂度O(n \times d)。其中n和d分别是数组nums的长度以及数组nums中元素的最大值与最小值的差值。

### **Java**

```java
class Solution {
    public int longestArithSeqLength(int[] nums) {
        int n = nums.length;
        int ans = 0;
        int[][] f = new int[n][1001];
        for (int i = 1; i < n; ++i) {
            for (int k = 0; k < i; ++k) {
                int j = nums[i] - nums[k] + 500;
                f[i][j] = Math.max(f[i][j], f[k][j] + 1);
                ans = Math.max(ans, f[i][j]);
            }
        }
        return ans + 1;
    }
}
```
