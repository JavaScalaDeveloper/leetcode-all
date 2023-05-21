# [1641. 统计字典序元音字符串的数目](https://leetcode.cn/problems/count-sorted-vowel-strings)

## 题目描述

<p>给你一个整数 <code>n</code>，请返回长度为 <code>n</code> 、仅由元音 (<code>a</code>, <code>e</code>, <code>i</code>, <code>o</code>, <code>u</code>) 组成且按 <strong>字典序排列</strong> 的字符串数量。</p>

<p>字符串 <code>s</code> 按 <strong>字典序排列</strong> 需要满足：对于所有有效的 <code>i</code>，<code>s[i]</code> 在字母表中的位置总是与 <code>s[i+1]</code> 相同或在 <code>s[i+1]</code> 之前。</p>



<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>5
<strong>解释：</strong>仅由元音组成的 5 个字典序字符串为 <code>["a","e","i","o","u"]</code>
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 2
<strong>输出：</strong>15
<strong>解释：</strong>仅由元音组成的 15 个字典序字符串为
["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"]
注意，"ea" 不是符合题意的字符串，因为 'e' 在字母表中的位置比 'a' 靠后
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 33
<strong>输出：</strong>66045
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= n <= 50</code> </li>
</ul>

## 解法

**方法一：记忆化搜索**

我们设计一个函数dfs(i, j)，表示当前已经选了i个元音字母，且最后一个元音字母是j的方案数。那么答案就是dfs(0, 0)。

函数dfs(i, j)的计算过程如下：

-   如果i ≥ n，说明已经选了n个元音字母，返回1。
-   否则，枚举j后面的元音字母，即k \in [j, 4]，并将dfs(i + 1, k)的结果累加，即dfs(i, j) = \sum_{k = j}^4 dfs(i + 1, k)。

过程中，我们可以使用记忆化搜索，将已经计算过的dfs(i, j)的结果保存起来，避免重复计算。

时间复杂度O(n × C^2)，空间复杂度O(n × C)。其中n为题目给定的整数，而C是元音字母的数量，本题中C = 5。

**方法二：动态规划 + 前缀和**

定义f[i][j]表示当前已经选了i个元音字母，且最后一个元音字母是j的方案数。初始时f[1][j]=1。答案是\sum_{j = 0}^4 f[n][j]。

我们可以发现f[i][j]只与f[i - 1][j]有关，因此可以将二维数组压缩为一维数组，从而优化空间复杂度。

时间复杂度O(n × C)，空间复杂度O(C)。其中n为题目给定的整数，而C是元音字母的数量，本题中C = 5。

### **Java**

```java
class Solution {
    private Integer[][] f;
    private int n;

    public int countVowelStrings(int n) {
        this.n = n;
        f = new Integer[n][5];
        return dfs(0, 0);
    }

    private int dfs(int i, int j) {
        if (i >= n) {
            return 1;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        int ans = 0;
        for (int k = j; k < 5; ++k) {
            ans += dfs(i + 1, k);
        }
        return f[i][j] = ans;
    }
}
```

```java
class Solution {
    public int countVowelStrings(int n) {
        int[] f = {1, 1, 1, 1, 1};
        for (int i = 0; i < n - 1; ++i) {
            int s = 0;
            for (int j = 0; j < 5; ++j) {
                s += f[j];
                f[j] = s;
            }
        }
        return Arrays.stream(f).sum();
    }
}
```
