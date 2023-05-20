/*
// Definition for a Node.
package com.solution._0430;
import change.datastructure.*;
import java.util.*;
public class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

package com.solution._0430;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        Node dummy = new Node();
        dummy.next = head;
        preorder(dummy, head);
        dummy.next.prev = null;
        return dummy.next;
    }

    private Node preorder(Node pre, Node cur) {
        if (cur == null) {
            return pre;
        }
        cur.prev = pre;
        pre.next = cur;

        Node t = cur.next;
        Node tail = preorder(cur, cur.child);
        cur.child = null;
        return preorder(tail, t);
    }
}
