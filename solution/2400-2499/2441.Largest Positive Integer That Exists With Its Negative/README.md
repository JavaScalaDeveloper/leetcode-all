# [2441. 与对应负数同时存在的最大正整数](https://leetcode.cn/problems/largest-positive-integer-that-exists-with-its-negative)

## 题目描述

<p>给你一个 <strong>不包含</strong> 任何零的整数数组 <code>nums</code> ，找出自身与对应的负数都在数组中存在的最大正整数 <code>k</code> 。</p>

<p>返回正整数<em> </em><code>k</code> ，如果不存在这样的整数，返回 <code>-1</code> 。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [-1,2,-3,3]
<strong>输出：</strong>3
<strong>解释：</strong>3 是数组中唯一一个满足题目要求的 k 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [-1,10,6,7,-7,1]
<strong>输出：</strong>7
<strong>解释：</strong>数组中存在 1 和 7 对应的负数，7 的值更大。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [-10,8,6,7,-2,-3]
<strong>输出：</strong>-1
<strong>解释：</strong>不存在满足题目要求的 k ，返回 -1 。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>nums[i] != 0</code></li>
</ul>

## 解法

**方法一：哈希表**

我们可以用哈希表s记录数组中出现的所有元素，用一个变量ans记录满足题目要求的最大正整数，初始时ans = -1。

接下来，我们遍历哈希表s中的每个元素x，如果s中存在-x，那么我们就更新ans = \max(ans, x)。

遍历结束后，返回ans即可。

时间复杂度O(n)，空间复杂度O(n)。其中n是数组的长度。

### **Java**

```java
class Solution {
    public int findMaxK(int[] nums) {
        int ans = -1;
        Set<Integer> s = new HashSet<>();
        for (int x : nums) {
            s.add(x);
        }
        for (int x : s) {
            if (s.contains(-x)) {
                ans = Math.max(ans, x);
            }
        }
        return ans;
    }
}
```
