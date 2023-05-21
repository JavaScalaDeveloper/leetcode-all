# [403. 青蛙过河](https://leetcode.cn/problems/frog-jump)

## 题目描述

<p>一只青蛙想要过河。 假定河流被等分为若干个单元格，并且在每一个单元格内都有可能放有一块石子（也有可能没有）。 青蛙可以跳上石子，但是不可以跳入水中。</p>

<p>给你石子的位置列表 <code>stones</code>（用单元格序号 <strong>升序</strong> 表示），&nbsp;请判定青蛙能否成功过河（即能否在最后一步跳至最后一块石子上）。开始时，&nbsp;青蛙默认已站在第一块石子上，并可以假定它第一步只能跳跃 <code>1</code> 个单位（即只能从单元格 1 跳至单元格 2 ）。</p>

<p>如果青蛙上一步跳跃了&nbsp;<code>k</code><em>&nbsp;</em>个单位，那么它接下来的跳跃距离只能选择为&nbsp;<code>k - 1</code>、<code>k</code><em>&nbsp;</em>或&nbsp;<code>k + 1</code> 个单位。&nbsp;另请注意，青蛙只能向前方（终点的方向）跳跃。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>stones = [0,1,3,5,6,8,12,17]
<strong>输出：</strong>true
<strong>解释：</strong>青蛙可以成功过河，按照如下方案跳跃：跳 1 个单位到第 2 块石子, 然后跳 2 个单位到第 3 块石子, 接着 跳 2 个单位到第 4 块石子, 然后跳 3 个单位到第 6 块石子, 跳 4 个单位到第 7 块石子, 最后，跳 5 个单位到第 8 个石子（即最后一块石子）。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>stones = [0,1,2,3,4,8,9,11]
<strong>输出：</strong>false
<strong>解释：</strong>这是因为第 5 和第 6 个石子之间的间距太大，没有可选的方案供青蛙跳跃过去。</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= stones.length &lt;= 2000</code></li>
	<li><code>0 &lt;= stones[i] &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>stones[0] == 0</code></li>
	<li><code>stones</code>&nbsp;按严格升序排列</li>
</ul>

## 解法

**方法一：哈希表 + 记忆化搜索**

我们用哈希表pos记录每个石子的下标，接下来设计一个函数dfs(i, k)，表示青蛙从第i个石子跳跃且上一次跳跃距离为k，如果青蛙能够到达终点，那么函数返回 `true`，否则返回 `false`。

函数dfs(i, k)的计算过程如下：

如果i是最后一个石子的下标，那么青蛙已经到达终点，返回 `true`；

否则，我们需要枚举青蛙接下来的跳跃距离j，其中j \in [k-1, k, k+1]。如果j是正数，并且哈希表pos中存在位置stones[i] + j，那么青蛙在第i个石子上可以选择跳跃j个单位，如果dfs(pos[stones[i] + j], j)返回 `true`，那么青蛙可以从第i个石子成功跳跃到终点，我们就可以返回 `true`。

枚举结束，说明青蛙在第i个石子上无法选择合适的跳跃距离跳到终点，我们就返回 `false`。

为了防止函数dfs(i, k)中出现重复计算，我们可以使用记忆化搜索，将dfs(i, k)的结果记录在一个数组f中，每当函数dfs(i, k)返回结果，我们就将f[i][k]进行赋值，并在下次遇到dfs(i, k)时直接返回f[i][k]。

时间复杂度O(n^2)，空间复杂度O(n^2)。其中n是石子的数量。

**方法二：动态规划**

我们定义f[i][k]表示青蛙能否达到「现在所处的石子编号」为i，「上一次跳跃距离」为k的状态。初始时f[0][0] = true，其余均为 `false`。

考虑f[i]，我们可以枚举上一块石子的编号j，那么上一次跳跃的距离k=stones[i]-stones[j]。如果k-1 > j，那么青蛙无法从第j块石子跳跃到第i块石子，我们可以直接跳过这种情况。否则，青蛙可以从第j块石子跳跃到第i块石子，那么f[i][k] = f[j][k-1] OR f[j][k] OR f[j][k+1]。如果i=n-1，且f[i][k]=true，那么青蛙可以成功过河，我们就可以返回 `true`。

否则，我们最后返回 `false`。

时间复杂度O(n^2)，空间复杂度O(n^2)。其中n是石子的数量。

### **Java**

```java
class Solution {
    private Boolean[][] f;
    private Map<Integer, Integer> pos = new HashMap<>();
    private int[] stones;
    private int n;

    public boolean canCross(int[] stones) {
        n = stones.length;
        f = new Boolean[n][n];
        this.stones = stones;
        for (int i = 0; i < n; ++i) {
            pos.put(stones[i], i);
        }
        return dfs(0, 0);
    }

    private boolean dfs(int i, int k) {
        if (i == n - 1) {
            return true;
        }
        if (f[i][k] != null) {
            return f[i][k];
        }
        for (int j = k - 1; j <= k + 1; ++j) {
            if (j > 0) {
                int h = stones[i] + j;
                if (pos.containsKey(h) && dfs(pos.get(h), j)) {
                    return f[i][k] = true;
                }
            }
        }
        return f[i][k] = false;
    }
}
```

```java
class Solution {
    public boolean canCross(int[] stones) {
        int n = stones.length;
        boolean[][] f = new boolean[n][n];
        f[0][0] = true;
        for (int i = 1; i < n; ++i) {
            for (int j = i - 1; j >= 0; --j) {
                int k = stones[i] - stones[j];
                if (k - 1 > j) {
                    break;
                }
                f[i][k] = f[j][k - 1] || f[j][k] || f[j][k + 1];
                if (i == n - 1 && f[i][k]) {
                    return true;
                }
            }
        }
        return false;
    }
}
```
