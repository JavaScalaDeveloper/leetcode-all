# [648. 单词替换](https://leetcode.cn/problems/replace-words)

[English Version](/solution/0600-0699/0648.Replace%20Words/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在英语中，我们有一个叫做&nbsp;<code>词根</code>(root) 的概念，可以词根<strong>后面</strong>添加其他一些词组成另一个较长的单词——我们称这个词为&nbsp;<code>继承词</code>(successor)。例如，词根<code>an</code>，跟随着单词&nbsp;<code>other</code>(其他)，可以形成新的单词&nbsp;<code>another</code>(另一个)。</p>

<p>现在，给定一个由许多<strong>词根</strong>组成的词典 <code>dictionary</code> 和一个用空格分隔单词形成的句子 <code>sentence</code>。你需要将句子中的所有<strong>继承词</strong>用<strong>词根</strong>替换掉。如果<strong>继承词</strong>有许多可以形成它的<strong>词根</strong>，则用<strong>最短</strong>的词根替换它。</p>

<p>你需要输出替换之后的句子。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
<strong>输出：</strong>"the cat was rat by the bat"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
<strong>输出：</strong>"a a b c"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= dictionary.length&nbsp;&lt;= 1000</code></li>
	<li><code>1 &lt;= dictionary[i].length &lt;= 100</code></li>
	<li><code>dictionary[i]</code>&nbsp;仅由小写字母组成。</li>
	<li><code>1 &lt;= sentence.length &lt;= 10^6</code></li>
	<li><code>sentence</code>&nbsp;仅由小写字母和空格组成。</li>
	<li><code>sentence</code> 中单词的总量在范围 <code>[1, 1000]</code> 内。</li>
	<li><code>sentence</code> 中每个单词的长度在范围 <code>[1, 1000]</code> 内。</li>
	<li><code>sentence</code> 中单词之间由一个空格隔开。</li>
	<li><code>sentence</code>&nbsp;没有前导或尾随空格。</li>
</ul>

<p>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀树**

我们定义前缀树的节点数据结构如下：

-   `children`：子节点数组，长度为 $26$，每个元素为一个节点或 `None`
-   `ref`：如果当前节点是一个单词的结尾，则 `ref` 为该单词在 `dictionary` 中的索引，否则为 $-1$

我们首先将 `dictionary` 中的单词插入到前缀树中，然后遍历 `sentence` 中的每个单词，查找前缀树中是否存在该单词的前缀，如果存在，则将该单词替换为前缀。

时间复杂度为 $O(\sum_{w \in dictionary} |w| + |sentence|)$，空间复杂度为 $O(\sum_{w \in dictionary} |w|)$。其中 $|w|$ 表示单词 $w$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->



### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        Set<String> s = new HashSet<>(dictionary);
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; ++i) {
            String word = words[i];
            for (int j = 1; j <= word.length(); ++j) {
                String t = word.substring(0, j);
                if (s.contains(t)) {
                    words[i] = t;
                    break;
                }
            }
        }
        return String.join(" ", words);
    }
}
```

```java
class Trie {
    private Trie[] children = new Trie[26];
    private int ref = -1;

    public void insert(String w, int i) {
        Trie node = this;
        for (int j = 0; j < w.length(); ++j) {
            int idx = w.charAt(j) - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
        }
        node.ref = i;
    }

    public int search(String w) {
        Trie node = this;
        for (int j = 0; j < w.length(); ++j) {
            int idx = w.charAt(j) - 'a';
            if (node.children[idx] == null) {
                return -1;
            }
            node = node.children[idx];
            if (node.ref != -1) {
                return node.ref;
            }
        }
        return -1;
    }
}

class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        Trie trie = new Trie();
        for (int i = 0; i < dictionary.size(); ++i) {
            trie.insert(dictionary.get(i), i);
        }
        List<String> ans = new ArrayList<>();
        for (String w : sentence.split("\\s")) {
            int idx = trie.search(w);
            ans.add(idx == -1 ? w : dictionary.get(idx));
        }
        return String.join(" ", ans);
    }
}
```









### **TypeScript**



### **...**

```

```


