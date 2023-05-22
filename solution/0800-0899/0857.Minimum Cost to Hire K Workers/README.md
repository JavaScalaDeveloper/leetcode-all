# [857. 雇佣 K 名工人的最低成本](https://leetcode.cn/problems/minimum-cost-to-hire-k-workers)

## 题目描述

<p>有 <code>n</code>&nbsp;名工人。&nbsp;给定两个数组&nbsp;<code>quality</code>&nbsp;和&nbsp;<code>wage</code>&nbsp;，其中，<code>quality[i]</code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;名工人的工作质量，其最低期望工资为&nbsp;<code>wage[i]</code>&nbsp;。</p>

<p>现在我们想雇佣&nbsp;<code>k</code>&nbsp;名工人组成一个<em>工资组。</em>在雇佣&nbsp;一组 <code>k</code>&nbsp;名工人时，我们必须按照下述规则向他们支付工资：</p>

<ol>
	<li>对工资组中的每名工人，应当按其工作质量与同组其他工人的工作质量的比例来支付工资。</li>
	<li>工资组中的每名工人至少应当得到他们的最低期望工资。</li>
</ol>

<p>给定整数 <code>k</code> ，返回 <em>组成满足上述条件的付费群体所需的最小金额&nbsp;</em>。在实际答案的&nbsp;<code>10<sup>-5</sup></code>&nbsp;以内的答案将被接受。。</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入： </strong>quality = [10,20,5], wage = [70,50,30], k = 2
<strong>输出： </strong>105.00000
<strong>解释：</strong> 我们向 0 号工人支付 70，向 2 号工人支付 35。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入： </strong>quality = [3,1,10,10,1], wage = [4,8,2,2,7], k = 3
<strong>输出： </strong>30.66667
<strong>解释： </strong>我们向 0 号工人支付 4，向 2 号和 3 号分别支付 13.33333。</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == quality.length == wage.length</code></li>
	<li><code>1 &lt;= k &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= quality[i], wage[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

**方法一：贪心 + 优先队列（大根堆）**

我们假设选取了某一个工资组，总工作质量为 `tot`，总支付金额为 `c`。每个工人的工作质量为q_i，工资为w_i。那么，对于此工资组的每个工人，均满足c× \frac{q_i}{tot} ≥ w_i。即c≥ tot× \frac{w_i}{q_i}。

在总工作质量 `tot` 固定的情况下，支付的金额取决于权重\frac{w_i}{q_i}的最大值。

我们可以从小到大枚举权重\frac{w_i}{q_i}作为工资组的最大值，此时工资组其他人员只需要在权重小于等于这个值的集合中，选取工作质量最小的k-1名工人来组成工资组即可。因此，可以用优先队列（最大堆）维护工作质量最小的k-1名工人。

时间复杂度O(nlog n)，空间复杂度O(n)。其中n是工人数。

相似题目：[1383. 最大的团队表现值](/solution/1300-1399/1383.Maximum%20Performance%20of%20a%20Team/README.md)

### **Java**

```java
class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int n = quality.length;
        Pair[] t = new Pair[n];
        for (int i = 0; i < n; ++i) {
            t[i] = new Pair(quality[i], wage[i]);
        }
        Arrays.sort(t, (a, b) -> Double.compare(a.x, b.x));
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        double ans = 1e9;
        int tot = 0;
        for (var e : t) {
            tot += e.q;
            pq.offer(e.q);
            if (pq.size() == k) {
                ans = Math.min(ans, tot * e.x);
                tot -= pq.poll();
            }
        }
        return ans;
    }
}

class Pair {
    double x;
    int q;

    Pair(int q, int w) {
        this.q = q;
        this.x = (double) w / q;
    }
}
```
