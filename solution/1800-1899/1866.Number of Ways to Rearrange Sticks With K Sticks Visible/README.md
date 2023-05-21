# [1866. 恰有 K 根木棍可以看到的排列数目](https://leetcode.cn/problems/number-of-ways-to-rearrange-sticks-with-k-sticks-visible)

## 题目描述

<p>有 <code>n</code> 根长度互不相同的木棍，长度为从 <code>1</code> 到 <code>n</code> 的整数。请你将这些木棍排成一排，并满足从左侧 <strong>可以看到</strong> <strong>恰好</strong> <code>k</code> 根木棍。从左侧 <strong>可以看到</strong> 木棍的前提是这个木棍的 <strong>左侧</strong> 不存在比它 <strong>更长的</strong> 木棍。</p>

<ul>
	<li>例如，如果木棍排列为 <code>[<em><strong>1</strong></em>,<em><strong>3</strong></em>,2,<em><strong>5</strong></em>,4]</code> ，那么从左侧可以看到的就是长度分别为 <code>1</code>、<code>3</code> 、<code>5</code> 的木棍。</li>
</ul>

<p>给你 <code>n</code> 和 <code>k</code> ，返回符合题目要求的排列 <strong>数目</strong> 。由于答案可能很大，请返回对 <code>10<sup>9</sup> + 7</code> <strong>取余 </strong>的结果。</p>



<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 3, k = 2
<strong>输出：</strong>3
<strong>解释：</strong>[<strong><em>1</em></strong>,<strong><em>3</em></strong>,2], [<em><strong>2</strong></em>,<em><strong>3</strong></em>,1] 和 [<em><strong>2</strong></em>,1,<em><strong>3</strong></em>] 是仅有的能满足恰好 2 根木棍可以看到的排列。
可以看到的木棍已经用粗体+斜体标识。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 5, k = 5
<strong>输出：</strong>1
<strong>解释：</strong>[<em><strong>1</strong></em>,<em><strong>2</strong></em>,<em><strong>3</strong></em>,<em><strong>4</strong></em>,<em><strong>5</strong></em>] 是唯一一种能满足全部 5 根木棍可以看到的排列。
可以看到的木棍已经用粗体+斜体标识。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = 20, k = 11
<strong>输出：</strong>647427950
<strong>解释：</strong>总共有 647427950 (mod 10<sup>9 </sup>+ 7) 种能满足恰好有 11 根木棍可以看到的排列。
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>

## 解法

**方法一：动态规划**

我们定义f[i][j]表示长度为i的排列中，恰有j根木棍可以看到的排列数目。初始时f[0][0]=1，其余f[i][j]=0。答案为f[n][k]。

考虑最后一根木棍是否可以看到，如果可以看到，那么它一定是最长的，那么它的前面有i - 1根木棍，恰有j - 1根木棍可以看到，即f[i - 1][j - 1]；如果最后一根木棍不可以看到，那么它可以是除了最长的木棍之外的任意一根，那么它的前面有i - 1根木棍，恰有j根木棍可以看到，即f[i - 1][j] × (i - 1)。

因此，状态转移方程为：


f[i][j] = f[i - 1][j - 1] + f[i - 1][j] × (i - 1)


最终答案为f[n][k]。

我们注意到f[i][j]只跟f[i - 1][j - 1]和f[i - 1][j]有关，因此可以使用一维数组优化空间复杂度。

时间复杂度O(n × k)，空间复杂度O(k)。其中n和k分别是题目中给定的两个整数。

### **Java**

```java
class Solution {
    public int rearrangeSticks(int n, int k) {
        final int mod = (int) 1e9 + 7;
        int[][] f = new int[n + 1][k + 1];
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= k; ++j) {
                f[i][j] = (int) ((f[i - 1][j - 1] + f[i - 1][j] * (long) (i - 1)) % mod);
            }
        }
        return f[n][k];
    }
}
```

```java
class Solution {
    public int rearrangeSticks(int n, int k) {
        final int mod = (int) 1e9 + 7;
        int[] f = new int[k + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = k; j > 0; --j) {
                f[j] = (int) ((f[j] * (i - 1L) + f[j - 1]) % mod);
            }
            f[0] = 0;
        }
        return f[k];
    }
}
```
