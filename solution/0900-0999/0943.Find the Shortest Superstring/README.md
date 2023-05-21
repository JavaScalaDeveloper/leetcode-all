# [943. 最短超级串](https://leetcode.cn/problems/find-the-shortest-superstring)

## 题目描述

<p>给定一个字符串数组 <code>words</code>，找到以 <code>words</code> 中每个字符串作为子字符串的最短字符串。如果有多个有效最短字符串满足题目条件，返回其中 <strong>任意一个</strong> 即可。</p>

<p>我们可以假设 <code>words</code> 中没有字符串是 <code>words</code> 中另一个字符串的子字符串。</p>



<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>words = ["alex","loves","leetcode"]
<strong>输出：</strong>"alexlovesleetcode"
<strong>解释：</strong>"alex"，"loves"，"leetcode" 的所有排列都会被接受。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>words = ["catg","ctaagt","gcta","ttca","atgcatc"]
<strong>输出：</strong>"gctaagttcatgcatc"</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= words.length <= 12</code></li>
	<li><code>1 <= words[i].length <= 20</code></li>
	<li><code>words[i]</code> 由小写英文字母组成</li>
	<li><code>words</code> 中的所有字符串 <strong>互不相同</strong></li>
</ul>

## 解法

**方法一：状态压缩 + 动态规划**

由于题目中字符串数组 `words` 的长度不超过 12，因此可以使用状态压缩的方法来表示字符串数组中的每个字符串是否被选中。

定义 $dp[i][j]$ 表示字符串当前选中状态为 $i$，且最后一个选中的字符串为 $s[j]$ 时，字符串重叠部分的最大长度。其中 $i$ 的二进制表示中的第 $j$ 位为 $1$ 表示字符串 $s[j]$ 被选中，为 $0$ 表示字符串 $s[j]$ 未被选中。重叠部分达到最大时，最终得到的字符串最短。我们只要求出重叠部分的最大值以及对应的字符串 $s[j]$，记录每一步状态转移，就能够逆推出最终的字符串。

字符串两两之间的重叠部分长度可以预处理出来，存储在二维数组 $g$ 中，其中 $g[i][j]$ 表示字符串 $s[i]$ 和字符串 $s[j]$ 之间的重叠部分长度。

动态规划的状态转移方程如下：

$$
dp[i][j] = \max_{k \in \{0, 1, \cdots, n - 1\}} \{dp[i - 2^j][k] + g[k][j]\}
$$

时间复杂度 $O(2^n \times n^2)$，空间复杂度 $O(2^n \times n)$。其中 $n$ 为字符串数组 `words` 的长度。

### **Java**

```java
class Solution {
    public String shortestSuperstring(String[] words) {
        int n = words.length;
        int[][] g = new int[n][n];
        for (int i = 0; i < n; ++i) {
            String a = words[i];
            for (int j = 0; j < n; ++j) {
                String b = words[j];
                if (i != j) {
                    for (int k = Math.min(a.length(), b.length()); k > 0; --k) {
                        if (a.substring(a.length() - k).equals(b.substring(0, k))) {
                            g[i][j] = k;
                            break;
                        }
                    }
                }
            }
        }
        int[][] dp = new int[1 << n][n];
        int[][] p = new int[1 << n][n];
        for (int i = 0; i < 1 << n; ++i) {
            Arrays.fill(p[i], -1);
            for (int j = 0; j < n; ++j) {
                if (((i >> j) & 1) == 1) {
                    int pi = i ^ (1 << j);
                    for (int k = 0; k < n; ++k) {
                        if (((pi >> k) & 1) == 1) {
                            int v = dp[pi][k] + g[k][j];
                            if (v > dp[i][j]) {
                                dp[i][j] = v;
                                p[i][j] = k;
                            }
                        }
                    }
                }
            }
        }
        int j = 0;
        for (int i = 0; i < n; ++i) {
            if (dp[(1 << n) - 1][i] > dp[(1 << n) - 1][j]) {
                j = i;
            }
        }
        List<Integer> arr = new ArrayList<>();
        arr.add(j);
        for (int i = (1 << n) - 1; p[i][j] != -1;) {
            int k = i;
            i ^= (1 << j);
            j = p[k][j];
            arr.add(j);
        }
        Set<Integer> vis = new HashSet<>(arr);
        for (int i = 0; i < n; ++i) {
            if (!vis.contains(i)) {
                arr.add(i);
            }
        }
        Collections.reverse(arr);
        StringBuilder ans = new StringBuilder(words[arr.get(0)]);
        for (int i = 1; i < n; ++i) {
            int k = g[arr.get(i - 1)][arr.get(i)];
            ans.append(words[arr.get(i)].substring(k));
        }
        return ans.toString();
    }
}
```
