# [100. 相同的树](https://leetcode.cn/problems/same-tree)

[English Version](/solution/0100-0199/0100.Same%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两棵二叉树的根节点 <code>p</code> 和 <code>q</code> ，编写一个函数来检验这两棵树是否相同。</p>

<p>如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0100.Same%20Tree/images/ex1.jpg" style="width: 622px; height: 182px;" />
<pre>
<strong>输入：</strong>p = [1,2,3], q = [1,2,3]
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0100.Same%20Tree/images/ex2.jpg" style="width: 382px; height: 182px;" />
<pre>
<strong>输入：</strong>p = [1,2], q = [1,null,2]
<strong>输出：</strong>false
</pre>

<p><strong>示例 3：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0100.Same%20Tree/images/ex3.jpg" style="width: 622px; height: 182px;" />
<pre>
<strong>输入：</strong>p = [1,2,1], q = [1,1,2]
<strong>输出：</strong>false
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>两棵树上的节点数目都在范围 <code>[0, 100]</code> 内</li>
	<li><code>-10<sup>4</sup> <= Node.val <= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：DFS**

我们可以使用 DFS 递归的方法来解决这个问题。

首先判断两个二叉树的根节点是否相同，如果两个根节点都为空，则两个二叉树相同，如果两个根节点中有且只有一个为空，则两个二叉树一定不同。如果两个根节点都不为空，则判断它们的值是否相同，如果不相同则两个二叉树一定不同，如果相同，则分别判断两个二叉树的左子树是否相同以及右子树是否相同。当以上所有条件都满足时，两个二叉树才相同。

时间复杂度 $O(\min(m, n))$，空间复杂度 $O(\min(m, n))$。其中 $m$ 和 $n$ 分别是两个二叉树的节点个数。空间复杂度主要取决于递归调用的层数，递归调用的层数不会超过较小的二叉树的节点个数。

**方法二：BFS**

我们也可以使用 BFS 迭代的方法来解决这个问题。

首先将两个二叉树的根节点分别加入两个队列。每次从两个队列各取出一个节点，进行如下比较操作。如果两个节点的值不相同，则两个二叉树的结构一定不同，如果两个节点的值相同，则判断两个节点的子节点是否为空，如果只有一个节点的左子节点为空，则两个二叉树的结构一定不同，如果只有一个节点的右子节点为空，则两个二叉树的结构一定不同，如果左右子节点的结构相同，则将两个节点的左子节点和右子节点分别加入两个队列，对于下一次迭代，将从两个队列各取出一个节点进行比较。当两个队列同时为空时，说明我们已经比较完了所有节点，两个二叉树的结构完全相同。

时间复杂度 $O(\min(m, n))$，空间复杂度 $O(\min(m, n))$。其中 $m$ 和 $n$ 分别是两个二叉树的节点个数。空间复杂度主要取决于队列中的元素个数，队列中的元素个数不会超过较小的二叉树的节点个数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

DFS：



BFS：



### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == q) return true;
        if (p == null || q == null || p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
```

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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == q) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        Deque<TreeNode> q1 = new ArrayDeque<>();
        Deque<TreeNode> q2 = new ArrayDeque<>();
        q1.offer(p);
        q2.offer(q);
        while (!q1.isEmpty() && !q2.isEmpty()) {
            p = q1.poll();
            q = q2.poll();
            if (p.val != q.val) {
                return false;
            }
            TreeNode la = p.left, ra = p.right;
            TreeNode lb = q.left, rb = q.right;
            if ((la != null && lb == null) || (lb != null && la == null)) {
                return false;
            }
            if ((ra != null && rb == null) || (rb != null && ra == null)) {
                return false;
            }
            if (la != null) {
                q1.offer(la);
                q2.offer(lb);
            }
            if (ra != null) {
                q1.offer(ra);
                q2.offer(rb);
            }
        }
        return true;
    }
}
```

















### **TypeScript**















### **...**

```

```


