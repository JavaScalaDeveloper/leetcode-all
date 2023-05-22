# [1359. 有效的快递序列数目](https://leetcode.cn/problems/count-all-valid-pickup-and-delivery-options)

## 题目描述

<p>给你&nbsp;<code>n</code>&nbsp;笔订单，每笔订单都需要快递服务。</p>

<p>请你统计所有有效的 收件/配送 序列的数目，确保第 <code>i</code> 个物品的配送服务&nbsp;<code>delivery(i)</code> 总是在其收件服务&nbsp;<code>pickup(i)</code> 之后。</p>

<p>由于答案可能很大，请返回答案对 <code>10^9 + 7</code> 取余的结果。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 1
<strong>输出：</strong>1
<strong>解释：</strong>只有一种序列 (P1, D1)，物品 1 的配送服务（D1）在物品 1 的收件服务（P1）后。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 2
<strong>输出：</strong>6
<strong>解释：</strong>所有可能的序列包括：
(P1,P2,D1,D2)，(P1,P2,D2,D1)，(P1,D1,P2,D2)，(P2,P1,D1,D2)，(P2,P1,D2,D1) 和 (P2,D2,P1,D1)。
(P1,D2,P2,D1) 是一个无效的序列，因为物品 2 的收件服务（P2）不应在物品 2 的配送服务（D2）之后。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = 3
<strong>输出：</strong>90
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 500</code></li>
</ul>

## 解法

**方法一：动态规划**

我们定义f[i]表示i个订单的所有有效的收件/配送序列的数目。初始时f[1] = 1。

我们可以选择这i个订单中的任意一个作为最后一个配送的订单D_i，那么它的收件订单P_i可以在之前2 × i - 1的任意一个位置，剩下的i - 1个订单的配送/收件序列数目为f[i - 1]，所以f[i]可以表示为：


f[i] = i × (2 × i - 1) × f[i - 1]


最终的答案即为f[n]。

我们注意到f[i]的值只与f[i - 1]有关，所以可以用一个变量代替数组，降低空间复杂度。

时间复杂度O(n)，空间复杂度O(1)。其中n为订单数目。

### **Java**

```java
class Solution {
    public int countOrders(int n) {
        final int mod = (int) 1e9 + 7;
        long f = 1;
        for (int i = 2; i <= n; ++i) {
            f = f * i * (2 * i - 1) % mod;
        }
        return (int) f;
    }
}
```
