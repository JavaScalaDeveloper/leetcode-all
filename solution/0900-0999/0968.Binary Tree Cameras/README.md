# [968. 监控二叉树](https://leetcode.cn/problems/binary-tree-cameras)

[English Version](/solution/0900-0999/0968.Binary%20Tree%20Cameras/README_EN.md)

## 题目描述

<p>给定一个二叉树，我们在树的节点上安装摄像头。</p>

<p>节点上的每个摄影头都可以监视<strong>其父对象、自身及其直接子对象。</strong></p>

<p>计算监控树的所有节点所需的最小摄像头数量。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0968.Binary%20Tree%20Cameras/images/bst_cameras_01.png" style="height: 163px; width: 138px;"></p>

<pre><strong>输入：</strong>[0,0,null,0,0]
<strong>输出：</strong>1
<strong>解释：</strong>如图所示，一台摄像头足以监控所有节点。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0968.Binary%20Tree%20Cameras/images/bst_cameras_02.png" style="height: 312px; width: 139px;"></p>

<pre><strong>输入：</strong>[0,0,null,0,null,0,null,null,0]
<strong>输出：</strong>2
<strong>解释：</strong>需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。
</pre>

<p><br>
<strong>提示：</strong></p>

<ol>
	<li>给定树的节点数的范围是&nbsp;<code>[1, 1000]</code>。</li>
	<li>每个节点的值都是 0。</li>
</ol>

## 解法

**方法一：动态规划（树形 DP）**

对于每个节点，我们定义三种状态：

-   `a`：当前节点有摄像头
-   `b`：当前节点无摄像头，但被子节点监控
-   `c`：当前节点无摄像头，也没被子节点监控

接下来，我们设计一个函数 $dfs(root)$，它将返回一个长度为 3 的数组，表示以 `root` 为根的子树中，三种状态下的最小摄像头数量。那么答案就是 $\min(dfs(root)[0], dfs(root)[1])$。

函数 $dfs(root)$ 的计算过程如下：

如果 `root` 为空，则返回 $[inf, 0, 0]$，其中 `inf` 表示一个很大的数，它用于表示不可能的情况。

否则，我们递归计算 `root` 的左右子树，分别得到 $[la, lb, lc]$ 和 $[ra, rb, rc]$。

-   如果当前节点有摄像头，那么它的左右节点必须都是被监控的状态，即 $a = \min(la, lb, lc) + \min(ra, rb, rc) + 1$。
-   如果当前节点无摄像头，但被子节点监控，那么子节点可以是其中之一或者两个都有摄像头，即 $b = \min(la + rb, lb + ra, la + ra)$。
-   如果当前节点无摄像头，也没被子节点监控，那么子节点必须被其子节点监控，即 $c = lb + rb$。

最后，我们返回 $[a, b, c]$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉树的节点数。

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
    public int minCameraCover(TreeNode root) {
        int[] ans = dfs(root);
        return Math.min(ans[0], ans[1]);
    }

    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[] {1 << 29, 0, 0};
        }
        var l = dfs(root.left);
        var r = dfs(root.right);
        int a = 1 + Math.min(Math.min(l[0], l[1]), l[2]) + Math.min(Math.min(r[0], r[1]), r[2]);
        int b = Math.min(Math.min(l[0] + r[1], l[1] + r[0]), l[0] + r[0]);
        int c = l[1] + r[1];
        return new int[] {a, b, c};
    }
}
```
