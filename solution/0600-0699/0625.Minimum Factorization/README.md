# [625. 最小因式分解](https://leetcode.cn/problems/minimum-factorization)

## 题目描述

<p>给定一个正整数 <code>a</code>，找出最小的正整数 <code>b</code> 使得 <code>b</code> 的所有数位相乘恰好等于 <code>a</code>。</p>

<p>如果不存在这样的结果或者结果不是 32 位有符号整数，返回 0。</p>

<p><strong>样例 1</strong></p>

<p>输入：</p>

<pre>48 
</pre>

<p>输出：</p>

<pre>68</pre>

<p><strong>样例 2</strong></p>

<p>输入：</p>

<pre>15
</pre>

<p>输出：</p>

<pre>35</pre>

## 解法

**方法一：贪心 + 因式分解**

我们先判断num是否小于2，如果是，直接返回num。然后从9开始，尽可能多地将数字分解为9，然后分解为8，以此类推，直到分解为2。如果最后剩下的数字不是1，或者结果超过了2^{31} - 1，则返回0。否则，我们返回结果。

> 注意，分解后的数字，应该依次填充到结果的个位、十位、百位、千位……上，因此我们需要维护一个变量mul，表示当前的位数。

时间复杂度O(log n)，空间复杂度O(1)。其中n为num的值。

### **Java**

```java
class Solution {
    public int smallestFactorization(int num) {
        if (num < 2) {
            return num;
        }
        long ans = 0, mul = 1;
        for (int i = 9; i >= 2; --i) {
            if (num % i == 0) {
                while (num % i == 0) {
                    num /= i;
                    ans = mul * i + ans;
                    mul *= 10;
                }
            }
        }
        return num < 2 && ans <= Integer.MAX_VALUE ? (int) ans : 0;
    }
}
```
