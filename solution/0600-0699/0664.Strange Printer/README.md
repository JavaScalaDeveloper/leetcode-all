# [664. 奇怪的打印机](https://leetcode.cn/problems/strange-printer)

## 题目描述

<p>有台奇怪的打印机有以下两个特殊要求：</p>

<ul>
	<li>打印机每次只能打印由 <strong>同一个字符</strong> 组成的序列。</li>
	<li>每次可以在从起始到结束的任意位置打印新字符，并且会覆盖掉原来已有的字符。</li>
</ul>

<p>给你一个字符串 <code>s</code> ，你的任务是计算这个打印机打印它需要的最少打印次数。</p>
&nbsp;

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "aaabbb"
<strong>输出：</strong>2
<strong>解释：</strong>首先打印 "aaa" 然后打印 "bbb"。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "aba"
<strong>输出：</strong>2
<strong>解释：</strong>首先打印 "aaa" 然后在第二个位置打印 "b" 覆盖掉原来的字符 'a'。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> 由小写英文字母组成</li>
</ul>

## 解法

**方法一：动态规划**

我们定义f[i][j]表示打印完成区间s[i..j]的最少操作数，初始时f[i][j]=\infty，答案为f[0][n-1]，其中n是字符串s的长度。

考虑f[i][j]，如果s[i] = s[j]，那么我们在打印s[i]时可以顺便打印s[j]，这样我们即可忽略字符s[j]，在区间s[i+1..j-1]内继续进行打印。如果s[i] \neq s[j]，那么我们需要分别完成该区间的打印，即使用s[i..k]和s[k+1..j]，其中k \in [i,j)。于是我们可以列出如下的转移方程：


f[i][j]=
\begin{cases}
1, & \text{if } i=j \\
f[i][j-1], & \text{if } s[i]=s[j] \\
min_{i ≤ k < j} \{f[i][k]+f[k+1][j]\}, & \text{otherwise}
\end{cases}


在枚举时，我们可以从大到小枚举i，从小到大枚举j，这样可以保证在计算f[i][j]时，状态f[i][j-1]和f[i][k]以及f[k+1][j]都已经被计算过。

时间复杂度O(n^3)，空间复杂度O(n^2)。其中n是字符串s的长度。

### **Java**

```java
class Solution {
    public int strangePrinter(String s) {
        final int inf = 1 << 30;
        int n = s.length();
        int[][] f = new int[n][n];
        for (var g : f) {
            Arrays.fill(g, inf);
        }
        for (int i = n - 1; i >= 0; --i) {
            f[i][i] = 1;
            for (int j = i + 1; j < n; ++j) {
                if (s.charAt(i) == s.charAt(j)) {
                    f[i][j] = f[i][j - 1];
                } else {
                    for (int k = i; k < j; ++k) {
                        f[i][j] = Math.min(f[i][j], f[i][k] + f[k + 1][j]);
                    }
                }
            }
        }
        return f[0][n - 1];
    }
}
```
