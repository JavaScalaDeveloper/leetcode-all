# [786. 第 K 个最小的素数分数](https://leetcode.cn/problems/k-th-smallest-prime-fraction)

[English Version](/solution/0700-0799/0786.K-th%20Smallest%20Prime%20Fraction/README_EN.md)

## 题目描述

<p>给你一个按递增顺序排序的数组 <code>arr</code> 和一个整数 <code>k</code> 。数组 <code>arr</code> 由 <code>1</code> 和若干 <strong>素数</strong>&nbsp; 组成，且其中所有整数互不相同。</p>

<p>对于每对满足 <code>0 &lt;= i &lt; j &lt; arr.length</code> 的 <code>i</code> 和 <code>j</code> ，可以得到分数 <code>arr[i] / arr[j]</code> 。</p>

<p>那么第&nbsp;<code>k</code>&nbsp;个最小的分数是多少呢?&nbsp; 以长度为 <code>2</code> 的整数数组返回你的答案, 这里&nbsp;<code>answer[0] == arr[i]</code>&nbsp;且&nbsp;<code>answer[1] == arr[j]</code> 。</p>
&nbsp;

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,2,3,5], k = 3
<strong>输出：</strong>[2,5]
<strong>解释：</strong>已构造好的分数,排序后如下所示: 
1/5, 1/3, 2/5, 1/2, 3/5, 2/3
很明显第三个最小的分数是 2/5
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,7], k = 1
<strong>输出：</strong>[1,7]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>arr[0] == 1</code></li>
	<li><code>arr[i]</code> 是一个 <strong>素数</strong> ，<code>i &gt; 0</code></li>
	<li><code>arr</code> 中的所有数字 <strong>互不相同</strong> ，且按 <strong>严格递增</strong> 排序</li>
	<li><code>1 &lt;= k &lt;= arr.length * (arr.length - 1) / 2</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你可以设计并实现时间复杂度小于 <code>O(n<sup>2</sup>)</code> 的算法解决此问题吗？</p>

## 解法

### **Java**

```java
class Solution {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        Queue<Frac> pq = new PriorityQueue<>();
        for (int i = 1; i < n; i++) {
            pq.offer(new Frac(1, arr[i], 0, i));
        }
        for (int i = 1; i < k; i++) {
            Frac f = pq.poll();
            if (f.i + 1 < f.j) {
                pq.offer(new Frac(arr[f.i + 1], arr[f.j], f.i + 1, f.j));
            }
        }
        Frac f = pq.peek();
        return new int[] {f.x, f.y};
    }

    static class Frac implements Comparable {
        int x, y, i, j;

        public Frac(int x, int y, int i, int j) {
            this.x = x;
            this.y = y;
            this.i = i;
            this.j = j;
        }

        @Override
        public int compareTo(Object o) {
            return x * ((Frac) o).y - ((Frac) o).x * y;
        }
    }
}
```
