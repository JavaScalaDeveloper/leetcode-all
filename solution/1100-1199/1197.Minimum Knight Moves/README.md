# [1197. 进击的骑士](https://leetcode.cn/problems/minimum-knight-moves)

## 题目描述

<p>一个坐标可以从 <code>-infinity</code>&nbsp;延伸到&nbsp;<code>+infinity</code>&nbsp;的 <strong>无限大的</strong>&nbsp;棋盘上，你的 <strong>骑士&nbsp;</strong>驻扎在坐标为&nbsp;<code>[0, 0]</code>&nbsp;的方格里。</p>

<p>骑士的走法和中国象棋中的马相似，走 “日” 字：即先向左（或右）走 1 格，再向上（或下）走 2 格；或先向左（或右）走 2 格，再向上（或下）走 1 格。</p>

<p>每次移动，他都可以按图示八个方向之一前进。</p>

<p><img src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1197.Minimum%20Knight%20Moves/images/knight.png" style="width: 250px; height: 250px;" /></p>

<p>返回 <em>骑士前去征服坐标为&nbsp;<code>[x, y]</code>&nbsp;的部落所需的最小移动次数</em> 。本题确保答案是一定存在的。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>x = 2, y = 1
<strong>输出：</strong>1
<strong>解释：</strong>[0, 0] → [2, 1]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>x = 5, y = 5
<strong>输出：</strong>4
<strong>解释：</strong>[0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-300 &lt;= x, y &lt;= 300</code></li>
	<li><code>0 &lt;= |x| + |y| &lt;= 300</code></li>
</ul>

## 解法

BFS 最短路模型。本题搜索空间不大，可以直接使用朴素 BFS，以下题解中还提供了双向 BFS 的题解代码，仅供参考。

双向 BFS 是 BFS 常见的一个优化方法，主要实现思路如下：

1. 创建两个队列 q1, q2 分别用于“起点 -> 终点”、“终点 -> 起点”两个方向的搜索；
2. 创建两个哈希表 m1, m2 分别记录访问过的节点以及对应的扩展次数（步数）；
3. 每次搜索时，优先选择元素数量较少的队列进行搜索扩展，如果在扩展过程中，搜索到另一个方向已经访问过的节点，说明找到了最短路径；
4. 只要其中一个队列为空，说明当前方向的搜索已经进行不下去了，说明起点到终点不连通，无需继续搜索。

双向 BFS：

### **Java**

```java
class Solution {
    public int minKnightMoves(int x, int y) {
        x += 310;
        y += 310;
        int ans = 0;
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{310, 310});
        boolean[][] vis = new boolean[700][700];
        vis[310][310] = true;
        int[][] dirs = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
        while (!q.isEmpty()) {
            for (int k = q.size(); k > 0; --k) {
                int[] p = q.poll();
                if (p[0] == x && p[1] == y) {
                    return ans;
                }
                for (int[] dir : dirs) {
                    int c = p[0] + dir[0];
                    int d = p[1] + dir[1];
                    if (!vis[c][d]) {
                        vis[c][d] = true;
                        q.offer(new int[]{c, d});
                    }
                }
            }
            ++ans;
        }
        return -1;
    }
}
```

双向 BFS：

```java
class Solution {
    private int n = 700;

    public int minKnightMoves(int x, int y) {
        if (x == 0 && y == 0) {
            return 0;
        }
        x += 310;
        y += 310;
        Map<Integer, Integer> m1 = new HashMap<>();
        Map<Integer, Integer> m2 = new HashMap<>();
        m1.put(310 * n + 310, 0);
        m2.put(x * n + y, 0);
        Queue<int[]> q1 = new ArrayDeque<>();
        Queue<int[]> q2 = new ArrayDeque<>();
        q1.offer(new int[] {310, 310});
        q2.offer(new int[] {x, y});
        while (!q1.isEmpty() && !q2.isEmpty()) {
            int t = q1.size() <= q2.size() ? extend(m1, m2, q1) : extend(m2, m1, q2);
            if (t != -1) {
                return t;
            }
        }
        return -1;
    }

    private int extend(Map<Integer, Integer> m1, Map<Integer, Integer> m2, Queue<int[]> q) {
        int[][] dirs = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
        for (int k = q.size(); k > 0; --k) {
            int[] p = q.poll();
            int step = m1.get(p[0] * n + p[1]);
            for (int[] dir : dirs) {
                int x = p[0] + dir[0];
                int y = p[1] + dir[1];
                if (m1.containsKey(x * n + y)) {
                    continue;
                }
                if (m2.containsKey(x * n + y)) {
                    return step + 1 + m2.get(x * n + y);
                }
                m1.put(x * n + y, step + 1);
                q.offer(new int[] {x, y});
            }
        }
        return -1;
    }
}
```

双向 BFS：

双向 BFS：
