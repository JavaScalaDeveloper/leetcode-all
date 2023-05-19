# [面试题 16.02. 单词频率](https://leetcode.cn/problems/words-frequency-lcci)

[English Version](/lcci/16.02.Words%20Frequency/README_EN.md)

## 题目描述


<p>设计一个方法，找出任意指定单词在一本书中的出现频率。</p>
<p>你的实现应该支持如下操作：</p>
<ul>
<li><code>WordsFrequency(book)</code>构造函数，参数为字符串数组构成的一本书</li>
<li><code>get(word)</code>查询指定单词在数中出现的频率</li>
</ul>
<p><strong>示例：</strong></p>
<pre>WordsFrequency wordsFrequency = new WordsFrequency({"i", "have", "an", "apple", "he", "have", "a", "pen"});
wordsFrequency.get("you"); //返回0，"you"没有出现过
wordsFrequency.get("have"); //返回2，"have"出现2次
wordsFrequency.get("an"); //返回1
wordsFrequency.get("apple"); //返回1
wordsFrequency.get("pen"); //返回1
</pre>
<p><strong>提示：</strong></p>
<ul>
<li><code>book[i]</code>中只包含小写字母</li>
<li><code>1 <= book.length <= 100000</code></li>
<li><code>1 <= book[i].length <= 10</code></li>
<li><code>get</code>函数的调用次数不会超过100000</li>
</ul>

## 解法

**方法一：哈希表**

我们用哈希表 `cnt` 统计每个单词出现的次数，`get` 函数直接返回 `cnt[word]` 即可。

初始化哈希表 `cnt` 的时间复杂度为 $O(n)$，其中 $n$ 为 `book` 的长度。`get` 函数的时间复杂度为 $O(1)$。空间复杂度为 $O(n)$。

### **Java**

```java
class WordsFrequency {
    private Map<String, Integer> cnt = new HashMap<>();

    public WordsFrequency(String[] book) {
        for (String x : book) {
            cnt.merge(x, 1, Integer::sum);
        }
    }

    public int get(String word) {
        return cnt.getOrDefault(word, 0);
    }
}

/**
 * Your WordsFrequency object will be instantiated and called as such:
 * WordsFrequency obj = new WordsFrequency(book);
 * int param_1 = obj.get(word);
 */
```
