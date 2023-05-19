# [500. 键盘行](https://leetcode.cn/problems/keyboard-row)

[English Version](/solution/0500-0599/0500.Keyboard%20Row/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串数组 <code>words</code> ，只返回可以使用在 <strong>美式键盘</strong> 同一行的字母打印出来的单词。键盘如下图所示。</p>

<p><strong>美式键盘</strong> 中：</p>

<ul>
	<li>第一行由字符 <code>"qwertyuiop"</code> 组成。</li>
	<li>第二行由字符 <code>"asdfghjkl"</code> 组成。</li>
	<li>第三行由字符 <code>"zxcvbnm"</code> 组成。</li>
</ul>

<p><img alt="American keyboard" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0500.Keyboard%20Row/images/keyboard.png" style="width: 100%; max-width: 600px" /></p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>words = ["Hello","Alaska","Dad","Peace"]
<strong>输出：</strong>["Alaska","Dad"]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = ["omk"]
<strong>输出：</strong>[]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>words = ["adsdf","sfd"]
<strong>输出：</strong>["adsdf","sfd"]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= words.length <= 20</code></li>
	<li><code>1 <= words[i].length <= 100</code></li>
	<li><code>words[i]</code> 由英文字母（小写和大写字母）组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：字符映射**

我们将每个键盘行的字符映射到对应的行数，然后遍历字符串数组，判断每个字符串是否都在同一行即可。

时间复杂度 $O(L)$，空间复杂度 $O(C)$。其中 $L$ 为所有字符串的长度之和；而 $C$ 为字符集的大小，本题中 $C = 26$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->





### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String[] findWords(String[] words) {
        String s = "12210111011122000010020202";
        List<String> ans = new ArrayList<>();
        for (var w : words) {
            String t = w.toLowerCase();
            char x = s.charAt(t.charAt(0) - 'a');
            boolean ok = true;
            for (char c : t.toCharArray()) {
                if (s.charAt(c - 'a') != x) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                ans.add(w);
            }
        }
        return ans.toArray(new String[0]);
    }
}
```













### **TypeScript**



### **...**

```

```


