# [913. 猫和老鼠](https://leetcode.cn/problems/cat-and-mouse)

## 题目描述

<p>两位玩家分别扮演猫和老鼠，在一张 <strong>无向</strong> 图上进行游戏，两人轮流行动。</p>

<p>图的形式是：<code>graph[a]</code> 是一个列表，由满足&nbsp;<code>ab</code> 是图中的一条边的所有节点 <code>b</code> 组成。</p>

<p>老鼠从节点 <code>1</code> 开始，第一个出发；猫从节点 <code>2</code> 开始，第二个出发。在节点 <code>0</code> 处有一个洞。</p>

<p>在每个玩家的行动中，他们 <strong>必须</strong> 沿着图中与所在当前位置连通的一条边移动。例如，如果老鼠在节点 <code>1</code> ，那么它必须移动到 <code>graph[1]</code> 中的任一节点。</p>

<p>此外，猫无法移动到洞中（节点 <code>0</code>）。</p>

<p>然后，游戏在出现以下三种情形之一时结束：</p>

<ul>
	<li>如果猫和老鼠出现在同一个节点，猫获胜。</li>
	<li>如果老鼠到达洞中，老鼠获胜。</li>
	<li>如果某一位置重复出现（即，玩家的位置和移动顺序都与上一次行动相同），游戏平局。</li>
</ul>

<p>给你一张图 <code>graph</code> ，并假设两位玩家都都以最佳状态参与游戏：</p>

<ul>
	<li>如果老鼠获胜，则返回&nbsp;<code>1</code>；</li>
	<li>如果猫获胜，则返回 <code>2</code>；</li>
	<li>如果平局，则返回 <code>0</code> 。</li>
</ul>
&nbsp;

<p><strong>示例 1：</strong></p>
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0913.Cat%20and%20Mouse/images/cat1.jpg" style="width: 300px; height: 300px;" />
<pre>
<strong>输入：</strong>graph = [[2,5],[3],[0,4,5],[1,4,5],[2,3],[0,2,3]]
<strong>输出：</strong>0
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0913.Cat%20and%20Mouse/images/cat2.jpg" style="width: 200px; height: 200px;" />
<pre>
<strong>输入：</strong>graph = [[1,3],[0],[3],[0,2]]
<strong>输出：</strong>1
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= graph.length &lt;= 50</code></li>
	<li><code>1&nbsp;&lt;= graph[i].length &lt; graph.length</code></li>
	<li><code>0 &lt;= graph[i][j] &lt; graph.length</code></li>
	<li><code>graph[i][j] != i</code></li>
	<li><code>graph[i]</code> 互不相同</li>
	<li>猫和老鼠在游戏中总是可以移动</li>
</ul>

## 解法

**方法一：拓扑排序**

猫和老鼠的游戏中，状态由三个因素决定：老鼠的位置、猫的位置和移动方。根据游戏规则，可以直接确定胜负的边界状态有：

-   当猫和老鼠的位置相同时，猫获胜，为猫的必胜状态，老鼠的必败状态。
-   当老鼠位于洞时，老鼠获胜，为老鼠的必胜状态，猫的必败状态。

为了得到初始状态的游戏结果，需要从边界状态开始遍历所有的状态。每个状态包含老鼠的位置、猫的位置和移动方，根据当前状态可以得到上一轮的所有可能状态，上一轮状态的移动方和当前状态的移动方相反，上一轮状态的移动方在上一轮状态的位置和当前状态的位置不同。

我们用元组 $(m, c, t)$ 表示本轮的状态，用 $(pm, pc, pt)$ 表示上一轮可能的状态，那么上一轮的所有可能状态有：

-   如果本轮的移动方是老鼠，那么上一轮的移动方是猫，上一轮的老鼠位置是本轮老鼠位置，上一轮的猫位置是本轮猫位置的所有邻接点。
-   如果本轮的移动方是猫，那么上一轮的移动方是老鼠，上一轮的猫位置是本轮猫位置，上一轮的老鼠位置是本轮老鼠位置的所有邻接点。

