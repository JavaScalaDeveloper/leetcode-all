# [205. 同构字符串](https://leetcode.cn/problems/isomorphic-strings)

[English Version](/solution/0200-0299/0205.Isomorphic%20Strings/README_EN.md)

## 题目描述

<p>给定两个字符串&nbsp;<code>s</code>&nbsp;和&nbsp;<code>t</code>&nbsp;，判断它们是否是同构的。</p>

<p>如果&nbsp;<code>s</code>&nbsp;中的字符可以按某种映射关系替换得到&nbsp;<code>t</code>&nbsp;，那么这两个字符串是同构的。</p>

<p>每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>s = <code>"egg", </code>t = <code>"add"</code>
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = <code>"foo", </code>t = <code>"bar"</code>
<strong>输出：</strong>false</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = <code>"paper", </code>t = <code>"title"</code>
<strong>输出：</strong>true</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<p><meta charset="UTF-8" /></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>t.length == s.length</code></li>
	<li><code>s</code>&nbsp;和&nbsp;<code>t</code>&nbsp;由任意有效的 ASCII 字符组成</li>
</ul>

## 解法

**方法一：哈希表或数组**

我们可以用两个哈希表或数组 $d_1$ 和 $d_2$ 记录 $s$ 和 $t$ 中字符的映射关系。

遍历 $s$ 和 $t$，如果 $d_1$ 和 $d_2$ 中对应的字符映射关系不同，则返回 `false`，否则更新 $d_1$ 和 $d_2$ 中对应的字符映射关系。遍历结束，说明 $s$ 和 $t$ 是同构的，返回 `true`。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 为字符串 $s$ 的长度；而 $C$ 为字符集大小，本题中 $C = 256$。

### **Java**

```java
class Solution {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> d1 = new HashMap<>();
        Map<Character, Character> d2 = new HashMap<>();
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char a = s.charAt(i), b = t.charAt(i);
            if (d1.containsKey(a) && d1.get(a) != b) {
                return false;
            }
            if (d2.containsKey(b) && d2.get(b) != a) {
                return false;
            }
            d1.put(a, b);
            d2.put(b, a);
        }
        return true;
    }
}
```

```java
class Solution {
    public boolean isIsomorphic(String s, String t) {
        int[] d1 = new int[256];
        int[] d2 = new int[256];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char a = s.charAt(i), b = t.charAt(i);
            if (d1[a] != d2[b]) {
                return false;
            }
            d1[a] = i + 1;
            d2[b] = i + 1;
        }
        return true;
    }
}
```
