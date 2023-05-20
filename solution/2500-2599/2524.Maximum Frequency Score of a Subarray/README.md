# [2524. 子数组的最大频率分数](https://leetcode.cn/problems/maximum-frequency-score-of-a-subarray)

## 题目描述

<p>给定一个整数数组 <code>nums</code> 和一个 <strong>正</strong> 整数 <code>k</code> 。</p>

<p>数组的 <strong>频率得分</strong> 是数组中 <strong>不同</strong> 值的 <strong>幂次</strong> 之和，并将和对&nbsp;<code>10<sup>9</sup>&nbsp;+ 7</code> <strong>取模</strong>。</p>

<p>例如，数组 <code>[5,4,5,7,4,4]</code> 的频率得分为 <code>(4<sup>3</sup>&nbsp;+ 5<sup>2</sup>&nbsp;+ 7<sup>1</sup>) modulo (10<sup>9</sup>&nbsp;+ 7) = 96</code> 。</p>

<p>返回 <code>nums</code> 中长度为 <code>k</code> 的 <strong>子数组</strong> 的 <strong>最大&nbsp;</strong>频率得分。你需要返回取模后的最大值，而不是实际值。</p>

<p><strong>子数组</strong>&nbsp;是一个数组的连续部分。</p>

<p><strong class="example">示例 1 ：</strong></p>

<pre>
<b>输入：</b>nums = [1,1,1,2,1,2], k = 3
<b>输出：</b>5
<b>解释：</b>子数组 [2,1,2] 的频率分数等于 5。可以证明这是我们可以获得的最大频率分数。
</pre>

<p><strong class="example">示例 2 ：</strong></p>

<pre>
<b>输入：</b>nums = [1,1,1,1,1,1], k = 4
<b>输出：</b>1
<b>解释：</b>所有长度为 4 的子数组的频率得分都等于 1。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

**方法一：哈希表 + 滑动窗口 + 快速幂**

我们用哈希表 `cnt` 维护窗口大小为 $k$ 的元素及其出现的次数。

先算出初始窗口为 $k$ 的所有元素的分数。然后利用滑动窗口，每次加入一个元素，并移除最左边的元素，同时利用快速幂更新分数。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 `nums` 的长度。

### **Java**

```java
class Solution {
    public int maxFrequencyScore(int[] nums, int k) {
        final int mod = (int) 1e9 + 7;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i < k; ++i) {
            cnt.put(nums[i], cnt.getOrDefault(nums[i], 0) + 1);
        }
        long cur = 0;
        for (var e : cnt.entrySet()) {
            cur = (cur + qmi(e.getKey(), e.getValue(), mod)) % mod;
        }
        long ans = cur;
        for (int i = k; i < nums.length; ++i) {
            int a = nums[i - k];
            int b = nums[i];
            if (a != b) {
                if (cnt.getOrDefault(b, 0) > 0) {
                    cur += (b - 1) * qmi(b, cnt.get(b), mod) % mod;
                } else {
                    cur += b;
                }
                if (cnt.getOrDefault(a, 0) > 1) {
                    cur -= (a - 1) * qmi(a, cnt.get(a) - 1, mod) % mod;
                } else {
                    cur -= a;
                }
                cur = (cur + mod) % mod;
                cnt.put(b, cnt.getOrDefault(b, 0) + 1);
                cnt.put(a, cnt.getOrDefault(a, 0) - 1);
                ans = Math.max(ans, cur);
            }
        }
        return (int) ans;
    }

    long qmi(long a, long k, long p) {
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
}
```
