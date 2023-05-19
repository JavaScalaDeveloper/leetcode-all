# [面试题 16.01. 交换数字](https://leetcode.cn/problems/swap-numbers-lcci)

[English Version](/lcci/16.01.Swap%20Numbers/README_EN.md)

## 题目描述


<p>编写一个函数，不用临时变量，直接交换<code>numbers = [a, b]</code>中<code>a</code>与<code>b</code>的值。</p>
<p><strong>示例：</strong></p>
<pre><strong>输入:</strong> numbers = [1,2]
<strong>输出:</strong> [2,1]
</pre>
<p><strong>提示：</strong></p>
<ul>
<li><code>numbers.length == 2</code></li>
</ul>

## 解法

异或运算。

### **Java**

```java
class Solution {
    public int[] swapNumbers(int[] numbers) {
        numbers[0] ^= numbers[1];
        numbers[1] ^= numbers[0];
        numbers[0] ^= numbers[1];
        return numbers;
    }
}
```
