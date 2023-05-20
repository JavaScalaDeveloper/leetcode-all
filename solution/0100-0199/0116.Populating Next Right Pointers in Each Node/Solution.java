/*
// Definition for a Node.
package com.solution._0116;
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

package com.solution._0116;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        Deque<Node> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            Node p = null;
            for (int n = q.size(); n > 0; --n) {
                Node node = q.poll();
                if (p != null) {
                    p.next = node;
                }
                p = node;
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
        }
        return root;
    }
}
