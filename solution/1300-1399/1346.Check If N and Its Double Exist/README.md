# [1346. 检查整数及其两倍数是否存在](https://leetcode.cn/problems/check-if-n-and-its-double-exist)

## 题目描述

<p>给你一个整数数组&nbsp;<code>arr</code>，请你检查是否存在两个整数&nbsp;<code>N</code> 和 <code>M</code>，满足&nbsp;<code>N</code>&nbsp;是&nbsp;<code>M</code>&nbsp;的两倍（即，<code>N = 2 * M</code>）。</p>

<p>更正式地，检查是否存在两个下标&nbsp;<code>i</code> 和 <code>j</code> 满足：</p>

<ul>
	<li><code>i != j</code></li>
	<li><code>0 &lt;= i, j &lt; arr.length</code></li>
	<li><code>arr[i] == 2 * arr[j]</code></li>
</ul>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [10,2,5,3]
<strong>输出：</strong>true
<strong>解释：</strong>N<code> = 10</code> 是 M<code> = 5 的两倍</code>，即 <code>10 = 2 * 5 。</code>
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr = [7,1,14,11]
<strong>输出：</strong>true
<strong>解释：</strong>N<code> = 14</code> 是 M<code> = 7 的两倍</code>，即 <code>14 = 2 * 7 </code>。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>arr = [3,1,7,11]
<strong>输出：</strong>false
<strong>解释：</strong>在该情况下不存在 N 和 M 满足 N = 2 * M 。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= arr.length &lt;= 500</code></li>
	<li><code>-10^3 &lt;= arr[i] &lt;= 10^3</code></li>
</ul>

## 解法

**方法一：哈希表**

使用哈希表 `m` 记录 `arr` 每个元素 `v` 及其对应的下标 `i`。

遍历 `arr` 每个元素 `v`，若能在哈希表中找到 `v * 2`，且下标值与当前 `v` 的下标值不相等，说明找到了满足条件的元素，返回 `true`。否则遍历结束返回 `false`。

时间复杂度：O(n)。
空间复杂度：O(n)。

**方法二：排序 + 二分查找**

首先对 `arr` 排序。

然后遍历 `arr` 每个元素 `v`，二分查找 `arr` 中是否存在 `v * 2` 元素，是则返回 `true`。

注意，元素可能为 0，这种情况下，`v*2` 的值同样为 0，二分查找可能会找到同个位置的元素，与题意不符。因此，可以预先统计 `arr` 中元素 0 的个数，若超过 1 个，可提前返回 `true`。

### **Java**

```java
class Solution {
    public boolean checkIfExist(int[] arr) {
        Map<Integer, Integer> m = new HashMap<>();
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            m.put(arr[i], i);
        }
        for (int i = 0; i < n; ++i) {
            if (m.containsKey(arr[i] << 1) && m.get(arr[i] << 1) != i) {
                return true;
            }
        }
        return false;
    }
}
```

```java
class Solution {
    public boolean checkIfExist(int[] arr) {
        Set<Integer> s = new HashSet<>();
        for (int v : arr) {
            if (s.contains(v * 2) || (v % 2 == 0 && s.contains(v / 2))) {
                return true;
            }
            s.add(v);
        }
        return false;
    }
}
```

```java
class Solution {
    public boolean checkIfExist(int[] arr) {
        int cnt = 0;
        for (int v : arr) {
            if (v == 0) {
                ++cnt;
                if (cnt > 1) {
                    return true;
                }
            }
        }
        Arrays.sort(arr);
        for (int v : arr) {
            if (v != 0) {
                int idx = Arrays.binarySearch(arr, v * 2);
                if (idx >= 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
```
