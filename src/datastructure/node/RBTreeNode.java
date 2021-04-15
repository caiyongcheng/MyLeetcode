package datastructure.node;

/**
 * @program: MyLeetcode
 * @description: 红黑树节点
 * @packagename: datastructure.node
 * @author: 6JSh5rC456iL
 * @date: 2021-04-12 14:39
 **/
public class RBTreeNode<T extends Comparable<T>> extends BTreeNode<T> {

    public static final int RBTREE_NOTE_COLOR_RED = 1;
    public static final int RBTREE_NOTE_COLOR_BLACK = 0;

    public static final int LEFT_CHILD = -1;
    public static final int RIGHT_CHILD = 1;
    public static final int NOTHING = 0;

    private int color;

    private RBTreeNode<T> parent;

    public RBTreeNode() {
        this.color = RBTREE_NOTE_COLOR_BLACK;
    }


    public RBTreeNode(T value) {
        super(value);
        this.color = RBTREE_NOTE_COLOR_RED;
    }


    public RBTreeNode(T value, int color) {
        super(value);
        this.color = color;
    }

    public RBTreeNode(T value, BTreeNode<T> leftChild, BTreeNode<T> rightChile, int color) {
        super(value, leftChild, rightChile);
        this.color = color;
    }

    public RBTreeNode(T value, int color, RBTreeNode<T> parent) {
        super(value);
        this.color = color;
        this.parent = parent;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public RBTreeNode<T> getParent() {
        return parent;
    }

    public void setParent(RBTreeNode<T> parent) {
        this.parent = parent;
    }
}
