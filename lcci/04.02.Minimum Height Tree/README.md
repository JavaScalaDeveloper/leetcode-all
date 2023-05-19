# [面试题 04.02. 最小高度树](https://leetcode.cn/problems/minimum-height-tree-lcci)

[English Version](/lcci/04.02.Minimum%20Height%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个有序整数数组，元素各不相同且按升序排列，编写一个算法，创建一棵高度最小的二叉搜索树。</p><strong>示例:</strong><pre>给定有序数组: [-10,-3,0,5,9],<br><br>一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：<br><br>          0 <br>         / &#92 <br>       -3   9 <br>       /   / <br>     -10  5 <br></pre>

## 解法

**方法一：递归**

先找到数组的中间点，作为二叉搜索树的根节点，然后递归左右子树即可。

时间复杂度 $O(n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组长度。

### **Java**

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private int[] nums;

    public TreeNode sortedArrayToBST(int[] nums) {
        this.nums = nums;
        return dfs(0, nums.length - 1);
    }

    private TreeNode dfs(int l, int r) {
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        return new TreeNode(nums[mid], dfs(l, mid - 1), dfs(mid + 1, r));
    }
}
```

### **TypeScript**
