# [790. 多米诺和托米诺平铺](https://leetcode.cn/problems/domino-and-tromino-tiling)

## 题目描述

<p>有两种形状的瓷砖：一种是&nbsp;<code>2 x 1</code> 的多米诺形，另一种是形如&nbsp;"L" 的托米诺形。两种形状都可以旋转。</p>

<p><img src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0790.Domino%20and%20Tromino%20Tiling/images/lc-domino.jpg" style="height: 195px; width: 362px;" /></p>

<p>给定整数 n ，返回可以平铺&nbsp;<code>2 x n</code> 的面板的方法的数量。<strong>返回对</strong>&nbsp;<code>10<sup>9</sup>&nbsp;+ 7</code>&nbsp;<strong>取模&nbsp;</strong>的值。</p>

<p>平铺指的是每个正方形都必须有瓷砖覆盖。两个平铺不同，当且仅当面板上有四个方向上的相邻单元中的两个，使得恰好有一个平铺有一个瓷砖占据两个正方形。</p>

<p><strong>示例 1:</strong></p>

<p><img src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0790.Domino%20and%20Tromino%20Tiling/images/lc-domino1.jpg" style="height: 226px; width: 500px;" /></p>

<pre>
<strong>输入:</strong> n = 3
<strong>输出:</strong> 5
<strong>解释:</strong> 五种不同的方法如上所示。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> n = 1
<strong>输出:</strong> 1
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

## 解法

**方法一：动态规划**

我们首先要读懂题意，题目实际上是让我们求铺满2\times n的面板的方案数，其中面板上的每个正方形只能被一个瓷砖覆盖。

瓷砖的形状有两种，分别是 `2 x 1` 型 和 `L` 型，并且两种瓷砖都可以旋转。我们将旋转后的瓷砖分别记为 `1 x 2` 型 和 `L'` 型。

我们定义f[i][j]表示平铺前2\times i的面板，其中j表示最后一列的状态。最后一列有4种状态，分别是：

-   最后一列铺满，记为0
-   最后一列只铺了上方一个瓷砖，记为1
-   最后一列只铺了下方一个瓷砖，记为2
-   最后一列没有铺瓷砖，记为3

那么答案就是f[n][0]。初始时f[0][0]=1，其余f[0][j]=0。

我们考虑铺到第i列，来看看状态转移方程：

当j=0时，最后一列铺满，可由前一列的0,1,2,3四种状态铺上对应的瓷砖转移而来，即f[i-1][0]铺上 `1 x 2` 型瓷砖，或者f[i-1][1]铺上 `L'` 型瓷砖，或者f[i-1][2]铺上 `L'` 型瓷砖，或者f[i-1][3]铺上两块 `2 x 1` 型瓷砖。因此f[i][0]=\sum_{j=0}^3f[i-1][j]。

当j=1时，最后一列只铺了上方一个瓷砖，可由前一列的2,3两种状态转移而来，即f[i-1][2]铺上 `2 x 1` 型瓷砖，或者f[i-1][3]铺上 `L` 型瓷砖。因此f[i][1]=f[i-1][2]+f[i-1][3]。

当j=2时，最后一列只铺了下方一个瓷砖，可由前一列的1,3两种状态转移而来，即f[i-1][1]铺上 `2 x 1` 型瓷砖，或者f[i-1][3]铺上 `L'` 型瓷砖。因此f[i][2]=f[i-1][1]+f[i-1][3]。

当j=3时，最后一列没有铺瓷砖，可由前一列的0一种状态转移而来。因此f[i][3]=f[i-1][0]。

可以发现，状态转移方程中只涉及到前一列的状态，因此我们可以使用滚动数组优化空间复杂度。

注意，过程中的状态数值可能会很大，因此需要对10^9+7取模。

时间复杂度O(n)，空间复杂度O(1)。其中n为面板的列数。

### **Java**

```java
class Solution {
    public int numTilings(int n) {
        long[] f = {1, 0, 0, 0};
        int mod = (int) 1e9 + 7;
        for (int i = 1; i <= n; ++i) {
            long[] g = new long[4];
            g[0] = (f[0] + f[1] + f[2] + f[3]) % mod;
            g[1] = (f[2] + f[3]) % mod;
            g[2] = (f[1] + f[3]) % mod;
            g[3] = f[0];
            f = g;
        }
        return (int) f[0];
    }
}
```
