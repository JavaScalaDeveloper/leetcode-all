# [1304. 和为零的 N 个不同整数](https://leetcode.cn/problems/find-n-unique-integers-sum-up-to-zero)

## 题目描述

<p>给你一个整数&nbsp;<code>n</code>，请你返回 <strong>任意&nbsp;</strong>一个由 <code>n</code>&nbsp;个 <strong>各不相同&nbsp;</strong>的整数组成的数组，并且这 <code>n</code> 个数相加和为 <code>0</code> 。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 5
<strong>输出：</strong>[-7,-1,1,3,4]
<strong>解释：</strong>这些数组也是正确的 [-5,-1,1,2,3]，[-3,-1,2,-2,4]。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 3
<strong>输出：</strong>[-1,0,1]
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = 1
<strong>输出：</strong>[0]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

## 解法

**方法一：构造**

我们可以从1开始，依次将正数和负数交替放入结果数组中，一共循环\frac{n}{2}次，如果n为奇数，则最后再将0放入结果数组中。

时间复杂度O(n)，其中n为给定的整数。忽略答案的空间消耗，空间复杂度O(1)。

**方法二：构造 + 数学**

我们也可以将1到n-1的所有整数放入结果数组中，最后再把前n-1个整数的和\frac{n(n-1)}{2}的相反数放入结果数组中。

时间复杂度O(n)，其中n为给定的整数。忽略答案的空间消耗，空间复杂度O(1)。

### **Java**

```java
class Solution {
    public int[] sumZero(int n) {
        int[] ans = new int[n];
        for (int i = 1, j = 0; i <= n / 2; ++i) {
            ans[j++] = i;
            ans[j++] = -i;
        }
        return ans;
    }
}
```

```java
class Solution {
    public int[] sumZero(int n) {
        int[] ans = new int[n];
        for (int i = 1; i < n; ++i) {
            ans[i] = i;
        }
        ans[0] = -(n * (n - 1) / 2);
        return ans;
    }
}
```
