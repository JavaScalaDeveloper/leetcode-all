# [1147. 段式回文](https://leetcode.cn/problems/longest-chunked-palindrome-decomposition)

## 题目描述

<p>你会得到一个字符串&nbsp;<code>text</code>&nbsp;。你应该把它分成 <code>k</code>&nbsp;个子字符串&nbsp;<code>(subtext1, subtext2，…， subtextk)</code>&nbsp;，要求满足:</p>

<ul>
	<li><code>subtext<sub>i</sub></code><sub>&nbsp;</sub>是 <strong>非空&nbsp;</strong>字符串</li>
	<li>所有子字符串的连接等于 <code>text</code> ( 即<code>subtext<sub>1</sub>&nbsp;+ subtext<sub>2</sub>&nbsp;+ ... + subtext<sub>k</sub>&nbsp;== text</code>&nbsp;)</li>
	<li>对于所有 <font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size:12.6px"><span style="background-color:#f9f2f4">i</span></span></font></font>&nbsp;的有效值( 即&nbsp;<code>1 &lt;= i&nbsp;&lt;= k</code> ) ，<code>subtext<sub>i</sub>&nbsp;== subtext<sub>k - i + 1</sub></code> 均成立</li>
</ul>

<p>返回<code>k</code>可能最大值。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>text = "ghiabcdefhelloadamhelloabcdefghi"
<strong>输出：</strong>7
<strong>解释：</strong>我们可以把字符串拆分成 "(ghi)(abcdef)(hello)(adam)(hello)(abcdef)(ghi)"。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>text = "merchant"
<strong>输出：</strong>1
<strong>解释：</strong>我们可以把字符串拆分成 "(merchant)"。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>text = "antaprezatepzapreanta"
<strong>输出：</strong>11
<strong>解释：</strong>我们可以把字符串拆分成 "(a)(nt)(a)(pre)(za)(tpe)(za)(pre)(a)(nt)(a)"。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= text.length &lt;= 1000</code></li>
	<li><code>text</code>&nbsp;仅由小写英文字符组成</li>
</ul>

## 解法

**方法一：贪心 + 双指针**

我们可以从字符串的两端开始，寻找长度最短的、相同且不重叠的前后缀：

-   如果找不到这样的前后缀，那么整个字符串作为一个段式回文，答案加 $1$；
-   如果找到了这样的前后缀，那么这个前后缀作为一个段式回文，答案加 $2$，然后继续寻找剩下的字符串的前后缀。

以上贪心策略的证明如下：

假设有一个前后缀 $A_1$ 和 $A_2$ 满足条件，又有一个前后缀 $B_1$ 和 $B_4$ 满足条件，由于 $A_1 = A_2$，且 $B_1=B_4$，那么有 $B_3=B_1=B_4=B_2$，并且 $C_1 = C_2$，因此，如果我们贪心地将 $B_1$ 和 $B_4$ 分割出来，那么剩下的 $C_1$ 和 $C_2$，以及 $B_2$ 和 $B_3$ 也能成功分割。因此我们应该贪心地选择长度最短的相同前后缀进行分割，这样剩余的字符串中，将可能分割出更多的段式回文串。

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1147.Longest%20Chunked%20Palindrome%20Decomposition/images/demo.png" style="width: 300px;" /></p>

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$ 或 $O(1)$。其中 $n$ 为字符串的长度。

**方法二：字符串哈希**

**字符串哈希**是把一个任意长度的字符串映射成一个非负整数，并且其冲突的概率几乎为 $0$。字符串哈希用于计算字符串哈希值，快速判断两个字符串是否相等。

因此，在方法一的基础上，我们可以使用字符串哈希的方法，在 $O(1)$ 时间内比较两个字符串是否相等。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串的长度。

### **Java**

```java
class Solution {
    public int longestDecomposition(String text) {
        int n = text.length();
        if (n < 2) {
            return n;
        }
        for (int i = 1; i <= n >> 1; ++i) {
            if (text.substring(0, i).equals(text.substring(n - i))) {
                return 2 + longestDecomposition(text.substring(i, n - i));
            }
        }
        return 1;
    }
}
```

```java
class Solution {
    public int longestDecomposition(String text) {
        int ans = 0;
        for (int i = 0, j = text.length() - 1; i <= j;) {
            boolean ok = false;
            for (int k = 1; i + k - 1 < j - k + 1; ++k) {
                if (check(text, i, j - k + 1, k)) {
                    ans += 2;
                    i += k;
                    j -= k;
                    ok = true;
                    break;
                }
            }
            if (!ok) {
                ++ans;
                break;
            }
        }
        return ans;
    }

    private boolean check(String s, int i, int j, int k) {
        while (k-- > 0) {
            if (s.charAt(i++) != s.charAt(j++)) {
                return false;
            }
        }
        return true;
    }
}
```

```java
class Solution {
    private long[] h;
    private long[] p;

    public int longestDecomposition(String text) {
        int n = text.length();
        int base = 131;
        h = new long[n + 10];
        p = new long[n + 10];
        p[0] = 1;
        for (int i = 0; i < n; ++i) {
            int t = text.charAt(i) - 'a' + 1;
            h[i + 1] = h[i] * base + t;
            p[i + 1] = p[i] * base;
        }
        int ans = 0;
        for (int i = 0, j = n - 1; i <= j;) {
            boolean ok = false;
            for (int k = 1; i + k - 1 < j - k + 1; ++k) {
                if (get(i + 1, i + k) == get(j - k + 2, j + 1)) {
                    ans += 2;
                    i += k;
                    j -= k;
                    ok = true;
                    break;
                }
            }
            if (!ok) {
                ++ans;
                break;
            }
        }
        return ans;
    }

    private long get(int i, int j) {
        return h[j] - h[i - 1] * p[j - i + 1];
    }
}
```
