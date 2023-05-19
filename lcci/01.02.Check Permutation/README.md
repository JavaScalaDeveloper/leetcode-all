# [面试题 01.02. 判定是否互为字符重排](https://leetcode.cn/problems/check-permutation-lcci)

[English Version](/lcci/01.02.Check%20Permutation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定两个字符串 <code>s1</code> 和 <code>s2</code>，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入:</strong> s1 = &quot;abc&quot;, s2 = &quot;bca&quot;
<strong>输出:</strong> true
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入:</strong> s1 = &quot;abc&quot;, s2 = &quot;bad&quot;
<strong>输出:</strong> false
</pre>

<p><strong>说明：</strong></p>

<ul>
	<li><code>0 &lt;= len(s1) &lt;= 100 </code></li>
	<li><code>0 &lt;= len(s2) &lt;= 100 </code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数组或哈希表**

先判断两个字符串的长度是否相等，若不相等则直接返回 `false`。

然后用一个数组或哈希表统计字符串 $s1$ 中字符出现的次数。

接着遍历另一个字符串 $s2$，每遍历到一个字符，就将该字符对应的次数减一，如果减一后的次数小于 $0$，则说明两个字符串中字符出现的次数不同，直接返回 `false`。

最后遍历完字符串 $s2$，返回 `true`。

注意：本题测试用例所有字符串仅包含小写字母，因此我们可以直接开一个长度为 $26$ 的数组来计数。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 为字符串的长度，而 $C$ 为字符集的大小，本题 $C=26$。

**方法二：排序**

按照字典序对两个字符串进行排序，然后比较两个字符串是否相等。

时间复杂度 $O(n\log n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->





### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean CheckPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int[] cnt = new int[26];
        for (char c : s1.toCharArray()) {
            ++cnt[c - 'a'];
        }
        for (char c : s2.toCharArray()) {
            if (--cnt[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
```

```java
class Solution {
    public boolean CheckPermutation(String s1, String s2) {
        char[] cs1 = s1.toCharArray();
        char[] cs2 = s2.toCharArray();
        Arrays.sort(cs1);
        Arrays.sort(cs2);
        return Arrays.equals(cs1, cs2);
    }
}
```

















### **TypeScript**











### **...**

```

```


