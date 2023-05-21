# [1033. 移动石子直到连续](https://leetcode.cn/problems/moving-stones-until-consecutive)

## 题目描述

<p>三枚石子放置在数轴上，位置分别为 <code>a</code>，<code>b</code>，<code>c</code>。</p>

<p>每一回合，你可以从两端之一拿起一枚石子（位置最大或最小），并将其放入两端之间的任一空闲位置。形式上，假设这三枚石子当前分别位于位置 <code>x, y, z</code> 且 <code>x < y < z</code>。那么就可以从位置 <code>x</code> 或者是位置 <code>z</code> 拿起一枚石子，并将该石子移动到某一整数位置 <code>k</code> 处，其中 <code>x < k < z</code> 且 <code>k != y</code>。</p>

<p>当你无法进行任何移动时，即，这些石子的位置连续时，游戏结束。</p>

<p>要使游戏结束，你可以执行的最小和最大移动次数分别是多少？ 以长度为 2 的数组形式返回答案：<code>answer = [minimum_moves, maximum_moves]</code></p>



<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>a = 1, b = 2, c = 5
<strong>输出：</strong>[1, 2]
<strong>解释：</strong>将石子从 5 移动到 4 再移动到 3，或者我们可以直接将石子移动到 3。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>a = 4, b = 3, c = 2
<strong>输出：</strong>[0, 0]
<strong>解释：</strong>我们无法进行任何移动。
</pre>



<p><strong>提示：</strong></p>

<ol>
	<li><code>1 <= a <= 100</code></li>
	<li><code>1 <= b <= 100</code></li>
	<li><code>1 <= c <= 100</code></li>
	<li><code>a != b, b != c, c != a</code></li>
</ol>

## 解法

**方法一：分类讨论**

我们先将a, b, c排序，记为x, y, z，即x < y < z。

接下来分情况讨论：

1. 如果z - x ≤ 2，说明3个数已经相邻，不用移动，结果为[0, 0]；
1. 否则，如果y - x < 3，或者z - y < 3，说明有两个数只间隔一个位置，我们只需要把另一个数移动到这两个数的中间，最小移动次数为1；其他情况，最小移动次数为2；
1. 最大移动次数就是两边的数字逐个往中间靠，最多移动z - x - 2次。

时间复杂度O(1)，空间复杂度O(1)。

### **Java**

```java
class Solution {
    public int[] numMovesStones(int a, int b, int c) {
        int x = Math.min(a, Math.min(b, c));
        int z = Math.max(a, Math.max(b, c));
        int y = a + b + c - x - z;
        int mi = 0, mx = 0;
        if (z - x > 2) {
            mi = y - x < 3 || z - y < 3 ? 1 : 2;
            mx = z - x - 2;
        }
        return new int[] {mi, mx};
    }
}
```
