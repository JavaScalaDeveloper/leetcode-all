# [473. 火柴拼正方形](https://leetcode.cn/problems/matchsticks-to-square)

## 题目描述

<p>你将得到一个整数数组 <code>matchsticks</code> ，其中 <code>matchsticks[i]</code> 是第 <code>i</code>&nbsp;个火柴棒的长度。你要用 <strong>所有的火柴棍</strong>&nbsp;拼成一个正方形。你 <strong>不能折断</strong> 任何一根火柴棒，但你可以把它们连在一起，而且每根火柴棒必须 <strong>使用一次</strong> 。</p>

<p>如果你能使这个正方形，则返回 <code>true</code> ，否则返回 <code>false</code> 。</p>

<p><strong>示例&nbsp;1:</strong></p>

<p><img src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0473.Matchsticks%20to%20Square/images/matchsticks1-grid.jpg" /></p>

<pre>
<strong>输入:</strong> matchsticks = [1,1,2,2,2]
<strong>输出:</strong> true
<strong>解释:</strong> 能拼成一个边长为2的正方形，每边两根火柴。
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入:</strong> matchsticks = [3,3,3,3,4]
<strong>输出:</strong> false
<strong>解释:</strong> 不能用所有火柴拼成一个正方形。
</pre>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= matchsticks.length &lt;= 15</code></li>
	<li><code>1 &lt;= matchsticks[i] &lt;= 10<sup>8</sup></code></li>
</ul>

## 解法

**方法一：排序 + 回溯**

用 $edges[i]$ 记录正方形每条边当前的长度，对于第 $u$ 根火柴，尝试把它加到 $edges[i]$ 每条边，若加入后 $edges[i]$ 不超过正方形期望长度 $x$，则继续往下递归 $u+1$ 根火柴。若所有火柴都能被加入，说明满足拼成正方形的要求。

这里对 $matchsticks$ 从大到小排序，可以减少搜索次数。

时间复杂度 $O(4^n)$，其中 $n$ 表示 $matchsticks$ 的长度。每根火柴可以被放入正方形的 $4$ 条边，共有 $n$ 根火柴。

**方法二：状态压缩 + 记忆化搜索**

记当前火柴被划分的情况为 $state$。对于第 $i$ 个数，若 $state \ \& \ (1<<i)=0$，说明第 $i$ 个火柴棒未被划分。我们的目标是从全部数字中凑出 $k$ 个和为 $s$ 的子集。

记当前子集的和为 $t$。在未划分第 $i$ 个火柴棒时：

-   若 $t+matchsticks[i]>s$，说明第 $i$ 个火柴棒不能被添加到当前子集中，由于我们对 $matchsticks$ 数组进行升序排列，因此从 $matchsticks$ 从第 $i$ 个火柴棒开始的所有数字都不能被添加到当前子集，直接返回 $false$。
-   否则，将第 $i$ 个火柴棒添加到当前子集中，状态变为 $state \ |\ (1<<i)$，继续对未划分的数字进行搜索。

注：若 $t+matchsticks[i]==s$，说明恰好可以得到一个和为 $s$ 的子集，下一步将 $t$ 归零（可以通过 $(t+matchsticks[i]) \%s$ 实现），并继续划分下一个子集。

### **Java**

```java
class Solution {
    public boolean makesquare(int[] matchsticks) {
        int s = 0, mx = 0;
        for (int v : matchsticks) {
            s += v;
            mx = Math.max(mx, v);
        }
        int x = s / 4, mod = s % 4;
        if (mod != 0 || x < mx) {
            return false;
        }
        Arrays.sort(matchsticks);
        int[] edges = new int[4];
        return dfs(matchsticks.length - 1, x, matchsticks, edges);
    }

    private boolean dfs(int u, int x, int[] matchsticks, int[] edges) {
        if (u < 0) {
            return true;
        }
        for (int i = 0; i < 4; ++i) {
            if (i > 0 && edges[i - 1] == edges[i]) {
                continue;
            }
            edges[i] += matchsticks[u];
            if (edges[i] <= x && dfs(u - 1, x, matchsticks, edges)) {
                return true;
            }
            edges[i] -= matchsticks[u];
        }
        return false;
    }
}
```
