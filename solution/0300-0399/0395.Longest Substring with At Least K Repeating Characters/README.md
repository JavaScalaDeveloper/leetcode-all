# [395. 至少有 K 个重复字符的最长子串](https://leetcode.cn/problems/longest-substring-with-at-least-k-repeating-characters)

## 题目描述

<p>给你一个字符串 <code>s</code> 和一个整数 <code>k</code> ，请你找出 <code>s</code> 中的最长子串， 要求该子串中的每一字符出现次数都不少于 <code>k</code> 。返回这一子串的长度。</p>



<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "aaabb", k = 3
<strong>输出：</strong>3
<strong>解释：</strong>最长子串为 "aaa" ，其中 'a' 重复了 3 次。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "ababbc", k = 2
<strong>输出：</strong>5
<strong>解释：</strong>最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 10<sup>4</sup></code></li>
	<li><code>s</code> 仅由小写英文字母组成</li>
	<li><code>1 <= k <= 10<sup>5</sup></code></li>
</ul>

## 解法

**方法一：分治**

对于字符串s，如果存在某个字符c，其出现次数小于k，则任何包含c的子串都不可能满足题目要求。因此我们可以将s按照每个不满足条件的字符c进行分割，分割得到的每个子串都是原字符串的一个「子问题」，我们可以递归地求解每个子问题，最终的答案即为所有子问题的最大值。

时间复杂度O(n × C)，空间复杂度O(C^2)。其中n为字符串s的长度，而C为字符集的大小。本题中C ≤ 26。

### **Java**

```java
class Solution {
    private String s;
    private int k;

    public int longestSubstring(String s, int k) {
        this.s = s;
        this.k = k;
        return dfs(0, s.length() - 1);
    }

    private int dfs(int l, int r) {
        int[] cnt = new int[26];
        for (int i = l; i <= r; ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        char split = 0;
        for (int i = 0; i < 26; ++i) {
            if (cnt[i] > 0 && cnt[i] < k) {
                split = (char) (i + 'a');
                break;
            }
        }
        if (split == 0) {
            return r - l + 1;
        }
        int i = l;
        int ans = 0;
        while (i <= r) {
            while (i <= r && s.charAt(i) == split) {
                ++i;
            }
            if (i > r) {
                break;
            }
            int j = i;
            while (j <= r && s.charAt(j) != split) {
                ++j;
            }
            int t = dfs(i, j - 1);
            ans = Math.max(ans, t);
            i = j;
        }
        return ans;
    }
}
```
