# [2413. 最小偶倍数](https://leetcode.cn/problems/smallest-even-multiple)

## 题目描述

给你一个正整数 <code>n</code> ，返回 <code>2</code><em> </em>和<em> </em><code>n</code> 的最小公倍数（正整数）。

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 5
<strong>输出：</strong>10
<strong>解释：</strong>5 和 2 的最小公倍数是 10 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 6
<strong>输出：</strong>6
<strong>解释：</strong>6 和 2 的最小公倍数是 6 。注意数字会是它自身的倍数。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 150</code></li>
</ul>

## 解法

**方法一：数学**

如果n为偶数，那么2和n的最小公倍数就是n本身。否则，2和n的最小公倍数就是n\times 2。

时间复杂度O(1)。

### **Java**

```java
class Solution {
    public int smallestEvenMultiple(int n) {
        return n % 2 == 0 ? n : n * 2;
    }
}
```

**
