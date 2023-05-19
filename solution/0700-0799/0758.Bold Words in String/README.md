# [758. 字符串中的加粗单词](https://leetcode.cn/problems/bold-words-in-string)

[English Version](/solution/0700-0799/0758.Bold%20Words%20in%20String/README_EN.md)

## 题目描述

<p>给定一个关键词集合&nbsp;<code>words</code> 和一个字符串&nbsp;<code>s</code>，将所有 <code>s</code> 中出现的关键词&nbsp;<code>words[i]</code>&nbsp;加粗。所有在标签&nbsp;<code>&lt;b&gt;</code>&nbsp;和&nbsp;<code>&lt;b&gt;</code>&nbsp;中的字母都会加粗。</p>

<p>加粗后返回 <code>s</code> 。返回的字符串需要使用尽可能少的标签，当然标签应形成有效的组合。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> words = ["ab","bc"], s = "aabcd"
<strong>输出:</strong> "a&lt;b&gt;abc&lt;/b&gt;d"
<strong>解释: </strong>注意返回 <code>"a&lt;b&gt;a&lt;b&gt;b&lt;/b&gt;c&lt;/b&gt;d"</code> 会使用更多的标签，因此是错误的。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> words = ["ab","cb"], s = "aabcd"
<strong>输出:</strong> "a&lt;b&gt;ab&lt;/b&gt;cd"
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 500</code></li>
	<li><code>0 &lt;= words.length &lt;= 50</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 10</code></li>
	<li><code>s</code>&nbsp;和&nbsp;<code>words[i]</code>&nbsp;由小写英文字母组成</li>
</ul>

<p>&nbsp;</p>

<p><strong>注：</strong>此题与「616 - 给字符串添加加粗标签」相同 - <a href="https://leetcode.cn/problems/add-bold-tag-in-string/">https://leetcode.cn/problems/add-bold-tag-in-string/</a></p>

## 解法

**方法一：前缀树 + 区间合并**

相似题目：[1065. 字符串的索引对](/solution/1000-1099/1065.Index%20Pairs%20of%20a%20String/README.md)、[616. 给字符串添加加粗标签](/solution/0600-0699/0616.Add%20Bold%20Tag%20in%20String/README.md)

### **Java**

```java
class Trie {
    Trie[] children = new Trie[128];
    boolean isEnd;

    public void insert(String word) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            if (node.children[c] == null) {
                node.children[c] = new Trie();
            }
            node = node.children[c];
        }
        node.isEnd = true;
    }
}

class Solution {
    public String boldWords(String[] words, String s) {
        Trie trie = new Trie();
        for (String w : words) {
            trie.insert(w);
        }
        List<int[]> pairs = new ArrayList<>();
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            Trie node = trie;
            for (int j = i; j < n; ++j) {
                int idx = s.charAt(j);
                if (node.children[idx] == null) {
                    break;
                }
                node = node.children[idx];
                if (node.isEnd) {
                    pairs.add(new int[] {i, j});
                }
            }
        }
        if (pairs.isEmpty()) {
            return s;
        }
        List<int[]> t = new ArrayList<>();
        int st = pairs.get(0)[0], ed = pairs.get(0)[1];
        for (int j = 1; j < pairs.size(); ++j) {
            int a = pairs.get(j)[0], b = pairs.get(j)[1];
            if (ed + 1 < a) {
                t.add(new int[] {st, ed});
                st = a;
                ed = b;
            } else {
                ed = Math.max(ed, b);
            }
        }
        t.add(new int[] {st, ed});
        int i = 0, j = 0;
        StringBuilder ans = new StringBuilder();
        while (i < n) {
            if (j == t.size()) {
                ans.append(s.substring(i));
                break;
            }
            st = t.get(j)[0];
            ed = t.get(j)[1];
            if (i < st) {
                ans.append(s.substring(i, st));
            }
            ++j;
            ans.append("<b>");
            ans.append(s.substring(st, ed + 1));
            ans.append("</b>");
            i = ed + 1;
        }
        return ans.toString();
    }
}
```
