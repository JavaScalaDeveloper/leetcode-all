# [1637. 两点之间不包含任何点的最宽垂直区域](https://leetcode.cn/problems/widest-vertical-area-between-two-points-containing-no-points)

## 题目描述

<p>给你&nbsp;<code>n</code>&nbsp;个二维平面上的点 <code>points</code> ，其中&nbsp;<code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code>&nbsp;，请你返回两点之间内部不包含任何点的&nbsp;<strong>最宽垂直区域</strong> 的宽度。</p>

<p><strong>垂直区域</strong> 的定义是固定宽度，而 y 轴上无限延伸的一块区域（也就是高度为无穷大）。 <strong>最宽垂直区域</strong> 为宽度最大的一个垂直区域。</p>

<p>请注意，垂直区域&nbsp;<strong>边上</strong>&nbsp;的点&nbsp;<strong>不在</strong>&nbsp;区域内。</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1600-1699/1637.Widest%20Vertical%20Area%20Between%20Two%20Points%20Containing%20No%20Points/images/points3.png" style="width: 276px; height: 371px;" />​
<pre>
<b>输入：</b>points = [[8,7],[9,9],[7,4],[9,7]]
<b>输出：</b>1
<b>解释：</b>红色区域和蓝色区域都是最优区域。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>points = [[3,1],[9,0],[1,0],[1,4],[5,3],[8,8]]
<b>输出：</b>3
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == points.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub>&nbsp;&lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

**方法一：排序**

我们可以对数组points按照x升序排列，获取相邻点之间x的差值的最大值。

时间复杂度O(n × log n)，空间复杂度O(log n)。其中n为数组points的长度。

**方法二：桶排序**

方法一中排序的时间复杂度为O(n × log n)，其实我们可以利用桶排序的思想，将时间复杂度降低到O(n)。

我们将数组points的横坐标放入数组nums中。

假设数组nums有n个元素，所有元素从小到大依次是nums_0到nums_{n - 1}，最大间距是maxGap。考虑数组中的最大元素和最小元素之差：


nums_{n - 1} - nums_0 = \sum_{i = 1}^{n - 1} (nums_i - nums_{i - 1}) \le{maxGap} × (n - 1)


因此maxGap ≥ \dfrac{nums_{n - 1} - nums_0}{n - 1}，即最大间距至少为\dfrac{nums_{n - 1} - nums_0}{n - 1}。

可以利用桶排序的思想，设定桶的大小（即每个桶最多包含的不同元素个数）为\dfrac{nums_{n - 1} - nums_0}{n - 1}，将元素按照元素值均匀分布到各个桶内，则同一个桶内的任意两个元素之差小于{maxGap}，差为{maxGap}的两个元素一定在两个不同的桶内。对于每个桶，维护桶内的最小值和最大值，初始时每个桶内的最小值和最大值分别是正无穷和负无穷，表示桶内没有元素。

遍历数组{nums}中的所有元素。对于每个元素，根据该元素与最小元素之差以及桶的大小计算该元素应该分到的桶的编号，可以确保编号小的桶内的元素都小于编号大的桶内的元素，使用元素值更新元素所在的桶内的最小值和最大值。

遍历数组结束之后，每个非空的桶内的最小值和最大值都可以确定。按照桶的编号从小到大的顺序依次遍历每个桶，当前的桶的最小值和上一个非空的桶的最大值是排序后的相邻元素，计算两个相邻元素之差，并更新最大间距。遍历桶结束之后即可得到最大间距。

时间复杂度O(n)，空间复杂度O(n)。其中n为数组points的长度。

### **Java**

```java
class Solution {
    public int maxWidthOfVerticalArea(int[][] points) {
        Arrays.sort(points, (a, b) -> a[0] - b[0]);
        int ans = 0;
        for (int i = 0; i < points.length - 1; ++i) {
            ans = Math.max(ans, points[i + 1][0] - points[i][0]);
        }
        return ans;
    }
}
```

```java
class Solution {
    public int maxWidthOfVerticalArea(int[][] points) {
        int n = points.length;
        int[] nums = new int[n];
        for (int i = 0; i < n; ++i) {
            nums[i] = points[i][0];
        }
        final int inf = 1 << 30;
        int mi = inf, mx = -inf;
        for (int v : nums) {
            mi = Math.min(mi, v);
            mx = Math.max(mx, v);
        }
        int bucketSize = Math.max(1, (mx - mi) / (n - 1));
        int bucketCount = (mx - mi) / bucketSize + 1;
        int[][] buckets = new int[bucketCount][2];
        for (var bucket : buckets) {
            bucket[0] = inf;
            bucket[1] = -inf;
        }
        for (int v : nums) {
            int i = (v - mi) / bucketSize;
            buckets[i][0] = Math.min(buckets[i][0], v);
            buckets[i][1] = Math.max(buckets[i][1], v);
        }
        int prev = inf;
        int ans = 0;
        for (var bucket : buckets) {
            if (bucket[0] > bucket[1]) {
                continue;
            }
            ans = Math.max(ans, bucket[0] - prev);
            prev = bucket[1];
        }
        return ans;
    }
}
```
