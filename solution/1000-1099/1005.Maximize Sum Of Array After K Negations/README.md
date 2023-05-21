# [1005. K 次取反后最大化的数组和](https://leetcode.cn/problems/maximize-sum-of-array-after-k-negations)

## 题目描述

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code> ，按以下方法修改该数组：</p>

<ul>
	<li>选择某个下标 <code>i</code>&nbsp;并将 <code>nums[i]</code> 替换为 <code>-nums[i]</code> 。</li>
</ul>

<p>重复这个过程恰好 <code>k</code> 次。可以多次选择同一个下标 <code>i</code> 。</p>

<p>以这种方式修改数组后，返回数组 <strong>可能的最大和</strong> 。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [4,2,3], k = 1
<strong>输出：</strong>5
<strong>解释：</strong>选择下标 1 ，nums 变为 [4,-2,3] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,-1,0,2], k = 3
<strong>输出：</strong>6
<strong>解释：</strong>选择下标 (1, 2, 2) ，nums 变为 [3,1,0,2] 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,-3,-1,5,-4], k = 2
<strong>输出：</strong>13
<strong>解释：</strong>选择下标 (1, 4) ，nums 变为 [2,3,-1,5,4] 。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-100 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

**方法一：贪心 + 计数**

我们观察发现，要使得数组的和尽可能大，就应该尽可能地将数组中的最小负数变成正数。

而题目中元素的范围为[-100,100]，因此，我们可以先用哈希表cnt统计数组nums中每个元素出现的次数。接着从-100开始遍历x，如果哈希表中存在x，那么我们取m = \min(cnt[x], k)作为元素x取反的个数，然后我们将cnt[x]减去m，将cnt[-x]加上m，并将k减去m。如果k为0，说明操作已经结束，直接退出循环即可。

如果k仍然为奇数，且cnt[0]=0，那么我们还需要取cnt中最小的一个正数x，将cnt[x]减去1，将cnt[-x]加上1。

最后，我们遍历哈希表cnt，将x与cnt[x]相乘的结果累加，即为答案。

时间复杂度O(n + M)，空间复杂度O(M)。其中n和M分别为数组nums的长度和nums的数据范围大小。

### **Java**

```java
class Solution {
    public int largestSumAfterKNegations(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum);
        }
        for (int x = -100; x < 0 && k > 0; ++x) {
            if (cnt.getOrDefault(x, 0) > 0) {
                int m = Math.min(cnt.get(x), k);
                cnt.merge(x, -m, Integer::sum);
                cnt.merge(-x, m, Integer::sum);
                k -= m;
            }
        }
        if ((k & 1) == 1 && cnt.getOrDefault(0, 0) == 0) {
            for (int x = 1; x <= 100; ++x) {
                if (cnt.getOrDefault(x, 0) > 0) {
                    cnt.merge(x, -1, Integer::sum);
                    cnt.merge(-x, 1, Integer::sum);
                    break;
                }
            }
        }
        int ans = 0;
        for (var e : cnt.entrySet()) {
            ans += e.getKey() * e.getValue();
        }
        return ans;
    }
}
```
