# [264. 丑数 II](https://leetcode.cn/problems/ugly-number-ii)

## 题目描述

<p>给你一个整数 <code>n</code> ，请你找出并返回第 <code>n</code> 个 <strong>丑数</strong> 。</p>

<p><strong>丑数 </strong>就是只包含质因数 <code>2</code>、<code>3</code> 和/或 <code>5</code> 的正整数。</p>



<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 10
<strong>输出：</strong>12
<strong>解释：</strong>[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>1
<strong>解释：</strong>1 通常被视为丑数。
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= n <= 1690</code></li>
</ul>

## 解法

**方法一：优先队列（最小堆）**

初始时，将第一个丑数1加入堆。每次取出堆顶元素x，由于2x,3x,5x也是丑数，因此将它们加入堆中。为了避免重复元素，可以用哈希表vis去重。

时间复杂度O(n × log n)，空间复杂度O(n)。

**方法二：动态规划**

定义数组dp，其中dp[i-1]表示第i个丑数，那么第n个丑数就是dp[n - 1]。最小的丑数是1，所以dp[0]=1。

定义3个指针p_2,p_3和p_5，表示下一个丑数是当前指针指向的丑数乘以对应的质因数。初始时，三个指针的值都指向0。

当i在[1,2..n-1]范围内，我们更新dp[i]=min(dp[p_2] × 2, dp[p_3] × 3, dp[p_5] × 5)，然后分别比较dp[i]与dp[p_2] × 2,dp[p_3] × 3,dp[p_5] × 5是否相等，若是，则对应的指针加1。

最后返回dp[n - 1]即可。

时间复杂度O(n)，空间复杂度O(n)。

### **Java**

```java
class Solution {
    public int nthUglyNumber(int n) {
        Set<Long> vis = new HashSet<>();
        PriorityQueue<Long> q = new PriorityQueue<>();
        int[] f = new int[]{2, 3, 5};
        q.offer(1L);
        vis.add(1L);
        long ans = 0;
        while (n-- > 0) {
            ans = q.poll();
            for (int v : f) {
                long next = ans * v;
                if (vis.add(next)) {
                    q.offer(next);
                }
            }
        }
        return (int) ans;
    }
}
```

```java
class Solution {
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0;
        for (int i = 1; i < n; ++i) {
            int next2 = dp[p2] * 2, next3 = dp[p3] * 3, next5 = dp[p5] * 5;
            dp[i] = Math.min(next2, Math.min(next3, next5));
            if (dp[i] == next2) ++p2;
            if (dp[i] == next3) ++p3;
            if (dp[i] == next5) ++p5;
        }
        return dp[n - 1];
    }
}
```
