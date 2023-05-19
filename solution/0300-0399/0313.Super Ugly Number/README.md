# [313. 超级丑数](https://leetcode.cn/problems/super-ugly-number)

[English Version](/solution/0300-0399/0313.Super%20Ugly%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>超级丑数</strong> 是一个正整数，并满足其所有质因数都出现在质数数组 <code>primes</code> 中。</p>

<p>给你一个整数 <code>n</code> 和一个整数数组 <code>primes</code> ，返回第 <code>n</code> 个 <strong>超级丑数</strong> 。</p>

<p>题目数据保证第 <code>n</code> 个 <strong>超级丑数</strong> 在 <strong>32-bit</strong> 带符号整数范围内。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 12, <code>primes</code> = <code>[2,7,13,19]</code>
<strong>输出：</strong>32 
<strong>解释：</strong>给定长度为 4 的质数数组 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1, primes = [2,3,5]
<strong>输出：</strong>1
<strong>解释：</strong>1 不含质因数，因此它的所有质因数都在质数数组 primes = [2,3,5] 中。
</pre>

&nbsp;

<div class="top-view__1vxA">
<div class="original__bRMd">
<div>
<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= primes.length &lt;= 100</code></li>
	<li><code>2 &lt;= primes[i] &lt;= 1000</code></li>
	<li>题目数据<strong> 保证</strong> <code>primes[i]</code> 是一个质数</li>
	<li><code>primes</code> 中的所有值都 <strong>互不相同</strong> ，且按 <strong>递增顺序</strong> 排列</li>
</ul>
</div>
</div>
</div>

## 解法

**方法一：优先队列（小根堆）**

我们用一个优先队列（小根堆）维护所有可能的超级丑数，初始时将 $1$ 放入队列中。

每次从队列中取出最小的超级丑数 $x$，将 $x$ 乘以数组 `primes` 中的每个数，将乘积放入队列中，然后重复上述操作 $n$ 次即可得到第 $n$ 个超级丑数。

由于题目保证第 $n$ 个超级丑数在 $32$ 位带符号整数范围内，因此，我们将乘积放入队列之前，可以先判断乘积是否超过 $2^{31} - 1$，如果超过，则不需要将乘积放入队列中。另外，可以使用欧拉筛优化。

时间复杂度 $O(n \times m \times \log (n \times m))$，空间复杂度 $O(n \times m)$。其中 $m$ 和 $n$ 分别为数组 `primes` 的长度和给定的整数 $n$。

### **Java**

```java
class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        q.offer(1);
        int x = 0;
        while (n-- > 0) {
            x = q.poll();
            while (!q.isEmpty() && q.peek() == x) {
                q.poll();
            }
            for (int k : primes) {
                if (k <= Integer.MAX_VALUE / x) {
                    q.offer(k * x);
                }
                if (x % k == 0) {
                    break;
                }
            }
        }
        return x;
    }
}
```
