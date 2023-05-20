# [675. 为高尔夫比赛砍树](https://leetcode.cn/problems/cut-off-trees-for-golf-event)

## 题目描述

<p>你被请来给一个要举办高尔夫比赛的树林砍树。树林由一个 <code>m x n</code> 的矩阵表示， 在这个矩阵中：</p>

<ul>
	<li><code>0</code> 表示障碍，无法触碰</li>
	<li><code>1</code> 表示地面，可以行走</li>
	<li><code>比 1 大的数</code> 表示有树的单元格，可以行走，数值表示树的高度</li>
</ul>

<p>每一步，你都可以向上、下、左、右四个方向之一移动一个单位，如果你站的地方有一棵树，那么你可以决定是否要砍倒它。</p>

<p>你需要按照树的高度从低向高砍掉所有的树，每砍过一颗树，该单元格的值变为 <code>1</code>（即变为地面）。</p>

<p>你将从 <code>(0, 0)</code> 点开始工作，返回你砍完所有树需要走的最小步数。 如果你无法砍完所有的树，返回 <code>-1</code> 。</p>

<p>可以保证的是，没有两棵树的高度是相同的，并且你至少需要砍倒一棵树。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0675.Cut%20Off%20Trees%20for%20Golf%20Event/images/trees1.jpg" style="width: 242px; height: 242px;" />
<pre>
<strong>输入：</strong>forest = [[1,2,3],[0,0,4],[7,6,5]]
<strong>输出：</strong>6
<strong>解释：</strong>沿着上面的路径，你可以用 6 步，按从最矮到最高的顺序砍掉这些树。</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0675.Cut%20Off%20Trees%20for%20Golf%20Event/images/trees2.jpg" style="width: 242px; height: 242px;" />
<pre>
<strong>输入：</strong>forest = [[1,2,3],[0,0,0],[7,6,5]]
<strong>输出：</strong>-1
<strong>解释：</strong>由于中间一行被障碍阻塞，无法访问最下面一行中的树。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>forest = [[2,3,4],[0,0,5],[8,7,6]]
<strong>输出：</strong>6
<strong>解释：</strong>可以按与示例 1 相同的路径来砍掉所有的树。
(0,0) 位置的树，可以直接砍去，不用算步数。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == forest.length</code></li>
	<li><code>n == forest[i].length</code></li>
	<li><code>1 <= m, n <= 50</code></li>
	<li><code>0 <= forest[i][j] <= 10<sup>9</sup></code></li>
</ul>

## 解法

**方法一：BFS + 优先队列（A\* 算法）**

题目的一个关键信息是“所有树的高度都不同”，要按照从小到大的顺序依次砍树，因此，我们先遍历树林，找出所有树及对应的坐标点。然后将树按照高度升序排列。

接下来就是找相邻两个点之间的最短距离。可以用 BFS，A\* 算法优化搜索。

A\* 算法主要思想如下：

1. 将 BFS 队列转换为优先队列（小根堆）；
1. 队列中的每个元素为 `(dist[state] + f(state), state)`，`dist[state]` 表示从起点到当前 state 的距离，`f(state)` 表示从当前 state 到终点的估计距离，这两个距离之和作为堆排序的依据；
1. 当终点第一次出队时，说明找到了从起点到终点的最短路径，直接返回对应的 step；
1. `f(state)` 是估价函数，并且估价函数要满足 `f(state) <= g(state)`，其中 `g(state)` 表示 state 到终点的真实距离；
1. A\* 算法只能保证终点第一次出队时，即找到了一条从起点到终点的最小路径，不能保证其他点出队时也是从起点到当前点的最短路径。

### **Java**

```java
class Solution {
    private int[] dist = new int[3600];
    private List<List<Integer>> forest;
    private int m;
    private int n;

    public int cutOffTree(List<List<Integer>> forest) {
        this.forest = forest;
        m = forest.size();
        n = forest.get(0).size();
        List<int[]> trees = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (forest.get(i).get(j) > 1) {
                    trees.add(new int[] {forest.get(i).get(j), i * n + j});
                }
            }
        }
        trees.sort(Comparator.comparingInt(a -> a[0]));
        int ans = 0;
        int start = 0;
        for (int[] tree : trees) {
            int end = tree[1];
            int t = bfs(start, end);
            if (t == -1) {
                return -1;
            }
            ans += t;
            start = end;
        }
        return ans;
    }

    private int bfs(int start, int end) {
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        q.offer(new int[] {f(start, end), start});
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            int state = q.poll()[1];
            if (state == end) {
                return dist[state];
            }
            for (int k = 0; k < 4; ++k) {
                int x = state / n + dirs[k];
                int y = state % n + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && forest.get(x).get(y) > 0) {
                    if (dist[x * n + y] > dist[state] + 1) {
                        dist[x * n + y] = dist[state] + 1;
                        q.offer(new int[] {dist[x * n + y] + f(x * n + y, end), x * n + y});
                    }
                }
            }
        }
        return -1;
    }

    private int f(int start, int end) {
        int a = start / n;
        int b = start % n;
        int c = end / n;
        int d = end % n;
        return Math.abs(a - c) + Math.abs(b - d);
    }
}
```
