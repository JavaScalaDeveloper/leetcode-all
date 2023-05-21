# [1434. 每个人戴不同帽子的方案数](https://leetcode.cn/problems/number-of-ways-to-wear-different-hats-to-each-other)

## 题目描述

<p>总共有 <code>n</code>&nbsp;个人和 <code>40</code> 种不同的帽子，帽子编号从 <code>1</code> 到 <code>40</code> 。</p>

<p>给你一个整数列表的列表&nbsp;<code>hats</code>&nbsp;，其中&nbsp;<code>hats[i]</code>&nbsp;是第 <code>i</code>&nbsp;个人所有喜欢帽子的列表。</p>

<p>请你给每个人安排一顶他喜欢的帽子，确保每个人戴的帽子跟别人都不一样，并返回方案数。</p>

<p>由于答案可能很大，请返回它对&nbsp;<code>10^9 + 7</code>&nbsp;取余后的结果。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>hats = [[3,4],[4,5],[5]]
<strong>输出：</strong>1
<strong>解释：</strong>给定条件下只有一种方法选择帽子。
第一个人选择帽子 3，第二个人选择帽子 4，最后一个人选择帽子 5。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>hats = [[3,5,1],[3,5]]
<strong>输出：</strong>4
<strong>解释：</strong>总共有 4 种安排帽子的方法：
(3,5)，(5,3)，(1,3) 和 (1,5)
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>hats = [[1,2,3,4],[1,2,3,4],[1,2,3,4],[1,2,3,4]]
<strong>输出：</strong>24
<strong>解释：</strong>每个人都可以从编号为 1 到 4 的帽子中选。
(1,2,3,4) 4 个帽子的排列方案数为 24 。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>hats = [[1,2,3],[2,3,5,6],[1,3,7,9],[1,8,9],[2,5,7]]
<strong>输出：</strong>111
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == hats.length</code></li>
	<li><code>1 &lt;= n &lt;= 10</code></li>
	<li><code>1 &lt;= hats[i].length &lt;= 40</code></li>
	<li><code>1 &lt;= hats[i][j] &lt;= 40</code></li>
	<li><code>hats[i]</code>&nbsp;包含一个数字互不相同的整数列表。</li>
</ul>

## 解法

**方法一：状态压缩动态规划**

我们注意到n不超过10，因此我们考虑使用状态压缩动态规划的方法求解。

我们定义f[i][j]表示在前i个帽子中，当前被分配的人的状态为j时的方案数。其中j是一个二进制数，表示当前被分配的人的集合。初始时f[0][0]=1，答案为f[m][2^n - 1]，其中m是帽子的最大编号，而n是人的数量。

考虑f[i][j]，如果第i个帽子不分配给任何人，那么f[i][j]=f[i-1][j]；如果第i个帽子分配给了喜欢它的人k，那么f[i][j]=f[i-1][j \oplus 2^k]。这里\oplus表示异或运算。因此我们可以得到状态转移方程：


f[i][j]=f[i-1][j]+ \sum_{k \in like[i]} f[i-1][j \oplus 2^k]


其中like[i]表示喜欢第i个帽子的人的集合。

最终的答案即为f[m][2^n - 1]，注意答案可能很大，需要对10^9 + 7取模。

时间复杂度O(m \times 2^n \times n)，空间复杂度O(m \times 2^n)。其中m是帽子的最大编号，本题中m \leq 40；而n是人的数量，本题中n \leq 10。

### **Java**

```java
class Solution {
    public int numberWays(List<List<Integer>> hats) {
        int n = hats.size();
        int m = 0;
        for (var h : hats) {
            for (int v : h) {
                m = Math.max(m, v);
            }
        }
        List<Integer>[] g = new List[m + 1];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 0; i < n; ++i) {
            for (int v : hats.get(i)) {
                g[v].add(i);
            }
        }
        final int mod = (int) 1e9 + 7;
        int[][] f = new int[m + 1][1 << n];
        f[0][0] = 1;
        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j < 1 << n; ++j) {
                f[i][j] = f[i - 1][j];
                for (int k : g[i]) {
                    if ((j >> k & 1) == 1) {
                        f[i][j] = (f[i][j] + f[i - 1][j ^ (1 << k)]) % mod;
                    }
                }
            }
        }
        return f[m][(1 << n) - 1];
    }
}
```
