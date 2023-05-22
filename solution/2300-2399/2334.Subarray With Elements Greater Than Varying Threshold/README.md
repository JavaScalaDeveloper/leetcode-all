# [2334. 元素值大于变化阈值的子数组](https://leetcode.cn/problems/subarray-with-elements-greater-than-varying-threshold)

## 题目描述

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>threshold</code>&nbsp;。</p>

<p>找到长度为 <code>k</code>&nbsp;的&nbsp;<code>nums</code>&nbsp;子数组，满足数组中&nbsp;<strong>每个</strong>&nbsp;元素都 <strong>大于</strong>&nbsp;<code>threshold / k</code>&nbsp;。</p>

<p>请你返回满足要求的 <strong>任意</strong>&nbsp;子数组的 <strong>大小</strong>&nbsp;。如果没有这样的子数组，返回&nbsp;<code>-1</code>&nbsp;。</p>

<p><strong>子数组</strong> 是数组中一段连续非空的元素序列。</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [1,3,4,3,1], threshold = 6
<b>输出：</b>3
<b>解释：</b>子数组 [3,4,3] 大小为 3 ，每个元素都大于 6 / 3 = 2 。
注意这是唯一合法的子数组。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [6,5,6,5,8], threshold = 7
<b>输出：</b>1
<b>解释：</b>子数组 [8] 大小为 1 ，且 8 &gt; 7 / 1 = 7 。所以返回 1 。
注意子数组 [6,5] 大小为 2 ，每个元素都大于 7 / 2 = 3.5 。
类似的，子数组 [6,5,6] ，[6,5,6,5] ，[6,5,6,5,8] 都是符合条件的子数组。
所以返回 2, 3, 4 和 5 都可以。</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], threshold &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

**方法一：并查集**

考虑**从大到小遍历**数组nums中的每个元素v，用并查集来维护以v作为子数组最小值的连通块。

遍历过程中：

v在数组nums中的下标为i，若下标i-1对应的元素遍历过，可以将i-1与i进行合并，同理，若下标i+1对应的元素也遍历过了，将i与i+1合并。合并过程中更新连通块的大小。

v作为当前连通块的最小值，当前连通块的大小为size[find(i)]，若v>\frac{\text{threshold}}{size[find(i)]}，说明找到了满足条件的子数组，返回true。

否则遍历结束，返回-1。

时间复杂度O(nlogn)。

相似题目：[1562. 查找大小为 M 的最新分组](/solution/1500-1599/1562.Find%20Latest%20Group%20of%20Size%20M/README.md)

**方法二：单调栈**

利用单调栈，得到以当前元素nums[i]作为最小元素的左右边界left[i]（左边第一个比nums[i]小的元素的位置）,right[i]（右边第一个比nums[i]小的元素的位置）。

那么对于当前元素nums[i]，有k=right[i]-left[i]-1，若nums[i]>\frac{\text{threshold}}{k}，说明找到了满足条件的子数组，返回true。

否则遍历结束，返回-1。

时间复杂度O(n)。

### **Java**

```java
class Solution {
    private int[] p;
    private int[] size;

    public int validSubarraySize(int[] nums, int threshold) {
        int n = nums.length;
        p = new int[n];
        size = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
            size[i] = 1;
        }
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; ++i) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }
        Arrays.sort(arr, (a, b) -> b[0] - a[0]);
        boolean[] vis = new boolean[n];
        for (int[] e : arr) {
            int v = e[0], i = e[1];
            if (i > 0 && vis[i - 1]) {
                merge(i, i - 1);
            }
            if (i < n - 1 && vis[i + 1]) {
                merge(i, i + 1);
            }
            if (v > threshold / size[find(i)]) {
                return size[find(i)];
            }
            vis[i] = true;
        }
        return -1;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    private void merge(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) {
            return;
        }
        p[pa] = pb;
        size[pb] += size[pa];
    }
}
```

```java
class Solution {
    public int validSubarraySize(int[] nums, int threshold) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, n);
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            int v = nums[i];
            while (!stk.isEmpty() && nums[stk.peek()] >= v) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                left[i] = stk.peek();
            }
            stk.push(i);
        }
        stk.clear();
        for (int i = n - 1; i >= 0; --i) {
            int v = nums[i];
            while (!stk.isEmpty() && nums[stk.peek()] >= v) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                right[i] = stk.peek();
            }
            stk.push(i);
        }
        for (int i = 0; i < n; ++i) {
            int v = nums[i];
            int k = right[i] - left[i] - 1;
            if (v > threshold / k) {
                return k;
            }
        }
        return -1;
    }
}
```
