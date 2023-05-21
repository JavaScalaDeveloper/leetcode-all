# [1836. 从未排序的链表中移除重复元素](https://leetcode.cn/problems/remove-duplicates-from-an-unsorted-linked-list)

## 题目描述

<p>给定一个链表的第一个节点 <code>head</code> ，找到链表中所有出现<strong>多于一次</strong>的元素，并删除这些元素所在的节点。</p>

<p>返回删除后的链表。</p>



<p><strong>示例 1:</strong></p>
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1836.Remove%20Duplicates%20From%20an%20Unsorted%20Linked%20List/images/tmp-linked-list.jpg" style="width: 422px; height: 222px;">
<pre><strong>输入:</strong> head = [1,2,3,2]
<strong>输出:</strong> [1,3]
<strong>解释:</strong> 2 在链表中出现了两次，所以所有的 2 都需要被删除。删除了所有的 2 之后，我们还剩下 [1,3] 。
</pre>

<p><strong>示例 2:</strong></p>
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1836.Remove%20Duplicates%20From%20an%20Unsorted%20Linked%20List/images/tmp-linked-list-1.jpg" style="width: 422px; height: 151px;">
<pre><strong>输入:</strong> head = [2,1,1,2]
<strong>输出:</strong> []
<strong>解释:</strong> 2 和 1 都出现了两次。所有元素都需要被删除。
</pre>

<p><strong>示例 3:</strong></p>
<img alt="" src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1800-1899/1836.Remove%20Duplicates%20From%20an%20Unsorted%20Linked%20List/images/tmp-linked-list-2.jpg" style="width: 500px; height: 142px;">
<pre><strong>输入:</strong> head = [3,2,2,1,3,2,4]
<strong>输出:</strong> [1,4]
<strong>解释: </strong>3 出现了两次，且 2 出现了三次。移除了所有的 3 和 2 后，我们还剩下 [1,4] 。
</pre>



<p><b>提示：</b></p>

<ul>
	<li>链表中节点个数的范围是 <code>[1, 10<sup>5</sup>]</code></li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

**方法一：哈希表**

我们可以用哈希表 `cnt` 统计链表中每个元素出现的次数，然后遍历链表，删除出现次数大于 1 的元素。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为链表的长度。

### **Java**

```java
class Solution {
    public ListNode deleteDuplicatesUnsorted(ListNode head) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (ListNode cur = head; cur != null; cur = cur.next) {
            cnt.put(cur.val, cnt.getOrDefault(cur.val, 0) + 1);
        }
        ListNode dummy = new ListNode(0, head);
        for (ListNode pre = dummy, cur = head; cur != null; cur = cur.next) {
            if (cnt.get(cur.val) > 1) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
        }
        return dummy.next;
    }
}
```
