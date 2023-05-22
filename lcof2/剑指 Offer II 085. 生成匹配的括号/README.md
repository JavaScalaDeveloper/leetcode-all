# [剑指 Offer II 085. 生成匹配的括号](https://leetcode.cn/problems/IDBivT)

## 题目描述



<p>正整数&nbsp;<code>n</code>&nbsp;代表生成括号的对数，请设计一个函数，用于能够生成所有可能的并且 <strong>有效的 </strong>括号组合。</p>



<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 3
<strong>输出：</strong>[&quot;((()))&quot;,&quot;(()())&quot;,&quot;(())()&quot;,&quot;()(())&quot;,&quot;()()()&quot;]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>[&quot;()&quot;]
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 8</code></li>
</ul>



<p><meta charset="UTF-8" />注意：本题与主站 22&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/generate-parentheses/">https://leetcode.cn/problems/generate-parentheses/</a></p>

## 解法

深度优先搜索 DFS。

### **Java**

```java
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        dfs(0, 0, n, "", ans);
        return ans;
    }

    private void dfs(int left, int right, int n, String t, List<String> ans) {
        if (left == n && right == n) {
            ans.add(t);
            return;
        }
        if (left < n) {
            dfs(left + 1, right, n, t + "(", ans);
        }
        if (right < left) {
            dfs(left, right + 1, n, t + ")", ans);
        }
    }
}
```
