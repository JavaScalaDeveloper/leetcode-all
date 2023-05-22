# [139. 单词拆分](https://leetcode.cn/problems/word-break)

## 题目描述

<p>给你一个字符串 <code>s</code> 和一个字符串列表 <code>wordDict</code> 作为字典。请你判断是否可以利用字典中出现的单词拼接出 <code>s</code> 。</p>

<p><strong>注意：</strong>不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> s = "leetcode", wordDict = ["leet", "code"]
<strong>输出:</strong> true
<strong>解释:</strong> 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入:</strong> s = "applepenapple", wordDict = ["apple", "pen"]
<strong>输出:</strong> true
<strong>解释:</strong> 返回 true 因为 <code>"</code>applepenapple<code>"</code> 可以由 <code>"</code>apple" "pen" "apple<code>" 拼接成</code>。
&nbsp;    注意，你可以重复使用字典中的单词。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入:</strong> s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
<strong>输出:</strong> false
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 300</code></li>
	<li><code>1 &lt;= wordDict.length &lt;= 1000</code></li>
	<li><code>1 &lt;= wordDict[i].length &lt;= 20</code></li>
	<li><code>s</code> 和 <code>wordDict[i]</code> 仅有小写英文字母组成</li>
	<li><code>wordDict</code> 中的所有字符串 <strong>互不相同</strong></li>
</ul>

## 解法

**方法一：动态规划**

dp[i]表示前i个字符组成的字符串s[0...i-1]能否拆分成若干个字典中出现的单词。

时间复杂度O(n^2)。

**方法二：前缀树 + 记忆化搜索**

根据wordDict构建前缀树trie，然后枚举前缀s[:i]作为第一个单词，若在trie中，则递归搜索s[i:]。

若存在满足条件的拆分方案，返回true。

### **Java**

```java
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (dp[j] && words.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
```

```java
class Trie {
    Trie[] children = new Trie[26];
    boolean isEnd;

    void insert(String w) {
        Trie node = this;
        for (char c : w.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                node.children[c] = new Trie();
            }
            node = node.children[c];
        }
        node.isEnd = true;
    }

    boolean search(String w) {
        Trie node = this;
        for (char c : w.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                return false;
            }
            node = node.children[c];
        }
        return node.isEnd;
    }
}

class Solution {
    private Trie trie = new Trie();
    private Map<String, Boolean> memo = new HashMap<>();

    public boolean wordBreak(String s, List<String> wordDict) {
        for (String w : wordDict) {
            trie.insert(w);
        }
        return dfs(s);
    }

    private boolean dfs(String s) {
        if (memo.containsKey(s)) {
            return memo.get(s);
        }
        if ("".equals(s)) {
            return true;
        }
        for (int i = 1; i <= s.length(); ++i) {
            if (trie.search(s.substring(0, i)) && dfs(s.substring(i))) {
                memo.put(s, true);
                return true;
            }
        }
        memo.put(s, false);
        return false;
    }
}
```
