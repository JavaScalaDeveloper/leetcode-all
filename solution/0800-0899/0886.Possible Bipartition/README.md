# [886. 可能的二分法](https://leetcode.cn/problems/possible-bipartition)

[English Version](/solution/0800-0899/0886.Possible%20Bipartition/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一组&nbsp;<code>n</code>&nbsp;人（编号为&nbsp;<code>1, 2, ..., n</code>），&nbsp;我们想把每个人分进<strong>任意</strong>大小的两组。每个人都可能不喜欢其他人，那么他们不应该属于同一组。</p>

<p>给定整数 <code>n</code>&nbsp;和数组 <code>dislikes</code>&nbsp;，其中&nbsp;<code>dislikes[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>&nbsp;，表示不允许将编号为 <code>a<sub>i</sub></code>&nbsp;和&nbsp;&nbsp;<code>b<sub>i</sub></code>的人归入同一组。当可以用这种方法将所有人分进两组时，返回 <code>true</code>；否则返回 <code>false</code>。</p>

<p>&nbsp;</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 4, dislikes = [[1,2],[1,3],[2,4]]
<strong>输出：</strong>true
<strong>解释：</strong>group1 [1,4], group2 [2,3]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 3, dislikes = [[1,2],[1,3],[2,3]]
<strong>输出：</strong>false
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2000</code></li>
	<li><code>0 &lt;= dislikes.length &lt;= 10<sup>4</sup></code></li>
	<li><code>dislikes[i].length == 2</code></li>
	<li><code>1 &lt;= dislikes[i][j] &lt;= n</code></li>
	<li><code>a<sub>i</sub>&nbsp;&lt; b<sub>i</sub></code></li>
	<li><code>dislikes</code>&nbsp;中每一组都 <strong>不同</strong></li>
</ul>

<p>&nbsp;</p>

## 解法

**方法一：染色法**

我们用两种颜色对图进行染色，如果可以完成染色，那么就说明可以将所有人分进两组。

具体的染色方法如下：

-   初始化所有人的颜色为 $0$，表示还没有染色。
-   遍历所有人，如果当前人没有染色，那么就用颜色 $1$ 对其进行染色，然后将其所有不喜欢的人用颜色 $2$ 进行染色。如果染色过程中出现了冲突，那么就说明无法将所有人分进两组，返回 `false`。
-   如果所有人都染色成功，那么就说明可以将所有人分进两组，返回 `true`。

时间复杂度 $O(n + m)$，其中 $n$, $m$ 分别是人数和不喜欢的关系数。

**方法二：并查集**

并查集是一种树形的数据结构，顾名思义，它用于处理一些不交集的**合并**及**查询**问题。 它支持两种操作：

1. 查找（Find）：确定某个元素处于哪个子集，单次操作时间复杂度 $O(\alpha(n))$
1. 合并（Union）：将两个子集合并成一个集合，单次操作时间复杂度 $O(\alpha(n))$

其中 $\alpha$ 为阿克曼函数的反函数，其增长极其缓慢，也就是说其单次操作的平均运行时间可以认为是一个很小的常数。

以下是并查集的常用模板，需要熟练掌握。其中：

-   `n` 表示节点数
-   `p` 存储每个点的父节点，初始时每个点的父节点都是自己
-   `size` 只有当节点是祖宗节点时才有意义，表示祖宗节点所在集合中，点的数量
-   `find(x)` 函数用于查找 $x$ 所在集合的祖宗节点
-   `union(a, b)` 函数用于合并 $a$ 和 $b$ 所在的集合

对于本题，我们遍历每一个人，他与他不喜欢的人不应该在同一个集合中，如果在同一个集合中，就产生了冲突，直接返回 `false`。如果没有冲突，那么就将他所有不喜欢的人合并到同一个集合中。

遍历结束，说明没有冲突，返回 `true`。

时间复杂度 $O(n + m\times \alpha(n))$。

### **Java**

```java
class Solution {
    private List<Integer>[] g;
    private int[] color;

    public boolean possibleBipartition(int n, int[][] dislikes) {
        g = new List[n];
        color = new int[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : dislikes) {
            int a = e[0] - 1, b = e[1] - 1;
            g[a].add(b);
            g[b].add(a);
        }
        for (int i = 0; i < n; ++i) {
            if (color[i] == 0) {
                if (!dfs(i, 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean dfs(int i, int c) {
        color[i] = c;
        for (int j : g[i]) {
            if (color[j] == c) {
                return false;
            }
            if (color[j] == 0 && !dfs(j, 3 - c)) {
                return false;
            }
        }
        return true;
    }
}
```

```java
class Solution {
    private int[] p;

    public boolean possibleBipartition(int n, int[][] dislikes) {
        p = new int[n];
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        for (var e : dislikes) {
            int a = e[0] - 1, b = e[1] - 1;
            g[a].add(b);
            g[b].add(a);
        }
        for (int i = 0; i < n; ++i) {
            for (int j : g[i]) {
                if (find(i) == find(j)) {
                    return false;
                }
                p[find(j)] = find(g[i].get(0));
            }
        }
        return true;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

### **TypeScript**
