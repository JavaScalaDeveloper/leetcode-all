# [面试题 59 - II. 队列的最大值](https://leetcode.cn/problems/dui-lie-de-zui-da-zhi-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

<p>请定义一个队列并实现函数 <code>max_value</code> 得到队列里的最大值，要求函数<code>max_value</code>、<code>push_back</code> 和 <code>pop_front</code> 的<strong>均摊</strong>时间复杂度都是O(1)。</p>

<p>若队列为空，<code>pop_front</code> 和 <code>max_value</code>&nbsp;需要返回 -1</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入:</strong> 
[&quot;MaxQueue&quot;,&quot;push_back&quot;,&quot;push_back&quot;,&quot;max_value&quot;,&quot;pop_front&quot;,&quot;max_value&quot;]
[[],[1],[2],[],[],[]]
<strong>输出:&nbsp;</strong>[null,null,null,2,1,2]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入:</strong> 
[&quot;MaxQueue&quot;,&quot;pop_front&quot;,&quot;max_value&quot;]
[[],[],[]]
<strong>输出:&nbsp;</strong>[null,-1,-1]
</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<ul>
	<li><code>1 &lt;= push_back,pop_front,max_value的总操作数&nbsp;&lt;= 10000</code></li>
	<li><code>1 &lt;= value &lt;= 10^5</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双队列**

我们维护两个队列 $q_1$ 和 $q_2$，其中 $q_1$ 用于存储所有元素，而 $q_2$ 用于存储当前队列中的最大值。

当获取队列中的最大值时，如果队列 $q_2$ 不为空，则队列中的最大值即为 $q_2$ 的队首元素；否则队列为空，返回 $-1$。

当向队列中添加元素时，我们需要将 $q_2$ 弹出所有队尾元素小于当前元素的元素，然后将当前元素添加到 $q_2$ 的队尾，最后将当前元素添加到 $q_1$ 的队尾。

当从队列中弹出元素时，如果 $q_1$ 为空，则返回 $-1$；否则，如果 $q_1$ 的队首元素等于 $q_2$ 的队首元素，则将 $q_2$ 的队首元素弹出，然后将 $q_1$ 的队首元素弹出；否则，只将 $q_1$ 的队首元素弹出。

以上操作的时间复杂度均为 $O(1)$，空间复杂度为 $O(n)$。其中 $n$ 为队列中的元素个数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->



### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class MaxQueue {
    private Deque<Integer> q1 = new ArrayDeque<>();
    private Deque<Integer> q2 = new ArrayDeque<>();

    public MaxQueue() {
    }

    public int max_value() {
        return q2.isEmpty() ? -1 : q2.peek();
    }

    public void push_back(int value) {
        while (!q2.isEmpty() && q2.peekLast() < value) {
            q2.pollLast();
        }
        q1.offer(value);
        q2.offer(value);
    }

    public int pop_front() {
        if (q1.isEmpty()) {
            return -1;
        }
        int ans = q1.poll();
        if (q2.peek() == ans) {
            q2.poll();
        }
        return ans;
    }
}

/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */
```













### **TypeScript**











### **...**

```

```


