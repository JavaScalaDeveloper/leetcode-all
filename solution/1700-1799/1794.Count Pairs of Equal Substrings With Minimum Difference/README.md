# [1794. 统计距离最小的子串对个数](https://leetcode.cn/problems/count-pairs-of-equal-substrings-with-minimum-difference)

## 题目描述

<p>输入数据为两个字符串<code>firstString</code> 和 <code>secondString</code>，两个字符串下标均从0开始，且均只包含小写的英文字符，请计算满足下列要求的下标四元组<code>(i,j,a,b)</code>的个数：</p>

<ul>
	<li><code>0 <= i <= j < firstString.length</code></li>
	<li><code>0 <= a <= b < secondString.length</code></li>
	<li><code>firstString</code>字符串中从<code>i</code>位置到<code>j</code>位置的子串(包括<code>j</code>位置的字符)和<code>secondString</code>字符串从<code>a</code>位置到<code>b</code>位置的子串(包括<code>b</code>位置字符)相等</li>
	<li><code>j-a</code>的数值是所有符合前面三个条件的四元组中可能的最小值</li>
</ul>

<p>返回符合上述 4 个条件的四元组的 <strong>个数</strong> 。</p>



<p><strong>示例1：</strong></p>

<pre>
<strong>输入：</strong>firstString = "abcd", secondString = "bccda"
<strong>输出：</strong>1
<strong>解释：</strong>(0,0,4,4)是唯一符合条件的四元组且其<code>j-a</code>的数值是最小的.
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>firstString = "ab", secondString = "cd"
<strong>输出：</strong>0
<strong>解释：</strong>没有任何一个四元组能满足上述4个要求.
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= firstString.length, secondString.length <= 2 * 10<sup>5</sup></code></li>
	<li>两个输入字符串均只包含小写英文字符.</li>
</ul>

## 解法

**方法一：贪心 + 哈希表**

题目实际上要我们找到一个最小的下标i和一个最大的下标j，使得firstString[i]与secondString[j]相等，且i - j的值是所有满足条件的下标对中最小的。

因此，我们先用哈希表 `last` 记录secondString中每个字符最后一次出现的下标，然后遍历firstString，对于每个字符c，如果c在secondString中出现过，则计算i - last[c]，如果i - last[c]的值小于当前最小值，则更新最小值，同时更新答案为 1；如果i - last[c]的值等于当前最小值，则答案加 1。

时间复杂度O(m + n)，空间复杂度O(C)。其中m和n分别是firstString和secondString的长度，而C是字符集的大小。本题中C = 26。

### **Java**

```java
class Solution {
    public int countQuadruples(String firstString, String secondString) {
        int[] last = new int[26];
        for (int i = 0; i < secondString.length(); ++i) {
            last[secondString.charAt(i) - 'a'] = i + 1;
        }
        int ans = 0, mi = 1 << 30;
        for (int i = 0; i < firstString.length(); ++i) {
            int j = last[firstString.charAt(i) - 'a'];
            if (j > 0) {
                int t = i - j;
                if (mi > t) {
                    mi = t;
                    ans = 1;
                } else if (mi == t) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}
```
