# [1035. 不相交的线](https://leetcode.cn/problems/uncrossed-lines)

## 题目描述

<p>在两条独立的水平线上按给定的顺序写下 <code>nums1</code> 和 <code>nums2</code> 中的整数。</p>

<p>现在，可以绘制一些连接两个数字 <code>nums1[i]</code>&nbsp;和 <code>nums2[j]</code>&nbsp;的直线，这些直线需要同时满足满足：</p>

<ul>
	<li>&nbsp;<code>nums1[i] == nums2[j]</code></li>
	<li>且绘制的直线不与任何其他连线（非水平线）相交。</li>
</ul>

<p>请注意，连线即使在端点也不能相交：每个数字只能属于一条连线。</p>

<p>以这种方法绘制线条，并返回可以绘制的最大连线数。</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1035.Uncrossed%20Lines/images/142.png" style="width: 400px; height: 286px;" />
<pre>
<strong>输入：</strong>nums1 = <span id="example-input-1-1">[1,4,2]</span>, nums2 = <span id="example-input-1-2">[1,2,4]</span>
<strong>输出：</strong><span id="example-output-1">2</span>
<strong>解释：</strong>可以画出两条不交叉的线，如上图所示。 
但无法画出第三条不相交的直线，因为从 nums1[1]=4 到 nums2[2]=4 的直线将与从 nums1[2]=2 到 nums2[1]=2 的直线相交。
</pre>

<div>
<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums1 = <span id="example-input-2-1">[2,5,1,2,5]</span>, nums2 = <span id="example-input-2-2">[10,5,2,1,5,2]</span>
<strong>输出：</strong><span id="example-output-2">3</span>
</pre>

<div>
<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums1 = <span id="example-input-3-1">[1,3,7,1,7,5]</span>, nums2 = <span id="example-input-3-2">[1,9,2,5,1]</span>
<strong>输出：</strong><span id="example-output-3">2</span></pre>

</div>
</div>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 500</code></li>
	<li><code>1 &lt;= nums1[i], nums2[j] &lt;= 2000</code></li>
</ul>

## 解法

**方法一：动态规划**

最长公共子序列问题。

定义 $dp[i][j]$ 表示数组 `nums1` 的前 $i$ 个元素和数组 `nums2` 的前 $j$ 个元素的最长公共子序列的长度。则有：

$$
dp[i][j]=
\begin{cases}
dp[i-1][j-1]+1, & nums1[i-1]=nums2[j-1] \\
\max(dp[i-1][j], dp[i][j-1]), & nums1[i-1]\neq nums2[j-1]
\end{cases}
$$

时间复杂度 $O(m\times n)$，空间复杂度 $O(m\times n)$。其中 $m$, $n$ 分别为数组 `nums1` 和 `nums2` 的长度。

### **Java**

```java
class Solution {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}
```
