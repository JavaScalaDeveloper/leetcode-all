# [217. 存在重复元素](https://leetcode.cn/problems/contains-duplicate)

## 题目描述

给你一个整数数组 <code>nums</code> 。如果任一值在数组中出现 <strong>至少两次</strong> ，返回 <code>true</code> ；如果数组中每个元素互不相同，返回 <code>false</code> 。

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,1]
<strong>输出：</strong>true</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,4]
<strong>输出：</strong>false</pre>

<p><strong>示例&nbsp;3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,1,3,3,4,3,2,4,2]
<strong>输出：</strong>true</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

**方法一：排序**

我们先对数组 `nums` 进行排序。

然后遍历数组，如果存在相邻两个元素相同，说明数组中存在重复元素，直接返回 `true`。

否则，遍历结束，返回 `false`。

时间复杂度 $O(n \times \log n)$。其中 $n$ 是数组 `nums` 的长度。

**方法二：哈希表**

遍历数组，将出现过的元素记录在哈希表 $s$ 中。若元素第二次出现时，说明数组中存在重复元素，直接返回 `true`。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 `nums` 的长度。

### **Java**

```java
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }
}
```

```java
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int num : nums) {
            if (!s.add(num)) {
                return true;
            }
        }
        return false;
    }
}
```
