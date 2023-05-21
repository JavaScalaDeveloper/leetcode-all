# [1208. 尽可能使字符串相等](https://leetcode.cn/problems/get-equal-substrings-within-budget)

## 题目描述

<p>给你两个长度相同的字符串，<code>s</code> 和 <code>t</code>。</p>

<p>将 <code>s</code> 中的第 <code>i</code> 个字符变到 <code>t</code> 中的第 <code>i</code> 个字符需要 <code>|s[i] - t[i]|</code> 的开销（开销可能为 0），也就是两个字符的 ASCII 码值的差的绝对值。</p>

<p>用于变更字符串的最大预算是 <code>maxCost</code>。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。</p>

<p>如果你可以将 <code>s</code> 的子字符串转化为它在 <code>t</code> 中对应的子字符串，则返回可以转化的最大长度。</p>

<p>如果 <code>s</code> 中没有子字符串可以转化成 <code>t</code> 中对应的子字符串，则返回 <code>0</code>。</p>



<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "abcd", t = "bcdf", maxCost = 3
<strong>输出：</strong>3
<strong>解释：</strong>s<strong> </strong>中的<strong> </strong>"abc" 可以变为 "bcd"。开销为 3，所以最大长度为 3。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "abcd", t = "cdef", maxCost = 3
<strong>输出：</strong>1
<strong>解释：</strong>s 中的任一字符要想变成 t 中对应的字符，其开销都是 2。因此，最大长度为<code> 1。</code>
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "abcd", t = "acde", maxCost = 0
<strong>输出：</strong>1
<strong>解释：</strong>a -> a, cost = 0，字符串未发生变化，所以最大长度为 1。
</pre>



<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length, t.length <= 10^5</code></li>
	<li><code>0 <= maxCost <= 10^6</code></li>
	<li><code>s</code> 和 <code>t</code> 都只含小写英文字母。</li>
</ul>

## 解法

**方法一：前缀和 + 二分查找**

我们可以创建一个长度为n + 1的数组f，其中f[i]表示字符串s的前i个字符与字符串t的前i个字符的 ASCII 码值的差的绝对值之和。这样，我们就可以通过f[j + 1] - f[i]来计算字符串s的第i个字符到第j个字符的 ASCII 码值的差的绝对值之和，其中0 \leq i \leq j < n。

注意到长度具有单调性，即如果存在长度为x的子串满足条件，那么长度为x - 1的子串也一定满足条件。因此，我们可以使用二分查找的方法来求解最大长度。

我们定义函数check(x)，表示是否存在长度为x的子串满足条件。在该函数中，我们只需要枚举所有长度为x的子串，判断其是否满足条件即可。如果存在满足条件的子串，那么函数返回 `true`，否则返回 `false`。

接下来，我们定义二分查找的左边界l为0，右边界r为n。在每一步中，我们令mid = \lfloor \frac{l + r + 1}{2} \rfloor，如果函数check(mid)的返回值为 `true`，那么我们将左边界更新为mid，否则我们将右边界更新为mid - 1。在二分查找结束后，我们得到的左边界即为答案。

时间复杂度O(n \times \log n)，空间复杂度O(n)。其中n为字符串s的长度。

**方法二：双指针**

我们可以维护两个指针j和i，初始时i = j = 0；维护一个变量sum，表示下标区间[i,..j]之间的 ASCII 码值的差的绝对值之和。在每一步中，我们将i向右移动一位，然后更新sum = sum + |s[i] - t[i]|。如果sum \gt maxCost，那么我们就循环将指针j向右移动，并且在移动过程中不断减少sum的值，直到sum \leq maxCost。然后我们更新答案，即ans = \max(ans, i - j + 1)。

最后返回答案即可。

时间复杂度O(n)，空间复杂度O(1)。其中n为字符串s的长度。

### **Java**

```java
class Solution {
    private int maxCost;
    private int[] f;
    private int n;

    public int equalSubstring(String s, String t, int maxCost) {
        n = s.length();
        f = new int[n + 1];
        this.maxCost = maxCost;
        for (int i = 0; i < n; ++i) {
            int x = Math.abs(s.charAt(i) - t.charAt(i));
            f[i + 1] = f[i] + x;
        }
        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r + 1) >>> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    private boolean check(int x) {
        for (int i = 0; i + x - 1 < n; ++i) {
            int j = i + x - 1;
            if (f[j + 1] - f[i] <= maxCost) {
                return true;
            }
        }
        return false;
    }
}
```

```java
class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        int n = s.length();
        int sum = 0;
        int ans = 0;
        for (int i = 0, j = 0; i < n; ++i) {
            sum += Math.abs(s.charAt(i) - t.charAt(i));
            while (sum > maxCost) {
                sum -= Math.abs(s.charAt(j) - t.charAt(j));
                ++j;
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}
```
