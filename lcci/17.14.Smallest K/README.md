# [面试题 17.14. 最小 K 个数](https://leetcode.cn/problems/smallest-k-lcci)

[English Version](/lcci/17.14.Smallest%20K/README_EN.md)

## 题目描述


<p>设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。</p>
<p><strong>示例：</strong></p>
<pre><strong>输入：</strong> arr = [1,3,5,7,2,4,6,8], k = 4
<strong>输出：</strong> [1,2,3,4]
</pre>
<p><strong>提示：</strong></p>
<ul>
	<li><code>0 &lt;= len(arr) &lt;= 100000</code></li>
	<li><code>0 &lt;= k &lt;= min(100000, len(arr))</code></li>
</ul>

## 解法

**方法一：排序**

直接排序，取前 k 个数即可。

时间复杂度 $O(n\log n)$。其中 $n$ 为数组长度。

**方法二：优先队列（大根堆）**

维护一个大小为 $k$ 的大根堆，遍历数组，将当前元素入堆，如果堆的大小超过 $k$，弹出堆顶元素。

遍历结束，将堆中元素的 $k$ 个元素取出即可。

时间复杂度 $O(n\log k)$。其中 $n$ 为数组长度。

### **Java**

```java
class Solution {
    public int[] smallestK(int[] arr, int k) {
        Arrays.sort(arr);
        int[] ans = new int[k];
        for (int i = 0; i < k; ++i) {
            ans[i] = arr[i];
        }
        return ans;
    }
}
```

```java
class Solution {
    public int[] smallestK(int[] arr, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        for (int v : arr) {
            q.offer(v);
            if (q.size() > k) {
                q.poll();
            }
        }
        int[] ans = new int[k];
        int i = 0;
        while (!q.isEmpty()) {
            ans[i++] = q.poll();
        }
        return ans;
    }
}
```
