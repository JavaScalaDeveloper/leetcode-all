# [456. 132 模式](https://leetcode.cn/problems/132-pattern)

[English Version](/solution/0400-0499/0456.132%20Pattern/README_EN.md)

## 题目描述

<p>给你一个整数数组 <code>nums</code> ，数组中共有 <code>n</code> 个整数。<strong>132 模式的子序列</strong> 由三个整数 <code>nums[i]</code>、<code>nums[j]</code> 和 <code>nums[k]</code> 组成，并同时满足：<code>i < j < k</code> 和 <code>nums[i] < nums[k] < nums[j]</code> 。</p>

<p>如果 <code>nums</code> 中存在 <strong>132 模式的子序列</strong> ，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,4]
<strong>输出：</strong>false
<strong>解释：</strong>序列中不存在 132 模式的子序列。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,1,4,2]
<strong>输出：</strong>true
<strong>解释：</strong>序列中有 1 个 132 模式的子序列： [1, 4, 2] 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [-1,3,2,0]
<strong>输出：</strong>true
<strong>解释：</strong>序列中有 3 个 132 模式的的子序列：[-1, 3, 2]、[-1, 3, 0] 和 [-1, 2, 0] 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 <= n <= 2 * 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> <= nums[i] <= 10<sup>9</sup></code></li>
</ul>

## 解法

**方法一：单调栈**

我们可以枚举从右往左枚举整数 $nums[i]$，并维护一个单调栈，栈中的元素从栈底到栈顶单调递减。维护一个变量 $vk$，表示 $nums[i]$ 右侧且小于 $nums[i]$ 的最大值。初始时，$vk$ 的值为 $-\infty$。

我们从右往左遍历数组，对于遍历到的每个元素 $nums[i]$，如果 $nums[i]$ 小于 $vk$，则说明我们找到了一个满足 $nums[i] \lt nums[k] \lt nums[j]$ 的三元组，返回 `true`。否则，如果栈顶元素小于 $nums[i]$，则我们循环将栈顶元素出栈，并且更新 $vk$ 的值为出栈元素，直到栈为空或者栈顶元素大于等于 $nums[i]$。最后，我们将 $nums[i]$ 入栈。

如果遍历结束后仍未找到满足条件的三元组，说明不存在这样的三元组，返回 `false`。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组的长度。

**方法二：离散化 + 树状数组**

我们可以用树状数组维护比某个数小的元素的个数，用一个数组 $left$ 记录 $nums[i]$ 左侧的最小值。

我们从右往左遍历数组，对于遍历到的每个元素 $nums[i]$，我们将 $nums[i]$ 离散化为一个整数 $x$，将 $left[i]$ 离散化为一个整数 $y$，如果此时 $x \gt y$，并且树状数组中存在比 $y$ 大但比 $x$ 小的元素，则说明存在满足 $nums[i] \lt nums[k] \lt nums[j]$ 的三元组，返回 `true`。否则，我们将 $nums[i]$ 的离散化结果 $x$ 更新到树状数组中。

如果遍历结束后仍未找到满足条件的三元组，说明不存在这样的三元组，返回 `false`。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组的长度。

### **Java**

```java
class Solution {
    public boolean find132pattern(int[] nums) {
        int vk = -(1 << 30);
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = nums.length - 1; i >= 0; --i) {
            if (nums[i] < vk) {
                return true;
            }
            while (!stk.isEmpty() && stk.peek() < nums[i]) {
                vk = stk.pop();
            }
            stk.push(nums[i]);
        }
        return false;
    }
}
```

```java
class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new int[n + 1];
    }

    public void update(int x, int v) {
        while (x <= n) {
            c[x] += v;
            x += x & -x;
        }
    }

    public int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= x & -x;
        }
        return s;
    }
}

class Solution {
    public boolean find132pattern(int[] nums) {
        int[] s = nums.clone();
        Arrays.sort(s);
        int n = nums.length;
        int m = 0;
        int[] left = new int[n + 1];
        left[0] = 1 << 30;
        for (int i = 0; i < n; ++i) {
            left[i + 1] = Math.min(left[i], nums[i]);
            if (i == 0 || s[i] != s[i - 1]) {
                s[m++] = s[i];
            }
        }
        BinaryIndexedTree tree = new BinaryIndexedTree(m);
        for (int i = n - 1; i >= 0; --i) {
            int x = search(s, m, nums[i]);
            int y = search(s, m, left[i]);
            if (x > y && tree.query(x - 1) > tree.query(y)) {
                return true;
            }
            tree.update(x, 1);
        }
        return false;
    }

    private int search(int[] nums, int r, int x) {
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
