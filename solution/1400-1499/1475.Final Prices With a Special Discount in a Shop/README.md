# [1475. 商品折扣后的最终价格](https://leetcode.cn/problems/final-prices-with-a-special-discount-in-a-shop)

[English Version](/solution/1400-1499/1475.Final%20Prices%20With%20a%20Special%20Discount%20in%20a%20Shop/README_EN.md)

## 题目描述

<p>给你一个数组&nbsp;<code>prices</code>&nbsp;，其中&nbsp;<code>prices[i]</code>&nbsp;是商店里第&nbsp;<code>i</code>&nbsp;件商品的价格。</p>

<p>商店里正在进行促销活动，如果你要买第&nbsp;<code>i</code>&nbsp;件商品，那么你可以得到与 <code>prices[j]</code> 相等的折扣，其中&nbsp;<code>j</code>&nbsp;是满足&nbsp;<code>j &gt; i</code>&nbsp;且&nbsp;<code>prices[j] &lt;= prices[i]</code>&nbsp;的&nbsp;<strong>最小下标</strong>&nbsp;，如果没有满足条件的&nbsp;<code>j</code>&nbsp;，你将没有任何折扣。</p>

<p>请你返回一个数组，数组中第&nbsp;<code>i</code>&nbsp;个元素是折扣后你购买商品 <code>i</code>&nbsp;最终需要支付的价格。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>prices = [8,4,6,2,3]
<strong>输出：</strong>[4,2,4,2,3]
<strong>解释：</strong>
商品 0 的价格为 price[0]=8 ，你将得到 prices[1]=4 的折扣，所以最终价格为 8 - 4 = 4 。
商品 1 的价格为 price[1]=4 ，你将得到 prices[3]=2 的折扣，所以最终价格为 4 - 2 = 2 。
商品 2 的价格为 price[2]=6 ，你将得到 prices[3]=2 的折扣，所以最终价格为 6 - 2 = 4 。
商品 3 和 4 都没有折扣。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>prices = [1,2,3,4,5]
<strong>输出：</strong>[1,2,3,4,5]
<strong>解释：</strong>在这个例子中，所有商品都没有折扣。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>prices = [10,1,1,6]
<strong>输出：</strong>[9,0,1,6]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= prices.length &lt;= 500</code></li>
	<li><code>1 &lt;= prices[i] &lt;= 10^3</code></li>
</ul>

## 解法

**方法一：暴力枚举**

按题意模拟，采用双重循环枚举 `i` 和 `j`。

时间复杂度为 $O(n^2)$，忽略结果数组的空间消耗，空间复杂度 $O(1)$。

**方法二：单调栈**

单调栈常见模型：找出每个数左/右边**离它最近的**且**比它大/小的数**。模板：

本题我们可以采用正序、逆序两种方式遍历数组 `prices`。

时间复杂度 $O(n)$，其中 $n$ 表示数组 `prices` 的长度。

### **Java**

```java
class Solution {
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = prices[i];
            for (int j = i + 1; j < n; ++j) {
                if (prices[j] <= prices[i]) {
                    ans[i] -= prices[j];
                    break;
                }
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int[] finalPrices(int[] prices) {
        Deque<Integer> stk = new ArrayDeque<>();
        int n = prices.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = prices[i];
            while (!stk.isEmpty() && prices[stk.peek()] >= prices[i]) {
                ans[stk.pop()] -= prices[i];
            }
            stk.push(i);
        }
        return ans;
    }
}
```

```java
class Solution {
    public int[] finalPrices(int[] prices) {
        Deque<Integer> stk = new ArrayDeque<>();
        int n = prices.length;
        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            ans[i] = prices[i];
            while (!stk.isEmpty() && prices[stk.peek()] > prices[i]) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                ans[i] -= prices[stk.peek()];
            }
            stk.push(i);
        }
        return ans;
    }
}
```
