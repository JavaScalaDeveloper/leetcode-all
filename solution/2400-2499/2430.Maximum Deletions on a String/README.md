# [2430. 对字母串可执行的最大删除数](https://leetcode.cn/problems/maximum-deletions-on-a-string)

## 题目描述

<p>给你一个仅由小写英文字母组成的字符串 <code>s</code> 。在一步操作中，你可以：</p>

<ul>
	<li>删除 <strong>整个字符串</strong> <code>s</code> ，或者</li>
	<li>对于满足&nbsp;<code>1 &lt;= i &lt;= s.length / 2</code> 的任意 <code>i</code> ，如果 <code>s</code> 中的 <strong>前</strong> <code>i</code> 个字母和接下来的 <code>i</code> 个字母 <strong>相等</strong> ，删除 <strong>前</strong> <code>i</code> 个字母。</li>
</ul>

<p>例如，如果 <code>s = "ababc"</code> ，那么在一步操作中，你可以删除 <code>s</code> 的前两个字母得到 <code>"abc"</code> ，因为 <code>s</code> 的前两个字母和接下来的两个字母都等于 <code>"ab"</code> 。</p>

<p>返回删除 <code>s</code> 所需的最大操作数。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "abcabcdabc"
<strong>输出：</strong>2
<strong>解释：</strong>
- 删除前 3 个字母（"abc"），因为它们和接下来 3 个字母相等。现在，s = "abcdabc"。
- 删除全部字母。
一共用了 2 步操作，所以返回 2 。可以证明 2 是所需的最大操作数。
注意，在第二步操作中无法再次删除 "abc" ，因为 "abc" 的下一次出现并不是位于接下来的 3 个字母。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "aaabaab"
<strong>输出：</strong>4
<strong>解释：</strong>
- 删除第一个字母（"a"），因为它和接下来的字母相等。现在，s = "aabaab"。
- 删除前 3 个字母（"aab"），因为它们和接下来 3 个字母相等。现在，s = "aab"。 
- 删除第一个字母（"a"），因为它和接下来的字母相等。现在，s = "ab"。
- 删除全部字母。
一共用了 4 步操作，所以返回 4 。可以证明 4 是所需的最大操作数。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "aaaaa"
<strong>输出：</strong>5
<strong>解释：</strong>在每一步操作中，都可以仅删除 s 的第一个字母。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 4000</code></li>
	<li><code>s</code> 仅由小写英文字母组成</li>
</ul>

## 解法

**方法一：记忆化搜索**

我们设计一个函数dfs(i)，表示删除s[i..]所有字符所需的最大操作数，那么答案就是dfs(0)。

函数dfs(i)的计算过程如下：

-   如果i ≥ n，那么dfs(i) = 0，直接返回。
-   否则，我们枚举字符串的长度j，其中1 ≤ j ≤ (n-1)/2，如果s[i..i+j] = s[i+j..i+j+j]，那么我们可以删除s[i..i+j]，此时dfs(i)=max(dfs(i), dfs(i+j)+1)。我们需要枚举所有的j，求dfs(i)的最大值即可。

这里我们需要快速判断s[i..i+j]与s[i+j..i+j+j]是否相等，我们可以预处理出字符串s的所有最长公共前缀，即g[i][j]表示s[i..]与s[j..]的最长公共前缀的长度。这样我们就可以快速判断s[i..i+j]与s[i+j..i+j+j]是否相等，即g[i][i+j] ≥ j。

为了避免重复计算，我们可以使用记忆化搜索，用一个数组f记录函数dfs(i)的值。

时间复杂度O(n^2)，空间复杂度O(n^2)。其中n是字符串s的长度。

**方法二：动态规划**

我们可以将方法一的记忆化搜索改为动态规划，定义f[i]表示删除s[i..]所有字符所需的最大操作数，初始时f[i]=1，答案为f[0]。

我们可以从后往前枚举i，对于每个i，我们枚举字符串的长度j，其中1 ≤ j ≤ (n-1)/2，如果s[i..i+j] = s[i+j..i+j+j]，那么我们可以删除s[i..i+j]，此时f[i]=max(f[i], f[i+j]+1)。我们需要枚举所有的j，求f[i]的最大值即可。

时间复杂度O(n^2)，空间复杂度O(n)。其中n是字符串s的长度。

### **Java**

```java
class Solution {
    private int n;
    private Integer[] f;
    private int[][] g;

    public int deleteString(String s) {
        n = s.length();
        f = new Integer[n];
        g = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                if (s.charAt(i) == s.charAt(j)) {
                    g[i][j] = g[i + 1][j + 1] + 1;
                }
            }
        }
        return dfs(0);
    }

    private int dfs(int i) {
        if (i == n) {
            return 0;
        }
        if (f[i] != null) {
            return f[i];
        }
        f[i] = 1;
        for (int j = 1; j <= (n - i) / 2; ++j) {
            if (g[i][i + j] >= j) {
                f[i] = Math.max(f[i], 1 + dfs(i + j));
            }
        }
        return f[i];
    }
}
```

```java
class Solution {
    public int deleteString(String s) {
        int n = s.length();
        int[][] g = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                if (s.charAt(i) == s.charAt(j)) {
                    g[i][j] = g[i + 1][j + 1] + 1;
                }
            }
        }
        int[] f = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            f[i] = 1;
            for (int j = 1; j <= (n - i) / 2; ++j) {
                if (g[i][i + j] >= j) {
                    f[i] = Math.max(f[i], f[i + j] + 1);
                }
            }
        }
        return f[0];
    }
}
```
