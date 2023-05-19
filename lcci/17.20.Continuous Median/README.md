# [面试题 17.20. 连续中值](https://leetcode.cn/problems/continuous-median-lcci)

[English Version](/lcci/17.20.Continuous%20Median/README_EN.md)

## 题目描述


<p>随机产生数字并传递给一个方法。你能否完成这个方法，在每次产生新值时，寻找当前所有值的中间值（中位数）并保存。</p>

<p>中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。</p>

<p>例如，</p>

<p>[2,3,4]&nbsp;的中位数是 3</p>

<p>[2,3] 的中位数是 (2 + 3) / 2 = 2.5</p>

<p>设计一个支持以下两种操作的数据结构：</p>

<ul>
	<li>void addNum(int num) - 从数据流中添加一个整数到数据结构中。</li>
	<li>double findMedian() - 返回目前所有元素的中位数。</li>
</ul>

<p><strong>示例：</strong></p>

<pre>addNum(1)
addNum(2)
findMedian() -&gt; 1.5
addNum(3) 
findMedian() -&gt; 2
</pre>

## 解法

**方法一：优先队列（双堆）**

创建大根堆、小根堆，其中：大根堆存放较小的一半元素，小根堆存放较大的一半元素。

添加元素时，先放入小根堆，然后将小根堆对顶元素弹出并放入大根堆（使得大根堆个数多 $1$）；若大小根堆元素个数差超过 $1$，则将大根堆元素弹出放入小根堆。

取中位数时，若大根堆元素较多，取大根堆堆顶，否则取两堆顶元素和的平均值。

**时间复杂度分析：**

每次添加元素的时间复杂度为 $O(\log n)$，取中位数的时间复杂度为 $O(1)$。

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
