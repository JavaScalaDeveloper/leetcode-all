# [剑指 Offer II 017. 含有所有字符的最短字符串](https://leetcode.cn/problems/M1oyTv)

## 题目描述

<p>给定两个字符串 <code>s</code> 和&nbsp;<code>t</code> 。返回 <code>s</code> 中包含&nbsp;<code>t</code>&nbsp;的所有字符的最短子字符串。如果 <code>s</code> 中不存在符合条件的子字符串，则返回空字符串 <code>&quot;&quot;</code> 。</p>

<p>如果 <code>s</code> 中存在多个符合条件的子字符串，返回任意一个。</p>

<p>&nbsp;</p>

<p><strong>注意： </strong>对于 <code>t</code> 中重复字符，我们寻找的子字符串中该字符数量必须不少于 <code>t</code> 中该字符数量。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;ADOBECODEBANC&quot;, t = &quot;ABC&quot;
<strong>输出：</strong>&quot;BANC&quot;
<strong>解释：</strong>最短子字符串 &quot;BANC&quot; 包含了字符串 t 的所有字符 &#39;A&#39;、&#39;B&#39;、&#39;C&#39;</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;a&quot;, t = &quot;a&quot;
<strong>输出：</strong>&quot;a&quot;
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = &quot;a&quot;, t = &quot;aa&quot;
<strong>输出：</strong>&quot;&quot;
<strong>解释：</strong>t 中两个字符 &#39;a&#39; 均应包含在 s 的子串中，因此没有符合条件的子字符串，返回空字符串。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length, t.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 和 <code>t</code> 由英文字母组成</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你能设计一个在 <code>o(n)</code> 时间内解决此问题的算法吗？</p>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 76&nbsp;题相似（本题答案不唯一）：<a href="https://leetcode.cn/problems/minimum-window-substring/">https://leetcode.cn/problems/minimum-window-substring/</a></p>

## 解法

滑动窗口，当窗口包含全部需要的的字符后，进行收缩，以求得最小长度

进阶解法：利用 `count` 变量避免重复对 `need` 和 `window` 进行扫描

进阶解法

### **Java**

```java
class Solution {
    public String minWindow(String s, String t) {
        int m = s.length(), n = t.length();
        if (n > m) {
            return "";
        }
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (char ch : t.toCharArray()) {
            need.merge(ch, 1, Integer::sum);
        }
        int start = 0, minLen = Integer.MAX_VALUE;
        int left = 0, right = 0;
        while (right < m) {
            window.merge(s.charAt(right++), 1, Integer::sum);
            while (check(need, window)) {
                if (right - left < minLen) {
                    minLen = right - left;
                    start = left;
                }
                window.merge(s.charAt(left++), -1, Integer::sum);
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    private boolean check(Map<Character, Integer> need, Map<Character, Integer> window) {
        for (Map.Entry<Character, Integer> entry : need.entrySet()) {
            if (window.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }
}
```

进阶解法

```java
class Solution {
    public static String minWindow(String s, String t) {
        // 统计 t 中每个字符的出现次数
        Map<Character, Integer> targetCounts = new HashMap<>();
        for (char ch : t.toCharArray()) {
            targetCounts.put(ch, targetCounts.getOrDefault(ch, 0) + 1);
        }

        int start = 0;
        int minLen = Integer.MAX_VALUE;
        int formed = 0;  // 已经形成的子串中包含的 t 中字符的数量
        Map<Character, Integer> windowCounts = new HashMap<>();  // 当前窗口中每个字符的出现次数

        int left = 0, right = 0;  // 滑动窗口的左右指针
        while (right < s.length()) {
            // 移动右指针，扩大窗口
            char chRight = s.charAt(right);
            windowCounts.put(chRight, windowCounts.getOrDefault(chRight, 0) + 1);
            // 如果当前窗口中该字符的数量达到 t 中的要求，则更新 formed 的值
            if (targetCounts.containsKey(chRight) && windowCounts.get(chRight).equals(targetCounts.get(chRight))) {
                formed++;
            }

            // 尝试缩小窗口，更新最小子串长度和起始位置
            while (left <= right && formed == targetCounts.size()) {
                char chLeft = s.charAt(left);
                // 更新最小子串长度和起始位置
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }
                // 移动左指针，缩小窗口
                windowCounts.put(chLeft, windowCounts.get(chLeft) - 1);
                if (targetCounts.containsKey(chLeft) && windowCounts.get(chLeft) < targetCounts.get(chLeft)) {
                    formed--;
                }
                left++;
            }

            right++;
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}
```

进阶解法
