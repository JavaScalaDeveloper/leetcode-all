# [791. 自定义字符串排序](https://leetcode.cn/problems/custom-sort-string)

## 题目描述

<p>给定两个字符串 <code>order</code> 和 <code>s</code> 。<code>order</code> 的所有字母都是 <strong>唯一</strong> 的，并且以前按照一些自定义的顺序排序。</p>

<p>对 <code>s</code> 的字符进行置换，使其与排序的&nbsp;<code>order</code>&nbsp;相匹配。更具体地说，如果在&nbsp;<code>order</code>&nbsp;中的字符 <code>x</code> 出现字符 <code>y</code> 之前，那么在排列后的字符串中， <code>x</code>&nbsp;也应该出现在 <code>y</code> 之前。</p>

<p>返回 <em>满足这个性质的 <code>s</code> 的任意一种排列&nbsp;</em>。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> order = "cba", s = "abcd"
<strong>输出:</strong> "cbad"
<strong>解释:</strong> 
“a”、“b”、“c”是按顺序出现的，所以“a”、“b”、“c”的顺序应该是“c”、“b”、“a”。
因为“d”不是按顺序出现的，所以它可以在返回的字符串中的任何位置。“dcba”、“cdba”、“cbda”也是有效的输出。</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> order = "cbafg", s = "abcd"
<strong>输出:</strong> "cbad"
</pre>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= order.length &lt;= 26</code></li>
	<li><code>1 &lt;= s.length &lt;= 200</code></li>
	<li><code>order</code>&nbsp;和&nbsp;<code>s</code>&nbsp;由小写英文字母组成</li>
	<li><code>order</code>&nbsp;中的所有字符都 <strong>不同</strong></li>
</ul>

## 解法

**方法一：自定义排序**

一种比较直接的思路是，用哈希表或数组 $d$ 记录字符串 $order$ 中每个字符的位置，然后对字符串 $s$ 中每个字符按照其在 $d$ 中的位置进行排序。如果某个字符不在 $d$ 中，我们可以将其位置置为 $0$。

时间复杂度 $O(m + n\times \log n)$，空间复杂度 $O(m)$。其中 $m$ 和 $n$ 分别是字符串 $order$ 和 $s$ 的长度。

**方法二：字符计数**

我们还可以先统计 $s$ 中每个字符的出现次数，存储在 $cnt$ 数组中。

然后把字符串 $s$ 在 $order$ 中出现的字符按照 $order$ 中的顺序排序，添加到结果字符串中。最后把剩余的字符直接追加到结果字符串中。

时间复杂度 $O(m+n)$，空间复杂度 $O(m)$。其中 $m$ 和 $n$ 分别是字符串 $order$ 和 $s$ 的长度。

### **Java**

```java
class Solution {
    public String customSortString(String order, String s) {
        int[] d = new int[26];
        for (int i = 0; i < order.length(); ++i) {
            d[order.charAt(i) - 'a'] = i;
        }
        List<Character> cs = new ArrayList<>();
        for (int i = 0; i < s.length(); ++i) {
            cs.add(s.charAt(i));
        }
        cs.sort((a, b) -> d[a - 'a'] - d[b - 'a']);
        return cs.stream().map(String::valueOf).collect(Collectors.joining());
    }
}
```

```java
class Solution {
    public String customSortString(String order, String s) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < order.length(); ++i) {
            char c = order.charAt(i);
            while (cnt[c - 'a']-- > 0) {
                ans.append(c);
            }
        }
        for (int i = 0; i < 26; ++i) {
            while (cnt[i]-- > 0) {
                ans.append((char) ('a' + i));
            }
        }
        return ans.toString();
    }
}
```
