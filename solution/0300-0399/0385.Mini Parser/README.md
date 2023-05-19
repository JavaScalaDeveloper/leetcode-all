# [385. 迷你语法分析器](https://leetcode.cn/problems/mini-parser)

[English Version](/solution/0300-0399/0385.Mini%20Parser/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串 s 表示一个整数嵌套列表，实现一个解析它的语法分析器并返回解析的结果&nbsp;<code>NestedInteger</code> 。</p>

<p>列表中的每个元素只可能是整数或整数嵌套列表</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "324",
<strong>输出：</strong>324
<strong>解释：</strong>你应该返回一个 NestedInteger 对象，其中只包含整数值 324。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "[123,[456,[789]]]",
<strong>输出：</strong>[123,[456,[789]]]
<strong>解释：</strong>返回一个 NestedInteger 对象包含一个有两个元素的嵌套列表：
1. 一个 integer 包含值 123
2. 一个包含两个元素的嵌套列表：
    i.  一个 integer 包含值 456
    ii. 一个包含一个元素的嵌套列表
         a. 一个 integer 包含值 789
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>s</code> 由数字、方括号&nbsp;<code>"[]"</code>、负号&nbsp;<code>'-'</code>&nbsp;、逗号&nbsp;<code>','</code>组成</li>
	<li>用例保证&nbsp;<code>s</code> 是可解析的&nbsp;<code>NestedInteger</code></li>
	<li>输入中的所有值的范围是&nbsp;<code>[-10<sup>6</sup>, 10<sup>6</sup>]</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：递归**

我们首先判断字符串 $s$ 是否为空或是一个空列表，如果是的话，直接返回一个空的 `NestedInteger` 即可。如果 $s$ 是一个整数，我们直接返回一个包含这个整数的 `NestedInteger`。否则，我们从左到右遍历字符串 $s$，如果当前深度为 $0$，并且遇到了逗号或者字符串 $s$ 的末尾，则我们截取出一个子串并递归调用函数解析该子串，将返回值加入到列表中。否则，如果当前遇到了左括号，我们将深度加 $1$，并继续遍历。如果遇到了右括号，我们将深度减 $1$，继续遍历。

遍历结束后，返回答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是字符串 $s$ 的长度。

**方法二：栈**

我们可以使用栈来模拟递归的过程。

我们首先判断字符串 $s$ 是否是一个整数，如果是，直接返回一个包含这个整数的 `NestedInteger`。否则，我们从左到右遍历字符串 $s$，对于当前遍历到的字符 $c$：

-   如果 $c$ 是负号，我们将负号标识置为 `true`；
-   如果 $c$ 是数字，我们将数字加入到当前数字 $x$ 中，其中 $x$ 的初始值为 $0$；
-   如果 $c$ 是左括号，我们将一个新的 `NestedInteger` 压入栈中；
-   如果 $c$ 是右括号或者逗号，我们判断当前字符的前一个字符是否是数字，如果是，我们根据负号标识将当前数字 $x$ 加入到栈顶的 `NestedInteger` 中，然后将负号标识置为 `false`，当前数字 $x$ 重置为 $0$。如果 $c$ 是右括号，并且当前栈的大小大于 $1$，我们将栈顶的 `NestedInteger` 出栈，将其加入到栈顶的 `NestedInteger` 中。

遍历结束后，返回栈顶的 `NestedInteger` 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是字符串 $s$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->





### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution {
    public NestedInteger deserialize(String s) {
        if ("".equals(s) || "[]".equals(s)) {
            return new NestedInteger();
        }
        if (s.charAt(0) != '[') {
            return new NestedInteger(Integer.parseInt(s));
        }
        NestedInteger ans = new NestedInteger();
        int depth = 0;
        for (int i = 1, j = 1; i < s.length(); ++i) {
            if (depth == 0 && (s.charAt(i) == ',' || i == s.length() - 1)) {
                ans.add(deserialize(s.substring(j, i)));
                j = i + 1;
            } else if (s.charAt(i) == '[') {
                ++depth;
            } else if (s.charAt(i) == ']') {
                --depth;
            }
        }
        return ans;
    }
}
```

```java
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution {
    public NestedInteger deserialize(String s) {
        if (s.charAt(0) != '[') {
            return new NestedInteger(Integer.parseInt(s));
        }
        Deque<NestedInteger> stk = new ArrayDeque<>();
        int x = 0;
        boolean neg = false;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '-') {
                neg = true;
            } else if (Character.isDigit(c)) {
                x = x * 10 + c - '0';
            } else if (c == '[') {
                stk.push(new NestedInteger());
            } else if (c == ',' || c == ']') {
                if (Character.isDigit(s.charAt(i - 1))) {
                    if (neg) {
                        x = -x;
                    }
                    stk.peek().add(new NestedInteger(x));
                }
                x = 0;
                neg = false;
                if (c == ']' && stk.size() > 1) {
                    NestedInteger t = stk.pop();
                    stk.peek().add(t);
                }
            }
        }
        return stk.peek();
    }
}
```













### **TypeScript**





### **...**

```

```


