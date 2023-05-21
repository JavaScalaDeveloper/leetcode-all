# [322. 零钱兑换](https://leetcode.cn/problems/coin-change)

## 题目描述

<p>给你一个整数数组 <code>coins</code> ，表示不同面额的硬币；以及一个整数 <code>amount</code> ，表示总金额。</p>

<p>计算并返回可以凑成总金额所需的 <strong>最少的硬币个数</strong> 。如果没有任何一种硬币组合能组成总金额，返回&nbsp;<code>-1</code> 。</p>

<p>你可以认为每种硬币的数量是无限的。</p>

<p><strong>示例&nbsp;1：</strong></p>

<pre>
<strong>输入：</strong>coins = <code>[1, 2, 5]</code>, amount = <code>11</code>
<strong>输出：</strong><code>3</code> 
<strong>解释：</strong>11 = 5 + 5 + 1</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>coins = <code>[2]</code>, amount = <code>3</code>
<strong>输出：</strong>-1</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>coins = [1], amount = 0
<strong>输出：</strong>0
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= coins.length &lt;= 12</code></li>
	<li><code>1 &lt;= coins[i] &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>0 &lt;= amount &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

**方法一：动态规划**

类似完全背包的思路，硬币数量不限，求凑成总金额所需的最少的硬币个数。

定义dp[i][j]表示从前i种硬币选出总金额为j所需的最少硬币数。

那么有：


dp[i][j] = \min(dp[i - 1][j], dp[i - 1][j - v] + 1, dp[i - 1][j - 2\times v] + 2, ... , dp[i - 1][j - k\times v] + k)


令j=j-v，则有：


dp[i][j - v] = \min( dp[i - 1][j - v], dp[i - 1][j - 2\times v] + 1, ... , dp[i - 1][j - k\times v] + k - 1)


因此，我们可以得到状态转移方程：


dp[i][j] = \min(dp[i - 1][j], dp[i][j - v] + 1)


时间复杂度O(m\times n)，空间复杂度O(m\times n)。其中m和n分别为硬币数量和总金额。

动态规划——完全背包问题朴素做法：

动态规划——完全背包问题空间优化：

### **Java**

```java
class Solution {
    public int coinChange(int[] coins, int amount) {
        int m = coins.length;
        int[][] dp = new int[m + 1][amount + 1];
        for (int i = 0; i <= m; ++i) {
            Arrays.fill(dp[i], amount + 1);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= m; ++i) {
            int v = coins[i - 1];
            for (int j = 0; j <= amount; ++j) {
                dp[i][j] = dp[i - 1][j];
                if (j >= v) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - v] + 1);
                }
            }
        }
        return dp[m][amount] > amount ? - 1 : dp[m][amount];
    }
}
```

```java
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int coin : coins) {
            for (int j = coin; j <= amount; j++) {
                dp[j] = Math.min(dp[j], dp[j - coin] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
```
