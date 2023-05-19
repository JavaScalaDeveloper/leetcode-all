# [954. 二倍数对数组](https://leetcode.cn/problems/array-of-doubled-pairs)

[English Version](/solution/0900-0999/0954.Array%20of%20Doubled%20Pairs/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个长度为偶数的整数数组 <code>arr</code>，只有对 <code>arr</code> 进行重组后可以满足 “对于每个 <code>0 &lt;=&nbsp;i &lt; len(arr) / 2</code>，都有 <code>arr[2 * i + 1] = 2 * arr[2 * i]</code>”&nbsp;时，返回 <code>true</code>；否则，返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [3,1,3,6]
<strong>输出：</strong>false
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [2,1,2,6]
<strong>输出：</strong>false
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>arr = [4,-2,2,-4]
<strong>输出：</strong>true
<strong>解释：</strong>可以用 [-2,-4] 和 [2,4] 这两组组成 [-2,-4,2,4] 或是 [2,4,-2,-4]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= arr.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>arr.length</code> 是偶数</li>
	<li><code>-10<sup>5</sup> &lt;= arr[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

**方法一：哈希表 + 排序**

### **Java**

```java
class Solution {
    public boolean canReorderDoubled(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int v : arr) {
            freq.put(v, freq.getOrDefault(v, 0) + 1);
        }
        if ((freq.getOrDefault(0, 0) & 1) != 0) {
            return false;
        }
        List<Integer> keys = new ArrayList<>(freq.keySet());
        keys.sort(Comparator.comparingInt(Math::abs));
        for (int k : keys) {
            if (freq.getOrDefault(k << 1, 0) < freq.get(k)) {
                return false;
            }
            freq.put(k << 1, freq.getOrDefault(k << 1, 0) - freq.get(k));
        }
        return true;
    }
}
```
