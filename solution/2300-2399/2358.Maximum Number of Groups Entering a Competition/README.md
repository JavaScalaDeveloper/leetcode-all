# [2358. 分组的最大数量](https://leetcode.cn/problems/maximum-number-of-groups-entering-a-competition)

## 题目描述

<p>给你一个正整数数组 <code>grades</code> ，表示大学中一些学生的成绩。你打算将 <strong>所有</strong> 学生分为一些 <strong>有序</strong> 的非空分组，其中分组间的顺序满足以下全部条件：</p>

<ul>
	<li>第 <code>i</code> 个分组中的学生总成绩 <strong>小于</strong> 第 <code>(i + 1)</code> 个分组中的学生总成绩，对所有组均成立（除了最后一组）。</li>
	<li>第 <code>i</code> 个分组中的学生总数 <strong>小于</strong> 第 <code>(i + 1)</code> 个分组中的学生总数，对所有组均成立（除了最后一组）。</li>
</ul>

<p>返回可以形成的 <strong>最大</strong> 组数。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>grades = [10,6,12,7,3,5]
<strong>输出：</strong>3
<strong>解释：</strong>下面是形成 3 个分组的一种可行方法：
- 第 1 个分组的学生成绩为 grades = [12] ，总成绩：12 ，学生数：1
- 第 2 个分组的学生成绩为 grades = [6,7] ，总成绩：6 + 7 = 13 ，学生数：2
- 第 3 个分组的学生成绩为 grades = [10,3,5] ，总成绩：10 + 3 + 5 = 18 ，学生数：3 
可以证明无法形成超过 3 个分组。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>grades = [8,8]
<strong>输出：</strong>1
<strong>解释：</strong>只能形成 1 个分组，因为如果要形成 2 个分组的话，会导致每个分组中的学生数目相等。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= grades.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= grades[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

**方法一：贪心 + 二分查找**

我们观察题目中的条件，第i组的学生人数要小于第i+1组的学生人数，且第i组的学生总成绩要小于第i+1组的学生总成绩，我们只需要将学生按照成绩从小到大排序，然后每一组依次分配1,2, ...,k个学生即可。如果最后一组的学生人数不足k个，那么我们可以将这些学生分配到前面的最后一组中。

因此，我们要找到最大的k，使得\frac{(1 + k) × k}{2} ≤ n，其中n为学生的总人数。我们可以使用二分查找来求解。

我们定义二分查找的左边界为l = 1，右边界为r = n，每一次二分查找的中点为mid = \lfloor \frac{l + r + 1}{2} \rfloor，如果(1 + mid) × mid > 2 × n，则说明mid太大，我们需要将右边界缩小至mid - 1，否则我们需要将左边界增大至mid。

最后，我们将l作为答案返回即可。

时间复杂度O(log n)，空间复杂度O(1)。其中n为学生的总人数。

### **Java**

```java
class Solution {
    public int maximumGroups(int[] grades) {
        int n = grades.length;
        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (1L * mid * mid + mid > n * 2L) {
                r = mid - 1;
            } else {
                l = mid;
            }
        }
        return l;
    }
}
```
