# [1373. 二叉搜索子树的最大键值和](https://leetcode.cn/problems/maximum-sum-bst-in-binary-tree)

## 题目描述

<p>给你一棵以 <code>root</code> 为根的 <strong>二叉树</strong> ，请你返回 <strong>任意</strong> 二叉搜索子树的最大键值和。</p>

<p>二叉搜索树的定义如下：</p>

<ul>
	<li>任意节点的左子树中的键值都 <strong>小于</strong> 此节点的键值。</li>
	<li>任意节点的右子树中的键值都 <strong>大于</strong> 此节点的键值。</li>
	<li>任意节点的左子树和右子树都是二叉搜索树。</li>
</ul>



<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1373.Maximum%20Sum%20BST%20in%20Binary%20Tree/images/sample_1_1709.png" style="height: 250px; width: 320px;" /></p>

<pre>
<strong>输入：</strong>root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
<strong>输出：</strong>20
<strong>解释：</strong>键值为 3 的子树是和最大的二叉搜索树。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1373.Maximum%20Sum%20BST%20in%20Binary%20Tree/images/sample_2_1709.png" style="height: 180px; width: 134px;" /></p>

<pre>
<strong>输入：</strong>root = [4,3,null,1,2]
<strong>输出：</strong>2
<strong>解释：</strong>键值为 2 的单节点子树是和最大的二叉搜索树。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>root = [-4,-2,-5]
<strong>输出：</strong>0
<strong>解释：</strong>所有节点键值都为负数，和最大的二叉搜索树为空。
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>root = [2,1,3]
<strong>输出：</strong>6
</pre>

<p><strong>示例 5：</strong></p>

<pre>
<strong>输入：</strong>root = [5,4,8,3,null,6,3]
<strong>输出：</strong>7
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li>每棵树有 <code>1</code> 到 <code>40000</code> 个节点。</li>
	<li>每个节点的键值在 <code>[-4 * 10^4 , 4 * 10^4]</code> 之间。</li>
</ul>

## 解法

**方法一：DFS**

判断一棵树是否是二叉搜索树，需要满足以下四个条件：

-   左子树是二叉搜索树；
-   右子树是二叉搜索树；
-   左子树的最大值小于根节点的值；
-   右子树的最小值大于根节点的值。

因此，我们设计一个函数dfs(root)，函数的返回值是一个四元组(bst, mi, mx, s)，其中：

-   数字bst表示以root为根的树是否是二叉搜索树。如果是二叉搜索树，则bst = 1；否则bst = 0；
-   数字mi表示以root为根的树的最小值；
-   数字mx表示以root为根的树的最大值；
-   数字s表示以root为根的树的所有节点的和。

函数dfs(root)的执行逻辑如下：

如果root为空节点，则返回(1, +\infty, -\infty, 0)，表示空树是二叉搜索树，最小值和最大值分别为正无穷和负无穷，节点和为0。

否则，递归计算root的左子树和右子树，分别得到(lbst, lmi, lmx, ls)和(rbst, rmi, rmx, rs)，然后判断root节点是否满足二叉搜索树的条件。

如果满足lbst = 1且rbst = 1且lmx < root.val < rmi，则以root为根的树是二叉搜索树，节点和s= ls + rs + root.val。我们更新答案ans = max(ans, s)，并返回(1, min(lmi, root.val), max(rmx, root.val), s)。

否则，以root为根的树不是二叉搜索树，我们返回(0, 0, 0, 0)。

我们在主函数中调用dfs(root)，执行完毕后，答案即为ans。

时间复杂度O(n)，空间复杂度O(n)。其中n是二叉树的节点数。

### **Java**

```java
class Solution {
    private int ans;
    private final int inf = 1 << 30;

    public int maxSumBST(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[] {1, inf, -inf, 0};
        }
        var l = dfs(root.left);
        var r = dfs(root.right);
        int v = root.val;
        if (l[0] == 1 && r[0] == 1 && l[2] < v && r[1] > v) {
            int s = v + l[3] + r[3];
            ans = Math.max(ans, s);
            return new int[] {1, Math.min(l[1], v), Math.max(r[2], v), s};
        }
        return new int[4];
    }
}
```
