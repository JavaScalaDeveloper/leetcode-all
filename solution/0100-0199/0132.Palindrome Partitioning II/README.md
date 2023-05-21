# [132. 分割回文串 II](https://leetcode.cn/problems/palindrome-partitioning-ii)

## 题目描述

<p>给你一个字符串 <code>s</code>，请你将 <code>s</code> 分割成一些子串，使每个子串都是回文。</p>

<p>返回符合要求的 <strong>最少分割次数</strong> 。</p>

<div class="original__bRMd">
<div>


<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "aab"
<strong>输出：</strong>1
<strong>解释：</strong>只需一次分割就可将 <em>s </em>分割成 ["aa","b"] 这样两个回文子串。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "a"
<strong>输出：</strong>0
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "ab"
<strong>输出：</strong>1
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 2000</code></li>
	<li><code>s</code> 仅由小写英文字母组成</li>
</ul>
</div>
</div>

## 解法

两次 dp，`dp1[i][j]` 表示 i ~ j 的子串是否是回文，可以参考 [5. 最长回文子串](../../0000-0099/0005.Longest%20Palindromic%20Substring/README.md)。`dp2[i]` 表示以 i 结尾的子串最少需要分割几次，如果本来就是回文串（`dp[0][i] == true`）就不需要分割，否则枚举分割点 `j`

### **Java**

```java
class Solution {
    private boolean[][] g;
    private int[] f;
    private String s;
    private int n;

    public int minCut(String s) {
        n = s.length();
        g = new boolean[n][n];
        for (var e : g) {
            Arrays.fill(e, true);
        }
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                g[i][j] = s.charAt(i) == s.charAt(j) && g[i + 1][j - 1];
            }
        }
        this.s = s;
        f = new int[n];
        Arrays.fill(f, -1);
        return dfs(0);
    }

    private int dfs(int i) {
        if (i >= n - 1) {
            return 0;
        }
        if (f[i] != -1) {
            return f[i];
        }
        int ans = Integer.MAX_VALUE;
        for (int j = i; j < n; ++j) {
            if (g[i][j]) {
                ans = Math.min(ans, dfs(j + 1) + (j < n - 1 ? 1 : 0));
            }
        }
        f[i] = ans;
        return ans;
    }
}
```

```java
class Solution {
    public int minCut(String s) {
        int n = s.length();
        boolean[][] dp1 = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                dp1[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp1[i + 1][j - 1]);
            }
        }
        int[] dp2 = new int[n];
        for (int i = 0; i < n; i++) {
            if (!dp1[0][i]) {
                dp2[i] = i;
                for (int j = 1; j <= i; j++) {
                    if (dp1[j][i]) {
                        dp2[i] = Math.min(dp2[i], dp2[j - 1] + 1);
                    }
                }
            }
        }
        return dp2[n - 1];
    }
}
```
