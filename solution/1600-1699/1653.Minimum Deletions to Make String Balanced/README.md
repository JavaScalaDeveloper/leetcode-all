# [1653. 使字符串平衡的最少删除次数](https://leetcode.cn/problems/minimum-deletions-to-make-string-balanced)

## 题目描述

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;，它仅包含字符&nbsp;<code>'a'</code> 和&nbsp;<code>'b'</code>​​​​ 。</p>

<p>你可以删除&nbsp;<code>s</code>&nbsp;中任意数目的字符，使得&nbsp;<code>s</code> <strong>平衡</strong>&nbsp;。当不存在下标对&nbsp;<code>(i,j)</code>&nbsp;满足&nbsp;<code>i &lt; j</code> ，且&nbsp;<code>s[i] = 'b'</code> 的同时&nbsp;<code>s[j]= 'a'</code> ，此时认为 <code>s</code> 是 <strong>平衡 </strong>的。</p>

<p>请你返回使 <code>s</code>&nbsp;<strong>平衡</strong>&nbsp;的 <strong>最少</strong>&nbsp;删除次数。</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>s = "aababbab"
<b>输出：</b>2
<b>解释：</b>你可以选择以下任意一种方案：
下标从 0 开始，删除第 2 和第 6 个字符（"aa<strong>b</strong>abb<strong>a</strong>b" -&gt; "aaabbb"），
下标从 0 开始，删除第 3 和第 6 个字符（"aab<strong>a</strong>bb<strong>a</strong>b" -&gt; "aabbbb"）。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>s = "bbaaaaabb"
<b>输出：</b>2
<b>解释：</b>唯一的最优解是删除最前面两个字符。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code>&nbsp;要么是&nbsp;<code>'a'</code> 要么是&nbsp;<code>'b'</code>​<strong>&nbsp;</strong>。​</li>
</ul>

## 解法

**方法一：动态规划**

我们定义f[i]表示前i个字符中，删除最少的字符数，使得字符串平衡。初始时f[0]=0。答案为f[n]。

我们遍历字符串s，维护变量b，表示当前遍历到的位置之前的字符中，字符b的个数。

-   如果当前字符为 `'b'`，此时不影响前i个字符的平衡性，因此f[i]=f[i-1]，然后我们更新b \leftarrow b+1。
-   如果当前字符为 `'a'`，此时我们可以选择删除当前字符，那么有f[i]=f[i-1]+1；也可以选择删除之前的字符b，那么有f[i]=b。因此我们取两者的最小值，即f[i]=min(f[i-1]+1,b)。

综上，我们可以得到状态转移方程：


f[i]=\begin{cases}
f[i-1], & s[i-1]='b'\\
min(f[i-1]+1,b), & s[i-1]='a'
\end{cases}


最终答案为f[n]。

我们注意到，状态转移方程中只与前一个状态以及变量b有关，因此我们可以仅用一个答案变量ans维护当前的f[i]，并不需要开辟数组f。

时间复杂度O(n)，空间复杂度O(1)。其中n为字符串s的长度。

**方法二：枚举 + 前缀和**

我们可以枚举字符串s中的每一个位置i，将字符串s分成两部分，分别为s[0,..,i-1]和s[i+1,..n-1]，要使得字符串平衡，我们在当前位置i需要删除的字符数为s[0,..,i-1]中字符b的个数加上s[i+1,..n-1]中字符a的个数。

因此，我们维护两个变量lb和ra分别表示s[0,..,i-1]中字符b的个数以及s[i+1,..n-1]中字符a的个数，那么我们需要删除的字符数为lb+ra。枚举过程中，更新变量lb和ra。

时间复杂度O(n)，空间复杂度O(1)。其中n为字符串s的长度。

### **Java**

```java
class Solution {
    public int minimumDeletions(String s) {
        int n = s.length();
        int[] f = new int[n + 1];
        int b = 0;
        for (int i = 1; i <= n; ++i) {
            if (s.charAt(i - 1) == 'b') {
                f[i] = f[i - 1];
                ++b;
            } else {
                f[i] = Math.min(f[i - 1] + 1, b);
            }
        }
        return f[n];
    }
}
```

```java
class Solution {
    public int minimumDeletions(String s) {
        int n = s.length();
        int ans = 0, b = 0;
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == 'b') {
                ++b;
            } else {
                ans = Math.min(ans + 1, b);
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int minimumDeletions(String s) {
        int lb = 0, ra = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == 'a') {
                ++ra;
            }
        }
        int ans = n;
        for (int i = 0; i < n; ++i) {
            ra -= (s.charAt(i) == 'a' ? 1 : 0);
            ans = Math.min(ans, lb + ra);
            lb += (s.charAt(i) == 'b' ? 1 : 0);
        }
        return ans;
    }
}
```
