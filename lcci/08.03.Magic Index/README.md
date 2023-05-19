# [面试题 08.03. 魔术索引](https://leetcode.cn/problems/magic-index-lcci)

[English Version](/lcci/08.03.Magic%20Index/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>魔术索引。 在数组<code>A[0...n-1]</code>中，有所谓的魔术索引，满足条件<code>A[i] = i</code>。给定一个有序整数数组，编写一种方法找出魔术索引，若有的话，在数组A中找出一个魔术索引，如果没有，则返回-1。若有多个魔术索引，返回索引值最小的一个。</p>

<p><strong>示例1:</strong></p>

<pre><strong> 输入</strong>：nums = [0, 2, 3, 4, 5]
<strong> 输出</strong>：0
<strong> 说明</strong>: 0下标的元素为0
</pre>

<p><strong>示例2:</strong></p>

<pre><strong> 输入</strong>：nums = [1, 1, 1]
<strong> 输出</strong>：1
</pre>

<p><strong>提示:</strong></p>

<ol>
	<li>nums长度在[1, 1000000]之间</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**线性查找：**

遍历数组，当 `A[i] = i` 时直接返回即可。

**优化：**

在遍历的基础，进行可能的 "跳跃"，结束时执行 `i = max(A[i], i + 1)`，而不再单纯 `i++`。

可行性证明：

因为数组是**有序**的，若 `A[i] != i`，那么就可以将 `A[i]` 以下的可能全部排除，直接将 `i` 设定为 `A[i]`。

若是考虑最糟的状况（所有元素都为负数），则该优化与遍历无差别。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->



### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findMagicIndex(int[] nums) {
        int left = 0, right = nums.length - 1;
        return find(nums, left, right);
    }

    private int find(int[] nums, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) >> 1;
        int leftIndex = find(nums, left, mid - 1);
        if (leftIndex != -1) {
            return leftIndex;
        }
        if (nums[mid] == mid) {
            return mid;
        }
        return find(nums, mid + 1, right);
    }
}
```













### **TypeScript**





## **Rust**





### **...**

```

```


