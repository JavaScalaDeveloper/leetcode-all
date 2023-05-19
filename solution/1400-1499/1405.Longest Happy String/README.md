# [1405. 最长快乐字符串](https://leetcode.cn/problems/longest-happy-string)

[English Version](/solution/1400-1499/1405.Longest%20Happy%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>如果字符串中不含有任何 <code>&#39;aaa&#39;</code>，<code>&#39;bbb&#39;</code> 或 <code>&#39;ccc&#39;</code> 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。</p>

<p>给你三个整数 <code>a</code>，<code>b</code> ，<code>c</code>，请你返回 <strong>任意一个</strong> 满足下列全部条件的字符串 <code>s</code>：</p>

<ul>
	<li><code>s</code> 是一个尽可能长的快乐字符串。</li>
	<li><code>s</code> 中 <strong>最多</strong> 有<code>a</code> 个字母 <code>&#39;a&#39;</code>、<code>b</code>&nbsp;个字母 <code>&#39;b&#39;</code>、<code>c</code> 个字母 <code>&#39;c&#39;</code> 。</li>
	<li><code>s </code>中只含有 <code>&#39;a&#39;</code>、<code>&#39;b&#39;</code> 、<code>&#39;c&#39;</code> 三种字母。</li>
</ul>

<p>如果不存在这样的字符串 <code>s</code> ，请返回一个空字符串 <code>&quot;&quot;</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>a = 1, b = 1, c = 7
<strong>输出：</strong>&quot;ccaccbcc&quot;
<strong>解释：</strong>&quot;ccbccacc&quot; 也是一种正确答案。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>a = 2, b = 2, c = 1
<strong>输出：</strong>&quot;aabbc&quot;
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>a = 7, b = 1, c = 0
<strong>输出：</strong>&quot;aabaa&quot;
<strong>解释：</strong>这是该测试用例的唯一正确答案。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= a, b, c &lt;= 100</code></li>
	<li><code>a + b + c &gt; 0</code></li>
</ul>

## 解法

贪心，优先选择剩余最多的字符，通过优先队列或排序，确保每次选到的字符都是剩余最多的（为了避免出现连续 3 个一样的字符，一些情况需要选择剩余第二多的字符）

### **Java**

```java
class Solution {
    public String longestDiverseString(int a, int b, int c) {
        Queue<int[]> pq = new PriorityQueue<>((x, y) -> y[1] - x[1]);
        if (a > 0) {
            pq.offer(new int[] {'a', a});
        }
        if (b > 0) {
            pq.offer(new int[] {'b', b});
        }
        if (c > 0) {
            pq.offer(new int[] {'c', c});
        }

        StringBuilder sb = new StringBuilder();
        while (pq.size() > 0) {
            int[] cur = pq.poll();
            int n = sb.length();
            if (n >= 2 && sb.codePointAt(n - 1) == cur[0] && sb.codePointAt(n - 2) == cur[0]) {
                if (pq.size() == 0) {
                    break;
                }
                int[] next = pq.poll();
                sb.append((char) next[0]);
                if (next[1] > 1) {
                    next[1]--;
                    pq.offer(next);
                }
                pq.offer(cur);
            } else {
                sb.append((char) cur[0]);
                if (cur[1] > 1) {
                    cur[1]--;
                    pq.offer(cur);
                }
            }
        }

        return sb.toString();
    }
}
```

### **TypeScript**
