# [920. 播放列表的数量](https://leetcode.cn/problems/number-of-music-playlists)

## 题目描述

<p>你的音乐播放器里有 <code>n</code> 首不同的歌，在旅途中，你计划听 <code>goal</code> 首歌（不一定不同，即，允许歌曲重复）。你将会按如下规则创建播放列表：</p>

<ul>
	<li>每首歌 <strong>至少播放一次</strong> 。</li>
	<li>一首歌只有在其他 <code>k</code> 首歌播放完之后才能再次播放。</li>
</ul>

<p>给你 <code>n</code>、<code>goal</code> 和 <code>k</code> ，返回可以满足要求的播放列表的数量。由于答案可能非常大，请返回对 <code>10<sup>9</sup> + 7</code> <strong>取余</strong> 的结果。</p>
&nbsp;

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 3, goal = 3, k = 1
<strong>输出：</strong>6
<strong>解释：</strong>有 6 种可能的播放列表。[1, 2, 3]，[1, 3, 2]，[2, 1, 3]，[2, 3, 1]，[3, 1, 2]，[3, 2, 1] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 2, goal = 3, k = 0
<strong>输出：</strong>6
<strong>解释：</strong>有 6 种可能的播放列表。[1, 1, 2]，[1, 2, 1]，[2, 1, 1]，[2, 2, 1]，[2, 1, 2]，[1, 2, 2] 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 2, goal = 3, k = 1
<strong>输出：</strong>2
<strong>解释：</strong>有 2 种可能的播放列表。[1, 2, 1]，[2, 1, 2] 。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= k &lt; n &lt;= goal &lt;= 100</code></li>
</ul>

## 解法

**方法一：动态规划**

我们定义f[i][j]表示听i首歌，且这i首歌中有j首不同歌曲的播放列表的数量。初始时f[0][0]=1。答案为f[goal][n]。

对于f[i][j]，我们可以选择没听过的歌，那么上一个状态为f[i - 1][j - 1]，这样的选择有n - (j - 1) = n - j + 1种，因此f[i][j] += f[i - 1][j - 1] \times (n - j + 1)。我们也可以选择听过的歌，那么上一个状态为f[i - 1][j]，这样的选择有j - k种，因此f[i][j] += f[i - 1][j] \times (j - k)，其中j \geq k。

综上，我们可以得到状态转移方程：


f[i][j] = \begin{cases}
1 & i = 0, j = 0 \\
f[i - 1][j - 1] \times (n - j + 1) + f[i - 1][j] \times (j - k) & i \geq 1, j \geq 1
\end{cases}


最终的答案为f[goal][n]。

时间复杂度O(goal \times n)，空间复杂度O(goal \times n)。其中goal和n为题目中给定的参数。

注意到f[i][j]只与f[i - 1][j - 1]和f[i - 1][j]有关，因此我们可以使用滚动数组优化空间复杂度，时间复杂度不变。

### **Java**

```java
class Solution {
    public int numMusicPlaylists(int n, int goal, int k) {
        final int mod = (int) 1e9 + 7;
        long[][] f = new long[goal + 1][n + 1];
        f[0][0] = 1;
        for (int i = 1; i <= goal; ++i) {
            for (int j = 1; j <= n; ++j) {
                f[i][j] = f[i - 1][j - 1] * (n - j + 1);
                if (j > k) {
                    f[i][j] += f[i - 1][j] * (j - k);
                }
                f[i][j] %= mod;
            }
        }
        return (int) f[goal][n];
    }
}
```

```java
class Solution {
    public int numMusicPlaylists(int n, int goal, int k) {
        final int mod = (int) 1e9 + 7;
        long[] f = new long[n + 1];
        f[0] = 1;
        for (int i = 1; i <= goal; ++i) {
            long[] g = new long[n + 1];
            for (int j = 1; j <= n; ++j) {
                g[j] = f[j - 1] * (n - j + 1);
                if (j > k) {
                    g[j] += f[j] * (j - k);
                }
                g[j] %= mod;
            }
            f = g;
        }
        return (int) f[n];
    }
}
```
