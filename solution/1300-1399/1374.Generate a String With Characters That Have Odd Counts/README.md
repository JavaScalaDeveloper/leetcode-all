# [1374. 生成每种字符都是奇数个的字符串](https://leetcode.cn/problems/generate-a-string-with-characters-that-have-odd-counts)

## 题目描述

<p>给你一个整数 <code>n</code>，请你返回一个含<em> <code>n</code> </em>个字符的字符串，其中每种字符在该字符串中都恰好出现 <strong>奇数次</strong> <em><strong>。</strong></em></p>

<p>返回的字符串必须只含小写英文字母。如果存在多个满足题目要求的字符串，则返回其中任意一个即可。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 4
<strong>输出：</strong>&quot;pppz&quot;
<strong>解释：</strong>&quot;pppz&quot; 是一个满足题目要求的字符串，因为 &#39;p&#39; 出现 3 次，且 &#39;z&#39; 出现 1 次。当然，还有很多其他字符串也满足题目要求，比如：&quot;ohhh&quot; 和 &quot;love&quot;。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 2
<strong>输出：</strong>&quot;xy&quot;
<strong>解释：</strong>&quot;xy&quot; 是一个满足题目要求的字符串，因为 &#39;x&#39; 和 &#39;y&#39; 各出现 1 次。当然，还有很多其他字符串也满足题目要求，比如：&quot;ag&quot; 和 &quot;ur&quot;。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = 7
<strong>输出：</strong>&quot;holasss&quot;
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 500</code></li>
</ul>

## 解法

**方法一：构造**

如果n为奇数，那么直接构造n个 `'a'` 即可。

如果n为偶数，那么构造n-1个 `'a'` 和1个 `'b'` 即可。

时间复杂度O(n)，空间复杂度O(n)。其中n为字符串长度。

### **Java**

```java
class Solution {
    public String generateTheString(int n) {
        return (n % 2 == 1) ? "a".repeat(n) : "a".repeat(n - 1) + "b";
    }
}
```
