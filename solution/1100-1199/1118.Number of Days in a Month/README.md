# [1118. 一月有多少天](https://leetcode.cn/problems/number-of-days-in-a-month)

[English Version](/solution/1100-1199/1118.Number%20of%20Days%20in%20a%20Month/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>指定年份&nbsp;<code>year</code> 和月份&nbsp;<code>month</code>，返回 <em>该月天数&nbsp;</em>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>year = 1992, month = 7
<strong>输出：</strong>31
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>year = 2000, month = 2
<strong>输出：</strong>29
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>year = 1900, month = 2
<strong>输出：</strong>28
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1583 &lt;= year &lt;= 2100</code></li>
	<li><code>1 &lt;= month &lt;= 12</code></li>
</ul>

## 解法

### **Java**

```java
class Solution {
    public int numberOfDays(int year, int month) {
        boolean leap = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        int[] days = new int[] {0, 31, leap ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return days[month];
    }
}
```
