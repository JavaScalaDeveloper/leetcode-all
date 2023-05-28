/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
package com.solution._0061;

import change.datastructure.ListNode;
import change.tools.listnode.ListNodeUtils;

public class Solution {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
        ListNode res1 = rotateRight(listNode, 2);
//        ListNode res2 = rotateRight2(listNode, 2);
        System.out.println(ListNodeUtils.getValues(res1));
//        System.out.println(ListNodeUtils.getValues(res2));
    }

    public static ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        int n = 0;
        for (ListNode cur = head; cur != null; cur = cur.next) {
            ++n;
        }
        k %= n;
        if (k == 0) {
            return head;
        }
        ListNode slow = head, fast = head;
        while (k-- > 0) {
            fast = fast.next;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        ListNode start = slow.next;
        slow.next = null;//上一步head=1,2,3,4,5 slow=3,4,5 fast=5 start=4,5 下一步head=1,2,3 slow=3,fast=5
        fast.next = head;//下一步fast=5,1,2,3 start=4,5,1,2,3
        return start;
    }
}
