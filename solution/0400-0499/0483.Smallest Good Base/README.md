# [483. 最小好进制](https://leetcode.cn/problems/smallest-good-base)

## 题目描述

<p>以字符串的形式给出 <code>n</code>&nbsp;, 以字符串的形式返回<em> <code>n</code> 的最小 <strong>好进制</strong> </em>&nbsp;。</p>

<p>如果 <code>n</code> 的 &nbsp;<code>k(k&gt;=2)</code>&nbsp;进制数的所有数位全为1，则称&nbsp;<code>k(k&gt;=2)</code>&nbsp;是 <code>n</code> 的一个&nbsp;<strong>好进制&nbsp;</strong>。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = "13"
<strong>输出：</strong>"3"
<strong>解释：</strong>13 的 3 进制是 111。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = "4681"
<strong>输出：</strong>"8"
<strong>解释：</strong>4681 的 8 进制是 11111。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = "1000000000000000000"
<strong>输出：</strong>"999999999999999999"
<strong>解释：</strong>1000000000000000000 的 999999999999999999 进制是 11。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n</code> 的取值范围是&nbsp;<code>[3, 10<sup>18</sup>]</code></li>
	<li><code>n</code> 没有前导 0</li>
</ul>

## 解法

**方法一：数学**

假设n在k进制下的所有位数均为1，且位数为m+1，那么有式子 ①：


n=k^0+k^1+k^2+...+k^m


当m=0时，上式n=1，而题目n取值范围为[3, 10^{18}]，因此m>0。

当m=1时，上式n=k^0+k^1=1+k，即k=n-1>=2。

我们来证明一般情况下的两个结论，以帮助解决本题。

**结论一：**m<log _{k} n

注意到式子 ① 是个首项为1，且公比为k的等比数列。利用等比数列求和公式，我们可以得出：


n=\frac{1-k^{m+1}}{1-k}


变形得：


k^{m+1}=k × n-n+1 < k × n


移项得：


m<log _{k} n


题目n取值范围为[3, 10^{18}]，又因为k>=2，因此m<log _{k} n<log _{2} 10^{18}<60。

**结论二：**k=\left \lfloor \sqrt[m]{n} \right \rfloor


n=k^0+k^1+k^2+...+k^m>k^m


根据二项式定理：


(a+b)^{n}=\sum_{k=0}^{n}\left(\begin{array}{l}
n \\
k
\end{array}\right) a^{n-k} b^{k}


整合，可得：


(k+1)^{m}=\left(\begin{array}{c}
m \\
0
\end{array}\right) k^{0}+\left(\begin{array}{c}
m \\
1
\end{array}\right) k^{1}+\left(\begin{array}{c}
m \\
2
\end{array}\right) k^{2}+\cdots+\left(\begin{array}{c}
m \\
m
\end{array}\right) k^{m}


当m>1时，满足：


\forall i \in[1, m-1],\left(\begin{array}{c}
m \\
i
\end{array}\right)>1


所以有：


\begin{aligned}
(k+1)^{m} &=\left(\begin{array}{c}
m \\
0
\end{array}\right) k^{0}+\left(\begin{array}{c}
m \\
1
\end{array}\right) k^{1}+\left(\begin{array}{c}
m \\
2
\end{array}\right) k^{2}+\cdots+\left(\begin{array}{c}
m \\
m
\end{array}\right) k^{m} \\
&>k^{0}+k^{1}+k^{2}+\cdots+k^{m}=n
\end{aligned}


即：


k < \sqrt[m]{n} < k+1


由于k是整数，因此k=\left \lfloor \sqrt[m]{n} \right \rfloor。

综上，依据结论一，我们知道m的取值范围为[1,log_{k}n)，且m=1时必然有解。随着m的增大，进制k不断减小。所以我们只需要从大到小检查每一个m可能的取值，利用结论二快速算出对应的k值，然后校验计算出的k值是否有效即可。如果k值有效，我们即可返回结果。

时间复杂度O(log^{2}n)。

### **Java**

```java
class Solution {
    public String smallestGoodBase(String n) {
        long num = Long.parseLong(n);
        for (int len = 63; len >= 2; --len) {
            long radix = getRadix(len, num);
            if (radix != -1) {
                return String.valueOf(radix);
            }
        }
        return String.valueOf(num - 1);
    }

    private long getRadix(int len, long num) {
        long l = 2, r = num - 1;
        while (l < r) {
            long mid = l + r >>> 1;
            if (calc(mid, len) >= num)
                r = mid;
            else
                l = mid + 1;
        }
        return calc(r, len) == num ? r : -1;
    }

    private long calc(long radix, int len) {
        long p = 1;
        long sum = 0;
        for (int i = 0; i < len; ++i) {
            if (Long.MAX_VALUE - sum < p) {
                return Long.MAX_VALUE;
            }
            sum += p;
            if (Long.MAX_VALUE / p < radix) {
                p = Long.MAX_VALUE;
            } else {
                p *= radix;
            }
        }
        return sum;
    }
}
```
