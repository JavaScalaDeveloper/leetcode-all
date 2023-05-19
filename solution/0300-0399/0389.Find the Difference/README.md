# [389. 找不同](https://leetcode.cn/problems/find-the-difference)

[English Version](/solution/0300-0399/0389.Find%20the%20Difference/README_EN.md)

## 题目描述

<p>给定两个字符串 <code>s</code> 和 <code>t</code>&nbsp;，它们只包含小写字母。</p>

<p>字符串 <code>t</code>&nbsp;由字符串 <code>s</code> 随机重排，然后在随机位置添加一个字母。</p>

<p>请找出在 <code>t</code>&nbsp;中被添加的字母。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "abcd", t = "abcde"
<strong>输出：</strong>"e"
<strong>解释：</strong>'e' 是那个被添加的字母。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "", t = "y"
<strong>输出：</strong>"y"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= s.length &lt;= 1000</code></li>
	<li><code>t.length == s.length + 1</code></li>
	<li><code>s</code> 和 <code>t</code> 只包含小写字母</li>
</ul>

## 解法

**方法一：计数**

使用数组（`cnt`）统计 `s` 与 `t` 当中字符出现的次数：`s[i]` 进行 `cnt[s[i] - 'a']++`，`t[i]` 进行 `cnt[t[i] - 'a']--`。

完成统计后，找到符合 `cnt[i] == -1` 的 `i`，返回即可（`return 'a' + i`）。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。本题中 $C=26$。

**方法二：求和**

由于 `s` 与 `t` 只存在一个不同元素，可以统计两者所有字符 ASCII 码之和，再进行相减（`sum(t) - sum(s)`），即可得到 `t` 中那一个额外字符的 ASCII 码。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。

### **Java**

```java
class Solution {
    public char findTheDifference(String s, String t) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        for (int i = 0; ; ++i) {
            if (--cnt[t.charAt(i) - 'a'] < 0) {
                return t.charAt(i);
            }
        }
    }
}
```

```java
class Solution {
    public char findTheDifference(String s, String t) {
        int ss = 0;
        for (int i = 0; i < t.length(); ++i) {
            ss += t.charAt(i);
        }
        for (int i = 0; i < s.length(); ++i) {
            ss -= s.charAt(i);
        }
        return (char) ss;
    }
}
```

### **C**

```c
char findTheDifference(char *s, char *t) {
    int n = strlen(s);
    int count[26] = {0};
    for (int i = 0; i < n; i++) {
        count[s[i] - 'a']++;
        count[t[i] - 'a']--;
    }
    count[t[n] - 'a']--;
    int i;
    for (i = 0; i < 26; i++) {
        if (count[i]) {
            break;
        }
    }
    return 'a' + i;
}
```

```c
char findTheDifference(char *s, char *t) {
    int n = strlen(s);
    char ans = 0;
    for (int i = 0; i < n; i++) {
        ans ^= s[i];
        ans ^= t[i];
    }
    ans ^= t[n];
    return ans;
}
```
