# [1214. 查找两棵二叉搜索树之和](https://leetcode.cn/problems/two-sum-bsts)

## 题目描述

<p>给出两棵二叉搜索树的根节点&nbsp;<meta charset="UTF-8" /><code>root1</code>&nbsp;和<meta charset="UTF-8" />&nbsp;<code>root2</code>&nbsp;，请你从两棵树中各找出一个节点，使得这两个节点的值之和等于目标值&nbsp;<code>Target</code>。</p>

<p>如果可以找到返回&nbsp;<code>True</code>，否则返回&nbsp;<code>False</code>。</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1214.Two%20Sum%20BSTs/images/ex1.png" style="height: 169px; width: 369px;" /></p>

<pre>
<strong>输入：</strong>root1 = [2,1,4], root2 = [1,0,3], target = 5
<strong>输出：</strong>true
<strong>解释：</strong>2 加 3 和为 5 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1214.Two%20Sum%20BSTs/images/ex2.png" style="height: 290px; width: 453px;" /></p>

<pre>
<strong>输入：</strong>root1 = [0,-10,10], root2 = [5,1,7,0,2], target = 18
<strong>输出：</strong>false</pre>

<p><strong>提示：</strong></p>

<ul>
	<li>每棵树上节点数在<meta charset="UTF-8" />&nbsp;<code>[1, 5000]</code>&nbsp;范围内。<meta charset="UTF-8" /></li>
	<li><code>-10<sup>9</sup>&nbsp;&lt;= Node.val, target &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

先中序遍历二叉搜索树 root1、root2 得到两个有序列表 vals1、vals2。然后利用双指针，i 指向 vals1 头部，j 指向 vals2 尾部，判断双指针指向的两元素和 s 与 target 的大小关系，若相等，直接返回 true，若 `s < target`，i 指针往右移动，否则 j 指针往左移动。

遍历结束，说明不存在两节点和为 target 的元素，直接返回 false。

### **Java**

```java
class Solution {
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        List<Integer> vals1 = new ArrayList<>();
        List<Integer> vals2 = new ArrayList<>();
        inorder(root1, vals1);
        inorder(root2, vals2);
        int i = 0, j = vals2.size() - 1;
        while (i < vals1.size() && j >= 0) {
            int s = vals1.get(i) + vals2.get(j);
            if (s == target) {
                return true;
            }
            if (s < target) {
                ++i;
            } else {
                --j;
            }
        }
        return false;
    }

    private void inorder(TreeNode root, List<Integer> vals) {
        if (root != null) {
            inorder(root.left, vals);
            vals.add(root.val);
            inorder(root.right, vals);
        }
    }
}
```
