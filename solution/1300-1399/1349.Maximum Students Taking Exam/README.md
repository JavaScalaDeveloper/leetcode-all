# [1349. 参加考试的最大学生数](https://leetcode.cn/problems/maximum-students-taking-exam)

## 题目描述

<p>给你一个&nbsp;<code>m&nbsp;* n</code>&nbsp;的矩阵 <code>seats</code>&nbsp;表示教室中的座位分布。如果座位是坏的（不可用），就用&nbsp;<code>&#39;#&#39;</code>&nbsp;表示；否则，用&nbsp;<code>&#39;.&#39;</code>&nbsp;表示。</p>

<p>学生可以看到左侧、右侧、左上、右上这四个方向上紧邻他的学生的答卷，但是看不到直接坐在他前面或者后面的学生的答卷。请你计算并返回该考场可以容纳的一起参加考试且无法作弊的最大学生人数。</p>

<p>学生必须坐在状况良好的座位上。</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1349.Maximum%20Students%20Taking%20Exam/images/image.png" style="height: 197px; width: 339px;"></p>

<pre><strong>输入：</strong>seats = [[&quot;#&quot;,&quot;.&quot;,&quot;#&quot;,&quot;#&quot;,&quot;.&quot;,&quot;#&quot;],
&nbsp;             [&quot;.&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;#&quot;,&quot;.&quot;],
&nbsp;             [&quot;#&quot;,&quot;.&quot;,&quot;#&quot;,&quot;#&quot;,&quot;.&quot;,&quot;#&quot;]]
<strong>输出：</strong>4
<strong>解释：</strong>教师可以让 4 个学生坐在可用的座位上，这样他们就无法在考试中作弊。 
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>seats = [[&quot;.&quot;,&quot;#&quot;],
&nbsp;             [&quot;#&quot;,&quot;#&quot;],
&nbsp;             [&quot;#&quot;,&quot;.&quot;],
&nbsp;             [&quot;#&quot;,&quot;#&quot;],
&nbsp;             [&quot;.&quot;,&quot;#&quot;]]
<strong>输出：</strong>3
<strong>解释：</strong>让所有学生坐在可用的座位上。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>seats = [[&quot;#&quot;,&quot;.&quot;,&quot;<strong>.</strong>&quot;,&quot;.&quot;,&quot;#&quot;],
&nbsp;             [&quot;<strong>.</strong>&quot;,&quot;#&quot;,&quot;<strong>.</strong>&quot;,&quot;#&quot;,&quot;<strong>.</strong>&quot;],
&nbsp;             [&quot;<strong>.</strong>&quot;,&quot;.&quot;,&quot;#&quot;,&quot;.&quot;,&quot;<strong>.</strong>&quot;],
&nbsp;             [&quot;<strong>.</strong>&quot;,&quot;#&quot;,&quot;<strong>.</strong>&quot;,&quot;#&quot;,&quot;<strong>.</strong>&quot;],
&nbsp;             [&quot;#&quot;,&quot;.&quot;,&quot;<strong>.</strong>&quot;,&quot;.&quot;,&quot;#&quot;]]
<strong>输出：</strong>10
<strong>解释：</strong>让学生坐在第 1、3 和 5 列的可用座位上。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>seats</code>&nbsp;只包含字符&nbsp;<code>&#39;.&#39;&nbsp;和</code><code>&#39;#&#39;</code></li>
	<li><code>m ==&nbsp;seats.length</code></li>
	<li><code>n ==&nbsp;seats[i].length</code></li>
	<li><code>1 &lt;= m &lt;= 8</code></li>
	<li><code>1 &lt;= n &lt;= 8</code></li>
</ul>

## 解法

**方法一：状态压缩 + 记忆化搜索**

我们注意到，每个座位有两种状态：可选和不可选。因此，我们可以使用二进制数来表示每一行的座位状态，其中1表示可选，而0表示不可选。例如，对于示例1中的第一行，我们可以表示为010010。因此，我们将初始座位转换为一个一维数组ss，其中ss[i]表示第i行的座位状态。

接下来，我们设计一个函数dfs(seat, i)，表示从第i行开始，当前行的座位状态为seat，可以容纳的最多学生人数。

我们可以枚举第i行的所有选座状态mask，并且判断mask是否满足以下条件：

-   状态mask不能选择seat之外的座位；
-   状态mask不能选择相邻的座位。

如果满足条件，我们求出当前行选择的座位个数cnt，如果当前是最后一行，则更新函数的返回值，即ans = max(ans, cnt)。否则，我们继续递归地求解下一行的最大人数，下一行的座位状态nxt = ss[i + 1]，并且需要排除当前行已选座位的左右两侧。然后我们递归地求解下一行的最大人数，即ans = max(ans, cnt + dfs(nxt, i + 1))。

最后，我们将ans作为函数的返回值返回。

为了避免重复计算，我们可以使用记忆化搜索，将函数dfs(seat, i)的返回值保存在一个二维数组f中，其中f[seat][i]表示从第i行开始，当前行的座位状态为seat，可以容纳的最多学生人数。

时间复杂度O(4^n × n × m)，空间复杂度O(2^n × m)。其中m和n分别为座位的行数和列数。

### **Java**

```java
class Solution {
    private Integer[][] f;
    private int n;
    private int[] ss;

    public int maxStudents(char[][] seats) {
        int m = seats.length;
        n = seats[0].length;
        ss = new int[m];
        f = new Integer[1 << n][m];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (seats[i][j] == '.') {
                    ss[i] |= 1 << j;
                }
            }
        }
        return dfs(ss[0], 0);
    }

    private int dfs(int seat, int i) {
        if (f[seat][i] != null) {
            return f[seat][i];
        }
        int ans = 0;
        for (int mask = 0; mask < 1 << n; ++mask) {
            if ((seat | mask) != seat || (mask & (mask << 1)) != 0) {
                continue;
            }
            int cnt = Integer.bitCount(mask);
            if (i == ss.length - 1) {
                ans = Math.max(ans, cnt);
            } else {
                int nxt = ss[i + 1];
                nxt &= ~(mask << 1);
                nxt &= ~(mask >> 1);
                ans = Math.max(ans, cnt + dfs(nxt, i + 1));
            }
        }
        return f[seat][i] = ans;
    }
}
```
