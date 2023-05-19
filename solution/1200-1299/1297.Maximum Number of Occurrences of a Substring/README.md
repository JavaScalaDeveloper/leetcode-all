# [1297. 子串的最大出现次数](https://leetcode.cn/problems/maximum-number-of-occurrences-of-a-substring)

[English Version](/solution/1200-1299/1297.Maximum%20Number%20of%20Occurrences%20of%20a%20Substring/README_EN.md)

## 题目描述

<p>给你一个字符串&nbsp;<code>s</code> ，请你返回满足以下条件且出现次数最大的&nbsp;<strong>任意</strong>&nbsp;子串的出现次数：</p>

<ul>
	<li>子串中不同字母的数目必须小于等于 <code>maxLetters</code> 。</li>
	<li>子串的长度必须大于等于&nbsp;<code>minSize</code> 且小于等于&nbsp;<code>maxSize</code> 。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = &quot;aababcaab&quot;, maxLetters = 2, minSize = 3, maxSize = 4
<strong>输出：</strong>2
<strong>解释：</strong>子串 &quot;aab&quot; 在原字符串中出现了 2 次。
它满足所有的要求：2 个不同的字母，长度为 3 （在 minSize 和 maxSize 范围内）。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = &quot;aaaa&quot;, maxLetters = 1, minSize = 3, maxSize = 3
<strong>输出：</strong>2
<strong>解释：</strong>子串 &quot;aaa&quot; 在原字符串中出现了 2 次，且它们有重叠部分。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s = &quot;aabcabcab&quot;, maxLetters = 2, minSize = 2, maxSize = 3
<strong>输出：</strong>3
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>s = &quot;abcde&quot;, maxLetters = 2, minSize = 3, maxSize = 3
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10^5</code></li>
	<li><code>1 &lt;= maxLetters &lt;= 26</code></li>
	<li><code>1 &lt;= minSize &lt;= maxSize &lt;= min(26, s.length)</code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
</ul>

## 解法

**方法一：哈希表 + 枚举**

根据题目描述，如果一个长串满足条件，那么这个长串的子串（长度至少为 `minSize`）也一定满足条件。因此，我们只需要枚举 $s$ 中所有长度为 `minSize` 的子串，然后利用哈希表记录所有子串的出现次数，找出最大的次数作为答案即可。

时间复杂度 $O(n \times m)$，空间复杂度 $O(n \times m)$。其中 $n$ 和 $m$ 分别为字符串 $s$ 的长度以及 `minSize` 的大小。本题中 $m$ 不超过 $26$。

### **Java**

```java
class Solution {
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int ans = 0;
        Map<String, Integer> cnt = new HashMap<>();
        for (int i = 0; i < s.length() - minSize + 1; ++i) {
            String t = s.substring(i, i + minSize);
            Set<Character> ss = new HashSet<>();
            for (int j = 0; j < minSize; ++j) {
                ss.add(t.charAt(j));
            }
            if (ss.size() <= maxLetters) {
                cnt.put(t, cnt.getOrDefault(t, 0) + 1);
                ans = Math.max(ans, cnt.get(t));
            }
        }
        return ans;
    }
}
```
