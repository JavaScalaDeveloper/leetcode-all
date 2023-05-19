# [1626. 无矛盾的最佳球队](https://leetcode.cn/problems/best-team-with-no-conflicts)

[English Version](/solution/1600-1699/1626.Best%20Team%20With%20No%20Conflicts/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>假设你是球队的经理。对于即将到来的锦标赛，你想组合一支总体得分最高的球队。球队的得分是球队中所有球员的分数 <strong>总和</strong> 。</p>

<p>然而，球队中的矛盾会限制球员的发挥，所以必须选出一支 <strong>没有矛盾</strong> 的球队。如果一名年龄较小球员的分数 <strong>严格大于</strong> 一名年龄较大的球员，则存在矛盾。同龄球员之间不会发生矛盾。</p>

<p>给你两个列表 <code>scores</code> 和 <code>ages</code>，其中每组 <code>scores[i]</code> 和 <code>ages[i]</code> 表示第 <code>i</code> 名球员的分数和年龄。请你返回 <strong>所有可能的无矛盾球队中得分最高那支的分数</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>scores = [1,3,5,10,15], ages = [1,2,3,4,5]
<strong>输出：</strong>34
<strong>解释：</strong>你可以选中所有球员。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>scores = [4,5,6,5], ages = [2,1,2,1]
<strong>输出：</strong>16
<strong>解释：</strong>最佳的选择是后 3 名球员。注意，你可以选中多个同龄球员。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>scores = [1,2,3,5], ages = [8,9,10,1]
<strong>输出：</strong>6
<strong>解释：</strong>最佳的选择是前 3 名球员。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= scores.length, ages.length &lt;= 1000</code></li>
	<li><code>scores.length == ages.length</code></li>
	<li><code>1 &lt;= scores[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= ages[i] &lt;= 1000</code></li>
</ul>

## 解法

**方法一：排序 + 动态规划**

我们可以将球员按照分数从小到大排序，如果分数相同，则按照年龄从小到大排序。

接下来，使用动态规划求解。

我们定义 $f[i]$ 表示以排序后的第 $i$ 个球员作为最后一个球员的最大得分，那么答案就是 $\max_{0 \leq i < n} f[i]$。

对于 $f[i]$，我们可以枚举 $0 \leq j \lt i$，如果第 $i$ 个球员的年龄大于等于第 $j$ 个球员的年龄，则 $f[i]$ 可以从 $f[j]$ 转移而来，转移方程为 $f[i] = \max(f[i], f[j])$。然后我们将第 $i$ 个球员的分数加到 $f[i]$ 中，即 $f[i] += scores[i]$。

最后，我们返回 $\max_{0 \leq i < n} f[i]$ 即可。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 为球员的数量。

**方法二：排序 + 树状数组**

与方法一类似，我们可以将球员按照分数从小到大排序，如果分数相同，则按照年龄从小到大排序。

接下来，我们使用树状数组维护不超过当前球员年龄的球员的最大得分。每一次，我们只需要在 $O(\log m)$ 的时间内找出不超过当前球员年龄的球员的最大得分，然后将当前球员的分数加到该得分上，即可更新当前球员年龄的最大得分。

最后，我们返回得分的最大值即可。

时间复杂度 $O(n \times (\log n + \log m))$，空间复杂度 $O(n + m)$。其中 $n$ 和 $m$ 分别为球员的数量和球员的年龄的最大值。

### **Java**

```java
class Solution {
    public int bestTeamScore(int[] scores, int[] ages) {
        int n = ages.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; ++i) {
            arr[i] = new int[] {scores[i], ages[i]};
        }
        Arrays.sort(arr, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int[] f = new int[n];
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (arr[i][1] >= arr[j][1]) {
                    f[i] = Math.max(f[i], f[j]);
                }
            }
            f[i] += arr[i][0];
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }
}
```

```java
class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new int[n + 1];
    }

    public void update(int x, int val) {
        while (x <= n) {
            c[x] = Math.max(c[x], val);
            x += x & -x;
        }
    }

    public int query(int x) {
        int s = 0;
        while (x > 0) {
            s = Math.max(s, c[x]);
            x -= x & -x;
        }
        return s;
    }
}

class Solution {
    public int bestTeamScore(int[] scores, int[] ages) {
        int n = ages.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; ++i) {
            arr[i] = new int[] {scores[i], ages[i]};
        }
        Arrays.sort(arr, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int m = 0;
        for (int age : ages) {
            m = Math.max(m, age);
        }
        BinaryIndexedTree tree = new BinaryIndexedTree(m);
        for (int[] x : arr) {
            tree.update(x[1], x[0] + tree.query(x[1]));
        }
        return tree.query(m);
    }
}
```

### **TypeScript**
