# [827. 最大人工岛](https://leetcode.cn/problems/making-a-large-island)

## 题目描述

<p>给你一个大小为 <code>n x n</code> 二进制矩阵 <code>grid</code> 。<strong>最多</strong> 只能将一格 <code>0</code> 变成 <code>1</code> 。</p>

<p>返回执行此操作后，<code>grid</code> 中最大的岛屿面积是多少？</p>

<p><strong>岛屿</strong> 由一组上、下、左、右四个方向相连的 <code>1</code> 形成。</p>



<p><strong>示例 1:</strong></p>

<pre>
<strong>输入: </strong>grid = [[1, 0], [0, 1]]
<strong>输出:</strong> 3
<strong>解释:</strong> 将一格0变成1，最终连通两个小岛得到面积为 3 的岛屿。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入: </strong>grid =<strong> </strong>[[1, 1], [1, 0]]
<strong>输出:</strong> 4
<strong>解释:</strong> 将一格0变成1，岛屿的面积扩大为 4。</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入: </strong>grid = [[1, 1], [1, 1]]
<strong>输出:</strong> 4
<strong>解释:</strong> 没有0可以让我们变成1，面积依然为 4。</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>n == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 <= n <= 500</code></li>
	<li><code>grid[i][j]</code> 为 <code>0</code> 或 <code>1</code></li>
</ul>

## 解法

**方法一：并查集**

并查集是一种树形的数据结构，顾名思义，它用于处理一些不交集的**合并**及**查询**问题。 它支持两种操作：

1. 查找（Find）：确定某个元素处于哪个子集，单次操作时间复杂度O(\alpha(n))
1. 合并（Union）：将两个子集合并成一个集合，单次操作时间复杂度O(\alpha(n))

其中\alpha为阿克曼函数的反函数，其增长极其缓慢，也就是说其单次操作的平均运行时间可以认为是一个很小的常数。

以下是并查集的常用模板，需要熟练掌握。其中：

-   `n` 表示节点数
-   `p` 存储每个点的父节点，初始时每个点的父节点都是自己
-   `size` 只有当节点是祖宗节点时才有意义，表示祖宗节点所在集合中，点的数量
-   `find(x)` 函数用于查找x所在集合的祖宗节点
-   `union(a, b)` 函数用于合并a和b所在的集合

在这道题中，相邻的1组成一个岛屿，因此，我们需要将相邻的1归到同一个集合中。这可以视为一个合并操作，不难想到用并查集来实现。

第一次遍历 `grid`，通过并查集的 `union` 操作合并所有相邻的1，并且统计每个岛屿的面积，记录在size数组中。

再次遍历 `grid`，对于每个0，我们统计相邻的四个点中1所在的岛屿（通过并查集的 `find` 操作找到所在岛屿），累加去重后的岛屿面积，更新最大值。

时间复杂度O(n^2\times \alpha(n))。其中n为矩阵 `grid` 的边长。

**方法二：DFS**

我们也可以通过 DFS，找到每个岛屿。

同一个岛屿中的所有点都属于同一个集合，我们可以用不同的 `root` 值标识不同的岛屿，用p记录每个grid[i][j]对应的 `root` 值，用cnt记录每个岛屿的面积。

遍历 `grid`，对于每个0，我们统计相邻的四个点中1所在的岛屿（与方法一不同的是，我们这里直接取p[i][j]作为 `root`），累加去重后的岛屿面积，更新最大值。

时间复杂度O(n^2)。其中n为矩阵 `grid` 的边长。

### **Java**

```java
class Solution {
    private int n;
    private int[] p;
    private int[] size;
    private int ans = 1;
    private int[] dirs = new int[] {-1, 0, 1, 0, -1};

    public int largestIsland(int[][] grid) {
        n = grid.length;
        p = new int[n * n];
        size = new int[n * n];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
            size[i] = 1;
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    for (int k = 0; k < 4; ++k) {
                        int x = i + dirs[k], y = j + dirs[k + 1];
                        if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 1) {
                            int pa = find(x * n + y), pb = find(i * n + j);
                            if (pa == pb) {
                                continue;
                            }
                            p[pa] = pb;
                            size[pb] += size[pa];
                            ans = Math.max(ans, size[pb]);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    int t = 1;
                    Set<Integer> vis = new HashSet<>();
                    for (int k = 0; k < 4; ++k) {
                        int x = i + dirs[k], y = j + dirs[k + 1];
                        if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 1) {
                            int root = find(x * n + y);
                            if (!vis.contains(root)) {
                                vis.add(root);
                                t += size[root];
                            }
                        }
                    }
                    ans = Math.max(ans, t);
                }
            }
        }
        return ans;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

```java
class Solution {
    private int n;
    private int ans;
    private int root;
    private int[][] p;
    private int[][] grid;
    private int[] cnt;
    private int[] dirs = new int[] {-1, 0, 1, 0, -1};

    public int largestIsland(int[][] grid) {
        n = grid.length;
        cnt = new int[n * n + 1];
        p = new int[n][n];
        this.grid = grid;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1 && p[i][j] == 0) {
                    ++root;
                    dfs(i, j);
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    int t = 1;
                    Set<Integer> vis = new HashSet<>();
                    for (int k = 0; k < 4; ++k) {
                        int x = i + dirs[k], y = j + dirs[k + 1];
                        if (x >= 0 && x < n && y >= 0 && y < n) {
                            int root = p[x][y];
                            if (!vis.contains(root)) {
                                vis.add(root);
                                t += cnt[root];
                            }
                        }
                    }
                    ans = Math.max(ans, t);
                }
            }
        }
        return ans;
    }

    private void dfs(int i, int j) {
        p[i][j] = root;
        ++cnt[root];
        ans = Math.max(ans, cnt[root]);
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 1 && p[x][y] == 0) {
                dfs(x, y);
            }
        }
    }
}
```
