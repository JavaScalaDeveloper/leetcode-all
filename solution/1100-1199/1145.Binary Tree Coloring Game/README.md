# [1145. 二叉树着色游戏](https://leetcode.cn/problems/binary-tree-coloring-game)

## 题目描述

<p>有两位极客玩家参与了一场「二叉树着色」的游戏。游戏中，给出二叉树的根节点&nbsp;<code>root</code>，树上总共有 <code>n</code> 个节点，且 <code>n</code> 为奇数，其中每个节点上的值从&nbsp;<code>1</code> 到&nbsp;<code>n</code>&nbsp;各不相同。</p>

<p>最开始时：</p>

<ul>
	<li>「一号」玩家从 <code>[1, n]</code>&nbsp;中取一个值&nbsp;<code>x</code>（<code>1 &lt;= x &lt;= n</code>）；</li>
	<li>「二号」玩家也从&nbsp;<code>[1, n]</code>&nbsp;中取一个值&nbsp;<code>y</code>（<code>1 &lt;= y &lt;= n</code>）且&nbsp;<code>y != x</code>。</li>
</ul>

<p>「一号」玩家给值为&nbsp;<code>x</code>&nbsp;的节点染上红色，而「二号」玩家给值为&nbsp;<code>y</code>&nbsp;的节点染上蓝色。</p>

<p>之后两位玩家轮流进行操作，「一号」玩家先手。每一回合，玩家选择一个被他染过色的节点，将所选节点一个 <strong>未着色 </strong>的邻节点（即左右子节点、或父节点）进行染色（「一号」玩家染红色，「二号」玩家染蓝色）。</p>

<p>如果（且仅在此种情况下）当前玩家无法找到这样的节点来染色时，其回合就会被跳过。</p>

<p>若两个玩家都没有可以染色的节点时，游戏结束。着色节点最多的那位玩家获得胜利 ✌️。</p>

<p>现在，假设你是「二号」玩家，根据所给出的输入，假如存在一个&nbsp;<code>y</code>&nbsp;值可以确保你赢得这场游戏，则返回&nbsp;<code>true</code> ；若无法获胜，就请返回 <code>false</code> 。</p>
&nbsp;

<p><strong class="example">示例 1 ：</strong></p>
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1145.Binary%20Tree%20Coloring%20Game/images/1480-binary-tree-coloring-game.png" style="width: 500px; height: 310px;" />
<pre>
<strong>输入：</strong>root = [1,2,3,4,5,6,7,8,9,10,11], n = 11, x = 3
<strong>输出：</strong>true
<strong>解释：</strong>第二个玩家可以选择值为 2 的节点。</pre>

<p><strong class="example">示例 2 ：</strong></p>

<pre>
<strong>输入：</strong>root = [1,2,3], n = 3, x = 1
<strong>输出：</strong>false
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点数目为 <code>n</code></li>
	<li><code>1 &lt;= x &lt;= n &lt;= 100</code></li>
	<li><code>n</code> 是奇数</li>
	<li><code>1 &lt;= Node.val &lt;= n</code></li>
	<li>树中所有值 <strong>互不相同</strong></li>
</ul>

## 解法

**方法一：DFS**

我们先通过DFS，找到「一号」玩家着色点x所在的节点，记为node。

接下来，我们统计node的左子树、右子树的节点个数，分别记为l和r，而node父节点方向上的个数为n - l - r - 1。只要满足max(l, r, n - l - r - 1) > \frac{n}{2}，则「二号」玩家存在一个必胜策略。

时间复杂度O(n)，空间复杂度O(n)。其中n是节点总数。

### **Java**

```java
class Solution {
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        TreeNode node = dfs(root, x);
        int l = count(node.left);
        int r = count(node.right);
        return Math.max(Math.max(l, r), n - l - r - 1) > n / 2;
    }

    private TreeNode dfs(TreeNode root, int x) {
        if (root == null || root.val == x) {
            return root;
        }
        TreeNode node = dfs(root.left, x);
        return node == null ? dfs(root.right, x) : node;
    }

    private int count(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + count(root.left) + count(root.right);
    }
}
```
