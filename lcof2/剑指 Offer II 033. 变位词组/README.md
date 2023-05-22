# [剑指 Offer II 033. 变位词组](https://leetcode.cn/problems/sfvd7V)

## 题目描述



<p>给定一个字符串数组 <code>strs</code> ，将&nbsp;<strong>变位词&nbsp;</strong>组合在一起。 可以按任意顺序返回结果列表。</p>

<p><strong>注意：</strong>若两个字符串中每个字符出现的次数都相同且<strong>字符顺序不完全相同</strong>，则称它们互为变位词。</p>



<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> strs = <code>[&quot;eat&quot;, &quot;tea&quot;, &quot;tan&quot;, &quot;ate&quot;, &quot;nat&quot;, &quot;bat&quot;]</code>
<strong>输出: </strong>[[&quot;bat&quot;],[&quot;nat&quot;,&quot;tan&quot;],[&quot;ate&quot;,&quot;eat&quot;,&quot;tea&quot;]]</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> strs = <code>[&quot;&quot;]</code>
<strong>输出: </strong>[[&quot;&quot;]]
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> strs = <code>[&quot;a&quot;]</code>
<strong>输出: </strong>[[&quot;a&quot;]]</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= strs.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= strs[i].length &lt;= 100</code></li>
	<li><code>strs[i]</code>&nbsp;仅包含小写字母</li>
</ul>



<p><meta charset="UTF-8" />注意：本题与主站 49&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/group-anagrams/">https://leetcode.cn/problems/group-anagrams/</a></p>

## 解法

遍历字符串，将每个字符串按照字符字典序排序后得到一个新的字符串，将相同的新字符串放在哈希表的同一个 key 对应 value 列表中。

| key     | value                   |
| ------- | ----------------------- |
| `"aet"` | `["eat", "tea", "ate"]` |
| `"ant"` | `["tan", "nat"] `       |
| `"abt"` | `["bat"] `              |

最后返回哈希表的 value 列表即可。

### **Java**

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> chars = new HashMap<>();
        for (String s : strs) {
            char[] t = s.toCharArray();
            Arrays.sort(t);
            String k = new String(t);
            chars.computeIfAbsent(k, key -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(chars.values());
    }
}
```
