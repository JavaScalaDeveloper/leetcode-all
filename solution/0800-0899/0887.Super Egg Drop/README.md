# [887. 鸡蛋掉落](https://leetcode.cn/problems/super-egg-drop)

## 题目描述

<p>给你 <code>k</code> 枚相同的鸡蛋，并可以使用一栋从第 <code>1</code> 层到第 <code>n</code> 层共有 <code>n</code> 层楼的建筑。</p>

<p>已知存在楼层 <code>f</code> ，满足 <code>0 <= f <= n</code> ，任何从<strong> 高于</strong> <code>f</code> 的楼层落下的鸡蛋都会碎，从 <code>f</code> 楼层或比它低的楼层落下的鸡蛋都不会破。</p>

<p>每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 <code>x</code> 扔下（满足 <code>1 <= x <= n</code>）。如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中 <strong>重复使用</strong> 这枚鸡蛋。</p>

<p>请你计算并返回要确定 <code>f</code> <strong>确切的值</strong> 的 <strong>最小操作次数</strong> 是多少？</p>
 

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>k = 1, n = 2
<strong>输出：</strong>2
<strong>解释：</strong>
鸡蛋从 1 楼掉落。如果它碎了，肯定能得出 f = 0 。 
否则，鸡蛋从 2 楼掉落。如果它碎了，肯定能得出 f = 1 。 
如果它没碎，那么肯定能得出 f = 2 。 
因此，在最坏的情况下我们需要移动 2 次以确定 f 是多少。 
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>k = 2, n = 6
<strong>输出：</strong>3
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>k = 3, n = 14
<strong>输出：</strong>4
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= k <= 100</code></li>
	<li><code>1 <= n <= 10<sup>4</sup></code></li>
</ul>

## 解法

**方法一：记忆化搜索 + 二分查找**

我们设计一个函数dfs(i, j)，表示有i层楼以及j个鸡蛋时，确定f值的最小操作次数，那么答案就是dfs(n, k)。

函数dfs(i, j)的执行逻辑如下：

如果i < 1，说明楼层已经小于等于0，此时返回0；

如果j = 1，说明只有一个鸡蛋，那么只能从第一层开始一层一层试，最坏情况下需要试i次，此时返回i；

否则，我们考虑枚举第一个鸡蛋从第x层扔下的情况，其中1 \le x \le i。如果鸡蛋在第x层扔下时碎了，说明f < x，此时我们接下来需要在x - 1层下方以及剩下的j - 1个鸡蛋确定f值，总共需要的最小操作次数为dfs(x - 1, j - 1) + 1次；如果鸡蛋在第x层扔下时没碎，说明f > x，此时我们需要在第x + 1层及以上以及剩下的j个鸡蛋确定f值，总共需要的最小操作次数为dfs(i - x, j) + 1次。由于我们要保证最坏情况下操作次数最少，因此dfs(i, j) = min_{1 \le x \le i} max(dfs(x - 1, j - 1), dfs(i - x, j)) + 1。

如果按照这样的方式枚举，由于状态数有n × k，每个状态需要枚举n次，那么总时间复杂度达到O(n^2 × k)，这会超出时间限制，我们考虑如何进行优化。

我们注意到函数dfs(x - 1, j - 1)随着x的增大而单调递增，而函数dfs(i - x, j)随着x的增大而单调递减，因此存在一个最优的x值使得max(dfs(x - 1, j - 1), dfs(i - x, j))达到最小值。我们可以对x进行二分查找，找出这个最优的x值。其中x是满足dfs(x - 1, j - 1) \le dfs(i - x, j)的最大整数。这样我们可以将时间复杂度降低到O(n × k log n)。

时间复杂度O(n × k log n)，空间复杂度O(n × k)。其中n和k分别是楼层数和鸡蛋数。

**方法二：动态规划 + 二分查找**

我们也可以使用动态规划的方法解决这个问题。

我们定义f[i][j]表示有i层楼以及j个鸡蛋时，确定f值的最小操作次数，那么答案就是f[n][k]。

状态转移方程为f[i][j] = min_{1 \le x \le i} max(f[x - 1][j - 1], f[i - x][j]) + 1。

与方法一类似，我们可以使用二分查找来优化x的枚举过程。

时间复杂度O(n × k log n)，空间复杂度O(n × k)。其中n和k分别是楼层数和鸡蛋数。

### **Java**

```java
class Solution {
    private int[][] f;

    public int superEggDrop(int k, int n) {
        f = new int[n + 1][k + 1];
        return dfs(n, k);
    }

    private int dfs(int i, int j) {
        if (i < 1) {
            return 0;
        }
        if (j == 1) {
            return i;
        }
        if (f[i][j] != 0) {
            return f[i][j];
        }
        int l = 1, r = i;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            int a = dfs(mid - 1, j - 1);
            int b = dfs(i - mid, j);
            if (a <= b) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return f[i][j] = Math.max(dfs(l - 1, j - 1), dfs(i - l, j)) + 1;
    }
}
```

```java
class Solution {
    public int superEggDrop(int k, int n) {
        int[][] f = new int[n + 1][k + 1];
        for (int i = 1; i <= n; ++i) {
            f[i][1] = i;
        }
        for (int i = 1; i <= n; ++i) {
            for (int j = 2; j <= k; ++j) {
                int l = 1, r = i;
                while (l < r) {
                    int mid = (l + r + 1) >> 1;
                    int a = f[mid - 1][j - 1];
                    int b = f[i - mid][j];
                    if (a <= b) {
                        l = mid;
                    } else {
                        r = mid - 1;
                    }
                }
                f[i][j] = Math.max(f[l - 1][j - 1], f[i - l][j]) + 1;
            }
        }
        return f[n][k];
    }
}
```
