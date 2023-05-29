# [242. 有效的字母异位词](https://leetcode.cn/problems/valid-anagram)

## 题目描述

<p>给定两个字符串 <code><em>s</em></code> 和 <code><em>t</em></code> ，编写一个函数来判断 <code><em>t</em></code> 是否是 <code><em>s</em></code> 的字母异位词。</p>

<p><strong>注意：</strong>若 <code><em>s</em></code> 和 <code><em>t</em></code><em> </em>中每个字符出现的次数都相同，则称 <code><em>s</em></code> 和 <code><em>t</em></code><em> </em>互为字母异位词。</p>



<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> <em>s</em> = "anagram", <em>t</em> = "nagaram"
<strong>输出:</strong> true
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> <em>s</em> = "rat", <em>t</em> = "car"
<strong>输出: </strong>false</pre>



<p><strong>提示:</strong></p>

<ul>
	<li><code>1 <= s.length, t.length <= 5 * 10<sup>4</sup></code></li>
	<li><code>s</code> 和 <code>t</code> 仅包含小写字母</li>
</ul>



<p><strong>进阶: </strong>如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？</p>

## 解法

**方法一：计数**

我们先判断两个字符串的长度是否相等，如果不相等，说明两个字符串中的字符肯定不同，返回 `false`。

否则，我们用哈希表或者一个长度为 $26$ 的数组来记录字符串 $s$ 中每个字符出现的次数，然后遍历另一个字符串 $t$，每遍历到一个字符，就将哈希表中对应的字符次数减一，如果减一后的次数小于 $0$，说明该字符在两个字符串中出现的次数不同，返回 `false`。如果遍历完两个字符串后，哈希表中的所有字符次数都为 $0$，说明两个字符串中的字符出现次数相同，返回 `true`。

时间复杂度 $O(n)$，空间复杂度 $O(C)$，其中 $n$ 是字符串的长度；而 $C$ 是字符集的大小，本题中 $C=26$。

### **Java**

```java
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++cnt[s.charAt(i) - 'a'];
            --cnt[t.charAt(i) - 'a'];
        }
        for (int i = 0; i < 26; ++i) {
            if (cnt[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
```
