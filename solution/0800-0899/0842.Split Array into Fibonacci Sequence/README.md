# [842. 将数组拆分成斐波那契序列](https://leetcode.cn/problems/split-array-into-fibonacci-sequence)

## 题目描述

<p>给定一个数字字符串 <code>num</code>，比如 <code>"123456579"</code>，我们可以将它分成「斐波那契式」的序列 <code>[123, 456, 579]</code>。</p>

<p>形式上，<strong>斐波那契式&nbsp;</strong>序列是一个非负整数列表 <code>f</code>，且满足：</p>

<ul>
	<li><code>0 &lt;= f[i] &lt; 2<sup>31</sup></code>&nbsp;，（也就是说，每个整数都符合 <strong>32 位</strong>&nbsp;有符号整数类型）</li>
	<li><code>f.length &gt;= 3</code></li>
	<li>对于所有的<code>0 &lt;= i &lt; f.length - 2</code>，都有 <code>f[i] + f[i + 1] = f[i + 2]</code></li>
</ul>

<p>另外，请注意，将字符串拆分成小块时，每个块的数字一定不要以零开头，除非这个块是数字 <code>0</code> 本身。</p>

<p>返回从 <code>num</code> 拆分出来的任意一组斐波那契式的序列块，如果不能拆分则返回 <code>[]</code>。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>num = "1101111"
<strong>输出：</strong>[11,0,11,11]
<strong>解释：</strong>输出[110,1,111]也可以。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入: </strong>num = "112358130"
<strong>输出: </strong>[]
<strong>解释: </strong>无法拆分。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>"0123"
<strong>输出：</strong>[]
<strong>解释：</strong>每个块的数字不能以零开头，因此 "01"，"2"，"3" 不是有效答案。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num.length &lt;= 200</code></li>
	<li><code>num</code>&nbsp;中只含有数字</li>
</ul>

## 解法

**方法一：回溯 + 剪枝**

我们设计一个函数dfs(i)，表示从字符串num的第i个字符开始拆分，拆分出的斐波那契式序列是否满足题目要求。如果满足，我们就返回true，否则返回false。

函数dfs(i)的具体实现如下：

如果i等于字符串num的长度，说明我们已经拆分完整个字符串，此时我们只需要判断拆分出的序列的长度是否大于2即可。如果大于2，说明我们找到了一组满足题目要求的斐波那契式序列，返回true；否则返回false。

如果i小于字符串num的长度，我们需要枚举拆分出的第一个数x，如果x的长度大于1，且以0开头，说明x不是一个合法的数，我们直接返回false。否则我们将x转换成十进制数，如果x大于2^{31} - 1，或者x大于ans的最后两个数之和，直接返回false。如果ans的长度小于2，或者x等于ans的最后两个数之和，我们将x加入到ans中，然后继续拆分字符串num的后面的部分，如果返回true，说明我们找到了一组满足题目要求的斐波那契式序列，返回true；否则我们将x从ans中移除，然后继续枚举拆分出的第一个数x。

时间复杂度O(n × log^2 M)，空间复杂度O(n)。其中n和M分别是字符串num的长度和整型数的最大值。

### **Java**

```java
class Solution {
    private List<Integer> ans = new ArrayList<>();
    private String num;

    public List<Integer> splitIntoFibonacci(String num) {
        this.num = num;
        dfs(0);
        return ans;
    }

    private boolean dfs(int i) {
        if (i == num.length()) {
            return ans.size() >= 3;
        }
        long x = 0;
        for (int j = i; j < num.length(); ++j) {
            if (j > i && num.charAt(i) == '0') {
                break;
            }
            x = x * 10 + num.charAt(j) - '0';
            if (x > Integer.MAX_VALUE
                || (ans.size() >= 2 && x > ans.get(ans.size() - 1) + ans.get(ans.size() - 2))) {
                break;
            }
            if (ans.size() < 2 || x == ans.get(ans.size() - 1) + ans.get(ans.size() - 2)) {
                ans.add((int) x);
                if (dfs(j + 1)) {
                    return true;
                }
                ans.remove(ans.size() - 1);
            }
        }
        return false;
    }
}
```
