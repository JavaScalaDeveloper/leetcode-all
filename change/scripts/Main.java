package change.scripts;

import change.datastructure.ListNode;
import change.tools.listnode.ListNodeUtils;


public class Main {
    public static void main(String[] args) {
//        1,2,3,4 -> 2,1,4,3
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        ListNode res = reverse(listNode);
        System.out.println(ListNodeUtils.getValues(res));
    }

    private static ListNode reverse(ListNode listNode) {
        ListNode cur=listNode; //234
        boolean flag=false;
        int val=0;
        while (cur.next != null) {
            if (!flag) {
                cur.val= cur.next.val;
                val = cur.val;
            } else {
                cur.val=val;
            }
            cur=cur.next;
//            ListNode next = cur.next;
//            cur.next=pre;
//            pre=cur;
//            cur=next;
        }
        return cur;
    }


}
