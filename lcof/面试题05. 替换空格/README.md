# [面试题 05. 替换空格](https://leetcode.cn/problems/ti-huan-kong-ge-lcof/)

## 题目描述

<p>请实现一个函数，把字符串 <code>s</code> 中的每个空格替换成&quot;%20&quot;。</p>



<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = &quot;We are happy.&quot;
<strong>输出：</strong>&quot;We%20are%20happy.&quot;</pre>



<p><strong>限制：</strong></p>

<p><code>0 &lt;= s 的长度 &lt;= 10000</code></p>

## 解法

**方法一：字符串内置方法**

使用 `replace()` 方法。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串长度。

**方法二：遍历替换**

我们直接遍历字符串，遇到空格就替换成 `%20` 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串长度。

### **Java**

```java
class Solution {
    public String replaceSpace(String s) {
        return s.replaceAll(" ", "%20");
    }
}
```

```java
class Solution {
    public String replaceSpace(String s) {
        StringBuilder ans = new StringBuilder();
        for (char c : s.toCharArray()) {
            ans.append(c == ' ' ? "%20" : c);
        }
        return ans.toString();
    }
}
```
