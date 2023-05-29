package com.solution._0116;

import change.datastructure.TreeNextLinkNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    public TreeNextLinkNode connect(TreeNextLinkNode root) {
        if (root == null) {
            return root;
        }
        Deque<TreeNextLinkNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNextLinkNode p = null;
            for (int n = q.size(); n > 0; --n) {
                TreeNextLinkNode node = q.poll();
                if (p != null) {
                    p.next = node;
                }
                p = node;
                if (node.left != null) {
                    q.offer((TreeNextLinkNode) node.left);
                }
                if (node.right != null) {
                    q.offer((TreeNextLinkNode) node.right);
                }
            }
        }
        return root;
    }
}
