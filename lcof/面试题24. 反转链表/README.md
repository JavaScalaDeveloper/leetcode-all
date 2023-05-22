# [面试题 24. 反转链表](https://leetcode.cn/problems/fan-zhuan-lian-biao-lcof/)

## 题目描述

<p>定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。</p>



<p><strong>示例:</strong></p>

<pre><strong>输入:</strong> 1-&gt;2-&gt;3-&gt;4-&gt;5-&gt;NULL
<strong>输出:</strong> 5-&gt;4-&gt;3-&gt;2-&gt;1-&gt;NULL</pre>



<p><strong>限制：</strong></p>

<p><code>0 &lt;= 节点个数 &lt;= 5000</code></p>



<p><strong>注意</strong>：本题与主站 206 题相同：<a href="https://leetcode.cn/problems/reverse-linked-list/">https://leetcode.cn/problems/reverse-linked-list/</a></p>

## 解法

**方法一：头插法**

创建虚拟头节点 $dummy$，遍历链表，将每个节点依次插入 $dummy$ 的下一个节点。遍历结束，返回 $dummy.next$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为链表的长度。

**方法二：递归**

递归反转链表的第二个节点到尾部的所有节点，然后 $head$ 插在反转后的链表的尾部。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为链表的长度。

### **Java**

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = dummy.next;
            dummy.next = curr;
            curr = next;
        }
        return dummy.next;
    }
}
```

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode ans = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return ans;
    }
}
```
