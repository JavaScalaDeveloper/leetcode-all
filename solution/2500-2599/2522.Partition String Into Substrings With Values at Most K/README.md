# [2522. 将字符串分割成值不超过 K 的子字符串](https://leetcode.cn/problems/partition-string-into-substrings-with-values-at-most-k)

## 题目描述

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;，它每一位都是&nbsp;<code>1</code>&nbsp;到&nbsp;<code>9</code>&nbsp;之间的数字组成，同时给你一个整数&nbsp;<code>k</code>&nbsp;。</p>

<p>如果一个字符串 <code>s</code>&nbsp;的分割满足以下条件，我们称它是一个 <strong>好</strong>&nbsp;分割：</p>

<ul>
	<li><code>s</code>&nbsp;中每个数位 <strong>恰好</strong>&nbsp;属于一个子字符串。</li>
	<li>每个子字符串的值都小于等于&nbsp;<code>k</code>&nbsp;。</li>
</ul>

<p>请你返回 <code>s</code>&nbsp;所有的 <strong>好</strong>&nbsp;分割中，子字符串的&nbsp;<strong>最少</strong>&nbsp;数目。如果不存在 <code>s</code>&nbsp;的<strong>&nbsp;好</strong>&nbsp;分割，返回&nbsp;<code>-1</code>&nbsp;。</p>

<p><b>注意：</b></p>

<ul>
	<li>一个字符串的 <strong>值</strong>&nbsp;是这个字符串对应的整数。比方说，<code>"123"</code>&nbsp;的值为&nbsp;<code>123</code>&nbsp;，<code>"1"</code>&nbsp;的值是&nbsp;<code>1</code>&nbsp;。</li>
	<li><strong>子字符串</strong>&nbsp;是字符串中一段连续的字符序列。</li>
</ul>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>s = "165462", k = 60
<b>输出：</b>4
<b>解释：</b>我们将字符串分割成子字符串 "16" ，"54" ，"6" 和 "2" 。每个子字符串的值都小于等于 k = 60 。
不存在小于 4 个子字符串的好分割。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>s = "238182", k = 5
<b>输出：</b>-1
<strong>解释：</strong>这个字符串不存在好分割。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code>&nbsp;是&nbsp;<code>'1'</code>&nbsp;到&nbsp;<code>'9'</code>&nbsp;之间的数字。</li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

**方法一：记忆化搜索**

我们设计一个函数dfs(i)表示从字符串s的下标i开始的最少分割数，那么答案就是dfs(0)。

函数dfs(i)的计算过程如下：

-   如果i ≥ n，说明已经到达字符串末尾，返回0。
-   否则，我们枚举i开始的所有子字符串，如果子字符串的值小于等于k，那么我们可以将子字符串作为一个分割，那么我们可以得到dfs(j + 1)，其中j是子字符串的末尾下标，然后我们取所有可能的分割中的最小值，再加上1，即为dfs(i)的值。

最后，如果dfs(0) = \infty，说明不存在好分割，返回-1，否则返回dfs(0)。

为了避免重复计算，我们可以使用记忆化搜索。

时间复杂度O(n)，空间复杂度O(n)。其中n是字符串s的长度。

### **Java**

```java
class Solution {
    private Integer[] f;
    private int n;
    private String s;
    private int k;
    private int inf = 1 << 30;

    public int minimumPartition(String s, int k) {
        n = s.length();
        f = new Integer[n];
        this.s = s;
        this.k = k;
        int ans = dfs(0);
        return ans < inf ? ans : -1;
    }

    private int dfs(int i) {
        if (i >= n) {
            return 0;
        }
        if (f[i] != null) {
            return f[i];
        }
        int res = inf;
        long v = 0;
        for (int j = i; j < n; ++j) {
            v = v * 10 + (s.charAt(j) - '0');
            if (v > k) {
                break;
            }
            res = Math.min(res, dfs(j + 1));
        }
        return f[i] = res + 1;
    }
}
```
