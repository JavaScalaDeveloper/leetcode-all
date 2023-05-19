# [718. 最长重复子数组](https://leetcode.cn/problems/maximum-length-of-repeated-subarray)

[English Version](/solution/0700-0799/0718.Maximum%20Length%20of%20Repeated%20Subarray/README_EN.md)

## 题目描述

<p>给两个整数数组&nbsp;<code>nums1</code>&nbsp;和&nbsp;<code>nums2</code>&nbsp;，返回 <em>两个数组中 <strong>公共的</strong> 、长度最长的子数组的长度&nbsp;</em>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
<strong>输出：</strong>3
<strong>解释：</strong>长度最长的公共子数组是 [3,2,1] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
<strong>输出：</strong>5
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 100</code></li>
</ul>

## 解法

**方法一：动态规划**

我们定义 $f[i][j]$ 表示以 $nums1[i - 1]$ 和 $nums2[j - 1]$ 结尾的最长公共子数组的长度，那么我们可以得到状态转移方程：

$$
f[i][j]=
\begin{cases}
0, & nums1[i - 1] \neq nums2[j - 1] \\
f[i - 1][j - 1] + 1, & nums1[i - 1] = nums2[j - 1]
\end{cases}
$$

最终的答案即为所有 $f[i][j]$ 中的最大值。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是数组 $nums1$ 和 $nums2$ 的长度。

### **Java**

```java
class Solution {
    public int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[][] f = new int[m + 1][n + 1];
        int ans = 0;
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                    ans = Math.max(ans, f[i][j]);
                }
            }
        }
        return ans;
    }
}
```
