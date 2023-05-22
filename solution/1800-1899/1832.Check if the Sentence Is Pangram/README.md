# [1832. 判断句子是否为全字母句](https://leetcode.cn/problems/check-if-the-sentence-is-pangram)

## 题目描述

<p><strong>全字母句</strong> 指包含英语字母表中每个字母至少一次的句子。</p>

<p>给你一个仅由小写英文字母组成的字符串 <code>sentence</code> ，请你判断 <code>sentence</code> 是否为 <strong>全字母句</strong> 。</p>

<p>如果是，返回<em> </em><code>true</code> ；否则，返回<em> </em><code>false</code> 。</p>



<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>sentence = "thequickbrownfoxjumpsoverthelazydog"
<strong>输出：</strong>true
<strong>解释：</strong><code>sentence</code> 包含英语字母表中每个字母至少一次。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>sentence = "leetcode"
<strong>输出：</strong>false
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= sentence.length <= 1000</code></li>
	<li><code>sentence</code> 由小写英语字母组成</li>
</ul>

## 解法

**方法一：数组或哈希表**

遍历字符串 `sentence`，用数组或哈希表记录出现过的字母，最后判断数组或哈希表中是否有26个字母即可。

时间复杂度O(n)，空间复杂度O(C)。其中n为字符串 `sentence` 的长度，而C为字符集大小。本题中C = 26。

**方法二：位运算**

我们也可以用一个整数mask记录出现过的字母，其中mask的第i位表示第i个字母是否出现过。

最后判断mask的二进制表示中是否有26个1，也即判断mask是否等于2^{26} - 1。若是，返回 `true`，否则返回 `false`。

时间复杂度O(n)，空间复杂度O(1)。其中n为字符串 `sentence` 的长度。

### **Java**

```java
class Solution {
    public boolean checkIfPangram(String sentence) {
        boolean[] vis = new boolean[26];
        for (int i = 0; i < sentence.length(); ++i) {
            vis[sentence.charAt(i) - 'a'] = true;
        }
        for (boolean v : vis) {
            if (!v) {
                return false;
            }
        }
        return true;
    }
}
```

```java
class Solution {
    public boolean checkIfPangram(String sentence) {
        int mask = 0;
        for (int i = 0; i < sentence.length(); ++i) {
            mask |= 1 << (sentence.charAt(i) - 'a');
        }
        return mask == (1 << 26) - 1;
    }
}
```

**
