# [823. 带因子的二叉树](https://leetcode.cn/problems/binary-trees-with-factors)

## 题目描述

<p>给出一个含有不重复整数元素的数组 <code>arr</code> ，每个整数 <code>arr[i]</code> 均大于 1。</p>

<p>用这些整数来构建二叉树，每个整数可以使用任意次数。其中：每个非叶结点的值应等于它的两个子结点的值的乘积。</p>

<p>满足条件的二叉树一共有多少个？答案可能很大，返回<strong> 对 </strong><code>10<sup>9</sup> + 7</code> <strong>取余</strong> 的结果。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> <code>arr = [2, 4]</code>
<strong>输出:</strong> 3
<strong>解释:</strong> 可以得到这些二叉树: <code>[2], [4], [4, 2, 2]</code></pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> <code>arr = [2, 4, 5, 10]</code>
<strong>输出:</strong> <code>7</code>
<strong>解释:</strong> 可以得到这些二叉树: <code>[2], [4], [5], [10], [4, 2, 2], [10, 2, 5], [10, 5, 2]</code>.</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>2 &lt;= arr[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>arr</code> 中的所有值 <strong>互不相同</strong></li>
</ul>

## 解法

**方法一：动态规划**

我们可以枚举 `arr` 中的每一个数a作为二叉树的根节点（根节点一定最大），然后枚举枚举左子树的值b，若a能被b整除，则右子树的值为a / b，若a / b也在 `arr` 中，则可以构成一棵二叉树。此时，以a为根节点的二叉树的个数为f(a) = f(b) \times f(a / b)，其中f(b)和f(a / b)分别为左子树和右子树的二叉树个数。

因此，我们先将 `arr` 排序，然后用f[i]表示以arr[i]为根节点的二叉树的个数，最终答案即为f[0] + f[1] + \cdots + f[n - 1]。

时间复杂度为O(n^2)，空间复杂度为O(n)。其中n为 `arr` 的长度。

### **Java**

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int numFactoredBinaryTrees(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        long[] f = new long[n];
        Arrays.fill(f, 1);
        Map<Integer, Integer> idx = new HashMap<>(n);
        for (int i = 0; i < n; ++i) {
            idx.put(arr[i], i);
        }
        for (int i = 0; i < n; ++i) {
            int a = arr[i];
            for (int j = 0; j < i; ++j) {
                int b = arr[j];
                if (a % b == 0) {
                    int c = a / b;
                    if (idx.containsKey(c)) {
                        int k = idx.get(c);
                        f[i] = (f[i] + f[j] * f[k]) % MOD;
                    }
                }
            }
        }
        long ans = 0;
        for (long v : f) {
            ans = (ans + v) % MOD;
        }
        return (int) ans;
    }
}
```
