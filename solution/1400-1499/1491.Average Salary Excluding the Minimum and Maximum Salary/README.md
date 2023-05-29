# [1491. 去掉最低工资和最高工资后的工资平均值](https://leetcode.cn/problems/average-salary-excluding-the-minimum-and-maximum-salary)

## 题目描述

<p>给你一个整数数组&nbsp;<code>salary</code>&nbsp;，数组里每个数都是 <strong>唯一</strong>&nbsp;的，其中&nbsp;<code>salary[i]</code> 是第&nbsp;<code>i</code>&nbsp;个员工的工资。</p>

<p>请你返回去掉最低工资和最高工资以后，剩下员工工资的平均值。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>salary = [4000,3000,1000,2000]
<strong>输出：</strong>2500.00000
<strong>解释：</strong>最低工资和最高工资分别是 1000 和 4000 。
去掉最低工资和最高工资以后的平均工资是 (2000+3000)/2= 2500
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>salary = [1000,2000,3000]
<strong>输出：</strong>2000.00000
<strong>解释：</strong>最低工资和最高工资分别是 1000 和 3000 。
去掉最低工资和最高工资以后的平均工资是 (2000)/1= 2000
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>salary = [6000,5000,4000,3000,2000,1000]
<strong>输出：</strong>3500.00000
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>salary = [8000,9000,2000,3000,6000,1000]
<strong>输出：</strong>4750.00000
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= salary.length &lt;= 100</code></li>
	<li><code>10^3&nbsp;&lt;= salary[i] &lt;= 10^6</code></li>
	<li><code>salary[i]</code>&nbsp;是唯一的。</li>
	<li>与真实值误差在&nbsp;<code>10^-5</code> 以内的结果都将视为正确答案。</li>
</ul>

## 解法

**方法一：模拟**

按题意模拟即可。

遍历数组，求出最大值和最小值，并且累加和，然后求出去掉最大值和最小值后的平均值。

时间复杂度 $O(n)$。其中 $n$ 为数组 `salary` 的长度。

### **Java**

```java
class Solution {
    public double average(int[] salary) {
        int s = 0;
        int mi = 10000000, mx = 0;
        for (int v : salary) {
            mi = Math.min(mi, v);
            mx = Math.max(mx, v);
            s += v;
        }
        s -= (mi + mx);
        return s * 1.0 / (salary.length - 2);
    }
}
```
