# [剑指 Offer II 114. 外星文字典](https://leetcode.cn/problems/Jf1JuT)

## 题目描述



<p>现有一种使用英语字母的外星文语言，这门语言的字母顺序与英语顺序不同。</p>

<p>给定一个字符串列表 <code>words</code> ，作为这门语言的词典，<code>words</code> 中的字符串已经 <strong>按这门新语言的字母顺序进行了排序</strong> 。</p>

<p>请你根据该词典还原出此语言中已知的字母顺序，并 <strong>按字母递增顺序</strong> 排列。若不存在合法字母顺序，返回 <code>&quot;&quot;</code> 。若存在多种可能的合法字母顺序，返回其中 <strong>任意一种</strong> 顺序即可。</p>

<p>字符串 <code>s</code> <strong>字典顺序小于</strong> 字符串 <code>t</code> 有两种情况：</p>

<ul>
	<li>在第一个不同字母处，如果 <code>s</code> 中的字母在这门外星语言的字母顺序中位于 <code>t</code> 中字母之前，那么&nbsp;<code>s</code> 的字典顺序小于 <code>t</code> 。</li>
	<li>如果前面 <code>min(s.length, t.length)</code> 字母都相同，那么 <code>s.length &lt; t.length</code> 时，<code>s</code> 的字典顺序也小于 <code>t</code> 。</li>
</ul>



<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>words = [&quot;wrt&quot;,&quot;wrf&quot;,&quot;er&quot;,&quot;ett&quot;,&quot;rftt&quot;]
<strong>输出：</strong>&quot;wertf&quot;
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = [&quot;z&quot;,&quot;x&quot;]
<strong>输出：</strong>&quot;zx&quot;
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>words = [&quot;z&quot;,&quot;x&quot;,&quot;z&quot;]
<strong>输出：</strong>&quot;&quot;
<strong>解释：</strong>不存在合法字母顺序，因此返回 &quot;&quot; 。
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 100</code></li>
	<li><code>words[i]</code> 仅由小写英文字母组成</li>
</ul>



<p><meta charset="UTF-8" />注意：本题与主站 269&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/alien-dictionary/">https://leetcode.cn/problems/alien-dictionary/</a></p>

## 解法

**方法一：拓扑排序 + BFS**

用数组 $g$ 记录在火星字典中的字母先后关系，$g[i][j] = true$ 表示字母 $i + 'a'$ 在字母 $j + 'a'$ 的前面；用数组 $s$ 记录当前字典出现过的字母，$cnt$ 表示出现过的字母数。

一个很简单的想法是遍历每一个单词，比较该单词和其后的所有单词，把所有的先后关系更新进数组 $g$，这样遍历时间复杂度为 $O(n^3)$；但是我们发现其实比较相邻的两个单词就可以了，比如 $a < b < c$ 则比较 $a < b$ 和 $b < c$， $a$ 和 $c$ 的关系便确定了。因此算法可以优化成比较相邻两个单词，时间复杂度为 $O(n^2)$。

出现矛盾的情况：

-   $g[i][j]$ = $g[j][i]$ = $true$；
-   后一个单词是前一个单词的前缀；
-   在拓扑排序后 $ans$ 的长度小于统计到的字母个数。

拓扑排序：

-   统计所有出现的字母入度；
-   将所有入度为 $0$ 的字母加入队列；
-   当队列不空，出队并更新其他字母的入度，入度为 $0$ 则入队，同时出队时将当前字母加入 $ans$ 的结尾；
-   得到的便是字母的拓扑序，也就是火星字典的字母顺序。

### **Java**

```java
class Solution {

    public String alienOrder(String[] words) {
        boolean[][] g = new boolean[26][26];
        boolean[] s = new boolean[26];
        int cnt = 0;
        int n = words.length;
        for (int i = 0; i < n - 1; ++i) {
            for (char c : words[i].toCharArray()) {
                if (cnt == 26) {
                    break;
                }
                c -= 'a';
                if (!s[c]) {
                    ++cnt;
                    s[c] = true;
                }
            }
            int m = words[i].length();
            for (int j = 0; j < m; ++j) {
                if (j >= words[i + 1].length()) {
                    return "";
                }
                char c1 = words[i].charAt(j), c2 = words[i + 1].charAt(j);
                if (c1 == c2) {
                    continue;
                }
                if (g[c2 - 'a'][c1 - 'a']) {
                    return "";
                }
                g[c1 - 'a'][c2 - 'a'] = true;
                break;
            }
        }
        for (char c : words[n - 1].toCharArray()) {
            if (cnt == 26) {
                break;
            }
            c -= 'a';
            if (!s[c]) {
                ++cnt;
                s[c] = true;
            }
        }

        int[] indegree = new int[26];
        for (int i = 0; i < 26; ++i) {
            for (int j = 0; j < 26; ++j) {
                if (i != j && s[i] && s[j] && g[i][j]) {
                    ++indegree[j];
                }
            }
        }
        Deque<Integer> q = new LinkedList<>();
        for (int i = 0; i < 26; ++i) {
            if (s[i] && indegree[i] == 0) {
                q.offerLast(i);
            }
        }
        StringBuilder ans = new StringBuilder();
        while (!q.isEmpty()) {
            int t = q.pollFirst();
            ans.append((char) (t + 'a'));
            for (int i = 0; i < 26; ++i) {
                if (i != t && s[i] && g[t][i]) {
                    if (--indegree[i] == 0) {
                        q.offerLast(i);
                    }
                }
            }
        }
        return ans.length() < cnt ? "" : ans.toString();
    }
}
```
