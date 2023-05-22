# [387. 字符串中的第一个唯一字符](https://leetcode.cn/problems/first-unique-character-in-a-string)

## 题目描述

<p>给定一个字符串&nbsp;<code>s</code>&nbsp;，找到 <em>它的第一个不重复的字符，并返回它的索引</em> 。如果不存在，则返回 <code>-1</code>&nbsp;。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> s = "leetcode"
<strong>输出:</strong> 0
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> s = "loveleetcode"
<strong>输出:</strong> 2
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> s = "aabb"
<strong>输出:</strong> -1
</pre>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code>&nbsp;只包含小写字母</li>
</ul>

## 解法

**方法一：数组或哈希表**

我们可以用数组或哈希表cnt记录字符串s中每个字符出现的次数。

然后我们再遍历字符串s，当遍历到某个字符c时，如果cnt[c]=1，则说明c是第一个不重复的字符，返回它的索引即可。

如果遍历完字符串s仍然没有找到不重复的字符，返回-1。

时间复杂度O(n)，空间复杂度O(\Sigma)，其中\Sigma是字符集的大小。

### **Java**

```java
class Solution {
    public int firstUniqChar(String s) {
        int[] cnt = new int[26];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        for (int i = 0; i < n; ++i) {
            if (cnt[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}
```
