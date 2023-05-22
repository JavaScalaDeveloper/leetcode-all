# [448. 找到所有数组中消失的数字](https://leetcode.cn/problems/find-all-numbers-disappeared-in-an-array)

## 题目描述

<p>给你一个含 <code>n</code> 个整数的数组 <code>nums</code> ，其中 <code>nums[i]</code> 在区间 <code>[1, n]</code> 内。请你找出所有在 <code>[1, n]</code> 范围内但没有出现在 <code>nums</code> 中的数字，并以数组的形式返回结果。</p>



<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [4,3,2,7,8,2,3,1]
<strong>输出：</strong>[5,6]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1]
<strong>输出：</strong>[2]
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 <= n <= 10<sup>5</sup></code></li>
	<li><code>1 <= nums[i] <= n</code></li>
</ul>

<p><strong>进阶：</strong>你能在不使用额外空间且时间复杂度为<em> </em><code>O(n)</code><em> </em>的情况下解决这个问题吗? 你可以假定返回的数组不算在额外空间内。</p>

## 解法

**方法一：数组或哈希表**

我们可以使用数组或哈希表记录数组中的数字，然后遍历 `[1, n]` 区间内的数字，若数字不存在于数组或哈希表中，则说明数组中缺失该数字，将其添加到结果列表中。

时间复杂度O(n)，空间复杂度O(n)。其中n为数组长度。

**方法二：原地修改**

我们可以遍历数组nums，将|nums[i]|-1位置的数字标记为负数，表示数组nums[i]出现过。最后遍历数组nums，若nums[i]为正数，则说明数组中缺失i+1，将其添加到结果列表中。

遍历结束后，返回结果列表即可。

时间复杂度O(n)，空间复杂度O(1)。其中n为数组长度。

### **Java**

```java
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        boolean[] s = new boolean[n + 1];
        for (int x : nums) {
            s[x] = true;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (!s[i]) {
                ans.add(i);
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for (int x : nums) {
            int i = Math.abs(x) - 1;
            if (nums[i] > 0) {
                nums[i] *= -1;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                ans.add(i + 1);
            }
        }
        return ans;
    }
}
```
