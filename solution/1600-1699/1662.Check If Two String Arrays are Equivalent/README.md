# [1662. 检查两个字符串数组是否相等](https://leetcode.cn/problems/check-if-two-string-arrays-are-equivalent)

## 题目描述

<p>给你两个字符串数组 <code>word1</code> 和 <code>word2</code> 。如果两个数组表示的字符串相同，返回<em> </em><code>true</code><em> </em>；否则，返回 <code>false</code><em> 。</em></p>

<p><strong>数组表示的字符串</strong> 是由数组中的所有元素 <strong>按顺序</strong> 连接形成的字符串。</p>



<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>word1 = ["ab", "c"], word2 = ["a", "bc"]
<strong>输出：</strong>true
<strong>解释：</strong>
word1 表示的字符串为 "ab" + "c" -> "abc"
word2 表示的字符串为 "a" + "bc" -> "abc"
两个字符串相同，返回 true</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>word1 = ["a", "cb"], word2 = ["ab", "c"]
<strong>输出：</strong>false
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>word1  = ["abc", "d", "defg"], word2 = ["abcddefg"]
<strong>输出：</strong>true
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= word1.length, word2.length <= 10<sup>3</sup></code></li>
	<li><code>1 <= word1[i].length, word2[i].length <= 10<sup>3</sup></code></li>
	<li><code>1 <= sum(word1[i].length), sum(word2[i].length) <= 10<sup>3</sup></code></li>
	<li><code>word1[i]</code> 和 <code>word2[i]</code> 由小写字母组成</li>
</ul>

## 解法

**方法一：字符串拼接**

将两个数组中的字符串拼接成两个字符串，然后比较两个字符串是否相等。

时间复杂度O(m)，空间复杂度O(m)。其中m为数组中字符串的总长度。

**方法二：直接遍历**

方法一中，我们是将两个数组中的字符串拼接成两个新的字符串，有额外的空间开销。我们也可以直接遍历两个数组，逐个字符比较。

我们使用两个指针i和j分别指向两个字符串数组，用另外两个指针x和y分别指向字符串对应的字符。初始时i = j = x = y = 0。

每次比较word1[i][x]和word2[j][y]，如果不相等，直接返回 `false`。否则，将x和y分别加1，如果x或y超出了对应的字符串的长度，将对应的字符串指针i或j加1，然后将x和y重置为0。

如果两个字符串数组遍历完毕，返回 `true`，否则返回 `false`。

时间复杂度O(m)，空间复杂度O(1)。其中m为数组中字符串的总长度。

### **Java**

```java
class Solution {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        return String.join("", word1).equals(String.join("", word2));
    }
}
```

```java
class Solution {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        int i = 0, j = 0;
        int x = 0, y = 0;
        while (i < word1.length && j < word2.length) {
            if (word1[i].charAt(x++) != word2[j].charAt(y++)) {
                return false;
            }
            if (x == word1[i].length()) {
                x = 0;
                ++i;
            }
            if (y == word2[j].length()) {
                y = 0;
                ++j;
            }
        }
        return i == word1.length && j == word2.length;
    }
}
```

**
