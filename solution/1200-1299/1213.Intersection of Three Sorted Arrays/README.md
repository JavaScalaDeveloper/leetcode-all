# [1213. 三个有序数组的交集](https://leetcode.cn/problems/intersection-of-three-sorted-arrays)

## 题目描述

<p>给出三个均为 <strong>严格递增排列 </strong>的整数数组&nbsp;<code>arr1</code>，<code>arr2</code> 和&nbsp;<code>arr3</code>。返回一个由&nbsp;<strong>仅 </strong>在这三个数组中&nbsp;<strong>同时出现&nbsp;</strong>的整数所构成的有序数组。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入: </strong>arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8]
<strong>输出: </strong>[1,5]
<strong>解释: </strong>只有 1 和 5 同时在这三个数组中出现.
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入: </strong>arr1 = [197,418,523,876,1356], arr2 = [501,880,1593,1710,1870], arr3 = [521,682,1337,1395,1764]
<strong>输出: </strong>[]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr1.length, arr2.length, arr3.length &lt;= 1000</code></li>
	<li><code>1 &lt;= arr1[i], arr2[i], arr3[i] &lt;= 2000</code></li>
</ul>

## 解法

**方法一：计数**

遍历三个数组，统计每个数字出现的次数，然后遍历任意一个数组，若某个数字出现的次数为3，则将其加入结果数组。

时间复杂度O(n)，空间复杂度O(m)。其中n和m分别为数组的长度和数组中数字的范围。

**方法二：二分查找**

遍历第一个数组，对于其中的每个数字，使用二分查找在第二个数组和第三个数组中查找该数字，若都找到，则将该数字加入结果数组。

时间复杂度O(n × log n)，空间复杂度O(1)。其中n为数组的长度。

### **Java**

```java
class Solution {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        List<Integer> ans = new ArrayList<>();
        int[] cnt = new int[2001];
        for (int x : arr1) {
            ++cnt[x];
        }
        for (int x : arr2) {
            ++cnt[x];
        }
        for (int x : arr3) {
            if (++cnt[x] == 3) {
                ans.add(x);
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        List<Integer> ans = new ArrayList<>();
        for (int x : arr1) {
            int i = Arrays.binarySearch(arr2, x);
            int j = Arrays.binarySearch(arr3, x);
            if (i >= 0 && j >= 0) {
                ans.add(x);
            }
        }
        return ans;
    }
}
```
