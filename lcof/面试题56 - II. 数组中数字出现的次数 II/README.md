# [面试题 56 - II. 数组中数字出现的次数 II](https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/)

## 题目描述

<p>在一个数组 <code>nums</code> 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。</p>



<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [3,4,3,3]
<strong>输出：</strong>4
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [9,1,7,9,7,9,7]
<strong>输出：</strong>1</pre>



<p><strong>限制：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10000</code></li>
	<li><code>1 &lt;= nums[i] &lt; 2^31</code></li>
</ul>



## 解法

**方法一：位运算**

我们用一个长度为 32 的数组 $cnt$ 来统计所有数字的每一位中 $1$ 的出现次数。如果某一位的 $1$ 的出现次数能被 $3$ 整除，那么那个只出现一次的数字二进制表示中对应的那一位也是 $0$；否则，那个只出现一次的数字二进制表示中对应的那一位是 $1$。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 是数组的长度；而 $C$ 是整数的位数，本题中 $C=32$。

### **Java**

```java
class Solution {
    public int singleNumber(int[] nums) {
        int[] cnt = new int[32];
        for (int x : nums) {
            for (int i = 0; i < 32; ++i) {
                cnt[i] += x & 1;
                x >>= 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; ++i) {
            if (cnt[i] % 3 == 1) {
                ans |= 1 << i;
            }
        }
        return ans;
    }
}
```
