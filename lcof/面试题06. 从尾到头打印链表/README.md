# [面试题 06. 从尾到头打印链表](https://leetcode.cn/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/)

## 题目描述

<p>输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>head = [1,3,2]
<strong>输出：</strong>[2,3,1]</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<p><code>0 &lt;= 链表长度 &lt;= 10000</code></p>

## 解法

**方法一：顺序遍历 + 反转**

我们可以顺序遍历链表，将每个节点的值存入数组中，然后将数组反转。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为链表的长度。

**方法二：递归**

我们可以使用递归的方式，先递归得到 `head` 之后的节点反过来的值列表，然后将 `head` 的值加到列表的末尾。

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
    public int[] reversePrint(ListNode head) {
        Deque<Integer> stk = new ArrayDeque<>();
        for (; head != null; head = head.next) {
            stk.push(head.val);
        }
        int[] ans = new int[stk.size()];
        for (int i = 0; !stk.isEmpty(); ++i) {
            ans[i] = stk.pop();
        }
        return ans;
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
    public int[] reversePrint(ListNode head) {
        int n = 0;
        ListNode cur = head;
        for (; cur != null; cur = cur.next) {
            ++n;
        }
        int[] ans = new int[n];
        cur = head;
        for (; cur != null; cur = cur.next) {
            ans[--n] = cur.val;
        }
        return ans;
    }
}
```
