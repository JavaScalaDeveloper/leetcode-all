# [328. 奇偶链表](https://leetcode.cn/problems/odd-even-linked-list)

## 题目描述

<p>给定单链表的头节点&nbsp;<code>head</code>&nbsp;，将所有索引为奇数的节点和索引为偶数的节点分别组合在一起，然后返回重新排序的列表。</p>

<p><strong>第一个</strong>节点的索引被认为是 <strong>奇数</strong> ， <strong>第二个</strong>节点的索引为&nbsp;<strong>偶数</strong> ，以此类推。</p>

<p>请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。</p>

<p>你必须在&nbsp;<code>O(1)</code>&nbsp;的额外空间复杂度和&nbsp;<code>O(n)</code>&nbsp;的时间复杂度下解决这个问题。</p>

<p><strong>示例 1:</strong></p>

<p><img src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0328.Odd%20Even%20Linked%20List/images/oddeven-linked-list.jpg" style="height: 123px; width: 300px;" /></p>

<pre>
<strong>输入: </strong>head = [1,2,3,4,5]
<strong>输出:</strong>&nbsp;[1,3,5,2,4]</pre>

<p><strong>示例 2:</strong></p>

<p><img src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0328.Odd%20Even%20Linked%20List/images/oddeven2-linked-list.jpg" style="height: 142px; width: 500px;" /></p>

<pre>
<strong>输入:</strong> head = [2,1,3,5,6,4,7]
<strong>输出:</strong> [2,3,6,7,1,5,4]</pre>

<p><strong>提示:</strong></p>

<ul>
	<li><code>n ==&nbsp;</code> 链表中的节点数</li>
	<li><code>0 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>6</sup>&nbsp;&lt;= Node.val &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

**方法一：一次遍历**

我们可以用两个指针a和b分别表示奇数节点和偶数节点的尾节点。初始时，指针a指向链表的头节点head，指针b指向链表的第二个节点head.next。另外，我们用一个指针c指向偶数节点的头节点head.next，即指针b的初始位置。

遍历链表，我们将指针a指向b的下一个节点，即a.next = b.next，然后将指针a向后移动一位，即a = a.next；将指针b指向a的下一个节点，即b.next = a.next，然后将指针b向后移动一位，即b = b.next。继续遍历，直到b到达链表的末尾。

最后，我们将奇数节点的尾节点a指向偶数节点的头节点c，即a.next = c，然后返回链表的头节点head即可。

时间复杂度O(n)，其中n是链表的长度，需要遍历链表一次。空间复杂度O(1)。只需要维护有限的指针。

### **Java**

```java
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode a = head;
        ListNode b = head.next, c = b;
        while (b != null && b.next != null) {
            a.next = b.next;
            a = a.next;
            b.next = a.next;
            b = b.next;
        }
        a.next = c;
        return head;
    }
}
```
