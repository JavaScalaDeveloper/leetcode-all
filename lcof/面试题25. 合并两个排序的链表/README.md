# [面试题 25. 合并两个排序的链表](https://leetcode.cn/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/)

## 题目描述

<p>输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。</p>

<p><strong>示例1：</strong></p>

<pre><strong>输入：</strong>1-&gt;2-&gt;4, 1-&gt;3-&gt;4
<strong>输出：</strong>1-&gt;1-&gt;2-&gt;3-&gt;4-&gt;4</pre>

<p><strong>限制：</strong></p>

<p><code>0 &lt;= 链表长度 &lt;= 1000</code></p>

<p>注意：本题与主站 21 题相同：<a href="https://leetcode.cn/problems/merge-two-sorted-lists/">https://leetcode.cn/problems/merge-two-sorted-lists/</a></p>

## 解法

**方法一：迭代**

我们先创建一个虚拟头结点 `dummy`，然后创建一个指针 `cur` 指向 `dummy`。

接下来，循环比较 `l1` 和 `l2` 的值，将较小的值接在 `cur` 后面，然后将指针向后移动一位。循环结束，将 `cur` 指向 `l1` 或者 `l2` 中剩余的部分。

最后返回 `dummy.next` 即可。

时间复杂度 $O(m + n)$，空间复杂度 $O(1)$。其中 $m$ 和 $n$ 分别为两个链表的长度。

**方法二：递归**

我们先判断 `l1` 和 `l2` 中有没有一个为空，如果有一个为空，那么我们直接返回另一个链表即可。

接下来，我们比较 `l1` 和 `l2` 的值：

-   如果 `l1` 的值小于等于 `l2` 的值，我们递归调用 `mergeTwoLists(l1.next, l2)`，并将 `l1.next` 指向返回的链表，然后返回 `l1`。
-   如果 `l1` 的值大于 `l2` 的值，我们递归调用 `mergeTwoLists(l1, l2.next)`，并将 `l2.next` 指向返回的链表，然后返回 `l2`。

时间复杂度 $O(m + n)$，空间复杂度 $O(m + n)$。其中 $m$ 和 $n$ 分别为两个链表的长度。

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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null ? l2 : l1;
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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val <= l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}
```

### **TypeScript**
