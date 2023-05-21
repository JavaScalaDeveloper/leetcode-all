# [1911. 最大子序列交替和](https://leetcode.cn/problems/maximum-alternating-subsequence-sum)

## 题目描述

<p>一个下标从 <strong>0</strong> 开始的数组的 <strong>交替和</strong> 定义为 <strong>偶数</strong> 下标处元素之 <strong>和</strong> 减去 <strong>奇数</strong> 下标处元素之 <strong>和</strong> 。</p>

<ul>
	<li>比方说，数组 <code>[4,2,5,3]</code> 的交替和为 <code>(4 + 5) - (2 + 3) = 4</code> 。</li>
</ul>

<p>给你一个数组 <code>nums</code> ，请你返回 <code>nums</code> 中任意子序列的 <strong>最大交替和</strong> （子序列的下标 <strong>重新</strong> 从 0 开始编号）。</p>

<ul>
</ul>

<p>一个数组的 <strong>子序列</strong> 是从原数组中删除一些元素后（也可能一个也不删除）剩余元素不改变顺序组成的数组。比方说，<code>[2,7,4]</code> 是 <code>[4,<strong>2</strong>,3,<strong>7</strong>,2,1,<strong>4</strong>]</code> 的一个子序列（加粗元素），但是 <code>[2,4,2]</code> 不是。</p>



<p><b>示例 1：</b></p>

<pre><b>输入：</b>nums = [<strong>4</strong>,<strong>2</strong>,<strong>5</strong>,3]
<b>输出：</b>7
<b>解释：</b>最优子序列为 [4,2,5] ，交替和为 (4 + 5) - 2 = 7 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [5,6,7,<strong>8</strong>]
<b>输出：</b>8
<b>解释：</b>最优子序列为 [8] ，交替和为 8 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>nums = [<strong>6</strong>,2,<strong>1</strong>,2,4,<strong>5</strong>]
<b>输出：</b>10
<b>解释：</b>最优子序列为 [6,1,5] ，交替和为 (6 + 5) - 1 = 10 。
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

**方法一：动态规划**

我们定义f[i]表示从前i个元素中选出的子序列，且最后一个元素为奇数下标时的最大交替和，定义g[i]表示从前i个元素中选出的子序列，且最后一个元素为偶数下标时的最大交替和。初始时f[0] = g[0] = 0。答案为max(f[n], g[n])。

我们考虑第i个元素nums[i - 1]：

如果选取该元素且该元素为奇数下标，那么上一个元素必须为偶数下标，且只能从前i-1个元素中选取，因此f[i] = g[i - 1] - nums[i - 1]；如果不选取该元素，那么f[i] = f[i - 1]。

同理，如果选取该元素且该元素为偶数下标，那么上一个元素必须为奇数下标，且只能从前i-1个元素中选取，因此g[i] = f[i - 1] + nums[i - 1]；如果不选取该元素，那么g[i] = g[i - 1]。

综上，我们可以得到状态转移方程：


\begin{aligned}
f[i] &= max(g[i - 1] - nums[i - 1], f[i - 1]) \\
g[i] &= max(f[i - 1] + nums[i - 1], g[i - 1])
\end{aligned}


最终答案为max(f[n], g[n])。

我们注意到f[i]和g[i]只与f[i - 1]和g[i - 1]有关，因此我们可以使用两个变量代替数组，将空间复杂度降低到O(1)。

时间复杂度O(n)，其中n为数组长度。

### **Java**

```java
class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] f = new long[n + 1];
        long[] g = new long[n + 1];
        for (int i = 1; i <= n; ++i) {
            f[i] = Math.max(g[i - 1] - nums[i - 1], f[i - 1]);
            g[i] = Math.max(f[i - 1] + nums[i - 1], g[i - 1]);
        }
        return Math.max(f[n], g[n]);
    }
}
```

```java
class Solution {
    public long maxAlternatingSum(int[] nums) {
        long f = 0, g = 0;
        for (int x : nums) {
            long ff = Math.max(g - x, f);
            long gg = Math.max(f + x, g);
            f = ff;
            g = gg;
        }
        return Math.max(f, g);
    }
}
```
