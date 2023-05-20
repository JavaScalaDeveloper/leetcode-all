# [536. 从字符串生成二叉树](https://leetcode.cn/problems/construct-binary-tree-from-string)

## 题目描述

<p>你需要用一个包括括号和整数的字符串构建一棵二叉树。</p>

<p>输入的字符串代表一棵二叉树。它包括整数和随后的 0 、1 或 2 对括号。整数代表根的值，一对括号内表示同样结构的子树。</p>

<p>若存在子结点，则从<strong>左子结点</strong>开始构建。</p>

<p><strong>示例 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0500-0599/0536.Construct%20Binary%20Tree%20from%20String/images/butree.jpg" style="height: 322px; width: 382px;" />
<pre>
<strong>输入：</strong> s = "4(2(3)(1))(6(5))"
<strong>输出：</strong> [4,2,6,3,1,5]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入：</strong> s = "4(2(3)(1))(6(5)(7))"
<strong>输出：</strong> [4,2,6,3,1,5,7]
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入：</strong> s = "-4(2(3)(1))(6(5)(7))"
<strong>输出： </strong>[-4,2,6,3,1,5,7]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= s.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li>输入字符串中只包含&nbsp;<code>'('</code>, <code>')'</code>, <code>'-'</code>&nbsp;和&nbsp;<code>'0'</code> ~ <code>'9'</code>&nbsp;</li>
	<li>空树由&nbsp;<code>""</code>&nbsp;而非<code>"()"</code>表示。</li>
</ul>

## 解法

DFS。

利用 cnt 变量，检测子树的位置，若 cnt == 0，说明已经定位到其中一棵子树，start 表示子树开始的位置（注意要去掉括号）。

### **Java**

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode str2tree(String s) {
        return dfs(s);
    }

    private TreeNode dfs(String s) {
        if ("".equals(s)) {
            return null;
        }
        int p = s.indexOf("(");
        if (p == -1) {
            return new TreeNode(Integer.parseInt(s));
        }
        TreeNode root = new TreeNode(Integer.parseInt(s.substring(0, p)));
        int start = p;
        int cnt = 0;
        for (int i = p; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                ++cnt;
            } else if (s.charAt(i) == ')') {
                --cnt;
            }
            if (cnt == 0) {
                if (start == p) {
                    root.left = dfs(s.substring(start + 1, i));
                    start = i + 1;
                } else {
                    root.right = dfs(s.substring(start + 1, i));
                }
            }
        }
        return root;
    }
}
```
