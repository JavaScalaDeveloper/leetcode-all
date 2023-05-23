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
package com.solution._0024;
import change.datastructure.*;
import change.tools.listnode.ListNodeUtils;

import java.util.*;
public class Solution {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        ListNode result = swapPairs(listNode);
        System.out.println(ListNodeUtils.getValues(result));
    }

    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy, cur = head;
        while (cur != null && cur.next != null) {
            ListNode t = cur.next;//t=2,3,4
            cur.next = t.next;//cur=1,3,4
            t.next = cur;//t=2,1,3,4
            pre.next = t;//pre=0,2,1,3,4
            pre = cur;//pre=1,3,4
            cur = cur.next;//cur=3,4
        }
        return dummy.next;
    }
}
