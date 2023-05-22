# [254. 因子的组合](https://leetcode.cn/problems/factor-combinations)

## 题目描述

<p>整数可以被看作是其因子的乘积。</p>

<p>例如：</p>

<pre>8 = 2 x 2 x 2;
  = 2 x 4.</pre>

<p>请实现一个函数，该函数接收一个整数 <em>n</em>&nbsp;并返回该整数所有的因子组合。</p>

<p><strong>注意：</strong></p>

<ol>
	<li>你可以假定 <em>n</em> 为永远为正数。</li>
	<li>因子必须大于 1 并且小于 <em>n</em>。</li>
</ol>

<p><strong>示例 1：</strong></p>

<pre><strong>输入: </strong><code>1</code>
<strong>输出: </strong>[]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入: </strong><code>37</code>
<strong>输出: </strong>[]</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入: </strong><code>12</code>
<strong>输出:</strong>
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]</pre>

<p><strong>示例 4: </strong></p>

<pre><strong>输入: </strong><code>32</code>
<strong>输出:</strong>
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]
</pre>

## 解法

**方法一：回溯**

我们设计函数dfs(n, i)，其中n表示当前待分解的数，i表示当前分解的数的最大因子，函数的作用是将n分解为若干个因子，其中每个因子都不小于i，并将所有分解结果保存到ans中。

在函数dfs(n, i)中，我们从i开始枚举n的因子j，如果j是n的因子，那么我们将j加入当前分解结果，然后继续分解n / j，即调用函数dfs(n / j, j)。

时间复杂度O(\sqrt{n})。

### **Java**

```java
class Solution {
    private List<Integer> t = new ArrayList<>();
    private List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> getFactors(int n) {
        dfs(n, 2);
        return ans;
    }

    private void dfs(int n, int i) {
        if (!t.isEmpty()) {
            List<Integer> cp = new ArrayList<>(t);
            cp.add(n);
            ans.add(cp);
        }
        for (int j = i; j <= n / j; ++j) {
            if (n % j == 0) {
                t.add(j);
                dfs(n / j, j);
                t.remove(t.size() - 1);
            }
        }
    }
}
```
