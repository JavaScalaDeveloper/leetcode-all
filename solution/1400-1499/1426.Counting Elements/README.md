# [1426. 数元素](https://leetcode.cn/problems/counting-elements)

## 题目描述

<p>给你一个整数数组&nbsp;<code>arr</code>， 对于元素 <code>x</code> ，只有当 <code>x + 1</code> 也在数组&nbsp;<code>arr</code> 里时，才能记为 <code>1</code> 个数。</p>

<p>如果数组&nbsp;<code>arr</code> 里有重复的数，每个重复的数单独计算。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,2,3]
<strong>输出：</strong>2
<strong>解释：</strong>1 和 2 被计算次数因为 2 和 3 在数组 arr 里。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,1,3,3,5,5,7,7]
<strong>输出：</strong>0
<strong>解释：</strong>所有的数都不算, 因为数组里没有 2、4、6、8。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>0 &lt;= arr[i] &lt;= 1000</code></li>
</ul>

## 解法

**方法一：暴力枚举**

枚举 `arr` 的每个元素 `x`，判断 `x+1` 是否在 `arr` 中，是则累加答案。

时间复杂度 $O(n^2)$，空间复杂度 $O(1)$。

**方法二：哈希表**

将 `arr` 所有元素放入哈希表 `s` 中。然后遍历 `arr` 的每个元素 `x`，判断 `x+1` 是否在 `s` 中，是则累加答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。

### **Java**

```java
class Solution {
    public int countElements(int[] arr) {
        int ans = 0;
        for (int x : arr) {
            for (int v : arr) {
                if (x + 1 == v) {
                    ++ans;
                    break;
                }
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int countElements(int[] arr) {
        Set<Integer> s = new HashSet<>();
        for (int num : arr) {
            s.add(num);
        }
        int res = 0;
        for (int num : arr) {
            if (s.contains(num + 1)) {
                ++res;
            }
        }
        return res;
    }
}
```
