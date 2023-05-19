# [1047. 删除字符串中的所有相邻重复项](https://leetcode.cn/problems/remove-all-adjacent-duplicates-in-string)

[English Version](/solution/1000-1099/1047.Remove%20All%20Adjacent%20Duplicates%20In%20String/README_EN.md)

## 题目描述

<p>给出由小写字母组成的字符串&nbsp;<code>S</code>，<strong>重复项删除操作</strong>会选择两个相邻且相同的字母，并删除它们。</p>

<p>在 S 上反复执行重复项删除操作，直到无法继续删除。</p>

<p>在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>&quot;abbaca&quot;
<strong>输出：</strong>&quot;ca&quot;
<strong>解释：</strong>
例如，在 &quot;abbaca&quot; 中，我们可以删除 &quot;bb&quot; 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 &quot;aaca&quot;，其中又只有 &quot;aa&quot; 可以执行重复项删除操作，所以最后的字符串为 &quot;ca&quot;。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= S.length &lt;= 20000</code></li>
	<li><code>S</code> 仅由小写英文字母组成。</li>
</ol>

## 解法

**方法一：栈**

遍历字符串 `s` 中的每个字符 `c`，若栈为空或者栈顶值不等于字符 `c`，将 `c` 入栈，否则栈顶元素出栈。

最后返回栈中元素所组成的字符串。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是字符串 `s` 的长度。

### **Java**

```java
class Solution {
    public String removeDuplicates(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (sb.length() > 0 && sb.charAt(sb.length() - 1) == c) {
                sb.deleteCharAt(sb.length() - 1);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
```

### **C**

```c
char *removeDuplicates(char *s) {
    int n = strlen(s);
    char *stack = malloc(sizeof(char) * (n + 1));
    int i = 0;
    for (int j = 0; j < n; j++) {
        char c = s[j];
        if (i && stack[i - 1] == c) {
            i--;
        } else {
            stack[i++] = c;
        }
    }
    stack[i] = '\0';
    return stack;
}
```
