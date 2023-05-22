# [面试题 33. 二叉搜索树的后序遍历序列](https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/)

## 题目描述



<p>输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回&nbsp;<code>true</code>，否则返回&nbsp;<code>false</code>。假设输入的数组的任意两个数字都互不相同。</p>



<p>参考以下这颗二叉搜索树：</p>

<pre>     5
    / \
   2   6
  / \
 1   3</pre>

<p><strong>示例 1：</strong></p>

<pre><strong>输入: </strong>[1,6,3,2,5]
<strong>输出: </strong>false</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入: </strong>[1,3,2,6,5]
<strong>输出: </strong>true</pre>



<p><strong>提示：</strong></p>

<ol>
	<li><code>数组长度 &lt;= 1000</code></li>
</ol>

## 解法

**方法一：递归**

后序遍历的最后一个元素为根节点，根据二叉搜索树的性质，根节点左边的元素都小于根节点，根节点右边的元素都大于根节点。因此，我们找到第一个大于根节点的位置 $i$，那么 $i$ 右边的元素都应该大于根节点，否则返回 `false`。然后递归判断左右子树。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 为数组长度。

**方法二：单调栈**

后序遍历的顺序为“左、右、根”，如果我们从右往左遍历数组，那么顺序就变成“根、右、左”，根据二叉搜索树的性质，右子树所有节点值均大于根节点值。

因此，从右往左遍历数组，就是从根节点往右子树走，此时值逐渐变大，直到遇到一个递减的节点，此时的节点应该属于左子树节点。我们找到该节点的直接父节点，那么此后其它节点都应该小于该父节点，否则返回 `false`。然后继续遍历，直到遍历完整个数组。

此过程，我们借助栈来实现，具体步骤如下：

我们首先初始化一个无穷大的父节点值 $mx$，然后初始化一个空栈。

接下来，我们从右往左遍历数组，对于每个遍历到的元素 $x$：

-   如果 $x$ 大于 $mx$，说明当前节点不满足二叉搜索树的性质，返回 `false`。
-   否则，如果当前栈不为空，且栈顶元素大于 $x$，说明当前节点为左子树节点，我们循环将栈顶元素出栈并赋值给 $mx$，直到栈为空或者栈顶元素小于等于 $x$，然后将 $x$ 入栈。

遍历结束后，返回 `true`。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组长度。

### **Java**

```java
class Solution {
    private int[] postorder;

    public boolean verifyPostorder(int[] postorder) {
        this.postorder = postorder;
        return dfs(0, postorder.length - 1);
    }

    private boolean dfs(int l, int r) {
        if (l >= r) {
            return true;
        }
        int v = postorder[r];
        int i = l;
        while (i < r && postorder[i] < v) {
            ++i;
        }
        for (int j = i; j < r; ++j) {
            if (postorder[j] < v) {
                return false;
            }
        }
        return dfs(l, i - 1) && dfs(i, r - 1);
    }
}
```

```java
class Solution {
    public boolean verifyPostorder(int[] postorder) {
        int mx = 1 << 30;
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = postorder.length - 1; i >= 0; --i) {
            int x = postorder[i];
            if (x > mx) {
                return false;
            }
            while (!stk.isEmpty() && stk.peek() > x) {
                mx = stk.pop();
            }
            stk.push(x);
        }
        return true;
    }
}
```
