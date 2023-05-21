# [1036. 逃离大迷宫](https://leetcode.cn/problems/escape-a-large-maze)

## 题目描述

<p>在一个 10<sup>6</sup> x 10<sup>6</sup> 的网格中，每个网格上方格的坐标为 <code>(x, y)</code> 。</p>

<p>现在从源方格 <code>source = [s<sub>x</sub>, s<sub>y</sub>]</code> 开始出发，意图赶往目标方格 <code>target = [t<sub>x</sub>, t<sub>y</sub>]</code> 。数组 <code>blocked</code> 是封锁的方格列表，其中每个 <code>blocked[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> 表示坐标为 <code>(x<sub>i</sub>, y<sub>i</sub>)</code> 的方格是禁止通行的。</p>

<p>每次移动，都可以走到网格中在四个方向上相邻的方格，只要该方格 <strong>不</strong> 在给出的封锁列表 <code>blocked</code> 上。同时，不允许走出网格。</p>

<p>只有在可以通过一系列的移动从源方格 <code>source</code> 到达目标方格 <code>target</code> 时才返回 <code>true</code>。否则，返回 <code>false</code>。</p>



<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>blocked = [[0,1],[1,0]], source = [0,0], target = [0,2]
<strong>输出：</strong>false
<strong>解释：</strong>
从源方格无法到达目标方格，因为我们无法在网格中移动。
无法向北或者向东移动是因为方格禁止通行。
无法向南或者向西移动是因为不能走出网格。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>blocked = [], source = [0,0], target = [999999,999999]
<strong>输出：</strong>true
<strong>解释：</strong>
因为没有方格被封锁，所以一定可以到达目标方格。
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= blocked.length <= 200</code></li>
	<li><code>blocked[i].length == 2</code></li>
	<li><code>0 <= x<sub>i</sub>, y<sub>i</sub> < 10<sup>6</sup></code></li>
	<li><code>source.length == target.length == 2</code></li>
	<li><code>0 <= s<sub>x</sub>, s<sub>y</sub>, t<sub>x</sub>, t<sub>y</sub> < 10<sup>6</sup></code></li>
	<li><code>source != target</code></li>
	<li>题目数据保证 <code>source</code> 和 <code>target</code> 不在封锁列表内</li>
</ul>

## 解法

### **Java**

```java
class Solution {
    private int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static final int N = (int) 1e6;
    private Set<Integer> blocked;

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        this.blocked = new HashSet<>();
        for (int[] b : blocked) {
            this.blocked.add(b[0] * N + b[1]);
        }
        return dfs(source, target, new HashSet<>()) && dfs(target, source, new HashSet<>());
    }

    private boolean dfs(int[] source, int[] target, Set<Integer> seen) {
        int sx = source[0], sy = source[1];
        int tx = target[0], ty = target[1];
        if (sx < 0 || sx >= N || sy < 0 || sy >= N || tx < 0 || tx >= N || ty < 0 || ty >= N
            || blocked.contains(sx * N + sy) || seen.contains(sx * N + sy)) {
            return false;
        }
        seen.add(sx * N + sy);
        if (seen.size() > 20000 || (sx == target[0] && sy == target[1])) {
            return true;
        }
        for (int[] dir : dirs) {
            if (dfs(new int[] {sx + dir[0], sy + dir[1]}, target, seen)) {
                return true;
            }
        }
        return false;
    }
}
```
