# [884. 两句话中的不常见单词](https://leetcode.cn/problems/uncommon-words-from-two-sentences)

## 题目描述

<p><strong>句子</strong> 是一串由空格分隔的单词。每个 <strong>单词</strong><em> </em>仅由小写字母组成。</p>

<p>如果某个单词在其中一个句子中恰好出现一次，在另一个句子中却 <strong>没有出现</strong> ，那么这个单词就是 <strong>不常见的</strong><em> </em>。</p>

<p>给你两个 <strong>句子</strong> <code>s1</code> 和 <code>s2</code> ，返回所有 <strong>不常用单词</strong> 的列表。返回列表中单词可以按 <strong>任意顺序</strong> 组织。</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s1 = "this apple is sweet", s2 = "this apple is sour"
<strong>输出：</strong>["sweet","sour"]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s1 = "apple apple", s2 = "banana"
<strong>输出：</strong>["banana"]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s1.length, s2.length &lt;= 200</code></li>
	<li><code>s1</code> 和 <code>s2</code> 由小写英文字母和空格组成</li>
	<li><code>s1</code> 和 <code>s2</code> 都不含前导或尾随空格</li>
	<li><code>s1</code> 和 <code>s2</code> 中的所有单词间均由单个空格分隔</li>
</ul>

## 解法

**方法一：哈希表**

根据题目描述，只要单词出现一次，就符合题目要求。因此，我们用哈希表cnt记录所有单词以及出现的次数。

然后遍历哈希表，取出所有出现次数为1的字符串即可。

时间复杂度O(m + n)，空间复杂度O(m + n)。其中m和n分别是字符串s1和s2的长度。

### **Java**

```java
class Solution {
    public String[] uncommonFromSentences(String s1, String s2) {
        Map<String, Integer> cnt = new HashMap<>();
        for (String s : s1.split(" ")) {
            cnt.put(s, cnt.getOrDefault(s, 0) + 1);
        }
        for (String s : s2.split(" ")) {
            cnt.put(s, cnt.getOrDefault(s, 0) + 1);
        }
        List<String> ans = new ArrayList<>();
        for (var e : cnt.entrySet()) {
            if (e.getValue() == 1) {
                ans.add(e.getKey());
            }
        }
        return ans.toArray(new String[0]);
    }
}
```
