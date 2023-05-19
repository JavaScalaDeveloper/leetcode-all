# [60. 排列序列](https://leetcode.cn/problems/permutation-sequence)

[English Version](/solution/0000-0099/0060.Permutation%20Sequence/README_EN.md)

## 题目描述

<p>给出集合 <code>[1,2,3,...,n]</code>，其所有元素共有 <code>n!</code> 种排列。</p>

<p>按大小顺序列出所有排列情况，并一一标记，当 <code>n = 3</code> 时, 所有排列如下：</p>

<ol>
	<li><code>"123"</code></li>
	<li><code>"132"</code></li>
	<li><code>"213"</code></li>
	<li><code>"231"</code></li>
	<li><code>"312"</code></li>
	<li><code>"321"</code></li>
</ol>

<p>给定 <code>n</code> 和 <code>k</code>，返回第 <code>k</code> 个排列。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 3, k = 3
<strong>输出：</strong>"213"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 4, k = 9
<strong>输出：</strong>"2314"
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 3, k = 1
<strong>输出：</strong>"123"
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= n <= 9</code></li>
	<li><code>1 <= k <= n!</code></li>
</ul>

## 解法

**方法一：枚举**

我们知道，集合 $[1,2,..n]$ 一共有 $n!$ 种排列，如果我们确定首位，那剩余位能组成的排列数量为 $(n-1)!$。

因此，我们枚举每一位 $i$，如果此时 $k$ 大于当前位置确定后的排列数量，那么我们可以直接减去这个数量；否则，说明我们找到了当前位置的数。

对于每一位 $i$，其中 $0 \leq i \lt n$，剩余位能组成的排列数量为 $(n-i-1)!$，我们记为 $fact$。过程中已使用的数记录在 `vis` 中。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。

### **Java**

```java
class Solution {
    public String getPermutation(int n, int k) {
        StringBuilder ans = new StringBuilder();
        boolean[] vis = new boolean[n + 1];
        for (int i = 0; i < n; ++i) {
            int fact = 1;
            for (int j = 1; j < n - i; ++j) {
                fact *= j;
            }
            for (int j = 1; j <= n; ++j) {
                if (!vis[j]) {
                    if (k > fact) {
                        k -= fact;
                    } else {
                        ans.append(j);
                        vis[j] = true;
                        break;
                    }
                }
            }
        }
        return ans.toString();
    }
}
```
