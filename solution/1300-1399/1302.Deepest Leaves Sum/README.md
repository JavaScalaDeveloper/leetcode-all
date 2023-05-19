# [1302. 层数最深叶子节点的和](https://leetcode.cn/problems/deepest-leaves-sum)

[English Version](/solution/1300-1399/1302.Deepest%20Leaves%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一棵二叉树的根节点 <code>root</code> ，请你返回 <strong>层数最深的叶子节点的和</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1302.Deepest%20Leaves%20Sum/images/1483_ex1.png" style="height: 265px; width: 273px;" /></strong></p>

<pre>
<strong>输入：</strong>root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
<strong>输出：</strong>15
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
<strong>输出：</strong>19
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>树中节点数目在范围 <code>[1, 10<sup>4</sup>]</code> 之间。</li>
	<li><code>1 <= Node.val <= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：BFS**

可以忽略一些细节，每次都统计当前遍历层级的数值和，当 BFS 结束时，最后一次数值和便是结果。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是树中节点的数目。

**方法二：DFS**

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是树中节点的数目。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->





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
    public int deepestLeavesSum(TreeNode root) {
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int ans = 0;
        while (!q.isEmpty()) {
            ans = 0;
            for (int n = q.size(); n > 0; --n) {
                root = q.pollFirst();
                ans += root.val;
                if (root.left != null) {
                    q.offer(root.left);
                }
                if (root.right != null) {
                    q.offer(root.right);
                }
            }
        }
        return ans;
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
    int mx;
    int ans;

    public int deepestLeavesSum(TreeNode root) {
        dfs(root, 1);
        return ans;
    }

    private void dfs(TreeNode root, int i) {
        if (root == null) {
            return;
        }
        if (i > mx) {
            mx = i;
            ans = root.val;
        } else if (i == mx) {
            ans += root.val;
        }
        dfs(root.left, i + 1);
        dfs(root.right, i + 1);
    }
}
```













### **C**

```c
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */


void dfs(struct TreeNode* root, int depth, int* maxDepth, int* res) {
    if (!root->left && !root->right) {
        if (depth == *maxDepth) {
            *res += root->val;
        } else if (depth > *maxDepth) {
            *maxDepth = depth;
            *res = root->val;
        }
        return;
    }
    if (root->left) {
        dfs(root->left, depth + 1, maxDepth, res);
    }
    if (root->right) {
        dfs(root->right, depth + 1, maxDepth, res);
    }
}

int deepestLeavesSum(struct TreeNode* root){
    int res = 0;
    int maxDepth = 0;
    dfs(root, 0, &maxDepth, &res);
    return res;
}
```

### **TypeScript**









### **...**

```

```


