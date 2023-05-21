# [939. 最小面积矩形](https://leetcode.cn/problems/minimum-area-rectangle)

## 题目描述

<p>给定在 xy 平面上的一组点，确定由这些点组成的矩形的最小面积，其中矩形的边平行于 x 轴和 y 轴。</p>

<p>如果没有任何矩形，就返回 0。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>[[1,1],[1,3],[3,1],[3,3],[2,2]]
<strong>输出：</strong>4
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>[[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
<strong>输出：</strong>2
</pre>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= points.length &lt;= 500</code></li>
	<li><code>0 &lt;=&nbsp;points[i][0] &lt;=&nbsp;40000</code></li>
	<li><code>0 &lt;=&nbsp;points[i][1] &lt;=&nbsp;40000</code></li>
	<li>所有的点都是不同的。</li>
</ol>

## 解法

**方法一：哈希表 + 排序 + 枚举**

对于每个点，我们将其横坐标作为键，纵坐标作为值存入哈希表d中。对于哈希表中的每个键，我们将其对应的纵坐标按照从小到大的顺序排序。

然后我们从小到大枚举横坐标，对于每个横坐标，我们枚举其对应的纵坐标中的所有点对(y_1, y_2)，其中y_1 < y_2。如果我们之前已经遇到过点对(y_1, y_2)，那么就可以用当前的横坐标和之前的横坐标计算出一个矩形的面积。我们用哈希表pos记录每个点对(y_1, y_2)第一次出现的横坐标，这样我们就可以在常数时间内找到这个横坐标。

最后，我们返回所有矩形的面积中的最小值。

时间复杂度O(n^2 × log n)，空间复杂度O(n^2)。其中n是点的数量。

### **Java**

```java
class Solution {
    public int minAreaRect(int[][] points) {
        TreeMap<Integer, List<Integer>> d = new TreeMap<>();
        for (var p : points) {
            int x = p[0], y = p[1];
            d.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
        }
        Map<Integer, Integer> pos = new HashMap<>();
        int ans = 1 << 30;
        for (var e : d.entrySet()) {
            int x = e.getKey();
            var ys = e.getValue();
            Collections.sort(ys);
            int n = ys.size();
            for (int i = 0; i < n; ++i) {
                int y1 = ys.get(i);
                for (int j = i + 1; j < n; ++j) {
                    int y2 = ys.get(j);
                    int p = y1 * 40001 + y2;
                    if (pos.containsKey(p)) {
                        ans = Math.min(ans, (x - pos.get(p)) * (y2 - y1));
                    }
                    pos.put(p, x);
                }
            }
        }
        return ans == 1 << 30 ? 0 : ans;
    }
}
```
