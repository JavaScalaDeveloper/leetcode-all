# [1130. 叶值的最小代价生成树](https://leetcode.cn/problems/minimum-cost-tree-from-leaf-values)

## 题目描述

<p>给你一个正整数数组&nbsp;<code>arr</code>，考虑所有满足以下条件的二叉树：</p>

<ul>
	<li>每个节点都有 <code>0</code> 个或是 <code>2</code> 个子节点。</li>
	<li>数组&nbsp;<code>arr</code>&nbsp;中的值与树的中序遍历中每个叶节点的值一一对应。</li>
	<li>每个非叶节点的值等于其左子树和右子树中叶节点的最大值的乘积。</li>
</ul>

<p>在所有这样的二叉树中，返回每个非叶节点的值的最小可能总和。这个和的值是一个&nbsp;32 位整数。</p>

<p>如果一个节点有 0 个子节点，那么该节点为叶节点。</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1130.Minimum%20Cost%20Tree%20From%20Leaf%20Values/images/tree1.jpg" style="width: 500px; height: 169px;" />
<pre>
<strong>输入：</strong>arr = [6,2,4]
<strong>输出：</strong>32
<strong>解释：</strong>有两种可能的树，第一种的非叶节点的总和为 36 ，第二种非叶节点的总和为 32 。 
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1130.Minimum%20Cost%20Tree%20From%20Leaf%20Values/images/tree2.jpg" style="width: 224px; height: 145px;" />
<pre>
<strong>输入：</strong>arr = [4,11]
<strong>输出：</strong>44
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= arr.length &lt;= 40</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 15</code></li>
	<li>答案保证是一个 32 位带符号整数，即小于&nbsp;<code>2<sup>31</sup></code> 。</li>
</ul>

## 解法

**方法一：记忆化搜索**

根据题目描述，数组arr中的值与树的中序遍历中每个叶节点的值一一对应，因此可以将数组arr中的值看作是树的叶节点，我们可以将数组划分为左右两个子数组，分别对应树的左右子树，递归地每个子树的所有非叶子节点的值的最小可能总和。

我们设计一个函数dfs(i, j)，表示数组arr中下标范围[i, j]内的所有叶节点的值的最小可能总和，那么答案就是dfs(0, n - 1)，其中n为数组arr的长度。

函数dfs(i, j)的计算过程如下：

-   如果i = j，说明数组arr中只有一个元素，因此dfs(i, j) = 0。
-   否则，我们枚举k \in [i, j - 1]，将数组arr划分为两个子数组arr[i \cdots k]和arr[k + 1 \cdots j]，对于每个k，我们计算dfs(i, k)和dfs(k + 1, j)，其中dfs(i, k)表示数组arr中下标范围[i, k]内的所有叶节点的值的最小可能总和，而dfs(k + 1, j)表示数组arr中下标范围[k + 1, j]内的所有叶节点的值的最小可能总和，那么dfs(i, j) = min_{i ≤ k < j} \{dfs(i, k) + dfs(k + 1, j) + max_{i ≤ t ≤ k} \{arr[t]\} max_{k < t ≤ j} \{arr[t]\}\}。

上述递归过程中，我们可以使用记忆化搜索的方法进行优化，避免重复计算。

最后，我们返回dfs(0, n - 1)即可。

时间复杂度O(n^3)，空间复杂度O(n^2)。其中n为数组arr的长度。

**方法二：动态规划**

我们可以将方法一中的记忆化搜索改为动态规划的方式进行求解。

定义f[i][j]表示数组arr中下标范围[i, j]内的所有叶节点的值的最小可能总和，而g[i][j]表示数组arr中下标范围[i, j]内的所有叶节点的最大值，那么f[i][j] = min_{i ≤ k < j} \{f[i][k] + f[k + 1][j] + g[i][k] \cdot g[k + 1][j]\}，其中g[i][j] = max_{i ≤ k ≤ j} \{arr[k]\}。

最后，我们返回f[0][n - 1]即可。

时间复杂度O(n^3)，空间复杂度O(n^2)。其中n为数组arr的长度。

### **Java**

```java
class Solution {
    private Integer[][] f;
    private int[][] g;

    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        f = new Integer[n][n];
        g = new int[n][n];
        for (int i = 0; i < n; i++) {
            g[i][i] = arr[i];
            for (int j = i + 1; j < n; j++) {
                g[i][j] = Math.max(g[i][j - 1], arr[j]);
            }
        }
        return dfs(0, n - 1);
    }

    private int dfs(int i, int j) {
        if (i == j) {
            return 0;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        int ans = 1 << 30;
        for (int k = i; k < j; k++) {
            ans = Math.min(ans, dfs(i, k) + dfs(k + 1, j) + g[i][k] * g[k + 1][j]);
        }
        return f[i][j] = ans;
    }
}
```

```java
class Solution {
    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        int[][] f = new int[n][n];
        int[][] g = new int[n][n];
        for (int i = 0; i < n; ++i) {
            g[i][i] = arr[i];
            for (int j = i + 1; j < n; ++j) {
                g[i][j] = Math.max(g[i][j - 1], arr[j]);
            }
        }
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = 1 << 30;
                for (int k = i; k < j; ++k) {
                    f[i][j] = Math.min(f[i][j], f[i][k] + f[k + 1][j] + g[i][k] * g[k + 1][j]);
                }
            }
        }
        return f[0][n - 1];
    }
}
```
