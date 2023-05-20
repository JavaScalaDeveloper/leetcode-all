# [2487. 从链表中移除节点](https://leetcode.cn/problems/remove-nodes-from-linked-list)

## 题目描述

<p>给你一个链表的头节点 <code>head</code> 。</p>

<p>对于列表中的每个节点 <code>node</code> ，如果其右侧存在一个具有 <strong>严格更大</strong> 值的节点，则移除 <code>node</code> 。</p>

<p>返回修改后链表的头节点<em> </em><code>head</code><em> </em>。</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2400-2499/2487.Remove%20Nodes%20From%20Linked%20List/images/drawio.png" style="width: 631px; height: 51px;" /></p>

<pre>
<strong>输入：</strong>head = [5,2,13,3,8]
<strong>输出：</strong>[13,8]
<strong>解释：</strong>需要移除的节点是 5 ，2 和 3 。
- 节点 13 在节点 5 右侧。
- 节点 13 在节点 2 右侧。
- 节点 8 在节点 3 右侧。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>head = [1,1,1,1]
<strong>输出：</strong>[1,1,1,1]
<strong>解释：</strong>每个节点的值都是 1 ，所以没有需要移除的节点。
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li>给定列表中的节点数目在范围 <code>[1, 10<sup>5</sup>]</code> 内</li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

**方法一：单调栈模拟**

我们可以先将链表中的节点值存入数组，然后遍历数组，维护一个从栈底到栈顶单调递减的栈，如果当前元素比栈顶元素大，则将栈顶元素出栈，直到当前元素小于等于栈顶元素，将当前元素入栈。最后将栈中的元素逆序，构造得到的链表即为答案。

时间复杂度为 $O(n)$，空间复杂度为 $O(n)$。

### **Java**

```java
class Solution {
    public ListNode removeNodes(ListNode head) {
        List<Integer> nums = new ArrayList<>();
        while (head != null) {
            nums.add(head.val);
            head = head.next;
        }
        Deque<Integer> stk = new ArrayDeque<>();
        for (int v : nums) {
            while (!stk.isEmpty() && stk.peek() < v) {
                stk.pop();
            }
            stk.push(v);
        }
        ListNode dummy = new ListNode();
        head = dummy;
        while (!stk.isEmpty()) {
            head.next = new ListNode(stk.pollLast());
            head = head.next;
        }
        return dummy.next;
    }
}
```
