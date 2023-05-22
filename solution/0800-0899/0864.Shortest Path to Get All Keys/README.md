# [864. 获取所有钥匙的最短路径](https://leetcode.cn/problems/shortest-path-to-get-all-keys)

## 题目描述

<p>给定一个二维网格&nbsp;<code>grid</code>&nbsp;，其中：</p>

<ul>
	<li><font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size:12.6px"><span style="background-color:#f9f2f4">'.'</span></span></font></font> 代表一个空房间</li>
	<li><font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size:12.6px"><span style="background-color:#f9f2f4">'#'</span></span></font></font> 代表一堵墙</li>
	<li><font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size:12.6px"><span style="background-color:#f9f2f4">'@'</span></span></font></font>&nbsp;是起点</li>
	<li>小写字母代表钥匙</li>
	<li>大写字母代表锁</li>
</ul>

<p>我们从起点开始出发，一次移动是指向四个基本方向之一行走一个单位空间。我们不能在网格外面行走，也无法穿过一堵墙。如果途经一个钥匙，我们就把它捡起来。除非我们手里有对应的钥匙，否则无法通过锁。</p>

<p>假设 k&nbsp;为 钥匙/锁 的个数，且满足&nbsp;<code>1 &lt;= k&nbsp;&lt;= 6</code>，字母表中的前 <code>k</code>&nbsp;个字母在网格中都有自己对应的一个小写和一个大写字母。换言之，每个锁有唯一对应的钥匙，每个钥匙也有唯一对应的锁。另外，代表钥匙和锁的字母互为大小写并按字母顺序排列。</p>

<p>返回获取所有钥匙所需要的移动的最少次数。如果无法获取所有钥匙，返回&nbsp;<code>-1</code>&nbsp;。</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0864.Shortest%20Path%20to%20Get%20All%20Keys/images/lc-keys2.jpg" /></p>

<pre>
<strong>输入：</strong>grid = ["@.a.#","###.#","b.A.B"]
<strong>输出：</strong>8
<strong>解释：</strong>目标是获得所有钥匙，而不是打开所有锁。
</pre>

<p><strong>示例 2：</strong></p>

<p><img src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0864.Shortest%20Path%20to%20Get%20All%20Keys/images/lc-key2.jpg" /></p>

<pre>
<strong>输入：</strong>grid = ["@..aA","..B#.","....b"]
<strong>输出：</strong>6
</pre>

<p><strong>示例 3:</strong></p>
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0864.Shortest%20Path%20to%20Get%20All%20Keys/images/lc-keys3.jpg" />
<pre>
<strong>输入:</strong> grid = ["@Aa"]
<strong>输出:</strong> -1</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 30</code></li>
	<li><code>grid[i][j]</code>&nbsp;只含有&nbsp;<code>'.'</code>,&nbsp;<code>'#'</code>,&nbsp;<code>'@'</code>,&nbsp;<code>'a'-</code><code>'f</code><code>'</code>&nbsp;以及&nbsp;<code>'A'-'F'</code></li>
	<li>钥匙的数目范围是&nbsp;<code>[1, 6]</code>&nbsp;</li>
	<li>每个钥匙都对应一个 <strong>不同</strong> 的字母</li>
	<li>每个钥匙正好打开一个对应的锁</li>
</ul>

## 解法

**方法一：状态压缩 + BFS**

根据题意，我们需要从起点出发，往上下左右四个方向走，获取所有钥匙，最后返回获取所有钥匙所需要的移动的最少次数。若无法获取所有钥匙，返回-1。

首先，我们遍历二维网格，找到起点位置(si, sj)，并统计钥匙的个数k。

然后，我们可以使用广度优先搜索BFS来解决本题。由于钥匙的个数范围是[1, 6]，我们可以用一个二进制数来表示钥匙的状态，其中第i位为1表示第i把钥匙已经获取到了，为0表示第i把钥匙还没有获取到。

