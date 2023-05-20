# [2170. 使数组变成交替数组的最少操作数](https://leetcode.cn/problems/minimum-operations-to-make-the-array-alternating)

## 题目描述

<p>给你一个下标从 <strong>0</strong> 开始的数组 <code>nums</code> ，该数组由 <code>n</code> 个正整数组成。</p>

<p>如果满足下述条件，则数组 <code>nums</code> 是一个 <strong>交替数组</strong> ：</p>

<ul>
	<li><code>nums[i - 2] == nums[i]</code> ，其中 <code>2 &lt;= i &lt;= n - 1</code> 。</li>
	<li><code>nums[i - 1] != nums[i]</code> ，其中 <code>1 &lt;= i &lt;= n - 1</code> 。</li>
</ul>

<p>在一步 <strong>操作</strong> 中，你可以选择下标 <code>i</code> 并将 <code>nums[i]</code> <strong>更改</strong> 为 <strong>任一</strong> 正整数。</p>

<p>返回使数组变成交替数组的 <strong>最少操作数</strong> 。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,1,3,2,4,3]
<strong>输出：</strong>3
<strong>解释：</strong>
使数组变成交替数组的方法之一是将该数组转换为 [3,1,3,<em><strong>1</strong></em>,<em><strong>3</strong></em>,<em><strong>1</strong></em>] 。
在这种情况下，操作数为 3 。
可以证明，操作数少于 3 的情况下，无法使数组变成交替数组。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,2,2,2]
<strong>输出：</strong>2
<strong>解释：</strong>
使数组变成交替数组的方法之一是将该数组转换为 [1,2,<em><strong>1</strong></em>,2,<em><strong>1</strong></em>].
在这种情况下，操作数为 2 。
注意，数组不能转换成 [<em><strong>2</strong></em>,2,2,2,2] 。因为在这种情况下，nums[0] == nums[1]，不满足交替数组的条件。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

### **Java**

```java
class Solution {
    private int[] nums;
    private int n;

    public int minimumOperations(int[] nums) {
        this.nums = nums;
        n = nums.length;
        int ans = Integer.MAX_VALUE;
        for (int[] e1 : get(0)) {
            for (int[] e2 : get(1)) {
                if (e1[0] != e2[0]) {
                    ans = Math.min(ans, n - (e1[1] + e2[1]));
                }
            }
        }
        return ans;
    }

    private int[][] get(int i) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (; i < n; i += 2) {
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
        }
        int a = 0;
        int n1 = 0;
        int b = 0;
        int n2 = 0;
        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            int k = e.getKey();
            int v = e.getValue();
            if (v > n1) {
                b = a;
                n2 = n1;
                a = k;
                n1 = v;
            } else if (v > n2) {
                b = k;
                n2 = v;
            }
        }
        return new int[][] {{a, n1}, {b, n2}};
    }
}
```
