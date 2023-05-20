# [212. 单词搜索 II](https://leetcode.cn/problems/word-search-ii)

## 题目描述

<p>给定一个&nbsp;<code>m x n</code> 二维字符网格&nbsp;<code>board</code><strong>&nbsp;</strong>和一个单词（字符串）列表 <code>words</code>，&nbsp;<em>返回所有二维网格上的单词</em>&nbsp;。</p>

<p>单词必须按照字母顺序，通过 <strong>相邻的单元格</strong> 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0212.Word%20Search%20II/images/search1.jpg" />
<pre>
<strong>输入：</strong>board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
<strong>输出：</strong>["eat","oath"]
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0212.Word%20Search%20II/images/search2.jpg" />
<pre>
<strong>输入：</strong>board = [["a","b"],["c","d"]], words = ["abcb"]
<strong>输出：</strong>[]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == board.length</code></li>
	<li><code>n == board[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 12</code></li>
	<li><code>board[i][j]</code> 是一个小写英文字母</li>
	<li><code>1 &lt;= words.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= words[i].length &lt;= 10</code></li>
	<li><code>words[i]</code> 由小写英文字母组成</li>
	<li><code>words</code> 中的所有字符串互不相同</li>
</ul>

## 解法

**方法一：前缀树 + DFS**

我们首先将 `words` 中的单词构建成前缀树，前缀树的每个节点包含一个长度为 $26$ 的数组 `children`，表示该节点的子节点，数组的下标表示子节点对应的字符，数组的值表示子节点的引用。同时，每个节点还包含一个整数 `ref`，表示该节点对应的单词在 `words` 中的引用，如果该节点不是单词的结尾，则 `ref` 的值为 $-1$。

接下来，我们对于 `board` 中的每个单元格，从该单元格出发，进行深度优先搜索，搜索过程中，如果当前单词不是前缀树中的单词，则剪枝，如果当前单词是前缀树中的单词，则将该单词加入答案，并将该单词在前缀树中的引用置为 $-1$，表示该单词已经被找到，不需要再次搜索。

最后，我们将答案返回即可。

时间复杂度 $(m \times n \times 3^{l-1})$，空间复杂度 $(k \times l)$。其中 $m$ 和 $n$ 分别是 `board` 的行数和列数。而 $l$ 和 $k$ 分别是 `words` 中的单词的平均长度和单词的个数。

### **Java**

```java
class Trie {
    Trie[] children = new Trie[26];
    int ref = -1;

    public void insert(String w, int ref) {
        Trie node = this;
        for (int i = 0; i < w.length(); ++i) {
            int j = w.charAt(i) - 'a';
            if (node.children[j] == null) {
                node.children[j] = new Trie();
            }
            node = node.children[j];
        }
        node.ref = ref;
    }
}

class Solution {
    private char[][] board;
    private String[] words;
    private List<String> ans = new ArrayList<>();

    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        this.words = words;
        Trie tree = new Trie();
        for (int i = 0; i < words.length; ++i) {
            tree.insert(words[i], i);
        }
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                dfs(tree, i, j);
            }
        }
        return ans;
    }

    private void dfs(Trie node, int i, int j) {
        int idx = board[i][j] - 'a';
        if (node.children[idx] == null) {
            return;
        }
        node = node.children[idx];
        if (node.ref != -1) {
            ans.add(words[node.ref]);
            node.ref = -1;
        }
        char c = board[i][j];
        board[i][j] = '#';
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int k = 0; k < 4; ++k) {
            int x = i + dirs[k], y = j + dirs[k + 1];
            if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y] != '#') {
                dfs(node, x, y);
            }
        }
        board[i][j] = c;
    }
}
```
