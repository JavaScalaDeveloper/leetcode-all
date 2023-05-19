# [面试题 13. 机器人的运动范围](https://leetcode.cn/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/)

## 题目描述

<p>地上有一个m行n列的方格，从坐标 <code>[0,0]</code> 到坐标 <code>[m-1,n-1]</code> 。一个机器人从坐标 <code>[0, 0] </code>的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>m = 2, n = 3, k = 1
<strong>输出：</strong>3
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>m = 3, n = 1, k = 0
<strong>输出：</strong>1
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n,m &lt;= 100</code></li>
	<li><code>0 &lt;= k&nbsp;&lt;= 20</code></li>
</ul>

## 解法

**方法一：DFS + 哈希表**

由于部分单元格不可达，因此，我们不能直接枚举所有坐标点 $(i, j)$ 进行判断，而是应该从起点 $(0, 0)$ 出发，搜索所有可达的节点，记录答案。

过程中，为了避免重复搜索同一个单元格，我们可以使用数组或哈希表记录所有访问过的节点。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别为方格的行数和列数。

<!-- tabs:start -->

### **Python3**





### **Java**

```java
class Solution {
    private boolean[][] vis;
    private int m;
    private int n;
    private int k;
    private int ans;

    public int movingCount(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        vis = new boolean[m][n];
        dfs(0, 0);
        return ans;
    }

    private void dfs(int i, int j) {
        vis[i][j] = true;
        ++ans;
        int[] dirs = {1, 0, 1};
        for (int l = 0; l < 2; ++l) {
            int x = i + dirs[l], y = j + dirs[l + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && f(x) + f(y) <= k && !vis[x][y]) {
                dfs(x, y);
            }
        }
    }

    private int f(int x) {
        int s = 0;
        for (; x > 0; x /= 10) {
            s += x % 10;
        }
        return s;
    }
}
```

```java
class Solution {
    private boolean[][] vis;
    private int m;
    private int n;
    private int k;

    public int movingCount(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        vis = new boolean[m][n];
        return dfs(0, 0);
    }

    private int dfs(int i, int j) {
        if (i >= m || j >= n || vis[i][j] || (i % 10 + i / 10 + j % 10 + j / 10) > k) {
            return 0;
        }
        vis[i][j] = true;
        return 1 + dfs(i + 1, j) + dfs(i, j + 1);
    }
}
```

















### **TypeScript**





循环：



递归：







### **...**

```

```


