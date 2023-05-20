/*
// Definition for a Node.
package com.solution._1666;
import change.datastructure.*;
import java.util.*;
public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};
*/

package com.solution._1666;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public Node flipBinaryTree(Node root, Node leaf) {
        Node cur = leaf;
        Node p = cur.parent;
        while (cur != root) {
            Node gp = p.parent;
            if (cur.left != null) {
                cur.right = cur.left;
            }
            cur.left = p;
            p.parent = cur;
            if (p.left == cur) {
                p.left = null;
            } else if (p.right == cur) {
                p.right = null;
            }
            cur = p;
            p = gp;
        }
        leaf.parent = null;
        return leaf;
    }
}
