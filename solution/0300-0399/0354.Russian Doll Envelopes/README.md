# [354. 俄罗斯套娃信封问题](https://leetcode.cn/problems/russian-doll-envelopes)

[English Version](/solution/0300-0399/0354.Russian%20Doll%20Envelopes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二维整数数组 <code>envelopes</code> ，其中 <code>envelopes[i] = [w<sub>i</sub>, h<sub>i</sub>]</code> ，表示第 <code>i</code> 个信封的宽度和高度。</p>

<p>当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。</p>

<p>请计算 <strong>最多能有多少个</strong> 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。</p>

<p><strong>注意</strong>：不允许旋转信封。</p>
&nbsp;

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>envelopes = [[5,4],[6,4],[6,7],[2,3]]
<strong>输出：</strong>3
<strong>解释：</strong>最多信封的个数为 <code>3, 组合为: </code>[2,3] =&gt; [5,4] =&gt; [6,7]。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>envelopes = [[1,1],[1,1],[1,1]]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= envelopes.length &lt;= 10<sup>5</sup></code></li>
	<li><code>envelopes[i].length == 2</code></li>
	<li><code>1 &lt;= w<sub>i</sub>, h<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

按 w 进行升序排序，若 w 相同则按 h 降序排序。然后问题转换为求 h 数组的最长递增子序列长度。参考 [300. 最长递增子序列](/solution/0300-0399/0300.Longest%20Increasing%20Subsequence/README.md)。

**方法一：贪心 + 二分查找**

时间复杂度 O(nlogn)。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->



### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> { return a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]; });
        int n = envelopes.length;
        int[] d = new int[n + 1];
        d[1] = envelopes[0][1];
        int size = 1;
        for (int i = 1; i < n; ++i) {
            int x = envelopes[i][1];
            if (x > d[size]) {
                d[++size] = x;
            } else {
                int left = 1, right = size;
                while (left < right) {
                    int mid = (left + right) >> 1;
                    if (d[mid] >= x) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }
                int p = d[left] >= x ? left : 1;
                d[p] = x;
            }
        }
        return size;
    }
}
```









### **...**

```

```


