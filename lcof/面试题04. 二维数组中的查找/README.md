# [面试题 04. 二维数组中的查找](https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/)

## 题目描述

<p>在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。</p>

<p> </p>

<p><strong>示例:</strong></p>

<p>现有矩阵 matrix 如下：</p>

<pre>
[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
</pre>

<p>给定 target = <code>5</code>，返回 <code>true</code>。</p>

<p>给定 target = <code>20</code>，返回 <code>false</code>。</p>

<p> </p>

<p><strong>限制：</strong></p>

<p><code>0 <= n <= 1000</code></p>

<p><code>0 <= m <= 1000</code></p>

<p> </p>

<p><strong>注意：</strong>本题与主站 240 题相同：<a href="https://leetcode.cn/problems/search-a-2d-matrix-ii/">https://leetcode.cn/problems/search-a-2d-matrix-ii/</a></p>

## 解法

**方法一：二分查找**

由于每一行的所有元素升序排列，因此，对于每一行，我们可以使用二分查找找到第一个大于等于 `target` 的元素，然后判断该元素是否等于 `target`。如果等于 `target`，说明找到了目标值，直接返回 `true`。如果不等于 `target`，说明这一行的所有元素都小于 `target`，应该继续搜索下一行。

如果所有行都搜索完了，都没有找到目标值，说明目标值不存在，返回 `false`。

时间复杂度 $O(m \times \log n)$，空间复杂度 $O(1)$。其中 $m$ 和 $n$ 分别为矩阵的行数和列数。

**方法二：从左下角或右上角搜索**

这里我们以左下角作为起始搜索点，往右上方向开始搜索，比较当前元素 `matrix[i][j]`与 `target` 的大小关系：

-   若 `matrix[i][j] == target`，说明找到了目标值，直接返回 `true`。
-   若 `matrix[i][j] > target`，说明这一行从当前位置开始往右的所有元素均大于 `target`，应该让 $i$ 指针往上移动，即 $i \leftarrow i - 1$。
-   若 `matrix[i][j] < target`，说明这一列从当前位置开始往上的所有元素均小于 `target`，应该让 $j$ 指针往右移动，即 $j \leftarrow j + 1$。

若搜索结束依然找不到 `target`，返回 `false`。

时间复杂度 $O(m + n)$，空间复杂度 $O(1)$。其中 $m$ 和 $n$ 分别为矩阵的行数和列数。

### **Java**

```java
class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        for (var row : matrix) {
            int j = Arrays.binarySearch(row, target);
            if (j >= 0) {
                return true;
            }
        }
        return false;
    }
}
```

```java
class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length, n = matrix[0].length;
        for (int i = m - 1, j = 0; i >= 0 && j < n;) {
            if (matrix[i][j] == target) {
                return true;
            }
            if (matrix[i][j] > target) {
                --i;
            } else {
                ++j;
            }
        }
        return false;
    }
}
```

### **TypeScript**
