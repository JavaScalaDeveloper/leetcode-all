# [1320. 二指输入的的最小距离](https://leetcode.cn/problems/minimum-distance-to-type-a-word-using-two-fingers)

## 题目描述

<p><img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1320.Minimum%20Distance%20to%20Type%20a%20Word%20Using%20Two%20Fingers/images/leetcode_keyboard.png" /></p>

<p>二指输入法定制键盘在 <strong>X-Y</strong> 平面上的布局如上图所示，其中每个大写英文字母都位于某个坐标处。</p>

<ul>
	<li>例如字母&nbsp;<strong>A</strong>&nbsp;位于坐标&nbsp;<strong>(0,0)</strong>，字母&nbsp;<strong>B</strong>&nbsp;位于坐标&nbsp;<strong>(0,1)</strong>，字母&nbsp;<strong>P</strong>&nbsp;位于坐标&nbsp;<strong>(2,3)</strong>&nbsp;且字母 <strong>Z</strong>&nbsp;位于坐标&nbsp;<strong>(4,1)</strong>。</li>
</ul>

<p>给你一个待输入字符串&nbsp;<code>word</code>，请你计算并返回在仅使用两根手指的情况下，键入该字符串需要的最小移动总距离。</p>

<p>坐标<code>&nbsp;<strong>(x<sub>1</sub>,y<sub>1</sub>)</strong> </code>和 <code><strong>(x<sub>2</sub>,y<sub>2</sub>)</strong></code> 之间的 <strong>距离</strong> 是&nbsp;<code><strong>|x<sub>1</sub> - x<sub>2</sub>| + |y<sub>1</sub> - y<sub>2</sub>|</strong></code>。&nbsp;</p>

<p><strong>注意</strong>，两根手指的起始位置是零代价的，不计入移动总距离。你的两根手指的起始位置也不必从首字母或者前两个字母开始。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>word = "CAKE"
<strong>输出：</strong>3
<strong>解释： 
</strong>使用两根手指输入 "CAKE" 的最佳方案之一是： 
手指 1 在字母 'C' 上 -&gt; 移动距离 = 0 
手指 1 在字母 'A' 上 -&gt; 移动距离 = 从字母 'C' 到字母 'A' 的距离 = 2 
手指 2 在字母 'K' 上 -&gt; 移动距离 = 0 
手指 2 在字母 'E' 上 -&gt; 移动距离 = 从字母 'K' 到字母 'E' 的距离  = 1 
总距离 = 3
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>word = "HAPPY"
<strong>输出：</strong>6
<strong>解释： </strong>
使用两根手指输入 "HAPPY" 的最佳方案之一是：
手指 1 在字母 'H' 上 -&gt; 移动距离 = 0
手指 1 在字母 'A' 上 -&gt; 移动距离 = 从字母 'H' 到字母 'A' 的距离 = 2
手指 2 在字母 'P' 上 -&gt; 移动距离 = 0
手指 2 在字母 'P' 上 -&gt; 移动距离 = 从字母 'P' 到字母 'P' 的距离 = 0
手指 1 在字母 'Y' 上 -&gt; 移动距离 = 从字母 'A' 到字母 'Y' 的距离 = 4
总距离 = 6
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= word.length &lt;= 300</code></li>
	<li>每个 <code>word[i]</code>&nbsp;都是一个大写英文字母。</li>
</ul>

## 解法

**方法一：动态规划**

我们定义f[i][j][k]表示输入完word[i]，且手指1位于位置j，手指2位于位置k时，最小的移动距离。这里的位置j和k表示的是字母对应的数字，取值范围为[0,..25]。初始时f[i][j][k]=\infty。

我们实现一个函数dist(a, b)，表示位置a和位置b之间的距离，即dist(a, b) = |\frac{a}{6} - \frac{b}{6}| + |a \bmod 6 - b \bmod 6|。

接下来，我们考虑输入word[0]，即只有一个字母的的情况，此时有两种选择：

-   手指1位于word[0]所在的位置，手指2位于任意位置，此时f[0][word[0]][k] = 0，其中k \in [0,..25]。
-   手指2位于word[0]所在的位置，手指1位于任意位置，此时f[0][k][word[0]] = 0，其中k \in [0,..25]。

然后我们考虑输入word[1,..n-1]，我们记上一个字母和当前字母所在的位置分别为a和b，接下来我们进行分情况讨论：

如果当前手指1位于位置b，我们枚举手指2的位置j，假如上一个位置a也是手指1的位置，那么此时有f[i][b][j]=\min(f[i][b][j], f[i-1][a][j]+dist(a, b))。如果手指2的位置与上一个位置a相同，即j=a，那么我们枚举上一个位置的手指1所在的位置k，此时有f[i][j][j]=\min(f[i][b][j], f[i-1][k][a]+dist(k, b))。

同理，如果当前手指2位于位置b，我们枚举手指1的位置j，假如上一个位置a也是手指2的位置，那么此时有f[i][j][b]=\min(f[i][j][b], f[i-1][j][a]+dist(a, b))。如果手指1的位置与上一个位置a相同，即j=a，那么我们枚举上一个位置的手指2所在的位置k，此时有f[i][j][b]=\min(f[i][j][b], f[i-1][a][k]+dist(k, b))。

最后，我们枚举最后一个位置的手指1和手指2所在的位置，取最小值即为答案。

时间复杂度O(n \times 26^2)，空间复杂度O(n \times 26^2)。其中n为字符串word的长度。

### **Java**

```java
class Solution {
    public int minimumDistance(String word) {
        int n = word.length();
        final int inf = 1 << 30;
        int[][][] f = new int[n][26][26];
        for (int[][] g : f) {
            for (int[] h : g) {
                Arrays.fill(h, inf);
            }
        }
        for (int j = 0; j < 26; ++j) {
            f[0][word.charAt(0) - 'A'][j] = 0;
            f[0][j][word.charAt(0) - 'A'] = 0;
        }
        for (int i = 1; i < n; ++i) {
            int a = word.charAt(i - 1) - 'A';
            int b = word.charAt(i) - 'A';
            int d = dist(a, b);
            for (int j = 0; j < 26; ++j) {
                f[i][b][j] = Math.min(f[i][b][j], f[i - 1][a][j] + d);
                f[i][j][b] = Math.min(f[i][j][b], f[i - 1][j][a] + d);
                if (j == a) {
                    for (int k = 0; k < 26; ++k) {
                        int t = dist(k, b);
                        f[i][b][j] = Math.min(f[i][b][j], f[i - 1][k][a] + t);
                        f[i][j][b] = Math.min(f[i][j][b], f[i - 1][a][k] + t);
                    }
                }
            }
        }
        int ans = inf;
        for (int j = 0; j < 26; ++j) {
            ans = Math.min(ans, f[n - 1][j][word.charAt(n - 1) - 'A']);
            ans = Math.min(ans, f[n - 1][word.charAt(n - 1) - 'A'][j]);
        }
        return ans;
    }

    private int dist(int a, int b) {
        int x1 = a / 6, y1 = a % 6;
        int x2 = b / 6, y2 = b % 6;
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
```
