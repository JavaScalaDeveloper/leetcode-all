# [327. 区间和的个数](https://leetcode.cn/problems/count-of-range-sum)

## 题目描述

<p>给你一个整数数组 <code>nums</code> 以及两个整数 <code>lower</code> 和 <code>upper</code> 。求数组中，值位于范围 <code>[lower, upper]</code> （包含 <code>lower</code> 和 <code>upper</code>）之内的 <strong>区间和的个数</strong> 。</p>

<p><strong>区间和</strong> <code>S(i, j)</code> 表示在 <code>nums</code> 中，位置从 <code>i</code> 到 <code>j</code> 的元素之和，包含 <code>i</code> 和 <code>j</code> (<code>i</code> ≤ <code>j</code>)。</p>


<strong>示例 1：</strong>

<pre>
<strong>输入：</strong>nums = [-2,5,-1], lower = -2, upper = 2
<strong>输出：</strong>3
<strong>解释：</strong>存在三个区间：[0,0]、[2,2] 和 [0,2] ，对应的区间和分别是：-2 、-1 、2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0], lower = 0, upper = 0
<strong>输出：</strong>1
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>-2<sup>31</sup> <= nums[i] <= 2<sup>31</sup> - 1</code></li>
	<li><code>-10<sup>5</sup> <= lower <= upper <= 10<sup>5</sup></code></li>
	<li>题目数据保证答案是一个 <strong>32 位</strong> 的整数</li>
</ul>

## 解法

**方法一：前缀和 + 树状数组**

题目要求区间和，因此我们可以先求出前缀和数组s，其中s[i]表示nums中前i个元素的和。那么对于任意的i < j，s[j+1] - s[i]就是nums中下标在[i, j]的元素之和。

而lower ≤ s[j+1] - s[i] ≤ upper，可以转换为s[j+1] - upper ≤ s[i] ≤ s[j+1] - lower，也就是说，对于当前前缀和s[j+1]，我们需要统计s中有多少个下标i满足s[j+1] - upper ≤ s[i] ≤ s[j+1] - lower。

我们可以用树状数组来维护每个前缀和出现的次数，这样对于每个前缀和s[j+1]，我们只需要查询树状数组中有多少个前缀和s[i]满足s[j+1] - upper ≤ s[i] ≤ s[j+1] - lower即可。

时间复杂度O(n × log n)，空间复杂度O(n)。其中n为数组长度。

### **Java**

```java
class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        this.c = new int[n + 1];
    }

    public void update(int x, int v) {
        while (x <= n) {
            c[x] += v;
            x += x & -x;
        }
    }

    public int query(int x) {
        int s = 0;
        while (x != 0) {
            s += c[x];
            x -= x & -x;
        }
        return s;
    }
}

class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] s = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        long[] arr = new long[n * 3 + 3];
        for (int i = 0, j = 0; i <= n; ++i, j += 3) {
            arr[j] = s[i];
            arr[j + 1] = s[i] - lower;
            arr[j + 2] = s[i] - upper;
        }
        Arrays.sort(arr);
        int m = 0;
        for (int i = 0; i < arr.length; ++i) {
            if (i == 0 || arr[i] != arr[i - 1]) {
                arr[m++] = arr[i];
            }
        }
        BinaryIndexedTree tree = new BinaryIndexedTree(m);
        int ans = 0;
        for (long x : s) {
            int l = search(arr, m, x - upper);
            int r = search(arr, m, x - lower);
            ans += tree.query(r) - tree.query(l - 1);
            tree.update(search(arr, m, x), 1);
        }
        return ans;
    }

    private int search(long[] nums, int r, long x) {
        int l = 0;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l + 1;
    }
}
```
