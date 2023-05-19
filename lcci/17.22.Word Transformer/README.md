# [面试题 17.22. 单词转换](https://leetcode.cn/problems/word-transformer-lcci)

[English Version](/lcci/17.22.Word%20Transformer/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定字典中的两个词，长度相等。写一个方法，把一个词转换成另一个词， 但是一次只能改变一个字符。每一步得到的新词都必须能在字典中找到。</p>

<p>编写一个程序，返回一个可能的转换序列。如有多个可能的转换序列，你可以返回任何一个。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong>
beginWord = &quot;hit&quot;,
endWord = &quot;cog&quot;,
wordList = [&quot;hot&quot;,&quot;dot&quot;,&quot;dog&quot;,&quot;lot&quot;,&quot;log&quot;,&quot;cog&quot;]

<strong>输出:</strong>
[&quot;hit&quot;,&quot;hot&quot;,&quot;dot&quot;,&quot;lot&quot;,&quot;log&quot;,&quot;cog&quot;]
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong>
beginWord = &quot;hit&quot;
endWord = &quot;cog&quot;
wordList = [&quot;hot&quot;,&quot;dot&quot;,&quot;dog&quot;,&quot;lot&quot;,&quot;log&quot;]

<strong>输出: </strong>[]

<strong>解释:</strong>&nbsp;<em>endWord</em> &quot;cog&quot; 不在字典中，所以不存在符合要求的转换序列。</pre>

## 解法

DFS。

### **Java**

```java
class Solution {
    private List<String> words;
    private List<String> ans;
    private Set<String> visited;

    public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
        words = wordList;
        ans = new ArrayList<>();
        visited = new HashSet<>();
        List<String> t = new ArrayList<>();
        t.add(beginWord);
        dfs(beginWord, endWord, t);
        return ans;
    }

    private void dfs(String begin, String end, List<String> t) {
        if (!ans.isEmpty()) {
            return;
        }
        if (Objects.equals(begin, end)) {
            ans = new ArrayList<>(t);
            return;
        }
        for (String word : words) {
            if (visited.contains(word) || !check(begin, word)) {
                continue;
            }
            t.add(word);
            visited.add(word);
            dfs(word, end, t);
            t.remove(t.size() - 1);
        }
    }

    private boolean check(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }
        int cnt = 0;
        for (int i = 0; i < a.length(); ++i) {
            if (a.charAt(i) != b.charAt(i)) {
                ++cnt;
            }
        }
        return cnt == 1;
    }
}
```
