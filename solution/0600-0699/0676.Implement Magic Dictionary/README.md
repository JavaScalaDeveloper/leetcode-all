# [676. 实现一个魔法字典](https://leetcode.cn/problems/implement-magic-dictionary)

[English Version](/solution/0600-0699/0676.Implement%20Magic%20Dictionary/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>设计一个使用单词列表进行初始化的数据结构，单词列表中的单词 <strong>互不相同</strong> 。 如果给出一个单词，请判定能否只将这个单词中<strong>一个</strong>字母换成另一个字母，使得所形成的新单词存在于你构建的字典中。</p>

<p>实现 <code>MagicDictionary</code> 类：</p>

<ul>
	<li><code>MagicDictionary()</code> 初始化对象</li>
	<li><code>void buildDict(String[] dictionary)</code> 使用字符串数组 <code>dictionary</code> 设定该数据结构，<code>dictionary</code> 中的字符串互不相同</li>
	<li><code>bool search(String searchWord)</code> 给定一个字符串 <code>searchWord</code> ，判定能否只将字符串中<strong> 一个 </strong>字母换成另一个字母，使得所形成的新字符串能够与字典中的任一字符串匹配。如果可以，返回 <code>true</code> ；否则，返回 <code>false</code> 。</li>
</ul>

<p> </p>

<div class="top-view__1vxA">
<div class="original__bRMd">
<div>
<p><strong>示例：</strong></p>

<pre>
<strong>输入</strong>
["MagicDictionary", "buildDict", "search", "search", "search", "search"]
[[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
<strong>输出</strong>
[null, null, false, true, false, false]

<strong>解释</strong>
MagicDictionary magicDictionary = new MagicDictionary();
magicDictionary.buildDict(["hello", "leetcode"]);
magicDictionary.search("hello"); // 返回 False
magicDictionary.search("hhllo"); // 将第二个 'h' 替换为 'e' 可以匹配 "hello" ，所以返回 True
magicDictionary.search("hell"); // 返回 False
magicDictionary.search("leetcoded"); // 返回 False
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= dictionary.length <= 100</code></li>
	<li><code>1 <= dictionary[i].length <= 100</code></li>
	<li><code>dictionary[i]</code> 仅由小写英文字母组成</li>
	<li><code>dictionary</code> 中的所有字符串 <strong>互不相同</strong></li>
	<li><code>1 <= searchWord.length <= 100</code></li>
	<li><code>searchWord</code> 仅由小写英文字母组成</li>
	<li><code>buildDict</code> 仅在 <code>search</code> 之前调用一次</li>
	<li>最多调用 <code>100</code> 次 <code>search</code></li>
</ul>
</div>
</div>
</div>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：直接遍历**

对于 $buildDict$ 方法，直接将 $dictionary$ 赋给 $MagicDictionary$ 的成员变量 $d$。

对于 $search$ 方法，遍历单词列表中的每个单词 $w$，依次与 $searchWord$ 进行比对，如果存在一个 $w$，满足 $w$ 与 $searchWord$ 恰好只有一个位置对应的字符不同，那么返回 $true$。

**方法二：哈希表 + 模式串**

用哈希表 $s$ 存放 $dictionary$ 所有单词，同时生成每个单词的所有模式串，用哈希表 $cnt$ 存放。

模式串的生成规则是：对于一个单词 $w$，我们将每个 $w[i]$ 都替换成 $.$，最终得到一个模式串列表。例如，我们可以生成 $leet$ 的模式串列表为：$[.eet, l.et, le.t, lee.]$。

执行 $search$ 时，我们拿到 $searchWord$ 的模式串列表，然后判断列表中每个模式串 $p$ 是否在 $cnt$ 和 $s$ 中出现过。若 $cnt>1$ 或 $cnt=1$ 且 $searchWord$ 没在 $s$ 中出现过，说明找到了满足条件的单词，返回 $true$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->





### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class MagicDictionary {
    private String[] d;

    public MagicDictionary() {

    }

    public void buildDict(String[] dictionary) {
        d = dictionary;
    }

    public boolean search(String searchWord) {
        for (String w : d) {
            if (w.length() != searchWord.length()) {
                continue;
            }
            int diff = 0;
            for (int i = 0; i < w.length(); ++i) {
                if (w.charAt(i) != searchWord.charAt(i)) {
                    ++diff;
                }
            }
            if (diff == 1) {
                return true;
            }
        }
        return false;
    }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dictionary);
 * boolean param_2 = obj.search(searchWord);
 */
```

```java
class MagicDictionary {
    private Set<String> s = new HashSet<>();
    private Map<String, Integer> cnt = new HashMap<>();

    /** Initialize your data structure here. */
    public MagicDictionary() {
    }

    public void buildDict(String[] dictionary) {
        for (String word : dictionary) {
            s.add(word);
            for (String p : gen(word)) {
                cnt.put(p, cnt.getOrDefault(p, 0) + 1);
            }
        }
    }

    public boolean search(String searchWord) {
        for (String p : gen(searchWord)) {
            int v = cnt.getOrDefault(p, 0);
            if (v > 1 || (v == 1 && !s.contains(searchWord))) {
                return true;
            }
        }
        return false;
    }

    private List<String> gen(String word) {
        List<String> res = new ArrayList<>();
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; ++i) {
            char c = chars[i];
            chars[i] = '*';
            res.add(new String(chars));
            chars[i] = c;
        }
        return res;
    }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dictionary);
 * boolean param_2 = obj.search(searchWord);
 */
```













### **...**

```

```


