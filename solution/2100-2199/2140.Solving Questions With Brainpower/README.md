# [2140. 解决智力问题](https://leetcode.cn/problems/solving-questions-with-brainpower)

## 题目描述

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的二维整数数组&nbsp;<code>questions</code>&nbsp;，其中&nbsp;<code>questions[i] = [points<sub>i</sub>, brainpower<sub>i</sub>]</code>&nbsp;。</p>

<p>这个数组表示一场考试里的一系列题目，你需要 <strong>按顺序</strong>&nbsp;（也就是从问题 <code>0</code><strong>&nbsp;</strong>开始依次解决），针对每个问题选择 <strong>解决</strong>&nbsp;或者 <strong>跳过</strong>&nbsp;操作。解决问题 <code>i</code>&nbsp;将让你 <b>获得</b>&nbsp;&nbsp;<code>points<sub>i</sub></code>&nbsp;的分数，但是你将 <strong>无法</strong>&nbsp;解决接下来的&nbsp;<code>brainpower<sub>i</sub></code>&nbsp;个问题（即只能跳过接下来的 <code>brainpower<sub>i</sub></code><sub>&nbsp;</sub>个问题）。如果你跳过问题&nbsp;<code>i</code>&nbsp;，你可以对下一个问题决定使用哪种操作。</p>

<ul>
	<li>比方说，给你&nbsp;<code>questions = [[3, 2], [4, 3], [4, 4], [2, 5]]</code>&nbsp;：

    <ul>
    	<li>如果问题&nbsp;<code>0</code>&nbsp;被解决了， 那么你可以获得&nbsp;<code>3</code>&nbsp;分，但你不能解决问题&nbsp;<code>1</code> 和&nbsp;<code>2</code>&nbsp;。</li>
    	<li>如果你跳过问题&nbsp;<code>0</code>&nbsp;，且解决问题&nbsp;<code>1</code>&nbsp;，你将获得 <code>4</code> 分但是不能解决问题&nbsp;<code>2</code> 和&nbsp;<code>3</code>&nbsp;。</li>
    </ul>
    </li>

</ul>

<p>请你返回这场考试里你能获得的 <strong>最高</strong>&nbsp;分数。</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>questions = [[3,2],[4,3],[4,4],[2,5]]
<b>输出：</b>5
<b>解释：</b>解决问题 0 和 3 得到最高分。
- 解决问题 0 ：获得 3 分，但接下来 2 个问题都不能解决。
- 不能解决问题 1 和 2
- 解决问题 3 ：获得 2 分
总得分为：3 + 2 = 5 。没有别的办法获得 5 分或者多于 5 分。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>questions = [[1,1],[2,2],[3,3],[4,4],[5,5]]
<b>输出：</b>7
<b>解释：</b>解决问题 1 和 4 得到最高分。
- 跳过问题 0
- 解决问题 1 ：获得 2 分，但接下来 2 个问题都不能解决。
- 不能解决问题 2 和 3
- 解决问题 4 ：获得 5 分
总得分为：2 + 5 = 7 。没有别的办法获得 7 分或者多于 7 分。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= questions.length &lt;= 10<sup>5</sup></code></li>
	<li><code>questions[i].length == 2</code></li>
	<li><code>1 &lt;= points<sub>i</sub>, brainpower<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

**方法一：记忆化搜索**

我们设计一个函数dfs(i)，表示从第i个问题开始解决，能够获得的最高分数。那么答案就是dfs(0)。

函数dfs(i)的计算方式如下：

-   如果i ≥ n，表示已经解决完所有问题，返回0；
-   否则，设第i个问题的分数为p，需要跳过的问题数为b，那么dfs(i) = max(p + dfs(i + b + 1), dfs(i + 1))。

为了避免重复计算，我们可以使用记忆化搜索的方法，用一个数组f记录所有已经计算过的dfs(i)的值。

时间复杂度O(n)，空间复杂度O(n)。其中n是问题的数量。

**方法二：动态规划**

我们定义f[i]表示从第i个问题开始解决，能够获得的最高分数。那么答案就是f[0]。

考虑f[i]，第i个问题的分数为p，需要跳过的问题数为b。如果我们解决了第i个问题，那么接下来我们需要解决b个问题，因此f[i] = p + f[i + b + 1]。如果我们跳过了第i个问题，那么接下来我们从第i + 1个问题开始解决，因此f[i] = f[i + 1]。两者取最大值即可。状态转移方程如下：


f[i] = max(p + f[i + b + 1], f[i + 1])


我们从后往前计算f的值，最后返回f[0]即可。

时间复杂度O(n)，空间复杂度O(n)。其中n是问题的数量。

### **Java**

```java
class Solution {
    private int n;
    private Long[] f;
    private int[][] questions;

    public long mostPoints(int[][] questions) {
        n = questions.length;
        f = new Long[n];
        this.questions = questions;
        return dfs(0);
    }

    private long dfs(int i) {
        if (i >= n) {
            return 0;
        }
        if (f[i] != null) {
            return f[i];
        }
        int p = questions[i][0], b = questions[i][1];
        return f[i] = Math.max(p + dfs(i + b + 1), dfs(i + 1));
    }
}
```

```java
class Solution {
    public long mostPoints(int[][] questions) {
        int n = questions.length;
        long[] f = new long[n + 1];
        for (int i = n - 1; i >= 0; --i) {
            int p = questions[i][0], b = questions[i][1];
            int j = i + b + 1;
            f[i] = Math.max(f[i + 1], p + (j > n ? 0 : f[j]));
        }
        return f[0];
    }
}
```
