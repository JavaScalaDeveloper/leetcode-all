# [1559. 二维网格图中探测环](https://leetcode.cn/problems/detect-cycles-in-2d-grid)

## 题目描述

<p>给你一个二维字符网格数组&nbsp;<code>grid</code>&nbsp;，大小为&nbsp;<code>m x n</code>&nbsp;，你需要检查&nbsp;<code>grid</code>&nbsp;中是否存在 <strong>相同值</strong> 形成的环。</p>

<p>一个环是一条开始和结束于同一个格子的长度 <strong>大于等于 4</strong>&nbsp;的路径。对于一个给定的格子，你可以移动到它上、下、左、右四个方向相邻的格子之一，可以移动的前提是这两个格子有 <strong>相同的值&nbsp;</strong>。</p>

<p>同时，你也不能回到上一次移动时所在的格子。比方说，环&nbsp;&nbsp;<code>(1, 1) -&gt; (1, 2) -&gt; (1, 1)</code>&nbsp;是不合法的，因为从 <code>(1, 2)</code>&nbsp;移动到 <code>(1, 1)</code> 回到了上一次移动时的格子。</p>

<p>如果 <code>grid</code>&nbsp;中有相同值形成的环，请你返回 <code>true</code>&nbsp;，否则返回 <code>false</code>&nbsp;。</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1559.Detect%20Cycles%20in%202D%20Grid/images/5482e1.png" style="height: 152px; width: 231px;"></strong></p>

<pre><strong>输入：</strong>grid = [[&quot;a&quot;,&quot;a&quot;,&quot;a&quot;,&quot;a&quot;],[&quot;a&quot;,&quot;b&quot;,&quot;b&quot;,&quot;a&quot;],[&quot;a&quot;,&quot;b&quot;,&quot;b&quot;,&quot;a&quot;],[&quot;a&quot;,&quot;a&quot;,&quot;a&quot;,&quot;a&quot;]]
<strong>输出：</strong>true
<strong>解释：</strong>如下图所示，有 2 个用不同颜色标出来的环：
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1559.Detect%20Cycles%20in%202D%20Grid/images/5482e11.png" style="height: 163px; width: 225px;">
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1559.Detect%20Cycles%20in%202D%20Grid/images/5482e2.png" style="height: 154px; width: 236px;"></strong></p>

<pre><strong>输入：</strong>grid = [[&quot;c&quot;,&quot;c&quot;,&quot;c&quot;,&quot;a&quot;],[&quot;c&quot;,&quot;d&quot;,&quot;c&quot;,&quot;c&quot;],[&quot;c&quot;,&quot;c&quot;,&quot;e&quot;,&quot;c&quot;],[&quot;f&quot;,&quot;c&quot;,&quot;c&quot;,&quot;c&quot;]]
<strong>输出：</strong>true
<strong>解释：</strong>如下图所示，只有高亮所示的一个合法环：
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1559.Detect%20Cycles%20in%202D%20Grid/images/5482e22.png" style="height: 157px; width: 229px;">
</pre>

<p><strong>示例 3：</strong></p>

<p><strong><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1559.Detect%20Cycles%20in%202D%20Grid/images/5482e3.png" style="height: 120px; width: 183px;"></strong></p>

<pre><strong>输入：</strong>grid = [[&quot;a&quot;,&quot;b&quot;,&quot;b&quot;],[&quot;b&quot;,&quot;z&quot;,&quot;b&quot;],[&quot;b&quot;,&quot;b&quot;,&quot;a&quot;]]
<strong>输出：</strong>false
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m &lt;= 500</code></li>
	<li><code>1 &lt;= n &lt;= 500</code></li>
	<li><code>grid</code>&nbsp;只包含小写英文字母。</li>
</ul>

## 解法

构造并查集，遍历每个坐标 `(i, j)`，如果下方或者右侧的元素 `(x, y)` 与当前元素 `(i, j)` 相同，进行合并操作。若是，若此前两个坐标已经处于连通状态，再进行合并时会形成环，直接返回 true。否则遍历结束返回 false。

并查集模板：

模板 1——朴素并查集：

模板 2——维护 size 的并查集：

模板 3——维护到祖宗节点距离的并查集：

### **Java**

```java
class Solution {
    private int[] p;

    public boolean containsCycle(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        p = new int[m * n];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        int[] dirs = {0, 1, 0};
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < 2; ++k) {
                    int x = i + dirs[k];
                    int y = j + dirs[k + 1];
                    if (x < m && y < n && grid[i][j] == grid[x][y]) {
                        if (find(x * n + y) == find(i * n + j)) {
                            return true;
                        }
                        p[find(x * n + y)] = find(i * n + j);
                    }
                }
            }
        }
        return false;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```
