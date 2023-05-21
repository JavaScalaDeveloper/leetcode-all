# [2519. 统计 K-Big 索引的数量](https://leetcode.cn/problems/count-the-number-of-k-big-indices)

## 题目描述

<p>给定一个 <strong>下标从0开始</strong> 的整数数组 <code>nums</code> 和一个正整数 <code>k</code> 。</p>

<p>如果满足以下条件，我们称下标 <code>i</code> 为 <strong>k-big</strong> ：</p>

<ul>
	<li>存在至少 <code>k</code> 个不同的索引 <code>idx1</code> ，满足 <code>idx1 &lt; i</code> 且 <code>nums[idx1] &lt; nums[i]</code> 。</li>
	<li>存在至少 <code>k</code> 个不同的索引 <code>idx2</code> ，满足 <code>idx2 &gt; i</code> 且 <code>nums[idx2] &lt; nums[i]</code> 。</li>
</ul>

<p>返回 k-big 索引的数量。</p>

<p><strong class="example">示例 1 ：</strong></p>

<pre>
<b>输入：</b>nums = [2,3,6,5,2,3], k = 2
<b>输出：</b>2
<b>解释：</b>在nums中只有两个 2-big 的索引:
- i = 2 --&gt; 有两个有效的 idx1: 0 和 1。有三个有效的 idx2: 2、3 和 4。
- i = 3 --&gt; 有两个有效的 idx1: 0 和 1。有两个有效的 idx2: 3 和 4。</pre>

<p><strong class="example">示例 2 ：</strong></p>

<pre>
<b>输入：</b>nums = [1,1,1], k = 3
<b>输出：</b>0
<b>解释：</b>在 nums 中没有 3-big 的索引
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], k &lt;= nums.length</code></li>
</ul>

## 解法

**方法一：树状数组**

维护两个树状数组，一个记录当前位置左边小于当前位置的数的个数，另一个记录当前位置右边小于当前位置的数的个数。

遍历数组，对于当前位置，如果左边小于当前位置的数的个数大于等于k，且右边小于当前位置的数的个数大于等于k，则当前位置是k-big，答案加一。

时间复杂度O(n\log n)，空间复杂度O(n)。其中n为数组长度。

### **Java**

```java
class BinaryIndexedTree {
    private int n;
    private int[] c;

    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new int[n + 1];
    }

    public void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
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
    public int kBigIndices(int[] nums, int k) {
        int n = nums.length;
        BinaryIndexedTree tree1 = new BinaryIndexedTree(n);
        BinaryIndexedTree tree2 = new BinaryIndexedTree(n);
        for (int v : nums) {
            tree2.update(v, 1);
        }
        int ans = 0;
        for (int v : nums) {
            tree2.update(v, -1);
            if (tree1.query(v - 1) >= k && tree2.query(v - 1) >= k) {
                ++ans;
            }
            tree1.update(v, 1);
        }
        return ans;
    }
}
```
