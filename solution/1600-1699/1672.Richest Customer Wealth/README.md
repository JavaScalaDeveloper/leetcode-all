# [1672. 最富有客户的资产总量](https://leetcode.cn/problems/richest-customer-wealth)

[English Version](/solution/1600-1699/1672.Richest%20Customer%20Wealth/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <code>m x n</code> 的整数网格 <code>accounts</code> ，其中 <code>accounts[i][j]</code> 是第 <code>i​​​​​<sup>​​​​​​</sup>​</code> 位客户在第 <code>j</code> 家银行托管的资产数量。返回最富有客户所拥有的 <strong>资产总量</strong> 。</p>

<p>客户的 <strong>资产总量</strong> 就是他们在各家银行托管的资产数量之和。最富有客户就是 <strong>资产总量</strong> 最大的客户。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>accounts = [[1,2,3],[3,2,1]]
<strong>输出：</strong>6
<strong>解释：</strong>
<code>第 1 位客户的资产总量 = 1 + 2 + 3 = 6
第 2 位客户的资产总量 = 3 + 2 + 1 = 6
</code>两位客户都是最富有的，资产总量都是 6 ，所以返回 6 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>accounts = [[1,5],[7,3],[3,5]]
<strong>输出：</strong>10
<strong>解释：</strong>
<code>第 1 位客户的资产总量</code> = 6
<code>第 2 位客户的资产总量</code> = 10 
<code>第 3 位客户的资产总量</code> = 8
第 2 位客户是最富有的，资产总量是 10</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>accounts = [[2,8,7],[7,1,3],[1,9,5]]
<strong>输出：</strong>17
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == accounts.length</code></li>
	<li><code>n == accounts[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 50</code></li>
	<li><code>1 &lt;= accounts[i][j] &lt;= 100</code></li>
</ul>

## 解法

**方法一：求和**

遍历 `accounts`，求出每一行的和，然后求出最大值。

时间复杂度 $O(m\times n)$。

### **Java**

```java
class Solution {
    public int maximumWealth(int[][] accounts) {
        int ans = 0;
        for (var e : accounts) {
            // int s = Arrays.stream(e).sum();
            int s = 0;
            for (int v : e) {
                s += v;
            }
            ans = Math.max(ans, s);
        }
        return ans;
    }
}
```

### **TypeScript**

### **C**

```c
#define max(a, b) (((a) > (b)) ? (a) : (b))

int maximumWealth(int **accounts, int accountsSize, int *accountsColSize) {
    int ans = INT_MIN;
    for (int i = 0; i < accountsSize; i++) {
        int sum = 0;
        for (int j = 0; j < accountsColSize[i]; j++) {
            sum += accounts[i][j];
        }
        ans = max(ans, sum);
    }
    return ans;
}
```

### **Kotlin**

```kotlin
class Solution {
    fun maximumWealth(accounts: Array<IntArray>): Int {
        var max = 0
        for (account in accounts) {
            val sum = account.sum()
            if (sum > max) {
                max = sum
            }
        }
        return max
    }
}
```
