# [993. 二叉树的堂兄弟节点](https://leetcode.cn/problems/cousins-in-binary-tree)

## 题目描述

<p>在二叉树中，根节点位于深度 <code>0</code> 处，每个深度为 <code>k</code> 的节点的子节点位于深度 <code>k+1</code> 处。</p>

<p>如果二叉树的两个节点深度相同，但<strong> 父节点不同</strong> ，则它们是一对<em>堂兄弟节点</em>。</p>

<p>我们给出了具有唯一值的二叉树的根节点 <code>root</code> ，以及树中两个不同节点的值 <code>x</code> 和 <code>y</code> 。</p>

<p>只有与值 <code>x</code> 和 <code>y</code> 对应的节点是堂兄弟节点时，才返回 <code>true</code> 。否则，返回 <code>false</code>。</p>

<p> </p>

<p><strong>示例 1：<br />
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0993.Cousins%20in%20Binary%20Tree/images/q1248-01.png" style="height: 160px; width: 180px;" /></strong></p>

<pre>
<strong>输入：</strong>root = [1,2,3,4], x = 4, y = 3
<strong>输出：</strong>false
</pre>

<p><strong>示例 2：<br />
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0993.Cousins%20in%20Binary%20Tree/images/q1248-02.png" style="height: 160px; width: 201px;" /></strong></p>

<pre>
<strong>输入：</strong>root = [1,2,3,null,4,null,5], x = 5, y = 4
<strong>输出：</strong>true
</pre>

<p><strong>示例 3：</strong></p>

<p><strong><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0993.Cousins%20in%20Binary%20Tree/images/q1248-03.png" style="height: 160px; width: 156px;" /></strong></p>

<pre>
<strong>输入：</strong>root = [1,2,3,null,4], x = 2, y = 3
<strong>输出：</strong>false</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>二叉树的节点数介于 <code>2</code> 到 <code>100</code> 之间。</li>
	<li>每个节点的值都是唯一的、范围为 <code>1</code> 到 <code>100</code> 的整数。</li>
</ul>

<p> </p>

## 解法

**方法一：BFS**

我们定义一个队列 $q$，队列中存储的是节点和其父节点。初始时，将根节点和空节点放入队列中。

每次从队列中取出一个节点，如果该节点的值为 $x$ 或 $y$，则记录该节点的父节点和深度。如果该节点的左右子节点不为空，则将左右子节点和该节点放入队列中。

当队列中所有节点都处理完毕后，如果 $x$ 和 $y$ 的深度相同且父节点不同，则返回 $true$，否则返回 $false$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉树的节点数。

**方法二：DFS**

我们设计一个函数 $dfs(root, fa, d)$，表示从根节点 $root$ 出发，其父节点为 $fa$，深度为 $d$，进行深度优先搜索。

在函数中，我们首先判断当前节点是否为空，如果为空，则直接返回。如果当前节点的值为 $x$ 或 $y$，则记录该节点的父节点和深度。然后对当前节点的左右子节点分别调用函数 $dfs$，其中父节点为当前节点，深度为当前深度加 $1$。即 $dfs(root.left, root, d + 1)$ 和 $dfs(root.right, root, d + 1)$。

当整棵二叉树遍历完毕后，如果 $x$ 和 $y$ 的深度相同且父节点不同，则返回 $true$，否则返回 $false$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是二叉树的节点数。

BFS：

DFS：

### **Java**

BFS：

```java
class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        TreeNode p1 = null, p2 = null;
        int d1 = 0, d2 = 0;
        Deque<TreeNode[]> q = new ArrayDeque<>();
        q.offer(new TreeNode[]{root, null});
        int d = 0;
        while (!q.isEmpty()) {
            for (int n = q.size(); n > 0; --n) {
                var p = q.poll();
                TreeNode node = p[0], fa = p[1];
                if (node.val == x) {
                    p1 = fa;
                    d1 = d;
                }
                if (node.val == y) {
                    p2 = fa;
                    d2 = d;
                }
                if (node.left != null) {
                    q.offer(new TreeNode[]{node.left, node});
                }
                if (node.right != null) {
                    q.offer(new TreeNode[]{node.right, node});
                }
            }
            ++d;
        }
        return p1 != p2 && d1 == d2;
    }
}
```

DFS：

```java
class Solution {
    private int x, y;
    private TreeNode p1, p2;
    private int d1, d2;

    public boolean isCousins(TreeNode root, int x, int y) {
        this.x = x;
        this.y = y;
        dfs(root, null, 0);
        return p1 != p2 && d1 == d2;
    }

    private void dfs(TreeNode root, TreeNode p, int d) {
        if (root == null) {
            return;
        }
        if (root.val == x) {
            p1 = p;
            d1 = d;
        }
        if (root.val == y) {
            p2 = p;
            d2 = d;
        }
        dfs(root.left, root, d + 1);
        dfs(root.right, root, d + 1);
    }
}
```

BFS：

DFS：

BFS：

DFS：
