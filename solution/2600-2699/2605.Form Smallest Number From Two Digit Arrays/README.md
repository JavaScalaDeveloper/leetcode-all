# [2605. 从两个数字数组里生成最小数字](https://leetcode.cn/problems/form-smallest-number-from-two-digit-arrays)

[English Version](/solution/2600-2699/2605.Form%20Smallest%20Number%20From%20Two%20Digit%20Arrays/README_EN.md)

## 题目描述

给你两个只包含 1 到 9 之间数字的数组&nbsp;<code>nums1</code> 和&nbsp;<code>nums2</code>&nbsp;，每个数组中的元素 <strong>互不相同</strong>&nbsp;，请你返回 <strong>最小</strong> 的数字，两个数组都 <strong>至少</strong> 包含这个数字的某个数位。

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums1 = [4,1,3], nums2 = [5,7]
<b>输出：</b>15
<b>解释：</b>数字 15 的数位 1 在 nums1 中出现，数位 5 在 nums2 中出现。15 是我们能得到的最小数字。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums1 = [3,5,2,6], nums2 = [3,1,7]
<b>输出：</b>3
<b>解释：</b>数字 3 的数位 3 在两个数组中都出现了。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 9</code></li>
	<li><code>1 &lt;= nums1[i], nums2[i] &lt;= 9</code></li>
	<li>每个数组中，元素 <strong>互不相同</strong>&nbsp;。</li>
</ul>

## 解法

**方法一：枚举**

我们观察发现，如果数组 $nums1$ 和 $nums2$ 中有相同的数字，那么相同数字中的最小值一定是最小的数字。否则我们取 $nums1$ 中的数字 $a$ 和 $nums2$ 中的数字 $b$，将 $a$ 和 $b$ 拼接成两个数字，取其中较小的数字即可。

时间复杂度 $O(m \times n)$，空间复杂度 $O(1)$。其中 $m$ 和 $n$ 分别是数组 $nums1$ 和 $nums2$ 的长度。

**方法二：哈希表或数组 + 枚举**

我们可以用哈希表或数组记录数组 $nums1$ 和 $nums2$ 中的数字，然后枚举 $1 \sim 9$，如果 $i$ 在两个数组中都出现了，那么 $i$ 就是最小的数字。否则我们取 $nums1$ 中的数字 $a$ 和 $nums2$ 中的数字 $b$，将 $a$ 和 $b$ 拼接成两个数字，取其中较小的数字即可。

时间复杂度 $(m + n)$，空间复杂度 $O(C)$。其中 $m$ 和 $n$ 分别是数组 $nums1$ 和 $nums2$ 的长度；而 $C$ 是数组 $nums1$ 和 $nums2$ 中数字的范围，本题中 $C = 10$。

**方法三：位运算**

由于数字的范围是 $1 \sim 9$，我们可以用一个长度为 $10$ 的二进制数来表示数组 $nums1$ 和 $nums2$ 中的数字。我们用 $mask1$ 表示数组 $nums1$ 中的数字，用 $mask2$ 表示数组 $nums2$ 中的数字。

如果 $mask1$ 和 $mask2$ 进行按位与得到的数字 $mask$ 不等于 $0$，那么我们提取 $mask$ 中最后一位 $1$ 所在的位置，即为最小的数字。

否则，我们分别提取 $mask1$ 和 $mask2$ 中最后一位 $1$ 所在的位置，分别记为 $a$ 和 $b$，那么最小的数字就是 $min(a \times 10 + b, b \times 10 + a)$。

时间复杂度 $O(m + n)$，空间复杂度 $O(1)$。其中 $m$ 和 $n$ 分别是数组 $nums1$ 和 $nums2$ 的长度。

### **Java**

```java
class Solution {
    public int minNumber(int[] nums1, int[] nums2) {
        int ans = 100;
        for (int a : nums1) {
            for (int b : nums2) {
                if (a == b) {
                    ans = Math.min(ans, a);
                } else {
                    ans = Math.min(ans, Math.min(a * 10 + b, b * 10 + a));
                }
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int minNumber(int[] nums1, int[] nums2) {
        boolean[] s1 = new boolean[10];
        boolean[] s2 = new boolean[10];
        for (int x : nums1) {
            s1[x] = true;
        }
        for (int x : nums2) {
            s2[x] = true;
        }
        int a = 0, b = 0;
        for (int i = 1; i < 10; ++i) {
            if (s1[i] && s2[i]) {
                return i;
            }
            if (a == 0 && s1[i]) {
                a = i;
            }
            if (b == 0 && s2[i]) {
                b = i;
            }
        }
        return Math.min(a * 10 + b, b * 10 + a);
    }
}
```

```java
class Solution {
    public int minNumber(int[] nums1, int[] nums2) {
        int mask1 = 0, mask2 = 0;
        for (int x : nums1) {
            mask1 |= 1 << x;
        }
        for (int x : nums2) {
            mask2 |= 1 << x;
        }
        int mask = mask1 & mask2;
        if (mask != 0) {
            return Integer.numberOfTrailingZeros(mask);
        }
        int a = Integer.numberOfTrailingZeros(mask1);
        int b = Integer.numberOfTrailingZeros(mask2);
        return Math.min(a * 10 + b, b * 10 + a);
    }
}
```
