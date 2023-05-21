# [1658. 将 x 减到 0 的最小操作数](https://leetcode.cn/problems/minimum-operations-to-reduce-x-to-zero)

## 题目描述

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>x</code> 。每一次操作时，你应当移除数组 <code>nums</code> 最左边或最右边的元素，然后从 <code>x</code> 中减去该元素的值。请注意，需要 <strong>修改</strong> 数组以供接下来的操作使用。</p>

<p>如果可以将 <code>x</code> <strong>恰好</strong> 减到 <code>0</code> ，返回<strong> 最小操作数 </strong>；否则，返回 <code>-1</code> 。</p>



<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,4,2,3], x = 5
<strong>输出：</strong>2
<strong>解释：</strong>最佳解决方案是移除后两个元素，将 x 减到 0 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [5,6,7,8,9], x = 4
<strong>输出：</strong>-1
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,2,20,1,1,3], x = 10
<strong>输出：</strong>5
<strong>解释：</strong>最佳解决方案是移除后三个元素和前两个元素（总共 5 次操作），将 x 减到 0 。
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= nums[i] <= 10<sup>4</sup></code></li>
	<li><code>1 <= x <= 10<sup>9</sup></code></li>
</ul>

## 解法

**方法一：哈希表 + 前缀和**

我们可以将问题转换为求中间连续子数组的最大长度，使得子数组的和为x = sum(nums) - x。

定义一个哈希表 `vis`，其中 `vis[s]` 表示前缀和为s的最小下标。

遍历数组 `nums`，对于每个元素nums[i]，我们先将nums[i]加到前缀和s上，如果哈希表中不存在s，则将其加入哈希表，其值为当前下标i。然后我们判断s - x是否在哈希表中，如果存在，则说明存在一个下标j，使得nums[j + 1,..i]的和为x，此时我们更新答案的最小值，即ans = min(ans, n - (i - j))。

遍历结束，如果找不到满足条件的子数组，返回-1，否则返回ans。

时间复杂度O(n)，空间复杂度O(n)。其中n为数组 `nums` 的长度。

**方法二：双指针**

与方法一类似，我们要找到一个子数组，使得子数组的和为x = sum(nums) - x。

定义两个指针j和i，初始时i = j = 0，然后我们向右移动指针i，将nums[i]加到前缀和s上。如果s > x，那么我们循环向右移动指针j，并且将nums[j]从前缀和s上减去，直到s \le x。如果s = x，我们可以更新答案的最小值，即ans = min(ans, n - (i - j + 1))。继续向右移动指针i，重复上述过程。

最后，如果找不到满足条件的子数组，返回-1，否则返回ans。

时间复杂度O(n)，空间复杂度O(1)。其中n为数组 `nums` 的长度。

### **Java**

```java
class Solution {
    public int minOperations(int[] nums, int x) {
        x = -x;
        for (int v : nums) {
            x += v;
        }
        Map<Integer, Integer> vis = new HashMap<>();
        vis.put(0, -1);
        int n = nums.length;
        int ans = 1 << 30;
        for (int i = 0, s = 0; i < n; ++i) {
            s += nums[i];
            vis.putIfAbsent(s, i);
            if (vis.containsKey(s - x)) {
                int j = vis.get(s - x);
                ans = Math.min(ans, n - (i - j));
            }
        }
        return ans == 1 << 30 ? -1 : ans;
    }
}
```

```java
class Solution {
    public int minOperations(int[] nums, int x) {
        x = -x;
        for (int v : nums) {
            x += v;
        }
        int n = nums.length;
        int ans = 1 << 30;
        for (int i = 0, j = 0, s = 0; i < n; ++i) {
            s += nums[i];
            while (j <= i && s > x) {
                s -= nums[j++];
            }
            if (s == x) {
                ans = Math.min(ans, n - (i - j + 1));
            }
        }
        return ans == 1 << 30 ? -1 : ans;
    }
}
```

**
