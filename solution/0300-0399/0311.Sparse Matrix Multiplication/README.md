# [311. 稀疏矩阵的乘法](https://leetcode.cn/problems/sparse-matrix-multiplication)

## 题目描述

<p>给定两个&nbsp;<a href="https://baike.baidu.com/item/%E7%A8%80%E7%96%8F%E7%9F%A9%E9%98%B5" target="_blank">稀疏矩阵</a>&nbsp;：大小为 <code>m x k</code> 的稀疏矩阵 <code>mat1</code> 和大小为 <code>k x n</code> 的稀疏矩阵 <code>mat2</code> ，返回 <code>mat1 x mat2</code> 的结果。你可以假设乘法总是可能的。</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0311.Sparse%20Matrix%20Multiplication/images/mult-grid.jpg" style="height: 142px; width: 500px;" /></p>

<pre>
<strong>输入：</strong>mat1 = [[1,0,0],[-1,0,3]], mat2 = [[7,0,0],[0,0,0],[0,0,1]]
<strong>输出：</strong>[[7,0,0],[-7,0,3]]
</pre>

<p><strong>&nbsp;示例 2:</strong></p>

<pre>
<b>输入：</b>mat1 = [[0]], mat2 = [[0]]
<b>输出：</b>[[0]]
</pre>

<p><strong>提示:</strong></p>

<ul>
	<li><code>m == mat1.length</code></li>
	<li><code>k == mat1[i].length == mat2.length</code></li>
	<li><code>n == mat2[i].length</code></li>
	<li><code>1 &lt;= m, n, k &lt;= 100</code></li>
	<li><code>-100 &lt;= mat1[i][j], mat2[i][j] &lt;= 100</code></li>
</ul>

## 解法

直接模拟。

用哈希表记录稀疏矩阵 mat1 中的非 0 值。

### **Java**

```java
class Solution {
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        int r1 = mat1.length, c1 = mat1[0].length, c2 = mat2[0].length;
        int[][] res = new int[r1][c2];
        Map<Integer, List<Integer>> mp = new HashMap<>();
        for (int i = 0; i < r1; ++i) {
            for (int j = 0; j < c1; ++j) {
                if (mat1[i][j] != 0) {
                    mp.computeIfAbsent(i, k -> new ArrayList<>()).add(j);
                }
            }
        }
        for (int i = 0; i < r1; ++i) {
            for (int j = 0; j < c2; ++j) {
                if (mp.containsKey(i)) {
                    for (int k : mp.get(i)) {
                        res[i][j] += mat1[i][k] * mat2[k][j];
                    }
                }
            }
        }
        return res;
    }
}
```
