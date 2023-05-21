# [837. 新 21 点](https://leetcode.cn/problems/new-21-game)

## 题目描述

<p>爱丽丝参与一个大致基于纸牌游戏 <strong>“21点”</strong> 规则的游戏，描述如下：</p>

<p>爱丽丝以 <code>0</code> 分开始，并在她的得分少于 <code>k</code> 分时抽取数字。 抽取时，她从 <code>[1, maxPts]</code> 的范围中随机获得一个整数作为分数进行累计，其中 <code>maxPts</code> 是一个整数。 每次抽取都是独立的，其结果具有相同的概率。</p>

<p>当爱丽丝获得 <code>k</code> 分 <strong>或更多分</strong> 时，她就停止抽取数字。</p>

<p>爱丽丝的分数不超过 <code>n</code> 的概率是多少？</p>

<p>与实际答案误差不超过&nbsp;<code>10<sup>-5</sup></code> 的答案将被视为正确答案。</p>
&nbsp;

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 10, k = 1, maxPts = 10
<strong>输出：</strong>1.00000
<strong>解释：</strong>爱丽丝得到一张牌，然后停止。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 6, k = 1, maxPts = 10
<strong>输出：</strong>0.60000
<strong>解释：</strong>爱丽丝得到一张牌，然后停止。 在 10 种可能性中的 6 种情况下，她的得分不超过 6 分。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 21, k = 17, maxPts = 10
<strong>输出：</strong>0.73278
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= k &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= maxPts &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

**方法一：记忆化搜索**

我们设计一个函数dfs(i)，表示当前分数为i时，到最终停止抽取数字时，分数不超过n的概率。那么答案就是dfs(0)。

函数dfs(i)的计算方法如下：

-   如果i \ge k，那么停止抽取数字，如果i \le n，返回1，否则返回0；
-   否则，可以在[1,..maxPts]范围内抽取下一个数字j，那么dfs(i) = \frac{1}{maxPts} \sum_{j=1}^{maxPts} dfs(i+j)。

这里我们可以使用记忆化搜索来加速计算。

以上方法的时间复杂度为O(k \times maxPts)，会超出时间限制，我们需要优化一下。

当i \lt k时，以下等式成立：


\begin{aligned}
dfs(i) &= (dfs(i + 1) + dfs(i + 2) + \cdots + dfs(i + maxPts)) / maxPts & (1)
\end{aligned}


当i \lt k - 1时，以下等式成立：


\begin{aligned}
dfs(i+1) &= (dfs(i + 2) + dfs(i + 3) + \cdots + dfs(i + maxPts + 1)) / maxPts & (2)
\end{aligned}


因此，当i \lt k-1时，我们将等式(1)减去等式(2)，得到：


\begin{aligned}
dfs(i) - dfs(i+1) &= (dfs(i + 1) - dfs(i + maxPts + 1)) / maxPts
\end{aligned}


即：


\begin{aligned}
dfs(i) &= dfs(i + 1) + (dfs(i + 1) - dfs(i + maxPts + 1)) / maxPts
\end{aligned}


如果i=k-1，有：


\begin{aligned}
dfs(i) &= dfs(k - 1) &= dfs(k) + dfs(k + 1) + \cdots + dfs(k + maxPts - 1) / maxPts & (3)
\end{aligned}


我们假设有i个数不超过n，那么k+i-1 \leq n，又因为i\leq maxPts，所以i \leq \min(n-k+1, maxPts)，因此等式(3)可以写成：


\begin{aligned}
dfs(k-1) &= \min(n-k+1, maxPts) / maxPts
\end{aligned}


综上所述，有以下状态转移方程：


\begin{aligned}
dfs(i) &= \begin{cases}
1, & i \geq k, i \leq n \\
0, & i \geq k, i \gt n \\
\min(n-k+1, maxPts) / maxPts, & i = k - 1 \\
dfs(i + 1) + (dfs(i + 1) - dfs(i + maxPts + 1)) / maxPts, & i < k - 1
\end{cases}
\end{aligned}


时间复杂度O(k + maxPts)，空间复杂度O(k + maxPts)。其中k为最大分数。

**方法二：动态规划**

我们可以将方法一中的记忆化搜索改成动态规划。

定义f[i]表示当前分数为i时，到最终停止抽取数字时，分数不超过n的概率。那么答案就是f[0]。

当k \leq i \leq \min(n, k + maxPts - 1)时，有f[i] = 1。

当i = k - 1时，有f[i] = \min(n-k+1, maxPts) / maxPts。

当i \lt k - 1时，有f[i] = f[i + 1] + (f[i + 1] - f[i + maxPts + 1]) / maxPts。

时间复杂度O(k + maxPts)，空间复杂度O(k + maxPts)。其中k为最大分数。

### **Java**

```java
class Solution {
    private double[] f;
    private int n, k, maxPts;

    public double new21Game(int n, int k, int maxPts) {
        f = new double[k];
        this.n = n;
        this.k = k;
        this.maxPts = maxPts;
        return dfs(0);
    }

    private double dfs(int i) {
        if (i >= k) {
            return i <= n ? 1 : 0;
        }
        if (i == k - 1) {
            return Math.min(n - k + 1, maxPts) * 1.0 / maxPts;
        }
        if (f[i] != 0) {
            return f[i];
        }
        return f[i] = dfs(i + 1) + (dfs(i + 1) - dfs(i + maxPts + 1)) / maxPts;
    }
}
```

```java
class Solution {
    public double new21Game(int n, int k, int maxPts) {
        if (k == 0) {
            return 1.0;
        }
        double[] f = new double[k + maxPts];
        for (int i = k; i < Math.min(n + 1, k + maxPts); ++i) {
            f[i] = 1;
        }
        f[k - 1] = Math.min(n - k + 1, maxPts) * 1.0 / maxPts;
        for (int i = k - 2; i >= 0; --i) {
            f[i] = f[i + 1] + (f[i + 1] - f[i + maxPts + 1]) / maxPts;
        }
        return f[0];
    }
}
```
