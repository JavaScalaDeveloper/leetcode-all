# [707. 设计链表](https://leetcode.cn/problems/design-linked-list)

## 题目描述

<p>你可以选择使用单链表或者双链表，设计并实现自己的链表。</p>

<p>单链表中的节点应该具备两个属性：<code>val</code> 和 <code>next</code> 。<code>val</code> 是当前节点的值，<code>next</code> 是指向下一个节点的指针/引用。</p>

<p>如果是双向链表，则还需要属性&nbsp;<code>prev</code>&nbsp;以指示链表中的上一个节点。假设链表中的所有节点下标从 <strong>0</strong> 开始。</p>

<p>实现 <code>MyLinkedList</code> 类：</p>

<ul>
	<li><code>MyLinkedList()</code> 初始化 <code>MyLinkedList</code> 对象。</li>
	<li><code>int get(int index)</code> 获取链表中下标为 <code>index</code> 的节点的值。如果下标无效，则返回 <code>-1</code> 。</li>
	<li><code>void addAtHead(int val)</code> 将一个值为 <code>val</code> 的节点插入到链表中第一个元素之前。在插入完成后，新节点会成为链表的第一个节点。</li>
	<li><code>void addAtTail(int val)</code> 将一个值为 <code>val</code> 的节点追加到链表中作为链表的最后一个元素。</li>
	<li><code>void addAtIndex(int index, int val)</code> 将一个值为 <code>val</code> 的节点插入到链表中下标为 <code>index</code> 的节点之前。如果 <code>index</code> 等于链表的长度，那么该节点会被追加到链表的末尾。如果 <code>index</code> 比长度更大，该节点将 <strong>不会插入</strong> 到链表中。</li>
	<li><code>void deleteAtIndex(int index)</code> 如果下标有效，则删除链表中下标为 <code>index</code> 的节点。</li>
</ul>

<p><strong class="example">示例：</strong></p>

<pre>
<strong>输入</strong>
["MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get", "deleteAtIndex", "get"]
[[], [1], [3], [1, 2], [1], [1], [1]]
<strong>输出</strong>
[null, null, null, null, 2, null, 3]

<strong>解释</strong>
MyLinkedList myLinkedList = new MyLinkedList();
myLinkedList.addAtHead(1);
myLinkedList.addAtTail(3);
myLinkedList.addAtIndex(1, 2);    // 链表变为 1-&gt;2-&gt;3
myLinkedList.get(1);              // 返回 2
myLinkedList.deleteAtIndex(1);    // 现在，链表变为 1-&gt;3
myLinkedList.get(1);              // 返回 3
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= index, val &lt;= 1000</code></li>
	<li>请不要使用内置的 LinkedList 库。</li>
	<li>调用 <code>get</code>、<code>addAtHead</code>、<code>addAtTail</code>、<code>addAtIndex</code> 和 <code>deleteAtIndex</code> 的次数不超过 <code>2000</code> 。</li>
</ul>

## 解法

**方法一：指针引用实现单链表**

我们创建链表虚拟头节点 `dummy`，用变量 `cnt` 记录当前链表节点个数。

具体的方法如下：

-   `get(index)`：遍历链表，找到第 `index` 个节点，返回其值，如果不存在，返回-1。时间复杂度O(n)。
-   `addAtHead(val)`：创建新节点，将其插入到虚拟头节点后面。时间复杂度O(1)。
-   `addAtTail(val)`：创建新节点，将其插入到链表尾部。时间复杂度O(n)。
-   `addAtIndex(index, val)`：如果 `index` 等于链表长度，则该节点将附加到链表的末尾。如果 `index` 大于链表长度，则不会插入节点。如果 `index` 小于0，则在头部插入节点。否则，遍历链表，找到第 `index` 个节点的前一个节点，将新节点插入到该节点后面。时间复杂度O(n)。
-   `deleteAtIndex(index)`：如果索引 `index` 有效，则删除链表中的第 `index` 个节点。否则，不做任何操作。时间复杂度O(n)。

时间复杂度见具体的方法说明。其中n为链表长度。

注意：LeetCode 平台已经内置 ListNode 单链表节点类，可以直接使用。

**方法二：静态数组实现单链表**

在方法一中，我们使用了指针引用的方式，每次动态创建一个链表节点。在链表节点数量达到10^5甚至更大时，频繁执行 new 操作，会大大增加程序的执行耗时。

因此，我们可以使用静态数组来实现单链表，预先申请一块大小略大于数据范围的内存空间，每次插入节点时，从数组中取出一个空闲的位置，将新节点插入到该位置，同时更新该位置的前驱和后继节点的指针引用。

我们定义以下几个变量，其中：

-   `head` 存放链表头节点的索引，初始时指向-1。
-   `e` 存放链表所有节点的值（预先申请）。
-   `ne` 存放链表所有节点的 `next` 指针（预先申请）。
-   `idx` 指向当前可分配的节点索引，初始时指向索引0。
-   `cnt` 记录当前链表节点个数，初始时为0。

具体操作可参考以下代码。时间复杂度与方法一相同。

### **Java**

```java
class MyLinkedList {
    private ListNode dummy = new ListNode();
    private int cnt;

    public MyLinkedList() {

    }

    public int get(int index) {
        if (index < 0 || index >= cnt) {
            return -1;
        }
        var cur = dummy.next;
        while (index-- > 0) {
            cur = cur.next;
        }
        return cur.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(cnt, val);
    }

    public void addAtIndex(int index, int val) {
        if (index > cnt) {
            return;
        }
        var pre = dummy;
        while (index-- > 0) {
            pre = pre.next;
        }
        pre.next = new ListNode(val, pre.next);
        ++cnt;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= cnt) {
            return;
        }
        var pre = dummy;
        while (index-- > 0) {
            pre = pre.next;
        }
        var t = pre.next;
        pre.next = t.next;
        t.next = null;
        --cnt;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
```

```java
class MyLinkedList {
    private int[] e = new int[1010];
    private int[] ne = new int[1010];
    private int head = -1;
    private int idx;
    private int cnt;

    public MyLinkedList() {
    }

    public int get(int index) {
        if (index < 0 || index >= cnt) {
            return -1;
        }
        int i = head;
        while (index-- > 0) {
            i = ne[i];
        }
        return e[i];
    }

    public void addAtHead(int val) {
        e[idx] = val;
        ne[idx] = head;
        head = idx++;
        ++cnt;
    }

    public void addAtTail(int val) {
        addAtIndex(cnt, val);
    }

    public void addAtIndex(int index, int val) {
        if (index > cnt) {
            return;
        }
        if (index <= 0) {
            addAtHead(val);
            return;
        }
        int i = head;
        while (--index > 0) {
            i = ne[i];
        }
        e[idx] = val;
        ne[idx] = ne[i];
        ne[i] = idx++;
        ++cnt;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= cnt) {
            return;
        }
        --cnt;
        if (index == 0) {
            head = ne[head];
            return;
        }
        int i = head;
        while (--index > 0) {
            i = ne[i];
        }
        ne[i] = ne[ne[i]];
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
```
