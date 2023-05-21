# [188. 买卖股票的最佳时机 IV](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv)

## 题目描述

<p>给定一个整数数组&nbsp;<code>prices</code> ，它的第<em> </em><code>i</code> 个元素&nbsp;<code>prices[i]</code> 是一支给定的股票在第 <code>i</code><em> </em>天的价格，和一个整型 <code>k</code> 。</p>

<p>设计一个算法来计算你所能获取的最大利润。你最多可以完成 <code>k</code> 笔交易。也就是说，你最多可以买 <code>k</code> 次，卖 <code>k</code> 次。</p>

<p><strong>注意：</strong>你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>k = 2, prices = [2,4,1]
<strong>输出：</strong>2
<strong>解释：</strong>在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>k = 2, prices = [3,2,6,5,0,3]
<strong>输出：</strong>7
<strong>解释：</strong>在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
     随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= k &lt;= 100</code></li>
	<li><code>0 &lt;= prices.length &lt;= 1000</code></li>
	<li><code>0 &lt;= prices[i] &lt;= 1000</code></li>
</ul>

## 解法

**方法一：记忆化搜索**

我们设计一个函数dfs(i, j, k)，表示从第i天开始，最多进行j笔交易，以及当前持有股票的状态为k（不持有股票用0表示，持有股票用1表示）时，所能获得的最大利润。答案即为dfs(0, k, 0)。

函数dfs(i, j, k)的执行逻辑如下：

-   如果i大于等于n，直接返回0；
-   第i天可以不进行任何操作，那么dfs(i, j, k) = dfs(i + 1, j, k)；
-   如果k \gt 0，那么第i天可以选择卖出股票，那么dfs(i, j, k) = max(dfs(i + 1, j - 1, 0) + prices[i], dfs(i + 1, j, k))；
-   否则，如果j \gt 0，那么第i天可以选择买入股票，那么dfs(i, j, k) = max(dfs(i + 1, j - 1, 1) - prices[i], dfs(i + 1, j, k))。

取上述三种情况的最大值即为dfs(i, j, k)的值。

过程中，我们可以使用记忆化搜索的方法，将每次计算的结果保存下来，避免重复计算。

时间复杂度O(n \times k)，空间复杂度O(n \times k)。其中n和k分别为数组prices的长度和k的值。

### **Java**

```java
class Solution {
    private Integer[][][] f;
    private int[] prices;
    private int n;

    public int maxProfit(int k, int[] prices) {
        n = prices.length;
        this.prices = prices;
        f = new Integer[n][k + 1][2];
        return dfs(0, k, 0);
    }

    private int dfs(int i, int j, int k) {
        if (i >= n) {
            return 0;
        }
        if (f[i][j][k] != null) {
            return f[i][j][k];
        }
        int ans = dfs(i + 1, j, k);
        if (k > 0) {
            ans = Math.max(ans, prices[i] + dfs(i + 1, j, 0));
        } else if (j > 0) {
            ans = Math.max(ans, -prices[i] + dfs(i + 1, j - 1, 1));
        }
        f[i][j][k] = ans;
        return ans;
    }
}
```

```java
class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n <= 1) {
            return 0;
        }
        int[][][] dp = new int[n][k + 1][2];
        for (int i = 1; i <= k; ++i) {
            dp[0][i][1] = -prices[0];
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j <= k; ++j) {
                dp[i][j][0] = Math.max(dp[i - 1][j][1] + prices[i], dp[i - 1][j][0]);
                dp[i][j][1] = Math.max(dp[i - 1][j - 1][0] - prices[i], dp[i - 1][j][1]);
            }
        }
        return dp[n - 1][k][0];
    }
}
```
