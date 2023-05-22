# [1019. 链表中的下一个更大节点](https://leetcode.cn/problems/next-greater-node-in-linked-list)

## 题目描述

<p>给定一个长度为&nbsp;<code>n</code>&nbsp;的链表&nbsp;<code>head</code></p>

<p>对于列表中的每个节点，查找下一个 <strong>更大节点</strong> 的值。也就是说，对于每个节点，找到它旁边的第一个节点的值，这个节点的值 <strong>严格大于</strong> 它的值。</p>

<p>返回一个整数数组 <code>answer</code> ，其中 <code>answer[i]</code> 是第 <code>i</code> 个节点( <strong>从1开始</strong> )的下一个更大的节点的值。如果第 <code>i</code> 个节点没有下一个更大的节点，设置&nbsp;<code>answer[i] = 0</code>&nbsp;。</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1019.Next%20Greater%20Node%20In%20Linked%20List/images/linkedlistnext1.jpg" /></p>

<pre>
<strong>输入：</strong>head = [2,1,5]
<strong>输出：</strong>[5,5,0]
</pre>

<p><strong>示例 2：</strong></p>

<p><img src="https://gcore.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1019.Next%20Greater%20Node%20In%20Linked%20List/images/linkedlistnext2.jpg" /></p>

<pre>
<strong>输入：</strong>head = [2,7,4,3,5]
<strong>输出：</strong>[7,0,5,5,0]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li>链表中节点数为&nbsp;<code>n</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= Node.val &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

**方法一：单调栈**

题目要求找到链表中每个节点的下一个更大的节点，即找到链表中每个节点的右边第一个比它大的节点。我们先遍历链表，将链表中的值存入数组nums中。那么对于数组nums中的每个元素，我们只需要找到它右边第一个比它大的元素即可。求下一个更大的元素的问题可以使用单调栈来解决。

我们从后往前遍历数组nums，维护一个从栈底到栈顶单调递减的栈stk，遍历过程中，如果栈顶元素小于等于当前元素，则循环将栈顶元素出栈，直到栈顶元素大于当前元素或者栈为空。

如果此时栈为空，则说明当前元素没有下一个更大的元素，否则当前元素的下一个更大的元素就是栈顶元素，更新答案数组ans。然后将当前元素入栈，继续遍历。

遍历结束后，返回答案数组ans即可。

时间复杂度O(n)，空间复杂度O(n)。其中n为链表的长度。

### **Java**

```java
class Solution {
    public int[] nextLargerNodes(ListNode head) {
        List<Integer> nums = new ArrayList<>();
        for (; head != null; head = head.next) {
            nums.add(head.val);
        }
        Deque<Integer> stk = new ArrayDeque<>();
        int n = nums.size();
        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            while (!stk.isEmpty() && stk.peek() <= nums.get(i)) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                ans[i] = stk.peek();
            }
            stk.push(nums.get(i));
        }
        return ans;
    }
}
```
