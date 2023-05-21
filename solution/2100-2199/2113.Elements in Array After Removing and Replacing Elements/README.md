# [2113. 查询删除和添加元素后的数组](https://leetcode.cn/problems/elements-in-array-after-removing-and-replacing-elements)

## 题目描述

<p>给你一个&nbsp;<strong>下标从 0 开始</strong>&nbsp;的数组&nbsp;<code>nums</code>。一开始，在第 <code>0</code> 分钟，数组没有变化。此后每过一分钟，数组的 <strong>最左边</strong> 的元素将被移除，直到数组为空。然后，每过一分钟，数组的 <strong>尾部</strong> 将添加一个元素，添加的顺序和删除的顺序相同，直到数组被复原。此后上述操作无限循环进行。</p>

<ul>
	<li>举个例子，如果 <code>nums = [0, 1, 2]</code>，那么数组将按如下流程变化：<code>[0,1,2] → [1,2] → [2] → [] → [0] → [0,1] → [0,1,2] → [1,2] → [2] → [] → [0] → [0,1] → [0,1,2] → ...</code></li>
</ul>

<p>然后给你一个长度为 <code>n</code> 的二维数组 <code>queries</code>，其中 <code>queries[j] = [time<sub>j</sub>, index<sub>j</sub>]</code>，表示第 <code>j</code> 个查询。第 <code>j</code> 个查询的答案定义如下：</p>

<ul>
	<li>如果在时刻&nbsp;<code>time<sub>j</sub></code>，<code>index<sub>j</sub> &lt; nums.length</code>，那么答案是此时的 <code>nums[index<sub>j</sub>]</code>；</li>
	<li>如果在时刻&nbsp;<code>time<sub>j</sub></code>，<code>index<sub>j</sub> &gt;= nums.length</code>，那么答案是 <code>-1</code>。</li>
</ul>

<p>请返回一个长度为 <code>n</code> 的整数数组 <code>ans</code>，其中 <code>ans[j]</code> 为第 <code>j</code> 个查询的答案。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> nums = [0,1,2], queries = [[0,2],[2,0],[3,2],[5,0]]
<strong>输出:</strong> [2,2,-1,0]
<strong>解释:</strong>
第 0 分钟: [0,1,2] - 数组和 nums 相同。
第 1 分钟: [1,2]   - 最左侧元素 0 被移除。
第 2 分钟: [2]     - 最左侧元素 1 被移除。
第 3 分钟: []      - 最左侧元素 0 被移除。
第 4 分钟: [0]     - 0 被添加到数组尾部。
第 5 分钟: [0,1]   - 1 被添加到数组尾部。

在第 0 分钟, nums[2] 是 2。
在第 2 分钟, nums[0] 是 2。
在第 3 分钟, nums[2] 不存在。
在第 5 分钟, nums[0] 是 0。
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> nums = [2], queries = [[0,0],[1,0],[2,0],[3,0]]
<strong>输出:</strong> [2,-1,2,-1]
第 0 分钟: [2] - 数组和 nums 相同。
第 1 分钟: []  - 最左侧元素 2 被移除。
第 2 分钟: [2] - 2 被添加到数组尾部。
第 3 分钟: []  - 最左侧元素 2 被移除。

在第 0 分钟, nums[0] 是 2。
在第 1 分钟, nums[0] 不存在。
在第 2 分钟, nums[0] 是 2。
在第 3 分钟, nums[0] 不存在。
</pre>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>n == queries.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[j].length == 2</code></li>
	<li><code>0 &lt;= time<sub>j</sub> &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= index<sub>j</sub> &lt; nums.length</code></li>
</ul>

## 解法

**方法一：直接计算**

我们先初始化一个数组ans，长度为m，用于存储答案，初始化所有元素为-1。

接下来遍历数组queries，对于每个查询，我们先获取当前查询的时间t和索引i，先将t对2n取模，然后判断t和n的关系：

-   如果t \lt n，那么t时刻的数组元素个数为n - t，并且数组元素是原数组元素整体向左移动t个位置后的结果，因此如果i \lt n - t，答案为nums[i + t]；
-   如果t \gt n，那么t时刻的数组元素个数为t - n，并且数组元素是原数组元素的前t - n个元素，因此如果i \lt t - n，答案为nums[i]。

最后返回数组ans即可。

时间复杂度O(m)，其中m为数组queries的长度。忽略答案数组的空间消耗，空间复杂度O(1)。

### **Java**

```java
class Solution {
    public int[] elementInNums(int[] nums, int[][] queries) {
        int n = nums.length, m = queries.length;
        int[] ans = new int[m];
        for (int j = 0; j < m; ++j) {
            ans[j] = -1;
            int t = queries[j][0], i = queries[j][1];
            t %= (2 * n);
            if (t < n && i < n - t) {
                ans[j] = nums[i + t];
            } else if (t > n && i < t - n) {
                ans[j] = nums[i];
            }
        }
        return ans;
    }
}
```
