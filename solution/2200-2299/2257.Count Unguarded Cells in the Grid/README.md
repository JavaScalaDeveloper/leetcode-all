# [2257. 统计网格图中没有被保卫的格子数](https://leetcode.cn/problems/count-unguarded-cells-in-the-grid)

## 题目描述

<p>给你两个整数&nbsp;<code>m</code>&nbsp;和&nbsp;<code>n</code>&nbsp;表示一个下标从<strong>&nbsp;0</strong>&nbsp;开始的&nbsp;<code>m x n</code>&nbsp;网格图。同时给你两个二维整数数组&nbsp;<code>guards</code> 和&nbsp;<code>walls</code>&nbsp;，其中&nbsp;<code>guards[i] = [row<sub>i</sub>, col<sub>i</sub>]</code>&nbsp;且&nbsp;<code>walls[j] = [row<sub>j</sub>, col<sub>j</sub>]</code>&nbsp;，分别表示第 <code>i</code>&nbsp;个警卫和第 <code>j</code>&nbsp;座墙所在的位置。</p>

<p>一个警卫能看到 4 个坐标轴方向（即东、南、西、北）的 <strong>所有</strong>&nbsp;格子，除非他们被一座墙或者另外一个警卫 <strong>挡住</strong>&nbsp;了视线。如果一个格子能被 <strong>至少</strong>&nbsp;一个警卫看到，那么我们说这个格子被 <strong>保卫</strong>&nbsp;了。</p>

<p>请你返回空格子中，有多少个格子是 <strong>没被保卫</strong>&nbsp;的。</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2257.Count%20Unguarded%20Cells%20in%20the%20Grid/images/example1drawio2.png" style="width: 300px; height: 204px;"></p>

<pre><b>输入：</b>m = 4, n = 6, guards = [[0,0],[1,1],[2,3]], walls = [[0,1],[2,2],[1,4]]
<b>输出：</b>7
<strong>解释：</strong>上图中，被保卫和没有被保卫的格子分别用红色和绿色表示。
总共有 7 个没有被保卫的格子，所以我们返回 7 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2257.Count%20Unguarded%20Cells%20in%20the%20Grid/images/example2drawio.png" style="width: 200px; height: 201px;"></p>

<pre><b>输入：</b>m = 3, n = 3, guards = [[1,1]], walls = [[0,1],[1,0],[2,1],[1,2]]
<b>输出：</b>4
<b>解释：</b>上图中，没有被保卫的格子用绿色表示。
总共有 4 个没有被保卫的格子，所以我们返回 4 。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
	<li><code>2 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= guards.length, walls.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>2 &lt;= guards.length + walls.length &lt;= m * n</code></li>
	<li><code>guards[i].length == walls[j].length == 2</code></li>
	<li><code>0 &lt;= row<sub>i</sub>, row<sub>j</sub> &lt; m</code></li>
	<li><code>0 &lt;= col<sub>i</sub>, col<sub>j</sub> &lt; n</code></li>
	<li><code>guards</code>&nbsp;和&nbsp;<code>walls</code>&nbsp;中所有位置 <strong>互不相同</strong>&nbsp;。</li>
</ul>

## 解法

**方法一：模拟**

我们创建一个m × n的二维数组g，其中g[i][j]表示第i行第j列的格子。初始时，g[i][j]的值为0，表示该格子没有被保卫。

然后遍历所有的警卫和墙，将g[i][j]的值置为2，这些位置不能被访问。

接下来，我们遍历所有警卫的位置，从该位置出发，向四个方向进行模拟，直到遇到墙或警卫，或者越界。在模拟的过程中，将遇到的格子的值置为1，表示该格子被保卫。

最后，我们遍历g，统计值为0的格子的个数，即为答案。

时间复杂度O(m × n)，空间复杂度O(m × n)。其中m和n分别为网格的行数和列数。

### **Java**

```java
class Solution {
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] g = new int[m][n];
        for (var e : guards) {
            g[e[0]][e[1]] = 2;
        }
        for (var e : walls) {
            g[e[0]][e[1]] = 2;
        }
        int[] dirs = {-1, 0, 1, 0, -1};
        for (var e : guards) {
            for (int k = 0; k < 4; ++k) {
                int x = e[0], y = e[1];
                int a = dirs[k], b = dirs[k + 1];
                while (x + a >= 0 && x + a < m && y + b >= 0 && y + b < n && g[x + a][y + b] < 2) {
                    x += a;
                    y += b;
                    g[x][y] = 1;
                }
            }
        }
        int ans = 0;
        for (var row : g) {
            for (int v : row) {
                if (v == 0) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}
```
