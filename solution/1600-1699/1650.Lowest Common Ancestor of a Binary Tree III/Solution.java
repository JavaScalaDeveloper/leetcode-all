/*
// Definition for a Node.
package com.solution._1650;
import change.datastructure.*;
import java.util.*;
public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};
*/

package com.solution._1650;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public Node lowestCommonAncestor(Node p, Node q) {
        Node a = p, b = q;
        while (a != b) {
            a = a.parent == null ? q : a.parent;
            b = b.parent == null ? p : b.parent;
        }
        return a;
    }
}
