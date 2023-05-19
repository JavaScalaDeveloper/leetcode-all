# [面试题 30. 包含 min 函数的栈](https://leetcode.cn/problems/bao-han-minhan-shu-de-zhan-lcof/)

## 题目描述

<p>定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。</p>

<p>&nbsp;</p>

<p><strong>示例:</strong></p>

<pre>MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.min();   --&gt; 返回 -3.
minStack.pop();
minStack.top();      --&gt; 返回 0.
minStack.min();   --&gt; 返回 -2.
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li>各函数的调用总次数不超过 20000 次</li>
</ol>

<p>&nbsp;</p>

<p>注意：本题与主站 155 题相同：<a href="https://leetcode.cn/problems/min-stack/">https://leetcode.cn/problems/min-stack/</a></p>

**方法一：双栈**

我们用两个栈来实现，其中`stk1` 用来存储数据，`stk2` 用来存储当前栈中的最小值。初始时，`stk2` 中存储一个极大值。

-   当我们向栈中压入一个元素 `x` 时，我们将 `x` 压入 `stk1`，并将 `min(x, stk2[-1])` 压入 `stk2`。
-   当我们从栈中弹出一个元素时，我们将 `stk1` 和 `stk2` 的栈顶元素都弹出。
-   当我们要获取当前栈中的栈顶元素时，我们只需要返回 `stk1` 的栈顶元素即可。
-   当我们要获取当前栈中的最小值时，我们只需要返回 `stk2` 的栈顶元素即可。

时间复杂度：对于每个操作，时间复杂度均为 $O(1)$，空间复杂度 $O(n)$。

### **Java**

```java
class MinStack {
    private Deque<Integer> stk1 = new ArrayDeque<>();
    private Deque<Integer> stk2 = new ArrayDeque<>();

    /** initialize your data structure here. */
    public MinStack() {
        stk2.push(Integer.MAX_VALUE);
    }

    public void push(int x) {
        stk1.push(x);
        stk2.push(Math.min(x, stk2.peek()));
    }

    public void pop() {
        stk1.pop();
        stk2.pop();
    }

    public int top() {
        return stk1.peek();
    }

    public int getMin() {
        return stk2.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
```
