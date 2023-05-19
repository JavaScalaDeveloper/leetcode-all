# [面试题 16.14. 最佳直线](https://leetcode.cn/problems/best-line-lcci)

[English Version](/lcci/16.14.Best%20Line/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个二维平面及平面上的 N 个点列表<code>Points</code>，其中第<code>i</code>个点的坐标为<code>Points[i]=[X<sub>i</sub>,Y<sub>i</sub>]</code>。请找出一条直线，其通过的点的数目最多。</p>
<p>设穿过最多点的直线所穿过的全部点编号从小到大排序的列表为<code>S</code>，你仅需返回<code>[S[0],S[1]]</code>作为答案，若有多条直线穿过了相同数量的点，则选择<code>S[0]</code>值较小的直线返回，<code>S[0]</code>相同则选择<code>S[1]</code>值较小的直线返回。</p>
<p><strong>示例：</strong></p>
<pre><strong>输入：</strong> [[0,0],[1,1],[1,0],[2,0]]
<strong>输出：</strong> [0,2]
<strong>解释：</strong> 所求直线穿过的3个点的编号为[0,2,3]
</pre>
<p><strong>提示：</strong></p>
<ul>
<li><code>2 <= len(Points) <= 300</code></li>
<li><code>len(Points[i]) = 2</code></li>
</ul>

## 解法

**方法一：暴力枚举**

我们可以枚举任意两个点 $(x_1, y_1), (x_2, y_2)$，把这两个点连成一条直线，那么此时这条直线上的点的个数就是 2，接下来我们再枚举其他点 $(x_3, y_3)$，判断它们是否在同一条直线上，如果在，那么直线上的点的个数就加 1，如果不在，那么直线上的点的个数不变。找出所有直线上的点的个数的最大值，其对应的最小的两个点的编号即为答案。

时间复杂度 $O(n^3)$，空间复杂度 $O(1)$。其中 $n$ 是数组 `points` 的长度。

**方法二：枚举 + 哈希表**

我们可以枚举一个点 $(x_1, y_1)$，把其他所有点 $(x_2, y_2)$ 与 $(x_1, y_1)$ 连成的直线的斜率存入哈希表中，斜率相同的点在同一条直线上，哈希表的键为斜率，值为直线上的点的个数。找出哈希表中的最大值，即为答案。为了避免精度问题，我们可以将斜率 $\frac{y_2 - y_1}{x_2 - x_1}$ 进行约分，约分的方法是求最大公约数，然后分子分母同时除以最大公约数，将求得的分子分母作为哈希表的键。

时间复杂度 $O(n^2 \times \log m)$，空间复杂度 $O(n)$。其中 $n$ 和 $m$ 分别是数组 `points` 的长度和数组 `points` 所有横纵坐标差的最大值。

相似题目：

-   [149. 直线上最多的点数](/solution/0100-0199/0149.Max%20Points%20on%20a%20Line/README.md)

### **Java**

```java
class Solution {
    public int[] bestLine(int[][] points) {
        int n = points.length;
        int mx = 0;
        int[] ans = new int[2];
        for (int i = 0; i < n; ++i) {
            int x1 = points[i][0], y1 = points[i][1];
            for (int j = i + 1; j < n; ++j) {
                int x2 = points[j][0], y2 = points[j][1];
                int cnt = 2;
                for (int k = j + 1; k < n; ++k) {
                    int x3 = points[k][0], y3 = points[k][1];
                    int a = (y2 - y1) * (x3 - x1);
                    int b = (y3 - y1) * (x2 - x1);
                    if (a == b) {
                        ++cnt;
                    }
                }
                if (mx < cnt) {
                    mx = cnt;
                    ans[0] = i;
                    ans[1] = j;
                }
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int[] bestLine(int[][] points) {
        int n = points.length;
        int mx = 0;
        int[] ans = new int[2];
        for (int i = 0; i < n; ++i) {
            int x1 = points[i][0], y1 = points[i][1];
            Map<String, List<int[]>> cnt = new HashMap<>();
            for (int j = i + 1; j < n; ++j) {
                int x2 = points[j][0], y2 = points[j][1];
                int dx = x2 - x1, dy = y2 - y1;
                int g = gcd(dx, dy);
                String key = (dx / g) + "." + (dy / g);
                cnt.computeIfAbsent(key, k -> new ArrayList<>()).add(new int[] {i, j});
                if (mx < cnt.get(key).size()
                    || (mx == cnt.get(key).size()
                        && (ans[0] > cnt.get(key).get(0)[0]
                            || (ans[0] == cnt.get(key).get(0)[0]
                                && ans[1] > cnt.get(key).get(0)[1])))) {
                    mx = cnt.get(key).size();
                    ans = cnt.get(key).get(0);
                }
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```
