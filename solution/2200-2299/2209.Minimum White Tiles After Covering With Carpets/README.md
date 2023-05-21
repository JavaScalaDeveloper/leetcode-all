# [2209. 用地毯覆盖后的最少白色砖块](https://leetcode.cn/problems/minimum-white-tiles-after-covering-with-carpets)

## 题目描述

<p>给你一个下标从<strong>&nbsp;0</strong>&nbsp;开始的 <strong>二进制</strong>&nbsp;字符串&nbsp;<code>floor</code>&nbsp;，它表示地板上砖块的颜色。</p>

<ul>
	<li><code>floor[i] = '0'</code>&nbsp;表示地板上第&nbsp;<code>i</code>&nbsp;块砖块的颜色是 <strong>黑色</strong>&nbsp;。</li>
	<li><code>floor[i] = '1'</code>&nbsp;表示地板上第&nbsp;<code>i</code>&nbsp;块砖块的颜色是 <strong>白色</strong>&nbsp;。</li>
</ul>

<p>同时给你&nbsp;<code>numCarpets</code> 和&nbsp;<code>carpetLen</code>&nbsp;。你有&nbsp;<code>numCarpets</code>&nbsp;条&nbsp;<strong>黑色</strong>&nbsp;的地毯，每一条&nbsp;<strong>黑色</strong>&nbsp;的地毯长度都为&nbsp;<code>carpetLen</code>&nbsp;块砖块。请你使用这些地毯去覆盖砖块，使得未被覆盖的剩余 <strong>白色</strong>&nbsp;砖块的数目 <strong>最小</strong>&nbsp;。地毯相互之间可以覆盖。</p>

<p>请你返回没被覆盖的白色砖块的 <strong>最少</strong>&nbsp;数目。</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2209.Minimum%20White%20Tiles%20After%20Covering%20With%20Carpets/images/ex1-1.png" style="width: 400px; height: 73px;"></p>

<pre><b>输入：</b>floor = "10110101", numCarpets = 2, carpetLen = 2
<b>输出：</b>2
<b>解释：</b>
上图展示了剩余 2 块白色砖块的方案。
没有其他方案可以使未被覆盖的白色砖块少于 2 块。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2209.Minimum%20White%20Tiles%20After%20Covering%20With%20Carpets/images/ex2.png" style="width: 353px; height: 123px;"></p>

<pre><b>输入：</b>floor = "11111", numCarpets = 2, carpetLen = 3
<b>输出：</b>0
<b>解释：</b>
上图展示了所有白色砖块都被覆盖的一种方案。
注意，地毯相互之间可以覆盖。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= carpetLen &lt;= floor.length &lt;= 1000</code></li>
	<li><code>floor[i]</code> 要么是&nbsp;<code>'0'</code>&nbsp;，要么是&nbsp;<code>'1'</code>&nbsp;。</li>
	<li><code>1 &lt;= numCarpets &lt;= 1000</code></li>
</ul>

## 解法

**方法一：记忆化搜索**

设计函数dfs(i, j)表示从下标i开始，使用j条地毯，最少有多少个白色砖块没有被覆盖。答案即为dfs(0, numCarpets)。

对于下标i，我们分情况讨论：

-   如果i ≥ n，说明已经覆盖完所有砖块，返回0；
-   如果floor[i] = 0，则不需要使用地毯，直接跳过即可，即dfs(i, j) = dfs(i + 1, j)；
-   如果j = 0，那么我们可以直接利用前缀和数组s计算出剩余未被覆盖的白色砖块的数目，即dfs(i, j) = s[n] - s[i]；
-   如果floor[i] = 1，那么我们可以选择使用地毯覆盖，也可以选择不使用地毯覆盖，取两者的最小值即可，即dfs(i, j) = min(dfs(i + 1, j), dfs(i + carpetLen, j - 1))。

记忆化搜索即可。

时间复杂度O(n× m)，空间复杂度O(n× m)。其中n和m分别为字符串floor的长度和numCarpets的值。

### **Java**

```java
class Solution {
    private int[][] f;
    private int[] s;
    private int n;
    private int k;

    public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
        n = floor.length();
        f = new int[n][numCarpets + 1];
        for (var e : f) {
            Arrays.fill(e, -1);
        }
        s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + (floor.charAt(i) == '1' ? 1 : 0);
        }
        k = carpetLen;
        return dfs(0, numCarpets);
    }

    private int dfs(int i, int j) {
        if (i >= n) {
            return 0;
        }
        if (j == 0) {
            return s[n] - s[i];
        }
        if (f[i][j] != -1) {
            return f[i][j];
        }
        if (s[i + 1] == s[i]) {
            return dfs(i + 1, j);
        }
        int ans = Math.min(1 + dfs(i + 1, j), dfs(i + k, j - 1));
        f[i][j] = ans;
        return ans;
    }
}
```
