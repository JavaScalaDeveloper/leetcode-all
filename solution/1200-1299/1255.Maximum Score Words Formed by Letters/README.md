# [1255. 得分最高的单词集合](https://leetcode.cn/problems/maximum-score-words-formed-by-letters)

## 题目描述

<p>你将会得到一份单词表&nbsp;<code>words</code>，一个字母表&nbsp;<code>letters</code>&nbsp;（可能会有重复字母），以及每个字母对应的得分情况表&nbsp;<code>score</code>。</p>

<p>请你帮忙计算玩家在单词拼写游戏中所能获得的「最高得分」：能够由&nbsp;<code>letters</code>&nbsp;里的字母拼写出的&nbsp;<strong>任意</strong>&nbsp;属于 <code>words</code>&nbsp;单词子集中，分数最高的单词集合的得分。</p>

<p>单词拼写游戏的规则概述如下：</p>

<ul>
	<li>玩家需要用字母表&nbsp;<code>letters</code> 里的字母来拼写单词表&nbsp;<code>words</code>&nbsp;中的单词。</li>
	<li>可以只使用字母表&nbsp;<code>letters</code> 中的部分字母，但是每个字母最多被使用一次。</li>
	<li>单词表 <code>words</code>&nbsp;中每个单词只能计分（使用）一次。</li>
	<li>根据字母得分情况表<code>score</code>，字母 <code>&#39;a&#39;</code>,&nbsp;<code>&#39;b&#39;</code>,&nbsp;<code>&#39;c&#39;</code>, ... ,&nbsp;<code>&#39;z&#39;</code> 对应的得分分别为 <code>score[0]</code>, <code>score[1]</code>,&nbsp;...,&nbsp;<code>score[25]</code>。</li>
	<li>本场游戏的「得分」是指：玩家所拼写出的单词集合里包含的所有字母的得分之和。</li>
</ul>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>words = [&quot;dog&quot;,&quot;cat&quot;,&quot;dad&quot;,&quot;good&quot;], letters = [&quot;a&quot;,&quot;a&quot;,&quot;c&quot;,&quot;d&quot;,&quot;d&quot;,&quot;d&quot;,&quot;g&quot;,&quot;o&quot;,&quot;o&quot;], score = [1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0]
<strong>输出：</strong>23
<strong>解释：</strong>
字母得分为  a=1, c=9, d=5, g=3, o=2
使用给定的字母表 letters，我们可以拼写单词 &quot;dad&quot; (5+1+5)和 &quot;good&quot; (3+2+2+5)，得分为 23 。
而单词 &quot;dad&quot; 和 &quot;dog&quot; 只能得到 21 分。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>words = [&quot;xxxz&quot;,&quot;ax&quot;,&quot;bx&quot;,&quot;cx&quot;], letters = [&quot;z&quot;,&quot;a&quot;,&quot;b&quot;,&quot;c&quot;,&quot;x&quot;,&quot;x&quot;,&quot;x&quot;], score = [4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,10]
<strong>输出：</strong>27
<strong>解释：</strong>
字母得分为  a=4, b=4, c=4, x=5, z=10
使用给定的字母表 letters，我们可以组成单词 &quot;ax&quot; (4+5)， &quot;bx&quot; (4+5) 和 &quot;cx&quot; (4+5) ，总得分为 27 。
单词 &quot;xxxz&quot; 的得分仅为 25 。</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>words = [&quot;leetcode&quot;], letters = [&quot;l&quot;,&quot;e&quot;,&quot;t&quot;,&quot;c&quot;,&quot;o&quot;,&quot;d&quot;], score = [0,0,1,1,1,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,0,0,0,0,0]
<strong>输出：</strong>0
<strong>解释：</strong>
字母 &quot;e&quot; 在字母表 letters 中只出现了一次，所以无法组成单词表 words 中的单词。</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 14</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 15</code></li>
	<li><code>1 &lt;= letters.length &lt;= 100</code></li>
	<li><code>letters[i].length == 1</code></li>
	<li><code>score.length ==&nbsp;26</code></li>
	<li><code>0 &lt;= score[i] &lt;= 10</code></li>
	<li><code>words[i]</code>&nbsp;和&nbsp;<code>letters[i]</code>&nbsp;只包含小写的英文字母。</li>
</ul>

## 解法

**方法一：二进制枚举**

我们注意到题目的数据范围不大，因此对于给定的单词表，我们可以使用二进制枚举的方法，枚举出所有的单词组合，然后判断每个单词组合是否满足题目要求，如果满足则计算其得分，最后取得分最大的单词组合。

我们首先用哈希表或数组cnt记录字母表letters中每个字母出现的次数。

接下来，我们使用二进制枚举的方法，枚举出所有的单词组合。二进制的每一位表示单词表中的每一个单词是否被选中，如果第i位为1，则表示第i个单词被选中，否则表示第i个单词没有被选中。

然后我们统计当前单词组合中每个字母出现的次数，记录在哈希表或数组cur中。如果cur中的每个字母的出现次数都不大于cnt中的对应字母的出现次数，则说明当前单词组合满足题目要求，我们计算当前单词组合的得分，取得分最大的单词组合。

时间复杂度(2^n × n × M)，空间复杂度O(C)。其中n和M分别为单词集合中单词的个数和单词的最大长度；而C为字母表中字母的个数，本题中C=26。

### **Java**

```java
class Solution {
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int[] cnt = new int[26];
        for (int i = 0; i < letters.length; ++i) {
            cnt[letters[i] - 'a']++;
        }
        int n = words.length;
        int ans = 0;
        for (int i = 0; i < 1 << n; ++i) {
            int[] cur = new int[26];
            for (int j = 0; j < n; ++j) {
                if (((i >> j) & 1) == 1) {
                    for (int k = 0; k < words[j].length(); ++k) {
                        cur[words[j].charAt(k) - 'a']++;
                    }
                }
            }
            boolean ok = true;
            int t = 0;
            for (int j = 0; j < 26; ++j) {
                if (cur[j] > cnt[j]) {
                    ok = false;
                    break;
                }
                t += cur[j] * score[j];
            }
            if (ok && ans < t) {
                ans = t;
            }
        }
        return ans;
    }
}
```
