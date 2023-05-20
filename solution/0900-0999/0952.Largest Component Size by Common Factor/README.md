# [952. 按公因数计算最大组件大小](https://leetcode.cn/problems/largest-component-size-by-common-factor)

## 题目描述

<p>给定一个由不同正整数的组成的非空数组&nbsp;<code>nums</code> ，考虑下面的图：</p>

<ul>
	<li>有&nbsp;<code>nums.length</code>&nbsp;个节点，按从&nbsp;<code>nums[0]</code>&nbsp;到&nbsp;<code>nums[nums.length - 1]</code>&nbsp;标记；</li>
	<li>只有当&nbsp;<code>nums[i]</code>&nbsp;和&nbsp;<code>nums[j]</code>&nbsp;共用一个大于 1 的公因数时，<code>nums[i]</code>&nbsp;和&nbsp;<code>nums[j]</code>之间才有一条边。</li>
</ul>

<p>返回 <em>图中最大连通组件的大小</em> 。</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0952.Largest%20Component%20Size%20by%20Common%20Factor/images/ex1.png" style="height: 97px; width: 500px;" /></p>

<pre>
<strong>输入：</strong>nums = [4,6,15,35]
<strong>输出：</strong>4
</pre>

<p><strong>示例 2：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0952.Largest%20Component%20Size%20by%20Common%20Factor/images/ex2.png" style="height: 85px; width: 500px;" /></p>

<pre>
<strong>输入：</strong>nums = [20,50,9,63]
<strong>输出：</strong>2
</pre>

<p><strong>示例 3：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0952.Largest%20Component%20Size%20by%20Common%20Factor/images/ex3.png" style="height: 260px; width: 500px;" /></p>

<pre>
<strong>输入：</strong>nums = [2,3,6,7,4,12,21,39]
<strong>输出：</strong>8
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>nums</code>&nbsp;中所有值都 <strong>不同</strong></li>
</ul>

## 解法

**方法一：数学 + 并查集**

利用“试除法”，对 $nums$ 中的每个数 $v$ 分解因数，然后将每个因数 $i$ 与 $v$ 合并，$v / i$ 与 $v$ 合并。此过程用并查集来维护连通分量。

最后，遍历 $nums$ 中每个数 $v$，找出所在的连通分量，出现次数最多的连通分量就是所求的答案。

### **Java**

```java
class UnionFind {
    int[] p;

    UnionFind(int n) {
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
    }

    void union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa != pb) {
            p[pa] = pb;
        }
    }

    int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}

class Solution {
    public int largestComponentSize(int[] nums) {
        int m = 0;
        for (int v : nums) {
            m = Math.max(m, v);
        }
        UnionFind uf = new UnionFind(m + 1);
        for (int v : nums) {
            int i = 2;
            while (i <= v / i) {
                if (v % i == 0) {
                    uf.union(v, i);
                    uf.union(v, v / i);
                }
                ++i;
            }
        }
        int[] cnt = new int[m + 1];
        int ans = 0;
        for (int v : nums) {
            int t = uf.find(v);
            ++cnt[t];
            ans = Math.max(ans, cnt[t]);
        }
        return ans;
    }
}
```
