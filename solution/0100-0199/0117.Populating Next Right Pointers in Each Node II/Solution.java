/*
// Definition for a Node.
package com.solution._0117;
import change.datastructure.*;
import java.util.*;
public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

package com.solution._0117;
import change.datastructure.*;
import java.util.*;
public class Solution {
    private TreeNextLinkNode prev, next;

    public TreeNextLinkNode connect(TreeNextLinkNode root) {
        TreeNextLinkNode node = root;
        while (node != null) {
            prev = null;
            next = null;
            while (node != null) {
                modify((TreeNextLinkNode)node.left);
                modify((TreeNextLinkNode)node.right);
                node = node.next;
            }
            node = next;
        }
        return root;
    }

    private void modify(TreeNextLinkNode curr) {
        if (curr == null) {
            return;
        }
        if (next == null) {
            next = curr;
        }
        if (prev != null) {
            prev.next = curr;
        }
        prev = curr;
    }
}
