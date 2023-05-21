# [1223. 掷骰子模拟](https://leetcode.cn/problems/dice-roll-simulation)

## 题目描述

<p>有一个骰子模拟器会每次投掷的时候生成一个 1 到 6 的随机数。</p>

<p>不过我们在使用它时有个约束，就是使得投掷骰子时，<strong>连续</strong> 掷出数字&nbsp;<code>i</code>&nbsp;的次数不能超过&nbsp;<code>rollMax[i]</code>（<code>i</code>&nbsp;从 1 开始编号）。</p>

<p>现在，给你一个整数数组&nbsp;<code>rollMax</code>&nbsp;和一个整数&nbsp;<code>n</code>，请你来计算掷&nbsp;<code>n</code>&nbsp;次骰子可得到的不同点数序列的数量。</p>

<p>假如两个序列中至少存在一个元素不同，就认为这两个序列是不同的。由于答案可能很大，所以请返回 <strong>模&nbsp;<code>10^9 + 7</code></strong>&nbsp;之后的结果。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 2, rollMax = [1,1,2,2,2,3]
<strong>输出：</strong>34
<strong>解释：</strong>我们掷 2 次骰子，如果没有约束的话，共有 6 * 6 = 36 种可能的组合。但是根据 rollMax 数组，数字 1 和 2 最多连续出现一次，所以不会出现序列 (1,1) 和 (2,2)。因此，最终答案是 36-2 = 34。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 2, rollMax = [1,1,1,1,1,1]
<strong>输出：</strong>30
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = 3, rollMax = [1,1,1,2,2,3]
<strong>输出：</strong>181
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 5000</code></li>
	<li><code>rollMax.length == 6</code></li>
	<li><code>1 &lt;= rollMax[i] &lt;= 15</code></li>
</ul>

## 解法

**方法一：记忆化搜索**

我们可以设计一个函数dfs(i, j, x)表示从第i次掷骰子开始，当前掷出的点数为j，且连续掷出j的次数为x的方案数。其中j的取值范围为[1, 6]，而x的取值范围为[1, rollMax[j - 1]]。那么答案就是dfs(0, 0, 0)。

函数dfs(i, j, x)的计算过程如下：

-   如果i ≥ n，说明已经掷完了n次骰子，返回1。
-   否则，我们枚举下一次掷出的点数k，如果k \ne j，那么我们可以直接掷出k，此时连续掷出j的次数x就会被重置为1，因此方案数为dfs(i + 1, k, 1)。如果k = j，那么我们需要判断x是否小于rollMax[j - 1]，如果小于，那么我们可以继续掷出j，此时连续掷出j的次数x就会加1，因此方案数为dfs(i + 1, j, x + 1)。最后将所有方案数相加，即为dfs(i, j, x)的值。注意答案可能很大，因此需要对10^9 + 7取模。

过程中，我们可以使用记忆化搜索避免重复计算。

时间复杂度O(n × k^2 × M)，空间复杂度O(n × k × M)。其中k为点数的取值范围，而M为连续掷出某个点数的最大次数。

**方法二：动态规划**

我们可以将方法一中的记忆化搜索改为动态规划。

定义f[i][j][x]表示投掷前i次骰子，且第i次投掷的点数为j，且连续投掷点数j的次数为x的方案数。初始时f[1][j][1] = 1，其中1 ≤ j ≤ 6。答案即是：


\sum_{j=1}^6 \sum_{x=1}^{rollMax[j-1]} f[n][j][x]


我们枚举上一次投掷的点数为j，且连续投掷点数j的次数为x，那么当前投掷的点数可以为1, 2, \cdots, 6，如果当前投掷的点数为k，那么有如下两种情况：

-   如果k \neq j，那么我们可以直接投掷出k，此时连续投掷点数j的次数x就会被重置为1，因此方案数f[i][k][1]就会增加f[i-1][j][x]。
-   如果k = j，那么我们需要判断x+1是否小于等于rollMax[j-1]，如果小于等于，那么我们可以继续投掷出j，此时连续投掷点数j的次数x就会加1，因此方案数f[i][j][x+1]就会增加f[i-1][j][x]。

最终的答案即为所有f[n][j][x]的和。

时间复杂度O(n × k^2 × M)，空间复杂度O(n × k × M)。其中k为点数的取值范围，而M为连续掷出某个点数的最大次数。

### **Java**

```java
class Solution {
    private Integer[][][] f;
    private int[] rollMax;

    public int dieSimulator(int n, int[] rollMax) {
        f = new Integer[n][7][16];
        this.rollMax = rollMax;
        return dfs(0, 0, 0);
    }

    private int dfs(int i, int j, int x) {
        if (i >= f.length) {
            return 1;
        }
        if (f[i][j][x] != null) {
            return f[i][j][x];
        }
        long ans = 0;
        for (int k = 1; k <= 6; ++k) {
            if (k != j) {
                ans += dfs(i + 1, k, 1);
            } else if (x < rollMax[j - 1]) {
                ans += dfs(i + 1, j, x + 1);
            }
        }
        ans %= 1000000007;
        return f[i][j][x] = (int) ans;
    }
}
```

```java
class Solution {
    public int dieSimulator(int n, int[] rollMax) {
        int[][][] f = new int[n + 1][7][16];
        for (int j = 1; j <= 6; ++j) {
            f[1][j][1] = 1;
        }
        final int mod = (int) 1e9 + 7;
        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= 6; ++j) {
                for (int x = 1; x <= rollMax[j - 1]; ++x) {
                    for (int k = 1; k <= 6; ++k) {
                        if (k != j) {
                            f[i][k][1] = (f[i][k][1] + f[i - 1][j][x]) % mod;
                        } else if (x + 1 <= rollMax[j - 1]) {
                            f[i][j][x + 1] = (f[i][j][x + 1] + f[i - 1][j][x]) % mod;
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int j = 1; j <= 6; ++j) {
            for (int x = 1; x <= rollMax[j - 1]; ++x) {
                ans = (ans + f[n][j][x]) % mod;
            }
        }
        return ans;
    }
}
```
