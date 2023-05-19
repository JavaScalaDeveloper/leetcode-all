# [2606. 找到最大开销的子字符串](https://leetcode.cn/problems/find-the-substring-with-maximum-cost)

[English Version](/solution/2600-2699/2606.Find%20the%20Substring%20With%20Maximum%20Cost/README_EN.md)

## 题目描述

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;，一个字符&nbsp;<strong>互不相同</strong>&nbsp;的字符串&nbsp;<code>chars</code>&nbsp;和一个长度与 <code>chars</code>&nbsp;相同的整数数组&nbsp;<code>vals</code>&nbsp;。</p>

<p><strong>子字符串的开销</strong>&nbsp;是一个子字符串中所有字符对应价值之和。空字符串的开销是 <code>0</code>&nbsp;。</p>

<p><strong>字符的价值</strong>&nbsp;定义如下：</p>

<ul>
	<li>如果字符不在字符串&nbsp;<code>chars</code>&nbsp;中，那么它的价值是它在字母表中的位置（下标从 <strong>1</strong>&nbsp;开始）。
    <ul>
    	<li>比方说，<code>'a'</code>&nbsp;的价值为&nbsp;<code>1</code>&nbsp;，<code>'b'</code>&nbsp;的价值为&nbsp;<code>2</code>&nbsp;，以此类推，<code>'z'</code>&nbsp;的价值为&nbsp;<code>26</code>&nbsp;。</li>
    </ul>
    </li>
    <li>否则，如果这个字符在 <code>chars</code>&nbsp;中的位置为 <code>i</code>&nbsp;，那么它的价值就是&nbsp;<code>vals[i]</code>&nbsp;。</li>
</ul>

<p>请你返回字符串 <code>s</code>&nbsp;的所有子字符串中的最大开销。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>s = "adaa", chars = "d", vals = [-1000]
<b>输出：</b>2
<b>解释：</b>字符 "a" 和 "d" 的价值分别为 1 和 -1000 。
最大开销子字符串是 "aa" ，它的开销为 1 + 1 = 2 。
2 是最大开销。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>s = "abc", chars = "abc", vals = [-1,-1,-1]
<b>输出：</b>0
<b>解释：</b>字符 "a" ，"b" 和 "c" 的价值分别为 -1 ，-1 和 -1 。
最大开销子字符串是 "" ，它的开销为 0 。
0 是最大开销。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
	<li><code>1 &lt;= chars.length &lt;= 26</code></li>
	<li><code>chars</code>&nbsp;只包含小写英文字母，且 <strong>互不相同</strong>&nbsp;。</li>
	<li><code>vals.length == chars.length</code></li>
	<li><code>-1000 &lt;= vals[i] &lt;= 1000</code></li>
</ul>

## 解法

**方法一：前缀和 + 维护前缀和的最小值**

我们根据题目描述，遍历字符串 $s$ 的每个字符 $c$，求出其对应的价值 $v$，然后更新当前的前缀和 $tot=tot+v$，那么以 $c$ 结尾的最大开销子字符串的开销为 $tot$ 减去前缀和的最小值 $mi$，即 $tot-mi$，我们更新答案 $ans=max(ans,tot-mi)$，并维护前缀和的最小值 $mi=min(mi,tot)$。

遍历结束后返回答案 $ans$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 为字符串 $s$ 的长度；而 $C$ 为字符集的大小，本题中 $C=26$。

**方法二：转化为最大子数组和问题**

我们可以将每个字符 $c$ 的价值 $v$ 看作是一个整数，那么题目实际上转化为求最大子数组和问题。

我们用变量 $f$ 维护以当前字符 $c$ 结尾的最大开销子字符串的开销，每次遍历到一个字符 $c$，更新 $f=max(f, 0) + v$，然后更新答案 $ans=max(ans,f)$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 为字符串 $s$ 的长度；而 $C$ 为字符集的大小，本题中 $C=26$。

### **Java**

```java
class Solution {
    public int maximumCostSubstring(String s, String chars, int[] vals) {
        int[] d = new int[26];
        for (int i = 0; i < d.length; ++i) {
            d[i] = i + 1;
        }
        int m = chars.length();
        for (int i = 0; i < m; ++i) {
            d[chars.charAt(i) - 'a'] = vals[i];
        }
        int ans = 0, tot = 0, mi = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int v = d[s.charAt(i) - 'a'];
            tot += v;
            ans = Math.max(ans, tot - mi);
            mi = Math.min(mi, tot);
        }
        return ans;
    }
}
```

```java
class Solution {
    public int maximumCostSubstring(String s, String chars, int[] vals) {
        int[] d = new int[26];
        for (int i = 0; i < d.length; ++i) {
            d[i] = i + 1;
        }
        int m = chars.length();
        for (int i = 0; i < m; ++i) {
            d[chars.charAt(i) - 'a'] = vals[i];
        }
        int ans = 0, f = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int v = d[s.charAt(i) - 'a'];
            f = Math.max(f, 0) + v;
            ans = Math.max(ans, f);
        }
        return ans;
    }
}
```
