# [567. 字符串的排列](https://leetcode.cn/problems/permutation-in-string)

[English Version](/solution/0500-0599/0567.Permutation%20in%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个字符串&nbsp;<code>s1</code>&nbsp;和&nbsp;<code>s2</code> ，写一个函数来判断 <code>s2</code> 是否包含 <code>s1</code><strong>&nbsp;</strong>的排列。如果是，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>换句话说，<code>s1</code> 的排列之一是 <code>s2</code> 的 <strong>子串</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s1 = "ab" s2 = "eidbaooo"
<strong>输出：</strong>true
<strong>解释：</strong>s2 包含 s1 的排列之一 ("ba").
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s1= "ab" s2 = "eidboaoo"
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s1.length, s2.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s1</code> 和 <code>s2</code> 仅包含小写字母</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：滑动窗口**

我们观察发现，题目实际上等价于判断字符串 $s2$ 中是否存在窗口大小为 $n$，且窗口内的字符及其个数与字符串 $s1$ 相同的子串。

因此，我们先用哈希表或数组 $cnt1$ 统计字符串 $s1$ 中每个字符出现的次数，然后遍历字符串 $s2$，维护一个窗口大小为 $n$ 的滑动窗口，用哈希表或数组 $cnt2$ 统计窗口内每个字符出现的次数，当 $cnt1 = cnt2$ 时，说明窗口内的字符及其个数与字符串 $s1$ 相同，返回 `true` 即可。

否则，遍历结束后，返回 `false`。

时间复杂度 $(n + m \times C)$，空间复杂度 $O(C)$。其中 $n$ 和 $m$ 分别为字符串 $s1$ 和 $s2$ 的长度；而 $C$ 为字符集的大小，本题中 $C=26$。

**方法二：滑动窗口优化**

在方法一中，我们每次加入和移除一个字符时，都需要比较两个哈希表或数组，时间复杂度较高。我们可以维护一个变量 $diff$，表示两个大小为 $n$ 的字符串中，有多少种字符出现的个数不同。当 $diff=0$ 时，说明两个字符串中的字符个数相同。

时间复杂度 $O(n + m)$，空间复杂度 $O(C)$。其中 $n$ 和 $m$ 分别为字符串 $s1$ 和 $s2$ 的长度；而 $C$ 为字符集的大小，本题中 $C=26$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->





### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < n; ++i) {
            ++cnt1[s1.charAt(i) - 'a'];
            ++cnt2[s2.charAt(i) - 'a'];
        }
        if (Arrays.equals(cnt1, cnt2)) {
            return true;
        }
        for (int i = n; i < m; ++i) {
            ++cnt2[s2.charAt(i) - 'a'];
            --cnt2[s2.charAt(i - n) - 'a'];
            if (Arrays.equals(cnt1, cnt2)) {
                return true;
            }
        }
        return false;
    }
}
```

```java
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        if (n > m) {
            return false;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < n; ++i) {
            --cnt[s1.charAt(i) - 'a'];
            ++cnt[s2.charAt(i) - 'a'];
        }
        int diff = 0;
        for (int x : cnt) {
            if (x != 0) {
                ++diff;
            }
        }
        if (diff == 0) {
            return true;
        }
        for (int i = n; i < m; ++i) {
            int a = s2.charAt(i - n) - 'a';
            int b = s2.charAt(i) - 'a';
            if (cnt[b] == 0) {
                ++diff;
            }
            if (++cnt[b] == 0) {
                --diff;
            }
            if (cnt[a] == 0) {
                ++diff;
            }
            if (--cnt[a] == 0) {
                --diff;
            }
            if (diff == 0) {
                return true;
            }
        }
        return false;
    }
}
```













### **TypeScript**











### **...**

```

```


