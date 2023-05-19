# [面试题 02.04. 分割链表](https://leetcode.cn/problems/partition-list-lcci)

[English Version](/lcci/02.04.Partition%20List/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个链表的头节点 <code>head</code> 和一个特定值<em> </em><code>x</code> ，请你对链表进行分隔，使得所有 <strong>小于</strong> <code>x</code> 的节点都出现在 <strong>大于或等于</strong> <code>x</code> 的节点之前。</p>

<p>你不需要&nbsp;<strong>保留</strong>&nbsp;每个分区中各节点的初始相对位置。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcci/02.04.Partition%20List/images/partition.jpg" style="width: 662px; height: 222px;" />

<pre>
<strong>输入：</strong>head = [1,4,3,2,5,2], x = 3
<strong>输出</strong>：[1,2,2,4,3,5]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>head = [2,1], x = 2
<strong>输出</strong>：[1,2]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
    <li>链表中节点的数目在范围 <code>[0, 200]</code> 内</li>
    <li><code>-100 &lt;= Node.val &lt;= 100</code></li>
    <li><code>-200 &lt;= x &lt;= 200</code></li>
</ul>

## 解法

**方法一：拼接链表**

创建两个链表，一个存放小于 `x` 的节点，另一个存放大于等于 `x` 的节点，之后进行拼接即可。

**方法二：头插法**

题中指出，**不需要保留节点的相对位置**。

1. 遍历链表。
2. 当节点符合小于 `x` 条件时，将其移动至头节点前方，成为新的头节点。
3. 忽略大于等于 `x` 的节点。

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
    public ListNode partition(ListNode head, int x) {
        ListNode l1 = new ListNode(0);
        ListNode l2 = new ListNode(0);
        ListNode cur1 = l1, cur2 = l2;
        while (head != null) {
            if (head.val < x) {
                cur1.next = head;
                cur1 = cur1.next;
            } else {
                cur2.next = head;
                cur2 = cur2.next;
            }
            head = head.next;
        }
        cur1.next = l2.next;
        cur2.next = null;
        return l1.next;
    }
}
```

### **TypeScript**
