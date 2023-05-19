# [2028. 找出缺失的观测数据](https://leetcode.cn/problems/find-missing-observations)

[English Version](/solution/2000-2099/2028.Find%20Missing%20Observations/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>现有一份 <code>n + m</code>&nbsp;次投掷单个<strong> 六面</strong> 骰子的观测数据，骰子的每个面从 <code>1</code> 到 <code>6</code> 编号。观测数据中缺失了 <code>n</code> 份，你手上只拿到剩余&nbsp;<code>m</code> 次投掷的数据。幸好你有之前计算过的这 <code>n + m</code> 次投掷数据的 <strong>平均值</strong> 。</p>

<p>给你一个长度为 <code>m</code> 的整数数组 <code>rolls</code> ，其中&nbsp;<code>rolls[i]</code> 是第 <code>i</code> 次观测的值。同时给你两个整数 <code>mean</code> 和 <code>n</code> 。</p>

<p>返回一个长度为<em> </em><code>n</code><em> </em>的数组，包含所有缺失的观测数据，且满足这<em> </em><code>n + m</code><em> </em>次投掷的 <strong>平均值</strong> 是<em> </em><code>mean</code> 。如果存在多组符合要求的答案，只需要返回其中任意一组即可。如果不存在答案，返回一个空数组。</p>

<p><code>k</code>&nbsp;个数字的 <strong>平均值</strong> 为这些数字求和后再除以&nbsp;<code>k</code> 。</p>

<p>注意 <code>mean</code> 是一个整数，所以 <code>n + m</code> 次投掷的总和需要被&nbsp;<code>n + m</code>&nbsp;整除。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>rolls = [3,2,4,3], mean = 4, n = 2
<strong>输出：</strong>[6,6]
<strong>解释：</strong>所有 n + m 次投掷的平均值是 (3 + 2 + 4 + 3 + 6 + 6) / 6 = 4 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>rolls = [1,5,6], mean = 3, n = 4
<strong>输出：</strong>[2,3,2,2]
<strong>解释：</strong>所有 n + m 次投掷的平均值是 (1 + 5 + 6 + 2 + 3 + 2 + 2) / 7 = 3 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>rolls = [1,2,3,4], mean = 6, n = 4
<strong>输出：</strong>[]
<strong>解释：</strong>无论丢失的 4 次数据是什么，平均值都不可能是 6 。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>rolls = [1], mean = 3, n = 1
<strong>输出：</strong>[5]
<strong>解释：</strong>所有 n + m 次投掷的平均值是 (1 + 5) / 2 = 3 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == rolls.length</code></li>
	<li><code>1 &lt;= n, m &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= rolls[i], mean &lt;= 6</code></li>
</ul>

## 解法

**方法一：构造**

根据题目描述，所有数字之和为 $(n + m) \times mean$，已知的数字之和为 `sum(rolls)`，那么缺失的数字之和为 $s = (n + m) \times mean - sum(rolls)$。

如果 $s \gt n \times 6$ 或者 $s \lt n$，说明不存在满足条件的答案，返回空数组。

否则，我们可以将 $s$ 平均分配到 $n$ 个数字上，即每个数字的值为 $s / n$，其中 $s \bmod n$ 个数字的值再加上 $1$。

时间复杂度 $O(n + m)$，空间复杂度 $O(1)$。其中 $n$ 和 $m$ 分别为缺失的数字个数和已知的数字个数。

### **Java**

```java
class Solution {
    public int[] missingRolls(int[] rolls, int mean, int n) {
        int m = rolls.length;
        int s = (n + m) * mean;
        for (int v : rolls) {
            s -= v;
        }
        if (s > n * 6 || s < n) {
            return new int[0];
        }
        int[] ans = new int[n];
        Arrays.fill(ans, s / n);
        for (int i = 0; i < s % n; ++i) {
            ++ans[i];
        }
        return ans;
    }
}
```

### **TypeScript**
