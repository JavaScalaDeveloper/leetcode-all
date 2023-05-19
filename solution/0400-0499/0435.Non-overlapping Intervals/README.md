# [435. 无重叠区间](https://leetcode.cn/problems/non-overlapping-intervals)

[English Version](/solution/0400-0499/0435.Non-overlapping%20Intervals/README_EN.md)

## 题目描述

<p>给定一个区间的集合&nbsp;<code>intervals</code>&nbsp;，其中 <code>intervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code>&nbsp;。返回 <em>需要移除区间的最小数量，使剩余区间互不重叠&nbsp;</em>。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> intervals = [[1,2],[2,3],[3,4],[1,3]]
<strong>输出:</strong> 1
<strong>解释:</strong> 移除 [1,3] 后，剩下的区间没有重叠。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> intervals = [ [1,2], [1,2], [1,2] ]
<strong>输出:</strong> 2
<strong>解释:</strong> 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> intervals = [ [1,2], [2,3] ]
<strong>输出:</strong> 0
<strong>解释:</strong> 你不需要移除任何区间，因为它们已经是无重叠的了。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= intervals.length &lt;= 10<sup>5</sup></code></li>
	<li><code>intervals[i].length == 2</code></li>
	<li><code>-5 * 10<sup>4</sup>&nbsp;&lt;= start<sub>i</sub>&nbsp;&lt; end<sub>i</sub>&nbsp;&lt;= 5 * 10<sup>4</sup></code></li>
</ul>

## 解法

**方法一：转换为最长上升子序列问题**

最长上升子序列问题，动态规划的做法，时间复杂度是 $O(n^2)$，这里可以采用贪心优化，将复杂度降至 $O(n\log n)$。

**方法二：排序 + 贪心**

先按照区间右边界排序。优先选择最小的区间的右边界作为起始边界。遍历区间：

-   若当前区间左边界大于等于起始右边界，说明该区间无需移除，直接更新起始右边界；
-   否则说明该区间需要移除，更新移除区间的数量 ans。

最后返回 ans 即可。

时间复杂度 $O(n\log n)$。

### **Java**

```java
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        int t = intervals[0][1], ans = 0;
        for (int i = 1; i < intervals.length; ++i) {
            if (intervals[i][0] >= t) {
                t = intervals[i][1];
            } else {
                ++ans;
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        int n = intervals.length;
        int[] d = new int[n + 1];
        d[1] = intervals[0][1];
        int size = 1;
        for (int i = 1; i < n; ++i) {
            int s = intervals[i][0], e = intervals[i][1];
            if (s >= d[size]) {
                d[++size] = e;
            } else {
                int left = 1, right = size;
                while (left < right) {
                    int mid = (left + right) >> 1;
                    if (d[mid] >= s) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }
                d[left] = Math.min(d[left], e);
            }
        }
        return n - size;
    }
}
```
