package change.scripts;

import change.datastructure.TreeNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/*
非负整数数组，将元素随机排序，将所有元素连接起来形成一个新的数字，找到最小的数字
010，100->10010
5,56->556
0,56->56
 */
public class Solution {

    public static void main(String[] args) throws InterruptedException {
        TreeNode treeNode = new TreeNode(1, new TreeNode(2, new TreeNode(4,null,null), null), new TreeNode(3, null, null));
        bfs(treeNode);
    }

    private static void bfs(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(treeNode);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == size - 1) {
                    System.out.println(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
    }

}


