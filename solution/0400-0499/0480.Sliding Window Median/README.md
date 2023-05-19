# [480. 滑动窗口中位数](https://leetcode.cn/problems/sliding-window-median)

[English Version](/solution/0400-0499/0480.Sliding%20Window%20Median/README_EN.md)

## 题目描述

<p>中位数是有序序列最中间的那个数。如果序列的长度是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。</p>

<p>例如：</p>

<ul>
	<li><code>[2,3,4]</code>，中位数是 <code>3</code></li>
	<li><code>[2,3]</code>，中位数是 <code>(2 + 3) / 2 = 2.5</code></li>
</ul>

<p>给你一个数组 <em>nums</em>，有一个长度为 <em>k</em> 的窗口从最左端滑动到最右端。窗口中有 <em>k</em> 个数，每次窗口向右移动 <em>1</em> 位。你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。</p>

<p> </p>

<p><strong>示例：</strong></p>

<p>给出 <em>nums</em> = <code>[1,3,-1,-3,5,3,6,7]</code>，以及 <em>k</em> = 3。</p>

<pre>
窗口位置                      中位数
---------------               -----
[1  3  -1] -3  5  3  6  7       1
 1 [3  -1  -3] 5  3  6  7      -1
 1  3 [-1  -3  5] 3  6  7      -1
 1  3  -1 [-3  5  3] 6  7       3
 1  3  -1  -3 [5  3  6] 7       5
 1  3  -1  -3  5 [3  6  7]      6
</pre>

<p> 因此，返回该滑动窗口的中位数数组 <code>[1,-1,-1,3,5,6]</code>。</p>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>你可以假设 <code>k</code> 始终有效，即：<code>k</code> 始终小于等于输入的非空数组的元素个数。</li>
	<li>与真实值误差在 <code>10 ^ -5</code> 以内的答案将被视作正确答案。</li>
</ul>

## 解法

**方法一：双优先队列（大小根堆） + 延迟删除**

我们可以使用两个优先队列（大小根堆）维护当前窗口中的元素，其中一个优先队列存储当前窗口中较小的一半元素，另一个优先队列存储当前窗口中较大的一半元素。这样，当前窗口的中位数就是两个优先队列的堆顶元素的平均值或其中的一个。

我们设计一个类 `MedianFinder`，用于维护当前窗口中的元素。该类包含以下方法：

-   `add_num(num)`：将 `num` 加入当前窗口中。
-   `find_median()`：返回当前窗口中元素的中位数。
-   `remove_num(num)`：将 `num` 从当前窗口中移除。
-   `prune(pq)`：如果堆顶元素在延迟删除字典 `delayed` 中，则将其从堆顶弹出，并从该元素的延迟删除次数中减一。如果该元素的延迟删除次数为零，则将其从延迟删除字典中删除。
-   `rebalance()`：如果较小的一半元素的数量比较大的一半元素的数量多 2 个，则将较大的一半元素的堆顶元素加入较小的一半元素中；如果较小的一半元素的数量比较大的一半元素的数量少，则将较大的一半元素的堆顶元素加入较小的一半元素中。

在 `add_num(num)` 方法中，我们先考虑将 `num` 加入较小的一半元素中，如果 `num` 大于较大的一半元素的堆顶元素，则将 `num` 加入较大的一半元素中。然后我们调用 `rebalance()` 方法，使得两个优先队列的大小之差不超过 $1$。

在 `remove_num(num)` 方法中，我们将 `num` 的延迟删除次数加一。然后我们将 `num` 与较小的一半元素的堆顶元素进行比较，如果 `num` 小于等于较小的一半元素的堆顶元素，则更新较小的一半元素的大小，并且调用 `prune()` 方法，使得较小的一半元素的堆顶元素不在延迟删除字典中。否则，我们更新较大的一半元素的大小，并且调用 `prune()` 方法，使得较大的一半元素的堆顶元素不在延迟删除字典中。

在 `find_median()` 方法中，如果当前窗口的大小为奇数，则返回较小的一半元素的堆顶元素；否则，返回较小的一半元素的堆顶元素与较大的一半元素的堆顶元素的平均值。

在 `prune(pq)` 方法中，如果堆顶元素在延迟删除字典中，则将其从堆顶弹出，并从该元素的延迟删除次数中减一。如果该元素的延迟删除次数为零，则将其从延迟删除字典中删除。

在 `rebalance()` 方法中，如果较小的一半元素的数量比较大的一半元素的数量多 2 个，则将较大的一半元素的堆顶元素加入较小的一半元素中；如果较小的一半元素的数量比较大的一半元素的数量少，则将较大的一半元素的堆顶元素加入较小的一半元素中。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 `nums` 的长度。

### **Java**

```java
class MedianFinder {
    private PriorityQueue<Integer> small = new PriorityQueue<>(Comparator.reverseOrder());
    private PriorityQueue<Integer> large = new PriorityQueue<>();
    private Map<Integer, Integer> delayed = new HashMap<>();
    private int smallSize;
    private int largeSize;
    private int k;

    public MedianFinder(int k) {
        this.k = k;
    }

    public void addNum(int num) {
        if (small.isEmpty() || num <= small.peek()) {
            small.offer(num);
            ++smallSize;
        } else {
            large.offer(num);
            ++largeSize;
        }
        rebalance();
    }

    public double findMedian() {
        return (k & 1) == 1 ? small.peek() : ((double) small.peek() + large.peek()) / 2;
    }

    public void removeNum(int num) {
        delayed.merge(num, 1, Integer::sum);
        if (num <= small.peek()) {
            --smallSize;
            if (num == small.peek()) {
                prune(small);
            }
        } else {
            --largeSize;
            if (num == large.peek()) {
                prune(large);
            }
        }
        rebalance();
    }

    private void prune(PriorityQueue<Integer> pq) {
        while (!pq.isEmpty() && delayed.containsKey(pq.peek())) {
            if (delayed.merge(pq.peek(), -1, Integer::sum) == 0) {
                delayed.remove(pq.peek());
            }
            pq.poll();
        }
    }

    private void rebalance() {
        if (smallSize > largeSize + 1) {
            large.offer(small.poll());
            --smallSize;
            ++largeSize;
            prune(small);
        } else if (smallSize < largeSize) {
            small.offer(large.poll());
            --largeSize;
            ++smallSize;
            prune(large);
        }
    }
}

class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        MedianFinder finder = new MedianFinder(k);
        for (int i = 0; i < k; ++i) {
            finder.addNum(nums[i]);
        }
        int n = nums.length;
        double[] ans = new double[n - k + 1];
        ans[0] = finder.findMedian();
        for (int i = k; i < n; ++i) {
            finder.addNum(nums[i]);
            finder.removeNum(nums[i - k]);
            ans[i - k + 1] = finder.findMedian();
        }
        return ans;
    }
}
```
