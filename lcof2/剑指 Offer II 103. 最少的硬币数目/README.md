# [剑指 Offer II 103. 最少的硬币数目](https://leetcode.cn/problems/gaM7Ch)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定不同面额的硬币 <code>coins</code> 和一个总金额 <code>amount</code>。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回&nbsp;<code>-1</code>。</p>

<p>你可以认为每种硬币的数量是无限的。</p>

<p>&nbsp;</p>

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

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>coins = [1], amount = 1
<strong>输出：</strong>1
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>coins = [1], amount = 2
<strong>输出：</strong>2
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= coins.length &lt;= 12</code></li>
	<li><code>1 &lt;= coins[i] &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>0 &lt;= amount &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 322&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/coin-change/">https://leetcode.cn/problems/coin-change/</a></p>

## 解法

动态规划。

类似完全背包的思路，硬币数量不限，求凑成总金额所需的最少的硬币个数。

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
                for (int k = 0; k * v <= j; ++k) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - k * v] + k);
                }
            }
        }
        return dp[m][amount] > amount ? -1 : dp[m][amount];
    }
}

```

下面对 k 这层循环进行优化：

由于：

-   `dp[i][j] = min(dp[i - 1][j], dp[i - 1][j - v] + 1, dp[i - 1][j - 2v] + 2, ... , dp[i - 1][j - kv] + k)`
-   `dp[i][j - v] = min( dp[i - 1][j - v], dp[i - 1][j - 2v] + 1, ... , dp[i - 1][j - kv] + k - 1)`

因此 `dp[i][j] = min(dp[i - 1][j], dp[i][j - v] + 1)`。

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
        return dp[m][amount] > amount ? -1 : dp[m][amount];
    }
}

```

空间优化：

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
