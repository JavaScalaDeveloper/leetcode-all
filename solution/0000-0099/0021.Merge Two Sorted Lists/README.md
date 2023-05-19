# [21. 合并两个有序链表](https://leetcode.cn/problems/merge-two-sorted-lists)

[English Version](/solution/0000-0099/0021.Merge%20Two%20Sorted%20Lists/README_EN.md)

## 题目描述

<p>将两个升序链表合并为一个新的 <strong>升序</strong> 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 </p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0021.Merge%20Two%20Sorted%20Lists/images/merge_ex1.jpg" style="width: 662px; height: 302px;" />
<pre>
<strong>输入：</strong>l1 = [1,2,4], l2 = [1,3,4]
<strong>输出：</strong>[1,1,2,3,4,4]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>l1 = [], l2 = []
<strong>输出：</strong>[]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>l1 = [], l2 = [0]
<strong>输出：</strong>[0]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>两个链表的节点数目范围是 <code>[0, 50]</code></li>
	<li><code>-100 <= Node.val <= 100</code></li>
	<li><code>l1</code> 和 <code>l2</code> 均按 <strong>非递减顺序</strong> 排列</li>
</ul>

## 解法

**方法一：递归**

我们先判断链表 $l_1$ 和 $l_2$ 是否为空，若其中一个为空，则返回另一个链表。否则，我们比较 $l_1$ 和 $l_2$ 的头节点：

-   若 $l_1$ 的头节点的值小于等于 $l_2$ 的头节点的值，则递归调用函数 $mergeTwoLists(l_1.next, l_2)$，并将 $l_1$ 的头节点与返回的链表头节点相连，返回 $l_1$ 的头节点。
-   否则，递归调用函数 $mergeTwoLists(l_1, l_2.next)$，并将 $l_2$ 的头节点与返回的链表头节点相连，返回 $l_2$ 的头节点。

时间复杂度 $O(m + n)$，空间复杂度 $O(m + n)$。其中 $m$ 和 $n$ 分别为两个链表的长度。

**方法二：迭代**

我们也可以用迭代的方式来实现两个排序链表的合并。

我们先定义一个虚拟头节点 $dummy$，然后循环遍历两个链表，比较两个链表的头节点，将较小的节点添加到 $dummy$ 的末尾，直到其中一个链表为空，然后将另一个链表的剩余部分添加到 $dummy$ 的末尾。

最后返回 $dummy.next$ 即可。

时间复杂度 $O(m + n)$，其中 $m$ 和 $n$ 分别为两个链表的长度。忽略答案链表的空间消耗，空间复杂度 $O(1)$。

### **Java**

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val <= list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }
}
```

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }
        curr.next = list1 == null ? list2 : list1;
        return dummy.next;
    }
}
```
