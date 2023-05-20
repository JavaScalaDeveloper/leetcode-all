# [1172. 餐盘栈](https://leetcode.cn/problems/dinner-plate-stacks)

## 题目描述

<p>我们把无限数量 &infin; 的栈排成一行，按从左到右的次序从 0 开始编号。每个栈的的最大容量&nbsp;<code>capacity</code> 都相同。</p>

<p>实现一个叫「餐盘」的类&nbsp;<code>DinnerPlates</code>：</p>

<ul>
	<li><code>DinnerPlates(int capacity)</code>&nbsp;- 给出栈的最大容量&nbsp;<code>capacity</code>。</li>
	<li><code>void push(int val)</code>&nbsp;- 将给出的正整数&nbsp;<code>val</code>&nbsp;推入&nbsp;<strong>从左往右第一个&nbsp;</strong>没有满的栈。</li>
	<li><code>int pop()</code>&nbsp;- 返回&nbsp;<strong>从右往左第一个&nbsp;</strong>非空栈顶部的值，并将其从栈中删除；如果所有的栈都是空的，请返回&nbsp;<code>-1</code>。</li>
	<li><code>int popAtStack(int index)</code>&nbsp;- 返回编号&nbsp;<code>index</code>&nbsp;的栈顶部的值，并将其从栈中删除；如果编号&nbsp;<code>index</code>&nbsp;的栈是空的，请返回 <code>-1</code>。</li>
</ul>

<p><strong>示例：</strong></p>

<pre><strong>输入： </strong>
[&quot;DinnerPlates&quot;,&quot;push&quot;,&quot;push&quot;,&quot;push&quot;,&quot;push&quot;,&quot;push&quot;,&quot;popAtStack&quot;,&quot;push&quot;,&quot;push&quot;,&quot;popAtStack&quot;,&quot;popAtStack&quot;,&quot;pop&quot;,&quot;pop&quot;,&quot;pop&quot;,&quot;pop&quot;,&quot;pop&quot;]
[[2],[1],[2],[3],[4],[5],[0],[20],[21],[0],[2],[],[],[],[],[]]
<strong>输出：</strong>
[null,null,null,null,null,null,2,null,null,20,21,5,4,3,1,-1]

<strong>解释：</strong>
DinnerPlates D = DinnerPlates(2);  // 初始化，栈最大容量 capacity = 2
D.push(1);
D.push(2);
D.push(3);
D.push(4);
D.push(5);         // 栈的现状为：    2 &nbsp;4
&nbsp;                                   1 &nbsp;3 &nbsp;5
                                    ﹈ ﹈ ﹈
D.popAtStack(0);   // 返回 2。栈的现状为：     &nbsp;4
            &nbsp;                             1 &nbsp;3 &nbsp;5
                                          ﹈ ﹈ ﹈
D.push(20);        // 栈的现状为：  20  4
&nbsp;                                  1 &nbsp;3 &nbsp;5
                                   ﹈ ﹈ ﹈
D.push(21);        // 栈的现状为：  20  4 21
&nbsp;                                  1 &nbsp;3 &nbsp;5
                                   ﹈ ﹈ ﹈
D.popAtStack(0);   // 返回 20。栈的现状为：       4 21
             &nbsp;                              1 &nbsp;3 &nbsp;5
                                            ﹈ ﹈ ﹈
D.popAtStack(2);   // 返回 21。栈的现状为：       4
             &nbsp;                              1 &nbsp;3 &nbsp;5
                                            ﹈ ﹈ ﹈ 
D.pop()            // 返回 5。栈的现状为：        4
             &nbsp;                              1 &nbsp;3 
                                            ﹈ ﹈  
D.pop()            // 返回 4。栈的现状为：    1  3 
                                           ﹈ ﹈   
D.pop()            // 返回 3。栈的现状为：    1 
                                           ﹈   
