# [1290. 二进制链表转整数](https://leetcode.cn/problems/convert-binary-number-in-a-linked-list-to-integer)

## 题目描述

<p>给你一个单链表的引用结点&nbsp;<code>head</code>。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式。</p>

<p>请你返回该链表所表示数字的 <strong>十进制值</strong> 。</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1290.Convert%20Binary%20Number%20in%20a%20Linked%20List%20to%20Integer/images/graph-1.png" style="height: 108px; width: 426px;"></p>

<pre><strong>输入：</strong>head = [1,0,1]
<strong>输出：</strong>5
<strong>解释：</strong>二进制数 (101) 转化为十进制数 (5)
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>head = [0]
<strong>输出：</strong>0
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>head = [1]
<strong>输出：</strong>1
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>head = [1,0,0,1,0,0,1,1,1,0,0,0,0,0,0]
<strong>输出：</strong>18880
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>head = [0,0]
<strong>输出：</strong>0
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li>链表不为空。</li>
	<li>链表的结点总数不超过&nbsp;<code>30</code>。</li>
	<li>每个结点的值不是&nbsp;<code>0</code> 就是 <code>1</code>。</li>
</ul>

## 解法

**方法一：遍历链表**

我们用变量 `ans` 记录当前的十进制值，初始值为 $0$。

遍历链表，对于每个结点，将 `ans` 左移一位，然后再或上当前结点的值。遍历结束后，`ans` 即为十进制值。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为链表的长度。

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
    public int getDecimalValue(ListNode head) {
        int ans = 0;
        for (; head != null; head = head.next) {
            ans = ans << 1 | head.val;
        }
        return ans;
    }
}
```

**
