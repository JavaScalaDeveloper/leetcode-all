# [1655. 分配重复整数](https://leetcode.cn/problems/distribute-repeating-integers)

## 题目描述

<p>给你一个长度为&nbsp;<code>n</code>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;，这个数组中至多有&nbsp;<code>50</code>&nbsp;个不同的值。同时你有 <code>m</code>&nbsp;个顾客的订单 <code>quantity</code>&nbsp;，其中，整数&nbsp;<code>quantity[i]</code>&nbsp;是第&nbsp;<code>i</code>&nbsp;位顾客订单的数目。请你判断是否能将 <code>nums</code>&nbsp;中的整数分配给这些顾客，且满足：</p>

<ul>
	<li>第&nbsp;<code>i</code>&nbsp;位顾客 <strong>恰好&nbsp;</strong>有&nbsp;<code>quantity[i]</code>&nbsp;个整数。</li>
	<li>第&nbsp;<code>i</code>&nbsp;位顾客拿到的整数都是 <strong>相同的</strong>&nbsp;。</li>
	<li>每位顾客都满足上述两个要求。</li>
</ul>

<p>如果你可以分配 <code>nums</code>&nbsp;中的整数满足上面的要求，那么请返回&nbsp;<code>true</code>&nbsp;，否则返回 <code>false</code>&nbsp;。</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,3,4], quantity = [2]
<b>输出：</b>false
<strong>解释：</strong>第 0 位顾客没办法得到两个相同的整数。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,3,3], quantity = [2]
<b>输出：</b>true
<b>解释：</b>第 0 位顾客得到 [3,3] 。整数 [1,2] 都没有被使用。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [1,1,2,2], quantity = [2,2]
<b>输出：</b>true
<b>解释：</b>第 0 位顾客得到 [1,1] ，第 1 位顾客得到 [2,2] 。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>m == quantity.length</code></li>
	<li><code>1 &lt;= m &lt;= 10</code></li>
	<li><code>1 &lt;= quantity[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>nums</code>&nbsp;中至多有&nbsp;<code>50</code>&nbsp;个不同的数字。</li>
</ul>

## 解法

**方法一：状态压缩动态规划 + 子集枚举**

我们先统计数组nums中每个数字出现的次数，记录在哈希表cnt中，然后将哈希表中的值存入数组arr中，我们记数组arr的长度为n。

注意到数组quantity的长度不超过10，因此，我们可以用一个二进制数表示quantity中的一个子集，即数字j表示quantity中的一个子集，其中j的二进制表示中的第i位为1表示quantity中的第i个数字被选中，为0表示第i个数字未被选中。

我们可以预处理出数组s，其中s[j]表示quantity中子集j中所有数字的和。

接下来，我们定义f[i][j]表示数组arr[0,..i-1]中的数字能否成功分配给quantity中的子集j，其中i的取值范围为[0,..n-1]，而j的取值范围为[0,2^m-1]，其中m为quantity的长度。

考虑f[i][j]，如果子集j中存在一个子集k，使得s[k] ≤ arr[i]，并且f[i-1][j \oplus k]为真，那么f[i][j]为真，否则f[i][j]为假。

答案为f[n-1][2^m-1]。

时间复杂度O(n × 3^m)，空间复杂度O(n × 2^m)。其中n是数组nums中不同整数的个数；而m是数组quantity的长度。

### **Java**

```java
class Solution {
    public boolean canDistribute(int[] nums, int[] quantity) {
        int m = quantity.length;
        int[] s = new int[1 << m];
        for (int i = 1; i < 1 << m; ++i) {
            for (int j = 0; j < m; ++j) {
                if ((i >> j & 1) != 0) {
                    s[i] = s[i ^ (1 << j)] + quantity[j];
                    break;
                }
            }
        }
        Map<Integer, Integer> cnt = new HashMap<>(50);
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum);
        }
        int n = cnt.size();
        int[] arr = new int[n];
        int i = 0;
        for (int x : cnt.values()) {
            arr[i++] = x;
        }
        boolean[][] f = new boolean[n][1 << m];
        for (i = 0; i < n; ++i) {
            f[i][0] = true;
        }
        for (i = 0; i < n; ++i) {
            for (int j = 1; j < 1 << m; ++j) {
                if (i > 0 && f[i - 1][j]) {
                    f[i][j] = true;
                    continue;
                }
                for (int k = j; k > 0; k = (k - 1) & j) {
                    boolean ok1 = i == 0 ? j == k : f[i - 1][j ^ k];
                    boolean ok2 = s[k] <= arr[i];
                    if (ok1 && ok2) {
                        f[i][j] = true;
                        break;
                    }
                }
            }
        }
        return f[n - 1][(1 << m) - 1];
    }
}
```
