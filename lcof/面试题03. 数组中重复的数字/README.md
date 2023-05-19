# [面试题 03. 数组中重复的数字](https://leetcode.cn/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/)

## 题目描述

<p>找出数组中重复的数字。</p>

<p><br>
在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>
[2, 3, 1, 0, 2, 5, 3]
<strong>输出：</strong>2 或 3 
</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<p><code>2 &lt;= n &lt;= 100000</code></p>

## 解法

**方法一：排序**

我们可以先对数组 `nums` 进行排序，然后遍历排序后的数组，判断相邻的两个元素是否相等，如果相等，即找到了一个重复的数字，返回该数字即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组 `nums` 的长度。

**方法二：哈希表**

我们可以使用哈希表来解决这个问题，遍历数组 `nums`，对于遍历到的每个元素，判断哈希表中是否存在该元素，如果哈希表中存在该元素，即找到了一个重复的数字，返回该数字即可；如果哈希表中不存在该元素，将该元素加入哈希表中。继续遍历，直到找到一个重复的数字。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 `nums` 的长度。

**方法三：原地交换**

我们可以遍历数组 `nums`，对于遍历到的每个元素 `nums[i]`，判断 `nums[i]` 是否等于 `i`，如果是，则继续遍历下一个元素；如果不是，则将 `nums[i]` 与 `nums[nums[i]]` 进行交换，交换之后，`nums[i]` 的值和下标都发生了改变，如果 `nums[i]` 与 `nums[nums[i]]` 相等，即找到了一个重复的数字，返回该数字即可；如果 `nums[i]` 与 `nums[nums[i]]` 不相等，继续遍历，直到找到一个重复的数字。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 是数组 `nums` 的长度。

### **Java**

```java
class Solution {
    public int findRepeatNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; ; ++i) {
            if (nums[i] == nums[i + 1]) {
                return nums[i];
            }
        }
    }
}
```

```java
class Solution {
    public int findRepeatNumber(int[] nums) {
        Set<Integer> vis = new HashSet<>();
        for (int i = 0; ; ++i) {
            if (!vis.add(nums[i])) {
                return nums[i];
            }
        }
    }
}
```

```java
class Solution {
    public int findRepeatNumber(int[] nums) {
        for (int i = 0;; ++i) {
            while (nums[i] != i) {
                int j = nums[i];
                if (nums[j] == j) {
                    return j;
                }
                int t = nums[i];
                nums[i] = nums[j];
                nums[j] = t;
            }
        }
    }
}
```

### **TypeScript**

### **Kotlin**

```kotlin
class Solution {
    fun findRepeatNumber(nums: IntArray): Int {
        for (i in nums.indices) {
            while (i != nums[i]) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                swap(nums, i, nums[i]);
            }
        }
        return -1;
    }

    fun swap(nums: IntArray, i: Int, j: Int) {
        var t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```
