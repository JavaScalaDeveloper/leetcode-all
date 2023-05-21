# [1751. 最多可以参加的会议数目 II](https://leetcode.cn/problems/maximum-number-of-events-that-can-be-attended-ii)

## 题目描述

<p>给你一个 <code>events</code> 数组，其中 <code>events[i] = [startDay<sub>i</sub>, endDay<sub>i</sub>, value<sub>i</sub>]</code> ，表示第 <code>i</code> 个会议在 <code>startDay<sub>i</sub></code><sub> </sub>天开始，第 <code>endDay<sub>i</sub></code> 天结束，如果你参加这个会议，你能得到价值 <code>value<sub>i</sub></code> 。同时给你一个整数 <code>k</code> 表示你能参加的最多会议数目。</p>

<p>你同一时间只能参加一个会议。如果你选择参加某个会议，那么你必须 <strong>完整</strong> 地参加完这个会议。会议结束日期是包含在会议内的，也就是说你不能同时参加一个开始日期与另一个结束日期相同的两个会议。</p>

<p>请你返回能得到的会议价值 <strong>最大和</strong> 。</p>



<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1751.Maximum%20Number%20of%20Events%20That%20Can%20Be%20Attended%20II/images/screenshot-2021-01-11-at-60048-pm.png" style="width: 400px; height: 103px;" /></p>

<pre>
<b>输入：</b>events = [[1,2,4],[3,4,3],[2,3,1]], k = 2
<b>输出：</b>7
<strong>解释：</strong>选择绿色的活动会议 0 和 1，得到总价值和为 4 + 3 = 7 。</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1751.Maximum%20Number%20of%20Events%20That%20Can%20Be%20Attended%20II/images/screenshot-2021-01-11-at-60150-pm.png" style="width: 400px; height: 103px;" /></p>

<pre>
<b>输入：</b>events = [[1,2,4],[3,4,3],[2,3,10]], k = 2
<b>输出：</b>10
<b>解释：</b>参加会议 2 ，得到价值和为 10 。
你没法再参加别的会议了，因为跟会议 2 有重叠。你 <strong>不</strong> 需要参加满 k 个会议。</pre>

<p><strong>示例 3：</strong></p>

<p><strong><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1751.Maximum%20Number%20of%20Events%20That%20Can%20Be%20Attended%20II/images/screenshot-2021-01-11-at-60703-pm.png" style="width: 400px; height: 126px;" /></strong></p>

<pre>
<b>输入：</b>events = [[1,1,1],[2,2,2],[3,3,3],[4,4,4]], k = 3
<b>输出：</b>9
<b>解释：</b>尽管会议互不重叠，你只能参加 3 个会议，所以选择价值最大的 3 个会议。</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= k <= events.length</code></li>
	<li><code>1 <= k * events.length <= 10<sup>6</sup></code></li>
	<li><code>1 <= startDay<sub>i</sub> <= endDay<sub>i</sub> <= 10<sup>9</sup></code></li>
	<li><code>1 <= value<sub>i</sub> <= 10<sup>6</sup></code></li>
</ul>

## 解法

**方法一：记忆化搜索 + 二分查找**

我们先将会议按照开始时间从小到大排序，然后设计一个函数dfs(i, k)表示从第i个会议开始，最多参加k个会议的最大价值和。答案即为dfs(0, k)。

函数dfs(i, k)的计算过程如下：

对于第i个会议，我们可以选择参加或者不参加。如果不参加，那么最大价值和就是dfs(i + 1, k)，如果参加，我们可以通过二分查找，找到第一个开始时间大于第i个会议结束时间的会议，记为j，那么最大价值和就是dfs(j, k - 1) + value[i]。取二者的较大值即可。即：


dfs(i, k) = max(dfs(i + 1, k), dfs(j, k - 1) + value[i])


其中j为第一个开始时间大于第i个会议结束时间的会议，可以通过二分查找得到。

由于函数dfs(i, k)的计算过程中，会调用dfs(i + 1, k)和dfs(j, k - 1)，因此我们可以使用记忆化搜索，将计算过的值保存下来，避免重复计算。

时间复杂度O(n× log n + n× k)，其中n为会议数量。

**方法二：动态规划 + 二分查找**

我们可以将方法一中的记忆化搜索改为动态规划。

先将会议排序，这次我们按照结束时间从小到大排序。然后定义f[i][j]表示前i个会议中，最多参加j个会议的最大价值和。答案即为f[n][k]。

对于第i个会议，我们可以选择参加或者不参加。如果不参加，那么最大价值和就是f[i][j]，如果参加，我们可以通过二分查找，找到最后一个结束时间小于第i个会议开始时间的会议，记为h，那么最大价值和就是f[h+1][j - 1] + value[i]。取二者的较大值即可。即：


f[i+1][j] = max(f[i][j], f[h+1][j - 1] + value[i])


其中h为最后一个结束时间小于第i个会议开始时间的会议，可以通过二分查找得到。

时间复杂度O(n× log n + n× k)，其中n为会议数量。

相似题目：

-   [1235. 规划兼职工作](/solution/1200-1299/1235.Maximum%20Profit%20in%20Job%20Scheduling/README.md)
-   [2008. 出租车的最大盈利](/solution/2000-2099/2008.Maximum%20Earnings%20From%20Taxi/README.md)

### **Java**

```java
class Solution {
    private int[][] events;
    private int[][] f;
    private int n;

    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        this.events = events;
        n = events.length;
        f = new int[n][k + 1];
        return dfs(0, k);
    }

    private int dfs(int i, int k) {
        if (i >= n || k <= 0) {
            return 0;
        }
        if (f[i][k] != 0) {
            return f[i][k];
        }
        int j = search(events, events[i][1], i + 1);
        int ans = Math.max(dfs(i + 1, k), dfs(j, k - 1) + events[i][2]);
        return f[i][k] = ans;
    }

    private int search(int[][] events, int x, int lo) {
        int l = lo, r = n;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (events[mid][0] > x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

```java
class Solution {
    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, (a, b) -> a[1] - b[1]);
        int n = events.length;
        int[][] f = new int[n + 1][k + 1];
        for (int i = 1; i <= n; ++i) {
            int st = events[i - 1][0], val = events[i - 1][2];
            int p = search(events, st, i - 1);
            for (int j = 1; j <= k; ++j) {
                f[i][j] = Math.max(f[i - 1][j], f[p][j - 1] + val);
            }
        }
        return f[n][k];
    }

    private int search(int[][] events, int x, int hi) {
        int l = 0, r = hi;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (events[mid][1] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```
