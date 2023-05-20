# [1998. 数组的最大公因数排序](https://leetcode.cn/problems/gcd-sort-of-an-array)

## 题目描述

<p>给你一个整数数组 <code>nums</code> ，你可以在 <code>nums</code> 上执行下述操作 <strong>任意次</strong> ：</p>

<ul>
	<li>如果 <code>gcd(nums[i], nums[j]) &gt; 1</code> ，交换 <code>nums[i]</code> 和 <code>nums[j]</code> 的位置。其中 <code>gcd(nums[i], nums[j])</code> 是&nbsp;<code>nums[i]</code> 和 <code>nums[j]</code> 的最大公因数。</li>
</ul>

<p>如果能使用上述交换方式将 <code>nums</code> 按 <strong>非递减顺序</strong> 排列，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [7,21,3]
<strong>输出：</strong>true
<strong>解释：</strong>可以执行下述操作完成对 [7,21,3] 的排序：
- 交换 7 和 21 因为 gcd(7,21) = 7 。nums = [<em><strong>21</strong></em>,<em><strong>7</strong></em>,3]
- 交换 21 和 3 因为 gcd(21,3) = 3 。nums = [<em><strong>3</strong></em>,7,<em><strong>21</strong></em>]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [5,2,6,2]
<strong>输出：</strong>false
<strong>解释：</strong>无法完成排序，因为 5 不能与其他元素交换。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [10,5,9,3,15]
<strong>输出：</strong>true
<strong>解释：</strong>
可以执行下述操作完成对 [10,5,9,3,15] 的排序：
- 交换 10 和 15 因为 gcd(10,15) = 5 。nums = [<em><strong>15</strong></em>,5,9,3,<em><strong>10</strong></em>]
- 交换 15 和 3 因为 gcd(15,3) = 3 。nums = [<em><strong>3</strong></em>,5,9,<em><strong>15</strong></em>,10]
- 交换 10 和 15 因为 gcd(10,15) = 5 。nums = [3,5,9,<em><strong>10</strong></em>,<em><strong>15</strong></em>]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>2 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

并查集。

并查集模板：

模板 1——朴素并查集：

模板 2——维护 size 的并查集：

模板 3——维护到祖宗节点距离的并查集：

对于本题，最大公因数大于 1 的两个数，可以进行交换，因此，只要一个集合中所有数都存在相同公因数，那么这个集合中任意数都能进行两两交换，因此可以用并查集，把同个集合中的所有数字进行合并。

> 在这道题中，可以先预处理每个数的质因数，数字与质因数归属同一个集合。

合并之后，将原数组复制一份，并进行升序排列，得到新数组 s。然后遍历原数组，若原数组对应元素与新数组对应元素不相同，并且两个元素也不在同一个集合中，说明不满足条件，直接返回 false，否则遍历结束返回 true。

### **Java**

```java
class Solution {
    private int[] p;

    public boolean gcdSort(int[] nums) {
        int n = 100010;
        p = new int[n];
        Map<Integer, List<Integer>> f = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        int mx = 0;
        for (int num : nums) {
            mx = Math.max(mx, num);
        }
        for (int i = 2; i <= mx; ++i) {
            if (f.containsKey(i)) {
                continue;
            }
            for (int j = i; j <= mx; j += i) {
                f.computeIfAbsent(j, k -> new ArrayList<>()).add(i);
            }
        }
        for (int i : nums) {
            for (int j : f.get(i)) {
                p[find(i)] = find(j);
            }
        }
        int[] s = new int[nums.length];
        System.arraycopy(nums, 0, s, 0, nums.length);
        Arrays.sort(s);
        for (int i = 0; i < nums.length; ++i) {
            if (s[i] != nums[i] && find(nums[i]) != find(s[i])) {
                return false;
            }
        }
        return true;
    }

    int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```