初始时，除了边界状态以外，其他所有状态的结果都是未知的。我们从边界状态开始，对于每个状态，得到上一轮的所有可能状态并更新结果，更新的逻辑如下：

1. 如果上一轮的移动方与本轮的获胜方相同，那么上一轮的移动方可以到达当前状态并获胜，直接更新上一轮的状态为本轮的获胜方。
1. 如果上一轮的移动方与本轮的获胜方不同，且上一轮的移动方可以到达的所有状态都是上一轮的移动方的必败状态，那么我们将上一轮的状态更新为本轮的获胜方。

对于第 $2$ 个更新逻辑，我们需要记录每个状态的度。初始时，每个状态的度表示该状态的移动方可以移动到的结点数，即移动方所在节点的相邻结点数，如果移动方是猫且所在结点与洞相邻则需要将该状态的度减 $1$。

当所有状态的结果都更新完毕时，初始状态的结果即为最终结果。

时间复杂度 $O(n^3)$，空间复杂度 $O(n^2)$。其中 $n$ 是图中的结点数。

### **Java**

```java
class Solution {
    private int n;
    private int[][] g;
    private int[][][] res;
    private int[][][] degree;

    private static final int HOLE = 0, MOUSE_START = 1, CAT_START = 2;
    private static final int MOUSE_TURN = 0, CAT_TURN = 1;
    private static final int MOUSE_WIN = 1, CAT_WIN = 2, TIE = 0;

    public int catMouseGame(int[][] graph) {
        n = graph.length;
        g = graph;
        res = new int[n][n][2];
        degree = new int[n][n][2];
        for (int i = 0; i < n; ++i) {
            for (int j = 1; j < n; ++j) {
                degree[i][j][MOUSE_TURN] = g[i].length;
                degree[i][j][CAT_TURN] = g[j].length;
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j : g[HOLE]) {
                --degree[i][j][CAT_TURN];
            }
        }
        Deque<int[]> q = new ArrayDeque<>();
        for (int j = 1; j < n; ++j) {
            res[0][j][MOUSE_TURN] = MOUSE_WIN;
            res[0][j][CAT_TURN] = MOUSE_WIN;
            q.offer(new int[] {0, j, MOUSE_TURN});
            q.offer(new int[] {0, j, CAT_TURN});
        }
        for (int i = 1; i < n; ++i) {
            res[i][i][MOUSE_TURN] = CAT_WIN;
            res[i][i][CAT_TURN] = CAT_WIN;
            q.offer(new int[] {i, i, MOUSE_TURN});
            q.offer(new int[] {i, i, CAT_TURN});
        }
        while (!q.isEmpty()) {
            int[] state = q.poll();
            int t = res[state[0]][state[1]][state[2]];
            List<int[]> prevStates = getPrevStates(state);
            for (var prevState : prevStates) {
                int pm = prevState[0], pc = prevState[1], pt = prevState[2];
                if (res[pm][pc][pt] == TIE) {
                    boolean win
                        = (t == MOUSE_WIN && pt == MOUSE_TURN) || (t == CAT_WIN && pt == CAT_TURN);
                    if (win) {
                        res[pm][pc][pt] = t;
                        q.offer(prevState);
                    } else {
                        if (--degree[pm][pc][pt] == 0) {
                            res[pm][pc][pt] = t;
                            q.offer(prevState);
                        }
                    }
                }
            }
        }
        return res[MOUSE_START][CAT_START][MOUSE_TURN];
    }

    private List<int[]> getPrevStates(int[] state) {
        List<int[]> pre = new ArrayList<>();
        int m = state[0], c = state[1], t = state[2];
        int pt = t ^ 1;
        if (pt == CAT_TURN) {
            for (int pc : g[c]) {
                if (pc != HOLE) {
                    pre.add(new int[] {m, pc, pt});
                }
            }
        } else {
            for (int pm : g[m]) {
                pre.add(new int[] {pm, c, pt});
            }
        }
        return pre;
    }
}
```
