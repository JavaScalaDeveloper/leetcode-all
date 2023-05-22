# [2110. 股票平滑下跌阶段的数目](https://leetcode.cn/problems/number-of-smooth-descent-periods-of-a-stock)

## 题目描述

<p>给你一个整数数组&nbsp;<code>prices</code>&nbsp;，表示一支股票的历史每日股价，其中&nbsp;<code>prices[i]</code>&nbsp;是这支股票第&nbsp;<code>i</code>&nbsp;天的价格。</p>

<p>一个 <strong>平滑下降的阶段</strong>&nbsp;定义为：对于&nbsp;<strong>连续一天或者多天</strong>&nbsp;，每日股价都比 <strong>前一日股价恰好少 </strong><code>1</code>&nbsp;，这个阶段第一天的股价没有限制。</p>

<p>请你返回 <strong>平滑下降阶段</strong>&nbsp;的数目。</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>prices = [3,2,1,4]
<b>输出：</b>7
<b>解释：</b>总共有 7 个平滑下降阶段：
[3], [2], [1], [4], [3,2], [2,1] 和 [3,2,1]
注意，仅一天按照定义也是平滑下降阶段。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>prices = [8,6,7,7]
<b>输出：</b>4
<b>解释：</b>总共有 4 个连续平滑下降阶段：[8], [6], [7] 和 [7]
由于 8 - 6 ≠ 1 ，所以 [8,6] 不是平滑下降阶段。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>prices = [1]
<b>输出：</b>1
<b>解释：</b>总共有 1 个平滑下降阶段：[1]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= prices.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= prices[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

**方法一：双指针**

我们定义一个答案变量 `ans`，初始值为0。

接下来，我们使用双指针i和j，分别指向当前平滑下降阶段的第一天和最后一天的下一天。初始时i = 0,j = 0。

从左到右遍历数组 `prices`，对于每个位置i，我们将j向右移动，直到j到达数组末尾或者prices[j - 1] - prices[j] \neq 1为止。此时，cnt = j - i即为当前平滑下降阶段的长度，我们累加\frac{(1 + cnt) × cnt}{2}到答案变量 `ans` 中。接下来将i更新为j，继续遍历。

遍历结束后，返回答案变量 `ans` 即可。

时间复杂度O(n)，空间复杂度O(1)。其中n为数组 `prices` 的长度。

### **Java**

```java
class Solution {
    public long getDescentPeriods(int[] prices) {
        long ans = 0;
        int n = prices.length;
        for (int i = 0, j = 0; i < n; i = j) {
            j = i + 1;
            while (j < n && prices[j - 1] - prices[j] == 1) {
                ++j;
            }
            int cnt = j - i;
            ans += (1L + cnt) * cnt / 2;
        }
        return ans;
    }
}
```
