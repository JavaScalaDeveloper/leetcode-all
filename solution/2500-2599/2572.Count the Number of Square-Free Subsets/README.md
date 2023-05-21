# [2572. 无平方子集计数](https://leetcode.cn/problems/count-the-number-of-square-free-subsets)

## 题目描述

<p>给你一个正整数数组 <code>nums</code> 。</p>

<p>如果数组 <code>nums</code> 的子集中的元素乘积是一个 <strong>无平方因子数</strong> ，则认为该子集是一个 <strong>无平方</strong> 子集。</p>

<p><strong>无平方因子数</strong> 是无法被除 <code>1</code> 之外任何平方数整除的数字。</p>

<p>返回数组 <code>nums</code> 中 <strong>无平方</strong> 且 <strong>非空</strong> 的子集数目。因为答案可能很大，返回对 <code>10<sup>9</sup> + 7</code> 取余的结果。</p>

<p><code>nums</code> 的 <strong>非空子集</strong> 是可以由删除 <code>nums</code> 中一些元素（可以不删除，但不能全部删除）得到的一个数组。如果构成两个子集时选择删除的下标不同，则认为这两个子集不同。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,4,4,5]
<strong>输出：</strong>3
<strong>解释：</strong>示例中有 3 个无平方子集：
- 由第 0 个元素 [3] 组成的子集。其元素的乘积是 3 ，这是一个无平方因子数。
- 由第 3 个元素 [5] 组成的子集。其元素的乘积是 5 ，这是一个无平方因子数。
- 由第 0 个和第 3 个元素 [3,5] 组成的子集。其元素的乘积是 15 ，这是一个无平方因子数。
可以证明给定数组中不存在超过 3 个无平方子集。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1]
<strong>输出：</strong>1
<strong>解释：</strong>示例中有 1 个无平方子集：
- 由第 0 个元素 [1] 组成的子集。其元素的乘积是 1 ，这是一个无平方因子数。
可以证明给定数组中不存在超过 1 个无平方子集。</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length&nbsp;&lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 30</code></li>
</ul>

## 解法

**方法一：状态压缩动态规划**

注意到题目中nums[i]的范围为[1, 30]，因此我们可以预处理出所有小于等于30的质数，即[2, 3, 5, 7, 11, 13, 17, 19, 23, 29]。

无平方子集中，所有元素的乘积可以表示为一个或多个互不相同的质数的乘积，也即是说，每个质因数最多只能出现一次。因此，我们可以使用一个二进制数来表示一个子集中的质因数，其中二进制数的第i位表示质数primes[i]是否出现在子集中。

我们可以使用状态压缩动态规划的方法来求解本题。设f[i]表示二进制数i表示的子集中的质因数的乘积为一个或多个互不相同的质数的乘积的方案数。初始时f[0]=1。

我们在[2,..30]的范围内枚举一个数x，如果x不在nums中，或者x为4, 9, 25的倍数，那么我们可以直接跳过。否则，我们可以将x的质因数用一个二进制数mask表示，然后我们从大到小枚举当前的状态state，如果state与mask按位与的结果为mask，那么我们可以从状态f[state \oplus mask]转移到状态f[state]，转移方程为f[state] = f[state] + cnt[x] \times f[state \oplus mask]，其中cnt[x]表示x在nums中出现的次数。

注意，我们没有从数字1开始枚举，因为我们可以选择任意个数字1，加入到无平方子集中。也可以只选择任意个数字1，不加入到无平方子集中，这两种情况都是合法的。那么答案就是(\sum_{i=0}^{2^{10}-1} f[i]) - 1。

时间复杂度O(n + C \times M)，空间复杂度O(M)。其中n为nums的长度；而C和M分别为题目中nums[i]的范围和状态的个数，本题中C=30,M=2^{10}。

相似题目：

-   [1994. 好子集的数目](/solution/1900-1999/1994.The%20Number%20of%20Good%20Subsets/README.md)

### **Java**

```java
class Solution {
    public int squareFreeSubsets(int[] nums) {
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
        int[] cnt = new int[31];
        for (int x : nums) {
            ++cnt[x];
        }
        final int mod = (int) 1e9 + 7;
        int n = primes.length;
        long[] f = new long[1 << n];
        f[0] = 1;
        for (int i = 0; i < cnt[1]; ++i) {
            f[0] = (f[0] * 2) % mod;
        }
        for (int x = 2; x < 31; ++x) {
            if (cnt[x] == 0 || x % 4 == 0 || x % 9 == 0 || x % 25 == 0) {
                continue;
            }
            int mask = 0;
            for (int i = 0; i < n; ++i) {
                if (x % primes[i] == 0) {
                    mask |= 1 << i;
                }
            }
            for (int state = (1 << n) - 1; state > 0; --state) {
                if ((state & mask) == mask) {
                    f[state] = (f[state] + cnt[x] * f[state ^ mask]) % mod;
                }
            }
        }
        long ans = 0;
        for (int i = 0; i < 1 << n; ++i) {
            ans = (ans + f[i]) % mod;
        }
        ans -= 1;
        return (int) ans;
    }
}
```
