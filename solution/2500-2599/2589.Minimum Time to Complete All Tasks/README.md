# [2589. 完成所有任务的最少时间](https://leetcode.cn/problems/minimum-time-to-complete-all-tasks)

## 题目描述

<p>你有一台电脑，它可以 <strong>同时</strong>&nbsp;运行无数个任务。给你一个二维整数数组&nbsp;<code>tasks</code>&nbsp;，其中&nbsp;<code>tasks[i] = [start<sub>i</sub>, end<sub>i</sub>, duration<sub>i</sub>]</code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;个任务需要在 <strong>闭区间</strong>&nbsp;时间段&nbsp;<code>[start<sub>i</sub>, end<sub>i</sub>]</code>&nbsp;内运行&nbsp;<code>duration<sub>i</sub></code>&nbsp;个整数时间点（但不需要连续）。</p>

<p>当电脑需要运行任务时，你可以打开电脑，如果空闲时，你可以将电脑关闭。</p>

<p>请你返回完成所有任务的情况下，电脑最少需要运行多少秒。</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>tasks = [[2,3,1],[4,5,1],[1,5,2]]
<b>输出：</b>2
<b>解释：</b>
- 第一个任务在闭区间 [2, 2] 运行。
- 第二个任务在闭区间 [5, 5] 运行。
- 第三个任务在闭区间 [2, 2] 和 [5, 5] 运行。
电脑总共运行 2 个整数时间点。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>tasks = [[1,3,2],[2,5,3],[5,6,2]]
<b>输出：</b>4
<b>解释：</b>
- 第一个任务在闭区间 [2, 3] 运行
- 第二个任务在闭区间 [2, 3] 和 [5, 5] 运行。
- 第三个任务在闭区间 [5, 6] 运行。
电脑总共运行 4 个整数时间点。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= tasks.length &lt;= 2000</code></li>
	<li><code>tasks[i].length == 3</code></li>
	<li><code>1 &lt;= start<sub>i</sub>, end<sub>i</sub> &lt;= 2000</code></li>
	<li><code>1 &lt;= duration<sub>i</sub> &lt;= end<sub>i</sub> - start<sub>i</sub> + 1 </code></li>
</ul>

## 解法

**方法一：贪心 + 排序**

我们观察发现，题目相当于在每一个区间[start,..,end]中，选择duration个整数时间点，使得总共选择的整数时间点最少。

因此，我们可以先对tasks按照结束时间end从小到大排序。然后贪心地进行选择，对于每一个任务，我们从结束时间end开始，从后往前选择尽可能靠后的点，这样这些点更有可能被后面的任务重复利用。

我们在实现上，可以用一个长度为2010的数组vis记录每个时间点是否被选择过。然后对于每一个任务，我们先统计[start,..,end]区间内已经被选择过的点的个数cnt，然后从后往前选择duration - cnt个点，同时记录选择的点的个数ans以及更新vis数组。

最后，我们返回ans即可。

时间复杂度O(n \times \log n + n \times m)，空间复杂度O(m)。其中n和m分别为tasks的长度和vis数组的长度。本题中m = 2010。

### **Java**

```java
class Solution {
    public int findMinimumTime(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> a[1] - b[1]);
        int[] vis = new int[2010];
        int ans = 0;
        for (var task : tasks) {
            int start = task[0], end = task[1], duration = task[2];
            for (int i = start; i <= end; ++i) {
                duration -= vis[i];
            }
            for (int i = end; i >= start && duration > 0; --i) {
                if (vis[i] == 0) {
                    --duration;
                    ans += vis[i] = 1;
                }
            }
        }
        return ans;
    }
}
```
