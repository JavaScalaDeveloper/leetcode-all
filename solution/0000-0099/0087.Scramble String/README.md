# [87. 扰乱字符串](https://leetcode.cn/problems/scramble-string)

## 题目描述

使用下面描述的算法可以扰乱字符串 <code>s</code> 得到字符串 <code>t</code> ：

<ol>
	<li>如果字符串的长度为 1 ，算法停止</li>
	<li>如果字符串的长度 > 1 ，执行下述步骤：
	<ul>
		<li>在一个随机下标处将字符串分割成两个非空的子字符串。即，如果已知字符串 <code>s</code> ，则可以将其分成两个子字符串 <code>x</code> 和 <code>y</code> ，且满足 <code>s = x + y</code> 。</li>
		<li><strong>随机</strong> 决定是要「交换两个子字符串」还是要「保持这两个子字符串的顺序不变」。即，在执行这一步骤之后，<code>s</code> 可能是 <code>s = x + y</code> 或者 <code>s = y + x</code> 。</li>
		<li>在 <code>x</code> 和 <code>y</code> 这两个子字符串上继续从步骤 1 开始递归执行此算法。</li>
	</ul>
	</li>
</ol>

<p>给你两个 <strong>长度相等</strong> 的字符串 <code>s1</code><em> </em>和 <code>s2</code>，判断 <code>s2</code><em> </em>是否是 <code>s1</code><em> </em>的扰乱字符串。如果是，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>



<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s1 = "great", s2 = "rgeat"
<strong>输出：</strong>true
<strong>解释：</strong>s1 上可能发生的一种情形是：
"great" --> "gr/eat" // 在一个随机下标处分割得到两个子字符串
"gr/eat" --> "gr/eat" // 随机决定：「保持这两个子字符串的顺序不变」
"gr/eat" --> "g/r / e/at" // 在子字符串上递归执行此算法。两个子字符串分别在随机下标处进行一轮分割
"g/r / e/at" --> "r/g / e/at" // 随机决定：第一组「交换两个子字符串」，第二组「保持这两个子字符串的顺序不变」
"r/g / e/at" --> "r/g / e/ a/t" // 继续递归执行此算法，将 "at" 分割得到 "a/t"
"r/g / e/ a/t" --> "r/g / e/ a/t" // 随机决定：「保持这两个子字符串的顺序不变」
算法终止，结果字符串和 s2 相同，都是 "rgeat"
这是一种能够扰乱 s1 得到 s2 的情形，可以认为 s2 是 s1 的扰乱字符串，返回 true
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s1 = "abcde", s2 = "caebd"
<strong>输出：</strong>false
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s1 = "a", s2 = "a"
<strong>输出：</strong>true
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>s1.length == s2.length</code></li>
	<li><code>1 <= s1.length <= 30</code></li>
	<li><code>s1</code> 和 <code>s2</code> 由小写英文字母组成</li>
</ul>

## 解法

**方法一：记忆化搜索**

我们设计一个函数dfs(i, j, k)，表示字符串s_1从i开始长度为k的子串是否能变换为字符串s_2从j开始长度为k的子串。如果能变换，返回 `true`，否则返回 `false`。那么答案就是dfs(0, 0, n)，其中n是字符串的长度。

函数dfs(i, j, k)的计算方式如下：

-   如果k=1，那么只需要判断s_1[i]和s_2[j]是否相等，如果相等返回 `true`，否则返回 `false`；
-   如果k > 1，我们枚举分割部分的长度h，那么有两种情况：如果不交换分割的两个子字符串，那么就是dfs(i, j, h) AND dfs(i+h, j+h, k-h)；如果交换了分割的两个子字符串，那么就是dfs(i, j+k-h, h) AND dfs(i+h, j, k-h)。如果两种情况中有一种情况成立，那么就说明dfs(i, j, k)成立，返回 `true`，否则返回 `false`。

最后，我们返回dfs(0, 0, n)。

为了避免重复计算，我们可以使用记忆化搜索的方式。

时间复杂度O(n^4)，空间复杂度O(n^3)。其中n是字符串的长度。

**方法二：动态规划（区间 DP）**

我们定义f[i][j][k]表示字符串s_1从i开始长度为k的子串是否能变换为字符串s_2从j开始长度为k的子串。那么答案就是f[0][0][n]，其中n是字符串的长度。

对于长度为1的子串，如果s_1[i] = s_2[j]，那么f[i][j][1] = true，否则f[i][j][1] = false。

接下来，我们从小到大枚举子串的长度k，从0开始枚举i，从0开始枚举j，如果f[i][j][h] AND f[i + h][j + h][k - h]或者f[i][j + k - h][h] AND f[i + h][j][k - h]成立，那么f[i][j][k]也成立。

最后，我们返回f[0][0][n]。

时间复杂度O(n^4)，空间复杂度O(n^3)。其中n是字符串的长度。

### **Java**

```java
class Solution {
    private Boolean[][][] f;
    private String s1;
    private String s2;

    public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        this.s1 = s1;
        this.s2 = s2;
        f = new Boolean[n][n][n + 1];
        return dfs(0, 0, n);
    }

    private boolean dfs(int i, int j, int k) {
        if (f[i][j][k] != null) {
            return f[i][j][k];
        }
        if (k == 1) {
            return s1.charAt(i) == s2.charAt(j);
        }
        for (int h = 1; h < k; ++h) {
            if (dfs(i, j, h) && dfs(i + h, j + h, k - h)) {
                return f[i][j][k] = true;
            }
            if (dfs(i + h, j, k - h) && dfs(i, j + k - h, h)) {
                return f[i][j][k] = true;
            }
        }
        return f[i][j][k] = false;
    }
}
```

```java
class Solution {
    public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        boolean[][][] f = new boolean[n][n][n + 1];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                f[i][j][1] = s1.charAt(i) == s2.charAt(j);
            }
        }
        for (int k = 2; k <= n; ++k) {
            for (int i = 0; i <= n - k; ++i) {
                for (int j = 0; j <= n - k; ++j) {
                    for (int h = 1; h < k; ++h) {
                        if (f[i][j][h] && f[i + h][j + h][k - h]) {
                            f[i][j][k] = true;
                            break;
                        }
                        if (f[i + h][j][k - h] && f[i][j + k - h][h]) {
                            f[i][j][k] = true;
                            break;
                        }
                    }
                }
            }
        }
        return f[0][0][n];
    }
}
```
