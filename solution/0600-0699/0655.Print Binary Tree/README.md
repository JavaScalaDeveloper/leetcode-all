# [655. 输出二叉树](https://leetcode.cn/problems/print-binary-tree)

## 题目描述

<p>给你一棵二叉树的根节点 <code>root</code> ，请你构造一个下标从 <strong>0</strong> 开始、大小为 <code>m x n</code> 的字符串矩阵 <code>res</code> ，用以表示树的 <strong>格式化布局</strong> 。构造此格式化布局矩阵需要遵循以下规则：</p>

<ul>
	<li>树的 <strong>高度</strong> 为 <code>height</code> ，矩阵的行数 <code>m</code> 应该等于 <code>height + 1</code> 。</li>
	<li>矩阵的列数 <code>n</code> 应该等于 <code>2<sup>height+1</sup> - 1</code> 。</li>
	<li><strong>根节点</strong> 需要放置在 <strong>顶行</strong> 的 <strong>正中间</strong> ，对应位置为 <code>res[0][(n-1)/2]</code> 。</li>
	<li>对于放置在矩阵中的每个节点，设对应位置为 <code>res[r][c]</code> ，将其左子节点放置在 <code>res[r+1][c-2<sup>height-r-1</sup>]</code> ，右子节点放置在 <code>res[r+1][c+2<sup>height-r-1</sup>]</code> 。</li>
	<li>继续这一过程，直到树中的所有节点都妥善放置。</li>
	<li>任意空单元格都应该包含空字符串 <code>""</code> 。</li>
</ul>

<p>返回构造得到的矩阵<em> </em><code>res</code> 。</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0655.Print%20Binary%20Tree/images/print1-tree.jpg" style="width: 141px; height: 181px;" />
<pre>
<strong>输入：</strong>root = [1,2]
<strong>输出：</strong>
[["","1",""],
&nbsp;["2","",""]]
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0600-0699/0655.Print%20Binary%20Tree/images/print2-tree.jpg" style="width: 207px; height: 302px;" />
<pre>
<strong>输入：</strong>root = [1,2,3,null,4]
<strong>输出：</strong>
[["","","","1","","",""],
&nbsp;["","2","","","","3",""],
&nbsp;["","","4","","","",""]]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点数在范围 <code>[1, 2<sup>10</sup>]</code> 内</li>
	<li><code>-99 &lt;= Node.val &lt;= 99</code></li>
	<li>树的深度在范围 <code>[1, 10]</code> 内</li>
</ul>

## 解法

**方法一：两次 DFS**

先通过 `DFS` 求二叉树的高度 $h$（高度从 `0` 开始），然后根据 $h$ 求得结果列表的行数 $m$ 和列数 $n$。

根据 $m$, $n$ 初始化结果列表 `ans`，然后 `DFS` 遍历二叉树，依次在每个位置填入二叉树节点值（字符串形式）即可。

时间复杂度 $O(h\times 2^h)$，空间复杂度 $O(h)$。其中 $h$ 是二叉树的高度。忽略结果返回值的空间消耗。

**方法二：两次 BFS**

方法一中，我们是通过 `DFS` 来求二叉树的高度，我们也可以改成 `BFS` 的方式，逐层往下扩展，那么扩展的层数就是二叉树的高度。

同样，我们初始化结果列表 `ans`，然后 `BFS` 遍历二叉树，依次在每个位置填入二叉树节点值（字符串形式）即可。

时间复杂度 $O(h\times 2^h)$，空间复杂度 $O(h)$。其中 $h$ 是二叉树的高度。忽略结果返回值的空间消耗。

### **Java**

```java
class Solution {
    public List<List<String>> printTree(TreeNode root) {
        int h = height(root);
        int m = h + 1, n = (1 << (h + 1)) - 1;
        String[][] res = new String[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(res[i], "");
        }
        dfs(root, res, h, 0, (n - 1) / 2);
        List<List<String>> ans = new ArrayList<>();
        for (String[] t : res) {
            ans.add(Arrays.asList(t));
        }
        return ans;
    }

    private void dfs(TreeNode root, String[][] res, int h, int r, int c) {
        if (root == null) {
            return;
        }
        res[r][c] = String.valueOf(root.val);
        dfs(root.left, res, h, r + 1, c - (1 << (h - r - 1)));
        dfs(root.right, res, h, r + 1, c + (1 << (h - r - 1)));
    }

    private int height(TreeNode root) {
        if (root == null) {
            return -1;
        }
        return 1 + Math.max(height(root.left), height(root.right));
    }
}
```

```java
class Solution {
    public List<List<String>> printTree(TreeNode root) {
        int h = height(root);
        int m = h + 1, n = (1 << (h + 1)) - 1;
        String[][] res = new String[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(res[i], "");
        }
        Deque<Tuple> q = new ArrayDeque<>();
        q.offer(new Tuple(root, 0, (n - 1) / 2));
        while (!q.isEmpty()) {
            Tuple p = q.pollFirst();
            root = p.node;
            int r = p.r, c = p.c;
            res[r][c] = String.valueOf(root.val);
            if (root.left != null) {
                q.offer(new Tuple(root.left, r + 1, c - (1 << (h - r - 1))));
            }
            if (root.right != null) {
                q.offer(new Tuple(root.right, r + 1, c + (1 << (h - r - 1))));
            }
        }
        List<List<String>> ans = new ArrayList<>();
        for (String[] t : res) {
            ans.add(Arrays.asList(t));
        }
        return ans;
    }

    private int height(TreeNode root) {
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int h = -1;
        while (!q.isEmpty()) {
            ++h;
            for (int n = q.size(); n > 0; --n) {
                root = q.pollFirst();
                if (root.left != null) {
                    q.offer(root.left);
                }
                if (root.right != null) {
                    q.offer(root.right);
                }
            }
        }
        return h;
    }
}

class Tuple {
    TreeNode node;
    int r;
    int c;

    public Tuple(TreeNode node, int r, int c) {
        this.node = node;
        this.r = r;
        this.c = c;
    }
}
```
