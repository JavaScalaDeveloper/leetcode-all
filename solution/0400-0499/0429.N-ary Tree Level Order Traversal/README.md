# [429. N 叉树的层序遍历](https://leetcode.cn/problems/n-ary-tree-level-order-traversal)

## 题目描述

<p>给定一个 N 叉树，返回其节点值的<em>层序遍历</em>。（即从左到右，逐层遍历）。</p>

<p>树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。</p>



<p><strong>示例 1：</strong></p>

<p><img src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0429.N-ary%20Tree%20Level%20Order%20Traversal/images/narytreeexample.png" style="width: 100%; max-width: 300px;" /></p>

<pre>
<strong>输入：</strong>root = [1,null,3,2,4,null,5,6]
<strong>输出：</strong>[[1],[3,2,4],[5,6]]
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0429.N-ary%20Tree%20Level%20Order%20Traversal/images/sample_4_964.png" style="width: 296px; height: 241px;" /></p>

<pre>
<strong>输入：</strong>root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
<strong>输出：</strong>[[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li>树的高度不会超过 <code>1000</code></li>
	<li>树的节点总数在 <code>[0, 10^4]</code> 之间</li>
</ul>

## 解法

**方法一：BFS**

借助队列，逐层遍历。

时间复杂度O(n)。

**方法二：DFS**

按深度遍历。

假设当前深度为 i，遍历到的节点为 root。若结果列表 `ans[i]` 不存在，则创建一个空列表放入 ans 中，然后将 `root.val` 放入 `ans[i]`。接着往下一层遍历（root 的子节点）。

时间复杂度O(n)。

BFS：

DFS：

### **Java**

BFS：

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Deque<Node> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            List<Integer> t = new ArrayList<>();
            for (int n = q.size(); n > 0; --n) {
                root = q.poll();
                t.add(root.val);
                q.addAll(root.children);
            }
            ans.add(t);
        }
        return ans;
    }
}
```

DFS：

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(root, 0, ans);
        return ans;
    }

    private void dfs(Node root, int i, List<List<Integer>> ans) {
        if (root == null) {
            return;
        }
        if (ans.size() <= i) {
            ans.add(new ArrayList<>());
        }
        ans.get(i++).add(root.val);
        for (Node child : root.children) {
            dfs(child, i, ans);
        }
    }
}
```

BFS：

DFS：

BFS：

DFS：

BFS：

DFS：
