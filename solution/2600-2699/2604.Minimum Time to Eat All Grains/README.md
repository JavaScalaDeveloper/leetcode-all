# [2604. 吃掉所有谷子的最短时间](https://leetcode.cn/problems/minimum-time-to-eat-all-grains)

## 题目描述

<p>一条线上有 <code>n</code> 只母鸡和 <code>m</code> 颗谷子。给定两个整数数组 <code>hens</code> 和 <code>grains</code> ，它们的大小分别为 <code>n</code> 和 <code>m</code> ，表示母鸡和谷子的初始位置。</p>

<p>如果一只母鸡和一颗谷子在同一个位置，那么这只母鸡可以吃掉这颗谷子。吃掉一颗谷子的时间可以忽略不计。一只母鸡也可以吃掉多颗谷子。</p>

<p>在 <code>1</code> 秒钟内，一只母鸡可以向左或向右移动 <code>1</code> 个单位。母鸡可以同时且独立地移动。</p>

<p>如果母鸡行动得当，返回吃掉所有谷子的 <strong>最短</strong> 时间。</p>

<p><strong class="example">示例 1 ：</strong></p>

<pre>
<b>输入：</b>hens = [3,6,7], grains = [2,4,7,9]
<b>输出：</b>2
<b>解释：</b>
母鸡吃掉所有谷子的一种方法如下：
- 第一只母鸡在 1 秒钟内吃掉位置 2 处的谷子。
- 第二只母鸡在 2 秒钟内吃掉位置 4 处的谷子。
- 第三只母鸡在 2 秒钟内吃掉位置 7 和 9 处的谷子。 
所以，需要的最长时间为2秒。 
可以证明，在2秒钟之前，母鸡不能吃掉所有谷子。</pre>

<p><strong class="example">示例 2 ：</strong></p>

<pre>
<b>输入：</b>hens = [4,6,109,111,213,215], grains = [5,110,214]
<b>输出：</b>1
<b>解释：</b>
母鸡吃掉所有谷子的一种方法如下：
- 第一只母鸡在 1 秒钟内吃掉位置 5 处的谷子。
- 第四只母鸡在 1 秒钟内吃掉位置 110 处的谷子。
- 第六只母鸡在 1 秒钟内吃掉位置 214 处的谷子。
- 其他母鸡不动。 
所以，需要的最长时间为 1 秒。</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= hens.length, grains.length &lt;= 2*10<sup>4</sup></code></li>
	<li><code>0 &lt;= hens[i], grains[j] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

**方法一：排序 + 二分查找**

我们先将鸡和谷物按照位置从小到大排序，接下来二分枚举时间t，找到一个最小的t使得所有谷物能在t秒内被吃完。

对于每个鸡，我们用指针j指向当前还未被吃的谷物中最左边的谷物，记当前鸡的位置为x，谷物的位置为y，则有以下几种情况：

-   如果y \leq x，我们记d = x - y，如果d \gt t，那么当前谷物无法被吃掉，直接返回 `false`。否则，我们向右移动指针j，直到j=m或者grains[j] \gt x。此时我们需要判断鸡是否能吃到j指向的谷物，如果能吃到，我们继续向右移动指针j，直到j=m或者min(d, grains[j] - x) + grains[j] - y \gt t。
-   如果y \lt x，我们向右移动指针j，直到j=m或者grains[j] - x \gt t。

如果j=m，说明所有谷物都被吃完了，返回 `true`，否则返回 `false`。

时间复杂度O(n \times \log n + m \times \log m + (m + n) \times \log U)，空间复杂度O(\log m + \log n)。其中n和m分别为鸡和谷物的数量，而U为所有鸡和谷物的位置的最大值。

### **Java**

```java
class Solution {
    private int[] hens;
    private int[] grains;
    private int m;

    public int minimumTime(int[] hens, int[] grains) {
        m = grains.length;
        this.hens = hens;
        this.grains = grains;
        Arrays.sort(hens);
        Arrays.sort(grains);
        int l = 0;
        int r = Math.abs(hens[0] - grains[0]) + grains[m - 1] - grains[0];
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private boolean check(int t) {
        int j = 0;
        for (int x : hens) {
            if (j == m) {
                return true;
            }
            int y = grains[j];
            if (y <= x) {
                int d = x - y;
                if (d > t) {
                    return false;
                }
                while (j < m && grains[j] <= x) {
                    ++j;
                }
                while (j < m && Math.min(d, grains[j] - x) + grains[j] - y <= t) {
                    ++j;
                }
            } else {
                while (j < m && grains[j] - x <= t) {
                    ++j;
                }
            }
        }
        return j == m;
    }
}
```
