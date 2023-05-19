# [720. 词典中最长的单词](https://leetcode.cn/problems/longest-word-in-dictionary)

[English Version](/solution/0700-0799/0720.Longest%20Word%20in%20Dictionary/README_EN.md)

## 题目描述

<p>给出一个字符串数组&nbsp;<code>words</code> 组成的一本英语词典。返回&nbsp;<code>words</code> 中最长的一个单词，该单词是由&nbsp;<code>words</code>&nbsp;词典中其他单词逐步添加一个字母组成。</p>

<p>若其中有多个可行的答案，则返回答案中字典序最小的单词。若无答案，则返回空字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>words = ["w","wo","wor","worl", "world"]
<strong>输出：</strong>"world"
<strong>解释：</strong> 单词"world"可由"w", "wo", "wor", 和 "worl"逐步添加一个字母组成。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
<strong>输出：</strong>"apple"
<strong>解释：</strong>"apply" 和 "apple" 都能由词典中的单词组成。但是 "apple" 的字典序小于 "apply" 
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 1000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 30</code></li>
	<li>所有输入的字符串&nbsp;<code>words[i]</code>&nbsp;都只包含小写字母。</li>
</ul>

## 解法

**方法一：哈希表**

用哈希表存放所有单词。遍历这些单词，找出**长度最长且字典序最小**的单词。

**方法二：排序**

优先返回符合条件、**长度最长且字典序最小**的单词，那么可以进行依照该规则，先对 `words` 进行排序，免去多个结果之间的比较。

### **Java**

```java
class Solution {
    private Set<String> s;

    public String longestWord(String[] words) {
        s = new HashSet<>(Arrays.asList(words));
        int cnt = 0;
        String ans = "";
        for (String w : s) {
            int n = w.length();
            if (check(w)) {
                if (cnt < n) {
                    cnt = n;
                    ans = w;
                } else if (cnt == n && w.compareTo(ans) < 0) {
                    ans = w;
                }
            }
        }
        return ans;
    }

    private boolean check(String word) {
        for (int i = 1, n = word.length(); i < n; ++i) {
            if (!s.contains(word.substring(0, i))) {
                return false;
            }
        }
        return true;
    }
}
```