D.pop()            // 返回 1。现在没有栈。
D.pop()            // 返回 -1。仍然没有栈。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= capacity&nbsp;&lt;= 20000</code></li>
	<li><code>1 &lt;= val&nbsp;&lt;= 20000</code></li>
	<li><code>0 &lt;= index&nbsp;&lt;= 100000</code></li>
	<li>最多会对&nbsp;<code>push</code>，<code>pop</code>，和&nbsp;<code>popAtStack</code>&nbsp;进行 <code>200000</code> 次调用。</li>
</ul>

## 解法

**方法一：栈数组 + 有序集合**

我们定义以下数据结构或变量：

-   `capacity`：每个栈的容量；
-   `stacks`：栈数组，用于存储所有的栈，其中每个栈的最大容量都是 `capacity`；
-   `not_full`：有序集合，用于存储所有未满的栈在栈数组中的下标。

对于 `push(val)` 操作：

-   我们首先判断 `not_full` 是否为空，如果为空，则说明没有未满的栈，需要新建一个栈，然后将 `val` 压入该栈中，此时判断容量 `capacity` 是否大于 $1$，如果大于 $1$，则将该栈的下标加入 `not_full` 中。
-   如果 `not_full` 不为空，则说明有未满的栈，我们取出 `not_full` 中最小的下标 `index`，将 `val` 压入 `stacks[index]` 中，此时如果 `stacks[index]` 的容量等于 `capacity`，则将 `index` 从 `not_full` 中删除。

对于 `popAtStack(index)` 操作：

-   我们首先判断 `index` 是否在 `stacks` 的下标范围内，如果不在，则直接返回 $-1$。如果 `stacks[index]` 为空，同样直接返回 $-1$。
-   如果 `stacks[index]` 不为空，则弹出 `stacks[index]` 的栈顶元素 `val`。如果 `index` 等于 `stacks` 的长度减 $1$，则说明 `stacks[index]` 是最后一个栈，如果为空，我们循环将最后一个栈的下标从 `not_full` 中移出，并且在栈数组 `stacks` 中移除最后一个栈，直到最后一个栈不为空、或者栈数组 `stacks` 为空为止。否则，如果 `stacks[index]` 不是最后一个栈，我们将 `index` 加入 `not_full` 中。
-   最后返回 `val`。

对于 `pop()` 操作：

-   我们直接调用 `popAtStack(stacks.length - 1)` 即可。

时间复杂度 $(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为操作次数。

### **Java**

```java
class DinnerPlates {
    private int capacity;
    private List<Deque<Integer>> stacks = new ArrayList<>();
    private TreeSet<Integer> notFull = new TreeSet<>();

    public DinnerPlates(int capacity) {
        this.capacity = capacity;
    }

    public void push(int val) {
        if (notFull.isEmpty()) {
            stacks.add(new ArrayDeque<>());
            stacks.get(stacks.size() - 1).push(val);
            if (capacity > 1) {
                notFull.add(stacks.size() - 1);
            }
        } else {
            int index = notFull.first();
            stacks.get(index).push(val);
            if (stacks.get(index).size() == capacity) {
                notFull.pollFirst();
            }
        }
    }

    public int pop() {
        return popAtStack(stacks.size() - 1);
    }

    public int popAtStack(int index) {
        if (index < 0 || index >= stacks.size() || stacks.get(index).isEmpty()) {
            return -1;
        }
        int val = stacks.get(index).pop();
        if (index == stacks.size() - 1 && stacks.get(stacks.size() - 1).isEmpty()) {
            while (!stacks.isEmpty() && stacks.get(stacks.size() - 1).isEmpty()) {
                notFull.remove(stacks.size() - 1);
                stacks.remove(stacks.size() - 1);
            }
        } else {
            notFull.add(index);
        }
        return val;
    }
}

/**
 * Your DinnerPlates object will be instantiated and called as such:
 * DinnerPlates obj = new DinnerPlates(capacity);
 * obj.push(val);
 * int param_2 = obj.pop();
 * int param_3 = obj.popAtStack(index);
 */
```
