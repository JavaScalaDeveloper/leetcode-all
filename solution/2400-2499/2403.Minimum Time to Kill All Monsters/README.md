# [2403. 杀死所有怪物的最短时间](https://leetcode.cn/problems/minimum-time-to-kill-all-monsters)

## 题目描述

<p>你有一个整数数组 <code>power</code>，其中&nbsp; <code>power[i]</code> 是第 <code>i</code> 个怪物的力量。</p>

<p>你从 <code>0</code> 点法力值开始，每天获取&nbsp;<code>gain</code> 点法力值，最初 <code>gain</code> 等于 <code>1</code>。</p>

<p>每天，在获得 <code>gain</code>&nbsp;点法力值后，如果你的法力值大于或等于怪物的力量，你就可以打败怪物。当你打败怪物时:</p>

<ul>
	<li>
	<p data-group="1-1">你的法力值会被重置为 <code>0</code>，并且</p>
	</li>
	<li>
	<p data-group="1-1"><code>gain</code>&nbsp;的值增加 <code>1</code>。</p>
	</li>
</ul>

<p>返回<em>打败所有怪物所需的&nbsp;<strong>最少&nbsp;</strong>天数。</em></p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> power = [3,1,4]
<strong>输出:</strong> 4
<strong>解释:</strong> 打败所有怪物的最佳方法是:
- 第 1 天: 获得 1 点法力值，现在总共拥有 1 点法力值。用尽所有法力值击杀第 2 个怪物。
- 第 2 天: 获得 2 点法力值，现在总共拥有 2 点法力值。
- 第 3 天: 获得 2 点法力值，现在总共拥有 4 点法力值。用尽所有法力值击杀第 3 个怪物。
- 第 4 天: 获得 3 点法力值，现在总共拥有 3 点法力值。 用尽所有法力值击杀第 1 个怪物。
可以证明，4 天是最少需要的天数。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> power = [1,1,4]
<strong>输出:</strong> 4
<strong>解释:</strong> 打败所有怪物的最佳方法是:
- 第 1 天: 获得 1 点法力值，现在总共拥有 1 点法力值。用尽所有法力值击杀第 1 个怪物。
- 第 2 天: 获得 2 点法力值，现在总共拥有 2 点法力值。用尽所有法力值击杀第 2 个怪物。
- 第 3 天: 获得 3 点法力值，现在总共拥有 3 点法力值。
- 第 4 天: 获得 3 点法力值，现在总共拥有 6 点法力值。用尽所有法力值击杀第 3 个怪物。
可以证明，4 天是最少需要的天数。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> power = [1,2,4,9]
<strong>输出:</strong> 6
<strong>解释:</strong> 打败所有怪物的最佳方法是:
- 第 1 天: 获得 1 点法力值，现在总共拥有 1 点法力值。用尽所有法力值击杀第 1 个怪物
- 第 2 天: 获得 2 点法力值，现在总共拥有 2 点法力值。用尽所有法力值击杀第 2 个怪物。
- 第 3 天: 获得 3 点法力值，现在总共拥有 3 点法力值。
- 第 4 天: 获得 3 点法力值，现在总共拥有 6 点法力值。
- 第 5 天: 获得 3 点法力值，现在总共拥有 9 点法力值。用尽所有法力值击杀第 4 个怪物。
- 第 6 天: 获得 4 点法力值，现在总共拥有 4 点法力值。用尽所有法力值击杀第 3 个怪物。
可以证明，6 天是最少需要的天数。
</pre>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= power.length &lt;= 17</code></li>
	<li><code>1 &lt;= power[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

**方法一：状态压缩 + 记忆化搜索/动态规划**

由于打怪才能增加每天法力的收益 `gain`，不同的打怪顺序对结果有影响，需要枚举。注意到题目的数据范围较小，考虑使用状态压缩动态规划求解。

我们定义状态 `mask` 表示当前已经打怪的情况，其二进制中的 `1` 表示已经被打倒的怪物，`0` 表示未被打倒的怪物。

时间复杂度O(n× 2^n)，空间复杂度O(2^n)。其中n是怪物数量。

### **Java**

```java
class Solution {
    private int n;
    private long[] f;
    private int[] power;

    public long minimumTime(int[] power) {
        n = power.length;
        f = new long[1 << n];
        Arrays.fill(f, -1);
        this.power = power;
        return dfs(0);
    }

    private long dfs(int mask) {
        if (f[mask] != -1) {
            return f[mask];
        }
        int cnt = Integer.bitCount(mask);
        if (cnt == n) {
            return 0;
        }
        long ans = Long.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            if (((mask >> i) & 1) == 1) {
                continue;
            }
            ans = Math.min(ans, dfs(mask | 1 << i) + (power[i] + cnt) / (cnt + 1));
        }
        f[mask] = ans;
        return ans;
    }
}
```

```java
class Solution {
    public long minimumTime(int[] power) {
        int n = power.length;
        long[] dp = new long[1 << n];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0;
        for (int mask = 1; mask < 1 << n; ++mask) {
            int cnt = Integer.bitCount(mask);
            for (int i = 0; i < n; ++i) {
                if (((mask >> i) & 1) == 1) {
                    dp[mask] = Math.min(dp[mask], dp[mask ^ (1 << i)] + (power[i] + cnt - 1) / cnt);
                }
            }
        }
        return dp[(1 << n) - 1];
    }
}
```
