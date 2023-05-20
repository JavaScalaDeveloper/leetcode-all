# [542. 01 矩阵](https://leetcode.cn/problems/01-matrix)

## 题目描述

<p>给定一个由 <code>0</code> 和 <code>1</code> 组成的矩阵 <code>mat</code> ，请输出一个大小相同的矩阵，其中每一个格子是 <code>mat</code> 中对应位置元素到最近的 <code>0</code> 的距离。</p>

<p>两个相邻元素间的距离为 <code>1</code> 。</p>

<p> </p>

<p><b>示例 1：</b></p>

<p><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0542.01%20Matrix/images/1626667201-NCWmuP-image.png" style="width: 150px; " /></p>

<pre>
<strong>输入：</strong>mat =<strong> </strong>[[0,0,0],[0,1,0],[0,0,0]]
<strong>输出：</strong>[[0,0,0],[0,1,0],[0,0,0]]
</pre>

<p><b>示例 2：</b></p>

<p><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0542.01%20Matrix/images/1626667205-xFxIeK-image.png" style="width: 150px; " /></p>

<pre>
<b>输入：</b>mat =<b> </b>[[0,0,0],[0,1,0],[1,1,1]]
<strong>输出：</strong>[[0,0,0],[0,1,0],[1,2,1]]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == mat.length</code></li>
	<li><code>n == mat[i].length</code></li>
	<li><code>1 <= m, n <= 10<sup>4</sup></code></li>
	<li><code>1 <= m * n <= 10<sup>4</sup></code></li>
	<li><code>mat[i][j] is either 0 or 1.</code></li>
	<li><code>mat</code> 中至少有一个 <code>0 </code></li>
</ul>

## 解法

**方法一：多源 BFS**

初始化结果矩阵 ans，所有 0 的距离为 0，所以 1 的距离为 -1。初始化队列 q 存储 BFS 需要检查的位置，并将所有 0 的位置入队。

循环弹出队列 q 的元素 `p(i, j)`，检查邻居四个点。对于邻居 `(x, y)`，如果 `ans[x][y] = -1`，则更新 `ans[x][y] = ans[i][j] + 1`。同时将 `(x, y)` 入队。

### **Java**

```java
class Solution {

    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(ans[i], -1);
        }
        Deque<int[]> q = new LinkedList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (mat[i][j] == 0) {
                    ans[i][j] = 0;
                    q.offer(new int[] {i, j});
                }
            }
        }
        int[] dirs = new int[] {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            int[] t = q.poll();
            for (int i = 0; i < 4; ++i) {
                int x = t[0] + dirs[i];
                int y = t[1] + dirs[i + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && ans[x][y] == -1) {
                    ans[x][y] = ans[t[0]][t[1]] + 1;
                    q.offer(new int[] {x, y});
                }
            }
        }
        return ans;
    }
}
```
