# [2539. 好子序列的个数](https://leetcode.cn/problems/count-the-number-of-good-subsequences)

## 题目描述

<p>如果字符串的某个 <strong>子序列</strong> 不为空，且其中每一个字符出现的频率都相同，就认为该子序列是一个好子序列。</p>

<p>给你一个字符串&nbsp;<code>s</code> ，请你统计并返回它的好子序列的个数。由于答案的值可能非常大，请返回对 <code>10<sup>9</sup> + 7</code> 取余的结果作为答案。</p>

<p>字符串的 <strong>子序列</strong> 是指，通过删除一些（也可以不删除）字符且不改变剩余字符相对位置所组成的新字符串。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "aabb"
<strong>输出：</strong>11
<strong>解释：</strong>s 的子序列的总数为 <code>2<sup>4 </sup>= 16 。其中，有 5 个子序列不是好子序列，分别是 </code>"<em><strong>aab</strong></em>b"，"a<em><strong>abb</strong></em>"，"<strong><em>a</em></strong>a<em><strong>bb</strong></em>"，"<em><strong>aa</strong></em>b<em><strong>b</strong></em>" 以及空字符串。因此，好子序列的个数为 16 <code>- 5 = 11</code> 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "leet"
<strong>输出：</strong>12
<strong>解释：</strong>s 的子序列的总数为 <code>2<sup>4 </sup>= 16 。</code>其中，<code>有 4 个子序列不是好子序列，分别是 </code>"<em><strong>lee</strong></em>t"，"l<em><strong>eet</strong></em>"，"<em><strong>leet</strong></em>" 以及空字符串。因此，好子序列的个数为 16 <code>- 4 = 12</code> 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "abcd"
<strong>输出：</strong>15
<strong>解释：</strong>s 所有非空子序列均为好子序列。因此，好子序列的个数为 16<code> - 1 = 15</code> 。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> 仅由小写英文字母组成</li>
</ul>

## 解法

**方法一：枚举 + 组合计数**

由于题目说的是子序列中字母出现的次数，因此，我们可以先用一个数组 `cnt` 统计字符串s中每个字母出现的次数，记最大的次数为mx。

接下来，我们在[1,2..mx]范围内枚举子序列中字母出现的次数i，然后枚举所有出现过的字母，如果该字母c的出现次数cnt[c]大于等于i，那么我们可以从这cnt[c]个相同字母中选择其中i个，也可以一个都不选，那么当前字母的可选方案数就是comb(cnt[c], i) + 1，将所有可选方案数相乘，可以得到当前次数的所有子序列次数，将次数减去1累加到答案中。

那么问题的关键在于如何快速求出comb(n, k)，我们可以用逆元来求解，具体实现见代码。

时间复杂度O(n × C)，空间复杂度O(n)。其中n为字符串s的长度，而C是字符集的大小，本题中C = 26。

### **Java**

```java
class Solution {
    private static final int N = 10001;
    private static final int MOD = (int) 1e9 + 7;
    private static final long[] F = new long[N];
    private static final long[] G = new long[N];

    static {
        F[0] = 1;
        G[0] = 1;
        for (int i = 1; i < N; ++i) {
            F[i] = F[i - 1] * i % MOD;
            G[i] = qmi(F[i], MOD - 2, MOD);
        }
    }

    public static long qmi(long a, long k, long p) {
        long res = 1;
        while (k != 0) {
            if ((k & 1) == 1) {
                res = res * a % p;
            }
            k >>= 1;
            a = a * a % p;
        }
        return res;
    }

    public static long comb(int n, int k) {
        return (F[n] * G[k] % MOD) * G[n - k] % MOD;
    }

    public int countGoodSubsequences(String s) {
        int[] cnt = new int[26];
        int mx = 1;
        for (int i = 0; i < s.length(); ++i) {
            mx = Math.max(mx, ++cnt[s.charAt(i) - 'a']);
        }
        long ans = 0;
        for (int i = 1; i <= mx; ++i) {
            long x = 1;
            for (int j = 0; j < 26; ++j) {
                if (cnt[j] >= i) {
                    x = x * (comb(cnt[j], i) + 1) % MOD;
                }
            }
            ans = (ans + x - 1) % MOD;
        }
        return (int) ans;
    }
}
```
