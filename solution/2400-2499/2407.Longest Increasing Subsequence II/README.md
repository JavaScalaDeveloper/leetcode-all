# [2407. 最长递增子序列 II](https://leetcode.cn/problems/longest-increasing-subsequence-ii)

[English Version](/solution/2400-2499/2407.Longest%20Increasing%20Subsequence%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;。</p>

<p>找到&nbsp;<code>nums</code>&nbsp;中满足以下要求的最长子序列：</p>

<ul>
	<li>子序列 <strong>严格递增</strong></li>
	<li>子序列中相邻元素的差值 <strong>不超过</strong>&nbsp;<code>k</code>&nbsp;。</li>
</ul>

<p>请你返回满足上述要求的 <strong>最长子序列</strong>&nbsp;的长度。</p>

<p><strong>子序列</strong>&nbsp;是从一个数组中删除部分元素后，剩余元素不改变顺序得到的数组。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [4,2,1,4,3,4,5,8,15], k = 3
<b>输出：</b>5
<strong>解释：</strong>
满足要求的最长子序列是 [1,3,4,5,8] 。
子序列长度为 5 ，所以我们返回 5 。
注意子序列 [1,3,4,5,8,15] 不满足要求，因为 15 - 8 = 7 大于 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [7,4,5,1,8,12,4,7], k = 5
<b>输出：</b>4
<strong>解释：</strong>
满足要求的最长子序列是 [4,5,8,12] 。
子序列长度为 4 ，所以我们返回 4 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>nums = [1,5], k = 1
<b>输出：</b>1
<strong>解释：</strong>
满足要求的最长子序列是 [1] 。
子序列长度为 1 ，所以我们返回 1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], k &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：线段树**

我们假设 `f[v]` 表示以数字 $v$ 结尾的最长递增子序列的长度。

我们遍历数组 `nums` 中的每个元素 $v$，有状态转移方程：`f[v]=max(f[v], f[x])`，其中 $x$ 的取值范围是 $[v-k, v-1]$。

因此，我们需要一个数据结构，来维护区间的最大值，不难想到使用线段树。

线段树将整个区间分割为多个不连续的子区间，子区间的数量不超过 $log(width)$。更新某个元素的值，只需要更新 $log(width)$ 个区间，并且这些区间都包含在一个包含该元素的大区间内。

-   线段树的每个节点代表一个区间；
-   线段树具有唯一的根节点，代表的区间是整个统计范围，如 $[1,N]$；
-   线段树的每个叶子节点代表一个长度为 $1$ 的元区间 $[x, x]$；
-   对于每个内部节点 $[l,r]$，它的左儿子是 $[l,mid]$，右儿子是 $[mid+1,r]$, 其中 $mid = \left \lfloor \frac{l+r}{2} \right \rfloor$。

对于本题，线段树节点维护的信息是区间范围内的最大值。

时间复杂度 $O(n\log n)$。其中 $n$ 是数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->



### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int lengthOfLIS(int[] nums, int k) {
        int mx = nums[0];
        for (int v : nums) {
            mx = Math.max(mx, v);
        }
        SegmentTree tree = new SegmentTree(mx);
        int ans = 0;
        for (int v : nums) {
            int t = tree.query(1, v - k, v - 1) + 1;
            ans = Math.max(ans, t);
            tree.modify(1, v, t);
        }
        return ans;
    }
}

class Node {
    int l;
    int r;
    int v;
}

class SegmentTree {
    private Node[] tr;

    public SegmentTree(int n) {
        tr = new Node[4 * n];
        for (int i = 0; i < tr.length; ++i) {
            tr[i] = new Node();
        }
        build(1, 1, n);
    }

    public void build(int u, int l, int r) {
        tr[u].l = l;
        tr[u].r = r;
        if (l == r) {
            return;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
    }

    public void modify(int u, int x, int v) {
        if (tr[u].l == x && tr[u].r == x) {
            tr[u].v = v;
            return;
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        if (x <= mid) {
            modify(u << 1, x, v);
        } else {
            modify(u << 1 | 1, x, v);
        }
        pushup(u);
    }

    public void pushup(int u) {
        tr[u].v = Math.max(tr[u << 1].v, tr[u << 1 | 1].v);
    }

    public int query(int u, int l, int r) {
        if (tr[u].l >= l && tr[u].r <= r) {
            return tr[u].v;
        }
        int mid = (tr[u].l + tr[u].r) >> 1;
        int v = 0;
        if (l <= mid) {
            v = query(u << 1, l, r);
        }
        if (r > mid) {
            v = Math.max(v, query(u << 1 | 1, l, r));
        }
        return v;
    }
}
```









### **TypeScript**



### **...**

```


```


