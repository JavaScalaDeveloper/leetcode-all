# [57. 插入区间](https://leetcode.cn/problems/insert-interval)

[English Version](/solution/0000-0099/0057.Insert%20Interval/README_EN.md)

## 题目描述

<p>给你一个<strong> 无重叠的</strong><em> ，</em>按照区间起始端点排序的区间列表。</p>

<p>在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[1,3],[6,9]], newInterval = [2,5]
<strong>输出：</strong>[[1,5],[6,9]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
<strong>输出：</strong>[[1,2],[3,10],[12,16]]
<strong>解释：</strong>这是因为新的区间 <code>[4,8]</code> 与 <code>[3,5],[6,7],[8,10]</code> 重叠。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>intervals = [], newInterval = [5,7]
<strong>输出：</strong>[[5,7]]
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[1,5]], newInterval = [2,3]
<strong>输出：</strong>[[1,5]]
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[1,5]], newInterval = [2,7]
<strong>输出：</strong>[[1,7]]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 <= intervals.length <= 10<sup>4</sup></code></li>
	<li><code>intervals[i].length == 2</code></li>
	<li><code>0 <= intervals[i][0] <= intervals[i][1] <= 10<sup>5</sup></code></li>
	<li><code>intervals</code> 根据 <code>intervals[i][0]</code> 按 <strong>升序</strong> 排列</li>
	<li><code>newInterval.length == 2</code></li>
	<li><code>0 <= newInterval[0] <= newInterval[1] <= 10<sup>5</sup></code></li>
</ul>

## 解法

**方法一：排序 + 区间合并**

我们可以先将新区间 `newInterval` 加入到区间列表 `intervals` 中，然后按照区间合并的常规方法进行合并。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是区间的数量。

**方法二：一次遍历**

我们可以遍历区间列表 `intervals`，记当前区间为 `interval`，对于每个区间有三种情况：

-   当前区间在新区间的右侧，即 $newInterval[1] \lt interval[0]$，此时如果新区间还没有被加入，那么将新区间加入到答案中，然后将当前区间加入到答案中。
-   当前区间在新区间的左侧，即 $interval[1] \lt newInterval[0]$，此时将当前区间加入到答案中。
-   否则，说明当前区间与新区间有交集，我们取当前区间的左端点和新区间的左端点的最小值，以及当前区间的右端点和新区间的右端点的最大值，作为新区间的左右端点，然后继续遍历区间列表。

遍历结束，如果新区间还没有被加入，那么将新区间加入到答案中。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是区间的数量。

### **Java**

```java
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> ans = new ArrayList<>();
        int st = newInterval[0], ed = newInterval[1];
        boolean insert = false;
        for (int[] interval : intervals) {
            int s = interval[0], e = interval[1];
            if (ed < s) {
                if (!insert) {
                    ans.add(new int[] {st, ed});
                    insert = true;
                }
                ans.add(interval);
            } else if (e < st) {
                ans.add(interval);
            } else {
                st = Math.min(st, s);
                ed = Math.max(ed, e);
            }
        }
        if (!insert) {
            ans.add(new int[] {st, ed});
        }
        return ans.toArray(new int[ans.size()][]);
    }
}
```
