package change.datastructure;

/**
 * 这个实体类包含了二叉树节点的value、左子节点、右子节点和指向父节点的指针。可以根据需求进行修改和扩展。
 *
 * 需要注意的是，在构造函数中，我们默认将左子节点、右子节点和父节点初始化为null，因为有些二叉树节点并不一定拥有左右子节点和父节点，这样当节点不存在时，这些变量就能够保证为null。
 */
//@Data
public class TreeParentLinkNode extends TreeNode{
    //指向父亲结点的指针
    public TreeParentLinkNode parent = null;

    public TreeParentLinkNode() {
    }

    public TreeParentLinkNode(int val) {
        this.val = val;
    }

    public TreeParentLinkNode(int val, TreeNode left, TreeNode right, TreeParentLinkNode parent) {
        super(val, left, right);
        this.parent = parent;
    }

    public TreeParentLinkNode(int val, TreeParentLinkNode left, TreeParentLinkNode right, TreeParentLinkNode parent) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }
}
