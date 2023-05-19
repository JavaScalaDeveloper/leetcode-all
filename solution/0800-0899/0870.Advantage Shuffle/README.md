# [870. 优势洗牌](https://leetcode.cn/problems/advantage-shuffle)

[English Version](/solution/0800-0899/0870.Advantage%20Shuffle/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个大小相等的数组&nbsp;<code>nums1</code>&nbsp;和&nbsp;<code>nums2</code>，<code>nums1</code>&nbsp;相对于 <code>nums2</code> 的<em>优势</em>可以用满足&nbsp;<code>nums1[i] &gt; nums2[i]</code>&nbsp;的索引 <code>i</code>&nbsp;的数目来描述。</p>

<p>返回 <font color="#c7254e" face="Menlo, Monaco, Consolas, Courier New, monospace" size="1"><span style="background-color: rgb(249, 242, 244);">nums1</span></font>&nbsp;的<strong>任意</strong>排列，使其相对于 <code>nums2</code>&nbsp;的优势最大化。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [2,7,11,15], nums2 = [1,10,4,11]
<strong>输出：</strong>[2,11,7,15]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [12,24,8,32], nums2 = [13,25,32,11]
<strong>输出：</strong>[24,32,8,12]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums2.length == nums1.length</code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

**方法一：贪心 + 排序**

类似田忌赛马。将 $nums1$, $nums2$ 按照升序排列。然后遍历 $nums1$ 中的每个元素 $v$，若在 $nums2[i..j]$ 中找不到比 $v$ 小的，则将 $v$ 与当前 $nums2[i..j]$ 中的最大元素匹配。

时间复杂度 $O(nlogn)$。

### **Java**

```java
class Solution {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[][] t = new int[n][2];
        for (int i = 0; i < n; ++i) {
            t[i] = new int[] {nums2[i], i};
        }
        Arrays.sort(t, (a, b) -> a[0] - b[0]);
        Arrays.sort(nums1);
        int[] ans = new int[n];
        int i = 0, j = n - 1;
        for (int v : nums1) {
            if (v <= t[i][0]) {
                ans[t[j--][1]] = v;
            } else {
                ans[t[i++][1]] = v;
            }
        }
        return ans;
    }
}
```

### **TypeScript**
