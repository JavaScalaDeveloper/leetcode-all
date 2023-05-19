# [1502. 判断能否形成等差数列](https://leetcode.cn/problems/can-make-arithmetic-progression-from-sequence)

[English Version](/solution/1500-1599/1502.Can%20Make%20Arithmetic%20Progression%20From%20Sequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个数字数组 <code>arr</code> 。</p>

<p>如果一个数列中，任意相邻两项的差总等于同一个常数，那么这个数列就称为 <strong>等差数列</strong> 。</p>

<p>如果可以重新排列数组形成等差数列，请返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [3,5,1]
<strong>输出：</strong>true
<strong>解释：</strong>对数组重新排序得到 [1,3,5] 或者 [5,3,1] ，任意相邻两项的差分别为 2 或 -2 ，可以形成等差数列。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr = [1,2,4]
<strong>输出：</strong>false
<strong>解释：</strong>无法通过重新排序得到等差数列。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>-10^6 &lt;= arr[i] &lt;= 10^6</code></li>
</ul>

## 解法

**方法一：排序 + 遍历**

我们可以先将数组 `arr` 排序，然后遍历数组，判断相邻两项的差是否相等即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组 `arr` 的长度。

### **Java**

```java
class Solution {
    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        int d = arr[1] - arr[0];
        for (int i = 2; i < arr.length; ++i) {
            if (arr[i] - arr[i - 1] != d) {
                return false;
            }
        }
        return true;
    }
}
```

### **TypeScript**

### **C**

```c
int cmp(const void *a, const void *b) {
    return *(int *) a - *(int *) b;
}

bool canMakeArithmeticProgression(int *arr, int arrSize) {
    qsort(arr, arrSize, sizeof(int), cmp);
    for (int i = 2; i < arrSize; i++) {
        if (arr[i - 2] - arr[i - 1] != arr[i - 1] - arr[i]) {
            return 0;
        }
    }
    return 1;
}
```
