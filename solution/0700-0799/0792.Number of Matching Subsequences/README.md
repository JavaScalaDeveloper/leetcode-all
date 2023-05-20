# [792. 匹配子序列的单词数](https://leetcode.cn/problems/number-of-matching-subsequences)

## 题目描述

<p>给定字符串 <code>s</code>&nbsp;和字符串数组&nbsp;<code>words</code>, 返回&nbsp;&nbsp;<em><code>words[i]</code>&nbsp;中是<code>s</code>的子序列的单词个数</em>&nbsp;。</p>

<p>字符串的 <strong>子序列</strong> 是从原始字符串中生成的新字符串，可以从中删去一些字符(可以是none)，而不改变其余字符的相对顺序。</p>

<ul>
	<li>例如， <code>“ace”</code> 是 <code>“abcde”</code> 的子序列。</li>
</ul>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> s = "abcde", words = ["a","bb","acd","ace"]
<strong>输出:</strong> 3
<strong>解释:</strong> 有三个是&nbsp;s 的子序列的单词: "a", "acd", "ace"。
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>输入: </strong>s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
<strong>输出:</strong> 2
</pre>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= words.length &lt;= 5000</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 50</code></li>
	<li><code>words[i]</code>和 <font color="#c7254e" face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size: 12.6px; background-color: rgb(249, 242, 244);">s</span></font>&nbsp;都只由小写字母组成。</li>
</ul>
<span style="display:block"><span style="height:0px"><span style="position:absolute">​​​​</span></span></span>

## 解法

**方法一：分桶**

题目中字符串 $s$ 的数据规模最高达到 $5 \times 10^4$，如果暴力枚举 $words$ 中的每个字符串 $w$，判断其是否为 $s$ 的子序列，很有可能会超时。

我们不妨将 $words$ 中的所有单词根据首字母来分桶，即：把所有单词按照首字母分到 $26$ 个桶中，每个桶中存储的是所有以该字母开头的所有单词。

比如对于 `words = ["a", "bb", "acd", "ace"]`，我们得到以下的分桶结果：

```text
a: ["a", "acd", "ace"]
b: ["bb"]
```

然后我们从 $s$ 的第一个字符开始遍历，假设当前字符为 `'a'`，我们从 `'a'` 开头的桶中取出所有单词。对于取出的每个单词，如果此时单词长度为 $1$，说明该单词已经匹配完毕，我们将答案加 $1$；否则我们将单词的首字母去掉，然后放入下一个字母开头的桶中，比如对于单词 `"acd"`，去掉首字母 `'a'` 后，我们将其放入 `'c'` 开头的桶中。这一轮结束后，分桶结果变为：

```text
c: ["cd", "ce"]
b: ["bb"]
```

遍历完 $s$ 后，我们就得到了答案。

实际上，每个桶可以只存储单词的下标 $i$ 以及该单词当前匹配到的位置 $j$，这样可以节省空间。

时间复杂度 $O(n + \sum_{i=0}^{m-1} |w_i|)$，空间复杂度 $O(m)$。其中 $n$ 和 $m$ 分别为 $s$ 和 $words$ 的长度，而 $|w_i|$ 为 $words[i]$ 的长度。

**方法二：二分查找**

我们还可以先用数组或哈希表 $d$ 存放字符串 $s$ 每个字符的下标，即 $d[c]$ 为 $s$ 中所有字符 $c$ 的下标组成的数组。

然后我们遍历 $words$ 中的每个单词 $w$，我们通过二分查找的方法，判断 $w$ 是否为 $s$ 的子序列，是则答案加 $1$。判断逻辑如下：

1. 定义指针 $i$ 表示当前指向字符串 $s$ 的第 $i$ 个字符，初始化为 $-1$。
1. 遍历字符串 $w$ 中的每个字符 $c$，在 $d[c]$ 中二分查找第一个大于 $i$ 的位置 $j$，如果不存在，则说明 $w$ 不是 $s$ 的子序列，直接跳出循环；否则，将 $i$ 更新为 $d[c][j]$，继续遍历下一个字符。
1. 如果遍历完 $w$ 中的所有字符，说明 $w$ 是 $s$ 的子序列。

时间复杂度 $O(\sum_{i=0}^{m-1} |w_i| \times \log n)$，空间复杂度 $O(m)$。其中 $n$ 和 $m$ 分别为 $s$ 和 $words$ 的长度，而 $|w_i|$ 为 $words[i]$ 的长度。

### **Java**

```java
class Solution {
    public int numMatchingSubseq(String s, String[] words) {
        Deque<String>[] d = new Deque[26];
        Arrays.setAll(d, k -> new ArrayDeque<>());
        for (String w : words) {
            d[w.charAt(0) - 'a'].add(w);
        }
        int ans = 0;
        for (char c : s.toCharArray()) {
            var q = d[c - 'a'];
            for (int k = q.size(); k > 0; --k) {
                String t = q.pollFirst();
                if (t.length() == 1) {
                    ++ans;
                } else {
                    d[t.charAt(1) - 'a'].offer(t.substring(1));
                }
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int numMatchingSubseq(String s, String[] words) {
        Deque<int[]>[] d = new Deque[26];
        Arrays.setAll(d, k -> new ArrayDeque<>());
        for (int i = 0; i < words.length; ++i) {
            d[words[i].charAt(0) - 'a'].offer(new int[] {i, 0});
        }
        int ans = 0;
        for (char c : s.toCharArray()) {
            var q = d[c - 'a'];
            for (int t = q.size(); t > 0; --t) {
                var p = q.pollFirst();
                int i = p[0], j = p[1] + 1;
                if (j ==  words[i].length()) {
                    ++ans;
                } else {
                    d[words[i].charAt(j) - 'a'].offer(new int[] {i, j});
                }
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    private List<Integer>[] d = new List[26];

    public int numMatchingSubseq(String s, String[] words) {
        Arrays.setAll(d, k -> new ArrayList<>());
        for (int i = 0; i < s.length(); ++i) {
            d[s.charAt(i) - 'a'].add(i);
        }
        int ans = 0;
        for (String w : words) {
            if (check(w)) {
                ++ans;
            }
        }
        return ans;
    }

    private boolean check(String w) {
        int i = -1;
        for (int k = 0; k < w.length(); ++k) {
            int c = w.charAt(k) - 'a';
            int j = search(d[c], i);
            if (j == d[c].size()) {
                return false;
            }
            i = d[c].get(j);
        }
        return true;
    }

    private int search(List<Integer> t, int x) {
        int left = 0, right = t.size();
        while (left < right) {
            int mid = (left + right) >> 1;
            if (t.get(mid) > x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```
