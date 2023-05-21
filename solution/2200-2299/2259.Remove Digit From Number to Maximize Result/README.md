# [2259. 移除指定数字得到的最大结果](https://leetcode.cn/problems/remove-digit-from-number-to-maximize-result)

## 题目描述

<p>给你一个表示某个正整数的字符串 <code>number</code> 和一个字符 <code>digit</code> 。</p>

<p>从 <code>number</code> 中 <strong>恰好</strong> 移除 <strong>一个</strong> 等于&nbsp;<code>digit</code> 的字符后，找出并返回按 <strong>十进制</strong> 表示 <strong>最大</strong> 的结果字符串。生成的测试用例满足 <code>digit</code> 在 <code>number</code> 中出现至少一次。</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>number = "123", digit = "3"
<strong>输出：</strong>"12"
<strong>解释：</strong>"123" 中只有一个 '3' ，在移除 '3' 之后，结果为 "12" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>number = "1231", digit = "1"
<strong>输出：</strong>"231"
<strong>解释：</strong>可以移除第一个 '1' 得到 "231" 或者移除第二个 '1' 得到 "123" 。
由于 231 &gt; 123 ，返回 "231" 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>number = "551", digit = "5"
<strong>输出：</strong>"51"
<strong>解释：</strong>可以从 "551" 中移除第一个或者第二个 '5' 。
两种方案的结果都是 "51" 。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= number.length &lt;= 100</code></li>
	<li><code>number</code> 由数字 <code>'1'</code> 到 <code>'9'</code> 组成</li>
	<li><code>digit</code> 是 <code>'1'</code> 到 <code>'9'</code> 中的一个数字</li>
	<li><code>digit</code> 在 <code>number</code> 中出现至少一次</li>
</ul>

## 解法

**方法一：暴力枚举**

我们可以枚举字符串number的所有位置i，如果number[i] = digit，那么我们取number的前缀number[0:i]和后缀number[i+1:]拼接起来，即为移除number[i]后的结果。我们取所有可能的结果中最大的即可。

时间复杂度O(n^2)，空间复杂度O(n)。其中n为字符串number的长度。

**方法二：贪心**

我们可以枚举字符串number的所有位置i，如果number[i] = digit，记录digit最后一次出现的位置last，并且如果i + 1 \lt n且number[i] \lt number[i + 1]，那么我们可以直接返回number[0:i] + number[i+1:]，即为移除number[i]后的结果。这是因为如果number[i] < number[i + 1]，那么移除number[i]后，结果一定会更大。

遍历结束，我们返回number[0:last] + number[last+1:]即可。

时间复杂度O(n)，空间复杂度O(n)。其中n为字符串number的长度。

### **Java**

```java
class Solution {
    public String removeDigit(String number, char digit) {
        String ans = "0";
        for (int i = 0, n = number.length(); i < n; ++i) {
            char d = number.charAt(i);
            if (d == digit) {
                String t = number.substring(0, i) + number.substring(i + 1);
                if (ans.compareTo(t) < 0) {
                    ans = t;
                }
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public String removeDigit(String number, char digit) {
        int last = -1;
        int n = number.length();
        for (int i = 0; i < n; ++i) {
            char d = number.charAt(i);
            if (d == digit) {
                last = i;
                if (i + 1 < n && d < number.charAt(i + 1)) {
                    break;
                }
            }
        }
        return number.substring(0, last) + number.substring(last + 1);
    }
}
```
