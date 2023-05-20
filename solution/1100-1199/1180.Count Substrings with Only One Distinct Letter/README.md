# [1180. 统计只含单一字母的子串](https://leetcode.cn/problems/count-substrings-with-only-one-distinct-letter)

## 题目描述

<p>给你一个字符串 <code>s</code>，返回 <em>只含 <strong>单一字母</strong> 的子串个数</em> 。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入： </strong>s = "aaaba"
<strong>输出： </strong>8
<strong>解释： </strong>只含单一字母的子串分别是 "aaa"， "aa"， "a"， "b"。
"aaa" 出现 1 次。
"aa" 出现 2 次。
"a" 出现 4 次。
"b" 出现 1 次。
所以答案是 1 + 2 + 4 + 1 = 8。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入： </strong>s = "aaaaaaaaaa"
<strong>输出： </strong>55
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s[i]</code> 仅由小写英文字母组成</li>
</ul>

## 解法

### **Java**

```java
class Solution {
    public int countLetters(String s) {
        int ans = 0;
        for (int i = 0, n = s.length(); i < n;) {
            int j = i;
            while (j < n && s.charAt(j) == s.charAt(i)) {
                ++j;
            }
            ans += (1 + j - i) * (j - i) / 2;
            i = j;
        }
        return ans;
    }
}
```
