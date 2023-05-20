# [301. 删除无效的括号](https://leetcode.cn/problems/remove-invalid-parentheses)

## 题目描述

<p>给你一个由若干括号和字母组成的字符串 <code>s</code> ，删除最小数量的无效括号，使得输入的字符串有效。</p>

<p>返回所有可能的结果。答案可以按 <strong>任意顺序</strong> 返回。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "()())()"
<strong>输出：</strong>["(())()","()()()"]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "(a)())()"
<strong>输出：</strong>["(a())()","(a)()()"]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = ")("
<strong>输出：</strong>[""]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 25</code></li>
	<li><code>s</code> 由小写英文字母以及括号 <code>'('</code> 和 <code>')'</code> 组成</li>
	<li><code>s</code> 中至多含 <code>20</code> 个括号</li>
</ul>

## 解法

**方法一：DFS + 剪枝**

我们首先处理得到字符串 $s$ 待删除的左、右括号的最小数量，分别记为 $l$ 和 $r$。

然后我们设计一个递归函数 `dfs(i, l, r, lcnt, rcnt, t)`，其中：

-   `i` 表示当前处理到字符串 $s$ 的第 $i$ 个字符；
-   `l` 和 `r` 分别表示剩余待删除的左、右括号的数量；
-   `t` 表示当前得到的字符串；
-   `lcnt` 和 `rcnt` 分别表示当前得到的字符串中左、右括号的数量。

递归函数的逻辑如下：

-   如果 `i` 等于字符串 $s$ 的长度，且 `l` 和 `r` 都等于 $0$，则将 `t` 加入答案数组中；
-   如果剩余的待处理字符数 $n-i$ 小于剩余待删除的左右括号数量 $l+r$，或者当前得到的字符串中的左括号数量小于右括号数量，直接返回；
-   如果当前字符是左括号，我们可以选择删除或者保留，如果删除，需要满足 $l \gt 0$，然后递归调用 `dfs(i+1, l-1, r, lcnt, rcnt, t)`；
-   如果当前字符是右括号，我们可以选择删除或者保留，如果删除，需要满足 $r \gt 0$，然后递归调用 `dfs(i+1, l, r-1, lcnt, rcnt, t)`；
-   如果选择保留当前字符，我们需要判断当前字符是左括号、右括号还是字母。如果是左括号，我们需要更新 `lcnt`，如果是右括号，我们需要更新 `rcnt`，然后递归调用 `dfs(i+1, l, r, lcnt, rcnt, t+s[i])`。

我们调用 `dfs(0, l, r, 0, 0, "")`，搜索所有可能的字符串。

最后返回去重后的答案数组即可。

时间复杂度 $O(n\times 2^n)$，空间复杂度 $O(n)$。长度为 $n$ 的字符串有 $2^n$ 种可能的删除方式，每种删除方式需要 $O(n)$ 的时间复制字符串。因此总时间复杂度为 $O(n\times 2^n)$。

### **Java**

```java
class Solution {
    private String s;
    private int n;
    private Set<String> ans = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {
        this.s = s;
        this.n = s.length();
        int l = 0, r = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                ++l;
            } else if (c == ')') {
                if (l > 0) {
                    --l;
                } else {
                    ++r;
                }
            }
        }
        dfs(0, l, r, 0, 0, "");
        return new ArrayList<>(ans);
    }

    private void dfs(int i, int l, int r, int lcnt, int rcnt, String t) {
        if (i == n) {
            if (l == 0 && r == 0) {
                ans.add(t);
            }
            return;
        }
        if (n - i < l + r || lcnt < rcnt) {
            return;
        }
        char c = s.charAt(i);
        if (c == '(' && l > 0) {
            dfs(i + 1, l - 1, r, lcnt, rcnt, t);
        }
        if (c == ')' && r > 0) {
            dfs(i + 1, l, r - 1, lcnt, rcnt, t);
        }
        int x = c == '(' ? 1 : 0;
        int y = c == ')' ? 1 : 0;
        dfs(i + 1, l, r, lcnt + x, rcnt + y, t + c);
    }
}
```
