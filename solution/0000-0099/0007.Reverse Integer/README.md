# [7. 整数反转](https://leetcode.cn/problems/reverse-integer)

## 题目描述

<p>给你一个 32 位的有符号整数 <code>x</code> ，返回将 <code>x</code> 中的数字部分反转后的结果。</p>

<p>如果反转后整数超过 32 位的有符号整数的范围 <code>[−2<sup>31</sup>,  2<sup>31 </sup>− 1]</code> ，就返回 0。</p>
<strong>假设环境不允许存储 64 位整数（有符号或无符号）。</strong>



<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>x = 123
<strong>输出：</strong>321
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>x = -123
<strong>输出：</strong>-321
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>x = 120
<strong>输出：</strong>21
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>x = 0
<strong>输出：</strong>0
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>-2<sup>31</sup> <= x <= 2<sup>31</sup> - 1</code></li>
</ul>

## 解法

**方法一：数学**

我们不妨记mi和mx分别为-2^{31}和2^{31} - 1，则x的反转结果ans需要满足mi \le ans \le mx。

我们可以通过不断地对x取余来获取x的最后一位数字y，并将y添加到ans的末尾。在添加y之前，我们需要判断ans是否溢出。即判断ans × 10 + y是否在[mi, mx]的范围内。

若x > 0，那么需要满足ans × 10 + y ≤ mx，即ans × 10 + y ≤ \left \lfloor \frac{mx}{10} \right \rfloor × 10 + 7。整理得(ans - \left \lfloor \frac{mx}{10} \right \rfloor) × 10 ≤ 7 - y。

下面我们讨论上述不等式成立的条件：

-   当ans < \left \lfloor \frac{mx}{10} \right \rfloor时，不等式显然成立；
-   当ans = \left \lfloor \frac{mx}{10} \right \rfloor时，不等式成立的充要条件是y ≤ 7。如果ans = \left \lfloor \frac{mx}{10} \right \rfloor并且还能继续添加数字，说明此时数字是最高位，即此时y一定不超过2，因此，不等式一定成立；
-   当ans > \left \lfloor \frac{mx}{10} \right \rfloor时，不等式显然不成立。

综上，当x > 0时，不等式成立的充要条件是ans ≤ \left \lfloor \frac{mx}{10} \right \rfloor。

同理，当x < 0时，不等式成立的充要条件是ans ≥ \left \lfloor \frac{mi}{10} \right \rfloor。

因此，我们可以通过判断ans是否在[\left \lfloor \frac{mi}{10} \right \rfloor, \left \lfloor \frac{mx}{10} \right \rfloor]的范围内来判断ans是否溢出。若溢出，则返回0。否则，将y添加到ans的末尾，然后将x的最后一位数字去除，即x ← \left \lfloor \frac{x}{10} \right \rfloor。

时间复杂度O(log |x|)，其中|x|为x的绝对值。空间复杂度O(1)。

### **Java**

```java
class Solution {
    public int reverse(int x) {
        int ans = 0;
        for (; x != 0; x /= 10) {
            if (ans < Integer.MIN_VALUE / 10 || ans > Integer.MAX_VALUE / 10) {
                return 0;
            }
            ans = ans * 10 + x % 10;
        }
        return ans;
    }
}
```

**
