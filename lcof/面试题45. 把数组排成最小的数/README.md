# [面试题 45. 把数组排成最小的数](https://leetcode.cn/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/)

## 题目描述



<p>输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> <code>[10,2]</code>
<strong>输出:</strong> &quot;<code>102&quot;</code></pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre><strong>输入:</strong> <code>[3,30,34,5,9]</code>
<strong>输出:</strong> &quot;<code>3033459&quot;</code></pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>0 &lt; nums.length &lt;= 100</code></li>
</ul>

<p><strong>说明: </strong></p>

<ul>
	<li>输出结果可能非常大，所以你需要返回一个字符串而不是整数</li>
	<li>拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0</li>
</ul>

## 解法

**方法一：自定义排序**

将数组中的数字转换为字符串，然后按照字符串拼接的大小进行排序。具体地，比较两个字符串 $a$ 和 $b$，如果 $a + b \lt b + a$，则 $a$ 小于 $b$，否则 $a$ 大于 $b$。

时间复杂度 $O(n \times \log n + n \times m)$，空间复杂度 $O(n \times m)$。其中 $n $ 和 $m$ 分别为数组的长度和字符串的平均长度。

### **Java**

```java
class Solution {
    public String minNumber(int[] nums) {
        return Arrays.stream(nums)
            .mapToObj(String::valueOf)
            .sorted((a, b) -> (a + b).compareTo(b + a))
            .reduce((a, b) -> a + b)
            .orElse("");
    }
}
```
