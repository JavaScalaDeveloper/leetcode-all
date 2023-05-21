# [611. 有效三角形的个数](https://leetcode.cn/problems/valid-triangle-number)

## 题目描述

<p>给定一个包含非负整数的数组&nbsp;<code>nums</code> ，返回其中可以组成三角形三条边的三元组个数。</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [2,2,3,4]
<strong>输出:</strong> 3
<strong>解释:</strong>有效的组合是: 
2,3,4 (使用第一个 2)
2,3,4 (使用第二个 2)
2,2,3
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [4,2,3,4]
<strong>输出:</strong> 4</pre>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

## 解法

**方法一：排序 + 二分查找**

一个有效三角形需要满足：**任意两边之和大于第三边**。即：`a + b > c`①, `a + c > b`②, `b + c > a`③。

如果我们将边按从小到大顺序排列，即 `a < b < c`，那么显然 ②③ 成立，我们只需要确保 ① 也成立，就可以形成一个有效三角形。

我们在 `[0, n - 3]` 范围内枚举 i，在 `[i + 1, n - 2]` 范围内枚举 j，在 `[j + 1, n - 1]` 范围内进行二分查找，找出第一个大于等于 `nums[i] + nums[j]` 的下标 left，那么在 `[j + 1, left - 1]` 范围内的 k 满足条件，将其累加到结果 ans。

时间复杂度：O(n^2\log n)。

### **Java**

```java
class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int res = 0;
        for (int i = n - 1; i >= 2; --i) {
            int l = 0, r = i - 1;
            while (l < r) {
                if (nums[l] + nums[r] > nums[i]) {
                    res += r - l;
                    --r;
                } else {
                    ++l;
                }
            }
        }
        return res;
    }
}
```

```java
class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0, n = nums.length; i < n - 2; ++i) {
            for (int j = i + 1; j < n - 1; ++j) {
                int left = j + 1, right = n;
                while (left < right) {
                    int mid = (left + right) >> 1;
                    if (nums[mid] >= nums[i] + nums[j]) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }
                ans += left - j - 1;
            }
        }
        return ans;
    }
}
```
