# [2006. 差的绝对值为 K 的数对数目](https://leetcode.cn/problems/count-number-of-pairs-with-absolute-difference-k)

## 题目描述

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;，请你返回数对&nbsp;<code>(i, j)</code>&nbsp;的数目，满足&nbsp;<code>i &lt; j</code>&nbsp;且&nbsp;<code>|nums[i] - nums[j]| == k</code>&nbsp;。</p>

<p><code>|x|</code>&nbsp;的值定义为：</p>

<ul>
	<li>如果&nbsp;<code>x &gt;= 0</code>&nbsp;，那么值为&nbsp;<code>x</code>&nbsp;。</li>
	<li>如果&nbsp;<code>x &lt; 0</code>&nbsp;，那么值为&nbsp;<code>-x</code>&nbsp;。</li>
</ul>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [1,2,2,1], k = 1
<b>输出：</b>4
<b>解释：</b>差的绝对值为 1 的数对为：
- [<em><strong>1</strong></em>,<em><strong>2</strong></em>,2,1]
- [<em><strong>1</strong></em>,2,<em><strong>2</strong></em>,1]
- [1,<em><strong>2</strong></em>,2,<em><strong>1</strong></em>]
- [1,2,<em><strong>2</strong></em>,<em><strong>1</strong></em>]
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [1,3], k = 3
<b>输出：</b>0
<b>解释：</b>没有任何数对差的绝对值为 3 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>nums = [3,2,1,5,4], k = 2
<b>输出：</b>3
<b>解释：</b>差的绝对值为 2 的数对为：
- [<em><strong>3</strong></em>,2,<em><strong>1</strong></em>,5,4]
- [<em><strong>3</strong></em>,2,1,<em><strong>5</strong></em>,4]
- [3,<em><strong>2</strong></em>,1,5,<em><strong>4</strong></em>]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 200</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>1 &lt;= k &lt;= 99</code></li>
</ul>

## 解法

**方法一：暴力枚举**

我们注意到，数组 `nums` 的长度不超过200，因此我们可以枚举所有的数对(i, j)，其中i < j，并判断|nums[i] - nums[j]|是否等于k，是则答案加一。

最后返回答案即可。

时间复杂度O(n^2)，空间复杂度O(1)。其中n为数组 `nums` 的长度。

**方法二：哈希表或数组**

我们可以使用哈希表或数组记录数组 `nums` 中每个数出现的次数，然后枚举数组 `nums` 中的每个数x，判断x + k和x - k是否在数组 `nums` 中，是则答案加上x+k和x-k出现的次数之和。

最后返回答案即可。

时间复杂度O(n)，空间复杂度O(n)。其中n为数组 `nums` 的长度。

### **Java**

```java
class Solution {
    public int countKDifference(int[] nums, int k) {
        int ans = 0;
        for (int i = 0, n = nums.length; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (Math.abs(nums[i] - nums[j]) == k) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int countKDifference(int[] nums, int k) {
        int ans = 0;
        int[] cnt = new int[110];
        for (int num : nums) {
            if (num >= k) {
                ans += cnt[num - k];
            }
            if (num + k <= 100) {
                ans += cnt[num + k];
            }
            ++cnt[num];
        }
        return ans;
    }
}
```
