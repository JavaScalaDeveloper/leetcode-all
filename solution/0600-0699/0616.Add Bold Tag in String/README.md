# [616. 给字符串添加加粗标签](https://leetcode.cn/problems/add-bold-tag-in-string)

[English Version](/solution/0600-0699/0616.Add%20Bold%20Tag%20in%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> 和一个字符串列表 <code>words</code> ，你需要将在字符串列表中出现过的 <code>s</code> 的子串添加加粗闭合标签 &lt;b&gt; 和 &lt;/b&gt; 。</p>

<p>如果两个子串有重叠部分，你需要把它们一起用一对闭合标签包围起来。同理，如果两个子字符串连续被加粗，那么你也需要把它们合起来用一对加粗标签包围。</p>

<p>返回添加加粗标签后的字符串 <code>s</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong> s = "abcxyz123", words = ["abc","123"]
<strong>输出：</strong>"&lt;b&gt;abc&lt;/b&gt;xyz&lt;b&gt;123&lt;/b&gt;"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "aaabbcc", words = ["aaa","aab","bc"]
<strong>输出：</strong>"&lt;b&gt;aaabbc&lt;/b&gt;c"
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 1000</code></li>
	<li><code>0 <= words.length <= 100</code></li>
	<li><code>1 <= words[i].length <= 1000</code></li>
	<li><code>s</code> 和 <code>words[i]</code> 由英文字母和数字组成</li>
	<li><code>words</code> 中的所有值 <strong>互不相同</strong></li>
</ul>

<p> </p>

<p><strong>注：</strong>此题与「758 - 字符串中的加粗单词」相同 - <a href="https://leetcode.cn/problems/bold-words-in-string">https://leetcode.cn/problems/bold-words-in-string</a></p>

<p> </p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀树 + 区间合并**

相似题目：[1065. 字符串的索引对](/solution/1000-1099/1065.Index%20Pairs%20of%20a%20String/README.md)、[758. 字符串中的加粗单词](/solution/0700-0799/0758.Bold%20Words%20in%20String/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->



### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
    public String addBoldTag(String s, String[] words) {
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









### **...**

```

```