比如，以下例子中，共有4个二进制位为1，也就表示有 `'b', 'c', 'd', 'f'`4把钥匙已经获取到了。

```
1 0 1 1 1 0
^   ^ ^ ^
f   d c b
```

我们定义一个队列q来存储当前位置以及当前拥有的钥匙的状态，即(i, j, state)，其中(i, j)表示当前位置，state表示当前拥有的钥匙的状态，即state的第i位为1表示当前拥有第i把钥匙，否则表示当前没有第i把钥匙。

另外，定义哈希表或数组vis记录当前位置以及当前拥有的钥匙的状态是否已经被访问过，如果访问过，则不需要再次访问。vis[i][j][state]表示当前位置为(i, j)，当前拥有的钥匙的状态为state时，是否已经被访问过。

我们从起点(si, sj)出发，将其加入队列q，并将vis[si][sj][0]置为true，表示起点位置以及拥有的钥匙的状态为0时已经被访问过。

在广度优先搜索的过程中，我们每次从队首取出一个位置(i, j, state)，并判断当前位置是否为终点，即当前位置是否拥有所有的钥匙，即state的二进制表示中的1的个数是否为k。如果是，将当前步数作为答案返回。

否则，我们从当前位置出发，往上下左右四个方向走，如果可以走到下一个位置(x, y)，则将(x, y, nxt)加入队列q，其中nxt表示下一个位置的钥匙的状态。

这里(x, y)首先需要满足在网格范围内，即0 ≤ x < m且0 ≤ y < n。其次，如果(x, y)位置是墙壁，即 `grid[x][y] == '#'`，或者(x, y)位置是锁，但我们没有对应的钥匙，即 `grid[x][y] >= 'A' && grid[x][y] <= 'F' && (state >> (grid[x][y] - 'A') & 1) == 0)`，则不能走到位置(x, y)。否则，我们可以走到位置(x, y)。

搜索结束，没能找到所有的钥匙，返回-1。

时间复杂度O(m× n× 2^k)，空间复杂度O(m× n× 2^k)。其中m和n分别为网格的行数和列数，而k为钥匙的个数。

### **Java**

```java
class Solution {
    private int[] dirs = {-1, 0, 1, 0, -1};

    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length, n = grid[0].length();
        int k = 0;
        int si = 0, sj = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                char c = grid[i].charAt(j);
                if (Character.isLowerCase(c)) {
                    // 累加钥匙数量
                    ++k;
                } else if (c == '@') {
                    // 起点
                    si = i;
                    sj = j;
                }
            }
        }
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {si, sj, 0});
        boolean[][][] vis = new boolean[m][n][1 << k];
        vis[si][sj][0] = true;
        int ans = 0;
        while (!q.isEmpty()) {
            for (int t = q.size(); t > 0; --t) {
                var p = q.poll();
                int i = p[0], j = p[1], state = p[2];
                // 找到所有钥匙，返回当前步数
                if (state == (1 << k) - 1) {
                    return ans;
                }
                // 往四个方向搜索
                for (int h = 0; h < 4; ++h) {
                    int x = i + dirs[h], y = j + dirs[h + 1];
                    // 在边界范围内
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        char c = grid[x].charAt(y);
                        // 是墙，或者是锁，但此时没有对应的钥匙，无法通过
                        if (c == '#'
                            || (Character.isUpperCase(c) && ((state >> (c - 'A')) & 1) == 0)) {
                            continue;
                        }
                        int nxt = state;
                        // 是钥匙
                        if (Character.isLowerCase(c)) {
                            // 更新状态
                            nxt |= 1 << (c - 'a');
                        }
                        // 此状态未访问过，入队
                        if (!vis[x][y][nxt]) {
                            vis[x][y][nxt] = true;
                            q.offer(new int[] {x, y, nxt});
                        }
                    }
                }
            }
            // 步数加一
            ++ans;
        }
        return -1;
    }
}
```
