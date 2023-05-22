# [1572. 矩阵对角线元素的和](https://leetcode.cn/problems/matrix-diagonal-sum)

## 题目描述

<p>给你一个正方形矩阵 <code>mat</code>，请你返回矩阵对角线元素的和。</p>

<p>请你返回在矩阵主对角线上的元素和副对角线上且不在主对角线上元素的和。</p>

<p><strong>示例&nbsp; 1：</strong></p>

<p><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1572.Matrix%20Diagonal%20Sum/images/sample_1911.png" style="height:174px; width:336px" /></p>

<pre>
<strong>输入：</strong>mat = [[<strong>1</strong>,2,<strong>3</strong>],
&nbsp;           [4,<strong>5</strong>,6],
&nbsp;           [<strong>7</strong>,8,<strong>9</strong>]]
<strong>输出：</strong>25
<strong>解释：</strong>对角线的和为：1 + 5 + 9 + 3 + 7 = 25
请注意，元素 mat[1][1] = 5 只会被计算一次。
</pre>

<p><strong>示例&nbsp; 2：</strong></p>

<pre>
<strong>输入：</strong>mat = [[<strong>1</strong>,1,1,<strong>1</strong>],
&nbsp;           [1,<strong>1</strong>,<strong>1</strong>,1],
&nbsp;           [1,<strong>1</strong>,<strong>1</strong>,1],
&nbsp;           [<strong>1</strong>,1,1,<strong>1</strong>]]
<strong>输出：</strong>8
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>mat = [[<strong>5</strong>]]
<strong>输出：</strong>5
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == mat.length == mat[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= mat[i][j] &lt;= 100</code></li>
</ul>

## 解法

**方法一：逐行遍历**

我们可以遍历矩阵的每一行row[i]，对于每一行，我们可以计算出两个对角线上的元素，即row[i][i]和row[i][n - i - 1]，其中n是矩阵的行数。如果i = n - i - 1，则说明当前行的对角线上只有一个元素，否则有两个元素。我们将其加到答案中即可。

遍历完所有行后，即可得到答案。

时间复杂度O(n)，空间复杂度O(1)。其中n是矩阵的行数。

### **Java**

```java
class Solution {
    public int diagonalSum(int[][] mat) {
        int ans = 0;
        int n = mat.length;
        for (int i = 0; i < n; ++i) {
            int j = n - i - 1;
            ans += mat[i][i] + (i == j ? 0 : mat[i][j]);
        }
        return ans;
    }
}
```

**
