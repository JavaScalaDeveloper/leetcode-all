# [845. 数组中的最长山脉](https://leetcode.cn/problems/longest-mountain-in-array)

## 题目描述

<p>把符合下列属性的数组 <code>arr</code> 称为 <strong>山脉数组</strong> ：</p>

<ul>
	<li><code>arr.length &gt;= 3</code></li>
	<li>存在下标 <code>i</code>（<code>0 &lt; i &lt; arr.length - 1</code>），满足
	<ul>
		<li><code>arr[0] &lt; arr[1] &lt; ... &lt; arr[i - 1] &lt; arr[i]</code></li>
		<li><code>arr[i] &gt; arr[i + 1] &gt; ... &gt; arr[arr.length - 1]</code></li>
	</ul>
	</li>
</ul>

<p>给出一个整数数组 <code>arr</code>，返回最长山脉子数组的长度。如果不存在山脉子数组，返回 <code>0</code> 。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [2,1,4,7,3,2,5]
<strong>输出：</strong>5
<strong>解释：</strong>最长的山脉子数组是 [1,4,7,3,2]，长度为 5。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [2,2,2]
<strong>输出：</strong>0
<strong>解释：</strong>不存在山脉子数组。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= arr[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<p><strong>进阶：</strong></p>

<ul>
	<li>你可以仅用一趟扫描解决此问题吗？</li>
	<li>你可以用 <code>O(1)</code> 空间解决此问题吗？</li>
</ul>

## 解法

**方法一：预处理 + 枚举**

我们定义两个数组f和g，其中f[i]表示以arr[i]结尾的最长上升子序列的长度，而g[i]表示以arr[i]开头的最长下降子序列的长度。那么对于每个下标i，如果f[i] > 1且g[i] > 1，那么以arr[i]为山顶的山脉的长度为f[i] + g[i] - 1，我们只需要枚举所有的i，找出最大的那个值即可。

时间复杂度O(n)，空间复杂度O(n)。其中n为数组arr的长度。

**方法二：一次遍历（枚举左侧山脚）**

我们可以枚举山脉的左侧山脚，然后向右寻找山脉的右侧山脚。我们可以使用两个指针l和r，其中l表示左侧山脚的下标，r表示右侧山脚的下标，初始时l=0,r=0，然后我们向右移动r，找到山顶的位置，此时判断r是否满足r + 1 < n并且arr[r] > arr[r + 1]，如果满足，我们向右继续移动r，直到找到右侧山脚的位置，此时山脉的长度为r - l + 1，我们更新答案，然后将l的值更新为r，继续寻找下一个山脉。

时间复杂度O(n)，空间复杂度O(1)。其中n为数组arr的长度。

### **Java**

```java
class Solution {
    public int longestMountain(int[] arr) {
        int n = arr.length;
        int[] f = new int[n];
        int[] g = new int[n];
        Arrays.fill(f, 1);
        Arrays.fill(g, 1);
        for (int i = 1; i < n; ++i) {
            if (arr[i] > arr[i - 1]) {
                f[i] = f[i - 1] + 1;
            }
        }
        int ans = 0;
        for (int i = n - 2; i >= 0; --i) {
            if (arr[i] > arr[i + 1]) {
                g[i] = g[i + 1] + 1;
                if (f[i] > 1) {
                    ans = Math.max(ans, f[i] + g[i] - 1);
                }
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int longestMountain(int[] arr) {
        int n = arr.length;
        int ans = 0;
        for (int l = 0, r = 0; l + 2 < n; l = r) {
            r = l + 1;
            if (arr[l] < arr[r]) {
                while (r + 1 < n && arr[r] < arr[r + 1]) {
                    ++r;
                }
                if (r + 1 < n && arr[r] > arr[r + 1]) {
                    while (r + 1 < n && arr[r] > arr[r + 1]) {
                        ++r;
                    }
                    ans = Math.max(ans, r - l + 1);
                } else {
                    ++r;
                }
            }
        }
        return ans;
    }
}
```
