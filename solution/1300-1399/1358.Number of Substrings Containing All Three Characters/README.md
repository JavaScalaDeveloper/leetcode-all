# [1358. 包含所有三种字符的子字符串数目](https://leetcode.cn/problems/number-of-substrings-containing-all-three-characters)

## 题目描述

<p>给你一个字符串 <code>s</code>&nbsp;，它只包含三种字符 a, b 和 c 。</p>

<p>请你返回 a，b 和 c 都&nbsp;<strong>至少&nbsp;</strong>出现过一次的子字符串数目。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = &quot;abcabc&quot;
<strong>输出：</strong>10
<strong>解释：</strong>包含 a，b 和 c 各至少一次的子字符串为<em> &quot;</em>abc<em>&quot;, &quot;</em>abca<em>&quot;, &quot;</em>abcab<em>&quot;, &quot;</em>abcabc<em>&quot;, &quot;</em>bca<em>&quot;, &quot;</em>bcab<em>&quot;, &quot;</em>bcabc<em>&quot;, &quot;</em>cab<em>&quot;, &quot;</em>cabc<em>&quot; </em>和<em> &quot;</em>abc<em>&quot; </em>(<strong>相同</strong><strong>字符串算多次</strong>)<em>。</em>
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = &quot;aaacb&quot;
<strong>输出：</strong>3
<strong>解释：</strong>包含 a，b 和 c 各至少一次的子字符串为<em> &quot;</em>aaacb<em>&quot;, &quot;</em>aacb<em>&quot; </em>和<em> &quot;</em>acb<em>&quot; 。</em>
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s = &quot;abc&quot;
<strong>输出：</strong>1
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= s.length &lt;= 5 x 10^4</code></li>
	<li><code>s</code>&nbsp;只包含字符 a，b 和 c 。</li>
</ul>

## 解法

**方法一：一次遍历**

我们用一个长度为3的数组d记录三种字符最近一次出现的位置，初始时均为-1。

遍历字符串s，对于当前位置i，我们先更新d[s[i]=i，然后合法的字符串个数为min(d[0], d[1], d[2]) + 1，累加到答案中。

时间复杂度O(n)，空间复杂度O(1)。其中n为字符串s的长度。

### **Java**

```java
class Solution {
    public int numberOfSubstrings(String s) {
        int[] d = new int[] {-1, -1, -1};
        int ans = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            d[c - 'a'] = i;
            ans += Math.min(d[0], Math.min(d[1], d[2])) + 1;
        }
        return ans;
    }
}
```
