# [137. 只出现一次的数字 II](https://leetcode.cn/problems/single-number-ii)

## 题目描述

<p>给你一个整数数组&nbsp;<code>nums</code> ，除某个元素仅出现 <strong>一次</strong> 外，其余每个元素都恰出现 <strong>三次 。</strong>请你找出并返回那个只出现了一次的元素。</p>

<p>你必须设计并实现线性时间复杂度的算法且不使用额外空间来解决此问题。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,2,3,2]
<strong>输出：</strong>3
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,1,0,1,0,1,99]
<strong>输出：</strong>99
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>nums</code> 中，除某个元素仅出现 <strong>一次</strong> 外，其余每个元素都恰出现 <strong>三次</strong></li>
</ul>

## 解法

**方法一：位运算**

我们可以枚举每个二进制位i，对于每个二进制位，我们统计所有数字在该二进制位上的和，如果该二进制位上的和能被3整除，那么只出现一次的数字在该二进制位上为0，否则为1。

时间复杂度O(n \times \log M)，空间复杂度O(1)。其中n和M分别是数组的长度和数组中元素的范围。

**方法二：数字电路**

我们考虑一种更高效的方法，该方法使用数字电路来模拟上述的位运算。

一个整数的每个二进制位是0或1，只能表示2种状态。但我们需要表示当前遍历过的所有整数的第i位之和模3的结果，因此，我们可以使用a和b两个整数来表示。那么会有以下三种情况：

1. 整数a的第i位为0且整数b的第i位为0，表示模3结果是0；
1. 整数a的第i位为0且整数b的第i位为1，表示模3结果是1；
1. 整数a的第i位为1且整数b的第i位为0，表示模3结果是2。

我们用整数c表示当前要读入的数，那么有以下真值表：

|a_i|b_i|c_i| 新的a_i| 新的b_i|
| ----- | ----- | ----- | ---------- | ---------- |
| 0     | 0     | 0     | 0          | 0          |
| 0     | 0     | 1     | 0          | 1          |
| 0     | 1     | 0     | 0          | 1          |
| 0     | 1     | 1     | 1          | 0          |
| 1     | 0     | 0     | 1          | 0          |
| 1     | 0     | 1     | 0          | 0          |

基于以上真值表，我们可以写出逻辑表达式：


a_i = a_i' b_i c_i + a_i b_i' c_i'


以及：


b_i = a_i' b_i' c_i + a_i' b_i c_i' = a_i' (b_i \oplus c_i)


最后结果是b，因为b的二进制位上为1时表示这个数字出现了1次。

时间复杂度O(n)，空间复杂度O(1)。其中n是数组的长度。

### **Java**

```java
class Solution {
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int cnt = 0;
            for (int num : nums) {
                cnt += num >> i & 1;
            }
            cnt %= 3;
            ans |= cnt << i;
        }
        return ans;
    }
}
```

```java
class Solution {
    public int singleNumber(int[] nums) {
        int a = 0, b = 0;
        for (int c : nums) {
            int aa = (~a & b & c) | (a & ~b & ~c);
            int bb = ~a & (b ^ c);
            a = aa;
            b = bb;
        }
        return b;
    }
}
```

需要注意 Golang 中的 `int` 在 64 位平台上相当于 `int64`

**
