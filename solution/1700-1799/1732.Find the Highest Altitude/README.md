# [1732. 找到最高海拔](https://leetcode.cn/problems/find-the-highest-altitude)

## 题目描述

<p>有一个自行车手打算进行一场公路骑行，这条路线总共由 <code>n + 1</code> 个不同海拔的点组成。自行车手从海拔为 <code>0</code> 的点 <code>0</code> 开始骑行。</p>

<p>给你一个长度为 <code>n</code> 的整数数组 <code>gain</code> ，其中 <code>gain[i]</code> 是点 <code>i</code> 和点 <code>i + 1</code> 的 <strong>净海拔高度差</strong>（<code>0 <= i < n</code>）。请你返回 <strong>最高点的海拔</strong> 。</p>



<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>gain = [-5,1,5,0,-7]
<b>输出：</b>1
<b>解释：</b>海拔高度依次为 [0,-5,-4,1,1,-6] 。最高海拔为 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>gain = [-4,-3,-2,-1,4,3,2]
<b>输出：</b>0
<b>解释：</b>海拔高度依次为 [0,-4,-7,-9,-10,-6,-3,-1] 。最高海拔为 0 。
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>n == gain.length</code></li>
	<li><code>1 <= n <= 100</code></li>
	<li><code>-100 <= gain[i] <= 100</code></li>
</ul>

## 解法

**方法一：前缀和（差分数组）**

我们假设每个点的海拔为h_i，由于gain[i]表示第i个点和第i + 1个点的海拔差，因此gain[i] = h_{i + 1} - h_i。那么：


\sum_{i = 0}^{n-1} gain[i] = h_1 - h_0 + h_2 - h_1 + \cdots + h_n - h_{n - 1} = h_n - h_0 = h_n


即：


h_{i+1} = \sum_{j = 0}^{i} gain[j]


可以发现，每个点的海拔都可以通过前缀和的方式计算出来。因此，我们只需要遍历一遍数组，求出前缀和的最大值，即为最高点的海拔。

> 实际上题目中的gain数组是一个差分数组，对差分数组求前缀和即可得到原海拔数组。然后求出原海拔数组的最大值即可。

时间复杂度O(n)，空间复杂度O(1)。其中n为数组gain的长度。

### **Java**

```java
class Solution {
    public int largestAltitude(int[] gain) {
        int ans = 0, h = 0;
        for (int v : gain) {
            h += v;
            ans = Math.max(ans, h);
        }
        return ans;
    }
}
```

**
