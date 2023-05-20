# [1258. 近义词句子](https://leetcode.cn/problems/synonymous-sentences)

## 题目描述

<p>给你一个近义词表&nbsp;<code>synonyms</code> 和一个句子&nbsp;<code>text</code>&nbsp;，&nbsp;<code>synonyms</code> 表中是一些近义词对 ，你可以将句子&nbsp;<code>text</code> 中每个单词用它的近义词来替换。</p>

<p>请你找出所有用近义词替换后的句子，按&nbsp;<strong>字典序排序</strong>&nbsp;后返回。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：
</strong>synonyms = [[&quot;happy&quot;,&quot;joy&quot;],[&quot;sad&quot;,&quot;sorrow&quot;],[&quot;joy&quot;,&quot;cheerful&quot;]],
text = &quot;I am happy today but was sad yesterday&quot;
<strong>输出：
</strong>[&quot;I am cheerful today but was sad yesterday&quot;,
&quot;I am cheerful today but was sorrow yesterday&quot;,
&quot;I am happy today but was sad yesterday&quot;,
&quot;I am happy today but was sorrow yesterday&quot;,
&quot;I am joy today but was sad yesterday&quot;,
&quot;I am joy today but was sorrow yesterday&quot;]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;=&nbsp;synonyms.length &lt;= 10</code></li>
	<li><code>synonyms[i].length == 2</code></li>
	<li><code>synonyms[0] != synonyms[1]</code></li>
	<li>所有单词仅包含英文字母，且长度最多为&nbsp;<code>10</code> 。</li>
	<li><code>text</code>&nbsp;最多包含&nbsp;<code>10</code> 个单词，且单词间用单个空格分隔开。</li>
</ul>

## 解法

**方法一：并查集 + DFS**

我们可以发现，题目中的近义词是可以传递的，即如果 `a` 和 `b` 是近义词，`b` 和 `c` 是近义词，那么 `a` 和 `c` 也是近义词。因此，我们可以用并查集找出近义词的连通分量，每个连通分量中的单词都是近义词，并且按字典序从小到大排列。

接下来，我们将字符串 `text` 按空格分割成单词数组 `sentence`，对于每个单词 `sentence[i]`，如果它是近义词，那么我们就将它替换成连通分量中的所有单词，否则不替换。这样，我们就可以得到所有的句子。这可以通过 DFS 搜索实现。

我们设计一个函数 $dfs(i)$，表示从 `sentence` 的第 $i$ 个单词开始，将其替换成连通分量中的所有单词，然后递归地处理后面的单词。

如果 $i$ 大于等于 `sentence` 的长度，那么说明我们已经处理完了所有的单词，此时将当前的句子加入答案数组中。否则，如果 `sentence[i]` 不是近义词，那么我们不替换它，直接将它加入当前的句子中，然后递归地处理后面的单词。否则，我们将 `sentence[i]` 替换成连通分量中的所有单词，同样递归地处理后面的单词。

最后，返回答案数组即可。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 是单词的数量。

### **Java**

```java
class UnionFind {
    private int[] p;
    private int[] size;

    public UnionFind(int n) {
        p = new int[n];
        size = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
            size[i] = 1;
        }
    }

    public int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    public void union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa != pb) {
            if (size[pa] > size[pb]) {
                p[pb] = pa;
                size[pa] += size[pb];
            } else {
                p[pa] = pb;
                size[pb] += size[pa];
            }
        }
    }
}

class Solution {
    private List<String> ans = new ArrayList<>();
    private List<String> t = new ArrayList<>();
    private List<String> words;
    private Map<String, Integer> d;
    private UnionFind uf;
    private List<Integer>[] g;
    private String[] sentence;

    public List<String> generateSentences(List<List<String>> synonyms, String text) {
        Set<String> ss = new HashSet<>();
        for (List<String> pairs : synonyms) {
            ss.addAll(pairs);
        }
        words = new ArrayList<>(ss);
        int n = words.size();
        d = new HashMap<>(n);
        for (int i = 0; i < words.size(); ++i) {
            d.put(words.get(i), i);
        }
        uf = new UnionFind(n);
        for (List<String> pairs : synonyms) {
            uf.union(d.get(pairs.get(0)), d.get(pairs.get(1)));
        }
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 0; i < n; ++i) {
            g[uf.find(i)].add(i);
        }
        for (List<Integer> e : g) {
            e.sort((i, j) -> words.get(i).compareTo(words.get(j)));
        }
        sentence = text.split(" ");
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i >= sentence.length) {
            ans.add(String.join(" ", t));
            return;
        }
        if (!d.containsKey(sentence[i])) {
            t.add(sentence[i]);
            dfs(i + 1);
            t.remove(t.size() - 1);
        } else {
            for (int j : g[uf.find(d.get(sentence[i]))]) {
                t.add(words.get(j));
                dfs(i + 1);
                t.remove(t.size() - 1);
            }
        }
    }
}
```
