# [2418. 按身高排序](https://leetcode.cn/problems/sort-the-people)

[English Version](/solution/2400-2499/2418.Sort%20the%20People/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串数组 <code>names</code> ，和一个由 <strong>互不相同</strong> 的正整数组成的数组 <code>heights</code> 。两个数组的长度均为 <code>n</code> 。</p>

<p>对于每个下标 <code>i</code>，<code>names[i]</code> 和 <code>heights[i]</code> 表示第 <code>i</code> 个人的名字和身高。</p>

<p>请按身高 <strong>降序</strong> 顺序返回对应的名字数组 <code>names</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>names = ["Mary","John","Emma"], heights = [180,165,170]
<strong>输出：</strong>["Mary","Emma","John"]
<strong>解释：</strong>Mary 最高，接着是 Emma 和 John 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>names = ["Alice","Bob","Bob"], heights = [155,185,150]
<strong>输出：</strong>["Bob","Alice","Bob"]
<strong>解释：</strong>第一个 Bob 最高，然后是 Alice 和第二个 Bob 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == names.length == heights.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>3</sup></code></li>
	<li><code>1 &lt;= names[i].length &lt;= 20</code></li>
	<li><code>1 &lt;= heights[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>names[i]</code> 由大小写英文字母组成</li>
	<li><code>heights</code> 中的所有值互不相同</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序**

根据题目描述，我们可以创建一个长度为 $n$ 的下标数组 $idx$，其中 $idx[i]=i$。然后我们对 $idx$ 中的每个下标按照 $heights$ 中对应的身高降序排序，最后遍历排序后的 $idx$ 中的每个下标 $i$，将 $names[i]$ 加入答案数组即可。

我们也可以创建一个长度为 $n$ 的数组 $arr$，数组中每个元素是一个二元组 $(heights[i], i)$，然后我们对 $arr$ 按照身高降序排序。最后遍历排序后的 $arr$ 中的每个元素 $(heights[i], i)$，将 $names[i]$ 加入答案数组即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $names$ 和 $heights$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->





### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String[] sortPeople(String[] names, int[] heights) {
        int n = names.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; ++i) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i, j) -> heights[j] - heights[i]);
        String[] ans = new String[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = names[idx[i]];
        }
        return ans;
    }
}
```

```java
class Solution {
    public String[] sortPeople(String[] names, int[] heights) {
        int n = names.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; ++i) {
            arr[i] = new int[] {heights[i], i};
        }
        Arrays.sort(arr, (a, b) -> b[0] - a[0]);
        String[] ans = new String[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = names[arr[i][1]];
        }
        return ans;
    }
}
```













### **TypeScript**









### **...**

```

```


