# [894. 所有可能的真二叉树](https://leetcode.cn/problems/all-possible-full-binary-trees)

## 题目描述

<p>给你一个整数 <code>n</code> ，请你找出所有可能含 <code>n</code> 个节点的 <strong>真二叉树</strong> ，并以列表形式返回。答案中每棵树的每个节点都必须符合 <code>Node.val == 0</code> 。</p>

<p>答案的每个元素都是一棵真二叉树的根节点。你可以按 <strong>任意顺序</strong> 返回最终的真二叉树列表<strong>。</strong></p>

<p><strong>真二叉树</strong> 是一类二叉树，树中每个节点恰好有 <code>0</code> 或 <code>2</code> 个子节点。</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0894.All%20Possible%20Full%20Binary%20Trees/images/fivetrees.png" style="width: 700px; height: 400px;" />
<pre>
<strong>输入：</strong>n = 7
<strong>输出：</strong>[[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 3
<strong>输出：</strong>[[0,0,0]]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 20</code></li>
</ul>

## 解法

**方法一：记忆化搜索**

如果n=1，直接返回单个节点的列表。

如果n > 1，我们可以枚举左子树的节点数量i，那么右子树的节点数量为n-1-i。对于每种情况，我们递归地构造左子树和右子树的所有可能的真二叉树。然后将左子树和右子树两两组合，得到所有可能的真二叉树。

此过程可以用记忆化搜索，避免重复计算。

时间复杂度O(2^n)，空间复杂度O(2^n)。其中n是节点数量。

### **Java**

```java
class Solution {
    private List<TreeNode>[] f;

    public List<TreeNode> allPossibleFBT(int n) {
        f = new List[n + 1];
        return dfs(n);
    }

    private List<TreeNode> dfs(int n) {
        if (f[n] != null) {
            return f[n];
        }
        if (n == 1) {
            return List.of(new TreeNode());
        }
        List<TreeNode> ans = new ArrayList<>();
        for (int i = 0; i < n - 1; ++i) {
            int j = n - 1 - i;
            for (var left : dfs(i)) {
                for (var right : dfs(j)) {
                    ans.add(new TreeNode(0, left, right));
                }
            }
        }
        return f[n] = ans;
    }
}
```
