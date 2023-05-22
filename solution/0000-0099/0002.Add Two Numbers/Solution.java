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
package com.solution._0002;
import change.datastructure.*;
import change.tools.listnode.ListNodeUtils;

import java.util.*;
public class Solution {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3, null)));
        ListNode l2 = new ListNode(5, new ListNode(4, new ListNode(6, null)));
        ListNode listNode = addTwoNumbers(l1, l2);
        System.out.println(ListNodeUtils.getValues(listNode));
    }
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        int carry = 0;
        ListNode cur = dummy;
        while (l1 != null || l2 != null || carry != 0) {
            int s = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
            carry = s / 10;
            cur.next = new ListNode(s % 10);
            cur = cur.next;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        return dummy.next;
    }
}
