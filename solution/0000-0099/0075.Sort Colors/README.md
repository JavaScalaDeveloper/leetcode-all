# [75. 颜色分类](https://leetcode.cn/problems/sort-colors)

[English Version](/solution/0000-0099/0075.Sort%20Colors/README_EN.md)

## 题目描述

<p>给定一个包含红色、白色和蓝色、共&nbsp;<code>n</code><em> </em>个元素的数组<meta charset="UTF-8" />&nbsp;<code>nums</code>&nbsp;，<strong><a href="https://baike.baidu.com/item/%E5%8E%9F%E5%9C%B0%E7%AE%97%E6%B3%95" target="_blank">原地</a></strong>对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。</p>

<p>我们使用整数 <code>0</code>、&nbsp;<code>1</code> 和 <code>2</code> 分别表示红色、白色和蓝色。</p>

<ul>
</ul>

<p>必须在不使用库内置的 sort 函数的情况下解决这个问题。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,0,2,1,1,0]
<strong>输出：</strong>[0,0,1,1,2,2]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,0,1]
<strong>输出：</strong>[0,1,2]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 300</code></li>
	<li><code>nums[i]</code> 为 <code>0</code>、<code>1</code> 或 <code>2</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong></p>

<ul>
	<li>你能想出一个仅使用常数空间的一趟扫描算法吗？</li>
</ul>

## 解法

**计数：**

-   遍历 `nums`，记录其中 `0`、`1` 和 `2` 出现的次数。
-   依照记录的数字，按照顺序重新填充 `nums`。

**双指针：**

数组元素只存在 `0`、`1` 和 `2` 三种，因此将 `0` 移动至数组头部，`2` 移动至数组尾部，排序便完成了。

-   安排两个变量，分别指向数组头部与尾部。
-   遍历数组，分三种情况：
    -   `0`：与头指针数值交换，并向前一步，遍历指针向前。
    -   `2`：与尾指针数值交换，并向后一步。**遍历指针不变**（还需要处理交换上来的数值）。
    -   `1`：遍历指针向前。

### **Java**

```java
class Solution {
    public void sortColors(int[] nums) {
        int i = -1, j = nums.length;
        int cur = 0;
        while (cur < j) {
            if (nums[cur] == 0) {
                swap(nums, cur++, ++i);
            } else if (nums[cur] == 1) {
                ++cur;
            } else {
                swap(nums, cur, --j);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```
