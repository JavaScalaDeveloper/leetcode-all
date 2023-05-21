# [1815. 得到新鲜甜甜圈的最多组数](https://leetcode.cn/problems/maximum-number-of-groups-getting-fresh-donuts)

## 题目描述

<p>有一个甜甜圈商店，每批次都烤 <code>batchSize</code> 个甜甜圈。这个店铺有个规则，就是在烤一批新的甜甜圈时，之前 <strong>所有</strong> 甜甜圈都必须已经全部销售完毕。给你一个整数 <code>batchSize</code> 和一个整数数组 <code>groups</code> ，数组中的每个整数都代表一批前来购买甜甜圈的顾客，其中 <code>groups[i]</code> 表示这一批顾客的人数。每一位顾客都恰好只要一个甜甜圈。</p>

<p>当有一批顾客来到商店时，他们所有人都必须在下一批顾客来之前购买完甜甜圈。如果一批顾客中第一位顾客得到的甜甜圈不是上一组剩下的，那么这一组人都会很开心。</p>

<p>你可以随意安排每批顾客到来的顺序。请你返回在此前提下，<strong>最多</strong> 有多少组人会感到开心。</p>



<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>batchSize = 3, groups = [1,2,3,4,5,6]
<b>输出：</b>4
<b>解释：</b>你可以将这些批次的顾客顺序安排为 [6,2,4,5,1,3] 。那么第 1，2，4，6 组都会感到开心。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>batchSize = 4, groups = [1,3,2,5,2,2,1,6]
<b>输出：</b>4
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= batchSize <= 9</code></li>
	<li><code>1 <= groups.length <= 30</code></li>
	<li><code>1 <= groups[i] <= 10<sup>9</sup></code></li>
</ul>

## 解法

**方法一：贪心 + 状态压缩 + 记忆化搜索**

题目实际上要我们找到一种安排顺序，使得前缀和（这里指的是“人数”）与batchSize取模后为0的组数最多。因此，我们可以将所有顾客按组分成两类：

-   人数为batchSize的整数倍的顾客，这些顾客不会对下一组顾客的甜甜圈产生影响，我们可以贪心地优先安排这些组的顾客，那么这些组的顾客都会感到开心，“初始答案”为这些组的数量；
-   人数不为batchSize的整数倍的顾客，这些顾客的安排顺序会影响下一组顾客的甜甜圈。我们可以对这里每一组的人数v模batchSize，得到的这些余数构成一个集合，集合中的元素值范围是[1,2...,batchSize-1]。数组groups的长度最大为30，因此，每个余数的数量最大不超过30。我们可以用5个二进制位来表示一个余数的数量，而batchSize最大为9，那么表示这些余数以及对应的数量总共需要的二进制位就是5\times (9-1)=40。我们可以用一个64位整数state来表示。

接下来，我们设计一个函数dfs(state, mod)，表示安排状态为state，且当前前缀余数为mod时，能使得多少组感到开心。那么我们在“初始答案”加上dfs(state, 0)，即为最终答案。

函数dfs(state, mod)的实现逻辑如下：

我们枚举1到batchSize-1的每一个余数i，如果余数i的数量不为0，那么我们可以将余数i的数量减去1，将当前前缀余数加上i并且对batchSize取模，然后递归调用函数dfs，求出子状态的最优解，取最大值即可。最后判断mod是否为0，如果为0，我们在最大值上加1后返回，否则直接返回最大值。

过程中，我们可以使用记忆化搜索来避免状态的重复计算。

时间复杂度不超过O(10^7)，空间复杂度不超过O(10^6)。

### **Java**

```java
class Solution {
    private Map<Long, Integer> f = new HashMap<>();
    private int size;

    public int maxHappyGroups(int batchSize, int[] groups) {
        size = batchSize;
        int ans = 0;
        long state = 0;
        for (int g : groups) {
            int i = g % size;
            if (i == 0) {
                ++ans;
            } else {
                state += 1l << (i * 5);
            }
        }
        ans += dfs(state, 0);
        return ans;
    }

    private int dfs(long state, int mod) {
        if (f.containsKey(state)) {
            return f.get(state);
        }
        int res = 0;
        for (int i = 1; i < size; ++i) {
            if ((state >> (i * 5) & 31) != 0) {
                int t = dfs(state - (1l << (i * 5)), (mod + i) % size);
                res = Math.max(res, t + (mod == 0 ? 1 : 0));
            }
        }
        f.put(state, res);
        return res;
    }
}
```
