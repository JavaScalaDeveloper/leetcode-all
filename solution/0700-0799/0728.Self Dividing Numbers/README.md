# [728. 自除数](https://leetcode.cn/problems/self-dividing-numbers)

[English Version](/solution/0700-0799/0728.Self%20Dividing%20Numbers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>自除数</strong><em>&nbsp;</em>是指可以被它包含的每一位数整除的数。</p>

<ul>
	<li>例如，<code>128</code> 是一个 <strong>自除数</strong> ，因为&nbsp;<code>128 % 1 == 0</code>，<code>128 % 2 == 0</code>，<code>128 % 8 == 0</code>。</li>
</ul>

<p><strong>自除数</strong> 不允许包含 0 。</p>

<p>给定两个整数&nbsp;<code>left</code>&nbsp;和&nbsp;<code>right</code> ，返回一个列表，<em>列表的元素是范围&nbsp;<code>[left, right]</code>&nbsp;内所有的 <strong>自除数</strong></em> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>left = 1, right = 22
<strong>输出：</strong>[1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<b>输入：</b>left = 47, right = 85
<b>输出：</b>[48,55,66,77]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= left &lt;= right &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

### **Java**

```java
class Solution {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> ans = new ArrayList<>();
        for (int i = left; i <= right; ++i) {
            if (check(i)) {
                ans.add(i);
            }
        }
        return ans;
    }

    private boolean check(int num) {
        for (int t = num; t != 0; t /= 10) {
            int x = t % 10;
            if (x == 0 || num % x != 0) {
                return false;
            }
        }
        return true;
    }
}
```
