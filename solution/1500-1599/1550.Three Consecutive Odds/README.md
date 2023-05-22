# [1550. 存在连续三个奇数的数组](https://leetcode.cn/problems/three-consecutive-odds)

## 题目描述

<p>给你一个整数数组 <code>arr</code>，请你判断数组中是否存在连续三个元素都是奇数的情况：如果存在，请返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [2,6,4,1]
<strong>输出：</strong>false
<strong>解释：</strong>不存在连续三个元素都是奇数的情况。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr = [1,2,34,3,4,5,7,23,12]
<strong>输出：</strong>true
<strong>解释：</strong>存在连续三个元素都是奇数的情况，即 [5,7,23] 。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 1000</code></li>
</ul>

## 解法

**方法一：遍历数组**

直接遍历数组，统计连续奇数的个数，如果个数达到 3，则返回 `true`，否则遍历结束，返回 `false`。

时间复杂度O(n)，空间复杂度O(1)，其中n为数组 `arr` 的长度。

### **Java**

```java
class Solution {
    public boolean threeConsecutiveOdds(int[] arr) {
        int cnt = 0;
        for (int v : arr) {
            if (v % 2 == 1) {
                ++cnt;
            } else {
                cnt = 0;
            }
            if (cnt == 3) {
                return true;
            }
        }
        return false;
    }
}
```
