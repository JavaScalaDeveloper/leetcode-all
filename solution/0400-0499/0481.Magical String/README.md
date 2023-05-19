# [481. 神奇字符串](https://leetcode.cn/problems/magical-string)

[English Version](/solution/0400-0499/0481.Magical%20String/README_EN.md)

## 题目描述

<p>神奇字符串 <code>s</code> 仅由 <code>'1'</code> 和 <code>'2'</code> 组成，并需要遵守下面的规则：</p>

<ul>
	<li>神奇字符串 s 的神奇之处在于，串联字符串中 <code>'1'</code> 和 <code>'2'</code> 的连续出现次数可以生成该字符串。</li>
</ul>

<p><code>s</code> 的前几个元素是 <code>s = "1221121221221121122……"</code> 。如果将 <code>s</code> 中连续的若干 <code>1</code> 和 <code>2</code> 进行分组，可以得到 <code>"1 22 11 2 1 22 1 22 11 2 11 22 ......"</code> 。每组中 <code>1</code> 或者 <code>2</code> 的出现次数分别是 <code>"1 2 2 1 1 2 1 2 2 1 2 2 ......"</code> 。上面的出现次数正是 <code>s</code> 自身。</p>

<p>给你一个整数 <code>n</code> ，返回在神奇字符串 <code>s</code> 的前 <code>n</code> 个数字中 <code>1</code> 的数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 6
<strong>输出：</strong>3
<strong>解释：</strong>神奇字符串 s 的前 6 个元素是 “<code>122112</code>”，它包含三个 1，因此返回 3 。 
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

**方法一：模拟构造过程**

根据题目，我们得知，字符串 $s$ 的每一组数字都可以由字符串 $s$ 自身的数字得到。

字符串 $s$ 前两组数字为 $1$ 和 $22$，是由字符串 $s$ 的第一个数字 $1$ 和第二个数字 $2$ 得到的，并且第一组数字只包含 $1$，第二组数字只包含 $2$，第三组数字只包含 $1$，以此类推。

由于前两组数字已知，我们初始化字符串 $s$ 为 $122$，然后从第三组开始构造，第三组数字是由字符串 $s$ 的第三个数字（下标 $i=2$）得到，因此我们此时将指针 $i$ 指向字符串 $s$ 的第三个数字 $2$。

```
1 2 2
    ^
    i
```

指针 $i$ 指向的数字为 $2$，表示第三组的数字会出现两次，并且，由于前一组数字为 $2$，组之间数字交替出现，因此第三组数字为两个 $1$，即 $11$。构造后，指针 $i$ 移动到下一个位置，即指向字符串 $s$ 的第四个数字 $1$。

```
1 2 2 1 1
      ^
      i
```

这时候指针 $i$ 指向的数字为 $1$，表示第四组的数字会出现一次，并且，由于前一组数字为 $1$，组之间数字交替出现，因此第四组数字为一个 $2$，即 $2$。构造后，指针 $i$ 移动到下一个位置，即指向字符串 $s$ 的第五个数字 $1$。

```
1 2 2 1 1 2
        ^
        i
```

我们按照这个规则，依次模拟构造过程，直到字符串 $s$ 的长度大于等于 $n$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。

### **Java**

```java
class Solution {
    public int magicalString(int n) {
        List<Integer> s = new ArrayList<>(Arrays.asList(1, 2, 2));
        for (int i = 2; s.size() < n; ++i) {
            int pre = s.get(s.size() - 1);
            int cur = 3 - pre;
            for (int j = 0; j < s.get(i); ++j) {
                s.add(cur);
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (s.get(i) == 1) {
                ++ans;
            }
        }
        return ans;
    }
}
```
