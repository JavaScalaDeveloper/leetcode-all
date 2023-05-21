# [2185. 统计包含给定前缀的字符串](https://leetcode.cn/problems/counting-words-with-a-given-prefix)

## 题目描述

<p>给你一个字符串数组 <code>words</code> 和一个字符串 <code>pref</code> 。</p>

<p>返回 <code>words</code><em> </em>中以 <code>pref</code> 作为 <strong>前缀</strong> 的字符串的数目。</p>

<p>字符串 <code>s</code> 的 <strong>前缀</strong> 就是&nbsp; <code>s</code> 的任一前导连续字符串。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>words = ["pay","<em><strong>at</strong></em>tention","practice","<em><strong>at</strong></em>tend"], <code>pref </code>= "at"
<strong>输出：</strong>2
<strong>解释：</strong>以 "at" 作为前缀的字符串有两个，分别是："<em><strong>at</strong></em>tention" 和 "<em><strong>at</strong></em>tend" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>words = ["leetcode","win","loops","success"], <code>pref </code>= "code"
<strong>输出：</strong>0
<strong>解释：</strong>不存在以 "code" 作为前缀的字符串。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length, pref.length &lt;= 100</code></li>
	<li><code>words[i]</code> 和 <code>pref</code> 由小写英文字母组成</li>
</ul>

## 解法

**方法一：一次遍历**

根据题目描述，我们遍历字符串数组 `words` 中的每个字符串w，判断其是否以pref作为前缀，如果是，则答案加一。

时间复杂度O(n \times m)，空间复杂度O(1)。其中n和m分别是字符串数组 `words` 和字符串pref的长度。

**方法二：前缀树**

我们还可以使用前缀树来查询答案。

定义前缀树的每个节点结构如下：

-   `children`：长度为26的数组，用于存储当前节点的所有子节点，其中 `children[i]` 表示当前节点的子节点；
-   `cnt`：所有以当前节点为前缀的字符串的数量。

另外，我们还需要定义两个函数：

-   其中一个函数insert(w)用于将字符串w插入前缀树中；
-   另一个函数search(pref)用于查询以字符串pref作为前缀的字符串的数量。查询时，我们从前缀树的根节点开始，遍历字符串pref，如果当前节点的子节点中不存在pref[i]，则说明pref不是任何字符串的前缀，直接返回0。否则，我们继续遍历pref的下一个字符，直到遍历完pref，返回当前节点的 `cnt` 即可。

有了上述函数，我们就可以查询答案了。

遍历字符串数组 `words`，对于每个字符串w，调用insert(w)函数将其插入前缀树中。最后调用search(pref)函数作为答案返回即可。

时间复杂度O(L)，空间复杂度O(L)。其中L是字符串数组 `words` 中所有字符串的长度之和。

### **Java**

```java
class Solution {
    public int prefixCount(String[] words, String pref) {
        int ans = 0;
        for (String w : words) {
            if (w.startsWith(pref)) {
                ++ans;
            }
        }
        return ans;
    }
}
```

```java
class Trie {
    private Trie[] children = new Trie[26];
    private int cnt;

    public void insert(String w) {
        Trie node = this;
        for (int i = 0; i < w.length(); ++i) {
            int j = w.charAt(i) - 'a';
            if (node.children[j] == null) {
                node.children[j] = new Trie();
            }
            node = node.children[j];
            ++node.cnt;
        }
    }

    public int search(String pref) {
        Trie node = this;
        for (int i = 0; i < pref.length(); ++i) {
            int j = pref.charAt(i) - 'a';
            if (node.children[j] == null) {
                return 0;
            }
            node = node.children[j];
        }
        return node.cnt;
    }
}

class Solution {
    public int prefixCount(String[] words, String pref) {
        Trie tree = new Trie();
        for (String w : words) {
            tree.insert(w);
        }
        return tree.search(pref);
    }
}
```

**
