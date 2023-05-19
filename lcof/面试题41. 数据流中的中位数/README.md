# [面试题 41. 数据流中的中位数](https://leetcode.cn/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof/)

## 题目描述



<p>如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。</p>

<p>例如，</p>

<p>[2,3,4]&nbsp;的中位数是 3</p>

<p>[2,3] 的中位数是 (2 + 3) / 2 = 2.5</p>

<p>设计一个支持以下两种操作的数据结构：</p>

<ul>
	<li>void addNum(int num) - 从数据流中添加一个整数到数据结构中。</li>
	<li>double findMedian() - 返回目前所有元素的中位数。</li>
</ul>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：
</strong>[&quot;MedianFinder&quot;,&quot;addNum&quot;,&quot;addNum&quot;,&quot;findMedian&quot;,&quot;addNum&quot;,&quot;findMedian&quot;]
[[],[1],[2],[],[3],[]]
<strong>输出：</strong>[null,null,null,1.50000,null,2.00000]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：
</strong>[&quot;MedianFinder&quot;,&quot;addNum&quot;,&quot;findMedian&quot;,&quot;addNum&quot;,&quot;findMedian&quot;]
[[],[2],[],[3],[]]
<strong>输出：</strong>[null,null,2.00000,null,2.50000]</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<ul>
	<li>最多会对&nbsp;<code>addNum、findMedian</code> 进行&nbsp;<code>50000</code>&nbsp;次调用。</li>
</ul>

<p>注意：本题与主站 295 题相同：<a href="https://leetcode.cn/problems/find-median-from-data-stream/">https://leetcode.cn/problems/find-median-from-data-stream/</a></p>

## 解法

**方法一：优先队列（大小根堆）**

我们可以维护两个优先队列，一个大根堆，一个小根堆，大根堆存储较小的一半数，小根堆存储较大的一半数。

当两个堆的元素个数相同时，我们优先往小根堆中添加元素，这样会使得小根堆元素个数比大根堆多 $1$，这样中位数就可以从小根堆中取出。

当两个堆的元素个数不同时，说明此时小根堆元素个数比大根堆多 $1$，我们往大根堆中添加元素，这样会使得两个堆元素个数相同，这样中位数就可以从两个堆中取出。

时间复杂度方面，添加元素的时间复杂度为 $O(\log n)$，查找中位数的时间复杂度为 $O(1)$。空间复杂度为 $O(n)$。其中 $n$ 为数据流中元素的个数。

### **Java**

```java
class MedianFinder {
    private PriorityQueue<Integer> q1 = new PriorityQueue<>();
    private PriorityQueue<Integer> q2 = new PriorityQueue<>((a, b) -> b - a);

    /** initialize your data structure here. */
    public MedianFinder() {
    }

    public void addNum(int num) {
        if (q1.size() > q2.size()) {
            q1.offer(num);
            q2.offer(q1.poll());
        } else {
            q2.offer(num);
            q1.offer(q2.poll());
        }
    }

    public double findMedian() {
        if (q1.size() > q2.size()) {
            return q1.peek();
        }
        return (q1.peek() + q2.peek()) / 2.0;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
```
