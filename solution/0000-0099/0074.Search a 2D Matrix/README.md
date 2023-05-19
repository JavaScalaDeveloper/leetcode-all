# [74. 搜索二维矩阵](https://leetcode.cn/problems/search-a-2d-matrix)

[English Version](/solution/0000-0099/0074.Search%20a%202D%20Matrix/README_EN.md)

## 题目描述

<p>编写一个高效的算法来判断 <code>m x n</code> 矩阵中，是否存在一个目标值。该矩阵具有如下特性：</p>

<ul>
	<li>每行中的整数从左到右按升序排列。</li>
	<li>每行的第一个整数大于前一行的最后一个整数。</li>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0074.Search%20a%202D%20Matrix/images/mat.jpg" style="width: 322px; height: 242px;" />
<pre>
<strong>输入：</strong>matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0074.Search%20a%202D%20Matrix/images/mat2.jpg" style="width: 322px; height: 242px;" />
<pre>
<strong>输入：</strong>matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
<strong>输出：</strong>false
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == matrix.length</code></li>
	<li><code>n == matrix[i].length</code></li>
	<li><code>1 <= m, n <= 100</code></li>
	<li><code>-10<sup>4</sup> <= matrix[i][j], target <= 10<sup>4</sup></code></li>
</ul>

## 解法

**方法一：二分查找**

将二维矩阵逻辑展开，然后二分查找即可。

时间复杂度 $O(log (m \times n))$。

**方法二：从左下角或右上角搜索**

这里我们以左下角作为起始搜索点，往右上方向开始搜索，比较当前元素 `matrix[i][j]` 与 `target` 的大小关系：

-   若 `matrix[i][j] == target`，说明找到了目标值，直接返回 true。
-   若 `matrix[i][j] > target`，说明这一行从当前位置开始往右的所有元素均大于 target，应该让 i 指针往上移动，即 `i--`。
-   若 `matrix[i][j] < target`，说明这一列从当前位置开始往上的所有元素均小于 target，应该让 j 指针往右移动，即 `j++`。

若搜索结束依然找不到 target，返回 false。

时间复杂度 $O(m + n)$。

二分查找：

从左下角或右上角搜索：

### **Java**

二分查找：

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int left = 0, right = m * n - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            int x = mid / n, y = mid % n;
            if (matrix[x][y] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return matrix[left / n][left % n] == target;
    }
}
```

从左下角或右上角搜索：

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
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

二分查找：

从左下角或右上角搜索：

二分查找：

从左下角或右上角搜索：

二分查找：

从左下角或右上角搜索：
