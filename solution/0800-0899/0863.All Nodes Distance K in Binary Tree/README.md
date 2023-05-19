# [863. 二叉树中所有距离为 K 的结点](https://leetcode.cn/problems/all-nodes-distance-k-in-binary-tree)

[English Version](/solution/0800-0899/0863.All%20Nodes%20Distance%20K%20in%20Binary%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二叉树（具有根结点&nbsp;<code>root</code>），&nbsp;一个目标结点&nbsp;<code>target</code>&nbsp;，和一个整数值 <code>k</code> 。</p>

<p>返回到目标结点 <code>target</code> 距离为 <code>k</code> 的所有结点的值的列表。 答案可以以 <strong>任何顺序</strong> 返回。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0800-0899/0863.All%20Nodes%20Distance%20K%20in%20Binary%20Tree/images/sketch0.png" style="height: 429px; width: 500px;" /></p>

<pre>
<strong>输入：</strong>root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
<strong>输出：</strong>[7,4,1]
<strong>解释：</strong>所求结点为与目标结点（值为 5）距离为 2 的结点，值分别为 7，4，以及 1
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> root = [1], target = 1, k = 3
<strong>输出:</strong> []
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li>节点数在&nbsp;<code>[1, 500]</code>&nbsp;范围内</li>
	<li><code>0 &lt;= Node.val &lt;= 500</code></li>
	<li><code>Node.val</code>&nbsp;中所有值 <strong>不同</strong></li>
	<li>目标结点&nbsp;<code>target</code>&nbsp;是树上的结点。</li>
	<li><code>0 &lt;= k &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>

## 解法

**方法一：DFS + 哈希表**

我们先用 DFS 遍历整棵树，记录每个结点的父结点，然后从目标结点开始，向上、向下分别搜索距离为 $k$ 的结点，添加到答案数组中。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉树的结点数。

### **Java**

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private Map<TreeNode, TreeNode> p;
    private Set<Integer> vis;
    private List<Integer> ans;

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        p = new HashMap<>();
        vis = new HashSet<>();
        ans = new ArrayList<>();
        parents(root, null);
        dfs(target, k);
        return ans;
    }

    private void parents(TreeNode root, TreeNode prev) {
        if (root == null) {
            return;
        }
        p.put(root, prev);
        parents(root.left, root);
        parents(root.right, root);
    }

    private void dfs(TreeNode root, int k) {
        if (root == null || vis.contains(root.val)) {
            return;
        }
        vis.add(root.val);
        if (k == 0) {
            ans.add(root.val);
            return;
        }
        dfs(root.left, k - 1);
        dfs(root.right, k - 1);
        dfs(p.get(root), k - 1);
    }
}
```
