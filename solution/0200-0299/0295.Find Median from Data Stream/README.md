# [295. 数据流的中位数](https://leetcode.cn/problems/find-median-from-data-stream)

## 题目描述

<p><strong>中位数</strong>是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。</p>

<ul>
	<li>例如 <code>arr = [2,3,4]</code>&nbsp;的中位数是 <code>3</code>&nbsp;。</li>
	<li>例如&nbsp;<code>arr = [2,3]</code> 的中位数是 <code>(2 + 3) / 2 = 2.5</code> 。</li>
</ul>

<p>实现 MedianFinder 类:</p>

<ul>
	<li>
	<p><code>MedianFinder() </code>初始化 <code>MedianFinder</code>&nbsp;对象。</p>
	</li>
	<li>
	<p><code>void addNum(int num)</code> 将数据流中的整数 <code>num</code> 添加到数据结构中。</p>
	</li>
	<li>
	<p><code>double findMedian()</code> 返回到目前为止所有元素的中位数。与实际答案相差&nbsp;<code>10<sup>-5</sup></code>&nbsp;以内的答案将被接受。</p>
	</li>
</ul>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入</strong>
["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
[[], [1], [2], [], [3], []]
<strong>输出</strong>
[null, null, null, 1.5, null, 2.0]

<strong>解释</strong>
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);    // arr = [1]
medianFinder.addNum(2);    // arr = [1, 2]
medianFinder.findMedian(); // 返回 1.5 ((1 + 2) / 2)
medianFinder.addNum(3);    // arr[1, 2, 3]
medianFinder.findMedian(); // return 2.0</pre>

<p><strong>提示:</strong></p>

<ul>
	<li><code>-10<sup>5</sup>&nbsp;&lt;= num &lt;= 10<sup>5</sup></code></li>
	<li>在调用 <code>findMedian</code>&nbsp;之前，数据结构中至少有一个元素</li>
	<li>最多&nbsp;<code>5 * 10<sup>4</sup></code>&nbsp;次调用&nbsp;<code>addNum</code>&nbsp;和&nbsp;<code>findMedian</code></li>
</ul>

## 解法

**方法一：优先队列（双堆）**

创建大根堆、小根堆，其中：大根堆存放较小的一半元素，小根堆存放较大的一半元素。

添加元素时，先放入小根堆，然后将小根堆对顶元素弹出并放入大根堆（使得大根堆个数多1）；若大小根堆元素个数差超过1，则将大根堆元素弹出放入小根堆。

取中位数时，若大根堆元素较多，取大根堆堆顶，否则取两堆顶元素和的平均值。

**时间复杂度分析：**

每次添加元素的时间复杂度为O(\log n)，取中位数的时间复杂度为O(1)。

### **Java**

```java
class MedianFinder {
    private PriorityQueue<Integer> q1 = new PriorityQueue<>();
    private PriorityQueue<Integer> q2 = new PriorityQueue<>(Collections.reverseOrder());

    /** initialize your data structure here. */
    public MedianFinder() {
    }

    public void addNum(int num) {
        q1.offer(num);
        q2.offer(q1.poll());
        if (q2.size() - q1.size() > 1) {
            q1.offer(q2.poll());
        }
    }

    public double findMedian() {
        if (q2.size() > q1.size()) {
            return q2.peek();
        }
        return (q1.peek() + q2.peek()) * 1.0 / 2;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
```
