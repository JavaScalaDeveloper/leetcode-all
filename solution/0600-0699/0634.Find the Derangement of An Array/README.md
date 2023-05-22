# [634. 寻找数组的错位排列](https://leetcode.cn/problems/find-the-derangement-of-an-array)

## 题目描述

<p>在组合数学中，如果一个排列中所有元素都不在原先的位置上，那么这个排列就被称为 <strong>错位排列</strong> 。</p>

<p>给定一个从&nbsp;<code>1</code> 到 <code>n</code>&nbsp;升序排列的数组，返回&nbsp;<em><strong>不同的错位排列</strong> 的数量&nbsp;</em>。由于答案可能非常大，你只需要将答案对 <code>10<sup>9</sup>+7</code> <strong>取余</strong>&nbsp;输出即可。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> n = 3
<strong>输出:</strong> 2
<strong>解释:</strong> 原始的数组为 [1,2,3]。两个错位排列的数组为 [2,3,1] 和 [3,1,2]。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> n = 2
<strong>输出:</strong> 1
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

**方法一：动态规划**

我们定义f[i]表示长度为i的数组的错位排列的数量。初始时f[0] = 1,f[1] = 0。答案即为f[n]。

对于长度为i的数组，我们考虑将数字1放在哪个位置，假设放在第j个位置，这里有i-1种选择，那么接下来数字j可以有两种选择：

-   放在第1个位置，那么剩下的i - 2个位置可以有f[i - 2]种错位排列，因此总共有(i - 1) × f[i - 2]种错位排列；
-   不放在第1个位置，那么相当于转化为了长度为i - 1的数组的错位排列，因此总共有(i - 1) × f[i - 1]种错位排列。

综上，我们有如下状态转移方程：


f[i] = (i - 1) × (f[i - 1] + f[i - 2])


最终答案即为f[n]。注意答案的取模操作。

我们发现，状态转移方程中只与f[i - 1]和f[i - 2]有关，因此我们可以使用两个变量a和b来分别表示f[i - 1]和f[i - 2]，从而将空间复杂度降低到O(1)。

时间复杂度O(n)，空间复杂度O(1)。其中n为数组的长度。

### **Java**

```java
class Solution {
    public int findDerangement(int n) {
        long[] f = new long[n + 1];
        f[0] = 1;
        final int mod = (int) 1e9 + 7;
        for (int i = 2; i <= n; ++i) {
            f[i] = (i - 1) * (f[i - 1] + f[i - 2]) % mod;
        }
        return (int) f[n];
    }
}
```

```java
class Solution {
    public int findDerangement(int n) {
        final int mod = (int) 1e9 + 7;
        long a = 1, b = 0;
        for (int i = 2; i <= n; ++i) {
            long c = (i - 1) * (a + b) % mod;
            a = b;
            b = c;
        }
        return (int) b;
    }
}
```
