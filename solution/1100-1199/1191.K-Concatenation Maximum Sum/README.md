# [1191. K 次串联后最大子数组之和](https://leetcode.cn/problems/k-concatenation-maximum-sum)

## 题目描述

<p>给定一个整数数组&nbsp;<code>arr</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;，通过重复&nbsp;<code>k</code>&nbsp;次来修改数组。</p>

<p>例如，如果&nbsp;<code>arr = [1, 2]</code>&nbsp;，<meta charset="UTF-8" />&nbsp;<code>k = 3</code>&nbsp;，那么修改后的数组将是 <code>[1, 2, 1, 2, 1, 2]</code> 。</p>

<p>返回修改后的数组中的最大的子数组之和。注意，子数组长度可以是 <code>0</code>，在这种情况下它的总和也是 <code>0</code>。</p>

<p>由于&nbsp;<strong>结果可能会很大</strong>，需要返回的<meta charset="UTF-8" />&nbsp;<code>10<sup>9</sup>&nbsp;+ 7</code>&nbsp;的&nbsp;<strong>模</strong>&nbsp;。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,2], k = 3
<strong>输出：</strong>9
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,-2,1], k = 5
<strong>输出：</strong>2
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>arr = [-1,-2], k = 7
<strong>输出：</strong>0
</pre>

<p><strong>提示：</strong></p>
<meta charset="UTF-8" />

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup>&nbsp;&lt;= arr[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

**方法一：前缀和 + 分类讨论**

我们记数组arr所有元素之和为s，最大前缀和为mxPre，最小前缀和为miPre，最大子数组和为mxSub。

遍历数组arr，对于每个元素x，我们更新s = s + x,mxPre = max(mxPre, s),miPre = min(miPre, s),mxSub = max(mxSub, s - miPre)。

接下来，我们考虑k的取值情况：

-   当k = 1时，答案为mxSub。
-   当k \ge 2时，如果最大子数组横跨两个arr，那么答案为mxPre + mxSuf，其中mxSuf = s - miPre。
-   当k \ge 2且s > 0时，如果最大子数组横跨三个arr，那么答案为(k - 2) \times s + mxPre + mxSuf。

最后，我们返回答案对10^9 + 7取模的结果。

时间复杂度O(n)，空间复杂度O(1)。其中n为数组arr的长度。

### **Java**

```java
class Solution {
    public int kConcatenationMaxSum(int[] arr, int k) {
        long s = 0, mxPre = 0, miPre = 0, mxSub = 0;
        for (int x : arr) {
            s += x;
            mxPre = Math.max(mxPre, s);
            miPre = Math.min(miPre, s);
            mxSub = Math.max(mxSub, s - miPre);
        }
        long ans = mxSub;
        final int mod = (int) 1e9 + 7;
        if (k == 1) {
            return (int) (ans % mod);
        }
        long mxSuf = s - miPre;
        ans = Math.max(ans, mxPre + mxSuf);
        if (s > 0) {
            ans = Math.max(ans, (k - 2) * s + mxPre + mxSuf);
        }
        return (int) (ans % mod);
    }
}
```
