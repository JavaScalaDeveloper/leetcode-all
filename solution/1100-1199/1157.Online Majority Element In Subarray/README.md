# [1157. 子数组中占绝大多数的元素](https://leetcode.cn/problems/online-majority-element-in-subarray)

## 题目描述

<p>设计一个数据结构，有效地找到给定子数组的 <strong>多数元素</strong> 。</p>

<p>子数组的 <strong>多数元素</strong> 是在子数组中出现&nbsp;<code>threshold</code>&nbsp;次数或次数以上的元素。</p>

<p>实现 <code>MajorityChecker</code> 类:</p>

<ul>
	<li><code>MajorityChecker(int[] arr)</code>&nbsp;会用给定的数组 <code>arr</code>&nbsp;对&nbsp;<code>MajorityChecker</code> 初始化。</li>
	<li><code>int query(int left, int right, int threshold)</code>&nbsp;返回子数组中的元素 &nbsp;<code>arr[left...right]</code>&nbsp;至少出现&nbsp;<code>threshold</code>&nbsp;次数，如果不存在这样的元素则返回 <code>-1</code>。</li>
</ul>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong>
["MajorityChecker", "query", "query", "query"]
[[[1, 1, 2, 2, 1, 1]], [0, 5, 4], [0, 3, 3], [2, 3, 2]]
<strong>输出：</strong>
[null, 1, -1, 2]

<b>解释：</b>
MajorityChecker majorityChecker = new MajorityChecker([1,1,2,2,1,1]);
majorityChecker.query(0,5,4); // 返回 1
majorityChecker.query(0,3,3); // 返回 -1
majorityChecker.query(2,3,2); // 返回 2
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= arr[i] &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= left &lt;= right &lt; arr.length</code></li>
	<li><code>threshold &lt;= right - left + 1</code></li>
	<li><code>2 * threshold &gt; right - left + 1</code></li>
	<li>调用&nbsp;<code>query</code>&nbsp;的次数最多为&nbsp;<code>10<sup>4</sup></code>&nbsp;</li>
</ul>

## 解法

**方法一：线段树 + 摩尔投票 + 二分查找**

我们注意到，题目需要我们找出特定区间内可能的众数，考虑使用线段树来维护每个区间内的候选众数以及其出现的次数。

我们定义线段树的每个节点为 `Node`，每个节点包含如下属性：

-   `l`：节点的左端点，下标从1开始。
-   `r`：节点的右端点，下标从1开始。
-   `x`：节点的候选众数。
-   `cnt`：节点的候选众数出现的次数。

线段树主要有以下几个操作：

-   `build(u, l, r)`：建立线段树。
-   `pushup(u)`：用子节点的信息更新父节点的信息。
-   `query(u, l, r)`：查询区间和。

在主函数的初始化方法中，我们先创建一个线段树，并且用哈希表d记录每个元素在数组中的所有下标。

在 `query(left, right, threshold)` 方法中，我们直接调用线段树的 `query` 方法，得到候选众数x。然后使用二分查找，找到x在数组中第一个大于等于left的下标l，以及第一个大于right的下标r。如果r - l ≥ threshold，则返回x，否则返回-1。

时间复杂度方面，初始化方法的时间复杂度为O(n)，查询方法的时间复杂度为O(log n)。空间复杂度为O(n)。其中n为数组的长度。

### **Java**

```java
class Node {
    int l, r;
    int x, cnt;
}

class SegmentTree {
    private Node[] tr;
    private int[] nums;

    public SegmentTree(int[] nums) {
        int n = nums.length;
        this.nums = nums;
        tr = new Node[n << 2];
        for (int i = 0; i < tr.length; ++i) {
            tr[i] = new Node();
        }
        build(1, 1, n);
    }

    private void build(int u, int l, int r) {
        tr[u].l = l;
        tr[u].r = r;
        if (l == r) {
            tr[u].x = nums[l - 1];
            tr[u].cnt = 1;
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
        pushup(u);
    }

    public int[] query(int u, int l, int r) {
        if (tr[u].l >= l && tr[u].r <= r) {
            return new int[] {tr[u].x, tr[u].cnt};
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        if (r <= mid) {
            return query(u << 1, l, r);
        }
        if (l > mid) {
            return query(u << 1 | 1, l, r);
        }
        var left = query(u << 1, l, r);
        var right = query(u << 1 | 1, l, r);
        if (left[0] == right[0]) {
            left[1] += right[1];
        } else if (left[1] >= right[1]) {
            left[1] -= right[1];
        } else {
            right[1] -= left[1];
            left = right;
        }
        return left;
    }

    private void pushup(int u) {
        if (tr[u << 1].x == tr[u << 1 | 1].x) {
            tr[u].x = tr[u << 1].x;
            tr[u].cnt = tr[u << 1].cnt + tr[u << 1 | 1].cnt;
        } else if (tr[u << 1].cnt >= tr[u << 1 | 1].cnt) {
            tr[u].x = tr[u << 1].x;
            tr[u].cnt = tr[u << 1].cnt - tr[u << 1 | 1].cnt;
        } else {
            tr[u].x = tr[u << 1 | 1].x;
            tr[u].cnt = tr[u << 1 | 1].cnt - tr[u << 1].cnt;
        }
    }
}

class MajorityChecker {
    private SegmentTree tree;
    private Map<Integer, List<Integer>> d = new HashMap<>();

    public MajorityChecker(int[] arr) {
        tree = new SegmentTree(arr);
        for (int i = 0; i < arr.length; ++i) {
            d.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
    }

    public int query(int left, int right, int threshold) {
        int x = tree.query(1, left + 1, right + 1)[0];
        int l = search(d.get(x), left);
        int r = search(d.get(x), right + 1);
        return r - l >= threshold ? x : -1;
    }

    private int search(List<Integer> arr, int x) {
        int left = 0, right = arr.size();
        while (left < right) {
            int mid = (left + right) >> 1;
            if (arr.get(mid) >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}

/**
 * Your MajorityChecker object will be instantiated and called as such:
 * MajorityChecker obj = new MajorityChecker(arr);
 * int param_1 = obj.query(left,right,threshold);
 */
```
