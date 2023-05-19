# [面试题 40. 最小的 k 个数](https://leetcode.cn/problems/zui-xiao-de-kge-shu-lcof/)

## 题目描述

<p>输入整数数组 <code>arr</code> ，找出其中最小的 <code>k</code> 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [3,2,1], k = 2
<strong>输出：</strong>[1,2] 或者 [2,1]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr = [0,1,2,1], k = 1
<strong>输出：</strong>[0]</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<ul>
	<li><code>0 &lt;= k &lt;= arr.length &lt;= 10000</code></li>
	<li><code>0 &lt;= arr[i]&nbsp;&lt;= 10000</code></li>
</ul>

## 解法

**方法一：排序**

我们可以直接对数组 `arr` 按从小到大排序，然后取前 $k$ 个数即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组 `arr` 的长度。

**方法二：优先队列（大根堆）**

我们可以用优先队列（大根堆）维护最小的 $k$ 个数。

遍历数组 `arr`，对于当前遍历到的数 $x$，我们先将其加入优先队列中，然后判断优先队列的大小是否超过 $k$，如果超过了，就将堆顶元素弹出。最后将优先队列中的数存入数组并返回即可。

时间复杂度 $O(n \times \log k)$，空间复杂度 $O(k)$。其中 $n$ 为数组 `arr` 的长度。

**方法三：快排思想**

我们可以利用快速排序的思想，每次划分后判断划分点的位置是否为 $k$，如果是，就直接返回划分点左边的数即可，否则根据划分点的位置决定下一步划分的区间。

时间复杂度 $O(n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组 `arr` 的长度。

### **Java**

```java
class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
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
    public int[] getLeastNumbers(int[] arr, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        for (int x : arr) {
            q.offer(x);
            if (q.size() > k) {
                q.poll();
            }
        }
        int[] ans = new int[k];
        for (int i = 0; i < k; ++i) {
            ans[i] = q.poll();
        }
        return ans;
    }
}
```

```java
class Solution {
    private int[] arr;
    private int k;

    public int[] getLeastNumbers(int[] arr, int k) {
        int n = arr.length;
        this.arr = arr;
        this.k = k;
        return k == n ? arr : quickSort(0, n - 1);
    }

    private int[] quickSort(int l, int r) {
        int i = l, j = r;
        while (i < j) {
            while (i < j && arr[j] >= arr[l]) {
                --j;
            }
            while (i < j && arr[i] <= arr[l]) {
                ++i;
            }
            swap(i, j);
        }
        swap(i, l);
        if (k < i) {
            return quickSort(l, i - 1);
        }
        if (k > i) {
            return quickSort(i + 1, r);
        }
        return Arrays.copyOf(arr, k);
    }

    private void swap(int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
```
