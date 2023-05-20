# [1215. 步进数](https://leetcode.cn/problems/stepping-numbers)

## 题目描述

<p>如果一个整数上的每一位数字与其相邻位上的数字的绝对差都是 <code>1</code>，那么这个数就是一个「步进数」。</p>

<p>例如，<code>321</code>&nbsp;是一个步进数，而&nbsp;<code>421</code>&nbsp;不是。</p>

<p>给你两个整数，<code>low</code>&nbsp;和&nbsp;<code>high</code>，请你找出在&nbsp;<code>[low, high]</code>&nbsp;范围内的所有步进数，并返回&nbsp;<strong>排序后</strong> 的结果。</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong>low = 0, high = 21
<strong>输出：</strong>[0,1,2,3,4,5,6,7,8,9,10,12,21]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= low &lt;= high &lt;= 2 * 10^9</code></li>
</ul>

## 解法

**方法一：BFS**

### **Java**

```java
class Solution {
    public List<Integer> countSteppingNumbers(int low, int high) {
        List<Integer> ans = new ArrayList<>();
        if (low == 0) {
            ans.add(0);
        }
        Deque<Long> q = new ArrayDeque<>();
        for (long i = 1; i < 10; ++i) {
            q.offer(i);
        }
        while (!q.isEmpty()) {
            long v = q.pollFirst();
            if (v > high) {
                break;
            }
            if (v >= low) {
                ans.add((int) v);
            }
            int x = (int) v % 10;
            if (x > 0) {
                q.offer(v * 10 + x - 1);
            }
            if (x < 9) {
                q.offer(v * 10 + x + 1);
            }
        }
        return ans;
    }
}
```
