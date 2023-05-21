# [368. 最大整除子集](https://leetcode.cn/problems/largest-divisible-subset)

## 题目描述

给你一个由 <strong>无重复</strong> 正整数组成的集合 <code>nums</code> ，请你找出并返回其中最大的整除子集 <code>answer</code> ，子集中每一元素对 <code>(answer[i], answer[j])</code> 都应当满足：

<ul>
	<li><code>answer[i] % answer[j] == 0</code> ，或</li>
	<li><code>answer[j] % answer[i] == 0</code></li>
</ul>

<p>如果存在多个有效解子集，返回其中任何一个均可。</p>



<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3]
<strong>输出：</strong>[1,2]
<strong>解释：</strong>[1,3] 也会被视为正确答案。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,4,8]
<strong>输出：</strong>[1,2,4,8]
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 1000</code></li>
	<li><code>1 <= nums[i] <= 2 * 10<sup>9</sup></code></li>
	<li><code>nums</code> 中的所有整数 <strong>互不相同</strong></li>
</ul>

## 解法

**方法一：排序 + 动态规划**

我们先对数组进行排序，这样可以保证对于任意的i \lt j，如果nums[i]可以整除nums[j]，那么nums[i]一定在nums[j]的左边。

接下来，我们定义f[i]表示以nums[i]为最大元素的最大整除子集的大小，初始时f[i]=1。

对于每一个i，我们从左往右枚举j，如果nums[i]可以被nums[j]整除，那么f[i]可以从f[j]转移而来，我们更新f[i]=max(f[i], f[j]+1)。过程中，我们记录f[i]的最大值的下标k以及对应的子集大小m。

最后，我们从k开始倒序遍历，如果nums[k]可以被nums[i]整除，且f[i]=m，那么nums[i]就是一个整除子集的元素，我们将nums[i]加入答案，并将m减1，同时更新k=i。继续倒序遍历，直到m=0。

时间复杂度O(n^2)，空间复杂度O(n)。其中n是数组的长度。

### **Java**

```java
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] f = new int[n];
        Arrays.fill(f, 1);
        int k = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[i] % nums[j] == 0) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            if (f[k] < f[i]) {
                k = i;
            }
        }
        int m = f[k];
        List<Integer> ans = new ArrayList<>();
        for (int i = k; m > 0; --i) {
            if (nums[k] % nums[i] == 0 && f[i] == m) {
                ans.add(nums[i]);
                k = i;
                --m;
            }
        }
        return ans;
    }
}
```
