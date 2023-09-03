# [剑指 Offer II 026. 重排链表](https://leetcode.cn/problems/LGjMqU)

## 题目描述



<p>给定一个单链表 <code>L</code><em> </em>的头节点 <code>head</code> ，单链表 <code>L</code> 表示为：</p>

<p><code>&nbsp;L<sub>0&nbsp;</sub>&rarr; L<sub>1&nbsp;</sub>&rarr; &hellip; &rarr; L<sub>n-1&nbsp;</sub>&rarr; L<sub>n&nbsp;</sub></code><br />
请将其重新排列后变为：</p>

<p><code>L<sub>0&nbsp;</sub>&rarr;&nbsp;L<sub>n&nbsp;</sub>&rarr;&nbsp;L<sub>1&nbsp;</sub>&rarr;&nbsp;L<sub>n-1&nbsp;</sub>&rarr;&nbsp;L<sub>2&nbsp;</sub>&rarr;&nbsp;L<sub>n-2&nbsp;</sub>&rarr; &hellip;</code></p>

<p>不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20026.%20%E9%87%8D%E6%8E%92%E9%93%BE%E8%A1%A8/images/1626420311-PkUiGI-image.png" style="width: 240px; " /></p>

<pre>
<strong>输入: </strong>head = [1,2,3,4]
<strong>输出: </strong>[1,4,2,3]</pre>

<p><strong>示例 2:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcof2/%E5%89%91%E6%8C%87%20Offer%20II%20026.%20%E9%87%8D%E6%8E%92%E9%93%BE%E8%A1%A8/images/1626420320-YUiulT-image.png" style="width: 320px; " /></p>

<pre>
<strong>输入: </strong>head = [1,2,3,4,5]
<strong>输出: </strong>[1,5,2,4,3]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li>链表的长度范围为 <code>[1, 5 * 10<sup>4</sup>]</code></li>
	<li><code>1 &lt;= node.val &lt;= 1000</code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 143&nbsp;题相同：<a href="https://leetcode.cn/problems/reorder-list/">https://leetcode.cn/problems/reorder-list/</a>&nbsp;</p>

## 解法

相当于这 3 道问题，只需要 5 行代码将它们组合：

-   [链表的中间结点](/solution/0800-0899/0876.Middle%20of%20the%20Linked%20List/README.md)
-   [反转链表](/solution/0200-0299/0206.Reverse%20Linked%20List/README.md)
-   合并两个链表

### **Java**

```java
class Solution {
    public void reorderList(ListNode head) {
        ListNode mid = middleNode(head);
        ListNode tmp = mid.next;
        mid.next = null;
        tmp = reverseList(tmp);
        head = mergeTwoLists(head, tmp);
    }

    private ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverseList(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            cur.next = l1;
            l1 = l1.next;
            cur = cur.next;
            cur.next = l2;
            l2 = l2.next;
            cur = cur.next;
        }
        cur.next = l1 != null ? l1 : l2;
        return dummy.next;
    }
}
```
