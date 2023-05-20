# [117. 填充每个节点的下一个右侧节点指针 II](https://leetcode.cn/problems/populating-next-right-pointers-in-each-node-ii)

## 题目描述

<p>给定一个二叉树：</p>

<pre>
struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}</pre>

<p>填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 <code>NULL</code> 。</p>

<p>初始状态下，所有&nbsp;next 指针都被设置为 <code>NULL</code> 。</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0100-0199/0117.Populating%20Next%20Right%20Pointers%20in%20Each%20Node%20II/images/117_sample.png" style="width: 500px; height: 171px;" />
<pre>
<strong>输入</strong>：root = [1,2,3,4,5,null,7]
<strong>输出：</strong>[1,#,2,3,#,4,5,7,#]
<strong>解释：</strong>给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化输出按层序遍历顺序（由 next 指针连接），'#' 表示每层的末尾。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = []
<strong>输出：</strong>[]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li>树中的节点数在范围 <code>[0, 6000]</code> 内</li>
	<li><code>-100 &lt;= Node.val &lt;= 100</code></li>
</ul>

<p><strong>进阶：</strong></p>

<ul>
	<li>你只能使用常量级额外空间。</li>
	<li>使用递归解题也符合要求，本题中递归程序的隐式栈空间不计入额外空间复杂度。</li>
</ul>

<ul>
</ul>

## 解法

**方法一：BFS**

使用队列进行层序遍历，每次遍历一层时，将当前层的节点按顺序连接起来。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为二叉树的节点个数。

**方法二：空间优化**

方法一的空间复杂度较高，因为需要使用队列存储每一层的节点。我们可以使用常数空间来实现。

定义两个指针 $prev$ 和 $next$，分别指向下一层的前一个节点和第一个节点。遍历当前层的节点时，把下一层的节点串起来，同时找到下一层的第一个节点。当前层遍历完后，把下一层的第一个节点 $next$ 赋值给 $node$，继续遍历。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为二叉树的节点个数。

### **Java**

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        Deque<Node> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            Node p = null;
            for (int n = q.size(); n > 0; --n) {
                Node node = q.poll();
                if (p != null) {
                    p.next = node;
                }
                p = node;
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
        }
        return root;
    }
}
```

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    private Node prev, next;

    public Node connect(Node root) {
        Node node = root;
        while (node != null) {
            prev = null;
            next = null;
            while (node != null) {
                modify(node.left);
                modify(node.right);
                node = node.next;
            }
            node = next;
        }
        return root;
    }

    private void modify(Node curr) {
        if (curr == null) {
            return;
        }
        if (next == null) {
            next = curr;
        }
        if (prev != null) {
            prev.next = curr;
        }
        prev = curr;
    }
}
```

BFS:

DFS:
