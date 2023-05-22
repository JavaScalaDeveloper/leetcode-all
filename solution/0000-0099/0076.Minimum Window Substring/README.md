# [76. 最小覆盖子串](https://leetcode.cn/problems/minimum-window-substring)

## 题目描述

<p>给你一个字符串 <code>s</code> 、一个字符串 <code>t</code> 。返回 <code>s</code> 中涵盖 <code>t</code> 所有字符的最小子串。如果 <code>s</code> 中不存在涵盖 <code>t</code> 所有字符的子串，则返回空字符串 <code>""</code> 。</p>

<p><strong>注意：</strong></p>

<ul>
	<li>对于 <code>t</code> 中重复字符，我们寻找的子字符串中该字符数量必须不少于 <code>t</code> 中该字符数量。</li>
	<li>如果 <code>s</code> 中存在这样的子串，我们保证它是唯一的答案。</li>
</ul>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "ADOBECODEBANC", t = "ABC"
<strong>输出：</strong>"BANC"
<strong>解释：</strong>最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "a", t = "a"
<strong>输出：</strong>"a"
<strong>解释：</strong>整个字符串 s 是最小覆盖子串。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> s = "a", t = "aa"
<strong>输出:</strong> ""
<strong>解释:</strong> t 中两个字符 'a' 均应包含在 s 的子串中，
因此没有符合条件的子字符串，返回空字符串。</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code><sup>m == s.length</sup></code></li>
	<li><code><sup>n == t.length</sup></code></li>
	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 和 <code>t</code> 由英文字母组成</li>
</ul>

<strong>进阶：</strong>你能设计一个在 <code>o(m+n)</code> 时间内解决此问题的算法吗？

## 解法

**方法一：计数 + 双指针**

我们用一个哈希表或数组need统计字符串t中每个字符出现的次数，用另一个哈希表或数组window统计滑动窗口中每个字符出现的次数。另外，定义两个指针j和i分别指向窗口的左右边界，变量cnt表示窗口中已经包含了t中的多少个字符，变量k和mi分别表示最小覆盖子串的起始位置和长度。

我们从左到右遍历字符串s，对于当前遍历到的字符s[i]：

我们将其加入窗口中，即window[s[i]] = window[s[i]] + 1，如果此时need[s[i]] ≥ window[s[i]]，则说明s[i]是一个「必要的字符」，我们将cnt加一。如果cnt等于t的长度，说明此时窗口中已经包含了t中的所有字符，我们就可以尝试更新最小覆盖子串的起始位置和长度了。如果i - j + 1 < mi，说明当前窗口表示的子串更短，我们就更新mi = i - j + 1和k = j。然后，我们尝试移动左边界j，如果此时need[s[j]] ≥ window[s[j]]，则说明s[j]是一个「必要的字符」，移动左边界时会把s[j]这个字符从窗口中移除，因此我们需要将cnt减一，然后更新window[s[j]] = window[s[j]] - 1，并将j右移一位。如果cnt与t的长度不相等，说明此时窗口中还没有包含t中的所有字符，我们就不需要移动左边界了，直接将i右移一位，继续遍历即可。

遍历结束，如果没有找到最小覆盖子串，返回空字符串，否则返回s[k:k+mi]即可。

时间复杂度O(m + n)，空间复杂度O(C)。其中m和n分别是字符串s和t的长度；而C是字符集的大小，本题中C = 128。

### **Java**

```java
class Solution {
    public String minWindow(String s, String t) {
        int[] need = new int[128];
        int[] window = new int[128];
        int m = s.length(), n = t.length();
        for (int i = 0; i < n; ++i) {
            ++need[t.charAt(i)];
        }
        int cnt = 0, j = 0, k = -1, mi = 1 << 30;
        for (int i = 0; i < m; ++i) {
            ++window[s.charAt(i)];
            if (need[s.charAt(i)] >= window[s.charAt(i)]) {
                ++cnt;
            }
            while (cnt == n) {
                if (i - j + 1 < mi) {
                    mi = i - j + 1;
                    k = j;
                }
                if (need[s.charAt(j)] >= window[s.charAt(j)]) {
                    --cnt;
                }
                --window[s.charAt(j++)];
            }
        }
        return k < 0 ? "" : s.substring(k, k + mi);
    }
}
```
