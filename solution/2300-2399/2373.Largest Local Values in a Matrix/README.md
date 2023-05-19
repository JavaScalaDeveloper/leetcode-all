# [2373. 矩阵中的局部最大值](https://leetcode.cn/problems/largest-local-values-in-a-matrix)

[English Version](/solution/2300-2399/2373.Largest%20Local%20Values%20in%20a%20Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个大小为 <code>n x n</code> 的整数矩阵 <code>grid</code> 。</p>

<p>生成一个大小为&nbsp;<code>(n - 2) x (n - 2)</code> 的整数矩阵&nbsp; <code>maxLocal</code> ，并满足：</p>

<ul>
	<li><code>maxLocal[i][j]</code> 等于 <code>grid</code> 中以 <code>i + 1</code> 行和 <code>j + 1</code> 列为中心的 <code>3 x 3</code> 矩阵中的 <strong>最大值</strong> 。</li>
</ul>

<p>换句话说，我们希望找出 <code>grid</code> 中每个&nbsp;<code>3 x 3</code> 矩阵中的最大值。</p>

<p>返回生成的矩阵。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2373.Largest%20Local%20Values%20in%20a%20Matrix/images/ex1.png" style="width: 371px; height: 210px;" /></p>

<pre>
<strong>输入：</strong>grid = [[9,9,8,1],[5,6,2,6],[8,2,6,4],[6,2,2,2]]
<strong>输出：</strong>[[9,9],[8,6]]
<strong>解释：</strong>原矩阵和生成的矩阵如上图所示。
注意，生成的矩阵中，每个值都对应 grid 中一个相接的 3 x 3 矩阵的最大值。</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2300-2399/2373.Largest%20Local%20Values%20in%20a%20Matrix/images/ex2new2.png" style="width: 436px; height: 240px;" /></p>

<pre>
<strong>输入：</strong>grid = [[1,1,1,1,1],[1,1,1,1,1],[1,1,2,1,1],[1,1,1,1,1],[1,1,1,1,1]]
<strong>输出：</strong>[[2,2,2],[2,2,2],[2,2,2]]
<strong>解释：</strong>注意，2 包含在 grid 中每个 3 x 3 的矩阵中。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == grid.length == grid[i].length</code></li>
	<li><code>3 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 100</code></li>
</ul>

## 解法

**方法一：枚举**

我们可以枚举每个 $3 \times 3$ 的矩阵，求出每个 $3 \times 3$ 的矩阵中的最大值，然后将这些最大值放入答案矩阵中。

时间复杂度 $O(n^2)$，其中 $n$ 是矩阵的边长。忽略答案矩阵的空间消耗，空间复杂度 $O(1)$。

### **Java**

```java
class Solution {
    public int[][] largestLocal(int[][] grid) {
        int n = grid.length;
        int[][] ans = new int[n - 2][n - 2];
        for (int i = 0; i < n - 2; ++i) {
            for (int j = 0; j < n - 2; ++j) {
                for (int x = i; x <= i + 2; ++x) {
                    for (int y = j; y <= j + 2; ++y) {
                        ans[i][j] = Math.max(ans[i][j], grid[x][y]);
                    }
                }
            }
        }
        return ans;
    }
}
```

### **TypeScript**
