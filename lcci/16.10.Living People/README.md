# [面试题 16.10. 生存人数](https://leetcode.cn/problems/living-people-lcci)

[English Version](/lcci/16.10.Living%20People/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定N个人的出生年份和死亡年份，第<code>i</code>个人的出生年份为<code>birth[i]</code>，死亡年份为<code>death[i]</code>，实现一个方法以计算生存人数最多的年份。</p>
<p>你可以假设所有人都出生于1900年至2000年（含1900和2000）之间。如果一个人在某一年的任意时期都处于生存状态，那么他们应该被纳入那一年的统计中。例如，生于1908年、死于1909年的人应当被列入1908年和1909年的计数。</p>
<p>如果有多个年份生存人数相同且均为最大值，输出其中最小的年份。</p>
<p><strong>示例：</strong></p>
<pre><strong>输入：</strong>
birth = {1900, 1901, 1950}
death = {1948, 1951, 2000}
<strong>输出：</strong> 1901
</pre>
<p><strong>提示：</strong></p>
<ul>
<li><code>0 < birth.length == death.length <= 10000</code></li>
<li><code>birth[i] <= death[i]</code></li>
</ul>

## 解法

不在乎某个区间，而是某一年的最多存活人数。

可以使用哈希表来统计每年的存活人数，当 `birth[i] >= year && year <= death[i]`，该年份的存活人数加一。

> 只有 `birth` 和 `death` 当中的出现过的年份才是有效年份，或者说，可能成为返回值的年份。

题目当中已说明年份范围是 `1900 ~ 2000`，对此也可以使用数组进行计数（`year - 1900`）。

### **Java**

```java
class Solution {
    public int maxAliveYear(int[] birth, int[] death) {
        int[] years = new int[101];
        int n = birth.length;
        for (int i = 0; i < n; ++i) {
            int start = birth[i] - 1900;
            int end = death[i] - 1900;
            for (int j = start; j <= end; ++j) {
                ++years[j];
            }
        }
        int max = years[0];
        int res = 0;
        for (int i = 1; i < 101; ++i) {
            if (years[i] > max) {
                max = years[i];
                res = i;
            }
        }
        return 1900 + res;
    }
}
```

### **TypeScript**
