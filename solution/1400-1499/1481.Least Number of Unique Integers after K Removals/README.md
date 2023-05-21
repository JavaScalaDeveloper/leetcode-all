# [1481. 不同整数的最少数目](https://leetcode.cn/problems/least-number-of-unique-integers-after-k-removals)

## 题目描述

<p>给你一个整数数组 <code>arr</code> 和一个整数 <code>k</code> 。现需要从数组中恰好移除 <code>k</code> 个元素，请找出移除后数组中不同整数的最少数目。</p>

<ol>
</ol>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [5,5,4], k = 1
<strong>输出：</strong>1
<strong>解释：</strong>移除 1 个 4 ，数组中只剩下 5 一种整数。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr = [4,3,1,1,3,3,2], k = 3
<strong>输出：</strong>2
<strong>解释：</strong>先移除 4、2 ，然后再移除两个 1 中的任意 1 个或者三个 3 中的任意 1 个，最后剩下 1 和 3 两种整数。</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length&nbsp;&lt;= 10^5</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 10^9</code></li>
	<li><code>0 &lt;= k&nbsp;&lt;= arr.length</code></li>
</ul>

## 解法

**方法一：哈希表 + 排序**

我们用哈希表cnt统计数组arr中每个整数出现的次数，然后将cnt中的值按照从小到大的顺序排序，记录在数组nums中。

接下来，我们遍历数组nums，对于当前遍历到的每个值nums[i]，我们将k减去nums[i]，如果k \lt 0，则说明我们已经移除了k个元素，此时数组中不同整数的最少数目为nums的长度减去当前遍历到的下标i，直接返回即可。

若遍历结束，说明我们移除了所有的元素，此时数组中不同整数的最少数目为0。

时间复杂度O(n \times \log n)，空间复杂度O(n)。其中n为数组arr的长度。

### **Java**

```java
class Solution {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : arr) {
            cnt.merge(x, 1, Integer::sum);
        }
        List<Integer> nums = new ArrayList<>(cnt.values());
        Collections.sort(nums);
        for (int i = 0, m = nums.size(); i < m; ++i) {
            k -= nums.get(i);
            if (k < 0) {
                return m - i;
            }
        }
        return 0;
    }
}
```
