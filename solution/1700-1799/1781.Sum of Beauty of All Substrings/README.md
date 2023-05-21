# [1781. 所有子字符串美丽值之和](https://leetcode.cn/problems/sum-of-beauty-of-all-substrings)

## 题目描述

<p>一个字符串的 <strong>美丽值</strong> 定义为：出现频率最高字符与出现频率最低字符的出现次数之差。</p>

<ul>
	<li>比方说，<code>"abaacc"</code> 的美丽值为 <code>3 - 1 = 2</code> 。</li>
</ul>

<p>给你一个字符串 <code>s</code> ，请你返回它所有子字符串的 <strong>美丽值</strong> 之和。</p>



<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>s = "aabcb"
<b>输出：</b>5
<strong>解释：</strong>美丽值不为零的字符串包括 ["aab","aabc","aabcb","abcb","bcb"] ，每一个字符串的美丽值都为 1 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>s = "aabcbaa"
<b>输出：</b>17
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <=<sup> </sup>500</code></li>
	<li><code>s</code> 只包含小写英文字母。</li>
</ul>

## 解法

**方法一：枚举 + 计数**

枚举每个子串的起点位置 $i$，找到以该起点位置的字符为左端点的所有子串，然后计算每个子串的美丽值，累加到答案中。

时间复杂度 $O(n^2 \times C)$，空间复杂度 $O(C)$。其中 $n$ 为字符串的长度，而 $C$ 为字符集的大小。本题中 $C = 26$。

### **Java**

```java
class Solution {
    public int beautySum(String s) {
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int[] cnt = new int[26];
            for (int j = i; j < n; ++j) {
                ++cnt[s.charAt(j) - 'a'];
                int mi = 1000, mx = 0;
                for (int v : cnt) {
                    if (v > 0) {
                        mi = Math.min(mi, v);
                        mx = Math.max(mx, v);
                    }
                }
                ans += mx - mi;
            }
        }
        return ans;
    }
}
```
