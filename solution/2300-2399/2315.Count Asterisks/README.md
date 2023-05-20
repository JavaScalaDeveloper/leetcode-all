# [2315. 统计星号](https://leetcode.cn/problems/count-asterisks)

## 题目描述

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;，每&nbsp;<strong>两个</strong>&nbsp;连续竖线&nbsp;<code>'|'</code>&nbsp;为 <strong>一对</strong>&nbsp;。换言之，第一个和第二个&nbsp;<code>'|'</code>&nbsp;为一对，第三个和第四个&nbsp;<code>'|'</code>&nbsp;为一对，以此类推。</p>

<p>请你返回 <strong>不在</strong> 竖线对之间，<code>s</code>&nbsp;中&nbsp;<code>'*'</code>&nbsp;的数目。</p>

<p><strong>注意</strong>，每个竖线&nbsp;<code>'|'</code>&nbsp;都会 <strong>恰好</strong>&nbsp;属于一个对。</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>s = "l|*e*et|c**o|*de|"
<b>输出：</b>2
<b>解释：</b>不在竖线对之间的字符加粗加斜体后，得到字符串："<strong><em>l</em></strong>|*e*et|<strong><em>c**o</em></strong>|*de|" 。
第一和第二条竖线 '|' 之间的字符不计入答案。
同时，第三条和第四条竖线 '|' 之间的字符也不计入答案。
不在竖线对之间总共有 2 个星号，所以我们返回 2 。</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>s = "iamprogrammer"
<b>输出：</b>0
<b>解释：</b>在这个例子中，s 中没有星号。所以返回 0 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>s = "yo|uar|e**|b|e***au|tifu|l"
<b>输出：</b>5
<b>解释：</b>需要考虑的字符加粗加斜体后："<strong><em>yo</em></strong>|uar|<strong><em>e**</em></strong>|b|<strong><em>e***au</em></strong>|tifu|<strong><em>l</em></strong>" 。不在竖线对之间总共有 5 个星号。所以我们返回 5 。</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母，竖线&nbsp;<code>'|'</code>&nbsp;和星号&nbsp;<code>'*'</code>&nbsp;。</li>
	<li><code>s</code>&nbsp;包含 <strong>偶数</strong>&nbsp;个竖线&nbsp;<code>'|'</code> 。</li>
</ul>

## 解法

**方法一：模拟**

我们定义一个整型变量 $ok$，表示遇到 `*` 时是否能计数，初始时 $ok=1$，表示可以计数。

遍历字符串 $s$，如果遇到 `*`，则根据 $ok$ 的值决定是否计数，如果遇到 `|`，则 $ok$ 的值取反。

最后返回计数的结果。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为字符串 $s$ 的长度。

### **Java**

```java
class Solution {
    public int countAsterisks(String s) {
        int ans = 0;
        for (int i = 0, ok = 1; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '*') {
                ans += ok;
            } else if (c == '|') {
                ok ^= 1;
            }
        }
        return ans;
    }
}
```

**
