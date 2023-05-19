# [216. 组合总和 III](https://leetcode.cn/problems/combination-sum-iii)

[English Version](/solution/0200-0299/0216.Combination%20Sum%20III/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>找出所有相加之和为&nbsp;<code>n</code><em> </em>的&nbsp;<code>k</code><strong>&nbsp;</strong>个数的组合，且满足下列条件：</p>

<ul>
	<li>只使用数字1到9</li>
	<li>每个数字&nbsp;<strong>最多使用一次</strong>&nbsp;</li>
</ul>

<p>返回 <em>所有可能的有效组合的列表</em> 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> <em><strong>k</strong></em> = 3, <em><strong>n</strong></em> = 7
<strong>输出:</strong> [[1,2,4]]
<strong>解释:</strong>
1 + 2 + 4 = 7
没有其他符合的组合了。</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> <em><strong>k</strong></em> = 3, <em><strong>n</strong></em> = 9
<strong>输出:</strong> [[1,2,6], [1,3,5], [2,3,4]]
<strong>解释:
</strong>1 + 2 + 6 = 9
1 + 3 + 5 = 9
2 + 3 + 4 = 9
没有其他符合的组合了。</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> k = 4, n = 1
<strong>输出:</strong> []
<strong>解释:</strong> 不存在有效的组合。
在[1,9]范围内使用4个不同的数字，我们可以得到的最小和是1+2+3+4 = 10，因为10 &gt; 1，没有有效的组合。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>2 &lt;= k &lt;= 9</code></li>
	<li><code>1 &lt;= n &lt;= 60</code></li>
</ul>

## 解法

**方法一：剪枝 + 回溯（两种方式）**

我们设计一个函数 $dfs(i, s)$，表示当前枚举到数字 $i$，还剩下和为 $s$ 的数字需要枚举，当前搜索路径为 $t$，答案为 $ans$。

函数 $dfs(i, s)$ 的执行逻辑如下：

方式一：

-   如果 $s = 0$，且当前搜索路径 $t$ 的长度为 $k$，说明找到了一组答案，将 $t$ 加入 $ans$ 中，然后返回。
-   如果 $i \gt 9$ 或者 $i \gt s$ 或者当前搜索路径 $t$ 的长度大于 $k$，说明当前搜索路径不可能是答案，直接返回。
-   否则，我们可以选择将数字 $i$ 加入搜索路径 $t$ 中，然后继续搜索，即执行 $dfs(i + 1, s - i)$，搜索完成后，将 $i$ 从搜索路径 $t$ 中移除；我们也可以选择不将数字 $i$ 加入搜索路径 $t$ 中，直接执行 $dfs(i + 1, s)$。

方式二：

-   如果 $s = 0$，且当前搜索路径 $t$ 的长度为 $k$，说明找到了一组答案，将 $t$ 加入 $ans$ 中，然后返回。
-   如果 $i \gt 9$ 或者 $i \gt s$ 或者当前搜索路径 $t$ 的长度大于 $k$，说明当前搜索路径不可能是答案，直接返回。
-   否则，我们枚举下一个数字 $j$，即 $j \in [i, 9]$，将数字 $j$ 加入搜索路径 $t$ 中，然后继续搜索，即执行 $dfs(j + 1, s - j)$，搜索完成后，将 $j$ 从搜索路径 $t$ 中移除。

在主函数中，我们调用 $dfs(1, n)$，即从数字 $1$ 开始枚举，剩下和为 $n$ 的数字需要枚举。搜索完成后，即可得到所有的答案。

时间复杂度 $(C_{9}^k \times k)$，空间复杂度 $O(k)$。

**方法二：二进制枚举**

我们可以用一个长度为 $9$ 的二进制整数表示数字 $1$ 到 $9$ 的选取情况，其中二进制整数的第 $i$ 位表示数字 $i + 1$ 是否被选取，如果第 $i$ 位为 $1$，则表示数字 $i + 1$ 被选取，否则表示数字 $i + 1$ 没有被选取。

我们在 $[0, 2^9)$ 范围内枚举二进制整数，对于当前枚举到的二进制整数 $mask$，如果 $mask$ 的二进制表示中 $1$ 的个数为 $k$，且 $mask$ 的二进制表示中 $1$ 所对应的数字之和为 $n$，则说明 $mask$ 对应的数字选取方案是一组答案。我们将 $mask$ 对应的数字选取方案加入答案即可。

时间复杂度 $O(2^9 \times 9)$，空间复杂度 $O(k)$。

相似题目：

-   [39. 组合总和](/solution/0000-0099/0039.Combination%20Sum/README.md)
-   [40. 组合总和 II](/solution/0000-0099/0040.Combination%20Sum%20II/README.md)
-   [77. 组合](/solution/0000-0099/0077.Combinations/README.md)

### **Java**

```java
class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> t = new ArrayList<>();
    private int k;

    public List<List<Integer>> combinationSum3(int k, int n) {
        this.k = k;
        dfs(1, n);
        return ans;
    }

    private void dfs(int i, int s) {
        if (s == 0) {
            if (t.size() == k) {
                ans.add(new ArrayList<>(t));
            }
            return;
        }
        if (i > 9 || i > s || t.size() >= k) {
            return;
        }
        t.add(i);
        dfs(i + 1, s - i);
        t.remove(t.size() - 1);
        dfs(i + 1, s);
    }
}
```

```java
class Solution {
    private List<List<Integer>> ans = new ArrayList<>();
    private List<Integer> t = new ArrayList<>();
    private int k;

    public List<List<Integer>> combinationSum3(int k, int n) {
        this.k = k;
        dfs(1, n);
        return ans;
    }

    private void dfs(int i, int s) {
        if (s == 0) {
            if (t.size() == k) {
                ans.add(new ArrayList<>(t));
            }
            return;
        }
        if (i > 9 || i > s || t.size() >= k) {
            return;
        }
        for (int j = i; j <= 9; ++j) {
            t.add(j);
            dfs(j + 1, s - j);
            t.remove(t.size() - 1);
        }
    }
}
```

```java
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int mask = 0; mask < 1 << 9; ++mask) {
            if (Integer.bitCount(mask) == k) {
                List<Integer> t = new ArrayList<>();
                int s = 0;
                for (int i = 0; i < 9; ++i) {
                    if ((mask >> i & 1) == 1) {
                        s += (i + 1);
                        t.add(i + 1);
                    }
                }
                if (s == n) {
                    ans.add(t);
                }
            }
        }
        return ans;
    }
}
```

### **TypeScript**
