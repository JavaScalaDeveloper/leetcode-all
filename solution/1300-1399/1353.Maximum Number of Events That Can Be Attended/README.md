# [1353. 最多可以参加的会议数目](https://leetcode.cn/problems/maximum-number-of-events-that-can-be-attended)

[English Version](/solution/1300-1399/1353.Maximum%20Number%20of%20Events%20That%20Can%20Be%20Attended/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个数组&nbsp;<code>events</code>，其中&nbsp;<code>events[i] = [startDay<sub>i</sub>, endDay<sub>i</sub>]</code>&nbsp;，表示会议&nbsp;<code>i</code>&nbsp;开始于&nbsp;<code>startDay<sub>i</sub></code>&nbsp;，结束于&nbsp;<code>endDay<sub>i</sub></code>&nbsp;。</p>

<p>你可以在满足&nbsp;<code>startDay<sub>i</sub>&nbsp;&lt;= d &lt;= endDay<sub>i</sub></code><sub>&nbsp;</sub>中的任意一天&nbsp;<code>d</code>&nbsp;参加会议&nbsp;<code>i</code>&nbsp;。注意，一天只能参加一个会议。</p>

<p>请你返回你可以参加的&nbsp;<strong>最大&nbsp;</strong>会议数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1353.Maximum%20Number%20of%20Events%20That%20Can%20Be%20Attended/images/e1.png" style="height: 267px; width: 400px;" /></p>

<pre>
<strong>输入：</strong>events = [[1,2],[2,3],[3,4]]
<strong>输出：</strong>3
<strong>解释：</strong>你可以参加所有的三个会议。
安排会议的一种方案如上图。
第 1 天参加第一个会议。
第 2 天参加第二个会议。
第 3 天参加第三个会议。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>events= [[1,2],[2,3],[3,4],[1,2]]
<strong>输出：</strong>4
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong>​​​​​​</p>

<ul>
	<li><code>1 &lt;= events.length &lt;= 10<sup>5</sup></code></li>
	<li><code>events[i].length == 2</code></li>
	<li><code>1 &lt;= startDay<sub>i</sub>&nbsp;&lt;= endDay<sub>i</sub>&nbsp;&lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

**方法一：哈希表 + 贪心 + 优先队列**

定义哈希表记录每个会议的开始和结束时间，其中键为会议开始时间，值为结束时间列表。

枚举当前时间 $s$，找出所有开始时间等于当前时间的会议，将其结束时间加入优先队列（小根堆）中。同时，优先队列要移除所有结束时间小于当前时间的会议。

然后从优先队列中取出结束时间最小的会议，即为当前时间可以参加的会议，累加答案数。如果优先队列为空，则说明当前时间没有可以参加的会议。

时间复杂度 $O(m\log n)$。其中 $m$, $n$ 分别表示会议的最大结束时间，以及会议的数量。

### **Java**

```java
class Solution {
    public int maxEvents(int[][] events) {
        Map<Integer, List<Integer>> d = new HashMap<>();
        int i = Integer.MAX_VALUE, j = 0;
        for (var v : events) {
            int s = v[0], e = v[1];
            d.computeIfAbsent(s, k -> new ArrayList<>()).add(e);
            i = Math.min(i, s);
            j = Math.max(j, e);
        }
        PriorityQueue<Integer> q = new PriorityQueue<>();
        int ans = 0;
        for (int s = i; s <= j; ++s) {
            while (!q.isEmpty() && q.peek() < s) {
                q.poll();
            }
            for (int e : d.getOrDefault(s, Collections.emptyList())) {
                q.offer(e);
            }
            if (!q.isEmpty()) {
                q.poll();
                ++ans;
            }
        }
        return ans;
    }
}
```
