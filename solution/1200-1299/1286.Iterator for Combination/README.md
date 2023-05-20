# [1286. 字母组合迭代器](https://leetcode.cn/problems/iterator-for-combination)

## 题目描述

<p>请你设计一个迭代器类&nbsp;<code>CombinationIterator</code>&nbsp;，包括以下内容：</p>

<ul>
	<li><code>CombinationIterator(string characters, int combinationLength)</code>&nbsp;一个构造函数，输入参数包括：用一个&nbsp;<strong>有序且字符唯一&nbsp;</strong>的字符串&nbsp;<code>characters</code>（该字符串只包含小写英文字母）和一个数字&nbsp;<code>combinationLength</code>&nbsp;。</li>
	<li>函数&nbsp;<em><code>next()</code>&nbsp;</em>，按&nbsp;<strong>字典序&nbsp;</strong>返回长度为&nbsp;<code>combinationLength</code> 的下一个字母组合。</li>
	<li>函数&nbsp;<em><code>hasNext()</code>&nbsp;</em>，只有存在长度为&nbsp;<code>combinationLength</code> 的下一个字母组合时，才返回&nbsp;<code>true</code></li>
</ul>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong>
["CombinationIterator", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
[["abc", 2], [], [], [], [], [], []]
<strong>输出：</strong>
[null, "ab", true, "ac", true, "bc", false]
<strong>解释：
</strong>CombinationIterator iterator = new CombinationIterator("abc", 2); // 创建迭代器 iterator
iterator.next(); // 返回 "ab"
iterator.hasNext(); // 返回 true
iterator.next(); // 返回 "ac"
iterator.hasNext(); // 返回 true
iterator.next(); // 返回 "bc"
iterator.hasNext(); // 返回 false
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= combinationLength &lt;=&nbsp;characters.length &lt;= 15</code></li>
	<li>&nbsp;<code>characters</code>&nbsp;中每个字符都 <strong>不同</strong></li>
	<li>每组测试数据最多对&nbsp;<code>next</code>&nbsp;和&nbsp;<code>hasNext</code>&nbsp;调用&nbsp;<code>10<sup>4</sup></code>次</li>
	<li>题目保证每次调用函数&nbsp;<code>next</code>&nbsp;时都存在下一个字母组合。</li>
</ul>

## 解法

**方法一：DFS 回溯**

我们通过 $DFS$ 枚举，预处理生成所有长度为 $combinationLength$ 的字符串，存放到 $cs$ 数组中。

**方法二：二进制编码**

我们看个例子，对于 $abcd$，若 $combinationLength$ 为 2，则 $cs$ 就是 $ab, ac, ad, bc, bd, cd, ...$。

对应的二进制数为：

```
1100
1010
1001
0110
0101
0011
...
```

观察到上述规律后，我们依次按照二进制编码从大到小的规律，将所有字符串依次求出。

所谓的长度 $combinationLength$，只需要满足二进制编码中 $1$ 的个数满足要求即可。

### **Java**

```java
class CombinationIterator {
    private int n;
    private int combinationLength;
    private String characters;
    private StringBuilder t = new StringBuilder();
    private List<String> cs = new ArrayList<>();
    private int idx = 0;

    public CombinationIterator(String characters, int combinationLength) {
        n = characters.length();
        this.combinationLength = combinationLength;
        this.characters = characters;
        dfs(0);
    }

    public String next() {
        return cs.get(idx++);
    }

    public boolean hasNext() {
        return idx < cs.size();
    }

    private void dfs(int i) {
        if (t.length() == combinationLength) {
            cs.add(t.toString());
            return;
        }
        if (i == n) {
            return;
        }
        t.append(characters.charAt(i));
        dfs(i + 1);
        t.deleteCharAt(t.length() - 1);
        dfs(i + 1);
    }
}

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * CombinationIterator obj = new CombinationIterator(characters, combinationLength);
 * String param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
```

```java
class CombinationIterator {
    private int curr;
    private int size;
    private char[] cs;

    public CombinationIterator(String characters, int combinationLength) {
        int n = characters.length();
        curr = (1 << n) - 1;
        size = combinationLength;
        cs = new char[n];
        for (int i = 0; i < n; ++i) {
            cs[i] = characters.charAt(n - i - 1);
        }
    }

    public String next() {
        while (curr >= 0 && Integer.bitCount(curr) != size) {
            --curr;
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < cs.length; ++i) {
            if (((curr >> i) & 1) == 1) {
                ans.append(cs[i]);
            }
        }
        --curr;
        return ans.reverse().toString();
    }

    public boolean hasNext() {
        while (curr >= 0 && Integer.bitCount(curr) != size) {
            --curr;
        }
        return curr >= 0;
    }
}

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * CombinationIterator obj = new CombinationIterator(characters, combinationLength);
 * String param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
```
