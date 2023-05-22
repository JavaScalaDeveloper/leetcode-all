# [22. 括号生成](https://leetcode.cn/problems/generate-parentheses)

## 题目描述

<p>数字 <code>n</code>&nbsp;代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 <strong>有效的 </strong>括号组合。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 3
<strong>输出：</strong>["((()))","(()())","(())()","()(())","()()()"]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>["()"]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 8</code></li>
</ul>

## 解法

**方法一：DFS + 剪枝**

题目中n的范围为[1, 8]，因此我们直接通过“暴力搜索 + 剪枝”的方式快速解决本题。

我们设计函数 `dfs(l, r, t)`，其中l和r分别表示左括号和右括号的数量，而t表示当前的括号序列。那么我们可以得到如下的递归结构：

-   如果l > n或者r > n或者l < r，那么当前括号组合t不合法，直接返回；
-   如果l = n且r = n，那么当前括号组合t合法，将其加入答案数组 `ans` 中，直接返回；
-   我们可以选择添加一个左括号，递归执行 `dfs(l + 1, r, t + "(")`；
-   我们也可以选择添加一个右括号，递归执行 `dfs(l, r + 1, t + ")")`。

时间复杂度O(2^{n× 2} × n)，空间复杂度O(n)。

### **Java**

```java
class Solution {
    private List<String> ans = new ArrayList<>();
    private int n;

    public List<String> generateParenthesis(int n) {
        this.n = n;
        dfs(0, 0, "");
        return ans;
    }

    private void dfs(int l, int r, String t) {
        if (l > n || r > n || l < r) {
            return;
        }
        if (l == n && r == n) {
            ans.add(t);
            return;
        }
        dfs(l + 1, r, t + "(");
        dfs(l, r + 1, t + ")");
    }
}
```
