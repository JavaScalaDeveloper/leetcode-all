# [1940. 排序数组之间的最长公共子序列](https://leetcode.cn/problems/longest-common-subsequence-between-sorted-arrays)

## 题目描述

<p>给定一个由整数数组组成的数组<code>arrays</code>，其中<code>arrays[i]</code>是严格递增排序的，返回一个表示所有数组之间的最长公共子序列的整数数组。</p>

<p>子序列是从另一个序列派生出来的序列，删除一些元素或不删除任何元素，而不改变其余元素的顺序。</p>

<p><strong>示例1:</strong></p>

<pre>
<strong>输入:</strong> arrays = [[<strong><em>1</em></strong>,3,<strong><em>4</em></strong>],
               [<strong><em>1</em></strong>,<strong><em>4</em></strong>,7,9]]
<strong>输出:</strong> [1,4]
<strong>解释:</strong> 这两个数组中的最长子序列是[1,4]。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> arrays = [[<strong><em>2</em></strong>,<strong><em>3</em></strong>,<strong><em>6</em></strong>,8],
               [1,<strong><em>2</em></strong>,<strong><em>3</em></strong>,5,<strong><em>6</em></strong>,7,10],
               [<strong><em>2</em></strong>,<strong><em>3</em></strong>,4,<em><strong>6</strong></em>,9]]
<strong>输出:</strong> [2,3,6]
<strong>解释:</strong> 这三个数组中的最长子序列是[2,3,6]。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> arrays = [[1,2,3,4,5],
               [6,7,8]]
<strong>输出:</strong> []
<strong>解释:</strong> 这两个数组之间没有公共子序列。
</pre>

<p> </p>

<p><strong>限制条件:</strong></p>

<ul>
	<li><code>2 <= arrays.length <= 100</code></li>
	<li><code>1 <= arrays[i].length <= 100</code></li>
	<li><code>1 <= arrays[i][j] <= 100</code></li>
	<li><code>arrays[i]</code> 是严格递增排序.</li>
</ul>

## 解法

计数器或者双指针实现。

### **Java**

```java
class Solution {
    public List<Integer> longestCommomSubsequence(int[][] arrays) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int[] array : arrays) {
            for (int e : array) {
                counter.put(e, counter.getOrDefault(e, 0) + 1);
            }
        }
        int n = arrays.length;
        List<Integer> res = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            if (entry.getValue() == n) {
                res.add(entry.getKey());
            }
        }
        return res;
    }
}
```
