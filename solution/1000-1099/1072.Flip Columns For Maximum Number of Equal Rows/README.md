# [1072. 按列翻转得到最大值等行数](https://leetcode.cn/problems/flip-columns-for-maximum-number-of-equal-rows)

## 题目描述

<p>给定&nbsp;<code>m x n</code>&nbsp;矩阵&nbsp;<code>matrix</code>&nbsp;。</p>

<p>你可以从中选出任意数量的列并翻转其上的&nbsp;<strong>每个&nbsp;</strong>单元格。（即翻转后，单元格的值从 <code>0</code> 变成 <code>1</code>，或者从 <code>1</code> 变为 <code>0</code> 。）</p>

<p>返回 <em>经过一些翻转后，行内所有值都相等的最大行数</em>&nbsp;。</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>matrix = [[0,1],[1,1]]
<strong>输出：</strong>1
<strong>解释：</strong>不进行翻转，有 1 行所有值都相等。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>matrix = [[0,1],[1,0]]
<strong>输出：</strong>2
<strong>解释：</strong>翻转第一列的值之后，这两行都由相等的值组成。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>matrix = [[0,0,0],[0,0,1],[1,1,0]]
<strong>输出：</strong>2
<strong>解释：</strong>翻转前两列的值之后，后两行由相等的值组成。</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 300</code></li>
	<li><code>matrix[i][j] == 0</code> 或&nbsp;<code>1</code></li>
</ul>

## 解法

**方法一：哈希表**

我们观察发现，如果矩阵中的两行满足以下条件之一，则它们可以通过翻转某些列的方式得到相等的行：

1. 两行的对应位置元素相等，即如果其中一行元素为1,0,0,1，则另一行元素也为1,0,0,1；
1. 两行的对应位置元素相反，即如果其中一行元素为1,0,0,1，则另一行元素为0,1,1,0。

我们称满足以上条件之一的两行元素为“等价行”，那么题目所求的答案即为矩阵中最多包含等价行的行数。

因此，我们可以遍历矩阵的每一行，将每一行转换成第一个元素为0的“等价行”。具体做法如下：

-   如果当前行的第一个元素为0，那么当前行的元素保持不变；
-   如果当前行的第一个元素为1，那么我们将当前行的每个元素进行翻转，即0变成1,1变成0。也就是说，我们将以1开头的行翻转成以0开头的“等价行”。

这样一来，我们只需要用一个哈希表来统计转换后的每一行的出现次数，其中键为转换后的行（可以将所有数字拼接成一个字符串），值为该行出现的次数。最后，哈希表中值的最大值即为矩阵中最多包含等价行的行数。

时间复杂度O(m × n)，空间复杂度O(m)。其中m和n分别是矩阵的行数和列数。

### **Java**

```java
class Solution {
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        Map<String, Integer> cnt = new HashMap<>();
        int ans = 0, n = matrix[0].length;
        for (var row : matrix) {
            char[] cs = new char[n];
            for (int i = 0; i < n; ++i) {
                cs[i] = (char) (row[0] ^ row[i]);
            }
            ans = Math.max(ans, cnt.merge(String.valueOf(cs), 1, Integer::sum));
        }
        return ans;
    }
}
```
