# [248. 中心对称数 III](https://leetcode.cn/problems/strobogrammatic-number-iii)

[English Version](/solution/0200-0299/0248.Strobogrammatic%20Number%20III/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个字符串 low 和 high 表示两个整数 <code>low</code>&nbsp;和 <code>high</code> ，其中&nbsp;<code>low &lt;= high</code>&nbsp;，返回 范围 <code>[low, high]</code>&nbsp;内的 <strong>「中心对称数」</strong>总数&nbsp;&nbsp;。</p>

<p><strong>中心对称数&nbsp;</strong>是一个数字在旋转了&nbsp;<code>180</code> 度之后看起来依旧相同的数字（或者上下颠倒地看）。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> low = "50", high = "100"
<strong>输出:</strong> 3 
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> low = "0", high = "0"
<strong>输出:</strong> 1
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong><meta charset="UTF-8" /></p>

<p><meta charset="UTF-8" /></p>

<ul>
	<li><code>1 &lt;= low.length, high.length &lt;= 15</code></li>
	<li><code>low</code>&nbsp;和&nbsp;<code>high</code>&nbsp;只包含数字</li>
	<li><code>low &lt;= high</code></li>
	<li><code>low</code>&nbsp;and&nbsp;<code>high</code>&nbsp;不包含任何前导零，除了零本身。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：递归**

若长度为 $1$，则中心对称数只有 $0, 1, 8$；若长度为 $2$，则中心对称数只有 $11, 69, 88, 96$。

我们设计递归函数 $dfs(u)$，其返回长度为 $u$ 的中心对称数。

若 $u$ 为 $0$，返回包含一个空串的列表，即 `[""]`；若 $u$ 为 $1$，返回列表 `["0", "1", "8"]`。

若 $u$ 大于 $1$，我们对长度为 $u - 2$ 的所有中心对称数进行遍历，对于每个中心对称数 $v$，在其左右两侧分别添加 $1, 8, 6, 9$，即可得到长度为 $u$ 的中心对称数。

注意，如果 $u\neq n$，我们还可以在中心对称数的左右两侧分别添加 $0$。

设 $low$ 和 $high$ 的长度分别为 $a$ 和 $b$。

接下来，我们在 $[a,..b]$ 范围内遍历所有长度，对于每个长度 $n$，我们获取所有中心对称数 $dfs(n)$，然后判断是否在 $[low, high]$ 范围内，若在，答案加一。

时间复杂度为 $O(2^{n+2}\times \log n)$。

相似题目：[247. 中心对称数 II](/solution/0200-0299/0247.Strobogrammatic%20Number%20II/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->



### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private static final int[][] PAIRS = {{1, 1}, {8, 8}, {6, 9}, {9, 6}};
    private int n;

    public int strobogrammaticInRange(String low, String high) {
        int a = low.length(), b = high.length();
        long l = Long.parseLong(low), r = Long.parseLong(high);
        int ans = 0;
        for (n = a; n <= b; ++n) {
            for (String s : dfs(n)) {
                long v = Long.parseLong(s);
                if (l <= v && v <= r) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    private List<String> dfs(int u) {
        if (u == 0) {
            return Collections.singletonList("");
        }
        if (u == 1) {
            return Arrays.asList("0", "1", "8");
        }
        List<String> ans = new ArrayList<>();
        for (String v : dfs(u - 2)) {
            for (var p : PAIRS) {
                ans.add(p[0] + v + p[1]);
            }
            if (u != n) {
                ans.add(0 + v + 0);
            }
        }
        return ans;
    }
}
```









### **...**

```

```


