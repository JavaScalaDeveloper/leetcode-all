# [1756. 设计最近使用（MRU）队列](https://leetcode.cn/problems/design-most-recently-used-queue)

## 题目描述

<p>设计一种类似队列的数据结构，该数据结构将最近使用的元素移到队列尾部。</p>

<p>实现 <code>MRUQueue</code> 类：</p>

<ul>
	<li><code>MRUQueue(int n)</code>  使用 <code>n</code> 个元素： <code>[1,2,3,...,n]</code> 构造 <code>MRUQueue</code> 。</li>
	<li><code>fetch(int k)</code> 将第 <code>k</code> 个元素<strong>（从 1 开始索引）</strong>移到队尾，并返回该元素。</li>
</ul>

<p> </p>

<p><b>示例 1：</b></p>

<pre>
<strong>输入：</strong>
["MRUQueue", "fetch", "fetch", "fetch", "fetch"]
[[8], [3], [5], [2], [8]]
<strong>输出：</strong>
[null, 3, 6, 2, 2]

<strong>解释：</strong>
MRUQueue mRUQueue = new MRUQueue(8); // 初始化队列为 [1,2,3,4,5,6,7,8]。
mRUQueue.fetch(3); // 将第 3 个元素 (3) 移到队尾，使队列变为 [1,2,4,5,6,7,8,3] 并返回该元素。
mRUQueue.fetch(5); // 将第 5 个元素 (6) 移到队尾，使队列变为 [1,2,4,5,7,8,3,6] 并返回该元素。
mRUQueue.fetch(2); // 将第 2 个元素 (2) 移到队尾，使队列变为 [1,4,5,7,8,3,6,2] 并返回该元素。
mRUQueue.fetch(8); // 第 8 个元素 (2) 已经在队列尾部了，所以直接返回该元素即可。
</pre>

<p> </p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 <= n <= 2000</code></li>
	<li><code>1 <= k <= n</code></li>
	<li>最多调用 <code>2000</code> 次 <code>fetch</code></li>
</ul>

<p> </p>
<b>进阶：</b>找到每次 <code>fetch</code> 的复杂度为 <code>O(n)</code> 的算法比较简单。你可以找到每次 <code>fetch</code> 的复杂度更佳的算法吗？

## 解法

**方法一：树状数组 + 二分查找**

我们用一个数组 $q$ 维护当前队列中的元素，移动第 $k$ 个元素时，我们考虑不删除该元素，而是直接将其追加到数组末尾。如果不删除，我们如何知道第 $k$ 个元素在数组 $q$ 中的位置呢？

我们可以用一个树状数组维护数组 $q$ 中每个位置的元素是否被删除，如果第 $i$ 个位置的元素被删除，那么我们更新树状数组中的第 $i$ 个位置，表示该位置被移动的次数增加 $1$。这样，我们每次要删除第 $k$ 个元素时，可以用二分查找，找到第一个满足 $i - tree.query(i) \geq k$ 的位置 $i$，即为第 $k$ 个元素在数组 $q$ 中的位置。不妨记 $x=q[i]$，那么我们将 $x$ 追加到数组 $q$ 的末尾，同时更新树状数组中第 $i$ 个位置的值，表示该位置被移动的次数增加 $1$。最后，我们返回 $x$ 即可。

时间复杂度 $(\log ^2 n)$，空间复杂度 $O(n)$。其中 $n$ 为队列的长度。

### **Java**

```java
class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        this.c = new int[n + 1];
    }

    public void update(int x, int v) {
        while (x <= n) {
            c[x] += v;
            x += x & -x;
        }
    }

    public int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= x & -x;
        }
        return s;
    }
}

class MRUQueue {
    private int n;
    private int[] q;
    private BinaryIndexedTree tree;

    public MRUQueue(int n) {
        this.n = n;
        q = new int[n + 2010];
        for (int i = 1; i <= n; ++i) {
            q[i] = i;
        }
        tree = new BinaryIndexedTree(n + 2010);
    }

    public int fetch(int k) {
        int l = 1, r = n;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (mid - tree.query(mid) >= k) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        int x = q[l];
        q[++n] = x;
        tree.update(l, 1);
        return x;
    }
}

/**
 * Your MRUQueue object will be instantiated and called as such:
 * MRUQueue obj = new MRUQueue(n);
 * int param_1 = obj.fetch(k);
 */
```
