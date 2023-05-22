# [面试题 17.04. 消失的数字](https://leetcode.cn/problems/missing-number-lcci)

[English Version](/lcci/17.04.Missing%20Number/README_EN.md)

## 题目描述


<p>数组<code>nums</code>包含从<code>0</code>到<code>n</code>的所有整数，但其中缺了一个。请编写代码找出那个缺失的整数。你有办法在O(n)时间内完成吗？</p>

<p><strong>注意：</strong>本题相对书上原题稍作改动</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>[3,0,1]
<strong>输出：</strong>2</pre>



<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>[9,6,4,2,3,5,7,0,1]
<strong>输出：</strong>8
</pre>

## 解法

**方法一：排序**

我们可以先对数组 $nums$ 进行排序，然后遍历排序后的数组，判断当前元素是否等于其下标，若不等，则返回下标即可。

否则遍历结束后，返回数组长度即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组 $nums$ 的长度。

**方法二：求和**

我们可以先求出 $0$ 到 $n$ 的和，然后遍历数组 $nums$，将数组中的元素依次减去，最后剩下的值即为缺失的数字。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 $nums$ 的长度。

**方法三：位运算**

我们可以使用异或运算，将 $0$ 到 $n$ 的所有数与数组 $nums$ 中的数进行异或运算，最后剩下的值即为缺失的数字。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 $nums$ 的长度。

### **Java**

```java
class Solution {
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (i != nums[i]) {
                return i;
            }
        }
        return n;
    }
}
```

```java
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int ans = n;
        for (int i = 0; i < n; ++i) {
            ans += i - nums[i];
        }
        return ans;
    }
}
```

```java
class Solution {
    public int missingNumber(int[] nums) {
        int ans = 0;
        for (int i = 1; i <= nums.length; ++i) {
            ans ^= i ^ nums[i - 1];
        }
        return ans;
    }
}
```
