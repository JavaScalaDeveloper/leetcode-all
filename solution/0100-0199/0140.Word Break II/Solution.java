package com.solution._0140;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    private Trie trie = new Trie();

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "catsanddog";
        String[] wordDict = new String[]{"cat", "cats", "and", "sand", "dog"};
        List<String> res = solution.wordBreak2(s, Arrays.asList(wordDict));
        System.out.println(res);
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        for (String w : wordDict) {
            trie.insert(w);
        }
        List<List<String>> res = dfs(s);
        return res.stream().map(e -> String.join(" ", e)).collect(Collectors.toList());
    }

    private List<List<String>> dfs(String s) {
        List<List<String>> res = new ArrayList<>();
        if ("".equals(s)) {
            res.add(new ArrayList<>());
            return res;
        }
        for (int i = 1; i <= s.length(); ++i) {
            if (trie.search(s.substring(0, i))) {
                for (List<String> v : dfs(s.substring(i))) {
                    v.add(0, s.substring(0, i));
                    res.add(v);
                }
            }
        }
        return res;
    }

    /*
    定义一个递归函数 dfs(i) 表示从 s 的第 i 个字符开始构建匹配的句子，并返回所有可行的句子。由于不同的起始位置可能构成相同的句子，因此我们使用一个哈希表 memo 记录已经计算过的起始位置以及它们所对应的句子列表。
    具体来说，在递归函数中，我们首先检查 memo 是否包含当前起始位置 i，如果包含，则直接返回 memo[i]；否则，我们枚举从 i 开始的所有可能的单词，并递归处理剩余的部分，将结果拼接到当前单词上。如果单词可以匹配剩余部分，并且剩余部分也可以构成匹配的句子，那么将其加入结果列表中，并添加到 memo 中。
    时间复杂度和空间复杂度均为 O(n^3)
     */
    public List<String> wordBreak2(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        Map<Integer, List<String>> memo = new HashMap<>();
        return dfs(s, 0, wordSet, memo);
    }

    private List<String> dfs(String s, int start, Set<String> wordSet, Map<Integer, List<String>> memo) {
        if (memo.containsKey(start)) {  // 如果 memo 中已经有了该起点的结果，则直接返回
            return memo.get(start);
        }
        List<String> result = new ArrayList<>();
        if (start == s.length()) {  // 到达字符串结尾
            result.add("");
            return result;
        }
        for (int end = start + 1; end <= s.length(); end++) {
            String word = s.substring(start, end);
            if (wordSet.contains(word)) {
                List<String> nexts = dfs(s, end, wordSet, memo);  // 递归处理后面部分
                for (String next : nexts) {
                    result.add(word + (next.isEmpty() ? "" : " ") + next);
                }
            }
        }
        memo.put(start, result);  // 将结果添加到 memo 中缓存
        return result;
    }

}

class Trie {
    Trie[] children = new Trie[26];
    boolean isEnd;

    void insert(String word) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                node.children[c] = new Trie();
            }
            node = node.children[c];
        }
        node.isEnd = true;
    }

    boolean search(String word) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                return false;
            }
            node = node.children[c];
        }
        return node.isEnd;
    }
}