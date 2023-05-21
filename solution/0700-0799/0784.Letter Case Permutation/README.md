# [784. 字母大小写全排列](https://leetcode.cn/problems/letter-case-permutation)

## 题目描述

<p>给定一个字符串&nbsp;<code>s</code>&nbsp;，通过将字符串&nbsp;<code>s</code>&nbsp;中的每个字母转变大小写，我们可以获得一个新的字符串。</p>

<p>返回 <em>所有可能得到的字符串集合</em> 。以 <strong>任意顺序</strong> 返回输出。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "a1b2"
<strong>输出：</strong>["a1b2", "a1B2", "A1b2", "A1B2"]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> s = "3z4"
<strong>输出:</strong> ["3z4","3Z4"]
</pre>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 12</code></li>
	<li><code>s</code>&nbsp;由小写英文字母、大写英文字母和数字组成</li>
</ul>

## 解法

**方法一：DFS**

由于s中的每个字母都可以转换为大写或小写，因此可以使用 DFS 深度优先搜索的方法，枚举所有可能的情况。

具体地，从左到右遍历字符串s，对于遍历到的每个字母，可以选择将其转变为大写或小写，然后继续遍历后面的字母。当遍历到字符串的末尾时，得到一个转换方案，将该方案加入答案即可。

转变大小写的方法可以使用位运算实现。对于一个字母，小写形式与大写形式的 ASCII 码之差为32，因此，我们可以通过将该字母的 ASCII 码与32进行异或运算来实现大小写转换。

时间复杂度O(n\times 2^n)，其中n是字符串s的长度。对于每个字母，我们可以选择将其转换为大写或小写，因此一共有2^n种转换方案。对于每种转换方案，我们需要O(n)的时间生成一个新的字符串。

**方法二：二进制枚举**

对于一个字母，我们可以将其转换为大写或小写，因此对于每个字母，我们可以使用一个二进制位表示其转换的方案，其中1表示小写，而0表示大写。

我们先统计字符串s中字母的个数，记为n，那么一共有2^n种转换方案，我们可以使用二进制数的每一位表示每个字母的转换方案，从0到2^n-1进行枚举。

具体地，我们可以使用一个变量i表示当前枚举到的二进制数，其中i的第j位表示第j个字母的转换方案。即i的第j位为1表示第j个字母转换为小写，而i的第j位为0表示第j个字母转换为大写。

时间复杂度O(n\times 2^n)，其中n是字符串s的长度。对于每个字母，我们可以选择将其转换为大写或小写，因此一共有2^n种转换方案。对于每种转换方案，我们需要O(n)的时间生成一个新的字符串。

### **Java**

```java
class Solution {
    private List<String> ans = new ArrayList<>();
    private char[] t;

    public List<String> letterCasePermutation(String s) {
        t = s.toCharArray();
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i >= t.length) {
            ans.add(String.valueOf(t));
            return;
        }
        dfs(i + 1);
        if (t[i] >= 'A') {
            t[i] ^= 32;
            dfs(i + 1);
        }
    }
}
```

```java
class Solution {
    public List<String> letterCasePermutation(String s) {
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) >= 'A') {
                ++n;
            }
        }
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 1 << n; ++i) {
            int j = 0;
            StringBuilder t = new StringBuilder();
            for (int k = 0; k < s.length(); ++k) {
                char x = s.charAt(k);
                if (x >= 'A') {
                    x = ((i >> j) & 1) == 1 ? Character.toLowerCase(x) : Character.toUpperCase(x);
                    ++j;
                }
                t.append(x);
            }
            ans.add(t.toString());
        }
        return ans;
    }
}
```
