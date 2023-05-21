# [2131. 连接两字母单词得到的最长回文串](https://leetcode.cn/problems/longest-palindrome-by-concatenating-two-letter-words)

## 题目描述

<p>给你一个字符串数组&nbsp;<code>words</code>&nbsp;。<code>words</code>&nbsp;中每个元素都是一个包含 <strong>两个</strong>&nbsp;小写英文字母的单词。</p>

<p>请你从 <code>words</code>&nbsp;中选择一些元素并按 <b>任意顺序</b>&nbsp;连接它们，并得到一个 <strong>尽可能长的回文串</strong>&nbsp;。每个元素 <strong>至多</strong>&nbsp;只能使用一次。</p>

<p>请你返回你能得到的最长回文串的 <strong>长度</strong>&nbsp;。如果没办法得到任何一个回文串，请你返回 <code>0</code>&nbsp;。</p>

<p><strong>回文串</strong>&nbsp;指的是从前往后和从后往前读一样的字符串。</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>words = ["lc","cl","gg"]
<b>输出：</b>6
<b>解释：</b>一个最长的回文串为 "lc" + "gg" + "cl" = "lcggcl" ，长度为 6 。
"clgglc" 是另一个可以得到的最长回文串。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>words = ["ab","ty","yt","lc","cl","ab"]
<b>输出：</b>8
<strong>解释：</strong>最长回文串是 "ty" + "lc" + "cl" + "yt" = "tylcclyt" ，长度为 8 。
"lcyttycl" 是另一个可以得到的最长回文串。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>words = ["cc","ll","xx"]
<b>输出：</b>2
<b>解释：</b>最长回文串是 "cc" ，长度为 2 。
"ll" 是另一个可以得到的最长回文串。"xx" 也是。</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 10<sup>5</sup></code></li>
	<li><code>words[i].length == 2</code></li>
	<li><code>words[i]</code>&nbsp;仅包含小写英文字母。</li>
</ul>

## 解法

**方法一：贪心 + 哈希表**

我们先用哈希表 `cnt` 统计每个单词出现的次数。

遍历 `cnt` 中的每个单词k以及其出现次数v：

如果k中两个字母相同，那么我们可以将\left \lfloor \frac{v}{2}  \right \rfloor × 2个k连接到回文串的前后，此时如果k还剩余一个，那么我们可以先记录到x中。

如果k中两个字母不同，那么我们要找到一个单词k'，使得k'中的两个字母与k相反，即k' = k[1] + k[0]。如果k'存在，那么我们可以将min(v, cnt[k'])个k连接到回文串的前后。

遍历结束后，如果x不为空，那么我们还可以将一个单词连接到回文串的中间。

时间复杂度O(n)，空间复杂度O(n)。其中n为 `words` 的长度。

### **Java**

```java
class Solution {
    public int longestPalindrome(String[] words) {
        Map<String, Integer> cnt = new HashMap<>();
        for (var w : words) {
            cnt.put(w, cnt.getOrDefault(w, 0) + 1);
        }
        int ans = 0, x = 0;
        for (var e : cnt.entrySet()) {
            var k = e.getKey();
            var rk = new StringBuilder(k).reverse().toString();
            int v = e.getValue();
            if (k.charAt(0) == k.charAt(1)) {
                x += v & 1;
                ans += v / 2 * 2 * 2;
            } else {
                ans += Math.min(v, cnt.getOrDefault(rk, 0)) * 2;
            }
        }
        ans += x > 0 ? 2 : 0;
        return ans;
    }
}
```

## **Go**
