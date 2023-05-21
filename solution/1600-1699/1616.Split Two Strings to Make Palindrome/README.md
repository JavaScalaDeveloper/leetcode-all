# [1616. 分割两个字符串得到回文串](https://leetcode.cn/problems/split-two-strings-to-make-palindrome)

## 题目描述

<p>给你两个字符串&nbsp;<code>a</code> 和&nbsp;<code>b</code>&nbsp;，它们长度相同。请你选择一个下标，将两个字符串都在&nbsp;<strong>相同的下标 </strong>分割开。由&nbsp;<code>a</code>&nbsp;可以得到两个字符串：&nbsp;<code>a<sub>prefix</sub></code>&nbsp;和&nbsp;<code>a<sub>suffix</sub></code>&nbsp;，满足&nbsp;<code>a = a<sub>prefix</sub> + a<sub>suffix</sub></code><sub>&nbsp;</sub>，同理，由&nbsp;<code>b</code> 可以得到两个字符串&nbsp;<code>b<sub>prefix</sub></code> 和&nbsp;<code>b<sub>suffix</sub></code>&nbsp;，满足&nbsp;<code>b = b<sub>prefix</sub> + b<sub>suffix</sub></code>&nbsp;。请你判断&nbsp;<code>a<sub>prefix</sub> + b<sub>suffix</sub></code> 或者&nbsp;<code>b<sub>prefix</sub> + a<sub>suffix</sub></code>&nbsp;能否构成回文串。</p>

<p>当你将一个字符串&nbsp;<code>s</code>&nbsp;分割成&nbsp;<code>s<sub>prefix</sub></code> 和&nbsp;<code>s<sub>suffix</sub></code>&nbsp;时，&nbsp;<code>s<sub>suffix</sub></code> 或者&nbsp;<code>s<sub>prefix</sub></code> 可以为空。比方说，&nbsp;<code>s = "abc"</code>&nbsp;那么&nbsp;<code>"" + "abc"</code>&nbsp;，&nbsp;<code>"a" + "bc"&nbsp;</code>，&nbsp;<code>"ab" + "c"</code>&nbsp;和&nbsp;<code>"abc" + ""</code>&nbsp;都是合法分割。</p>

<p>如果 <strong>能构成回文字符串</strong> ，那么请返回&nbsp;<code>true</code>，否则返回<em>&nbsp;</em><code>false</code>&nbsp;。</p>

<p><strong>注意</strong>，&nbsp;<code>x + y</code>&nbsp;表示连接字符串&nbsp;<code>x</code> 和&nbsp;<code>y</code>&nbsp;。</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>a = "x", b = "y"
<b>输出：</b>true
<b>解释：</b>如果 a 或者 b 是回文串，那么答案一定为 true ，因为你可以如下分割：
a<sub>prefix</sub> = "", a<sub>suffix</sub> = "x"
b<sub>prefix</sub> = "", b<sub>suffix</sub> = "y"
那么 a<sub>prefix</sub> + b<sub>suffix</sub> = "" + "y" = "y" 是回文串。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>a = "abdef", b = "fecab"
<strong>输出：</strong>true
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>a = "ulacfd", b = "jizalu"
<b>输出：</b>true
<b>解释：</b>在下标为 3 处分割：
a<sub>prefix</sub> = "ula", a<sub>suffix</sub> = "cfd"
b<sub>prefix</sub> = "jiz", b<sub>suffix</sub> = "alu"
那么 a<sub>prefix</sub> + b<sub>suffix</sub> = "ula" + "alu" = "ulaalu" 是回文串。</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= a.length, b.length &lt;= 10<sup>5</sup></code></li>
	<li><code>a.length == b.length</code></li>
	<li><code>a</code> 和&nbsp;<code>b</code>&nbsp;都只包含小写英文字母</li>
</ul>

## 解法

**方法一：双指针**

我们可以使用双指针，其中一个指针i从字符串a的头部开始，另一个指针j从字符串b的尾部开始，如果两个指针指向的字符相等，那么两个指针同时往中间移动，直到遇到不同的字符或两指针交叉。

如果两指针交叉，即i \geq j，说明prefix和suffix已经可以得到回文串，返回 `true`；否则，我们还需要判断a[i,...j]或者b[i,...j]是否是回文串，若是，返回 `true`。

否则，我们尝试交换两个字符串a和b，重复上述同样的过程。

时间复杂度O(n)，空间复杂度O(1)。其中n是字符串a或b的长度。

### **Java**

```java
class Solution {
    public boolean checkPalindromeFormation(String a, String b) {
        return check1(a, b) || check1(b, a);
    }

    private boolean check1(String a, String b) {
        int i = 0;
        int j = b.length() - 1;
        while (i < j && a.charAt(i) == b.charAt(j)) {
            i++;
            j--;
        }
        return i >= j || check2(a, i, j) || check2(b, i, j);
    }

    private boolean check2(String a, int i, int j) {
        while (i < j && a.charAt(i) == a.charAt(j)) {
            i++;
            j--;
        }
        return i >= j;
    }
}
```
