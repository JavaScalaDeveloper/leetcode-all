/*
// Definition for a Node.
package com.solution._0510;
import change.datastructure.*;
import java.util.*;
public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};
*/

package com.solution._0510;
import change.datastructure.*;
import java.util.*;
public class Solution {
    public Node inorderSuccessor(Node node) {
        if (node.right != null) {
            node = node.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        return node.parent;
    }
}
