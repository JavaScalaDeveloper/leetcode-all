# [437. 路径总和 III](https://leetcode.cn/problems/path-sum-iii)

## 题目描述

<p>给定一个二叉树的根节点 <code>root</code> ，和一个整数 <code>targetSum</code> ，求该二叉树里节点值之和等于 <code>targetSum</code> 的 <strong>路径</strong> 的数目。</p>

<p><strong>路径</strong> 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。</p>



<p><strong>示例 1：</strong></p>

<p><img src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0437.Path%20Sum%20III/images/pathsum3-1-tree.jpg" style="width: 452px; " /></p>

<pre>
<strong>输入：</strong>root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
<strong>输出：</strong>3
<strong>解释：</strong>和等于 8 的路径有 3 条，如图所示。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
<strong>输出：</strong>3
</pre>



<p><strong>提示:</strong></p>

<ul>
	<li>二叉树的节点个数的范围是 <code>[0,1000]</code></li>
	<li><meta charset="UTF-8" /><code>-10<sup>9</sup> <= Node.val <= 10<sup>9</sup></code> </li>
	<li><code>-1000 <= targetSum <= 1000</code> </li>
</ul>

## 解法

**方法一：哈希表 + 前缀和 + 递归**

我们可以运用前缀和的思想，对二叉树进行递归遍历，同时用哈希表 $cnt$ 统计从根节点到当前节点的路径上各个前缀和出现的次数。

我们设计一个递归函数 $dfs(node, s)$，表示当前遍历到的节点为 $node$，从根节点到当前节点的路径上的前缀和为 $s$。函数的返回值是统计以 $node$ 节点及其子树节点作为路径终点且路径和为 $targetSum$ 的路径数目。那么答案就是 $dfs(root, 0)$。

函数 $dfs(node, s)$ 的递归过程如下：

-   如果当前节点 $node$ 为空，则返回 $0$。
-   计算从根节点到当前节点的路径上的前缀和 $s$。
-   用 $cnt[s - targetSum]$ 表示以当前节点为路径终点且路径和为 $targetSum$ 的路径数目，其中 $cnt[s - targetSum]$ 即为 $cnt$ 中前缀和为 $s - targetSum$ 的个数。
-   将前缀和 $s$ 的计数值加 $1$，即 $cnt[s] = cnt[s] + 1$。
-   递归地遍历当前节点的左右子节点，即调用函数 $dfs(node.left, s)$ 和 $dfs(node.right, s)$，并将它们的返回值相加。
-   在返回值计算完成以后，需要将当前节点的前缀和 $s$ 的计数值减 $1$，即执行 $cnt[s] = cnt[s] - 1$。
-   最后返回答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉树的节点个数。

### **Java**

```java
class Solution {
    private Map<Long, Integer> cnt = new HashMap<>();
    private int targetSum;

    public int pathSum(TreeNode root, int targetSum) {
        cnt.put(0L, 1);
        this.targetSum = targetSum;
        return dfs(root, 0);
    }

    private int dfs(TreeNode node, long s) {
        if (node == null) {
            return 0;
        }
        s += node.val;
        int ans = cnt.getOrDefault(s - targetSum, 0);
        cnt.merge(s, 1, Integer::sum);
        ans += dfs(node.left, s);
        ans += dfs(node.right, s);
        cnt.merge(s, -1, Integer::sum);
        return ans;
    }
}
```
