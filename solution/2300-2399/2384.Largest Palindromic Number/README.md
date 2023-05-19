# [2384. 最大回文数字](https://leetcode.cn/problems/largest-palindromic-number)

[English Version](/solution/2300-2399/2384.Largest%20Palindromic%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个仅由数字（<code>0 - 9</code>）组成的字符串 <code>num</code> 。</p>

<p>请你找出能够使用 <code>num</code> 中数字形成的 <strong>最大回文</strong> 整数，并以字符串形式返回。该整数不含 <strong>前导零</strong> 。</p>

<p><strong>注意：</strong></p>

<ul>
	<li>你 <strong>无需</strong> 使用 <code>num</code> 中的所有数字，但你必须使用 <strong>至少</strong> 一个数字。</li>
	<li>数字可以重新排序。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>num = "444947137"
<strong>输出：</strong>"7449447"
<strong>解释：</strong>
从 "<em><strong>44494</strong></em><em><strong>7</strong></em>13<em><strong>7</strong></em>" 中选用数字 "4449477"，可以形成回文整数 "7449447" 。
可以证明 "7449447" 是能够形成的最大回文整数。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>num = "00009"
<strong>输出：</strong>"9"
<strong>解释：</strong>
可以证明 "9" 能够形成的最大回文整数。
注意返回的整数不应含前导零。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num.length &lt;= 10<sup>5</sup></code></li>
	<li><code>num</code> 由数字（<code>0 - 9</code>）组成</li>
</ul>

## 解法

**方法一：统计 + 贪心**

用 $cnt$ 数组记录每个数字出现的次数。

回文数字需要满足回文串中最多有一个数字出现了奇数次，因此我们先找最大的、出现了奇数次的数字 $v$（也可能不存在），作为回文数字的中间数字，对应的 $cnt$ 减 $1$。

接下来我们从回文数字中间，按数字从小到大的顺序，往左右两边扩散（也可以枚举回文串的右半部分，然后将右半部分反转，得到左半部分）。这样我们可以得到一个“可能”含有前导零的回文串。

我们去除回文串的前导零，若回文串为空，则返回“0”。否则返回该回文串。

时间复杂度 $O(n)$，其中 $n$ 为 $num$ 的长度。

### **Java**

```java
class Solution {
    public String largestPalindromic(String num) {
        int[] cnt = new int[10];
        for (char c : num.toCharArray()) {
            ++cnt[c - '0'];
        }
        String mid = "";
        for (int i = 9; i >= 0; --i) {
            if (cnt[i] % 2 == 1) {
                mid += i;
                --cnt[i];
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; ++i) {
            if (cnt[i] > 0) {
                cnt[i] >>= 1;
                sb.append(("" + i).repeat(cnt[i]));
            }
        }
        while (sb.length() > 0 && sb.charAt(sb.length() - 1) == '0') {
            sb.deleteCharAt(sb.length() - 1);
        }
        String t = sb.toString();
        String ans = sb.reverse().toString() + mid + t;
        return "".equals(ans) ? "0" : ans;
    }
}
```

### **TypeScript**

### **...**

```

```
