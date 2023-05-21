# [1023. 驼峰式匹配](https://leetcode.cn/problems/camelcase-matching)

## 题目描述

<p>给你一个字符串数组 <code>queries</code>，和一个表示模式的字符串&nbsp;<code>pattern</code>，请你返回一个布尔数组 <code>answer</code> 。只有在待查项&nbsp;<code>queries[i]</code> 与模式串&nbsp;<code>pattern</code> 匹配时，&nbsp;<code>answer[i]</code>&nbsp;才为 <code>true</code>，否则为 <code>false</code>。</p>

<p>如果可以将<strong>小写字母</strong>插入模式串&nbsp;<code>pattern</code>&nbsp;得到待查询项&nbsp;<code>query</code>，那么待查询项与给定模式串匹配。可以在任何位置插入每个字符，也可以不插入字符。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FB"
<strong>输出：</strong>[true,false,true,true,false]
<strong>示例：</strong>
"FooBar" 可以这样生成："F" + "oo" + "B" + "ar"。
"FootBall" 可以这样生成："F" + "oot" + "B" + "all".
"FrameBuffer" 可以这样生成："F" + "rame" + "B" + "uffer".</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBa"
<strong>输出：</strong>[true,false,true,false,false]
<strong>解释：</strong>
"FooBar" 可以这样生成："Fo" + "o" + "Ba" + "r".
"FootBall" 可以这样生成："Fo" + "ot" + "Ba" + "ll".
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBaT"
<strong>输出：</strong>[false,true,false,false,false]
<strong>解释： </strong>
"FooBarTest" 可以这样生成："Fo" + "o" + "Ba" + "r" + "T" + "est".
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= pattern.length, queries.length &lt;= 100</code></li>
	<li><code>1 &lt;= queries[i].length &lt;= 100</code></li>
	<li><code>queries[i]</code> 和 <code>pattern</code> 由英文字母组成</li>
</ul>

## 解法

**方法一：双指针**

我们可以遍历 `queries` 中的每个字符串，判断其是否与 `pattern` 匹配，若匹配则将 `true` 加入答案数组，否则加入 `false`。

接下来，我们实现一个check(s, t)函数，用于判断字符串s和t是否匹配。

我们可以使用双指针i和j，分别指向两个字符串的首字符，然后遍历两个字符串。如果指针i和j指向的字符不同，并且s[i]为小写字母，则指针i循环向后移动一位。

如果指针i已经到达字符串s的末尾，或者指针i和j指向的字符不同，则返回 `false`。否则，指针i和j同时向后移动一位。当指针j到达字符串t的末尾时，我们需要判断字符串s中剩余的字符是否都为小写字母，若是则返回 `true`，否则返回 `false`。

时间复杂度(n \times m)，其中n和m分别为数组 `queries` 的长度和字符串 `pattern` 的长度。

### **Java**

```java
class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> ans = new ArrayList<>();
        for (var q : queries) {
            ans.add(check(q, pattern));
        }
        return ans;
    }

    private boolean check(String s, String t) {
        int m = s.length(), n = t.length();
        int i = 0, j = 0;
        for (; j < n; ++i, ++j) {
            while (i < m && s.charAt(i) != t.charAt(j) && Character.isLowerCase(s.charAt(i))) {
                ++i;
            }
            if (i == m || s.charAt(i) != t.charAt(j)) {
                return false;
            }
        }
        while (i < m && Character.isLowerCase(s.charAt(i))) {
            ++i;
        }
        return i == m;
    }
}
```
