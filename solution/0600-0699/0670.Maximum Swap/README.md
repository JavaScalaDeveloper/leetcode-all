# [670. 最大交换](https://leetcode.cn/problems/maximum-swap)

## 题目描述

<p>给定一个非负整数，你<strong>至多</strong>可以交换一次数字中的任意两位。返回你能得到的最大值。</p>

<p><strong>示例 1 :</strong></p>

<pre>
<strong>输入:</strong> 2736
<strong>输出:</strong> 7236
<strong>解释:</strong> 交换数字2和数字7。
</pre>

<p><strong>示例 2 :</strong></p>

<pre>
<strong>输入:</strong> 9973
<strong>输出:</strong> 9973
<strong>解释:</strong> 不需要交换。
</pre>

<p><strong>注意:</strong></p>

<ol>
	<li>给定数字的范围是&nbsp;[0, 10<sup>8</sup>]</li>
</ol>

## 解法

**方法一：贪心**

先将数字转为字符串 `s`，然后从右往左遍历字符串 `s`，用数组或哈希表 `d` 记录每个数字右侧的最大数字的位置（可以是字符本身的位置）。

接着从左到右遍历 `d`，如果 `s[i] < s[d[i]]`，则进行交换，并退出遍历的过程。

最后将字符串 `s` 转为数字，即为答案。

时间复杂度O(log C)，空间复杂度O(log C)。其中C是数字 `num` 的值域。

### **Java**

```java
class Solution {
    public int maximumSwap(int num) {
        char[] s = String.valueOf(num).toCharArray();
        int n = s.length;
        int[] d = new int[n];
        for (int i = 0; i < n; ++i) {
            d[i] = i;
        }
        for (int i = n - 2; i >= 0; --i) {
            if (s[i] <= s[d[i + 1]]) {
                d[i] = d[i + 1];
            }
        }
        for (int i = 0; i < n; ++i) {
            int j = d[i];
            if (s[i] < s[j]) {
                char t = s[i];
                s[i] = s[j];
                s[j] = t;
                break;
            }
        }
        return Integer.parseInt(String.valueOf(s));
    }
}
```
