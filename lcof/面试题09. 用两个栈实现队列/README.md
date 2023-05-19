# [面试题 09. 用两个栈实现队列](https://leetcode.cn/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/)

## 题目描述

<p>用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 <code>appendTail</code> 和 <code>deleteHead</code> ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，<code>deleteHead</code>&nbsp;操作返回 -1 )</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>
[&quot;CQueue&quot;,&quot;appendTail&quot;,&quot;deleteHead&quot;,&quot;deleteHead&quot;]
[[],[3],[],[]]
<strong>输出：</strong>[null,null,3,-1]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>
[&quot;CQueue&quot;,&quot;deleteHead&quot;,&quot;appendTail&quot;,&quot;appendTail&quot;,&quot;deleteHead&quot;,&quot;deleteHead&quot;]
[[],[],[5],[2],[],[]]
<strong>输出：</strong>[null,-1,null,null,5,2]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= values &lt;= 10000</code></li>
	<li><code>最多会对&nbsp;appendTail、deleteHead 进行&nbsp;10000&nbsp;次调用</code></li>
</ul>

## 解法

**方法一：双栈**

我们可以使用两个栈来实现队列，其中一个栈 `stk1` 用来存储输入的元素，另一个栈 `stk2` 用来输出元素。

当调用 `appendTail()` 方法时，我们将元素压入 `stk1` 中。

当调用 `deleteHead()` 方法时，如果此时栈 `stk2` 为空，我们将栈 `stk1` 中的元素逐个弹出并压入栈 `stk2` 中，然后弹出栈 `stk2` 的栈顶元素即可。如果此时栈 `stk2` 不为空，我们直接弹出栈 `stk2` 的栈顶元素即可。如果两个栈都为空，说明队列中没有元素，返回 `-1`。

时间复杂度上，对于 `appendTail()` 方法，时间复杂度为 $O(1)$；对于 `deleteHead()` 方法，时间复杂度为 $O(n)$；空间复杂度为 $O(n)$。其中 $n$ 为队列中的元素个数。

<!-- tabs:start -->

### **Python3**



### **Java**

```java
class CQueue {
    private Deque<Integer> stk1 = new ArrayDeque<>();
    private Deque<Integer> stk2 = new ArrayDeque<>();

    public CQueue() {
    }

    public void appendTail(int value) {
        stk1.push(value);
    }

    public int deleteHead() {
        if (stk2.isEmpty()) {
            while (!stk1.isEmpty()) {
                stk2.push(stk1.pop());
            }
        }
        return stk2.isEmpty() ? -1 : stk2.pop();
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
```













### **TypeScript**











### **...**

```

```


