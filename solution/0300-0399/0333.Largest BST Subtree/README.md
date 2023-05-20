# [333. 最大 BST 子树](https://leetcode.cn/problems/largest-bst-subtree)

## 题目描述

<p>给定一个二叉树，找到其中最大的二叉搜索树（BST）子树，并返回该子树的大小。其中，最大指的是子树节点数最多的。</p>

<p><strong>二叉搜索树（BST）</strong>中的所有节点都具备以下属性：</p>

<ul>
	<li>
	<p>左子树的值小于其父（根）节点的值。</p>
	</li>
	<li>
	<p>右子树的值大于其父（根）节点的值。</p>
	</li>
</ul>

<p><strong>注意：</strong>子树必须包含其所有后代。</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0333.Largest%20BST%20Subtree/images/tmp.jpg" /></strong></p>

<pre>
<strong>输入：</strong>root = [10,5,15,1,8,null,7]
<strong>输出：</strong>3
<strong>解释：</strong>本例中最大的 BST 子树是高亮显示的子树。返回值是子树的大小，即 3 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = [4,2,7,2,3,5,null,2,null,null,null,null,null,1]
<strong>输出：</strong>2
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li>树上节点数目的范围是 <code>[0, 10<sup>4</sup>]</code></li>
	<li><code>-10<sup>4</sup> &lt;= Node.val &lt;= 10<sup>4</sup></code></li>
</ul>

<p><strong>进阶:</strong>&nbsp; 你能想出 O(n) 时间复杂度的解法吗？</p>

## 解法

后序遍历，定义 `dfs(root)` 获取以当前结点为根结点的二叉搜索树的结点最小值、最大值、结点数。

### **Java**

```java
class Solution {
    private int ans;

    public int largestBSTSubtree(TreeNode root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        }
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        if (left[1] < root.val && root.val < right[0]) {
            ans = Math.max(ans, left[2] + right[2] + 1);
            return new int[] {
                Math.min(root.val, left[0]), Math.max(root.val, right[1]), left[2] + right[2] + 1};
        }
        return new int[] {Integer.MIN_VALUE, Integer.MAX_VALUE, 0};
    }
}
```
