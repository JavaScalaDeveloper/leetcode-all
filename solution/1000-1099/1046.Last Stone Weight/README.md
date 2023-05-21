# [1046. 最后一块石头的重量](https://leetcode.cn/problems/last-stone-weight)

## 题目描述

<p>有一堆石头，每块石头的重量都是正整数。</p>

<p>每一回合，从中选出两块<strong> 最重的</strong> 石头，然后将它们一起粉碎。假设石头的重量分别为 <code>x</code> 和 <code>y</code>，且 <code>x <= y</code>。那么粉碎的可能结果如下：</p>

<ul>
	<li>如果 <code>x == y</code>，那么两块石头都会被完全粉碎；</li>
	<li>如果 <code>x != y</code>，那么重量为 <code>x</code> 的石头将会完全粉碎，而重量为 <code>y</code> 的石头新重量为 <code>y-x</code>。</li>
</ul>

<p>最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 <code>0</code>。</p>



<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>[2,7,4,1,8,1]
<strong>输出：</strong>1
<strong>解释：</strong>
先选出 7 和 8，得到 1，所以数组转换为 [2,4,1,1,1]，
再选出 2 和 4，得到 2，所以数组转换为 [2,1,1,1]，
接着是 2 和 1，得到 1，所以数组转换为 [1,1,1]，
最后选出 1 和 1，得到 0，最终数组转换为 [1]，这就是最后剩下那块石头的重量。</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= stones.length <= 30</code></li>
	<li><code>1 <= stones[i] <= 1000</code></li>
</ul>

## 解法

**方法一：优先队列（大根堆）**

我们将数组 `stones` 所有元素放入大根堆，然后执行循环操作，每次弹出两个元素 $y$ 和 $x$，如果 $x \neq y$，将 $y - x$ 放入大根堆。当堆元素个数小于 $2$ 时，退出循环。

最后如果存在堆顶元素，则将其返回，否则返回 $0$。

时间复杂度 $O(n\log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 `stones` 的长度。

### **Java**

```java
class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        for (int x : stones) {
            q.offer(x);
        }
        while (q.size() > 1) {
            int y = q.poll();
            int x = q.poll();
            if (x != y) {
                q.offer(y - x);
            }
        }
        return q.isEmpty() ? 0 : q.poll();
    }
}
```
