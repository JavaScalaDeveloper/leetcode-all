# [1759. 统计同质子字符串的数目](https://leetcode.cn/problems/count-number-of-homogenous-substrings)

## 题目描述

<p>给你一个字符串 <code>s</code> ，返回<em> </em><code>s</code><em> </em>中 <strong>同质子字符串</strong> 的数目。由于答案可能很大，只需返回对 <code>10<sup>9</sup> + 7</code> <strong>取余 </strong>后的结果。</p>

<p><strong>同质字符串</strong> 的定义为：如果一个字符串中的所有字符都相同，那么该字符串就是同质字符串。</p>

<p><strong>子字符串</strong> 是字符串中的一个连续字符序列。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "abbcccaa"
<strong>输出：</strong>13
<strong>解释：</strong>同质子字符串如下所列：
"a"   出现 3 次。
"aa"  出现 1 次。
"b"   出现 2 次。
"bb"  出现 1 次。
"c"   出现 3 次。
"cc"  出现 2 次。
"ccc" 出现 1 次。
3 + 1 + 2 + 1 + 3 + 2 + 1 = 13</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "xy"
<strong>输出：</strong>2
<strong>解释：</strong>同质子字符串是 "x" 和 "y" 。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "zzzzz"
<strong>输出：</strong>15
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 由小写字符串组成</li>
</ul>

## 解法

**方法一：双指针**

遍历字符串s，用指针i指向当前字符，指针j指向下一个不同的字符，那么[i,..j-1]区间内的字符都是相同的，假设cnt=j-i，那么该区间内的同构子字符串个数为\frac{(1 + cnt) \times cnt}{2}，将其累加到答案中即可。继续遍历，直到指针i到达字符串末尾。

遍历完字符串s后，返回答案即可。注意答案的取模操作。

时间复杂度O(n)，空间复杂度O(1)。其中n为字符串s的长度。

### **Java**

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int countHomogenous(String s) {
        int n = s.length();
        long ans = 0;
        for (int i = 0, j = 0; i < n; i = j) {
            j = i;
            while (j < n && s.charAt(j) == s.charAt(i)) {
                ++j;
            }
            int cnt = j - i;
            ans += (long) (1 + cnt) * cnt / 2;
            ans %= MOD;
        }
        return (int) ans;
    }
}
```

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int countHomogenous(String s) {
        int n = s.length();
        int ans = 1, cnt = 1;
        for (int i = 1; i < n; ++i) {
            cnt = s.charAt(i) == s.charAt(i - 1) ? cnt + 1 : 1;
            ans = (ans + cnt) % MOD;
        }
        return ans;
    }
}
```

**
