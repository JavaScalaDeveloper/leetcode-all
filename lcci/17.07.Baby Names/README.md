# [面试题 17.07. 婴儿名字](https://leetcode.cn/problems/baby-names-lcci)

[English Version](/lcci/17.07.Baby%20Names/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>每年，政府都会公布一万个最常见的婴儿名字和它们出现的频率，也就是同名婴儿的数量。有些名字有多种拼法，例如，John 和 Jon 本质上是相同的名字，但被当成了两个名字公布出来。给定两个列表，一个是名字及对应的频率，另一个是本质相同的名字对。设计一个算法打印出每个真实名字的实际频率。注意，如果 John 和 Jon 是相同的，并且 Jon 和 Johnny 相同，则 John 与 Johnny 也相同，即它们有传递和对称性。</p>

<p>在结果列表中，选择<strong>字典序最小</strong>的名字作为真实名字。</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>names = [&quot;John(15)&quot;,&quot;Jon(12)&quot;,&quot;Chris(13)&quot;,&quot;Kris(4)&quot;,&quot;Christopher(19)&quot;], synonyms = [&quot;(Jon,John)&quot;,&quot;(John,Johnny)&quot;,&quot;(Chris,Kris)&quot;,&quot;(Chris,Christopher)&quot;]
<strong>输出：</strong>[&quot;John(27)&quot;,&quot;Chris(36)&quot;]</pre>

<p>提示：</p>

<ul>
	<li><code>names.length &lt;= 100000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表 + DFS**

对于每个同义词对，我们将其两个名字建立双向边，存放在邻接表 $g$ 中，然后，我们遍历所有名字，将其存放在集合 $s$ 中，同时将其频率存放在哈希表 $cnt$ 中。

接下来，我们遍历集合 $s$ 中的每个名字，如果该名字未被访问过，则进行深度优先搜索，找到该名字所在的连通分量中的所有名字，以字典序最小的名字为真实名字，将其频率求和，即为真实名字的频率。然后，我们将该名字以及其频率存放在答案数组中。

遍历完所有名字后，答案数组即为所求。

时间复杂度 $O(n + m)$，空间复杂度 $O(n + m)$。其中 $n$ 和 $m$ 分别为名字数组和同义词数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->



### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private Map<String, List<String>> g = new HashMap<>();
    private Map<String, Integer> cnt = new HashMap<>();
    private Set<String> vis = new HashSet<>();
    private int freq;

    public String[] trulyMostPopular(String[] names, String[] synonyms) {
        for (String pairs : synonyms) {
            String[] pair = pairs.substring(1, pairs.length() - 1).split(",");
            String a = pair[0], b = pair[1];
            g.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
            g.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
        }
        Set<String> s = new HashSet<>();
        for (String x : names) {
            int i = x.indexOf('(');
            String name = x.substring(0, i);
            s.add(name);
            cnt.put(name, Integer.parseInt(x.substring(i + 1, x.length() - 1)));
        }
        List<String> res = new ArrayList<>();
        for (String name : s) {
            if (!vis.contains(name)) {
                freq = 0;
                name = dfs(name);
                res.add(name + "(" + freq + ")");
            }
        }
        return res.toArray(new String[0]);
    }

    private String dfs(String a) {
        String mi = a;
        vis.add(a);
        freq += cnt.getOrDefault(a, 0);
        for (String b : g.getOrDefault(a, new ArrayList<>())) {
            if (!vis.contains(b)) {
                String t = dfs(b);
                if (t.compareTo(mi) < 0) {
                    mi = t;
                }
            }
        }
        return mi;
    }
}
```









### **TypeScript**



### **...**

```

```


