# [2426. 满足不等式的数对数目](https://leetcode.cn/problems/number-of-pairs-satisfying-inequality)

[English Version](/solution/2400-2499/2426.Number%20of%20Pairs%20Satisfying%20Inequality/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums1</code> 和&nbsp;<code>nums2</code>&nbsp;，两个数组的大小都为&nbsp;<code>n</code>&nbsp;，同时给你一个整数&nbsp;<code>diff</code>&nbsp;，统计满足以下条件的&nbsp;<strong>数对&nbsp;</strong><code>(i, j)</code>&nbsp;：</p>

<ul>
	<li><code>0 &lt;= i &lt; j &lt;= n - 1</code>&nbsp;<b>且</b></li>
	<li><code>nums1[i] - nums1[j] &lt;= nums2[i] - nums2[j] + diff</code>.</li>
</ul>

<p>请你返回满足条件的 <strong>数对数目</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums1 = [3,2,5], nums2 = [2,2,1], diff = 1
<b>输出：</b>3
<strong>解释：</strong>
总共有 3 个满足条件的数对：
1. i = 0, j = 1：3 - 2 &lt;= 2 - 2 + 1 。因为 i &lt; j 且 1 &lt;= 1 ，这个数对满足条件。
2. i = 0, j = 2：3 - 5 &lt;= 2 - 1 + 1 。因为 i &lt; j 且 -2 &lt;= 2 ，这个数对满足条件。
3. i = 1, j = 2：2 - 5 &lt;= 2 - 1 + 1 。因为 i &lt; j 且 -3 &lt;= 2 ，这个数对满足条件。
所以，我们返回 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums1 = [3,-1], nums2 = [-2,2], diff = -1
<b>输出：</b>0
<strong>解释：</strong>
没有满足条件的任何数对，所以我们返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums1[i], nums2[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= diff &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

**方法一：树状数组**

我们将题目的不等式转换一下，得到 `nums1[i] - nums2[i] <= nums1[j] - nums2[j] + diff`，因此，如果我们对两个数组对应位置的元素求差值，得到另一个数组 `nums`，那么题目就转换为求 `nums` 中满足 `nums[i] <= nums[j] + diff` 的数对数目。

我们可以从小到大枚举 $j$，找出前面有多少个数满足 `nums[i] <= nums[j] + diff`，这样就可以求出数对数目。我们可以使用树状数组来维护前缀和，这样就可以在 $O(\log n)$ 的时间内求出前面有多少个数满足 `nums[i] <= nums[j] + diff`。

时间复杂度 $O(n\log n)$。

### **Java**

```java
class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new int[n + 1];
    }

    public static final int lowbit(int x) {
        return x & -x;
    }

    public void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += lowbit(x);
        }
    }

    public int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= lowbit(x);
        }
        return s;
    }
}

class Solution {
    public long numberOfPairs(int[] nums1, int[] nums2, int diff) {
        BinaryIndexedTree tree = new BinaryIndexedTree(100000);
        long ans = 0;
        for (int i = 0; i < nums1.length; ++i) {
            int v = nums1[i] - nums2[i];
            ans += tree.query(v + diff + 40000);
            tree.update(v + 40000, 1);
        }
        return ans;
    }
}
```

### **TypeScript**
