# [656. 金币路径](https://leetcode.cn/problems/coin-path)

## 题目描述

<p>给定一个数组 <code>A</code>（下标从 <code>1</code> 开始）包含 N 个整数：A<sub>1</sub>，A<sub>2</sub>，&hellip;&hellip;，A<sub>N</sub>&nbsp;和一个整数 <code>B</code>。你可以从数组 <code>A</code> 中的任何一个位置（下标为 <code>i</code>）跳到下标&nbsp;<code>i+1</code>，<code>i+2</code>，&hellip;&hellip;，<code>i+B</code>&nbsp;的任意一个可以跳到的位置上。如果你在下标为 <code>i</code> 的位置上，你需要支付 A<sub>i</sub> 个金币。如果 A<sub>i</sub> 是 -1，意味着下标为 <code>i</code> 的位置是不可以跳到的。</p>

<p>现在，你希望花费最少的金币从数组 <code>A</code> 的 <code>1</code> 位置跳到&nbsp;<code>N</code> 位置，你需要输出花费最少的路径，依次输出所有经过的下标（从 1 到 N）。</p>

<p>如果有多种花费最少的方案，输出字典顺序最小的路径。</p>

<p>如果无法到达 N 位置，请返回一个空数组。</p>

<p><strong>样例 1 :</strong></p>

<pre><strong>输入:</strong> [1,2,4,-1,2], 2
<strong>输出:</strong> [1,3,5]
</pre>

<p><strong>样例 2 :</strong></p>

<pre><strong>输入:</strong> [1,2,4,-1,2], 1
<strong>输出:</strong> []
</pre>

<p><strong>注释 :</strong></p>

<ol>
	<li>路径 Pa<sub>1</sub>，Pa<sub>2</sub>，&hellip;&hellip;，Pa<sub>n&nbsp;</sub>是字典序小于 Pb<sub>1</sub>，Pb<sub>2</sub>，&hellip;&hellip;，Pb<sub>m&nbsp;</sub>的，当且仅当第一个 Pa<sub>i</sub> 和 Pb<sub>i</sub> 不同的 <code>i</code> 满足 Pa<sub>i</sub> &lt; Pb<sub>i</sub>，如果不存在这样的 <code>i</code> 那么满足 <code>n</code> &lt; <code>m</code>。</li>
	<li>A<sub>1</sub> &gt;= 0。&nbsp;A<sub>2</sub>, ..., A<sub>N</sub>&nbsp;（如果存在）&nbsp;的范围是 [-1, 100]。</li>
	<li>A 数组的长度范围 [1, 1000].</li>
	<li>B 的范围&nbsp;[1, 100].</li>
</ol>

## 解法

**方法一：动态规划（逆向）**

题目需要我们找到从下标 1 到下标 n 的最小花费路径，且字典序最小，我们可以使用动态规划求解。

我们定义f[i]表示从下标i到下标n-1的最小花费。如果coins[n - 1] = -1，则不存在从下标n-1到下标n-1的路径，直接返回空数组即可。否则f[n - 1] = coins[n - 1]。

接下来，我们从下标n-2开始，逆向遍历数组，对于下标i，如果coins[i] = -1，则f[i] = \infty，否则f[i] = \min_{j = i + 1}^{min(n - 1, i + maxJump)} f[j] + coins[i]。

然后我们判断f[0]是否为\infty，如果是，则不存在一条满足条件的路径，返回空数组即可。否则，我们的总花费为s = f[0]，我们从下标 0 开始，向后遍历数组，如果f[i] = s，则说明从下标i到下标n-1的花费为s，我们将s减去coins[i]，并将下标i+1加入到结果数组中，直到遍历到下标n-1，返回结果数组即可。

时间复杂度O(n \times m)，空间复杂度O(n)。其中n和m分别为数组的长度和最大跳跃长度。

### **Java**

```java
class Solution {
    public List<Integer> cheapestJump(int[] coins, int maxJump) {
        int n = coins.length;
        List<Integer> ans = new ArrayList<>();
        if (coins[n - 1] == -1) {
            return ans;
        }
        int[] f = new int[n];
        final int inf = 1 << 30;
        Arrays.fill(f, inf);
        f[n - 1] = coins[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            if (coins[i] != -1) {
                for (int j = i + 1; j < Math.min(n, i + maxJump + 1); ++j) {
                    if (f[i] > f[j] + coins[i]) {
                        f[i] = f[j] + coins[i];
                    }
                }
            }
        }
        if (f[0] == inf) {
            return ans;
        }
        for (int i = 0, s = f[0]; i < n; ++i) {
            if (f[i] == s) {
                s -= coins[i];
                ans.add(i + 1);
            }
        }
        return ans;
    }
}
```
