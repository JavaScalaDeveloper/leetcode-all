# [1562. 查找大小为 M 的最新分组](https://leetcode.cn/problems/find-latest-group-of-size-m)

[English Version](/solution/1500-1599/1562.Find%20Latest%20Group%20of%20Size%20M/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个数组 <code>arr</code> ，该数组表示一个从 <code>1</code> 到 <code>n</code> 的数字排列。有一个长度为 <code>n</code> 的二进制字符串，该字符串上的所有位最初都设置为 <code>0</code> 。</p>

<p>在从 <code>1</code> 到 <code>n</code> 的每个步骤 <code>i</code> 中（假设二进制字符串和 <code>arr</code> 都是从 <code>1</code> 开始索引的情况下），二进制字符串上位于位置 <code>arr[i]</code> 的位将会设为 <code>1</code> 。</p>

<p>给你一个整数 <code>m</code> ，请你找出二进制字符串上存在长度为 <code>m</code> 的一组 <code>1</code> 的最后步骤。一组 <code>1</code> 是一个连续的、由 <code>1</code> 组成的子串，且左右两边不再有可以延伸的 <code>1</code> 。</p>

<p>返回存在长度 <strong>恰好</strong> 为 <code>m</code> 的 <strong>一组 <code>1</code>&nbsp;</strong> 的最后步骤。如果不存在这样的步骤，请返回 <code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [3,5,1,2,4], m = 1
<strong>输出：</strong>4
<strong>解释：
</strong>步骤 1：&quot;00<strong>1</strong>00&quot;，由 1 构成的组：[&quot;1&quot;]
步骤 2：&quot;0010<strong>1</strong>&quot;，由 1 构成的组：[&quot;1&quot;, &quot;1&quot;]
步骤 3：&quot;<strong>1</strong>0101&quot;，由 1 构成的组：[&quot;1&quot;, &quot;1&quot;, &quot;1&quot;]
步骤 4：&quot;1<strong>1</strong>101&quot;，由 1 构成的组：[&quot;111&quot;, &quot;1&quot;]
步骤 5：&quot;111<strong>1</strong>1&quot;，由 1 构成的组：[&quot;11111&quot;]
存在长度为 1 的一组 1 的最后步骤是步骤 4 。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr = [3,1,5,4,2], m = 2
<strong>输出：</strong>-1
<strong>解释：
</strong>步骤 1：&quot;00<strong>1</strong>00&quot;，由 1 构成的组：[&quot;1&quot;]
步骤 2：&quot;<strong>1</strong>0100&quot;，由 1 构成的组：[&quot;1&quot;, &quot;1&quot;]
步骤 3：&quot;1010<strong>1</strong>&quot;，由 1 构成的组：[&quot;1&quot;, &quot;1&quot;, &quot;1&quot;]
步骤 4：&quot;101<strong>1</strong>1&quot;，由 1 构成的组：[&quot;1&quot;, &quot;111&quot;]
步骤 5：&quot;1<strong>1</strong>111&quot;，由 1 构成的组：[&quot;11111&quot;]
不管是哪一步骤都无法形成长度为 2 的一组 1 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>arr = [1], m = 1
<strong>输出：</strong>1
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>arr = [2,1], m = 2
<strong>输出：</strong>2
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == arr.length</code></li>
	<li><code>1 &lt;= n &lt;= 10^5</code></li>
	<li><code>1 &lt;= arr[i] &lt;= n</code></li>
	<li><code>arr</code> 中的所有整数 <strong>互不相同</strong></li>
	<li><code>1 &lt;= m&nbsp;&lt;= arr.length</code></li>
</ul>

## 解法

**方法一：并查集**

正向遍历 $arr$，利用并查集动态维护每组 $1$ 的长度。

时间复杂度 $O(nlogn)$。

相似题目：[2334. 元素值大于变化阈值的子数组](/solution/2300-2399/2334.Subarray%20With%20Elements%20Greater%20Than%20Varying%20Threshold/README.md)

**方法二：动态维护区间端点的长度**

我们其实并不需要去通过查找并查集来获取每个区间长度，我们只需要在每个区间端点处记录每个区间长度，由于合并的时候**只会访问区间端点**，所以合并区间的时候修改端点区间长度即可。

时间复杂度 $O(n)$。

### **Java**

```java
class Solution {
    private int[] p;
    private int[] size;

    public int findLatestStep(int[] arr, int m) {
        int n = arr.length;
        if (m == n) {
            return n;
        }
        boolean[] vis = new boolean[n];
        p = new int[n];
        size = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
            size[i] = 1;
        }
        int ans = -1;
        for (int i = 0; i < n; ++i) {
            int v = arr[i] - 1;
            if (v > 0 && vis[v - 1]) {
                if (size[find(v - 1)] == m) {
                    ans = i;
                }
                union(v, v - 1);
            }
            if (v < n - 1 && vis[v + 1]) {
                if (size[find(v + 1)] == m) {
                    ans = i;
                }
                union(v, v + 1);
            }
            vis[v] = true;
        }
        return ans;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    private void union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) {
            return;
        }
        p[pa] = pb;
        size[pb] += size[pa];
    }
}
```

```java
class Solution {
    public int findLatestStep(int[] arr, int m) {
        int n = arr.length;
        if (m == n) {
            return n;
        }
        int[] cnt = new int[n + 2];
        int ans = -1;
        for (int i = 0; i < n; ++i) {
            int v = arr[i];
            int l = cnt[v - 1], r = cnt[v + 1];
            if (l == m || r == m) {
                ans = i;
            }
            cnt[v - l] = l + r + 1;
            cnt[v + r] = l + r + 1;
        }
        return ans;
    }
}
```
