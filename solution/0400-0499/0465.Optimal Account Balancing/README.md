# [465. 最优账单平衡](https://leetcode.cn/problems/optimal-account-balancing)

[English Version](/solution/0400-0499/0465.Optimal%20Account%20Balancing/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个表示交易的数组 <code>transactions</code> ，其中 <code>transactions[i] = [from<sub>i</sub>, to<sub>i</sub>, amount<sub>i</sub>]</code> 表示 <code>ID = from<sub>i</sub></code> 的人给&nbsp;<code>ID = to<sub>i</sub></code> 的人共计 <code>amount<sub>i</sub> $</code> 。</p>

<p>请你计算并返回还清所有债务的最小交易笔数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>transactions = [[0,1,10],[2,0,5]]
<strong>输出：</strong>2
<strong>解释：</strong>
#0 给 #1 $10 。
#2 给 #0 $5 。
需要进行两笔交易。一种结清债务的方式是 #1 给 #0 和 #2 各 $5 。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>transactions = [[0,1,10],[1,0,1],[1,2,5],[2,0,5]]
<strong>输出：</strong>1
<strong>解释：</strong>
#0 给 #1 $10 。
#1 给 #0 $1 。
#1 给 #2 $5 。
#2 给 #0 $5 。
因此，#1 只需要给 #0 $4 ，所有的债务即可还清。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= transactions.length &lt;= 8</code></li>
	<li><code>transactions[i].length == 3</code></li>
	<li><code>0 &lt;= from<sub>i</sub>, to<sub>i</sub> &lt; 12</code></li>
	<li><code>from<sub>i</sub> != to<sub>i</sub></code></li>
	<li><code>1 &lt;= amount<sub>i</sub> &lt;= 100</code></li>
</ul>

## 解法

**方法一：状态压缩动态规划 + 子集枚举**

我们先遍历数组 `transactions`，统计每个人的收支情况，然后将所有收支不为零的人的收支情况存入数组 $nums$ 中。如果我们可以找到一个子集，子集中共有 $k$ 个人，且这 $k$ 个人的收支情况之和为零，那么我们最多通过 $k-1$ 次交易，就能够使得这 $k$ 个人的收支情况全部清零。这样，我们就能将原问题转化成一个子集枚举的问题。

我们定义 $f[i]$ 表示将集合 $i$ 的所有元素的收支情况全部清零，所需的最少交易次数，初始时 $f[0]=0$，其余 $f[i]=+\infty$。

考虑 $f[i]$，其中 $i \in [1,2^m)$, $m$ 是数组 $nums$ 的长度。我们可以统计集合 $i$ 中所有元素的收支情况之和 $s$，如果 $s=0$，那么 $f[i]$ 的取值不超过 $|i|-1$，其中 $|i|$ 表示集合 $i$ 中的元素个数。然后我们可以枚举 $i$ 的所有非空子集 $j$，计算 $f[j]+f[i-j]$，其中 $f[j]$ 和 $f[i-j]$ 分别表示将集合 $j$ 和 $i-j$ 的所有元素的收支情况全部清零，所需的最少交易次数。我们可以得到状态转移方程：

$$
f[i]=
\begin{cases}
0, & i=0 \\
+\infty, & i \neq 0, s \neq 0 \\
\min(|i|-1, \min_{j \subset i, j \neq \emptyset} \{f[j]+f[i-j]\}), & i \neq 0, s = 0
\end{cases}
$$

其中 $j \subset i$ 表示 $j$ 是 $i$ 的子集，且 $j \neq \emptyset$。

最终答案即为 $f[2^m-1]$。

时间复杂度 $O(3^n)$，空间复杂度 $O(2^n)$。其中 $n$ 是人的数量，本题中 $n \leq 12$。

### **Java**

```java
class Solution {
    public int minTransfers(int[][] transactions) {
        int[] g = new int[12];
        for (var t : transactions) {
            g[t[0]] -= t[2];
            g[t[1]] += t[2];
        }
        List<Integer> nums = new ArrayList<>();
        for (int x : g) {
            if (x != 0) {
                nums.add(x);
            }
        }
        int m = nums.size();
        int[] f = new int[1 << m];
        Arrays.fill(f, 1 << 29);
        f[0] = 0;
        for (int i = 1; i < 1 << m; ++i) {
            int s = 0;
            for (int j = 0; j < m; ++j) {
                if ((i >> j & 1) == 1) {
                    s += nums.get(j);
                }
            }
            if (s == 0) {
                f[i] = Integer.bitCount(i) - 1;
                for (int j = (i - 1) & i; j > 0; j = (j - 1) & i) {
                    f[i] = Math.min(f[i], f[j] + f[i ^ j]);
                }
            }
        }
        return f[(1 << m) - 1];
    }
}
```

### **TypeScript**
