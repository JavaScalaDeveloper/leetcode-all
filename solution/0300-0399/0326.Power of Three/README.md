# [326. 3 的幂](https://leetcode.cn/problems/power-of-three)

## 题目描述

<p>给定一个整数，写一个函数来判断它是否是 3&nbsp;的幂次方。如果是，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>整数 <code>n</code> 是 3 的幂次方需满足：存在整数 <code>x</code> 使得 <code>n == 3<sup>x</sup></code></p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 27
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 0
<strong>输出：</strong>false
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 9
<strong>输出：</strong>true
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>n = 45
<strong>输出：</strong>false
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-2<sup>31</sup> &lt;= n &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<p><strong>进阶：</strong>你能不使用循环或者递归来完成本题吗？</p>

## 解法

**方法一：试除法**

如果n > 2，我们可以不断地将n除以3，如果不能整除，说明n不是3的幂，否则继续除以3，直到n小于等于2。如果n等于1，说明n是3的幂，否则不是3的幂。

时间复杂度O(log_3n)，空间复杂度O(1)。

**方法二：数学**

如果n是3的幂，那么n最大是3^{19} = 1162261467，因此我们只需要判断n是否是3^{19}的约数即可。

时间复杂度O(1)，空间复杂度O(1)。

### **Java**

```java
class Solution {
    public boolean isPowerOfThree(int n) {
        while (n > 2) {
            if (n % 3 != 0) {
                return false;
            }
            n /= 3;
        }
        return n == 1;
    }
}
```

```java
class Solution {
    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
}
```
