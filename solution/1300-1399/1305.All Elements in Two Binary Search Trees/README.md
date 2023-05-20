# [1305. 两棵二叉搜索树中的所有元素](https://leetcode.cn/problems/all-elements-in-two-binary-search-trees)

## 题目描述

<p>给你&nbsp;<code>root1</code> 和 <code>root2</code>&nbsp;这两棵二叉搜索树。请你返回一个列表，其中包含&nbsp;<strong>两棵树&nbsp;</strong>中的所有整数并按 <strong>升序</strong> 排序。.</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1305.All%20Elements%20in%20Two%20Binary%20Search%20Trees/images/q2-e1.png" /></p>

<pre>
<strong>输入：</strong>root1 = [2,1,4], root2 = [1,0,3]
<strong>输出：</strong>[0,1,1,2,3,4]
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1305.All%20Elements%20in%20Two%20Binary%20Search%20Trees/images/q2-e5-.png" /></p>

<pre>
<strong>输入：</strong>root1 = [1,null,8], root2 = [8,1]
<strong>输出：</strong>[1,1,8,8]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li>每棵树的节点数在&nbsp;<code>[0, 5000]</code> 范围内</li>
	<li><code>-10<sup>5</sup>&nbsp;&lt;= Node.val &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

二叉树中序遍历 + 有序列表归并。

### **Java**

```java
class Solution {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> t1 = new ArrayList<>();
        List<Integer> t2 = new ArrayList<>();
        dfs(root1, t1);
        dfs(root2, t2);
        return merge(t1, t2);
    }

    private void dfs(TreeNode root, List<Integer> t) {
        if (root == null) {
            return;
        }
        dfs(root.left, t);
        t.add(root.val);
        dfs(root.right, t);
    }

    private List<Integer> merge(List<Integer> t1, List<Integer> t2) {
        List<Integer> ans = new ArrayList<>();
        int i = 0, j = 0;
        while (i < t1.size() && j < t2.size()) {
            if (t1.get(i) <= t2.get(j)) {
                ans.add(t1.get(i++));
            } else {
                ans.add(t2.get(j++));
            }
        }
        while (i < t1.size()) {
            ans.add(t1.get(i++));
        }
        while (j < t2.size()) {
            ans.add(t2.get(j++));
        }
        return ans;
    }
}
```
