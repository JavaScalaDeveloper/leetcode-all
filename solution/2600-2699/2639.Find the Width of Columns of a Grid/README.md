# [2639. 查询网格图中每一列的宽度](https://leetcode.cn/problems/find-the-width-of-columns-of-a-grid)

## 题目描述

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的&nbsp;<code>m x n</code>&nbsp;整数矩阵&nbsp;<code>grid</code>&nbsp;。矩阵中某一列的宽度是这一列数字的最大 <strong>字符串长度</strong>&nbsp;。</p>

<ul>
	<li>比方说，如果&nbsp;<code>grid = [[-10], [3], [12]]</code>&nbsp;，那么唯一一列的宽度是&nbsp;<code>3</code>&nbsp;，因为&nbsp;<code>-10</code>&nbsp;的字符串长度为&nbsp;<code>3</code>&nbsp;。</li>
</ul>

<p>请你返回一个大小为 <code>n</code>&nbsp;的整数数组&nbsp;<code>ans</code>&nbsp;，其中&nbsp;<code>ans[i]</code>&nbsp;是第&nbsp;<code>i</code>&nbsp;列的宽度。</p>

<p>一个有 <code>len</code>&nbsp;个数位的整数 <code>x</code>&nbsp;，如果是非负数，那么&nbsp;<strong>字符串</strong><strong>长度</strong>&nbsp;为&nbsp;<code>len</code>&nbsp;，否则为&nbsp;<code>len + 1</code>&nbsp;。</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>grid = [[1],[22],[333]]
<b>输出：</b>[3]
<b>解释：</b>第 0 列中，333 字符串长度为 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>grid = [[-15,1,3],[15,7,12],[5,6,-2]]
<b>输出：</b>[3,1,2]
<b>解释：</b>
第 0 列中，只有 -15 字符串长度为 3 。
第 1 列中，所有整数的字符串长度都是 1 。
第 2 列中，12 和 -2 的字符串长度都为 2 。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 100 </code></li>
	<li><code>-10<sup>9</sup> &lt;= grid[r][c] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

**方法一：模拟**

我们记矩阵的列数为n，创建一个长度为n的数组ans，其中ans[i]表示第i列的宽度。初始时，ans[i]的值均为0。

遍历矩阵中的每一行，对于每一行中的每个元素，计算其字符串长度w，并更新ans[j]的值为max(ans[j], w)。

遍历完所有行后，数组ans中的每个元素即为对应列的宽度。

时间复杂度O(m × n)，空间复杂度O(log M)。其中m和n分别为矩阵的行数和列数，而M为矩阵中的最大元素绝对值。

### **Java**

```java
class Solution {
    public int[] findColumnWidth(int[][] grid) {
        int n = grid[0].length;
        int[] ans = new int[n];
        for (var row : grid) {
            for (int j = 0; j < n; ++j) {
                int w = String.valueOf(row[j]).length();
                ans[j] = Math.max(ans[j], w);
            }
        }
        return ans;
    }
}
```
