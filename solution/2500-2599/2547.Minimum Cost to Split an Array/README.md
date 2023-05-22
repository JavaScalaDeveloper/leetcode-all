# [2547. 拆分数组的最小代价](https://leetcode.cn/problems/minimum-cost-to-split-an-array)

## 题目描述

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code> 。</p>

<p>将数组拆分成一些非空子数组。拆分的 <strong>代价</strong> 是每个子数组中的 <strong>重要性</strong> 之和。</p>

<p>令 <code>trimmed(subarray)</code> 作为子数组的一个特征，其中所有仅出现一次的数字将会被移除。</p>

<ul>
	<li>例如，<code>trimmed([3,1,2,4,3,4]) = [3,4,3,4]</code> 。</li>
</ul>

<p>子数组的 <strong>重要性</strong> 定义为 <code>k + trimmed(subarray).length</code> 。</p>

<ul>
	<li>例如，如果一个子数组是 <code>[1,2,3,3,3,4,4]</code> ，<code>trimmed([1,2,3,3,3,4,4]) = [3,3,3,4,4]</code> 。这个子数组的重要性就是 <code>k + 5</code> 。</li>
</ul>

<p>找出并返回拆分 <code>nums</code> 的所有可行方案中的最小代价。</p>

<p><strong>子数组</strong> 是数组的一个连续 <strong>非空</strong> 元素序列。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,1,2,1,3,3], k = 2
<strong>输出：</strong>8
<strong>解释：</strong>将 nums 拆分成两个子数组：[1,2], [1,2,1,3,3]
[1,2] 的重要性是 2 + (0) = 2 。
[1,2,1,3,3] 的重要性是 2 + (2 + 2) = 6 。
拆分的代价是 2 + 6 = 8 ，可以证明这是所有可行的拆分方案中的最小代价。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,1,2,1], k = 2
<strong>输出：</strong>6
<strong>解释：</strong>将 nums 拆分成两个子数组：[1,2], [1,2,1] 。
[1,2] 的重要性是 2 + (0) = 2 。
[1,2,1] 的重要性是 2 + (2) = 4 。
拆分的代价是 2 + 4 = 6 ，可以证明这是所有可行的拆分方案中的最小代价。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,1,2,1], k = 5
<strong>输出：</strong>10
<strong>解释：</strong>将 nums 拆分成一个子数组：[1,2,1,2,1].
[1,2,1,2,1] 的重要性是 5 + (3 + 2) = 10 。
拆分的代价是 10 ，可以证明这是所有可行的拆分方案中的最小代价。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt; nums.length</code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

**方法一：记忆化搜索**

我们设计一个函数dfs(i)，表示从下标i开始拆分的最小代价。那么答案就是dfs(0)。

函数dfs(i)的计算过程如下：

-   如果i ≥ n，说明已经拆分到了数组末尾，此时返回0。
-   否则，我们枚举子数组的末尾j，过程中用一个数组或哈希表 `cnt` 统计子数组中每个数字出现的次数，用一个变量 `one` 统计子数组中出现次数为1的数字的个数。那么子数组的重要性就是k + j - i + 1 - one，拆分的代价就是k + j - i + 1 - one + dfs(j + 1)。我们枚举所有的j，取其中的最小值作为dfs(i)的返回值。

过程中，我们可以使用记忆化搜索，即使用一个数组f记忆化函数dfs(i)的返回值，避免重复计算。

时间复杂度O(n^2)，空间复杂度O(n)。其中n为数组 `nums` 的长度。

### **Java**

```java
class Solution {
    private Integer[] f;
    private int[] nums;
    private int n, k;

    public int minCost(int[] nums, int k) {
        n = nums.length;
        this.k = k;
        this.nums = nums;
        f = new Integer[n];
        return dfs(0);
    }

    private int dfs(int i) {
        if (i >= n) {
            return 0;
        }
        if (f[i] != null) {
            return f[i];
        }
        int[] cnt = new int[n];
        int one = 0;
        int ans = 1 << 30;
        for (int j = i; j < n; ++j) {
            int x = ++cnt[nums[j]];
            if (x == 1) {
                ++one;
            } else if (x == 2) {
                --one;
            }
            ans = Math.min(ans, k + j - i + 1 - one + dfs(j + 1));
        }
        return f[i] = ans;
    }
}
```
