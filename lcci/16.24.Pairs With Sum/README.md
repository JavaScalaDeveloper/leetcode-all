# [面试题 16.24. 数对和](https://leetcode.cn/problems/pairs-with-sum-lcci)

[中文文档](/lcci/16.24.Pairs%20With%20Sum/README.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>设计一个算法，找出数组中两数之和为指定值的所有整数对。一个数只能属于一个数对。</p>
<p><strong>示例 1:</strong></p>
<pre><strong>输入:</strong> nums = [5,6,5], target = 11
<strong>输出: </strong>[[5,6]]</pre>
<p><strong>示例 2:</strong></p>
<pre><strong>输入:</strong> nums = [5,6,5,6], target = 11
<strong>输出: </strong>[[5,6],[5,6]]</pre>
<p><strong>提示：</strong></p>
<ul>
	<li><code>nums.length &lt;= 100000</code></li>
</ul>

## 解法

**方法一：哈希表**

我们可以使用哈希表来存储数组中的元素，键为数组中的元素，值为该元素出现的次数。

遍历数组，对于每个元素 $x$，我们计算 $y = target - x$，如果哈希表中存在 $y$，则说明存在一对数 $(x, y)$，我们将其加入答案，并减少 $y$ 的出现次数。如果哈希表中不存在 $y$，则说明不存在这样的数对，我们将 $x$ 的出现次数加 $1$。

遍历结束后，即可得到答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组的长度。

### **Java**

```java
class Solution {
    public List<List<Integer>> pairSums(int[] nums, int target) {
        Map<Integer, Integer> cnt = new HashMap<>();
        List<List<Integer>> ans = new ArrayList<>();
        for (int x : nums) {
            int y = target - x;
            if (cnt.containsKey(y)) {
                ans.add(List.of(x, y));
                if (cnt.merge(y, -1, Integer::sum) == 0) {
                    cnt.remove(y);
                }
            } else {
                cnt.merge(x, 1, Integer::sum);
            }
        }
        return ans;
    }
}
```

### **TypeScript**
