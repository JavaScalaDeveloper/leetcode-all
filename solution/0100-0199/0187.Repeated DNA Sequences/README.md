# [187. 重复的 DNA 序列](https://leetcode.cn/problems/repeated-dna-sequences)

## 题目描述

<p><strong>DNA序列</strong>&nbsp;由一系列核苷酸组成，缩写为<meta charset="UTF-8" />&nbsp;<code>'A'</code>,&nbsp;<code>'C'</code>,&nbsp;<code>'G'</code>&nbsp;和<meta charset="UTF-8" />&nbsp;<code>'T'</code>.。</p>

<ul>
	<li>例如，<meta charset="UTF-8" /><code>"ACGAATTCCG"</code>&nbsp;是一个 <strong>DNA序列</strong> 。</li>
</ul>

<p>在研究 <strong>DNA</strong> 时，识别 DNA 中的重复序列非常有用。</p>

<p>给定一个表示 <strong>DNA序列</strong> 的字符串 <code>s</code> ，返回所有在 DNA 分子中出现不止一次的&nbsp;<strong>长度为&nbsp;<code>10</code></strong>&nbsp;的序列(子字符串)。你可以按 <strong>任意顺序</strong> 返回答案。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
<strong>输出：</strong>["AAAAACCCCC","CCCCCAAAAA"]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "AAAAAAAAAAAAA"
<strong>输出：</strong>["AAAAAAAAAA"]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code><code>==</code><code>'A'</code>、<code>'C'</code>、<code>'G'</code>&nbsp;or&nbsp;<code>'T'</code></li>
</ul>

## 解法

**方法一：哈希表**

朴素解法，用哈希表保存所有长度为 10 的子序列出现的次数，当子序列出现次数大于 1 时，把该子序列作为结果之一。

假设字符串 `s` 长度为 `n`，则时间复杂度O(n × 10)，空间复杂度O(n)。

**方法二：Rabin-Karp 字符串匹配算法**

本质上是滑动窗口和哈希的结合方法，和 [0028.找出字符串中第一个匹配项的下标](https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/) 类似，本题可以借助哈希函数将子序列计数的时间复杂度降低到O(1)。

假设字符串 `s` 长度为 `n`，则时间复杂度为O(n)，空间复杂度O(n)。

### **Java**

```java
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        int n = s.length() - 10;
        Map<String, Integer> cnt = new HashMap<>();
        List<String> ans = new ArrayList<>();
        for (int i = 0; i <= n; ++i) {
            String sub = s.substring(i, i + 10);
            cnt.put(sub, cnt.getOrDefault(sub, 0) + 1);
            if (cnt.get(sub) == 2) {
                ans.add(sub);
            }
        }
        return ans;
    }
}
```

哈希表：

Rabin-Karp:
