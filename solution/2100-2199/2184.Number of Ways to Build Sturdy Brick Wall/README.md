# [2184. 建造坚实的砖墙的方法数](https://leetcode.cn/problems/number-of-ways-to-build-sturdy-brick-wall)

[English Version](/solution/2100-2199/2184.Number%20of%20Ways%20to%20Build%20Sturdy%20Brick%20Wall/README_EN.md)

## 题目描述

<p>给你两个整数&nbsp;<code>height</code>&nbsp;与<code>width</code>&nbsp;，表示你要建造的砖墙的高和宽。再给你一个下标从 <strong>0</strong> 开始的数组 <code>bricks</code> ，其中第 <code>i</code> 块砖的高度是 <code>1</code> ，宽度为 <code>bricks[i]</code> 。每种砖的数量都是 <strong>无限 </strong>的，并且砖 <strong>不可以</strong> 进行旋转。</p>

<p>墙的每一行必须正好&nbsp;<code>width</code> 单位长。为了让墙体<strong> 坚实 </strong>，除了在首尾的位置，相邻的行砖缝<strong> 不能 </strong>在同一个位置。</p>

<p>请你返回建造坚实的砖墙的方法数，由于答案可能很大，需要对 <code>10<sup>9</sup> + 7</code><strong> 取余</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2184.Number%20of%20Ways%20to%20Build%20Sturdy%20Brick%20Wall/images/image-20220220190749-1.png" style="width: 919px; height: 250px;" />
<pre>
<strong>输入：</strong>height = 2, width = 3, bricks = [1,2]
<strong>输出：</strong>2
<strong>解释：</strong>前两图中的两种方法是建造一座坚实砖墙的唯二的方法。注意，第三幅图所展示的不是坚实的砖墙，因为相邻的行在中间的连接点位置相同。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>height = 1, width = 1, bricks = [5]
<strong>输出：</strong>0
<strong>解释：</strong>无法建造符合题目要求的砖墙，因为仅有的砖的长度比墙还要长。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= height &lt;= 100</code></li>
	<li><code>1 &lt;= width &lt;= 10</code></li>
	<li><code>1 &lt;= bricks.length &lt;= 10</code></li>
	<li><code>1 &lt;= bricks[i] &lt;= 10</code></li>
	<li><code>bricks</code>&nbsp;中所有数字<strong> 互不相同</strong></li>
</ul>

## 解法

**方法一：DFS + 动态规划**

首先通过 DFS 构造出所有合法的排列。然后所有排列进行两两比较，找出每种排列相邻的合法排列，记录在 `g` 数组中。

然后进行动态规划。

过程是这样的：计算以某种排列结束的所有方案数。

初始化第一排每种排列的方案数为 1；每一排选取某种排列的总方案数为上一排能与自己相邻的排列的方案数之和。

答案为最后一排的方案数之和。

### **Java**

```java
class Solution {
    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> t = new ArrayList<>();
    private static final int MOD = (int) 1e9 + 7;
    private int width;
    private int[] bricks;

    public int buildWall(int height, int width, int[] bricks) {
        this.width = width;
        this.bricks = bricks;
        dfs(0);
        int n = res.size();
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 0; i < n; ++i) {
            if (check(res.get(i), res.get(i))) {
                g[i].add(i);
            }
            for (int j = i + 1; j < n; ++j) {
                if (check(res.get(i), res.get(j))) {
                    g[i].add(j);
                    g[j].add(i);
                }
            }
        }
        int[][] dp = new int[height][n];
        for (int j = 0; j < n; ++j) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < height; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k : g[j]) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][k]) % MOD;
                }
            }
        }
        int ans = 0;
        for (int j = 0; j < n; ++j) {
            ans = (ans + dp[height - 1][j]) % MOD;
        }
        return ans;
    }

    private boolean check(List<Integer> a, List<Integer> b) {
        int s1 = a.get(0);
        int s2 = b.get(0);
        int i = 1, j = 1;
        while (i < a.size() && j < b.size()) {
            if (s1 == s2) {
                return false;
            }
            if (s1 < s2) {
                s1 += a.get(i++);
            } else {
                s2 += b.get(j++);
            }
        }
        return true;
    }

    private void dfs(int v) {
        if (v > width) {
            return;
        }
        if (v == width) {
            res.add(new ArrayList<>(t));
            return;
        }
        for (int x : bricks) {
            t.add(x);
            dfs(v + x);
            t.remove(t.size() - 1);
        }
    }
}
```
