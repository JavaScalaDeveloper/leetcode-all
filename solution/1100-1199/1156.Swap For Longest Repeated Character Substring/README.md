# [1156. 单字符重复子串的最大长度](https://leetcode.cn/problems/swap-for-longest-repeated-character-substring)

## 题目描述

<p>如果字符串中的所有字符都相同，那么这个字符串是单字符重复的字符串。</p>

<p>给你一个字符串&nbsp;<code>text</code>，你只能交换其中两个字符一次或者什么都不做，然后得到一些单字符重复的子串。返回其中最长的子串的长度。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>text = &quot;ababa&quot;
<strong>输出：</strong>3
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>text = &quot;aaabaaa&quot;
<strong>输出：</strong>6
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>text = &quot;aaabbaaa&quot;
<strong>输出：</strong>4
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>text = &quot;aaaaa&quot;
<strong>输出：</strong>5
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>text = &quot;abcdef&quot;
<strong>输出：</strong>1
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= text.length &lt;= 20000</code></li>
	<li><code>text</code> 仅由小写英文字母组成。</li>
</ul>

## 解法

**方法一：双指针**

我们先统计每个字符出现的次数，记录在数组 `cnt` 中。

然后我们使用双指针i和j，初始时i = j = 0，然后我们不断地向右移动j，直到j指向的字符与i指向的字符不同，此时我们得到了一个长度为l = j - i的子串，其中所有字符都相同。然后我们跳过第j个字符，用指针k继续向右移动，直到k指向的字符与i指向的字符不同，此时我们得到了一个长度为r = k - j - 1的子串，其中所有字符都相同。此时我们可以得到的最长子串长度为min(l + r + 1, cnt[text[i]])，其中cnt[text[i]]表示字符text[i]出现的次数。我们将这个值与当前的最大值进行比较，取较大值作为答案。

时间复杂度为O(n)，空间复杂度O(C)。其中n为字符串的长度，而C为字符集的大小。本题中C = 26。

### **Java**

```java
class Solution {
    public int maxRepOpt1(String text) {
        int[] cnt = new int[26];
        int n = text.length();
        for (int i = 0; i < n; ++i) {
            ++cnt[text.charAt(i) - 'a'];
        }
        int ans = 0, i = 0;
        while (i < n) {
            int j = i;
            while (j < n && text.charAt(j) == text.charAt(i)) {
                ++j;
            }
            int l = j - i;
            int k = j + 1;
            while (k < n && text.charAt(k) == text.charAt(i)) {
                ++k;
            }
            int r = k - j - 1;
            ans = Math.max(ans, Math.min(l + r + 1, cnt[text.charAt(i) - 'a']));
            i = j;
        }
        return ans;
    }
}
```
