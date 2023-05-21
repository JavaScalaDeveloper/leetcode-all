# [231. 2 的幂](https://leetcode.cn/problems/power-of-two)

## 题目描述

<p>给你一个整数 <code>n</code>，请你判断该整数是否是 2 的幂次方。如果是，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>如果存在一个整数 <code>x</code> 使得 <code>n == 2<sup>x</sup></code> ，则认为 <code>n</code> 是 2 的幂次方。</p>



<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>true
<strong>解释：</strong>2<sup>0</sup> = 1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 16
<strong>输出：</strong>true
<strong>解释：</strong>2<sup>4</sup> = 16
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 3
<strong>输出：</strong>false
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>n = 4
<strong>输出：</strong>true
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>n = 5
<strong>输出：</strong>false
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>-2<sup>31</sup> <= n <= 2<sup>31</sup> - 1</code></li>
</ul>



<p><strong>进阶：</strong>你能够不使用循环/递归解决此问题吗？</p>

## 解法

**方法一：位运算**

\texttt{n\&(n-1)}可将最后一个二进制形式的n的最后一位1移除，若移除后为0，说明n是2的幂。

**方法二：lowbit**

\texttt{n\&(-n)}可以得到n的最后一位1表示的十进制数，若与n相等，说明n是2的幂。

注意：要满足n是2的幂次方，需要保证n大于0。

lowbit:

### **Java**

```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
```

lowbit:

```java
class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && n == (n & (-n));
    }
}
```

lowbit:

lowbit:

lowbit:

lowbit:
