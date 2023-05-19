# [2475. 数组中不等三元组的数目](https://leetcode.cn/problems/number-of-unequal-triplets-in-array)

[English Version](/solution/2400-2499/2475.Number%20of%20Unequal%20Triplets%20in%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的正整数数组 <code>nums</code> 。请你找出并统计满足下述条件的三元组 <code>(i, j, k)</code> 的数目：</p>

<ul>
	<li><code>0 &lt;= i &lt; j &lt; k &lt; nums.length</code></li>
	<li><code>nums[i]</code>、<code>nums[j]</code> 和 <code>nums[k]</code> <strong>两两不同</strong> 。
	<ul>
		<li>换句话说：<code>nums[i] != nums[j]</code>、<code>nums[i] != nums[k]</code> 且 <code>nums[j] != nums[k]</code> 。</li>
	</ul>
	</li>
</ul>

<p>返回满足上述条件三元组的数目<em>。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [4,4,2,4,3]
<strong>输出：</strong>3
<strong>解释：</strong>下面列出的三元组均满足题目条件：
- (0, 2, 4) 因为 4 != 2 != 3
- (1, 2, 4) 因为 4 != 2 != 3
- (2, 3, 4) 因为 2 != 4 != 3
共计 3 个三元组，返回 3 。
注意 (2, 0, 4) 不是有效的三元组，因为 2 &gt; 0 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,1,1,1]
<strong>输出：</strong>0
<strong>解释：</strong>不存在满足条件的三元组，所以返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

## 解法

**方法一：暴力枚举**

我们可以直接枚举所有的三元组 $(i, j, k)$，统计所有符合条件的数量。

时间复杂度 $O(n^3)$，空间复杂度 $O(1)$。其中 $n$ 为数组 `nums` 的长度。

**方法二：排序 + 枚举中间元素 + 二分查找**

我们可以先对数组 `nums` 进行排序。

然后遍历 `nums`，枚举中间元素 $nums[j]$，在 $nums[j]$ 左侧找到最近的下标 $i$，使得 $nums[i] \lt nums[j]$ 成立；在 $nums[j]$ 右侧找到最近的下标 $k$，使得 $nums[k] \gt nums[j]$ 成立。那么以 $nums[j]$ 作为中间元素，且符合条件的三元组数量为 $(i+1) \times (n - k)$，累加到答案中。

时间复杂度 $O(n\times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组 `nums` 的长度。

**方法三：哈希表**

我们还可以使用哈希表 $cnt$ 来统计数组 `nums` 中每个元素的数量。

然后遍历哈希表 $cnt$，枚举中间元素的个数 $b$，左侧元素个数记为 $a$，那么右侧元素个数有 $n-a-b$，此时符合条件的三元组数量为 $a \times b \times c$，累加到答案中。接着更新 $a=a+b$，继续枚举中间元素的个数 $b$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 `nums` 的长度。

### **Java**

```java
class Solution {
    public int unequalTriplets(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                for (int k = j + 1; k < n; ++k) {
                    if (nums[i] != nums[j] && nums[j] != nums[k] && nums[i] != nums[k]) {
                        ++ans;
                    }
                }
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int unequalTriplets(int[] nums) {
        Arrays.sort(nums);
        int ans = 0, n  = nums.length;
        for (int j = 1; j < n - 1; ++j) {
            int i = search(nums, nums[j], 0, j) - 1;
            int k = search(nums, nums[j] + 1, j + 1, n);
            if (i >= 0 && k < n) {
                ans += (i + 1) * (n - k);
            }
        }
        return ans;
    }

    private int search(int[] nums, int x, int left, int right) {
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

```java
class Solution {
    public int unequalTriplets(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int v : nums) {
            cnt.put(v, cnt.getOrDefault(v, 0) + 1);
        }
        int ans = 0, a = 0;
        int n = nums.length;
        for (int b : cnt.values()) {
            int c = n - a - b;
            ans += a * b * c;
            a += b;
        }
        return ans;
    }
}
```

### **TypeScript**
