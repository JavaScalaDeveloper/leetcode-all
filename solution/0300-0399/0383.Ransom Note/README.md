# [383. 赎金信](https://leetcode.cn/problems/ransom-note)

## 题目描述

<p>给你两个字符串：<code>ransomNote</code> 和 <code>magazine</code> ，判断 <code>ransomNote</code> 能不能由 <code>magazine</code> 里面的字符构成。</p>

<p>如果可以，返回 <code>true</code> ；否则返回 <code>false</code> 。</p>

<p><code>magazine</code> 中的每个字符只能在 <code>ransomNote</code> 中使用一次。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>ransomNote = "a", magazine = "b"
<strong>输出：</strong>false
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>ransomNote = "aa", magazine = "ab"
<strong>输出：</strong>false
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>ransomNote = "aa", magazine = "aab"
<strong>输出：</strong>true
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= ransomNote.length, magazine.length &lt;= 10<sup>5</sup></code></li>
	<li><code>ransomNote</code> 和 <code>magazine</code> 由小写英文字母组成</li>
</ul>

## 解法

**方法一：哈希表或数组**

我们可以用一个哈希表或长度为26的数组cnt记录字符串 `magazine` 中所有字符出现的次数。然后遍历字符串 `ransomNote`，对于其中的每个字符c，我们将其从cnt的次数减1，如果减1之后的次数小于0，说明c在 `magazine` 中出现的次数不够，因此无法构成 `ransomNote`，返回false即可。

否则，遍历结束后，说明 `ransomNote` 中的每个字符都可以在 `magazine` 中找到对应的字符，因此返回true。

时间复杂度O(m + n)，空间复杂度O(C)。其中m和n分别为字符串 `ransomNote` 和 `magazine` 的长度；而C为字符集的大小，本题中C = 26。

### **Java**

```java
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] cnt = new int[26];
        for (int i = 0; i < magazine.length(); ++i) {
            ++cnt[magazine.charAt(i) - 'a'];
        }
        for (int i = 0; i < ransomNote.length(); ++i) {
            if (--cnt[ransomNote.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
```
