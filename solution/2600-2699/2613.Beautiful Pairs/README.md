# [2613. 美数对](https://leetcode.cn/problems/beautiful-pairs)

## 题目描述

<p>给定两个长度相同的 <strong>下标从 0 开始</strong> 的整数数组 <code>nums1</code> 和 <code>nums2</code>&nbsp;，如果 <code>|nums1[i] - nums1[j]| + |nums2[i] - nums2[j]|</code> 在所有可能的下标对中是最小的，其中 <code>i &lt; j</code> ，则称下标对 <code>(i,j)</code> 为 <strong>美</strong> 数对，</p>

<p>返回美数对。如果有多个美数对，则返回字典序最小的美数对。</p>

<p>注意：</p>

<ul>
	<li><code>|x|</code> 表示 <code>x</code> 的绝对值。</li>
	<li>一对索引 <code>(i1, j1)</code> 在字典序意义下小于 <code>(i2, j2)</code> ，当且仅当 <code>i1 &lt; i2</code> 或 <code>i1 == i2</code> 且 <code>j1 &lt; j2</code>&nbsp;。</li>
</ul>

<p><strong class="example">示例 1 ：</strong></p>

<pre>
<b>输入：</b>nums1 = [1,2,3,2,4], nums2 = [2,3,1,2,3]
<b>输出：</b>[0,3]
<b>解释：</b>取下标为 0 和下标为 3 的数对，计算出 |nums1[0]-nums1[3]| + |nums2[0]-nums2[3]| 的值为 1 ，这是我们能够得到的最小值。
</pre>

<p><strong class="example">示例 2 ：</strong></p>

<pre>
<b>输入：</b>nums1 = [1,2,4,3,2,5], nums2 = [1,4,2,3,5,1]
<b>输出：</b>[1,4]
<b>解释：</b>取下标为 1 和下标为 4 的数对，计算出 |nums1[1]-nums1[4]| + |nums2[1]-nums2[4]| 的值为 1，这是我们可以达到的最小值。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums1.length, nums2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums1.length == nums2.length</code></li>
	<li><code>0 &lt;= nums1<sub>i</sub><sub>&nbsp;</sub>&lt;= nums1.length</code></li>
	<li><code>0 &lt;= nums2<sub>i</sub>&nbsp;&lt;= nums2.length</code></li>
</ul>

## 解法

**方法一：排序 + 分治**

本题相当于找出平面中两个点，使得它们的曼哈顿距离最小，如果有多个点满足条件，则返回下标字典序最小的点。

我们先处理重复点的情况，找出每个点对应的下标列表，如果某个点的下标列表长度大于 $1$，那么它的前两个下标可作为候选答案，我们找出最小的下标对即可。

如果没有重复点，我们将所有点按照 $x$ 坐标排序，然后分治求解。

对于每个区间 $[l, r]$，我们先求出 $x$ 坐标的中位数 $m$，然后递归求解左右两个区间，分别得到 $d_1, (pi_1, pj_1)$ 和 $d_2, (pi_2, pj_2)$，其中 $d_1$ 和 $d_2$ 分别表示左右两个区间的最小曼哈顿距离，而 $(pi_1, pj_1)$ 和 $(pi_2, pj_2)$ 分别表示左右两个区间的最小曼哈顿距离的两个点的下标。我们取 $d_1$ 和 $d_2$ 中较小的一个，如果 $d_1 = d_2$，则取下标字典序较小的一个，将其作为当前区间的最小曼哈顿距离，同时将对应的两个点的下标作为答案。

以上考虑的是两个点位于同一侧的情况，如果两个点位于不同侧，那么我们以中间点，即下标为 $m = \lfloor (l + r) / 2 \rfloor$ 的点为标准，划分出一个新的区域，区域的范围为中间点向左右两侧分别扩展 $d_1$ 的范围。然后我们将这些范围内的点按照 $y$ 坐标排序，然后遍历排序后的每个点对，如果两个点的 $y$ 坐标之差大于当前的最小曼哈顿距离，那么后面的点对都不用考虑了，因为它们的 $y$ 坐标之差更大，所以曼哈顿距离更大，不会比当前的最小曼哈顿距离更小。否则，我们更新最小曼哈顿距离，同时更新答案。

最后，我们返回答案即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组的长度。

### **Java**

```java
class Solution {
    private List<int[]> points = new ArrayList<>();

    public int[] beautifulPair(int[] nums1, int[] nums2) {
        int n = nums1.length;
        Map<Long, List<Integer>> pl = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            long z = f(nums1[i], nums2[i]);
            pl.computeIfAbsent(z, k -> new ArrayList<>()).add(i);
        }
        for (int i = 0; i < n; ++i) {
            long z = f(nums1[i], nums2[i]);
            if (pl.get(z).size() > 1) {
                return new int[] {i, pl.get(z).get(1)};
            }
            points.add(new int[] {nums1[i], nums2[i], i});
        }
        points.sort((a, b) -> a[0] - b[0]);
        int[] ans = dfs(0, points.size() - 1);
        return new int[] {ans[1], ans[2]};
    }

    private long f(int x, int y) {
        return x * 100000L + y;
    }

    private int dist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    private int[] dfs(int l, int r) {
        if (l >= r) {
            return new int[] {1 << 30, -1, -1};
        }
        int m = (l + r) >> 1;
        int x = points.get(m)[0];
        int[] t1 = dfs(l, m);
        int[] t2 = dfs(m + 1, r);
        if (t1[0] > t2[0]
            || (t1[0] == t2[0] && (t1[1] > t2[1] || (t1[1] == t2[1] && t1[2] > t2[2])))) {
            t1 = t2;
        }
        List<int[]> t = new ArrayList<>();
        for (int i = l; i <= r; ++i) {
            if (Math.abs(points.get(i)[0] - x) <= t1[0]) {
                t.add(points.get(i));
            }
        }
        t.sort((a, b) -> a[1] - b[1]);
        for (int i = 0; i < t.size(); ++i) {
            for (int j = i + 1; j < t.size(); ++j) {
                if (t.get(j)[1] - t.get(i)[1] > t1[0]) {
                    break;
                }
                int pi = Math.min(t.get(i)[2], t.get(j)[2]);
                int pj = Math.max(t.get(i)[2], t.get(j)[2]);
                int d = dist(t.get(i)[0], t.get(i)[1], t.get(j)[0], t.get(j)[1]);
                if (d < t1[0] || (d == t1[0] && (pi < t1[1] || (pi == t1[1] && pj < t1[2])))) {
                    t1 = new int[] {d, pi, pj};
                }
            }
        }
        return t1;
    }
}
```
