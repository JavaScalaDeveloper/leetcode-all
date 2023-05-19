# [剑指 Offer II 035. 最小时间差](https://leetcode.cn/problems/569nqc)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个 24 小时制（小时:分钟 <strong>&quot;HH:MM&quot;</strong>）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>timePoints = [&quot;23:59&quot;,&quot;00:00&quot;]
<strong>输出：</strong>1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>timePoints = [&quot;00:00&quot;,&quot;23:59&quot;,&quot;00:00&quot;]
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= timePoints &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>timePoints[i]</code> 格式为 <strong>&quot;HH:MM&quot;</strong></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 539&nbsp;题相同：&nbsp;<a href="https://leetcode.cn/problems/minimum-time-difference/">https://leetcode.cn/problems/minimum-time-difference/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

我们注意到，时间点最多只有 `24 * 60` 个，因此，当 timePoints 长度超过 `24 * 60`，说明有重复的时间点，提前返回 0。

接下来：

首先，遍历时间列表，将其转换为“分钟制”列表 `mins`，比如，对于时间点 `13:14`，将其转换为 `13 * 60 + 14`。

接着将“分钟制”列表按升序排列，然后将此列表的最小时间 `mins[0]` 加上 `24 * 60` 追加至列表尾部，用于处理最大值、最小值的差值这种特殊情况。

最后遍历“分钟制”列表，找出相邻两个时间的最小值即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->



### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findMinDifference(List<String> timePoints) {
        if (timePoints.size() > 24 * 60) {
            return 0;
        }
        List<Integer> mins = new ArrayList<>();
        for (String t : timePoints) {
            String[] time = t.split(":");
            mins.add(Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]));
        }
        Collections.sort(mins);
        mins.add(mins.get(0) + 24 * 60);
        int res = 24 * 60;
        for (int i = 1; i < mins.size(); ++i) {
            res = Math.min(res, mins.get(i) - mins.get(i - 1));
        }
        return res;
    }
}
```









### **...**

```

```


