# [68. 文本左右对齐](https://leetcode.cn/problems/text-justification)

[English Version](/solution/0000-0099/0068.Text%20Justification/README_EN.md)

## 题目描述

<p>给定一个单词数组&nbsp;<code>words</code> 和一个长度&nbsp;<code>maxWidth</code>&nbsp;，重新排版单词，使其成为每行恰好有&nbsp;<code>maxWidth</code>&nbsp;个字符，且左右两端对齐的文本。</p>

<p>你应该使用 “<strong>贪心算法</strong>” 来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格&nbsp;<code>' '</code>&nbsp;填充，使得每行恰好有 <em>maxWidth</em>&nbsp;个字符。</p>

<p>要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。</p>

<p>文本的最后一行应为左对齐，且单词之间不插入<strong>额外的</strong>空格。</p>

<p><strong>注意:</strong></p>

<ul>
	<li>单词是指由非空格字符组成的字符序列。</li>
	<li>每个单词的长度大于 0，小于等于&nbsp;<em>maxWidth</em>。</li>
	<li>输入单词数组 <code>words</code>&nbsp;至少包含一个单词。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入: </strong>words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
<strong>输出:</strong>
[
&nbsp; &nbsp;"This &nbsp; &nbsp;is &nbsp; &nbsp;an",
&nbsp; &nbsp;"example &nbsp;of text",
&nbsp; &nbsp;"justification. &nbsp;"
]
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入:</strong>words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
<strong>输出:</strong>
[
&nbsp; "What &nbsp; must &nbsp; be",
&nbsp; "acknowledgment &nbsp;",
&nbsp; "shall be &nbsp; &nbsp; &nbsp; &nbsp;"
]
<strong>解释: </strong>注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
&nbsp;    因为最后一行应为左对齐，而不是左右两端对齐。       
     第二行同样为左对齐，这是因为这行只包含一个单词。
</pre>

<p><strong>示例&nbsp;3:</strong></p>

<pre>
<strong>输入:</strong>words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"]，maxWidth = 20
<strong>输出:</strong>
[
&nbsp; "Science &nbsp;is &nbsp;what we",
  "understand &nbsp; &nbsp; &nbsp;well",
&nbsp; "enough to explain to",
&nbsp; "a &nbsp;computer. &nbsp;Art is",
&nbsp; "everything &nbsp;else &nbsp;we",
&nbsp; "do &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;"
]
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 300</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 20</code></li>
	<li><code>words[i]</code>&nbsp;由小写英文字母和符号组成</li>
	<li><code>1 &lt;= maxWidth &lt;= 100</code></li>
	<li><code>words[i].length &lt;= maxWidth</code></li>
</ul>

## 解法

**方法一：模拟**

根据题意模拟即可，注意，如果是最后一行，或者这一行只有一个单词，那么要左对齐，否则要均匀分配空格。

时间复杂度 $O(L)$，空间复杂度 $O(L)$。其中 $L$ 为所有单词的长度之和。

### **Java**

```java
class Solution {
    /*
        首先，将单词数组中的每个单词按顺序加入到当前行中，直到超过了 maxWidth 的长度。
        然后，根据单词数组中剩余的单词数量和需要填充的空格数量来计算每个单词之间需要填充的平均空格数和额外需要填充的空格数。
        最后，将当前行按照左对齐或均匀分配空格的方式进行格式化。
         */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int left = 0; // 当前行的第一个单词在 words 数组中的下标
        while (left < words.length) {
            int right = left; // 当前行最后一个单词在 words 数组中的下标
            int lineLength = words[right].length(); // 当前行所有单词（含空格）的总长度
            while (right + 1 < words.length && lineLength + words[right + 1].length() + 1 <= maxWidth) {
                // 直到把下一个单词添加到当前行时行长度超过 maxWidth
                right++;
                lineLength += words[right].length() + 1; // 加上新单词的长度和一个空格的长度
            }
            // 计算空格的数量和单词之间的平均空格数
            int spaces = maxWidth - lineLength;
            int spaceCount = right - left; // 单词之间的空格数量
            String line = words[left];
            if (right == words.length - 1) { // 最后一行，单词之间不需要填充额外的空格
                for (int i = left + 1; i <= right; i++) {
                    line += " " + words[i];
                }
                for (int i = line.length(); i < maxWidth; i++) {
                    line += " ";
                }
            } else if (spaceCount == 0) { // 当前行只有一个单词
                for (int i = line.length(); i < maxWidth; i++) {
                    line += " ";
                }
            } else {
                int averageSpaceCount = spaces / spaceCount; // 单词之间平均需要填充的空格数
                int extraSpaceCount = spaces % spaceCount; // 需要额外填充的空格数
                for (int i = left + 1; i <= right; i++) {
                    int spaceLength = averageSpaceCount + (extraSpaceCount-- > 0 ? 1 : 0);
                    for (int j = 0; j < spaceLength; j++) {
                        line += " ";
                    }
                    line += words[i];
                }
            }
            result.add(line);
            left = right + 1; // 处理下一行
        }
        return result;
    }
}
```
