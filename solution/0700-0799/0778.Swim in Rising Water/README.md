# [778. 水位上升的泳池中游泳](https://leetcode.cn/problems/swim-in-rising-water)

[English Version](/solution/0700-0799/0778.Swim%20in%20Rising%20Water/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在一个 <code>n x n</code>&nbsp;的整数矩阵&nbsp;<code>grid</code> 中，每一个方格的值 <code>grid[i][j]</code> 表示位置 <code>(i, j)</code> 的平台高度。</p>

<p>当开始下雨时，在时间为&nbsp;<code>t</code>&nbsp;时，水池中的水位为&nbsp;<code>t</code>&nbsp;。你可以从一个平台游向四周相邻的任意一个平台，但是前提是此时水位必须同时淹没这两个平台。假定你可以瞬间移动无限距离，也就是默认在方格内部游动是不耗时的。当然，在你游泳的时候你必须待在坐标方格里面。</p>

<p>你从坐标方格的左上平台&nbsp;<code>(0，0)</code> 出发。返回 <em>你到达坐标方格的右下平台&nbsp;<code>(n-1, n-1)</code>&nbsp;所需的最少时间 。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0778.Swim%20in%20Rising%20Water/images/swim1-grid.jpg" /></p>

<pre>
<strong>输入:</strong> grid = [[0,2],[1,3]]
<strong>输出:</strong> 3
<strong>解释:</strong>
时间为0时，你位于坐标方格的位置为 <code>(0, 0)。</code>
此时你不能游向任意方向，因为四个相邻方向平台的高度都大于当前时间为 0 时的水位。
等时间到达 3 时，你才可以游向平台 (1, 1). 因为此时的水位是 3，坐标方格中的平台没有比水位 3 更高的，所以你可以游向坐标方格中的任意位置
</pre>

<p><strong>示例 2:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0778.Swim%20in%20Rising%20Water/images/swim2-grid-1.jpg" /></p>

<pre>
<strong>输入:</strong> grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
<strong>输出:</strong> 16
<strong>解释: </strong>最终的路线用加粗进行了标记。
我们必须等到时间为 16，此时才能保证平台 (0, 0) 和 (4, 4) 是连通的
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>n == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 50</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;&nbsp;n<sup>2</sup></code></li>
	<li><code>grid[i][j]</code>&nbsp;中每个值&nbsp;<strong>均无重复</strong></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

并查集。每经过一个时刻 t，找到此时和雨水高度相等的单元格 `(i, j)`，如果与 `(i, j)` 相邻的单元格 `(x, y)` 满足高度不超过 t，则将这两个单元格进行合并。如果在某个时刻合并之后，单元格 `(0, 0)` 与单元格 `(n - 1, n - 1)` 连通，则返回该时刻。

以下是并查集的几个常用模板。

模板 1——朴素并查集：



模板 2——维护 size 的并查集：



模板 3——维护到祖宗节点距离的并查集：



<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->



### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] p;

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        p = new int[n * n];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        int[] hi = new int[n * n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                hi[grid[i][j]] = i * n + j;
            }
        }
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int t = 0; t < n * n; ++t) {
            int i = hi[t] / n;
            int j = hi[t] % n;
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k];
                int y = j + dirs[k + 1];
                if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] <= t) {
                    p[find(x * n + y)] = find(i * n + j);
                }
                if (find(0) == find(n * n - 1)) {
                    return t;
                }
            }
        }
        return -1;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

### **TypeScript**











### **...**

```

```


